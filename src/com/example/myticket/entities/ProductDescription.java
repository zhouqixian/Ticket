package com.example.myticket.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class ProductDescription implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5162643260685440280L;
	private String product_description_id;
	private String movie_id;
	private String cinema_id;
	private String screening_room_id;
	private String type;
	private Date start_time;
	private float price;
	private String seat_availible;
	
	public ProductDescription() {
		// TODO Auto-generated constructor stub
	}
	
	public ProductDescription(String pid, String mid, String cid, String sid) {
		// TODO Auto-generated constructor stub
		product_description_id = pid;
		movie_id = mid;
		cinema_id = cid;
		screening_room_id = sid;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("ProductDescription{product_description_id:%s, movie_id:%s, cinema_id:%s, "
				+ "screening_room_id:%s, type:%s, time:%s, price:%f, seat_availible:%s}", 
				product_description_id, movie_id, cinema_id, screening_room_id, type, start_time.toString(), price, seat_availible);
	}
	public String getProduct_description_id() {
		return product_description_id;
	}
	public void setProduct_description_id(String product_description_id) {
		this.product_description_id = product_description_id;
	}
	public String getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(String movie_id) {
		this.movie_id = movie_id;
	}
	public String getCinema_id() {
		return cinema_id;
	}
	public void setCinema_id(String cinema_id) {
		this.cinema_id = cinema_id;
	}
	public String getScreening_room_id() {
		return screening_room_id;
	}
	public void setScreening_room_id(String screening_room_id) {
		this.screening_room_id = screening_room_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getStartTime() {
		return start_time;
	}
	public void setStartTime(Date time) {
		this.start_time = time;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getSeat_availible() {
		return seat_availible;
	}
	public void setSeat_availible(String seat_availible) {
		this.seat_availible = seat_availible;
	}

}
