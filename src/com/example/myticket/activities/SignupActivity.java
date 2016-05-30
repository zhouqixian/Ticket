package com.example.myticket.activities;

import com.example.myticket.R;
import com.example.myticket.db.DataBaseHelper;
import com.example.myticket.db.MyApplication;
import com.example.myticket.entities.User;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends Activity{
	private EditText _phone_edt, _name_edt, _pw_edt, _pw_confirm_edt;
	private Button _signup_btn;
	private DataBaseHelper dbHandler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		initView();
		initEvent();
	}
	private void initView() {
		dbHandler = DataBaseHelper.getInstance(this);
		_phone_edt = (EditText)findViewById(R.id.signup_phone_edt);
		_name_edt = (EditText)findViewById(R.id.signup_name_edt);
		_pw_edt = (EditText)findViewById(R.id.signup_password_edt);
		_pw_confirm_edt = (EditText)findViewById(R.id.signup_confirm_password_edt);
		_signup_btn = (Button)findViewById(R.id.signup_signup_btn);
	}
	private void initEvent() {
		_signup_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				signupFunction();
			}
		});
	}
	private void signupFunction() {
		String phone = _phone_edt.getText().toString();
		String name = _name_edt.getText().toString();
		String pw1 = _pw_edt.getText().toString();
		String pw2 = _pw_confirm_edt.getText().toString();
		if (phone.equals("") || name.equals("") || pw1.equals("") || pw2.equals("")) {
			Toast.makeText(this, "信息不能为空", Toast.LENGTH_SHORT).show();
			return;
		}
		if (!pw1.equals(pw2)) {
			Toast.makeText(this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
			_pw_edt.setText("");
			_pw_confirm_edt.setText("");
			return;
		}
		User temp = dbHandler.queryUser(phone);
		if (temp != null) {
			Toast.makeText(this, "用该手机号注册的用户已存在", Toast.LENGTH_SHORT).show();
			_phone_edt.setText("");
			_name_edt.setText("");
			_pw_edt.setText("");
			_pw_confirm_edt.setText("");
			return;
		}
		temp = new User();
		temp.setUser_id(phone);
		temp.setPassword(pw1);
		temp.setName(name);
		temp.setDist_code("440106 510510");
		dbHandler.addUser(temp);
		Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
		finish();
	}
}
