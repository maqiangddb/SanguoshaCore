package me.corp_so.sanguosha.core;

import java.util.ArrayList;

import javax.management.relation.RoleList;

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
		d("摸牌阶段");
		Card card1 = cardStack.nextCard();
		Card card2 = cardStack.nextCard();
		d(player.toString() + "得到" + card1);
		player.addCard(card1);
		d(player.toString() + "得到" + card2);
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
		d("判定阶段");
		
		if (!willJudge) {
			return;
		}
		if (!player.hasJudgeCards()) {
			d("无需判定,跳过");
			return;
		} else {
			d("需要判定");
		}
	}

	private void leadCards(Player player) {
		// TODO Auto-generated method stub
		if (!willLead) {
			return;
		}
		d("出牌阶段");
		while (canAct(player)) {
			d("请出牌");
			dinfo();
			ReactStrategy strategy = null;
			try {
				strategy = player.onLeadCards();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// TODO strategy
		}
	}

	private boolean canAct(Player player) {
		// TODO Auto-generated method stub
		return player.currentCardsNum() > 0;
	}

	public void start() {
		d("拿出来牌");
		cardStack = new CardStack();
		
		d("洗牌");
		cardStack.shuffle();
		
		for (int i = 0; i < playersNum; i++) {
			Player player = new Player("玩家" + (i+1));
			playerList.add(player);
		}
		
		d("分配阵营");
		assignGroup(); // 分配阵营
		
		sort();
		
		selectRole(); // 玩家选择角色
		
		// 最初的四张牌
		for (int i = 0; i < 4; i++) {
			for (Player player : playerList) {
				Card card = cardStack.nextCard();
				d(player.toString() + " 得到一张 " + card);
				player.addCard(card);
			}
		}
		
		dinfo();
		
		run();
	}

	private void run() {
		while (!end()) {
			for (Player player : playerList) {
				d(player.toString() + "的回合开始");
				judge(player);
				dealCards(player);
				leadCards(player);
				endRound(player);
			}
		}
	}

	private void sort() {
		// 调整顺序,使主公排在第一
		Player king = selectKing();
		int kingPos = playerList.indexOf(king);
		for (int i = 0; i < kingPos; i++) {
			Player player = playerList.remove(0);
			playerList.add(player);
		}
	}

	private void selectRole() {
		
		// 主公选择
		ArrayList<Role> roleListForKing = Role.randomForKing();
		Player king = selectKing();
		Role kingRole = userChooseRole(king, roleListForKing);
		king.setRole(kingRole);
		Role.setChosen(kingRole); // 表明已经被选择了,不能再次选择此角色
		
		int kingPos = playerList.indexOf(king);
		playerList.remove(king);
		
		// 玩家选择角色, 有三个可供选择
		int size = playerList.size();
		ArrayList<ArrayList<Role>> list = Role.random(size, 3);
		for (int i = 0; i < size; i++) {
			ArrayList<Role> l = list.get(i);
			Player player = playerList.get(i);
			Role role = userChooseRole(player, l);
			player.setRole(role);
		}
		playerList.add(kingPos, king); // 复原
	}

	private Player selectKing() {
		for (Player p : playerList) {
			if (p.isKing()) {
				return p;
			}
		}
		return null;
	}

	protected abstract Role userChooseRole(Player player, ArrayList<Role> list);

	/**
	 * print info for debug
	 */
	private void dinfo() {
		d("场上局势");
		for (Player player : playerList) {
			d(player.name() + "(" + player.roleName() + ")(" + player.groupName() + ")-" + 
					player.currentCardsNum() + "张手牌" +
					"-血" + player.currentBlood() + "/" + player.maxBlood());
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
