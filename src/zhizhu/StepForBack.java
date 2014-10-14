package zhizhu;

import java.util.List;

public class StepForBack {

	int from;
	
	int now;
	
	List<Card> cards;
	
	boolean isShowNewCard;

	public StepForBack(int from, int now, List<Card> cards) {
		super();
		this.from = from;
		this.now = now;
		this.cards = cards;
		
		this.isShowNewCard = false;
	}

	/**
	 * @return the isShowNewCard
	 */
	public boolean isShowNewCard() {
		return isShowNewCard;
	}

	/**
	 * @param isShowNewCard the isShowNewCard to set
	 */
	public void setShowNewCard(boolean isShowNewCard) {
		this.isShowNewCard = isShowNewCard;
	}

	/**
	 * @return the from
	 */
	public int getFrom() {
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(int from) {
		this.from = from;
	}

	/**
	 * @return the now
	 */
	public int getNow() {
		return now;
	}

	/**
	 * @param now the now to set
	 */
	public void setNow(int now) {
		this.now = now;
	}

	/**
	 * @return the cards
	 */
	public List<Card> getCards() {
		return cards;
	}

	/**
	 * @param cards the cards to set
	 */
	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
	

}
