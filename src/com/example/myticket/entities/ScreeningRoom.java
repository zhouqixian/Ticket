package com.example.myticket.entities;

import java.io.Serializable;

public class ScreeningRoom implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8741085925680660372L;
	private String screening_room_id;
	private String screening_room_name;
	private int row;
	private int col;
	public ScreeningRoom() {
		// TODO Auto-generated constructor stub
	}
	public ScreeningRoom(String id, String name, int row, int col) {
		// TODO Auto-generated constructor stub
		screening_room_id = id;
		screening_room_name = name;
		this.row = row;
		this.col = col;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("ScreeningRoom{screening_room_id:%s, screening_room_name:%s, row:%d, col:%d}", 
				screening_room_id, screening_room_name, row, col);
	}
	public String getScreening_room_id() {
		return screening_room_id;
	}
	public void setScreening_room_id(String screening_room_id) {
		this.screening_room_id = screening_room_id;
	}
	public String getScreening_room_name() {
		return screening_room_name;
	}
	public void setScreening_room_name(String screening_room_name) {
		this.screening_room_name = screening_room_name;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	
}
