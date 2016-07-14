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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MovieInfoActivity extends Activity {
	private TextView _name_tv, _point_tv, _tag_tv, _language_tv, _duration_tv, _director_tv;
	private TextView _actors_tv, _plot_tv;
	private ImageView _img, _back;
	private Button _reserve_btn;
	private MyApplication _application;
	private DataBaseHelper dbHandler;
	private Movie movie;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movie_info);
		initView();
		initEvent();
	}

	// init variables
	private void initView() {
		_application = (MyApplication)getApplication();
		dbHandler = DataBaseHelper.getInstance(this);
		_name_tv = (TextView)findViewById(R.id.movie_info_name);
		_point_tv = (TextView)findViewById(R.id.movie_info_point);
		_tag_tv = (TextView)findViewById(R.id.movie_info_tag);
		_language_tv = (TextView)findViewById(R.id.movie_info_language);
		_duration_tv = (TextView)findViewById(R.id.movie_info_duration);
		_director_tv = (TextView)findViewById(R.id.movie_info_director);
		_actors_tv = (TextView)findViewById(R.id.movie_info_actors);
		_plot_tv = (TextView)findViewById(R.id.movie_info_plot);
		_img = (ImageView)findViewById(R.id.movie_info_img);
		_back = (ImageView)findViewById(R.id.movie_info_back);
		_reserve_btn = (Button)findViewById(R.id.movie_info_reserve);
		movie = (Movie)getIntent().getSerializableExtra("movie");

		// Show movie info in detail
		if (movie != null) {
			_img.setImageResource(_application.getPhotoMap().get(movie.getPhoto()));
			_name_tv.setText(movie.getMovie_name());
			_point_tv.setText(Float.toString(movie.getPoint()));
			_tag_tv.setText(movie.getTag());
			_language_tv.setText(movie.getLanguages());
			_duration_tv.setText(Integer.toString(movie.getDuration()));
			_director_tv.setText(movie.getDirector());
			_actors_tv.setText(movie.getDirector());
			_plot_tv.setText(movie.getDescription());
		}
	}

	// set listeners
	private void initEvent() {
		_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		_reserve_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MovieInfoActivity.this, ProductActivity.class);
				intent.putExtra("movie", movie);
				startActivityForResult(intent, 111);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 111 && resultCode == 112) {
			finish();
		}
	}
}
