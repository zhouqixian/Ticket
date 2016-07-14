package com.example.myticket.entities;

import java.io.Serializable;
import java.util.ArrayList;

// Movie class
public class Movie implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3926320723679082084L;
	private String movie_id;
	private String movie_name;
	private String tag;
	private String actors;
	private String director;
	private String photo;
	private String description;
	private String languages;
	private int duration;
	private long sale_account;
	private float point;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("Movie{movie_id:%s, movie_name:%s, tag:%s, actors:%s, director:%s"
				+ "photo:%s, description:%s, language:%s, duarion:%d, sale_account:%d, point:%f}", 
				movie_id, movie_name, tag, actors, director, photo, description, languages,
				duration, sale_account, point);
	}

	// get and set functions
	public String getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(String movie_id) {
		this.movie_id = movie_id;
	}

	public String getMovie_name() {
		return movie_name;
	}

	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLanguages() {
		return languages;
	}

	public void setLanguages(String languages) {
		this.languages = languages;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public long getSale_account() {
		return sale_account;
	}

	public void setSale_account(long sale_account) {
		this.sale_account = sale_account;
	}

	public float getPoint() {
		return point;
	}
	
	public void setPoint(float point) {
		this.point = point;
	}
}
