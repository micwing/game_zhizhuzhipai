package zhizhu;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class GameTool {
	public static void initCache() {
		Cache.cardBackPic = "img/back.gif";
		Cache.cardEmptyPic = "img/empty.gif";
		Cache.pushCardNum = 0;
		Cache.ctrlKeyPresswd = false;
		
		if (Cache.moveCards != null) {
			Cache.moveCards.clear();
		} else {
			Cache.moveCards = new ArrayList<Card>();
		}
		if (Cache.emptyLabels != null) {
			Cache.emptyLabels.clear();
		} else {
			Cache.emptyLabels = new ArrayList<JLabel>();
		}
		if (Cache.finishCards != null) {
			Cache.finishCards.clear();
		} else {
			Cache.finishCards = new ArrayList<Card>();
		}
		if (Cache.stepList != null) {
			Cache.stepList.clear();
		} else {
			Cache.stepList = new ArrayList<StepForBack>();
		}
		if (Cache.finishLists != null) {
			Cache.finishLists.clear();
		} else {
			Cache.finishLists = new ArrayList<List<Card>>();
		}
		
	}
	
	/*-----------------------------------�˿��Ƴ�ʼ������start------------------------------------*/
	/**
	 * ��ʼ���ڴ��ﱣ��
	 */
	public static void initCacheList() {
		// ��ʼ������
		Cache.poolLists = new CardList[10];
		Cache.readyLists = new CardList[6];
		Cache.emptyLabels = new ArrayList<JLabel>();
		for (int i = 0; i < 10; i++) {
			Cache.poolLists[i] = new CardList();
		}
		for (int i = 0; i < 6; i++) {
			Cache.readyLists[i] = new CardList();
		}
		for (int i = 0; i < 10; i++) {
			JLabel label = new JLabel(new ImageIcon(Cache.cardEmptyPic));
			Cache.emptyLabels.add(label);
		}
	}
	
	/**
	 * ��ʼ�����ҵ��˿���
	 * @param level
	 */
	public static void initRandomCard(int level) {
		List<Card> list = GameTool.getPrepareCard(level);
		while(list.size() != 0) {
			Random r = new Random();
			Card card = list.get(r.nextInt(list.size()));
			randomPutCard(card, Cache.poolLists, Cache.readyLists);
			list.remove(card);
		}
	}

	/**
	 * ׼����˳���ƣ����������
	 * @param level
	 * @return
	 */
	private static List<Card> getPrepareCard(int level) {
		List<Card> list = new LinkedList<Card>();
		if (level == Const.GAME_LEVEL_SIMPLE) {
			for (int i = 0; i < 8; i++) {
				for (int j = 1; j <= 13; j++) {
					list.add(new Card(Const.HEITAO, j));
				}
			}
		}
		if (level == Const.GAME_LEVEL_NORMAL) {
			for (int i = 0; i < 4; i++) {
				for (int j = 1; j <= 13; j++) {
					list.add(new Card(Const.HEITAO, j));
				}
			}
			for (int i = 0; i < 4; i++) {
				for (int j = 1; j <= 13; j++) {
					list.add(new Card(Const.HONGTAO, j));
				}
			}
		}
		if (level == Const.GAME_LEVEL_HARD) {
			for (int i = 0; i < 2; i++) {
				for (int j = 1; j <= 13; j++) {
					list.add(new Card(Const.HEITAO, j));
				}
			}
			for (int i = 0; i < 2; i++) {
				for (int j = 1; j <= 13; j++) {
					list.add(new Card(Const.HONGTAO, j));
				}
			}
			for (int i = 0; i < 2; i++) {
				for (int j = 1; j <= 13; j++) {
					list.add(new Card(Const.CAOHUA, j));
				}
			}
			for (int i = 0; i < 2; i++) {
				for (int j = 1; j <= 13; j++) {
					list.add(new Card(Const.FANGPIAN, j));
				}
			}
		}
		return list;
	}
	
	/**
	 * ������ҿ�Ƭ,
	 * @param card
	 * @param poolLists
	 * @param readyLists
	 */
	private static void randomPutCard(Card card, CardList[] poolLists, CardList[] readyLists) {
		for (int i = 0; i < 4; i++) {
			if (poolLists[i].getList().size() < 5) {
				poolLists[i].getList().add(card);
				return;
			}
		}
		
		for (int i = 4; i < 10; i++) {
			if (poolLists[i].getList().size() < 4) {
				poolLists[i].getList().add(card);
				return;
			}
		}
		
		for (int i = 0; i < 6; i++) {
			if (readyLists[i].getList().size() < 10) {
				readyLists[i].getList().add(card);
				return;
			}
		}
	}
	/*-----------------------------------�˿��Ƴ�ʼ������end------------------------------------*/
	
	/*-----------------------------------�ƶ��˿���ʱ���õķ���start-----------------------------*/
	/**
	 * �趨 Ҫ�ƶ����� �Լ� ������ ���ڴ��� 
	 * int[0] ���ĸ���
	 * int[1] �ƶ����ٸ�
	 * @param card
	 * @return
	 */
	public static void setMoveCardToSetCache(Card card) {
		Cache.moveCards.clear();
		for (int i = 0; i < 10; i++) {
			int size = Cache.poolLists[i].getList().size();
			for (int j = 0; j < size; j++) {
				if (card == Cache.poolLists[i].getList().get(size - 1 - j)) {
					Cache.moveCardFromNo = i;
					for (int k =0; k < j + 1; k++) {
						Cache.moveCards.add(Cache.poolLists[i].getList().get(size - 1 - j + k));
					}
					return;
				}
			}
		}
		//System.out.println("GameTool.java getMoveCardFromNo ͨ��һ����Ѱ�Ҹ�����������һ�� ����");
	}
	
	/**
	 * ���ش������������CardList��
	 * @param c
	 * @return
	 */
	public synchronized static int getNearListNum(Card c) {
		Point p = c.getLocation();
		CardList[] poolLists = Cache.poolLists;
		for (int i = 0; i < 10; i++) {
			Point temp = null;
			List<Card> list = poolLists[i].getList();
			if (list.size() == 0) {// �������û����
				temp = Cache.emptyLabels.get(i).getLocation();
			} else {// ��������
				Card card = poolLists[i].getList().get(poolLists[i].getList().size() - 1);
				// ������һ����Ҫ�ƶ����Ƶ����һ��
				if (card == Cache.moveCards.get(Cache.moveCards.size() - 1)) {
					continue;
				}
				temp = card.getLocation();
			}
			// temp���ұ�С��p�������
			if (temp.getX() + Const.cardWidth < p.getX() + Const.cardWidth/2) {
				continue;
			}
			// temp������ߴ���p�����ұ�
			if (temp.getX() > p.getX() + Const.cardWidth/2) {
				continue;
			}
			// temp�����ϱߴ���p��������
			if (temp.getY() > p.getY() + Const.cardHeight) {
				continue;
			}
			// temp��������С��p��������
			if (temp.getY() + Const.cardHeight < p.getY()) {
				continue;
			}
			return i;
		}
		return -1;
	}

	/**
	 * �ж��Ƿ�����ƶ���
	 * @return
	 */
	public static boolean isCanMove() {
		if (Cache.moveCards.size() == 1) {
			return true;
		}
		// �ƶ�������
		// ѭ���ж�cards������Ƿ�����ƶ�Ҫ��
		for (int i = 1; i < Cache.moveCards.size(); i++) {
			// ����������������ƻ�ɫ����ͬ�������ƶ�
			if (!Cache.moveCards.get(i).getType().equals(Cache.moveCards.get(i - 1).getType())) {
				return false;
			}
			// ����������������Ƶ����������1�������ƶ�
			if (Cache.moveCards.get(i).getNum() - Cache.moveCards.get(i - 1).getNum() != -1) {
				return false;
			}
		}
		// ѭ���ж�ͨ�������ƶ���ÿ���ƶ�����Ҫ��
		return true;
	}
	/*-----------------------------------�ƶ��˿���ʱ���õķ���end-----------------------------*/
	
	/*-----------------------------------�ƶ�����˵ĵ��õķ���start-----------------------------*/
	/**
	 * ����
	 */
	public static void cardGoBack(GamePanel panel) {
		if (Cache.stepList.size() == 0) {
			return;
		}
		StepForBack step = Cache.stepList.get(Cache.stepList.size() - 1);
		
		if (step.isShowNewCard()) {
			List<Card> list = Cache.poolLists[step.getFrom()].getList();
			list.get(list.size() - 1).hiddenCard();
		}
		
		List<Card> list = step.getCards();
		for (int i = 0; i < list.size(); i++) {
			Card card = list.get(i);
			Cache.poolLists[step.getFrom()].getList().add(card);
			Cache.poolLists[step.getNow()].getList().remove(card);
		}
		Cache.stepList.remove(step);
		PanelTool.updateCardList(panel);
	}
	/*-----------------------------------�ƶ�����˵ĵ��õķ���end-----------------------------*/
	
	/*-----------------------------------�ƶ����¼�����ĵ��õķ���start-----------------------------*/
	/**
	 * ��¼һ���ƶ��ƵĲ���
	 */
	public static void setStepToCache(int n,CardList clist) {
		StepForBack st = null;// ׼����¼�ƶ�����

		// cs��ȡ�ƶ�����
		List<Card> cs = new ArrayList<Card>();
		for (int i = 0; i < Cache.moveCards.size();i++) {
			cs.add(Cache.moveCards.get(i));
		}
		st = new StepForBack(Cache.moveCardFromNo, n, cs);
		
		if (clist.getList().size() != 0) {// �����ƺ�������л�����
			Card cd = clist.getList().get(clist.getList().size() - 1);// �������һ����
			if (!cd.isShow()) {// ���һ�������ص���
				st.setShowNewCard(true);// ��һ�����ƣ���¼��step��
				cd.showCard();
			}
		}
		Cache.stepList.add(st);
	}
	/*-----------------------------------�ƶ����¼�����ĵ��õķ���end-----------------------------*/

	/*-----------------------------------��������ƺ���õķ���start-----------------------------*/
	/**
	 * �Ƿ����һ����
	 */
	public static boolean isFinishCards(int n, GamePanel panel) {
		List<Card> list = Cache.poolLists[n].getList();
		if (list.size() < 13) {
			return false;
		}
		if (list.get(list.size() - 1).getNum() != 1) {
			return false;
		}
		Cache.finishCards.clear();
		Cache.finishCards.add(list.get(list.size() - 1));
		for (int i = 1; i < 13; i++) {
			if (list.get(list.size() - 1 - i).getNum() - list.get(list.size() - i).getNum() != 1 ||
					!list.get(list.size() - 1 - i).getType().equals(list.get(list.size() - i).getType())) {
				return false;
			}
			Cache.finishCards.add(list.get(list.size() - 1 - i));
		}

		int size = list.size();
		// do
		for (int i = 0; i < 13; i++) {
			panel.remove(list.get(size - 1 - i));
			list.remove(size - 1 - i);
		}
		if (list.size()!=0 && !list.get(list.size() - 1).isShow()) {
			list.get(list.size() - 1).showCard();
		}
		Cache.stepList.clear();
		List<Card> lst = new ArrayList<Card>();
		for (int i = 0; i < Cache.finishCards.size(); i++) {
			lst.add(Cache.finishCards.get(i));
		}
		Cache.finishLists.add(lst);
		PanelTool.updateFinishList(panel);
		Cache.finishCards.clear();
		return true;
	}
	
	/**
	 * �ж��Ƿ�ȫ�����
	 * @return
	 */
	public static boolean isFinishAllCards() {
		for (CardList cardList : Cache.poolLists) {
			if (cardList.getList().size() != 0) {
				return false;
			}
		}
		return true;
	}
	/*-----------------------------------��������ƺ���õķ���end-----------------------------*/
}
