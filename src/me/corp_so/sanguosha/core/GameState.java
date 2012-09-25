package me.corp_so.sanguosha.core;

import java.util.ArrayList;

public class GameState {
	private ArrayList<PlayerState> playerStates = new ArrayList<PlayerState>();
	private CardStack cardStackState;

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (PlayerState s : playerStates) {
			sb.append(s.toString() + "\n");
		}
		return sb.toString();
	}

	public PlayerState selectPlayer(int c) {
		return playerStates.get(c);
	}

	/**
	 * 前置条件， playerStates 和 cardStackState 为空
	 * @param playerList
	 * @param cardStack
	 */
	public void init(ArrayList<Player> playerList, CardStack cardStack) {
		assert playerStates.isEmpty() : "有点事情";
		assert cardStackState == null;
		update(playerList, cardStack);
	}

	public void update(ArrayList<Player> playerList, CardStack cardStack) {
		// TODO card 的信息也需要隐藏？
		this.cardStackState = cardStack;
		playerStates.removeAll(playerStates);
		for (Player player : playerList) {
			PlayerState state = player.extractState();
			playerStates.add(state);
		}
	}
	
}
