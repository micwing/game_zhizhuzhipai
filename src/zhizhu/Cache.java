package zhizhu;

import java.util.List;

import javax.swing.JLabel;

public class Cache {
	
	public static CardList[] poolLists;
	public static CardList[] readyLists;
	public static List<List<Card>> finishLists;
	
	public static List<StepForBack> stepList;

	public static int level;
	
	public static String cardBackPic;
	public static String cardEmptyPic;
	
	public static int pushCardNum;
	

	public static int xMouse, yMouse;
	
	// 移动的牌
	public static List<Card> moveCards;
	public static List<Card> finishCards;
	
	// 标记列为空的JLabel
	public static List<JLabel> emptyLabels;
	// 从哪列移动的
	public static int moveCardFromNo;

	
	public static boolean isMousePressed;
	
	public static boolean ctrlKeyPresswd;
	

	
}
