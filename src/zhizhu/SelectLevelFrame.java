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
		button1.setText(" 初级 : 单色");
		
		button2 = new JRadioButton();
		button2.setText(" 中级 : 双色");
		button2.setSelected(true);
		Cache.level = Const.GAME_LEVEL_NORMAL;
		
		button3 = new JRadioButton();
		button3.setText(" 困难 : 四色");
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(button1);
		bg.add(button2);
		bg.add(button3);
		
		JLabel tips = new JLabel("请选择游戏的难易级别：");
		tips.setBounds(10, 1, 200, 30);
		panel.add(tips);
		
		button1.setBounds(75, 43, 155, 20);
		button2.setBounds(75, 63, 155, 20);
		button3.setBounds(75, 83, 155, 20);
		panel.add(button1);
		panel.add(button2);
		panel.add(button3);
		
		JButton ok = new JButton("确定");
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
		JButton cancel = new JButton("取消");
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
		this.setTitle("难易级别");
		this.setVisible(true);
		
	}
	
	/**
	 * 初始化窗口位置
	 */
	public void initLocation() {
		int windowWidth = this.getWidth(); // 获得窗口宽
		int windowHeight = this.getHeight(); // 获得窗口高
		Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
		Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
		int screenWidth = screenSize.width; // 获取屏幕的宽
		int screenHeight = screenSize.height; // 获取屏幕的高
		this.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2
				- windowHeight / 2);// 设置窗口居中显示
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
