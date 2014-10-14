package zhizhu;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class GameFrame extends JFrame implements ActionListener{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3720337171695583030L;
	
	GamePanel panel = null;

	public GameFrame() {
		
		GameTool.initCache();
		
		GameTool.initCacheList();
		
		init();
	}
	
	public void init() {
		initMenu();

		panel = new GamePanel();
		this.add(panel);
		
		initFrameSize();
		initLocation();
		this.setVisible(true);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("蜘蛛纸牌");
		
		this.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent e) {
			}
			
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
					Cache.ctrlKeyPresswd = false;
				}
			}
			
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
					Cache.ctrlKeyPresswd = true;
					return;
				}
				if (e.getKeyCode() == KeyEvent.VK_Z) {
					if (Cache.ctrlKeyPresswd) {
						GameTool.cardGoBack(panel);
					}
				}
			}
		});
	}
	
	public void initMenu() {
		JMenuBar bar=new JMenuBar();
		JMenu game=new JMenu("游戏");
		JMenu help=new JMenu("帮助");
		JMenuItem item;
		game.add(item=new JMenuItem("开局"));item.addActionListener(this);
		game.addSeparator();
		ButtonGroup bg=new ButtonGroup();
		game.add(item=new JCheckBoxMenuItem("初级"));bg.add(item);item.addActionListener(this);
		game.add(item=new JCheckBoxMenuItem("中级"));bg.add(item);item.addActionListener(this);
		game.add(item=new JCheckBoxMenuItem("高级"));bg.add(item);item.addActionListener(this);
		game.addSeparator();
		game.add(item=new JMenuItem("退出"));item.addActionListener(this);
	
		help.add(item=new JMenuItem("关于"));item.addActionListener(this);
		
		bar.add(game);
		bar.add(help);
		
		this.setJMenuBar(bar);
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
	
	/**
	 * 设置界面大小
	 */
	private void initFrameSize() {
		this.setSize(840, 700);
	}
	
	public void actionPerformed(ActionEvent e) {
		if ("开局".equals(e.getActionCommand())) {
			panel.removeAll();
			GameTool.initCache();
			GameTool.initCacheList();
			GameTool.initRandomCard(Cache.level);
			panel.initCardLoc();
			panel.initMoveActionListener();
			panel.pushCards();
			return;
		}
		if ("初级".equals(e.getActionCommand())) {
			Cache.level = Const.GAME_LEVEL_SIMPLE;
			panel.removeAll();
			GameTool.initCache();
			GameTool.initCacheList();
			GameTool.initRandomCard(Cache.level);
			panel.initCardLoc();
			panel.initMoveActionListener();
			panel.pushCards();
			return;
		}
		if ("中级".equals(e.getActionCommand())) {
			Cache.level = Const.GAME_LEVEL_NORMAL;
			panel.removeAll();
			GameTool.initCache();
			GameTool.initCacheList();
			GameTool.initRandomCard(Cache.level);
			panel.initCardLoc();
			panel.initMoveActionListener();
			panel.pushCards();
			return;
		}
		if ("高级".equals(e.getActionCommand())) {
			Cache.level = Const.GAME_LEVEL_HARD;
			panel.removeAll();
			GameTool.initCache();
			GameTool.initCacheList();
			GameTool.initRandomCard(Cache.level);
			panel.initCardLoc();
			panel.initMoveActionListener();
			panel.pushCards();
			return;
		}
		if ("退出".equals(e.getActionCommand())) {
			System.exit(0);
			return;
		}
		if ("关于".equals(e.getActionCommand())) {
			JOptionPane.showMessageDialog(this, "名称：蜘蛛纸牌\n版本：1.0\n作者：Mic.Wing\n时间：2010.08.04", "关于蜘蛛纸牌", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
	}
	
}
