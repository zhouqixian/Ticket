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
		c1.setCinema_name("青宫电影城");
		c1.setDist_code("440104 510180");
		Cinema c2 = new Cinema();
		c2.setCinema_id("c2");
		c2.setCinema_name("正佳飞扬电影城");
		c2.setDist_code("440106 510510");
		dbHelper.addCinema(c1);
		dbHelper.addCinema(c2);
		
//		ArrayList<Cinema> cs = dbHelper.queryAllCinema();
//		for (Cinema cinema : cs) {
//			Log.i("test", cinema.toString());
//		}
		dbHelper.addLocation(new Location("440106 510510", "广东省广州市天河区"));
		dbHelper.addLocation(new Location("440103 510145", "广东省广州市荔湾区"));
		dbHelper.addLocation(new Location("440104 510180", "广东省广州市越秀区"));
		dbHelper.addLocation(new Location("440105 510220", "广东省广州市海珠区"));
		dbHelper.addLocation(new Location("440111 510440", "广东省广州市白云区"));
		dbHelper.addLocation(new Location("440112 510700", "广东省广州市黄埔区"));
		dbHelper.addLocation(new Location("440113 511400", "广东省广州市番禺区"));
		dbHelper.addLocation(new Location("440114 510800", "广东省广州市花都区"));
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
		m1.setActors("克里斯·埃文斯，小罗伯特·唐尼，塞巴斯蒂安·斯坦，斯嘉丽·约翰逊，伊丽莎白·奥尔森，查德维克·博斯曼，安东尼·麦凯，保罗·贝坦尼，保罗·路德，唐·钱德尔，杰瑞米·雷纳，汤姆·赫兰德");
		m1.setDescription("在奥创对这个世界造成了巨大的影响之后，复仇者联盟还是团结一致保护人类的。但是在一些政治角力的背后，政府中有人认为是有必要控制一下这些超级英雄的超自然能力和他们的行动了。于是，一项管控措施出台。这个措施就是要求复联按照政府的要求来行动。任务的开展、进程和结束，都要由政府主导。这个管控措施在复联中引起了极大的争议。意见最极端、最两极分化不可调和的，就是钢铁侠和美国队长之间的问题，于是，这两个同盟者之间的矛盾就此爆发了出来。而复联的“内战”也不可避免的爆发。");
		m1.setDirector("安东尼·罗素、乔·罗素");
		m1.setDuration(147);
		m1.setLanguages("English");
		m1.setMovie_name("美国队长3");
		m1.setPhoto("photo1");
		m1.setPoint(new Float(7.9));
		m1.setSale_account(79961);
		m1.setTag("动作、科幻、冒险");
		m2.setMovie_id("m2");
		m2.setActors("汤唯，吴秀波，秦沛，吴彦姝，惠英红，王志文，陆毅，祖峰，刘志宏");
		m2.setDescription("姣爷（汤唯饰）15岁就随父亲移民到澳门，从此在赌城安家，并成为赌场公关。过着朝不保夕、居无定所的落魄生活。来自北京的大牛Daniel （吴秀波饰）只身在美国打滚多年，已贵为加洲销售成绩最优秀的房地产经纪之一，更是身边不乏女伴的钻石王老五。姣爷一日起床后看到头顶一本《查令十字街84号》的书，书谐音同“输”，姣爷气愤不已，几次把书扔掉都意外再回来，无奈之下只好将书按照书上的地址寄了出去。无独有偶，Daniel大牛也同时因为这本书备受困扰，将书寄到了同一地址。查令十字街84号的书店老板也是闲来无事，又将两人的信件寄给了对方，收到书后更加疑惑的两人通过后续的书信来往从彼此不顺眼慢慢成为灵魂知己……");
		m2.setDirector("薛晓路");
		m2.setDuration(129);
		m2.setLanguages("汉语普通话");
		m2.setMovie_name("北京遇上西雅图2");
		m2.setPhoto("photo2");
		m2.setPoint(new Float(6.7));
		m2.setSale_account(52764);
		m2.setTag("爱情、喜剧");
		m3.setMovie_id("m3");
		m3.setActors("宝儿，谭耀文，吕聿来，孟瑶，高亚麟");
		m3.setDescription("泰国魅力美女邢潇（Poy饰）从小和双胞胎姐姐邢慧（孟瑶饰）分离，她和男友沙青（吕聿来饰）为了找回邢慧用尽了各种努力。在这过程中邢潇邂逅了陈永霖（谭耀文饰）。好奇和悲悯让陈永霖介入了沙青和邢潇的生活。当他陷入邢潇的情网之后，才发现，这只是一个处处设防，步步惊心的迷局。");
		m3.setDirector("吴宗强");
		m3.setDuration(83);
		m3.setLanguages("汉语普通话");
		m3.setMovie_name("妖医");
		m3.setPhoto("photo3");
		m3.setPoint(new Float(5.3));
		m3.setSale_account(23238);
		m3.setTag("惊悚，爱情，悬疑");
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
