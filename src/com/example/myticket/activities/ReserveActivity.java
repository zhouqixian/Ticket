package com.example.myticket.activities;

import java.util.ArrayList;
import java.util.Date;

import com.example.myticket.R;
import com.example.myticket.R.layout;
import com.example.myticket.db.DataBaseHelper;
import com.example.myticket.db.MyApplication;
import com.example.myticket.entities.Movie;
import com.example.myticket.entities.ProductDescription;
import com.example.myticket.entities.Reservation;
import com.example.myticket.entities.ScreeningRoom;

import android.R.integer;
import android.app.Activity;
import android.graphics.Paint.Join;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioButton;
import android.widget.Toast;

public class ReserveActivity extends Activity {
	private LinearLayout _select_zoom;
	private ArrayList<ImageView> _select_items;
	private EditText _phone_edt;
	private TextView _total_tv;
	private Button _ok_btn;
	private ImageView _back;
	private ProductDescription _product;
	private ScreeningRoom screeningRoom;
	private DataBaseHelper dbHandler;
	private MyApplication _application;
	private int _select_counter = 0;
	private String[] _seat_state;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reserve);
		initView();
		initEvent();
	}
	private void initView() {
		_select_items = new ArrayList<ImageView>();
		dbHandler = DataBaseHelper.getInstance(this);
		_application = (MyApplication)getApplication();
		_product = (ProductDescription)getIntent().getSerializableExtra("product");
		_select_zoom = (LinearLayout)findViewById(R.id.reserve_seat_select_zoom);
		_back = (ImageView)findViewById(R.id.reserve_back);
		_ok_btn = (Button)findViewById(R.id.reserve_ok_btn);
		_total_tv = (TextView)findViewById(R.id.reserve_total);
		_phone_edt = (EditText)findViewById(R.id.reserve_phone_edt);
		if (_application.getUser() != null) {
			_phone_edt.setVisibility(View.GONE);
		}
		initSelectZoom();
	}
	private void initEvent() {
		_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		_ok_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String phone;
				if (_select_counter == 0) {
					Toast.makeText(ReserveActivity.this, "请选择座位", Toast.LENGTH_SHORT).show();
					return;
				}
				if (_phone_edt.getVisibility() != View.GONE) {
					phone = _phone_edt.getText().toString();
					if (phone.equals("")) {
						Toast.makeText(ReserveActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
						return;
					}
				}
				else {
					phone = _application.getUser().getUser_id();
				}
				Reservation reservation = new Reservation();
				reservation.setPhone(phone);
				reservation.setProduct_description_id(_product.getProduct_description_id());
				reservation.setTicket_quantity(_select_counter);
				reservation.setTotal_price(_select_counter * _product.getPrice());
				ArrayList<String> seats = new ArrayList<String>();
				for (int i = 0; i < _select_items.size(); i++) {
					if (_select_items.get(i).isSelected()) {
						int row = i / screeningRoom.getCol() + 1;
						int col = i % screeningRoom.getCol() + 1;
						seats.add(Integer.toString(row) + "," + Integer.toString(col));
						_seat_state[i] = "0";
					}
				}
				_product.setSeat_availible(Join(_seat_state, ","));
				dbHandler.updateProductDescription(_product);
				reservation.setSeat(Join(seats.toArray(new String[]{}), "/"));
				reservation.setReservationTime(new Date(System.currentTimeMillis()));
				String s = "r" + Integer.toString((int)(1+Math.random()*(1000000-1+1)));
				while (dbHandler.queryReservation(s) != null) {
					s = "r" + Integer.toString((int)(1+Math.random()*(1000000-1+1)));
				}
				reservation.setReservation_id(s);
				dbHandler.addReservation(reservation);
				Movie movie = dbHandler.queryMovie(_product.getMovie_id());
				movie.setSale_account(movie.getSale_account() + _select_counter);
				dbHandler.updateMovie(movie);
				setResult(112);
				finish();
			}
		});
	}
	private void initSelectZoom() {
		if (_product == null) return;
		_seat_state = _product.getSeat_availible().split(",");
		screeningRoom = dbHandler.queryScreeningRoom(_product.getScreening_room_id());
		if (screeningRoom == null) return;
		for (int i = 0; i < screeningRoom.getRow(); i++) {
			LinearLayout linearLayout = new LinearLayout(this);
			linearLayout.setOrientation(LinearLayout.HORIZONTAL);
			linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			for (int j = 0; j < screeningRoom.getCol(); j++) {
				int index = i * screeningRoom.getCol() + j;
				ImageView imageView = new ImageView(this);
				LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
				lp.weight = 1;
				imageView.setLayoutParams(lp);
				imageView.setAdjustViewBounds(true);
				imageView.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if (v.isSelected()) {
							v.setSelected(false);
							_select_counter--;
						}
						else {
							v.setSelected(true);
							_select_counter++;
						}
						_total_tv.setText(Float.toString((_product.getPrice() * _select_counter)));
					}
				});
				if (_seat_state[index].equals("1")) {
					imageView.setImageResource(R.drawable.seat_selector);
				} else {
					imageView.setImageResource(R.drawable.ic_seat_have_selected);
					imageView.setClickable(false);
				}
				_select_items.add(imageView);
				linearLayout.addView(imageView);
			}
			_select_zoom.addView(linearLayout);
		}
	}
	private String Join(String[] strs, String splite) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < strs.length - 1; i++) {
			stringBuilder.append(strs[i]).append(splite);
		}
		stringBuilder.append(strs[strs.length - 1]);
		return stringBuilder.toString();
	}
}
