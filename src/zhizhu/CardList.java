package zhizhu;

import java.util.ArrayList;
import java.util.List;

public class CardList {

	private List<Card> list;
	
	// 待发牌的List是否已经发牌
	private boolean isReadyListUsed;

	public CardList() {
		list = new ArrayList<Card>();
	}
	
	/**
	 * 移动若干张牌到另一个列
	 * @param targetCardList
	 * @param moveSize
	 * @return
	 */
	public boolean moveSomeCard(CardList targetCardList) {
		// 本List没有元素
		if (this.list.size() == 0) {
			return false;
		}
		// cards倒叙存放要移动的牌，例如移动5432，则cards存放为2345
		List<Card> cards = Cache.moveCards;
		// 目标List没有元素
		if (targetCardList.getList().size() == 0) {
			// 以5432的循序放进目标List
			for (int i = 0; i < cards.size(); i++) {
				targetCardList.getList().add(cards.get(i));
			}
			// 把本List中的5432删除
			for (int i = 0; i < cards.size(); i++) {
				this.list.remove(cards.get(i));				
			}
			return true;
		}
		// 本List有元素，目标List也有元素
		// 目标List的最后一张牌
		Card targetCard = targetCardList.getList().get(targetCardList.getList().size() - 1);
		// 点数相差1，则符合要求
		if (targetCard.getNum() - cards.get(0).getNum() == 1) {
			// 以5432的循序放进目标List
			for (int i = 0; i < cards.size(); i++) {
				targetCardList.getList().add(cards.get(i));
			}
			// 把本List中的5432删除
			for (int i = 0; i < cards.size(); i++) {
				this.list.remove(cards.get(i));				
			}
			return true;
		}
		// 点数不符合要求
		return false;
		
	}

	/**
	 * @return the isReadyListUsed
	 */
	public boolean isReadyListUsed() {
		return isReadyListUsed;
	}

	/**
	 * @param isReadyListUsed the isReadyListUsed to set
	 */
	public void setReadyListUsed(boolean isReadyListUsed) {
		this.isReadyListUsed = isReadyListUsed;
	}

	/**
	 * @return the list
	 */
	public List<Card> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<Card> list) {
		this.list = list;
	}
	
	
}
