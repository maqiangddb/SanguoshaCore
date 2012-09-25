package me.corp_so.sanguosha.core;

public class PlayCard extends Target {

	private Card realCard;
	private Card virtualCard;

	public PlayCard(Card realCard, Card virtualCard) {
		this.realCard = realCard;
		this.virtualCard = virtualCard;
		type = CARD;
		id = realCard.id;
	}

	public boolean isKill() {
		return virtualCard.isKill();
	}

	public int type() {
		return virtualCard.type();
	}

}
