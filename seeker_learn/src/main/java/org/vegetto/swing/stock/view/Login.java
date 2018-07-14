package org.vegetto.swing.stock.view;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.vegetto.swing.stock.control.LoginAction;
import org.vegetto.swing.stock.util.BackImg;
//登陆界面设置
@SuppressWarnings("serial")
public class Login extends JFrame  {
	// 组件声明
	public JLabel jlone, jltwo, jlthree;
	public JButton jbone, jbtwo;
	public JTextField username;
	public JPasswordField password;
	
	MainMenu mm;
	public Login() {
		Font font = new Font("微软雅黑", Font.BOLD, 13);
		// 窗体属性
		this.setTitle("XXXX登陆界面");
		this.setDefaultCloseOperation(Login.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(340, 250);
		this.setLocationRelativeTo(null);
		// 组件实例化
		jlone = new JLabel("XXXX登陆界面");
		jltwo = new JLabel("账号:");
		jlthree = new JLabel("密码:");
		jbone = new JButton("登陆");
		jbone.setFont(font);
		jbtwo = new JButton("重置");
		username = new JTextField("admin");
		password = new JPasswordField("123");
		jlone.setFont(font);
		jltwo.setFont(font);
		jlthree.setFont(font);
		jbtwo.setFont(font);
		username.setFont(font);
		password.setFont(font);
		// logo设置
		ImageIcon img = new ImageIcon(this.getClass().getResource(
				"/images/logo.gif"));
		Image logo = img.getImage();
		this.setIconImage(logo);
		// 增加到窗体
		JPanel jp = new BackImg().toShow("/images/dl1.jpg");
		jp.setLayout(null);
		this.setContentPane(jp);
		jp.add(jlone);
		jp.add(jltwo);
		jp.add(jlthree);
		jp.add(jbone);
		jp.add(jbtwo);
		jp.add(username);
		jp.add(password);
		// 设置组件的位置和大小
		jlone.setBounds(120, 20, 200, 20);
		jltwo.setBounds(60, 60, 100, 20);
		username.setBounds(120, 60, 140, 25);
		jlthree.setBounds(60, 100, 100, 20);
		password.setBounds(120, 100, 140, 25);
		jbone.setBounds(60, 150, 100, 25);
		jbtwo.setBounds(180, 150, 100, 25);
		// 添加监听事件
		LoginAction la = new LoginAction(this);
		jbtwo.addActionListener(la);
		jbone.addActionListener(la);
		username.addActionListener(la);
		password.addActionListener(la);
	}
	public static void main(String[] args) {
		new Login().setVisible(true);

	}
}
