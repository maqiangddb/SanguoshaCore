package me.corp_so.sanguosha.core;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class Card {
	public int category;
	public int type;
	public int id;
	public int color;
	public int point;
	
	private static final Integer KILL = 1;
	private static final Integer HIDE = 2;
	private static final Integer HEAL = 3;
    private static final Integer DUEL = 4;
	private static final Integer DROP = 5;
	private static final Integer STEAL = 6;
	private static final Integer MORE = 7;
	private static final Integer OTHER_HAND = 8;
	private static final Integer IMOK = 9;
	private static final Integer ALLKILL = 10;
	private static final Integer ALLHIDE = 11;
	private static final Integer ALLHEAL = 12;
	private static final Integer ALLMORE = 13;
	private static final Integer REST = 14;
	private static final Integer THUNDER = 15;
	
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
	
	public Card(String type, String color, String point) {
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
		if (colorMap.containsValue(color)) {
			for (Entry<Integer, String> s : colorMap.entrySet()) {
				if (color.equals(s.getValue())) {
					return s.getKey();
				}
			}
		} else {
			throw new Exception("no color:" + color);
		}
		return -1;
	}

	public static int getTypeFromStr(String type) throws Exception {
		type = type.trim();
		if (typeMap.containsValue(type)) {
			for (Entry<Integer, String> s : typeMap.entrySet()) {
				if (type.equals(s.getValue())) {
					return s.getKey();
				}
			}
		} else {
			throw new Exception("no type: " + type);
		}
		return -1;
	}

	@Override
	public String toString() {
		return "牌:" + typeMap.get(new Integer(type)) + "(" + colorMap.get(color) + point + ")";
	}
	
}