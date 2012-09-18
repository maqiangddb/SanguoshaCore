package me.corp_so.sanguosha.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Test extends GameLogic {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Test game = new Test();
		game.setPlayersNum(8);
		try {
			game.setGroupDiv(2, 1, 4);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		game.start();
	}

	@Override
	protected Role userChooseRole(Player player, ArrayList<Role> list) {
		d(player.name() + "(" + player.groupName() + "), 请在以下角色中选择");
		Scanner scanner = new Scanner(System.in);
		return new Role(scanner.nextInt());
	}
}
