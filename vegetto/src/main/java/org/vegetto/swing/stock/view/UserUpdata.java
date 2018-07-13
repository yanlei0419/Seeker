package org.vegetto.swing.stock.view;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.vegetto.swing.stock.control.UserUFocus;
import org.vegetto.swing.stock.control.UserUpdataAction;

@SuppressWarnings("serial")
public class UserUpdata extends JFrame {
	// 声明
	
	// 声明
	public JButton UserUpdatabtnEnter, UserUpdatabtnReset;
	public JLabel text, textmsg;
	public JTextField UserUpdataName, UserUpdataType;
	public JPasswordField UserUpdataPassWord1, UserUpdataPassWord2,
			UserUpdataPassWord3;

	public String UserName;

	public UserUpdata(final String UserName) {
		this.UserName = UserName;
	}

	public JPanel UpdataMenu() {
		Font font = new Font("微软雅黑", Font.BOLD, 16);
		Font font1 = new Font("微软雅黑", Font.BOLD, 14);
		// 上面
		JPanel UserUpdataMain = new JPanel();
		UserUpdataMain.setFont(font);
		GridLayout grid = new GridLayout(8, 1);
		UserUpdataMain.setLayout(grid);
		this.getContentPane();

	

		JPanel jp1 = new JPanel();
		text = new JLabel("修 改 用 户 密 码");
		text.setFont(new Font("微软雅黑", Font.BOLD, 30));
		jp1.add(text);
		UserUpdataMain.add(jp1);

		JPanel one = new JPanel();
		text = new JLabel();
		one.add(text);

		// 网格布局
		JPanel two = new JPanel();
		text = new JLabel(" 旧 密 码 :");
		UserUpdataPassWord1 = new JPasswordField(12);

		two.setLayout(null);
		text.setFont(font);
		text.setBounds(250, 2, 100, 20);
		UserUpdataPassWord1.setBounds(350, 2, 150, 20);

		two.add(text);
		two.add(UserUpdataPassWord1);

		JPanel three = new JPanel();
		text = new JLabel(" 新 密 码 :");
		UserUpdataPassWord2 = new JPasswordField(12);
		three.add(text);
		three.add(UserUpdataPassWord2);

		three.setLayout(null);
		text.setFont(font);
		text.setBounds(250, 2, 100, 20);
		UserUpdataPassWord2.setBounds(350, 2, 150, 20);

		JPanel five = new JPanel();
		text = new JLabel("确认密码 :");
		UserUpdataPassWord3 = new JPasswordField(12);
		five.add(text);
		five.add(UserUpdataPassWord3);

		five.setLayout(null);
		text.setFont(font);
		text.setBounds(250, 2, 100, 20);
		UserUpdataPassWord3.setBounds(350, 2, 150, 20);
		
		JPanel ten = new JPanel();
		textmsg = new JLabel();
		ten.add(textmsg);

		UserUpdataMain.add(one);
		UserUpdataMain.add(two);
		UserUpdataMain.add(three);
		UserUpdataMain.add(five);
		UserUpdataMain.add(ten);
		
		JPanel jp3 = new JPanel();
		UserUpdatabtnEnter = new JButton("修  改");
		UserUpdatabtnEnter.setFont(font1);
		UserUpdatabtnReset = new JButton("重  置");
		UserUpdatabtnReset.setFont(font1);
		jp3.add(UserUpdatabtnEnter);
		jp3.add(UserUpdatabtnReset);
		UserUpdataMain.add(jp3);

		UserUpdataAction ra = new UserUpdataAction(this);
		UserUpdatabtnEnter.addActionListener(ra);
		UserUpdatabtnReset.addActionListener(ra);
		
		
		UserUFocus uf =new UserUFocus(this);
		UserUpdataPassWord1.addFocusListener(uf);
		UserUpdataPassWord2.addFocusListener(uf);
		UserUpdataPassWord3.addFocusListener(uf);

		

		return UserUpdataMain;
	}

}
