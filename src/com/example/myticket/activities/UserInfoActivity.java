package com.example.myticket.activities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.myticket.R;
import com.example.myticket.R.id;
import com.example.myticket.R.layout;
import com.example.myticket.db.DataBaseHelper;
import com.example.myticket.db.MyApplication;
import com.example.myticket.entities.Cinema;
import com.example.myticket.entities.Movie;
import com.example.myticket.entities.ProductDescription;
import com.example.myticket.entities.Reservation;
import com.example.myticket.entities.ScreeningRoom;
import com.example.myticket.entities.User;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class UserInfoActivity extends Activity {
	private ImageView _back;
	private ListView _reservation_lv;
	private DataBaseHelper dbHandler;
	private MyApplication _application;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info);
		initView();
		initEvent();
	}
	private void initView() {
		dbHandler = DataBaseHelper.getInstance(this);
		_application = (MyApplication)getApplication();
		_back = (ImageView)findViewById(R.id.user_info_back);
		_reservation_lv = (ListView)findViewById(R.id.user_info_reservation_list);
		setAdapter();
	}
	private void initEvent() {
		_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
	private void setAdapter() {
		User user = _application.getUser();
		ArrayList<Reservation> reservations = dbHandler.queryReservationsByUserId(user.getUser_id());
		if (reservations == null) return;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		List<Map<String, Object>> listItems = new ArrayList<Map<String,Object>>();
		for (Reservation reservation : reservations) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("time", df.format(reservation.getReservationTime()));
			item.put("total_price", Float.toString(reservation.getTotal_price()));
			item.put("quantity", Integer.toString(reservation.getTicket_quantity()));
			String[] seats = reservation.getSeat().split("/");
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < seats.length - 1; i++) {
				sb.append(seats[i].split(",")[0]);
				sb.append("ее");
				sb.append(seats[i].split(",")[1]);
				sb.append("╨е\n");
			}
			sb.append(seats[seats.length - 1].split(",")[0]);
			sb.append("ее");
			sb.append(seats[seats.length - 1].split(",")[1]);
			sb.append("╨е");
			item.put("seat", sb.toString());
			ProductDescription productDescription = dbHandler.queryProductDescription(reservation.getProduct_description_id());
			Movie movie = dbHandler.queryMovie(productDescription.getMovie_id());
			Cinema cinema = dbHandler.queryCinema(productDescription.getCinema_id());
			ScreeningRoom screeningRoom = dbHandler.queryScreeningRoom(productDescription.getScreening_room_id());
			item.put("movie_name", movie.getMovie_name());
			item.put("cinema_name", cinema.getCinema_name());
			item.put("sr_name", screeningRoom.getScreening_room_name());
			listItems.add(item);
		}
		SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems, R.layout.reservations_list_item, 
				new String[]{"time", "total_price", "quantity", "seat", "movie_name", "cinema_name", "sr_name"}, 
				new int[]{R.id.reservation_list_time, R.id.reservation_list_price, R.id.reservation_list_quantity, R.id.reservation_list_seat, R.id.reservation_list_movie_name, R.id.reservation_list_cinema_name, R.id.reservation_list_screening_room_name});
		_reservation_lv.setAdapter(simpleAdapter);
	}
}
