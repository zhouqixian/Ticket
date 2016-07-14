package com.example.myticket.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myticket.R;
import com.example.myticket.db.DataBaseHelper;
import com.example.myticket.db.MyApplication;
import com.example.myticket.entities.User;

public class LoginActivity extends Activity{
	private Button _login_btn, _signup_btn;
	private EditText _phone_edt, _password_edt;
	private DataBaseHelper dbHandler;
	private MyApplication _application;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
		initEvent();
	}

	// init variables
	private void initView() {
		dbHandler = DataBaseHelper.getInstance(this);
		_application = (MyApplication)getApplication();
		_login_btn = (Button)findViewById(R.id.login_login_btn);
		_signup_btn = (Button)findViewById(R.id.login_signup_btn);
		_password_edt = (EditText)findViewById(R.id.login_password_edt);
		_phone_edt = (EditText)findViewById(R.id.login_phone_edt);
	}

	// set listeners
	private void initEvent() {
		_signup_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(LoginActivity.this, SignupActivity.class));
			}
		});

		_login_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				loginFunction();
			}
		});
	}

	// Login
	private void loginFunction() {
		String phone = _phone_edt.getText().toString();
		String pw = _password_edt.getText().toString();

		// Blank info
		if (phone.equals("") || pw.equals("")) {
			Toast.makeText(this, "’À∫≈ªÚ√‹¬Î≤ªƒ‹Œ™ø’", Toast.LENGTH_SHORT).show();
			return;
		}

		User temp = dbHandler.queryUser(phone);
		// User not existed
		if (temp == null) {
			Toast.makeText(this, "’À∫≈≤ª¥Ê‘⁄", Toast.LENGTH_SHORT).show();
			_phone_edt.setText("");
			_password_edt.setText("");
			return;
		}

		// Wrong password
		if (!temp.getPassword().equals(pw)) {
			Toast.makeText(this, "√‹¬Î¥ÌŒÛ", Toast.LENGTH_SHORT).show();
			_phone_edt.setText("");
			_password_edt.setText("");
			return;
		}

		// DB opeartions
		_application.setUser(temp);
		SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString("phone", temp.getUser_id());
		editor.commit();
		
		finish();
	}
}
