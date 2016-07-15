package com.example.myticket.db;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Stack;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.myticket.entities.Cinema;
import com.example.myticket.entities.Location;
import com.example.myticket.entities.Movie;
import com.example.myticket.entities.ProductDescription;
import com.example.myticket.entities.Reservation;
import com.example.myticket.entities.ScreeningRoom;
import com.example.myticket.entities.User;

public class DataBaseHelper extends SQLiteOpenHelper{
	private final static String DATABASE_NAME = "ticket.db3";  
    private final static int DB_VERSION = 5;
    
    //表名
    public final static String TABLE_CINEMA = "CINEMA";
    public final static String TABLE_LOCATION = "LOCATION";
    public final static String TABLE_MOVIE = "MOVIE";
    public final static String TABLE_PRODUCT_DESCRIPTION = "PRODUCTDESCRIPTION";
    public final static String TABLE_RESERVATION = "RESERVATION";
    public final static String TABLE_SCREENING_ROOM = "SCREENINGROOM";
    public final static String TABLE_USER = "USER";
    //列名
    public final static String USER_ID = "UserId";
    public final static String USER_NAME = "Name";
    public final static String USER_PASSWORD = "Password";
    public final static String DIST_CODE = "DistCode";

    public final static String MOVIE_ID = "MovieId";
    public final static String MOVIE_NAME = "Name";
    public final static String MOVIE_TAG = "Tag";
    public final static String MOVIE_ACTORS = "Actors";
    public final static String MOVIE_DIRECTOR = "Director";
    public final static String MOVIE_PHOTO = "Photo";
    public final static String MOVIE_DESCRIPTION = "Description";
    public final static String MOVIE_LANGUAGES = "Languages";
    public final static String MOVIE_DURATION = "Duration";
    public final static String MOVIE_SALE_ACCOUNT = "SaleAccount";
    public final static String MOVIE_POINT = "Point";

    public final static String CINEMA_ID = "CinemaId";
    public final static String CINEMA_NAME = "Name";

    public final static String LOCATION_ADDRESS_NAME = "AddressName";

    public final static String PRODES_ID = "ProductDescriptionId";
    public final static String PRODES_TYPE = "Type";
    public final static String PRODES_START_TIME = "StartTime";
    public final static String PRODES_PRICE = "Price";
    public final static String PRODES_SEAT_AVAILIABLE = "SeatAvailiable";

    public final static String SCREENING_ROOM_ID = "ScreeningRoomId";
    public final static String SCREENING_ROOM_NAME = "Name";
    public final static String SCREENING_ROOM_ROW = "Row";
    public final static String SCREENING_ROOM_COL = "Col";
    
    public final static String RESERVATION_ID = "ReservationId";
    public final static String RESERVATION_RESERVE_TIME = "ReserveTime";
    public final static String RESERVATION_PHONE = "Phone";
    public final static String RESERVATION_SEAT = "Seat";
    public final static String RESERVATION_TOTAL_PRICE = "TotalPrice";
    public final static String RESERVATION_TICKET_QUANTITY = "TicketQuantity";
    
    private static DataBaseHelper _instance = null;

    // Constructor
    private DataBaseHelper(Context context) {
    	super(context, DATABASE_NAME, null, DB_VERSION);
    }

    // Singleton
    public static synchronized DataBaseHelper getInstance(Context context) {
		if (_instance == null) {
			_instance = new DataBaseHelper(context);
		}
		return _instance;
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		Log.i("test", "create db");

		// Define table creation strings
		String create_cinema_table = String.format("create table %s(%s text primary key, %s text, %s text)", 
				TABLE_CINEMA, CINEMA_ID, DIST_CODE, CINEMA_NAME);

		String create_user_table = String.format("create table %s(%s text primary key, %s text, %s text, %s text)", 
				TABLE_USER, USER_ID, USER_NAME, USER_PASSWORD, DIST_CODE);

		String create_location_table = String.format("create table %s(%s text primary key, %s text)", 
				TABLE_LOCATION, DIST_CODE, LOCATION_ADDRESS_NAME);

		String create_movie_table = String.format("create table %s(%s text primary key, %s text, %s text, %s text, %s text, %s text, %s memo, %s text, %s Integer, %s Long, %s Single)", 
				TABLE_MOVIE, MOVIE_ID, MOVIE_NAME, MOVIE_TAG, MOVIE_PHOTO, MOVIE_DIRECTOR, MOVIE_ACTORS, MOVIE_DESCRIPTION, MOVIE_LANGUAGES, MOVIE_DURATION, MOVIE_SALE_ACCOUNT, MOVIE_POINT);

		String create_reservation_table = String.format("create table %s(%s text primary key, %s text, %s text, %s text, %s text, %s Single, %s Integer)", 
				TABLE_RESERVATION, RESERVATION_ID, RESERVATION_PHONE, RESERVATION_RESERVE_TIME, RESERVATION_SEAT, PRODES_ID, RESERVATION_TOTAL_PRICE, RESERVATION_TICKET_QUANTITY);

		String create_pd_table = String.format("create table %s(%s text primary key, %s text, %s text, %s text, %s text, %s text, %s text, %s Double)", 
				TABLE_PRODUCT_DESCRIPTION, PRODES_ID, MOVIE_ID, CINEMA_ID, SCREENING_ROOM_ID, PRODES_TYPE, PRODES_START_TIME, PRODES_SEAT_AVAILIABLE, PRODES_PRICE);

		String create_sr_table = String.format("create table %s(%s text primary key, %s text, %s Integer, %s Integer)", 
				TABLE_SCREENING_ROOM, SCREENING_ROOM_ID, SCREENING_ROOM_NAME, SCREENING_ROOM_ROW, SCREENING_ROOM_COL);
		
		// Drop tables
		db.execSQL("drop table if exists " + TABLE_CINEMA);
		db.execSQL("drop table if exists " + TABLE_LOCATION);
		db.execSQL("drop table if exists " + TABLE_MOVIE);
		db.execSQL("drop table if exists " + TABLE_PRODUCT_DESCRIPTION);
		db.execSQL("drop table if exists " + TABLE_RESERVATION);
		db.execSQL("drop table if exists " + TABLE_SCREENING_ROOM);
		db.execSQL("drop table if exists " + TABLE_USER);
		Log.i("test", "after drop");

		// Create tables
		db.execSQL(create_cinema_table);
		Log.i("test", "1");
		db.execSQL(create_location_table);
		Log.i("test", "2");
		db.execSQL(create_movie_table);
		Log.i("test", "3");
		db.execSQL(create_pd_table);
		Log.i("test", "4");
		db.execSQL(create_reservation_table);
		Log.i("test", "5");
		db.execSQL(create_sr_table);
		Log.i("test", "6");
		db.execSQL(create_user_table);
		Log.i("test", "7");
    	Log.i("test", "create completed");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		onCreate(db);
	}
	

	// Adding
	// Add a user to the DB
	public long addUser(User user) {
		if (this.queryUser(user.getUser_id()) != null) return 0;
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(USER_ID, user.getUser_id());
		values.put(USER_NAME, user.getName());
		values.put(USER_PASSWORD, user.getPassword());
		values.put(DIST_CODE, user.getDist_code());
		return db.insert(TABLE_USER, null, values);
	}

	// Add a cinema to the DB
	public long addCinema(Cinema cinema) {
		if (this.queryCinema(cinema.getCinema_id()) != null) return 0;
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(CINEMA_ID, cinema.getCinema_id());
		values.put(CINEMA_NAME, cinema.getCinema_name());
		values.put(DIST_CODE, cinema.getDist_code());
		return db.insert(TABLE_CINEMA, null, values);
	}

	// Add a location to the DB
	public long addLocation(Location location) {
		if (this.queryLocation(location.getDist_code()) != null) return 0;
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(LOCATION_ADDRESS_NAME, location.getAddress_name());
		values.put(DIST_CODE, location.getDist_code());
		return db.insert(TABLE_LOCATION, null, values);
	}

	// Add a movie to the DB
	public long addMovie(Movie movie) {
		if (this.queryMovie(movie.getMovie_id()) != null) return 0;
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(MOVIE_ID, movie.getMovie_id());
		values.put(MOVIE_NAME, movie.getMovie_name());
		values.put(MOVIE_DESCRIPTION, movie.getDescription());
		values.put(MOVIE_DIRECTOR, movie.getDirector());
		values.put(MOVIE_TAG, movie.getTag());
		values.put(MOVIE_ACTORS, movie.getActors());
		values.put(MOVIE_LANGUAGES, movie.getLanguages());
		values.put(MOVIE_PHOTO, movie.getPhoto());
		values.put(MOVIE_DURATION, movie.getDuration());
		values.put(MOVIE_POINT, movie.getPoint());
		values.put(MOVIE_SALE_ACCOUNT, movie.getSale_account());
		return db.insert(TABLE_MOVIE, null, values);
	}

	// Add a product description to the DB
	public long addProductDescription(ProductDescription productDescription) {
		if (this.queryProductDescription(productDescription.getProduct_description_id()) != null) return 0;
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(PRODES_ID, productDescription.getProduct_description_id());
		values.put(MOVIE_ID, productDescription.getMovie_id());
		values.put(CINEMA_ID, productDescription.getCinema_id());
		values.put(SCREENING_ROOM_ID, productDescription.getScreening_room_id());
		values.put(PRODES_TYPE, productDescription.getType());
		values.put(PRODES_PRICE, productDescription.getPrice());
		values.put(PRODES_SEAT_AVAILIABLE, productDescription.getSeat_availible());
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		values.put(PRODES_START_TIME, df.format(productDescription.getStartTime()));
		return db.insert(TABLE_PRODUCT_DESCRIPTION, null, values);
	}

	// Add a reservation to the DB
	public long addReservation(Reservation reservation) {
		if (this.queryReservation(reservation.getReservation_id()) != null) return 0;
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(RESERVATION_ID, reservation.getReservation_id());
		values.put(PRODES_ID, reservation.getProduct_description_id());
		values.put(RESERVATION_PHONE, reservation.getPhone());
		values.put(RESERVATION_SEAT, reservation.getSeat());
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		values.put(RESERVATION_RESERVE_TIME, df.format(reservation.getReservationTime()));
		values.put(RESERVATION_TOTAL_PRICE, reservation.getTotal_price());
		values.put(RESERVATION_TICKET_QUANTITY, reservation.getTicket_quantity());
		return db.insert(TABLE_RESERVATION, null, values);
	}

	// Add a screening room to the DB
	public long addScreeningRoom(ScreeningRoom screeningRoom) {
		if (this.queryScreeningRoom(screeningRoom.getScreening_room_id()) != null) return 0;
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(SCREENING_ROOM_ID, screeningRoom.getScreening_room_id());
		values.put(SCREENING_ROOM_NAME, screeningRoom.getScreening_room_name());
		values.put(SCREENING_ROOM_ROW, screeningRoom.getRow());
		values.put(SCREENING_ROOM_COL, screeningRoom.getCol());
		return db.insert(TABLE_SCREENING_ROOM, null, values);
	}


	// Updating
	// Update user info
	public int updateUser(User user) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(USER_ID, user.getUser_id());
		values.put(USER_NAME, user.getName());
		values.put(USER_PASSWORD, user.getPassword());
		values.put(DIST_CODE, user.getDist_code());
		return db.update(TABLE_USER, values, USER_ID + "=?", new String[]{user.getUser_id()});
	}

	// Update cinema info
	public int updateCinema(Cinema cinema) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(CINEMA_ID, cinema.getCinema_id());
		values.put(CINEMA_NAME, cinema.getCinema_name());
		values.put(DIST_CODE, cinema.getDist_code());
		return db.update(TABLE_CINEMA, values, CINEMA_ID + "=?", new String[]{cinema.getCinema_id()});
	}

	// Update location info
	public int updateLocation(Location location) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(LOCATION_ADDRESS_NAME, location.getAddress_name());
		values.put(DIST_CODE, location.getDist_code());
		return db.update(TABLE_LOCATION, values, DIST_CODE + "=?", new String[]{location.getDist_code()});
	}

	// Update movie info
	public int updateMovie(Movie movie) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(MOVIE_ID, movie.getMovie_id());
		values.put(MOVIE_NAME, movie.getMovie_name());
		values.put(MOVIE_DESCRIPTION, movie.getDescription());
		values.put(MOVIE_DIRECTOR, movie.getDirector());
		values.put(MOVIE_TAG, movie.getTag());
		values.put(MOVIE_ACTORS, movie.getActors());
		values.put(MOVIE_LANGUAGES, movie.getLanguages());
		values.put(MOVIE_PHOTO, movie.getPhoto());
		values.put(MOVIE_DURATION, movie.getDuration());
		values.put(MOVIE_POINT, movie.getPoint());
		values.put(MOVIE_SALE_ACCOUNT, movie.getSale_account());
		return db.update(TABLE_MOVIE, values, MOVIE_ID+"=?", new String[]{movie.getMovie_id()});
	}

	// Update product description info
	public int updateProductDescription(ProductDescription productDescription) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(PRODES_ID, productDescription.getProduct_description_id());
		values.put(MOVIE_ID, productDescription.getMovie_id());
		values.put(CINEMA_ID, productDescription.getCinema_id());
		values.put(SCREENING_ROOM_ID, productDescription.getScreening_room_id());
		values.put(PRODES_TYPE, productDescription.getType());
		values.put(PRODES_PRICE, productDescription.getPrice());
		values.put(PRODES_SEAT_AVAILIABLE, productDescription.getSeat_availible());
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		values.put(PRODES_START_TIME, df.format(productDescription.getStartTime()));
		return db.update(TABLE_PRODUCT_DESCRIPTION, values, PRODES_ID+"=?", new String[]{productDescription.getProduct_description_id()});
	}

	// Update reservation info
	public int updateReservation(Reservation reservation) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(RESERVATION_ID, reservation.getReservation_id());
		values.put(PRODES_ID, reservation.getProduct_description_id());
		values.put(RESERVATION_PHONE, reservation.getPhone());
		values.put(RESERVATION_SEAT, reservation.getSeat());
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		values.put(RESERVATION_RESERVE_TIME, df.format(reservation.getReservationTime()));
		values.put(RESERVATION_TOTAL_PRICE, reservation.getTotal_price());
		values.put(RESERVATION_TICKET_QUANTITY, reservation.getTicket_quantity());
		return db.update(TABLE_RESERVATION, values, RESERVATION_ID+"=?", new String[]{reservation.getReservation_id()});
	}

	// Update screening room info
	public int updateScreeningRoom(ScreeningRoom screeningRoom) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(SCREENING_ROOM_ID, screeningRoom.getScreening_room_id());
		values.put(SCREENING_ROOM_NAME, screeningRoom.getScreening_room_name());
		values.put(SCREENING_ROOM_ROW, screeningRoom.getRow());
		values.put(SCREENING_ROOM_COL, screeningRoom.getCol());
		return db.update(TABLE_SCREENING_ROOM, values, SCREENING_ROOM_ID+"=?", new String[]{screeningRoom.getScreening_room_id()});
	}
	

	// Deleting
	// Delete the user
	public int deleteUser(String id) {
		SQLiteDatabase db = this.getWritableDatabase();
		return db.delete(TABLE_USER, USER_ID+"=?", new String[]{id});
	}

	// Delete the cinema
	public int deleteCinema(String id) {
		SQLiteDatabase db = this.getWritableDatabase();
		return db.delete(TABLE_CINEMA, CINEMA_ID+"=?", new String[]{id});
	}

	// Delete the location
	public int deleteLocation(String dist_code) {
		SQLiteDatabase db = this.getWritableDatabase();
		return db.delete(TABLE_LOCATION, DIST_CODE+"=?", new String[]{dist_code});
	}

	// Delete the movie
	public int deleteMovie(String id) {
		SQLiteDatabase db = this.getWritableDatabase();
		return db.delete(TABLE_MOVIE, MOVIE_ID+"=?", new String[]{id});
	}

	// Delete the product description
	public int deleteProductDescription(String id) {
		SQLiteDatabase db = this.getWritableDatabase();
		return db.delete(TABLE_PRODUCT_DESCRIPTION, PRODES_ID+"=?", new String[]{id});
	}

	// Delete the reservation
	public int deleteReservation(String id) {
		SQLiteDatabase db = this.getWritableDatabase();
		return db.delete(TABLE_RESERVATION, RESERVATION_ID+"=?", new String[]{id});
	}

	// Delete the screening room
	public int deleteScreeningRoom(String id) {
		SQLiteDatabase db = this.getWritableDatabase();
		return db.delete(TABLE_SCREENING_ROOM, SCREENING_ROOM_ID+"=?", new String[]{id});
	}
	

	// Querying
	// Query user by id
	public User queryUser(String id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(String.format("select * from %s where %s = ?", TABLE_USER, USER_ID), 
				new String[]{id});
		try {
			if (cursor != null && cursor.moveToFirst()) {
				User temp = new User();
				temp.setUser_id(cursor.getString(0));
				temp.setName(cursor.getString(1));
				temp.setPassword(cursor.getString(2));
				temp.setDist_code(cursor.getString(3));
				cursor.close();
				return temp;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		return null;
	}

	// Query all users
	public ArrayList<User> queryAllUser() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(String.format("select * from %s", TABLE_USER), 
				null);
		try {
			if (cursor != null && cursor.moveToFirst()) {
				ArrayList<User> re = new ArrayList<User>();
				do {
					User temp = new User();
					temp.setUser_id(cursor.getString(0));
					temp.setName(cursor.getString(1));
					temp.setPassword(cursor.getString(2));
					temp.setDist_code(cursor.getString(3));
					re.add(temp);
				} while (cursor.moveToNext());
				cursor.close();
				return re;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		return null;
	}

	// Query cinema by id
	public Cinema queryCinema(String id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from " + TABLE_CINEMA + " where " + CINEMA_ID + " like ?", 
				new String[]{id});
		try {
			if (cursor != null && cursor.moveToFirst()) {
				Cinema temp = new Cinema();
				temp.setCinema_id(cursor.getString(0));
				temp.setDist_code(cursor.getString(1));
				temp.setCinema_name(cursor.getString(2));
				cursor.close();
				return temp;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		return null;
	}

	// Query all cinemas
	public ArrayList<Cinema> queryAllCinema() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(String.format("select * from %s", TABLE_CINEMA), 
				null);
		try {
			if (cursor != null && cursor.moveToFirst()) {
				ArrayList<Cinema> result = new ArrayList<Cinema>();
				do {
					Cinema temp = new Cinema();
					temp.setCinema_id(cursor.getString(0));
					temp.setDist_code(cursor.getString(1));
					temp.setCinema_name(cursor.getString(2));
					result.add(temp);
				} while (cursor.moveToNext());
				
				cursor.close();
				return result;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		return null;
	}

	// Query location by dist_code
	public Location queryLocation(String dist_code) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(String.format("select * from %s where %s = ?", TABLE_LOCATION, DIST_CODE), 
				new String[]{dist_code});
		try {
			if (cursor != null && cursor.moveToFirst()) {
				Location temp = new Location();
				temp.setDist_code(cursor.getString(0));
				temp.setAddress_name(cursor.getString(1));
				cursor.close();
				return temp;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		return null;
	}

	// Query all locations
	public ArrayList<Location> queryAllLocation() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(String.format("select * from %s", TABLE_LOCATION), 
				null);
		try {
			if (cursor != null && cursor.moveToFirst()) {
				ArrayList<Location> re = new ArrayList<Location>();
				do {
					Location temp = new Location();
					temp.setDist_code(cursor.getString(0));
					temp.setAddress_name(cursor.getString(1));
					re.add(temp);
				} while (cursor.moveToNext());
				
				cursor.close();
				return re;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		return null;
	}

	// Query movie by id
	public Movie queryMovie(String id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(String.format("select * from %s where %s = ?", TABLE_MOVIE, MOVIE_ID), 
				new String[]{id});
		try {
			if (cursor != null && cursor.moveToFirst()) {
				Movie temp = new Movie();
				temp.setMovie_id(cursor.getString(0));
				temp.setMovie_name(cursor.getString(1));
				temp.setTag(cursor.getString(2));
				temp.setPhoto(cursor.getString(3));
				temp.setDirector(cursor.getString(4));
				temp.setActors(cursor.getString(5));
				temp.setDescription(cursor.getString(6));
				temp.setLanguages(cursor.getString(7));
				temp.setDuration(cursor.getInt(8));
				temp.setSale_account(cursor.getLong(9));
				temp.setPoint(cursor.getFloat(10));
				cursor.close();
				return temp;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		return null;
	}

	// Query all movies
	public ArrayList<Movie> queryAllMovie() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(String.format("select * from %s", TABLE_MOVIE), 
				null);
		try {
			if (cursor != null && cursor.moveToFirst()) {
				ArrayList<Movie> re = new ArrayList<Movie>();
				do {
					Movie temp = new Movie();
					temp.setMovie_id(cursor.getString(0));
					temp.setMovie_name(cursor.getString(1));
					temp.setTag(cursor.getString(2));
					temp.setPhoto(cursor.getString(3));
					temp.setDirector(cursor.getString(4));
					temp.setActors(cursor.getString(5));
					temp.setDescription(cursor.getString(6));
					temp.setLanguages(cursor.getString(7));
					temp.setDuration(cursor.getInt(8));
					temp.setSale_account(cursor.getLong(9));
					temp.setPoint(cursor.getFloat(10));
					re.add(temp);
				} while (cursor.moveToNext());
				
				cursor.close();
				return re;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		return null;
	}

	// Query product descriptions by id
	public ProductDescription queryProductDescription(String id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(String.format("select * from %s where %s = ?", TABLE_PRODUCT_DESCRIPTION, PRODES_ID), 
				new String[]{id});
		try {
			if (cursor != null && cursor.moveToFirst()) {
				ProductDescription temp = new ProductDescription();
				temp.setProduct_description_id(cursor.getString(0));
				temp.setMovie_id(cursor.getString(1));
				temp.setCinema_id(cursor.getString(2));
				temp.setScreening_room_id(cursor.getString(3));
				temp.setType(cursor.getString(4));
				String st = cursor.getString(5);
				SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				temp.setStartTime(df.parse(st));
				temp.setSeat_availible(cursor.getString(6));
				temp.setPrice(cursor.getFloat(7));
				cursor.close();
				return temp;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		return null;
	}

	// Query product descriptions by movie
	public ArrayList<ProductDescription> queryProductDescriptionsByMovie(String movieId) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(String.format("select * from %s where %s = ?", TABLE_PRODUCT_DESCRIPTION, MOVIE_ID), 
				new String[]{movieId});
		try {
			if (cursor != null && cursor.moveToFirst()) {
				ArrayList<ProductDescription> re = new ArrayList<ProductDescription>();
				do {
					ProductDescription temp = new ProductDescription();
					temp.setProduct_description_id(cursor.getString(0));
					temp.setMovie_id(cursor.getString(1));
					temp.setCinema_id(cursor.getString(2));
					temp.setScreening_room_id(cursor.getString(3));
					temp.setType(cursor.getString(4));
					String st = cursor.getString(5);
					SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					temp.setStartTime(df.parse(st));
					temp.setSeat_availible(cursor.getString(6));
					temp.setPrice(cursor.getFloat(7));
					re.add(temp);
				} while (cursor.moveToNext());
				cursor.close();
				return re;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		return null;
	}

	// Query all product descriptions
	public ArrayList<ProductDescription> queryAllProductDescription() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(String.format("select * from %s", TABLE_PRODUCT_DESCRIPTION), 
				null);
		try {
			if (cursor != null && cursor.moveToFirst()) {
				ArrayList<ProductDescription> re = new ArrayList<ProductDescription>();
				do {
					ProductDescription temp = new ProductDescription();
					temp.setProduct_description_id(cursor.getString(0));
					temp.setMovie_id(cursor.getString(1));
					temp.setCinema_id(cursor.getString(2));
					temp.setScreening_room_id(cursor.getString(3));
					temp.setType(cursor.getString(4));
					String st = cursor.getString(5);
					SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					temp.setStartTime(df.parse(st));
					temp.setSeat_availible(cursor.getString(6));
					temp.setPrice(cursor.getFloat(7));
					re.add(temp);
				} while (cursor.moveToNext());
				
				cursor.close();
				return re;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		return null;
	}

	// Query reservation by id
	public Reservation queryReservation(String id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(String.format("select * from %s where %s = ?", TABLE_RESERVATION, RESERVATION_ID), 
				new String[]{id});
		try {
			if (cursor != null && cursor.moveToFirst()) {
				Reservation temp = new Reservation();
				temp.setReservation_id(cursor.getString(0));
				temp.setPhone(cursor.getString(1));
				String t = cursor.getString(2);
				SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				temp.setReservationTime(df.parse(t));
				temp.setSeat(cursor.getString(3));
				temp.setProduct_description_id(cursor.getString(4));
				temp.setTotal_price(cursor.getFloat(5));
				temp.setTicket_quantity(cursor.getInt(6));
				cursor.close();
				return temp;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		return null;
	}

	// Query reservations by userId
	public ArrayList<Reservation> queryReservationsByUserId(String id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(String.format("select * from %s where %s = ?", TABLE_RESERVATION, RESERVATION_PHONE), 
				new String[]{id});
		try {
			if (cursor != null && cursor.moveToFirst()) {
				ArrayList<Reservation> re = new ArrayList<Reservation>();
				do {
					Reservation temp = new Reservation();
					temp.setReservation_id(cursor.getString(0));
					temp.setPhone(cursor.getString(1));
					String t = cursor.getString(2);
					SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					temp.setReservationTime(df.parse(t));
					temp.setSeat(cursor.getString(3));
					temp.setProduct_description_id(cursor.getString(4));
					temp.setTotal_price(cursor.getFloat(5));
					temp.setTicket_quantity(cursor.getInt(6));
					re.add(temp);
				} while (cursor.moveToNext());
				
				cursor.close();
				return re;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		return null;
	}

	// Query all reservations
	public ArrayList<Reservation> queryAllReservation() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(String.format("select * from %s", TABLE_RESERVATION), 
				null);
		try {
			if (cursor != null && cursor.moveToFirst()) {
				ArrayList<Reservation> re = new ArrayList<Reservation>();
				do {
					Reservation temp = new Reservation();
					temp.setReservation_id(cursor.getString(0));
					temp.setPhone(cursor.getString(1));
					String t = cursor.getString(2);
					SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					temp.setReservationTime(df.parse(t));
					temp.setSeat(cursor.getString(3));
					temp.setProduct_description_id(cursor.getString(4));
					temp.setTotal_price(cursor.getFloat(5));
					temp.setTicket_quantity(cursor.getInt(6));
					re.add(temp);
				} while (cursor.moveToNext());
				
				cursor.close();
				return re;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		return null;
	}

	// Query screening room by id
	public ScreeningRoom queryScreeningRoom(String id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(String.format("select * from %s where %s = ?", TABLE_SCREENING_ROOM, SCREENING_ROOM_ID), 
				new String[]{id});
		try {
			if (cursor != null && cursor.moveToFirst()) {
				ScreeningRoom temp = new ScreeningRoom();
				temp.setScreening_room_id(cursor.getString(0));
				temp.setScreening_room_name(cursor.getString(1));
				temp.setRow(cursor.getInt(2));
				temp.setCol(cursor.getInt(3));
				cursor.close();
				return temp;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		return null;
	}

	// Query all screening rooms
	public ArrayList<ScreeningRoom> queryAllScreeningRoom() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(String.format("select * from %s", TABLE_SCREENING_ROOM), 
				null);
		try {
			if (cursor != null && cursor.moveToFirst()) {
				ArrayList<ScreeningRoom> re = new ArrayList<ScreeningRoom>();
				do {
					ScreeningRoom temp = new ScreeningRoom();
					temp.setScreening_room_id(cursor.getString(0));
					temp.setScreening_room_name(cursor.getString(1));
					temp.setRow(cursor.getInt(2));
					temp.setCol(cursor.getInt(3));
					re.add(temp);
				} while (cursor.moveToNext());
				
				cursor.close();
				return re;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		return null;
	}
}
