package com.example.myticket.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.example.myticket.R;
import com.example.myticket.entities.Cinema;
import com.example.myticket.entities.Location;
import com.example.myticket.entities.Movie;
import com.example.myticket.entities.ProductDescription;
import com.example.myticket.entities.ScreeningRoom;
import com.example.myticket.entities.User;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

public class MyApplication extends Application{
	private User _u;
	private Map<String, Integer> _photo_map;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
		String phone = sharedPreferences.getString("phone", " ");
		_u = DataBaseHelper.getInstance(getApplicationContext()).queryUser(phone);
		_photo_map = new HashMap<String, Integer>();
		_photo_map.put("photo1", new Integer(R.drawable.photo1));
		_photo_map.put("photo2", new Integer(R.drawable.photo2));
		_photo_map.put("photo3", new Integer(R.drawable.photo3));
		initData();
	}
	public void setUser(User user) {
		this._u = user;
	}
	public User getUser() {
		return _u;
	}
	public Map<String, Integer> getPhotoMap() {
		return _photo_map;
	}
	private void initData() {
		DataBaseHelper dbHelper = DataBaseHelper.getInstance(getApplicationContext());
		
		Cinema c1 = new Cinema();
		c1.setCinema_id("c1");
		c1.setCinema_name("�๬��Ӱ��");
		c1.setDist_code("440104 510180");
		Cinema c2 = new Cinema();
		c2.setCinema_id("c2");
		c2.setCinema_name("���ѷ����Ӱ��");
		c2.setDist_code("440106 510510");
		dbHelper.addCinema(c1);
		dbHelper.addCinema(c2);
		
//		ArrayList<Cinema> cs = dbHelper.queryAllCinema();
//		for (Cinema cinema : cs) {
//			Log.i("test", cinema.toString());
//		}
		dbHelper.addLocation(new Location("440106 510510", "�㶫ʡ�����������"));
		dbHelper.addLocation(new Location("440103 510145", "�㶫ʡ������������"));
		dbHelper.addLocation(new Location("440104 510180", "�㶫ʡ������Խ����"));
		dbHelper.addLocation(new Location("440105 510220", "�㶫ʡ�����к�����"));
		dbHelper.addLocation(new Location("440111 510440", "�㶫ʡ�����а�����"));
		dbHelper.addLocation(new Location("440112 510700", "�㶫ʡ�����л�����"));
		dbHelper.addLocation(new Location("440113 511400", "�㶫ʡ�����з�خ��"));
		dbHelper.addLocation(new Location("440114 510800", "�㶫ʡ�����л�����"));
//		ArrayList<Location> ls = dbHelper.queryAllLocation();
//		for (Location location : ls) {
//			Log.i("test", location.toString());
//		}
		dbHelper.addScreeningRoom(new ScreeningRoom("s1", "A", 10, 8));
		dbHelper.addScreeningRoom(new ScreeningRoom("s2", "B", 10, 8));
		dbHelper.addScreeningRoom(new ScreeningRoom("s3", "C", 10, 8));
		dbHelper.addScreeningRoom(new ScreeningRoom("s4", "D", 10, 8));
		dbHelper.addScreeningRoom(new ScreeningRoom("s5", "E", 10, 8));
//		ArrayList<ScreeningRoom> ss = dbHelper.queryAllScreeningRoom();
//		for (ScreeningRoom screeningRoom : ss) {
//			Log.i("test", screeningRoom.toString());
//		}
		Movie m1, m2, m3;
		m1 = new Movie();
		m2 = new Movie();
		m3 = new Movie();
		m1.setMovie_id("m1");
		m1.setActors("����˹������˹��С�޲��ء����ᣬ����˹�ٰ���˹̹��˹������Լ��ѷ������ɯ�ס��¶�ɭ�����ά�ˡ���˹���������ᡤ�󿭣����ޡ���̹�ᣬ���ޡ�·�£��ơ�Ǯ�¶��������ס����ɣ���ķ��������");
		m1.setDescription("�ڰ´��������������˾޴��Ӱ��֮�󣬸��������˻����Ž�һ�±�������ġ�������һЩ���ν����ı���������������Ϊ���б�Ҫ����һ����Щ����Ӣ�۵ĳ���Ȼ���������ǵ��ж��ˡ����ǣ�һ��ܿش�ʩ��̨�������ʩ����Ҫ��������������Ҫ�����ж�������Ŀ�չ�����̺ͽ�������Ҫ����������������ܿش�ʩ�ڸ����������˼�������顣�����ˡ��������ֻ����ɵ��͵ģ����Ǹ������������ӳ�֮������⣬���ǣ�������ͬ����֮���ì�ܾʹ˱����˳������������ġ���ս��Ҳ���ɱ���ı�����");
		m1.setDirector("�����ᡤ���ء��ǡ�����");
		m1.setDuration(147);
		m1.setLanguages("English");
		m1.setMovie_name("�����ӳ�3");
		m1.setPhoto("photo1");
		m1.setPoint(new Float(7.9));
		m1.setSale_account(79961);
		m1.setTag("�������ƻá�ð��");
		m2.setMovie_id("m2");
		m2.setActors("��Ψ�����㲨�����棬����正���Ӣ�죬��־�ģ�½�㣬��壬��־��");
		m2.setDescription("�ү����Ψ�Σ�15����游�����񵽰��ţ��Ӵ��ڶĳǰ��ң�����Ϊ�ĳ����ء����ų�����Ϧ�����޶���������������Ա����Ĵ�ţDaniel �����㲨�Σ�ֻ��������������꣬�ѹ�Ϊ�������۳ɼ�������ķ��ز�����֮һ��������߲���Ů�����ʯ�����塣�үһ���𴲺󿴵�ͷ��һ��������ʮ�ֽ�84�š����飬��г��ͬ���䡱���ү���߲��ѣ����ΰ����ӵ��������ٻ���������֮��ֻ�ý��鰴�����ϵĵ�ַ���˳�ȥ���޶���ż��Daniel��ţҲͬʱ��Ϊ�Ȿ�鱸�����ţ�����ĵ���ͬһ��ַ������ʮ�ֽ�84�ŵ�����ϰ�Ҳ���������£��ֽ����˵��ż��ĸ��˶Է����յ��������ɻ������ͨ�����������������ӱ˴˲�˳��������Ϊ���֪������");
		m2.setDirector("Ѧ��·");
		m2.setDuration(129);
		m2.setLanguages("������ͨ��");
		m2.setMovie_name("������������ͼ2");
		m2.setPhoto("photo2");
		m2.setPoint(new Float(6.7));
		m2.setSale_account(52764);
		m2.setTag("���顢ϲ��");
		m3.setMovie_id("m3");
		m3.setActors("������̷ҫ�ģ��������������������");
		m3.setDescription("̩��������Ů���죨Poy�Σ���С��˫��̥����ϻۣ������Σ����룬��������ɳ�ࣨ������Σ�Ϊ���һ��ϻ��þ��˸���Ŭ����������������������˳����أ�̷ҫ���Σ�������ͱ����ó����ؽ�����ɳ����������������������������֮�󣬲ŷ��֣���ֻ��һ������������������ĵ��Ծ֡�");
		m3.setDirector("����ǿ");
		m3.setDuration(83);
		m3.setLanguages("������ͨ��");
		m3.setMovie_name("��ҽ");
		m3.setPhoto("photo3");
		m3.setPoint(new Float(5.3));
		m3.setSale_account(23238);
		m3.setTag("��㤣����飬����");
		dbHelper.addMovie(m1);
		dbHelper.addMovie(m2);
		dbHelper.addMovie(m3);
//		ArrayList<Movie> ms = dbHelper.queryAllMovie();
//		for (Movie movie : ms) {
//			Log.i("test", movie.toString());
//		}
		ProductDescription p1 = new ProductDescription("p1", "m1", "c1", "s1");
		ProductDescription p2 = new ProductDescription("p2", "m1", "c2", "s3");
		ProductDescription p3 = new ProductDescription("p3", "m2", "c1", "s2");
		ProductDescription p4 = new ProductDescription("p4", "m2", "c2", "s1");
		ProductDescription p5 = new ProductDescription("p5", "m3", "c1", "s5");
		ProductDescription p6 = new ProductDescription("p6", "m3", "c2", "s4");
		p1.setPrice((float)(100));
		p2.setPrice((float)(100));
		p3.setPrice((float)(60));
		p4.setPrice((float)(60));
		p5.setPrice((float)(50));
		p6.setPrice((float)(50));
		p1.setType("3D");
		p2.setType("3D");
		p3.setType("2D");
		p4.setType("2D");
		p5.setType("2D");
		p6.setType("2D");
		p1.setStartTime(new Date(2016, 5, 30, 9, 10, 0));
		p2.setStartTime(new Date(2016, 5, 30, 10, 0, 0));
		p3.setStartTime(new Date(2016, 5, 30, 14, 35, 0));
		p4.setStartTime(new Date(2016, 5, 30, 19, 20, 0));
		p5.setStartTime(new Date(2016, 5, 30, 17, 0, 0));
		p6.setStartTime(new Date(2016, 5, 30, 21, 40, 0));
		StringBuilder sb = new StringBuilder("1");
		for (int i = 0; i < 79; i++) {
			sb.append(",1");
		}
		String s = sb.toString();
		p1.setSeat_availible(s);
		p2.setSeat_availible(s);
		p3.setSeat_availible(s);
		p4.setSeat_availible(s);
		p5.setSeat_availible(s);
		p6.setSeat_availible(s);
		dbHelper.addProductDescription(p1);
		dbHelper.addProductDescription(p2);
		dbHelper.addProductDescription(p3);
		dbHelper.addProductDescription(p4);
		dbHelper.addProductDescription(p5);
		dbHelper.addProductDescription(p6);
//		ArrayList<ProductDescription> ps = dbHelper.queryAllProductDescription();
//		for (ProductDescription productDescription : ps) {
//			Log.i("test", productDescription.toString());
//		}
	}
}
