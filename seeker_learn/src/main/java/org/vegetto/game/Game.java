package org.vegetto.game;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Game extends JFrame {
	// 组件声明
	private int ve = 4, x, y;

	JLabel lb;

	JLabel people;
	// 图片获取
	ImageIcon up = new ImageIcon(this.getClass()
			.getResource("/images/up.gif"));
	ImageIcon up1 = new ImageIcon(this.getClass().getResource(
			"/images/up-1.gif"));
	ImageIcon up2 = new ImageIcon(this.getClass().getResource(
			"/images/up-2.gif"));
	ImageIcon down = new ImageIcon(this.getClass().getResource(
			"/images/down.gif"));
	ImageIcon down1 = new ImageIcon(this.getClass().getResource(
			"/images/down-1.gif"));
	ImageIcon down2 = new ImageIcon(this.getClass().getResource(
			"/images/down-2.gif"));
	ImageIcon left = new ImageIcon(this.getClass().getResource(
			"/images/left.gif"));
	ImageIcon left1 = new ImageIcon(this.getClass().getResource(
			"/images/left-1.gif"));
	ImageIcon left2 = new ImageIcon(this.getClass().getResource(
			"/images/left-2.gif"));
	ImageIcon right = new ImageIcon(this.getClass().getResource(
			"/images/right.gif"));
	ImageIcon right1 = new ImageIcon(this.getClass().getResource(
			"/images/right-1.gif"));
	ImageIcon right2 = new ImageIcon(this.getClass().getResource(
			"/images/right-2.gif"));
	ImageIcon map = new ImageIcon(this.getClass()
			.getResource("/images/map.jpg"));
	ImageIcon imglogo = new ImageIcon(this.getClass().getResource("/images/image01.jpg"));
	Image logo=imglogo.getImage();

	// 构造方法
	public Game() {
		// 窗体属性
		super("小游戏");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 是程序正常关闭
		this.setSize(640, 460);// 窗体的大小
		this.setLocationRelativeTo(null); // 屏幕居中
		this.setResizable(false);// 视窗体不能更改大小	
		this.setIconImage(logo);//添加logo

		// 组件实例化
		JPanel jp = new JPanel();
		this.setContentPane(jp);
		jp.setLayout(null);
		// 人的
		people = new JLabel(down1);
		people.setBounds(227, 100, 32, 32);
		jp.add(people);

		// 地图
		lb = new JLabel(map);
		jp.add(lb);
		lb.setBounds(0, 0, 640, 460);
		// 事件绑定,绑定的类的对象
		MyAction ma = new MyAction();
		this.addKeyListener(ma);
	}

	/**
	 * 内部类
	 * */
	class MyAction implements KeyListener {

		
		@Override
		public void keyPressed(KeyEvent e) {// 按下
			x = people.getX();
			y = people.getY();

			// 向上行动
			if (e.getKeyCode() == KeyEvent.VK_W
					|| e.getKeyCode() == KeyEvent.VK_UP) {
				people.setIcon(up1);
				people.setIcon(up);
				if (y > 0) {
					if(y==180&&x>=450){
						people.setLocation(x, 180);
					}else{
					people.setLocation(x, y - ve);
					System.out.println("(" + x + "," + (y - ve) + ")");}
				}
			}
			// 向下移动
			if (e.getKeyCode() == KeyEvent.VK_S
					|| e.getKeyCode() == KeyEvent.VK_DOWN) {
				people.setIcon(down1);
				people.setIcon(down);
				if (y < 400) {
					people.setLocation(x, y + ve);
					System.out.println("(" + x + "," + (y+ve)+ ")");
				}
			}
			// 向左移动
			if (e.getKeyCode() == KeyEvent.VK_A
					|| e.getKeyCode() == KeyEvent.VK_LEFT) {
				people.setIcon(left1);
				people.setIcon(left);
				if(y>125&&x>100&&x<0){
					people.setLocation(x , y);
					
				}else if (x > 0) {
					people.setLocation(x-ve , y);
				}
				
				System.out.println("(" + (x-ve)+ "," + y + ")");
			}
			// 向右移动
			if (e.getKeyCode() == KeyEvent.VK_D
					|| e.getKeyCode() == KeyEvent.VK_RIGHT) {
				people.setIcon(right1);
				people.setIcon(right);
				 if (x < 600) {	
					 if(x>=450&&y<180){
						 people.setLocation(450, y);
					 }else{
					people.setLocation(x + ve, y);
					System.out.println("(" + (x + ve) + "," + y + ")");}
				}
			}

			/*
			 * // 左上移动 if (e.getKeyCode() == KeyEvent.VK_W && e.getKeyCode() ==
			 * KeyEvent.VK_A) { people.setIcon(hou);
			 * people.setBounds(people.getX() - 3, people.getY() + 3, 32, 32); }
			 * // 右上移动 if (e.getKeyCode() == KeyEvent.VK_W && e.getKeyCode() ==
			 * KeyEvent.VK_D) { people.setIcon(qian);
			 * people.setBounds(people.getX() + 3, people.getY() + 3, 32, 32); }
			 * // 左下移动 if (e.getKeyCode() == KeyEvent.VK_S && e.getKeyCode() ==
			 * KeyEvent.VK_A) { people.setIcon(qian);
			 * people.setBounds(people.getX() - 3, people.getY() - 3, 32, 32); }
			 * // 右下移动 if (e.getKeyCode() == KeyEvent.VK_S && e.getKeyCode() ==
			 * KeyEvent.VK_D) { people.setIcon(qian);
			 * people.setBounds(people.getX() + 3, people.getY() - 3, 32, 32); }
			 */
		}

		@Override
		public void keyReleased(KeyEvent e) {// 松开键盘
			if (e.getKeyCode() == KeyEvent.VK_W
					|| e.getKeyCode() == KeyEvent.VK_UP) {
				people.setIcon(up2);
			}
			if (e.getKeyCode() == KeyEvent.VK_S
					|| e.getKeyCode() == KeyEvent.VK_DOWN) {
				people.setIcon(down2);

			}
			if (e.getKeyCode() == KeyEvent.VK_A
					|| e.getKeyCode() == KeyEvent.VK_LEFT) {
				people.setIcon(left2);
			}
			if (e.getKeyCode() == KeyEvent.VK_D
					|| e.getKeyCode() == KeyEvent.VK_RIGHT) {
				people.setIcon(right2);
			}
		}
		@Override
		public void keyTyped(KeyEvent e) {// 敲击键盘
		/*	if (e.getKeyCode() == KeyEvent.VK_W
					|| e.getKeyCode() == KeyEvent.VK_UP) {
				people.setIcon(up);
			}
			if (e.getKeyCode() == KeyEvent.VK_S
					|| e.getKeyCode() == KeyEvent.VK_DOWN) {
				people.setIcon(down);

			}
			if (e.getKeyCode() == KeyEvent.VK_A
					|| e.getKeyCode() == KeyEvent.VK_LEFT) {
				people.setIcon(left);
			}
			if (e.getKeyCode() == KeyEvent.VK_D
					|| e.getKeyCode() == KeyEvent.VK_RIGHT) {
				people.setIcon(right);
			}*/

		}


	}
}
