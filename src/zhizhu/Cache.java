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
	
	// �ƶ�����
	public static List<Card> moveCards;
	public static List<Card> finishCards;
	
	// �����Ϊ�յ�JLabel
	public static List<JLabel> emptyLabels;
	// �������ƶ���
	public static int moveCardFromNo;

	
	public static boolean isMousePressed;
	
	public static boolean ctrlKeyPresswd;
	

	
}
