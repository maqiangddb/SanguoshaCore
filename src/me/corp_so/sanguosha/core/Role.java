package me.corp_so.sanguosha.core;

import java.util.ArrayList;

public class Role {

	// TODO 数量不够,待会补够 15 个? 17 个?
	private static final String[] NAME_ARRAY = {
		"刘备", "孙权", "曹操", "张角",
		"关羽", "张飞", "诸葛亮", "赵云", "黄忠",
		"周瑜", "黄盖", "鲁肃",
		"郭嘉", "张辽", "许褚",
		"吕布", "貂蝉", "华佗"
	};
	private static final int [] BLOOD_ARRAY = {
		4, 4, 4, 3,
		4, 4, 3, 4, 4,
		3, 4, 3,
		3, 4, 4, 
		4, 3, 3,
	};
	private static int chosen = -1;
	
	private static boolean inList(Role r, ArrayList<Role> list) {
		for (Role role : list)
			if (r.equals(role))
				return true;
		return false;
	}

	private static Role random() {
		int id = -1;
		do {
			id = (int) Math.floor(Math.random() * NAME_ARRAY.length);
		} while (id == chosen);
		return new Role(id);
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

	public static ArrayList<ArrayList<Role>> random(int groupNum, int everyGroup) {
		ArrayList<Role> rawList = random(groupNum * everyGroup);
		ArrayList<ArrayList<Role>> list = new ArrayList<ArrayList<Role>>();
		for (int i = 0; i < groupNum; i++) {
			ArrayList<Role> l = new ArrayList<Role>();
			for (int j = 0; j < everyGroup; j++) {
				Role role = rawList.remove(0);
				l.add(role);
			}
			list.add(l);
		}
		return list;
	}

	public static ArrayList<Role> randomForKing() {
		ArrayList<Role> list = new ArrayList<Role>();
		int kingsNum = 4;
		int moreNum = 2;
		for (int i = 0; i < kingsNum; i++) { // 1 - 4 是主公, 这里写死了
			list.add(new Role(i));
		}
		while (list.size() < (kingsNum + moreNum)) {
			Role r = Role.random();
			if (!inList(r, list)) {
				list.add(r);
			}
		}
		return list;
	}

	private int id = -1;

	public Role() {
	}

	public Role(int id) {
		this.id = id;
	}

	public boolean equals(Role role) {
		return this.id == role.id;
	}

	public static void setChosen(Role role) {
		chosen = role.id;
	}

	public String name() {
		return Role.NAME_ARRAY[id];
	}

	@Override
	public String toString() {
		return name();
	}

	public int blood() {
		return BLOOD_ARRAY[id];
	}

}
