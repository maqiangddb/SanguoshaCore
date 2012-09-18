package me.corp_so.sanguosha.core;

public class Group {
	public static final int KING  = 0;
	public static final int LOYAL = 1;
	public static final int CHEAT = 2;
	public static final int REBEL = 3;

	private int id = -1;

	public Group(int id) {
		this.id = id;
	}

	public static int random() {
		return (int) Math.floor(Math.random() * 4); // 四种阵营,暂时在这里写死
	}

	public boolean isKing() {
		// TODO Auto-generated method stub
		return id  == KING;
	}

	public String name() throws Exception {
		switch (id) {
		case KING:
			return "主公";
		case LOYAL:
			return "忠臣";
		case CHEAT:
			return "奸臣";
		case REBEL:
			return "反贼";
		default:
			throw new Exception("这是哪个阵营我不知道");
		}
	}

	@Override
	public String toString() {
		try {
			return name();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
