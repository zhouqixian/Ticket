package com.example.myticket.activities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.myticket.R;
import com.example.myticket.R.layout;
import com.example.myticket.db.DataBaseHelper;
import com.example.myticket.db.MyApplication;
import com.example.myticket.entities.Cinema;
import com.example.myticket.entities.Location;
import com.example.myticket.entities.Movie;
import com.example.myticket.entities.ProductDescription;
import com.example.myticket.entities.ScreeningRoom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ProductActivity extends Activity {
	private SimpleAdapter simpleAdapter;
	private List<Map<String, Object>> _listItems;
	private ListView _pd_lv;
	private MyApplication _application;
	private DataBaseHelper dbHandler;
	private Movie movie;
	private ImageView _back;
	private ArrayList<ProductDescription> _products;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product);
		initView();
		setAdapter();
		initEvent();
	}
	private void initView() {
		_application = (MyApplication)getApplication();
		dbHandler = DataBaseHelper.getInstance(this);
		movie = (Movie)getIntent().getSerializableExtra("movie");
		_pd_lv = (ListView)findViewById(R.id.product_list);
		_back = (ImageView)findViewById(R.id.product_back);
	}
	private void initEvent() {
		_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		_pd_lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ProductActivity.this, ReserveActivity.class);
				intent.putExtra("product", _products.get(arg2));
				startActivityForResult(intent, 111);
			}
		});
	}
	private void setAdapter() {
		if (movie == null) return;
		_products = dbHandler.queryProductDescriptionsByMovie(movie.getMovie_id());
		_listItems = new ArrayList<Map<String,Object>>();
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
		for (ProductDescription i : _products) {
			Cinema cinema = dbHandler.queryCinema(i.getCinema_id());
			ScreeningRoom screeningRoom = dbHandler.queryScreeningRoom(i.getScreening_room_id());
			if (cinema != null && screeningRoom != null) {
				Location location = dbHandler.queryLocation(cinema.getDist_code());
				if (location != null) {
					Map<String,Object> item = new HashMap<String, Object>();
					item.put("cinema_name", cinema.getCinema_name());
					item.put("cinema_address", location.getAddress_name());
					item.put("type", i.getType());
					item.put("screening_room", screeningRoom.getScreening_room_name());
					Date start = i.getStartTime();
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(start);
					calendar.add(Calendar.MINUTE, movie.getDuration());
					Date end = calendar.getTime();
					item.put("start_time", sdf.format(start));
					item.put("end_time", sdf1.format(end));
					item.put("price", Float.toString(i.getPrice()));
					_listItems.add(item);
				}
			}
		}
		if (!_listItems.isEmpty()) {
			simpleAdapter = new SimpleAdapter(this, _listItems, R.layout.products_list_item, 
					new String[]{"cinema_name", "cinema_address", "type", "screening_room", "start_time", "end_time", "price"}, 
					new int[]{R.id.product_list_cinema_name, R.id.product_list_address, R.id.product_list_type, R.id.product_list_screening_room, R.id.product_list_start_time, R.id.product_list_end_time, R.id.product_list_price});
			_pd_lv.setAdapter(simpleAdapter);
		}
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 111 && resultCode == 112) {
			setResult(112);
			finish();
		}
	}
}
