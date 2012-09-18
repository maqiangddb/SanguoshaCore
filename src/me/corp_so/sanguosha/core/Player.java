package me.corp_so.sanguosha.core;

public class Player {

	private Role role = null;
	public int currentBlood;
	private int currentCardsNum;
	private Group group; // 阵营
	private String name;
	
	public Player(String name) {
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
		currentCardsNum++;
	}
	
	public int currentCardsNum() {
		return currentCardsNum;
	}

	public ReactStrategy onLeadCards() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		this.role = role;
	}

	public boolean isKing() {
		return group.isKing();
	}
	
}
