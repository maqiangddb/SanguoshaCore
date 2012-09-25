package me.corp_so.sanguosha.core;

import java.io.PrintStream;
import java.util.Scanner;

public class Player extends PlayerState {

	private GameState gameState;
	
	public Player(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return name + "(" + role + ")(" + group + ")";
	}

	/**
	 * 
	 * @param card
	 * @return 现在的牌的数量
	 */
	public void addCard(Card card) {
		// TODO
		cards.add(card);
	}
	
	public int currentCardsNum() {
		return cards.size();
	}

	public ReactStrategy onLeadCards() throws Exception {
		// TODO 这里的使用输入的形式,后面要改,不过我还不知道如何把这个策略抽象出来
		
		PrintStream o = System.out;
		Scanner scanner = new Scanner(System.in);
		o.println("您的现有手牌(" + currentCardsNum() + ")");
		int i = 0;
		for (Card card : cards) {
			i++;
			o.println("" + i + ". " + card);
		}
		o.println("选择要使用的手牌,输入0主动结束回合");
		int c = scanner.nextInt();
		if (c <= currentCardsNum()) {
			Card card = cards.get(c-1);
			PlayCard handCard = card.makePlayCard();
			o.println("您选择的手牌是" + card);
			if (card.needTarget()) {
				o.println("请选择您的目标");
				o.println(gameState.toString());
				c = scanner.nextInt();
				PlayerState target = gameState.selectPlayer(c);
				o.println("您选择的玩家是：" + target);
				return new ReactStrategy(handCard, target);
			}
		} else {
			throw new Exception("无此手牌");
		}
		
		return null;
	}

	public String roleName() {
		return role.name();
	}

	public void setGroup(int group) {
		this.group = new Group(group);
	}

	public String groupName() {
		try {
			return group.name();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String name() {
		// TODO Auto-generated method stub
		return name ;
	}

	public void setRole(Role role) {
		this.role = role;
		initBlood();
	}

	private void initBlood() {
		// 初始化血量
		maxBlood = role.blood();
		if (isKing()) {
			maxBlood++;
		}
		currentBlood = maxBlood;
	}

	public boolean isKing() {
		return group.isKing();
	}

	public boolean hasJudgeCards() {
		return judgeCards != null && !judgeCards.isEmpty();
	}

	public int currentBlood() {
		return currentBlood;
	}

	public int maxBlood() {
		return maxBlood;
	}

	public void setGameState(GameState minGameState) {
		gameState = minGameState;
	}

	// 显然，这里用非继承可以得到更简洁的代码
	public PlayerState extractState() {
		return new PlayerState(name, role, group, currentBlood, maxBlood, cards, judgeCards);
	}

	public ReactStrategy onReact(PlayCard handCard) {
		
		
		// TODO Auto-generated method stub
		
		return null;
	}
	
}
