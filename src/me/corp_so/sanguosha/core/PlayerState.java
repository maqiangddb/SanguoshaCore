package me.corp_so.sanguosha.core;

import java.util.ArrayList;

public class PlayerState {

	protected String name;
	protected Role role = null;
	protected Group group; // 阵营
	protected int currentBlood = -1;
	protected int maxBlood = -1;
	protected ArrayList<Card> cards = new ArrayList<Card>();
	protected ArrayList<JudgeCard> judgeCards = null;
	
	public PlayerState() {
		super();
	}

}