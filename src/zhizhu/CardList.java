package zhizhu;

import java.util.ArrayList;
import java.util.List;

public class CardList {

	private List<Card> list;
	
	// �����Ƶ�List�Ƿ��Ѿ�����
	private boolean isReadyListUsed;

	public CardList() {
		list = new ArrayList<Card>();
	}
	
	/**
	 * �ƶ��������Ƶ���һ����
	 * @param targetCardList
	 * @param moveSize
	 * @return
	 */
	public boolean moveSomeCard(CardList targetCardList) {
		// ��Listû��Ԫ��
		if (this.list.size() == 0) {
			return false;
		}
		// cards������Ҫ�ƶ����ƣ������ƶ�5432����cards���Ϊ2345
		List<Card> cards = Cache.moveCards;
		// Ŀ��Listû��Ԫ��
		if (targetCardList.getList().size() == 0) {
			// ��5432��ѭ��Ž�Ŀ��List
			for (int i = 0; i < cards.size(); i++) {
				targetCardList.getList().add(cards.get(i));
			}
			// �ѱ�List�е�5432ɾ��
			for (int i = 0; i < cards.size(); i++) {
				this.list.remove(cards.get(i));				
			}
			return true;
		}
		// ��List��Ԫ�أ�Ŀ��ListҲ��Ԫ��
		// Ŀ��List�����һ����
		Card targetCard = targetCardList.getList().get(targetCardList.getList().size() - 1);
		// �������1�������Ҫ��
		if (targetCard.getNum() - cards.get(0).getNum() == 1) {
			// ��5432��ѭ��Ž�Ŀ��List
			for (int i = 0; i < cards.size(); i++) {
				targetCardList.getList().add(cards.get(i));
			}
			// �ѱ�List�е�5432ɾ��
			for (int i = 0; i < cards.size(); i++) {
				this.list.remove(cards.get(i));				
			}
			return true;
		}
		// ����������Ҫ��
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
