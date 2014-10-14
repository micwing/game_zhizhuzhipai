package zhizhu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class SelectLevelFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3424667996992933653L;
	
	JRadioButton button1 = null;
	
	JRadioButton button2 = null;
	
	JRadioButton button3 = null;
	
	public SelectLevelFrame() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		button1 = new JRadioButton();
		button1.setText(" ���� : ��ɫ");
		
		button2 = new JRadioButton();
		button2.setText(" �м� : ˫ɫ");
		button2.setSelected(true);
		Cache.level = Const.GAME_LEVEL_NORMAL;
		
		button3 = new JRadioButton();
		button3.setText(" ���� : ��ɫ");
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(button1);
		bg.add(button2);
		bg.add(button3);
		
		JLabel tips = new JLabel("��ѡ����Ϸ�����׼���");
		tips.setBounds(10, 1, 200, 30);
		panel.add(tips);
		
		button1.setBounds(75, 43, 155, 20);
		button2.setBounds(75, 63, 155, 20);
		button3.setBounds(75, 83, 155, 20);
		panel.add(button1);
		panel.add(button2);
		panel.add(button3);
		
		JButton ok = new JButton("ȷ��");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (button1.isSelected()) {
					Cache.level = Const.GAME_LEVEL_SIMPLE;
				}
				if (button2.isSelected()) {
					Cache.level = Const.GAME_LEVEL_NORMAL;
				}
				if (button3.isSelected()) {
					Cache.level = Const.GAME_LEVEL_HARD;
				}
				startGame();
			}
		});
		JButton cancel = new JButton("ȡ��");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				closeGame();
			}
		});
		ok.setBounds(60, 130, 70, 20);
		cancel.setBounds(155, 130, 70, 20);
		this.add(ok);
		this.add(cancel);
		
		button1.setBackground(new Color(220,220,220));
		button2.setBackground(new Color(220,220,220));
		button3.setBackground(new Color(220,220,220));
		panel.setBackground(new Color(220,220,220));
		this.add(panel);
		this.setSize(300, 200);
		initLocation();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("���׼���");
		this.setVisible(true);
		
	}
	
	/**
	 * ��ʼ������λ��
	 */
	public void initLocation() {
		int windowWidth = this.getWidth(); // ��ô��ڿ�
		int windowHeight = this.getHeight(); // ��ô��ڸ�
		Toolkit kit = Toolkit.getDefaultToolkit(); // ���幤�߰�
		Dimension screenSize = kit.getScreenSize(); // ��ȡ��Ļ�ĳߴ�
		int screenWidth = screenSize.width; // ��ȡ��Ļ�Ŀ�
		int screenHeight = screenSize.height; // ��ȡ��Ļ�ĸ�
		this.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2
				- windowHeight / 2);// ���ô��ھ�����ʾ
	}
	
	public void startGame() {
		this.dispose();
		new GameFrame();
	}
	
	public void closeGame() {
		this.dispose();
		System.exit(0);
	}

}
