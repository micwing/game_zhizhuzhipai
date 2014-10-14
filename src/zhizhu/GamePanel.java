package zhizhu;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4542133027546376618L;

	public GamePanel() {
		this.setBackground(new Color(39,139,34));
		// ��ʼ��ÿ���˿�������,���������ͻ�ɫ
		GameTool.initRandomCard(Cache.level);
		
		// ��ʼ��ÿ���˿���λ��
		initCardLoc();
		
		// ��ʼ��ÿ���˿��Ƶļ�����
		initMoveActionListener();
		
		// ����һ��
		pushCards();
	}

	/**
	 *  ��ʼ��ÿ���˿���λ��
	 */
	public void initCardLoc() {
		this.setLayout(null);

		PanelTool.initEmptyLabel();
		
		// ��ʼ������һ��10����
		PanelTool.updateCardList(this);
		
		// ��ʼ������6����������
		PanelTool.updateReadyList(this);
		
		PanelTool.initUserInfoLabel(this);
	}
	
	/**
	 * ��ʼ��������
	 */
	public void initMoveActionListener() {
		// ��ʼ��10���Ƶļ���
		for (int i = 0; i < Cache.poolLists.length; i++) {
			for (int j = 0; j < Cache.poolLists[i].getList().size(); j++) {
				Card card = Cache.poolLists[i].getList().get(j);
				card.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {// ��������ƶ��¼�
						myMousePressed(e);
					}
					public void mouseReleased(MouseEvent e) {// �ƶ��Ʒ����¼�
						myMouseReleased(e);
					}
				});
				
				card.addMouseMotionListener(new MouseMotionListener() {// ����ס�����ƶ��¼�
					public void mouseDragged(MouseEvent e) {
						myMouseDragged(e);
					}

					public void mouseMoved(MouseEvent e) {
					}
				});
			}
		}
		
		// ��ʼ�������Ƶļ���
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < Cache.readyLists[i].getList().size(); j++) {
				Card card = Cache.readyLists[i].getList().get(j);
				card.addMouseListener(new MouseAdapter() {
					@SuppressWarnings("static-access")
					public void mouseReleased(MouseEvent e) {
						if (e.getButton() == e.BUTTON1) {// ���
							pushCards();
						}
					}
				});
			}
		}
	}

	/*--------------------------------------����ʵ�ֶ���start------------------------------------------*/
	/**
	 * ����ͷ�
	 */
	public void myMouseReleased(MouseEvent e) {
		Card card = (Card) e.getSource();

		int n = GameTool.getNearListNum(card);// �жϷŵ��ĸ�һ��

		CardList clist = Cache.poolLists[Cache.moveCardFromNo];
		
		boolean r = false;// r���������ƶ����Ƿ�ɹ�
		if (n == -1) {// Ϊ-1��˵��û���ҵ��˿�������
			PanelTool.updateCardList(this);
			return;
			
		} else {// ��Ϊ-1˵���ҵ��˿�������
			r = clist.moveSomeCard(Cache.poolLists[n]);
		}
		if (r) {//  �ƶ��ɹ����¼��step
			GameTool.setStepToCache(n, clist);
		}
		PanelTool.updateCardList(this);

		if (n > -1 && GameTool.isFinishCards(n, this)) {
			PanelTool.updateCardList(this);
			
			if (GameTool.isFinishAllCards()) {
				int re = JOptionPane.showConfirmDialog(this, "�ۣ�����������\n��Ҫ������Ϸ��", "���¿���", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (re == JOptionPane.OK_OPTION) {
					this.removeAll();
					GameTool.initCache();
					GameTool.initCacheList();
					GameTool.initRandomCard(Cache.level);
					this.initCardLoc();
					this.initMoveActionListener();
					this.pushCards();
				} else {
					System.exit(0);
				}
			}
		}
	}
	
	/**
	 * ������˿��Ƶ��õķ���
	 * @param e
	 */
	public void myMousePressed(MouseEvent e) {
		Cache.xMouse = e.getX();// ��̬������λ��
		Cache.yMouse = e.getY();// ��̬������λ��
		
		Card card = (Card) e.getSource();
		GameTool.setMoveCardToSetCache(card);// �����ƶ��Ƶ��ڴ�

		PanelTool.setCardTop(card, this);
		
	}
	
	/**
	 * ����ƶ����õķ���
	 * @param e
	 */
	public void myMouseDragged(MouseEvent e) {
		if (!GameTool.isCanMove()) {
			return;
		}
		for (Card card : Cache.moveCards) {
			card.setLocation(card.getX() + e.getX() - Cache.xMouse, card.getY() + e.getY() - Cache.yMouse);
		}
	}
	/*--------------------------------------����ʵ�ֶ���end------------------------------------------*/
	
	
	/**
	 * ���ƶ���
	 */
	public void pushCards() {
		for (CardList list : Cache.poolLists) {
			if (list.getList().size() == 0) {
				JOptionPane.showMessageDialog(this, "�п�λʱ��������");
				return;
			}
		}
		Cache.stepList.clear();
		for (int i = 0; i < 10; i++) {
			Card card = Cache.readyLists[Cache.pushCardNum].getList().get(i);
			card.showCard();
			
			Cache.poolLists[i].getList().add(card);
			card.removeMouseListener(card.getMouseListeners()[0]);

			card.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					myMousePressed(e);
				}
				public void mouseReleased(MouseEvent e) {
					myMouseReleased(e);
				}
			});
			card.addMouseMotionListener(new MouseMotionListener(){

				public void mouseDragged(MouseEvent e) {
					myMouseDragged(e);
				}

				public void mouseMoved(MouseEvent e) {
				}});
			PanelTool.updateCardList(this);
		}
		Cache.pushCardNum++;
	}
}
