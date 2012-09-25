package me.corp_so.sanguosha.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.corp_so.io.File;

public class CardStack {
	
	private ArrayList<Card> leftCards = new ArrayList<Card>();
	private ArrayList<Card> waitCards = new ArrayList<Card>(); // 废牌
	
	public CardStack() {
		String fileName = "cards";
		File file = new File(fileName);
		String content = file.getContents();
		try {
			leftCards = parseCards(content);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private ArrayList<Card> parseCards(String str) throws Exception {
		ArrayList<Card> list = new ArrayList<Card>();
		String [] cates = str.split("\n\n");
		for (String c : cates) {
			String type = null;
			String color = null;
			String point = null;
			String [] lines = c.split("\n");
			for (String l : lines) {
				// TODO 这里的正则都不太严谨
				Matcher matcher;
				matcher = Pattern.compile("# (.*)").matcher(l);
				if (matcher.find()) {
					type = matcher.group(1);
					continue;
				}
				matcher = Pattern.compile("(黑桃|红桃|梅花|方片)：(.*)").matcher(l);
				if (matcher.find()) {
					color = matcher.group(1);
					String [] points = matcher.group(2).split("、");
					for (String rawPoint : points) {
						matcher = Pattern.compile("(.*)（(\\d)张）").matcher(rawPoint);
						Card card = null;
						if (matcher.find()) {
							point = matcher.group(1);
							int cardsNum = Integer.valueOf(matcher.group(2));
							for (int i = 0; i < cardsNum; i++) {
								card = new Card(list.size(), type, color, point);
								list.add(card);
							}
						} else {
							point = rawPoint;
							card = new Card(list.size(), type, color, point);
							list.add(card);
						}
					}
					continue;
				}
				matcher = Pattern.compile("> (.*)").matcher(l);
				if (matcher.find()) {
					continue;
				}
				throw new Exception("should not be here");
			}
		}
		return list;
	}
	
	public Card nextCard() {
		int left = leftCards.size();
		if (left == 0) {
			leftCards = new ArrayList<Card>(waitCards);
			shuffle();
		}
		Card card = leftCards.remove(left - 1);
		waitCards.add(card);
		return card;
	}
	
	public int leftCardsNum() {
		return leftCards.size();
	}
	
	public void shuffle() {
		Collections.shuffle(leftCards);
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("牌堆里的牌(" + leftCards.size() + "):\n");
		for (Card card : leftCards) {
			sb.append(card.toString() + "\n");
		}
		sb.append("废牌(" + waitCards.size() + "):\n");
		for (Card card : waitCards) {
			sb.append(card.toString() + "\n");
		}
		return sb.toString();
	}
}
