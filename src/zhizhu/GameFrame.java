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
		this.setTitle("֩��ֽ��");
		
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
		JMenu game=new JMenu("��Ϸ");
		JMenu help=new JMenu("����");
		JMenuItem item;
		game.add(item=new JMenuItem("����"));item.addActionListener(this);
		game.addSeparator();
		ButtonGroup bg=new ButtonGroup();
		game.add(item=new JCheckBoxMenuItem("����"));bg.add(item);item.addActionListener(this);
		game.add(item=new JCheckBoxMenuItem("�м�"));bg.add(item);item.addActionListener(this);
		game.add(item=new JCheckBoxMenuItem("�߼�"));bg.add(item);item.addActionListener(this);
		game.addSeparator();
		game.add(item=new JMenuItem("�˳�"));item.addActionListener(this);
	
		help.add(item=new JMenuItem("����"));item.addActionListener(this);
		
		bar.add(game);
		bar.add(help);
		
		this.setJMenuBar(bar);
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
	
	/**
	 * ���ý����С
	 */
	private void initFrameSize() {
		this.setSize(840, 700);
	}
	
	public void actionPerformed(ActionEvent e) {
		if ("����".equals(e.getActionCommand())) {
			panel.removeAll();
			GameTool.initCache();
			GameTool.initCacheList();
			GameTool.initRandomCard(Cache.level);
			panel.initCardLoc();
			panel.initMoveActionListener();
			panel.pushCards();
			return;
		}
		if ("����".equals(e.getActionCommand())) {
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
		if ("�м�".equals(e.getActionCommand())) {
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
		if ("�߼�".equals(e.getActionCommand())) {
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
		if ("�˳�".equals(e.getActionCommand())) {
			System.exit(0);
			return;
		}
		if ("����".equals(e.getActionCommand())) {
			JOptionPane.showMessageDialog(this, "���ƣ�֩��ֽ��\n�汾��1.0\n���ߣ�Mic.Wing\nʱ�䣺2010.08.04", "����֩��ֽ��", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
	}
	
}
