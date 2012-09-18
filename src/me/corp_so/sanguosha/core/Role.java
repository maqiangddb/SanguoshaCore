package me.corp_so.sanguosha.core;

import java.util.ArrayList;

public class Role {

	private int id = -1;
	
	private static final String[] NAME_ARRAY = {
		"刘备", "孙权", "曹操", "张角",
		"关羽", "张飞", "诸葛亮", "赵云", "黄忠",
		"周瑜", "黄盖", "鲁肃",
		"郭嘉", "张辽", "许褚",
		"吕布", "貂蝉", "华佗"
	};

	public Role(int id) {
		this.id = id;
	}

	public Role() {
	}

	public String name() {
		return Role.NAME_ARRAY[id];
	}

	public static ArrayList<Role> random(int num) {
		ArrayList<Role> list = new ArrayList<Role>();
		while (list.size() < num) {
			Role r = Role.random();
			if (!inList(r, list)) {
				list.add(r);
			}
		}
		return list;
	}

	private static boolean inList(Role r, ArrayList<Role> list) {
		for (Role role : list)
			if (r.equals(role))
				return true;
		return false;
	}

	private static Role random() {
		// TODO Auto-generated method stub
		return new Role((int) Math.floor(Math.random() * NAME_ARRAY.length));
	}

}
