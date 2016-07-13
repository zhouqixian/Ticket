package com.example.myticket.entities;

import java.io.Serializable;

public class Cinema implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4533039824018335374L;
	private String cinema_id;
	private String dist_code;
	private String cinema_name;
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("Cinema{cinema_id:%s, dist_code:%s, cinema_name:%s}", cinema_id, dist_code, cinema_name);
	}
	public String getCinema_id() {
		return cinema_id;
	}
	public void setCinema_id(String cinema_id) {
		this.cinema_id = cinema_id;
	}
	public String getDist_code() {
		return dist_code;
	}
	public void setDist_code(String dist_code) {
		this.dist_code = dist_code;
	}
	public String getCinema_name() {
		return cinema_name;
	}
	public void setCinema_name(String cinema_name) {
		this.cinema_name = cinema_name;
	}
}
