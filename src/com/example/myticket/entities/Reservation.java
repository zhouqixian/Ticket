package com.example.myticket.entities;

import java.io.Serializable;
import java.util.Date;

// Reservation class
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
	private float total_price;
	private int ticket_quantity;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("Reservation{reservation_id:%s, product_description_id:%s, reservationTime:%s, phone:%s, seat:%s, total_price:%f, ticket_quantity:%d}", 
				reservation_id, product_description_id, reservationTime.toString(), phone, seat, total_price, ticket_quantity);
	}

	// get and set functions
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

	public float getTotal_price() {
		return total_price;
	}

	public void setTotal_price(float total_price) {
		this.total_price = total_price;
	}

	public int getTicket_quantity() {
		return ticket_quantity;
	}

	public void setTicket_quantity(int ticket_quantity) {
		this.ticket_quantity = ticket_quantity;
	}
}
