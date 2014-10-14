package zhizhu;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class Card extends JLabel {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -495643825896146278L;

	private String type;
	
	private int num;
	
	private boolean isShow;
	
	private String picture;

	/**
	 * @return the picture
	 */
	public String getPicture() {
		return picture;
	}

	/**
	 * @param picture the picture to set
	 */
	public void setPicture(String picture) {
		this.picture = picture;
	}

	/**
	 * @return the isShow
	 */
	public boolean isShow() {
		return isShow;
	}

	/**
	 * @param isShow the isShow to set
	 */
	public void setShow(boolean isShow) {
		this.isShow = isShow;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the num
	 */
	public int getNum() {
		return num;
	}

	/**
	 * @param num the num to set
	 */
	public void setNum(int num) {
		this.num = num;
	}

	public Card(String type, int num) {
		super();
		this.type = type;
		this.num = num;
		isShow = false;
		picture = Cache.cardBackPic;
		this.setIcon(new ImageIcon(picture));
//		showCard();//É¾µô¸ÃÐÐ
	}
	
	public void showCard() {
		setShow(true);
		setPicture("img/" + type + num +".gif");
		this.setIcon(new ImageIcon(getPicture()));
	}

	public void hiddenCard() {
		setShow(false);
		setPicture(Cache.cardBackPic);
		this.setIcon(new ImageIcon(getPicture()));
	}
}
