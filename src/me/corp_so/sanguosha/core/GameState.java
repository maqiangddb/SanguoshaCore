package me.corp_so.sanguosha.core;

import java.util.ArrayList;

public class GameState {
	private ArrayList<PlayerState> playerStates = new ArrayList<PlayerState>();

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (PlayerState s : playerStates) {
			sb.append(s.toString() + "\n");
		}
		return sb.toString();
	}
	
}
