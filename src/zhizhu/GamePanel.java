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
		// 初始化每个扑克牌数据,包括点数和花色
		GameTool.initRandomCard(Cache.level);
		
		// 初始化每个扑克牌位置
		initCardLoc();
		
		// 初始化每个扑克牌的监听器
		initMoveActionListener();
		
		// 发牌一次
		pushCards();
	}

	/**
	 *  初始化每个扑克牌位置
	 */
	public void initCardLoc() {
		this.setLayout(null);

		PanelTool.initEmptyLabel();
		
		// 初始化上面一行10列牌
		PanelTool.updateCardList(this);
		
		// 初始化下面6叠待发的牌
		PanelTool.updateReadyList(this);
		
		PanelTool.initUserInfoLabel(this);
	}
	
	/**
	 * 初始化监听器
	 */
	public void initMoveActionListener() {
		// 初始化10列牌的监听
		for (int i = 0; i < Cache.poolLists.length; i++) {
			for (int j = 0; j < Cache.poolLists[i].getList().size(); j++) {
				Card card = Cache.poolLists[i].getList().get(j);
				card.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {// 点击牌想移动事件
						myMousePressed(e);
					}
					public void mouseReleased(MouseEvent e) {// 移动牌放手事件
						myMouseReleased(e);
					}
				});
				
				card.addMouseMotionListener(new MouseMotionListener() {// 鼠标点住击牌移动事件
					public void mouseDragged(MouseEvent e) {
						myMouseDragged(e);
					}

					public void mouseMoved(MouseEvent e) {
					}
				});
			}
		}
		
		// 初始化待发牌的监听
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < Cache.readyLists[i].getList().size(); j++) {
				Card card = Cache.readyLists[i].getList().get(j);
				card.addMouseListener(new MouseAdapter() {
					@SuppressWarnings("static-access")
					public void mouseReleased(MouseEvent e) {
						if (e.getButton() == e.BUTTON1) {// 左击
							pushCards();
						}
					}
				});
			}
		}
	}

	/*--------------------------------------监听实现动作start------------------------------------------*/
	/**
	 * 鼠标释放
	 */
	public void myMouseReleased(MouseEvent e) {
		Card card = (Card) e.getSource();

		int n = GameTool.getNearListNum(card);// 判断放到哪个一列

		CardList clist = Cache.poolLists[Cache.moveCardFromNo];
		
		boolean r = false;// r用来保存移动牌是否成功
		if (n == -1) {// 为-1则说明没有找到了靠近的列
			PanelTool.updateCardList(this);
			return;
			
		} else {// 不为-1说明找到了靠近的列
			r = clist.moveSomeCard(Cache.poolLists[n]);
		}
		if (r) {//  移动成功则记录该step
			GameTool.setStepToCache(n, clist);
		}
		PanelTool.updateCardList(this);

		if (n > -1 && GameTool.isFinishCards(n, this)) {
			PanelTool.updateCardList(this);
			
			if (GameTool.isFinishAllCards()) {
				int re = JOptionPane.showConfirmDialog(this, "哇，您好厉害！\n还要继续游戏吗？", "重新开局", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
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
	 * 鼠标点击扑克牌调用的方法
	 * @param e
	 */
	public void myMousePressed(MouseEvent e) {
		Cache.xMouse = e.getX();// 静态存放鼠标位置
		Cache.yMouse = e.getY();// 静态存放鼠标位置
		
		Card card = (Card) e.getSource();
		GameTool.setMoveCardToSetCache(card);// 设置移动牌到内存

		PanelTool.setCardTop(card, this);
		
	}
	
	/**
	 * 鼠标移动调用的方法
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
	/*--------------------------------------监听实现动作end------------------------------------------*/
	
	
	/**
	 * 发牌动作
	 */
	public void pushCards() {
		for (CardList list : Cache.poolLists) {
			if (list.getList().size() == 0) {
				JOptionPane.showMessageDialog(this, "有空位时不允许发牌");
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
