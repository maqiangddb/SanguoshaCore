package me.corp_so.sanguosha.core;

import java.util.ArrayList;

public class PlayerState extends Target {

	protected String name;
	protected Role role = null;
	protected Group group; // 阵营
	protected int currentBlood = -1;
	protected int maxBlood = -1;
	protected ArrayList<Card> cards = new ArrayList<Card>();
	protected ArrayList<PlayCard> judgeCards = null;

	//added by MQ
	protected Equipment equipment;
	//end
	
	public PlayerState() {
		super();
		type = PLAYER;
	}

	public PlayerState(String name, Role role, Group group, int currentBlood,
			int maxBlood, ArrayList<Card> cards, ArrayList<PlayCard> judgeCards) {
		super();
		this.name = name;
		this.role = role;
		this.group = group;
		this.currentBlood = currentBlood;
		this.maxBlood = maxBlood;
		this.cards = cards;
		this.judgeCards = judgeCards;
	}

	@Override
	public String toString() {
		return name + "(" + role + ")(" + group + ")";
	}

	public Object id() {
		return id;
	}
	
}