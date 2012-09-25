package me.corp_so.sanguosha.core;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Card {
	public int category;
	public int type;
	public int id = -1;
	public int color;
	public int point;
	
	static final Integer KILL = 1;
	static final Integer HIDE = 2;
	static final Integer HEAL = 3;
    static final Integer DUEL = 4;
	static final Integer DROP = 5;
	static final Integer STEAL = 6;
	static final Integer MORE = 7;
	static final Integer OTHER_HAND = 8;
	static final Integer IMOK = 9;
	static final Integer ALLKILL = 10;
	static final Integer ALLHIDE = 11;
	static final Integer ALLHEAL = 12;
	static final Integer ALLMORE = 13;
	static final Integer REST = 14;
	static final Integer THUNDER = 15;
	
	private static final Map<Integer, String> typeMap;
	static {
        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(KILL, "杀");
        map.put(HIDE, "闪");
        map.put(HEAL, "桃");
        
        map.put(DUEL, "决斗");
        map.put(DROP, "过河拆桥");
        map.put(STEAL, "顺手牵羊");
        map.put(MORE, "无中生有");
        map.put(OTHER_HAND, "借刀杀人");
        map.put(IMOK, "无懈可击");
        map.put(ALLKILL, "南蛮入侵");
        map.put(ALLHIDE, "万箭齐发");
        map.put(ALLHEAL, "桃园结义");
        map.put(ALLMORE, "五谷丰登");
        
        map.put(REST, "乐不思蜀");
        map.put(THUNDER, "闪电");
        
        typeMap = Collections.unmodifiableMap(map);
    }
	
	private static final Integer SPADE = 1;
	private static final Integer HEART = 2;
	private static final Integer CLUB = 3;
	private static final Integer DIAMOND = 4;
	
	private static final Map<Integer, String> colorMap;
	static {
        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(SPADE, "黑桃");
        map.put(HEART, "红桃");
        map.put(CLUB, "梅花");
        map.put(DIAMOND, "方片");
        colorMap = Collections.unmodifiableMap(map);
    }
	
	public Card(int id, String type, String color, String point) {
		this.id = id;
		try {
			this.type = getTypeFromStr(type);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.color = getColorFromStr(color);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.point = getPointFromStr(point);
	}

	public Card(Card card) {
		super();
		this.id = card.id;
		this.type = card.type;
		this.color = card.color;
		this.point = card.point;
	}

	private static int getPointFromStr(String point) {
		point = point.trim();
		if (point.equals("J"))
			return 11;
		if (point.equals("Q"))
			return 12;
		if (point.equals("K"))
			return 13;
		if (point.equals("A"))
			return 1;
		return Integer.valueOf(point);
	}

	private static int getColorFromStr(String color) throws Exception {
		for (Entry<Integer, String> s : colorMap.entrySet()) {
			if (color.equals(s.getValue())) {
				return s.getKey();
			}
		}
		throw new Exception("no color:" + color);
	}

	public int type() {
		return type;
	}

	public static int getTypeFromStr(String type) throws Exception {
		type = type.trim();
		for (Entry<Integer, String> s : typeMap.entrySet()) {
			if (type.equals(s.getValue())) {
				return s.getKey();
			}
		}
		throw new Exception("no type: " + type);
	}

	@Override
	public String toString() {
		return "牌:" + typeMap.get(new Integer(type)) + "(" + colorMap.get(color) + point + ")";
	}

	public boolean needTarget() {
		int[] targetMap = {KILL, HEAL, DUEL, DROP, STEAL, OTHER_HAND, IMOK, REST, THUNDER};
		for (int i : targetMap) {
			if (i == type) return true;
		}
		return false;
	}

	public boolean isKill() {
		return type == KILL;
	}

	public PlayCard makePlayCard() {
		return new PlayCard(this, new Card(this));
	}
	
}