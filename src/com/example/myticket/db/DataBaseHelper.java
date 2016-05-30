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
    private final static int DB_VERSION = 1;
    
    //����
    private final static String TABLE_CINEMA = "CINEMA";
    private final static String TABLE_LOCATION = "LOCATION";
    private final static String TABLE_MOVIE = "MOVIE";
    private final static String TABLE_PRODUCT_DESCRIPTION = "PRODUCTDESCRIPTION";
    private final static String TABLE_RESERVATION = "RESERVATION";
    private final static String TABLE_SCREENING_ROOM = "SCREENINGROOM";
    private final static String TABLE_USER = "USER";
    //����
    private final static String USER_ID = "UserId";
    private final static String USER_NAME = "Name";
    private final static String USER_PASSWORD = "Password";
    private final static String DIST_CODE = "DistCode";
    private final static String MOVIE_ID = "MovieId";
    private final static String MOVIE_NAME = "Name";
    private final static String MOVIE_TAG = "Tag";
    private final static String MOVIE_ACTORS = "Actors";
    private final static String MOVIE_DIRECTOR = "Director";
    private final static String MOVIE_PHOTO = "Photo";
    private final static String MOVIE_DESCRIPTION = "Description";
    private final static String MOVIE_LANGUAGES = "Languages";
    private final static String MOVIE_DURATION = "Duration";
    private final static String MOVIE_SALE_ACCOUNT = "SaleAccount";
    private final static String MOVIE_POINT = "Point";
    private final static String CINEMA_ID = "CinemaId";
    private final static String CINEMA_NAME = "Name";
    private final static String LOCATION_ADDRESS_NAME = "AddressName";
    private final static String PRODES_ID = "ProductDescriptionId";
    private final static String PRODES_TYPE = "Type";
    private final static String PRODES_START_TIME = "StartTime";
    private final static String PRODES_PRICE = "Price";
    private final static String PRODES_SEAT_AVAILIABLE = "SeatAvailiable";
    private final static String SCREENING_ROOM_ID = "ScreeningRoomId";
    private final static String SCREENING_ROOM_NAME = "Name";
    private final static String SCREENING_ROOM_ROW = "Row";
    private final static String SCREENING_ROOM_COL = "Col";
    private final static String RESERVATION_ID = "ReservationId";
    private final static String RESERVATION_RESERVE_TIME = "ReserveTime";
    private final static String RESERVATION_PHONE = "Phone";
    private final static String RESERVATION_SEAT = "Seat";
    
    
    private static DataBaseHelper _instance = null;
    private DataBaseHelper(Context context) {
    	super(context, DATABASE_NAME, null, DB_VERSION);
    }
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
		String creat_cinema_table = String.format("create table %s(%s text primary key, %s text, %s text)", 
				TABLE_CINEMA, CINEMA_ID, DIST_CODE, CINEMA_NAME);

		String creat_user_table = String.format("create table %s(%s text primary key, %s text, %s text, %s text)", 
				TABLE_USER, USER_ID, USER_NAME, USER_PASSWORD, DIST_CODE);

		String creat_location_table = String.format("create table %s(%s text primary key, %s text)", 
				TABLE_LOCATION, DIST_CODE, LOCATION_ADDRESS_NAME);

		String creat_movie_table = String.format("create table %s(%s text primary key, %s text, %s text, %s text, %s text, %s text, %s memo, %s text, %s Integer, %s Long, %s Single)", 
				TABLE_MOVIE, MOVIE_ID, MOVIE_NAME, MOVIE_TAG, MOVIE_PHOTO, MOVIE_DIRECTOR, MOVIE_ACTORS, MOVIE_DESCRIPTION, MOVIE_LANGUAGES, MOVIE_DURATION, MOVIE_SALE_ACCOUNT, MOVIE_POINT);

		String creat_reservation_table = String.format("create table %s(%s text primary key, %s text, %s text, %s text, %s text)", 
				TABLE_RESERVATION, RESERVATION_ID, RESERVATION_PHONE, RESERVATION_RESERVE_TIME, RESERVATION_SEAT, PRODES_ID);

		String creat_pd_table = String.format("create table %s(%s text primary key, %s text, %s text, %s text, %s text, %s text, %s text, %s Double)", 
				TABLE_PRODUCT_DESCRIPTION, PRODES_ID, MOVIE_ID, CINEMA_ID, SCREENING_ROOM_ID, PRODES_TYPE, PRODES_START_TIME, PRODES_SEAT_AVAILIABLE, PRODES_PRICE);

		String creat_sr_table = String.format("create table %s(%s text primary key, %s text, %s Integer, %s Integer)", 
				TABLE_SCREENING_ROOM, SCREENING_ROOM_ID, SCREENING_ROOM_NAME, SCREENING_ROOM_ROW, SCREENING_ROOM_COL);
		db.execSQL("drop table if exists " + TABLE_CINEMA);
		db.execSQL("drop table if exists " + TABLE_LOCATION);
		db.execSQL("drop table if exists " + TABLE_MOVIE);
		db.execSQL("drop table if exists " + TABLE_PRODUCT_DESCRIPTION);
		db.execSQL("drop table if exists " + TABLE_RESERVATION);
		db.execSQL("drop table if exists " + TABLE_SCREENING_ROOM);
		db.execSQL("drop table if exists " + TABLE_USER);
		Log.i("test", "after drop");
		db.execSQL(creat_cinema_table);
		Log.i("test", "1");
		db.execSQL(creat_location_table);
		Log.i("test", "2");
		db.execSQL(creat_movie_table);
		Log.i("test", "3");
		db.execSQL(creat_pd_table);
		Log.i("test", "4");
		db.execSQL(creat_reservation_table);
		Log.i("test", "5");
		db.execSQL(creat_sr_table);
		Log.i("test", "6");
		db.execSQL(creat_user_table);
		Log.i("test", "7");
    	Log.i("test", "create completed");
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		onCreate(db);
	}
	

	public long addUser(User user) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(USER_ID, user.getUser_id());
		values.put(USER_NAME, user.getName());
		values.put(USER_PASSWORD, user.getPassword());
		values.put(DIST_CODE, user.getDist_code());
		return db.insert(TABLE_USER, null, values);
	}
	public long addCinema(Cinema cinema) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(CINEMA_ID, cinema.getCinema_id());
		values.put(CINEMA_NAME, cinema.getCinema_name());
		values.put(DIST_CODE, cinema.getDist_code());
		return db.insert(TABLE_CINEMA, null, values);
	}
	public long addLocation(Location location) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(LOCATION_ADDRESS_NAME, location.getAddress_name());
		values.put(DIST_CODE, location.getDist_code());
		return db.insert(TABLE_LOCATION, null, values);
	}
	public long addMovie(Movie movie) {
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
	public long addProductDescription(ProductDescription productDescription) {
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
	public long addReservation(Reservation reservation) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(RESERVATION_ID, reservation.getReservation_id());
		values.put(PRODES_ID, reservation.getProduct_description_id());
		values.put(RESERVATION_PHONE, reservation.getPhone());
		values.put(RESERVATION_SEAT, reservation.getSeat());
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		values.put(RESERVATION_RESERVE_TIME, df.format(reservation.getReservationTime()));
		return db.insert(TABLE_RESERVATION, null, values);
	}
	public long addScreeningRoom(ScreeningRoom screeningRoom) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(SCREENING_ROOM_ID, screeningRoom.getScreening_room_id());
		values.put(SCREENING_ROOM_NAME, screeningRoom.getScreening_room_name());
		values.put(SCREENING_ROOM_ROW, screeningRoom.getRow());
		values.put(SCREENING_ROOM_COL, screeningRoom.getCol());
		return db.insert(TABLE_SCREENING_ROOM, null, values);
	}


	public int updateUser(User user) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(USER_ID, user.getUser_id());
		values.put(USER_NAME, user.getName());
		values.put(USER_PASSWORD, user.getPassword());
		values.put(DIST_CODE, user.getDist_code());
		return db.update(TABLE_USER, values, USER_ID + "=?", new String[]{user.getUser_id()});
	}
	public int updateCinema(Cinema cinema) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(CINEMA_ID, cinema.getCinema_id());
		values.put(CINEMA_NAME, cinema.getCinema_name());
		values.put(DIST_CODE, cinema.getDist_code());
		return db.update(TABLE_CINEMA, values, CINEMA_ID + "=?", new String[]{cinema.getCinema_id()});
	}
	public int updateLocation(Location location) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(LOCATION_ADDRESS_NAME, location.getAddress_name());
		values.put(DIST_CODE, location.getDist_code());
		return db.update(TABLE_LOCATION, values, DIST_CODE + "=?", new String[]{location.getDist_code()});
	}
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
	public int updateReservation(Reservation reservation) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(RESERVATION_ID, reservation.getReservation_id());
		values.put(PRODES_ID, reservation.getProduct_description_id());
		values.put(RESERVATION_PHONE, reservation.getPhone());
		values.put(RESERVATION_SEAT, reservation.getSeat());
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		values.put(RESERVATION_RESERVE_TIME, df.format(reservation.getReservationTime()));
		return db.update(TABLE_RESERVATION, values, RESERVATION_ID+"=?", new String[]{reservation.getReservation_id()});
	}
	public int updateScreeningRoom(ScreeningRoom screeningRoom) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(SCREENING_ROOM_ID, screeningRoom.getScreening_room_id());
		values.put(SCREENING_ROOM_NAME, screeningRoom.getScreening_room_name());
		values.put(SCREENING_ROOM_ROW, screeningRoom.getRow());
		values.put(SCREENING_ROOM_COL, screeningRoom.getCol());
		return db.update(TABLE_SCREENING_ROOM, values, SCREENING_ROOM_ID+"=?", new String[]{screeningRoom.getScreening_room_id()});
	}
	
	public int deleteUser(User user) {
		SQLiteDatabase db = this.getWritableDatabase();
		return db.delete(TABLE_USER, USER_ID+"=?", new String[]{user.getUser_id()});
	}
	public int deleteCinema(Cinema cinema) {
		SQLiteDatabase db = this.getWritableDatabase();
		return db.delete(TABLE_CINEMA, CINEMA_ID+"=?", new String[]{cinema.getCinema_id()});
	}
	public int deleteLocation(Location location) {
		SQLiteDatabase db = this.getWritableDatabase();
		return db.delete(TABLE_LOCATION, DIST_CODE+"=?", new String[]{location.getDist_code()});
	}
	public int deleteMovie(Movie movie) {
		SQLiteDatabase db = this.getWritableDatabase();
		return db.delete(TABLE_MOVIE, MOVIE_ID+"=?", new String[]{movie.getMovie_id()});
	}
	public int deleteProductDescription(ProductDescription productDescription) {
		SQLiteDatabase db = this.getWritableDatabase();
		return db.delete(TABLE_PRODUCT_DESCRIPTION, PRODES_ID+"=?", new String[]{productDescription.getProduct_description_id()});
	}
	public int deleteReservation(Reservation reservation) {
		SQLiteDatabase db = this.getWritableDatabase();
		return db.delete(TABLE_RESERVATION, RESERVATION_ID+"=?", new String[]{reservation.getReservation_id()});
	}
	public int deleteScreeningRoom(ScreeningRoom screeningRoom) {
		SQLiteDatabase db = this.getWritableDatabase();
		return db.delete(TABLE_SCREENING_ROOM, SCREENING_ROOM_ID+"=?", new String[]{screeningRoom.getScreening_room_id()});
	}
	
	public User queryUser(String id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(String.format("select * from %s where %s = %s", TABLE_USER, USER_ID, id), 
				null);
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
	public Cinema queryCinema(String id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(String.format("select * from %s where %s = %s", TABLE_CINEMA, CINEMA_ID, id), 
				null);
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
	public Location queryLocation(String dist_code) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(String.format("select * from %s where %s = %s", TABLE_LOCATION, DIST_CODE, dist_code), 
				null);
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
	public Movie queryMovie(String id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(String.format("select * from %s where %s = %s", TABLE_MOVIE, MOVIE_ID, id), 
				null);
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
	public ProductDescription queryProductDescription(String id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(String.format("select * from %s where %s = %s", TABLE_PRODUCT_DESCRIPTION, PRODES_ID, id), 
				null);
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
	public Reservation queryReservation(String id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(String.format("select * from %s where %s = %s", TABLE_RESERVATION, RESERVATION_ID, id), 
				null);
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
				cursor.close();
				return temp;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		return null;
	}
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
	public ScreeningRoom queryScreeningRoom(String id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(String.format("select * from %s where %s = %s", TABLE_SCREENING_ROOM, SCREENING_ROOM_ID, id), 
				null);
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
