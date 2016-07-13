package com.example.myticket.entities;

import java.io.Serializable;

public class Location implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5923610321266764084L;
	private String dist_code;
	private String address_name;
	public Location() {
		// TODO Auto-generated constructor stub
	}
	public Location(String s1, String s2) {
		// TODO Auto-generated constructor stub
		dist_code = s1;
		address_name = s2;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("Location{dist_code:%s, address_name:%s}", 
				dist_code, address_name);
	}
	public String getDist_code() {
		return dist_code;
	}
	public void setDist_code(String dist_code) {
		this.dist_code = dist_code;
	}
	public String getAddress_name() {
		return address_name;
	}
	public void setAddress_name(String address_name) {
		this.address_name = address_name;
	}
}
