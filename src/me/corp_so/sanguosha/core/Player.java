package me.corp_so.sanguosha.core;

public class Player {
	private int role;
	private int identity;
	public int currentBlood;
	private int currentCardsNum;
	/**
	 * 
	 * @param card
	 * @return 现在的牌的数量
	 */
	public void addCard(Card card) {
		// TODO
		currentCardsNum++;
	}
	
	public int currentCardsNum() {
		return currentCardsNum;
	}

	public ReactStrategy onLeadCards() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
