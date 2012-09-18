package me.corp_so.sanguosha.core;

public class Group {
	public static final int KING  = 0;
	public static final int LOYAL = 1;
	public static final int CHEAT = 2;
	public static final int REBEL = 3;

	public static int random() {
		return (int) Math.floor(Math.random() * 4); // 四种阵营,暂时在这里写死
	}

	public static String getStr(int group) throws Exception {
		switch (group) {
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

}
