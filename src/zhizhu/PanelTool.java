package zhizhu;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JLabel;

public class PanelTool {
	/**
	 * 初始化用户信息的JLabel
	 * @param panel
	 */
	public static void initUserInfoLabel(GamePanel panel) {
		JLabel infoLabel = new JLabel("可可编写的蜘蛛纸牌游戏，送给最爱的小小");
		infoLabel.setBounds(290, 520, 300, 200);
		panel.add(infoLabel);
		infoLabel.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				
			}
		});
	}

	/**
	 * 初始化标记为空牌的Label，设置进内存
	 */
	public static void initEmptyLabel() {
		int xLoca = Const.xLoca;
		for (int i = 0; i < 10; i++) {
			Cache.emptyLabels.get(i).setBounds(xLoca, Const.yLoca, Const.cardWidth, Const.cardHeight);
			Cache.emptyLabels.add(Cache.emptyLabels.get(i));
			xLoca = xLoca + Const.cardWidth + Const.xInsert;
		}
	}
	
	/**
	 * 刷新绘制一次牌
	 */
	public static void updateCardList(GamePanel panel) {
		panel.updateUI();
		
		int xLoca = Const.xLoca;
		for (int i = 0; i < 10; i++) {
			List<Card> list = Cache.poolLists[i].getList();

			int yLocaTemp = Const.yLoca;
			// 该数组用了保存每张牌的位置
			int[] yLocs = new int[list.size()];
			for (int j = 0; j < list.size(); j++) {
				yLocs[j] = yLocaTemp;
				if (list.get(j).isShow()) {
					yLocaTemp = yLocaTemp + Const.yShowStep;
				} else {
					yLocaTemp = yLocaTemp + Const.yHiddenStep;
				}
			}		
			
			// 显示牌
			for (int j = 0; j < list.size(); j++) {
				Card card = list.get(list.size() - 1 - j);				
				card.setBounds(xLoca, yLocs[list.size() - 1 -j], Const.cardWidth, Const.cardHeight);
				panel.add(card);
			}
			xLoca = xLoca + Const.cardWidth + Const.xInsert;
		}
		
		for (int i = 0; i < 10; i++) {
			panel.add(Cache.emptyLabels.get(i));
		}
	}

	/**
	 * 更新待发的牌
	 * @param panel
	 */
	public static void updateReadyList(GamePanel panel) {
		panel.updateUI();
		int xLoc = 650;
		int yLoc = 530;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < Cache.readyLists[i].getList().size(); j++) {
				Card card = Cache.readyLists[i].getList().get(j);
				card.setBounds(xLoc, yLoc, 71, 96);
				panel.add(card);
			}
			xLoc = xLoc + 15;
		}
	}
	
	/**
	 * 把要移动的牌置顶
	 */
	public static void setCardTop(Card c, GamePanel panel) {
		panel.updateUI();
		List<Card> list = Cache.poolLists[Cache.moveCardFromNo].getList();
		for (int j = 0; j < list.size(); j++) {
			Card card = list.get(list.size() - 1 - j);
			panel.add(card);
		}
		for (int i = 0; i < 10; i++) {
			if (i == Cache.moveCardFromNo) {
				continue;
			}
			for (int j = 0; j < Cache.poolLists[i].getList().size(); j++) {
				Card card = Cache.poolLists[i].getList().get(Cache.poolLists[i].getList().size() - 1 - j);
				panel.add(card);
			}
		}
		for (int i = 0; i < 10; i++) {
			panel.add(Cache.emptyLabels.get(i));
		}
	}
	
	/**
	 * 更新玩完成的牌
	 */
	public static void updateFinishList(GamePanel panel) {
		panel.updateUI();
		int xLoc = 30 + 15 * Cache.finishLists.size();
		int yLoc = 530;
		for (int i = 0; i < Cache.finishLists.size(); i++) {
			for (int j = 0; j < Cache.finishLists.get(i).size(); j++) {
				Card card = Cache.finishLists.get(Cache.finishLists.size() - 1 - i).get(Cache.finishLists.get(i).size() - 1 - j);
				card.setBounds(xLoc, yLoc, 71, 96);
				panel.add(card);
			}
			xLoc = xLoc - 15;
		}
	}
}
