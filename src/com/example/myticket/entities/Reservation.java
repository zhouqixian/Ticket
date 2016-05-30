package com.example.myticket.entities;

import java.io.Serializable;
import java.util.Date;

public class Reservation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2058060653513311881L;
	private String reservation_id;
	private String product_description_id;
	private Date reservationTime;
	private String phone;
	private String seat;
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("Reservation{reservation_id:%s, product_description_id:%s, reservationTime:%s, phone:%s, seat:%s}", 
				reservation_id, product_description_id, reservationTime.toString(), phone, seat);
	}

	public Date getReservationTime() {
		return reservationTime;
	}
	public void setReservationTime(Date reservationTime) {
		this.reservationTime = reservationTime;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getReservation_id() {
		return reservation_id;
	}
	public void setReservation_id(String reservation_id) {
		this.reservation_id = reservation_id;
	}
	public String getProduct_description_id() {
		return product_description_id;
	}
	public void setProduct_description_id(String product_description_id) {
		this.product_description_id = product_description_id;
	}
	public String getSeat() {
		return seat;
	}
	public void setSeat(String seat) {
		this.seat = seat;
	}

}
