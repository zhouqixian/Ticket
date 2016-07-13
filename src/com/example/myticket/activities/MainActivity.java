package com.example.myticket.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.myticket.R;
import com.example.myticket.db.DataBaseHelper;
import com.example.myticket.db.MyApplication;
import com.example.myticket.entities.Movie;
import com.example.myticket.entities.User;

public class MainActivity extends Activity {
	private TextView _main_text, _logout_tv;
	private ListView _movies_view;
	private ArrayList<Movie> _movies;
	private MyApplication _application;
	private DataBaseHelper dbHandler;
	private List<Map<String, Object>> _listItems;
	private SimpleAdapter simpleAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		setAdapter();
		initEvent();
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		User temp = _application.getUser();
		if (temp != null) {
			_main_text.setText(temp.getName());
			_logout_tv.setVisibility(View.VISIBLE);
		}
		else {
			_logout_tv.setVisibility(View.GONE);
		}
		setAdapter();
	}
	private void initEvent() {
		_main_text.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (_application.getUser() == null) {
					startActivity(new Intent(MainActivity.this, LoginActivity.class));
				}
				else {
					startActivity(new Intent(MainActivity.this, UserInfoActivity.class));
				}
			}
		});
		_logout_tv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
				SharedPreferences.Editor editor = sharedPreferences.edit();
				editor.putString("phone", " ");
				editor.commit();
				_application.setUser(null);
				_main_text.setText("ÇëµÇÂ¼");
				_logout_tv.setVisibility(View.GONE);
			}
		});
		_movies_view.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, MovieInfoActivity.class);
				intent.putExtra("movie", _movies.get(arg2));
				startActivity(intent);
			}
		});
	}
	private void setAdapter() {
		_movies = dbHandler.queryAllMovie();
		if (_movies == null) return;
		_listItems = new ArrayList<Map<String,Object>>();
		for (Movie movie : _movies) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("img", _application.getPhotoMap().get(movie.getPhoto()));
			item.put("name", movie.getMovie_name());
			item.put("point", Float.toString(movie.getPoint()));
			item.put("type", movie.getTag());
			item.put("director", movie.getDirector());
			item.put("actors", movie.getActors());
			item.put("saleAccount", movie.getSale_account());
			_listItems.add(item);
		}
		simpleAdapter = new SimpleAdapter(this, _listItems, R.layout.movies_list_item, 
				new String[]{"img", "name", "point", "type", "director", "actors", "saleAccount"}, 
				new int[]{R.id.movies_list_img, R.id.movies_list_name, R.id.movies_list_point, R.id.movies_list_type, R.id.movies_list_director, R.id.movies_list_actors, R.id.movies_list_sale_account});
		_movies_view.setAdapter(simpleAdapter);
		
	}
	private void initView() {
		_main_text = (TextView)findViewById(R.id.main_user_name);
		_movies_view = (ListView)findViewById(R.id.main_movie_list);
		_logout_tv = (TextView)findViewById(R.id.main_user_logout);
		_application = (MyApplication)getApplication();
		dbHandler = DataBaseHelper.getInstance(this);
	}
}
