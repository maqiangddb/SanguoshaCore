package me.corp_so.sanguosha.core;

import java.util.ArrayList;
import java.util.Scanner;

public class Test extends GameLogic {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Test game = new Test();
		game.d("现在是全知的上帝视角");
		game.setPlayersNum(5);
		try {
			game.setGroupDiv(1, 1, 2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		game.start();
	}

	@Override
	protected Role userChooseRole(Player player, ArrayList<Role> list) {
		d(player.name() + "(" + player.groupName() + "), 请在以下角色中选择");
		int i = 0;
		for (Role role : list) {
			i++;
			d("" + i + ". " + role.name());
		}
		Scanner scanner = new Scanner(System.in);
		//i = scanner.nextInt();
		i = 1; // 自动选择第一个
		Role role = null;
		try {
			role = list.get(i-1);
		} catch (IndexOutOfBoundsException e) {
			d("不要乱选");
			role = list.get(0);
		}
		d("你选择了" + role.name());
		return role;
	}
}
