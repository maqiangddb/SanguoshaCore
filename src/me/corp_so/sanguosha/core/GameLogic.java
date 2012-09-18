package me.corp_so.sanguosha.core;

import java.util.ArrayList;


// 只能运行一份
public class GameLogic {
	private CardStack cardStack;
	private ArrayList<Player> playerList;
	
	private boolean willJudge = true; // 是否有判定阶段
	private boolean willDeal = true;  // 是否有摸牌阶段
	private boolean willLead = true;  // 是否有出牌阶段

	private void dealCards(Player player) {
		// TODO Auto-generated method stub
		if (!willDeal) {
			return;
		}
		Card card1 = cardStack.nextCard();
		Card card2 = cardStack.nextCard();
		player.addCard(card1);
		player.addCard(card2);
	}

	private boolean end() {
		// TODO Auto-generated method stub
		return false;
	}

	private void endRound(Player player) {
		// TODO Auto-generated method stub
		
	}

	private void judge(Player player) {
		// TODO Auto-generated method stub
		
	}

	private void leadCards(Player player) {
		// TODO Auto-generated method stub
		if (!willLead) {
			return;
		}
		while (canAct(player)) {
			ReactStrategy strategy = player.onLeadCards();
			// TODO strategy
		}
	}

	private boolean canAct(Player player) {
		// TODO Auto-generated method stub
		return player.currentCardsNum() > 0;
	}

	public void start() {
		cardStack = new CardStack();
		cardStack.shuffle();
		
		// 最初的四张牌
		for (Player player : playerList) {
			for (int i = 0; i < 4; i++) {
				Card card = cardStack.nextCard();
				player.addCard(card);
			}
		}
		
		while (!end()) {
			for (Player player : playerList) {
				judge(player);
				dealCards(player);
				leadCards(player);
				endRound(player);
			}
		}
	}
}
