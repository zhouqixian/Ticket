package com.example.myticket.db;

import com.example.myticket.entities.User;

import android.app.Application;

public class MyApplication extends Application{
	private User _u;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		_u = null;
	}
	public void setUser(User user) {
		this._u = user;
	}
	public User getUser() {
		return _u;
	}
}
