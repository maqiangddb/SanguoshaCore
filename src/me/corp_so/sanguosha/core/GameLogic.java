package me.corp_so.sanguosha.core;

import java.util.ArrayList;

// 只能运行一份
public abstract class GameLogic {
	private CardStack cardStack;
	private ArrayList<Player> playerList = new ArrayList<Player>();
	
	private boolean willJudge = true; // 是否有判定阶段
	private boolean willDeal = true;  // 是否有摸牌阶段
	private boolean willLead = true;  // 是否有出牌阶段
	
	private int playersNum = 8; // 玩家数量
	
	private int  kingNum = 1; // 主公数量
	private int loyalNum = 2; // 忠臣数量
	private int cheatNum = 1; // 奸臣数量
	private int rebelNum = 4; // 反贼数量

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
		
		for (int i = 0; i < playersNum; i++) {
			Player player = new Player("玩家" + (i+1));
			playerList.add(player);
		}
		
		assignGroup(); // 分配阵营
		
		for (Player player : playerList) {
			// 玩家选择角色
			// 有三个可供选择
			ArrayList<Role> list = Role.random(3);
			Role role = userChooseRole(player, list);
			player.setRole(role);
		}
		
		dinfo();
		
		// 最初的四张牌
		for (Player player : playerList) {
			for (int i = 0; i < 4; i++) {
				Card card = cardStack.nextCard();
				player.addCard(card);
			}
		}
		
		run();
	}

	protected abstract Role userChooseRole(Player player, ArrayList<Role> list);

	/**
	 * print info for debug
	 */
	private void dinfo() {
		for (Player player : playerList) {
			d(player.name() + "(" + player.roleName() + ")");
		}
	}

	/**
	 * 分配阵营
	 */
	private void assignGroup() {
		int i = playersNum;
		int iKing = kingNum;
		int iLoyal = loyalNum;
		int iCheat = cheatNum;
		int iRebel = rebelNum;
		while (i > 0) {
			int group = Group.random();
			switch (group) {
			case Group.KING:
				if (iKing > 0) {
					playerList.get(i-1).setGroup(group);
					iKing--;
					i--;
				}
				break;
			case Group.LOYAL:
				if (iLoyal > 0) {
					playerList.get(i-1).setGroup(group);
					iLoyal--;
					i--;
				}
				break;
			case Group.CHEAT:
				if (iCheat > 0) {
					playerList.get(i-1).setGroup(group);
					iCheat--;
					i--;
				}
				break;
			case Group.REBEL:
				if (iRebel > 0) {
					playerList.get(i-1).setGroup(group);
					iRebel--;
					i--;
				}
			}
		}
	}

	protected void d(String str) {
		System.out.println(str);
	}

	private void run() {
		while (!end()) {
			for (Player player : playerList) {
				judge(player);
				dealCards(player);
				leadCards(player);
				endRound(player);
			}
		}
	}

	public void setPlayersNum(int num) {
		playersNum  = num;
	}

	/**
	 * 
	 * @param loyal 忠臣数量
	 * @param cheat 奸臣数量
	 * @param rebel 反贼数量
	 * @throws Exception 
	 */
	public void setGroupDiv(int loyal, int cheat, int rebel) throws Exception {
		int king = kingNum;
		if ((loyal + cheat + rebel + king ) != playersNum) {
			throw new Exception("数量不对");
		}
		loyalNum = loyal;
		cheatNum = cheat;
		rebelNum = rebel;
	}
}
