package com.example.myticket.entities;

import java.io.Serializable;

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6861234226441106078L;
	private String user_id;
	private String name;
	private String password;
	private String dist_code;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDist_code() {
		return dist_code;
	}
	public void setDist_code(String dist_code) {
		this.dist_code = dist_code;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("User{user_id:%s, name:%s, password:%s, dist_code:%s}", 
				user_id, name, password, dist_code);
	}
}
