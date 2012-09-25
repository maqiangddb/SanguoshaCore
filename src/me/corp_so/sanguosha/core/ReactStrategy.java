package me.corp_so.sanguosha.core;

public class ReactStrategy {
	private PlayCard card;
	Target target1;
	private Target target2;
	private Target target3;
	private Equipment equip;
	private String special;
	
	public ReactStrategy(PlayCard card, PlayerState player) {
		this.card = card;
		this.target1 = player;
	}
	
	public int cardType() {
		return card.type();
	}
	
	public boolean end() {
		// TODO 需要一个变量来判断是否需要end？还是直接检测 card 和 target1 都是 null ？
		return false;
	}
	
	public PlayCard card() {
		return card;
	}
}
