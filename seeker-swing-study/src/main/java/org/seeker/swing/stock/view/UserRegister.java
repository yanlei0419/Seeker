package org.seeker.swing.stock.view;

import org.seeker.swing.stock.control.UserRFocus;
import org.seeker.swing.stock.control.UserRegisterAction;

import javax.swing.*;
import java.awt.*;


@SuppressWarnings("serial")
public class UserRegister extends JFrame {
	// 声明
	public JButton UserbtnEnter, UserbtnReset;
	public JLabel text, textmsg;
	public JTextField UserName;
	public JComboBox UserType;
	public JPasswordField UserPassWord1, UserPassWord2;

	public UserRegister() {

	}

	public JPanel RegisterMenu() {
		// 上面
		JPanel UserMain = new JPanel();
		GridLayout grid = new GridLayout(8, 1);
		UserMain.setLayout(grid);
		UserMain.setOpaque(true);
		this.getContentPane();
		Font font = new Font("微软雅黑", Font.BOLD, 16);
		Font font1 = new Font("微软雅黑", Font.BOLD, 14);

		JPanel jp1 = new JPanel();
		jp1.setOpaque(true);
		text = new JLabel("新 用 户 注 册");
		text.setFont(new Font("微软雅黑", Font.BOLD, 30));
		jp1.add(text);
		UserMain.add(jp1);

		JPanel one1 = new JPanel();
		text = new JLabel();
		one1.add(text);

		JPanel one = new JPanel();

		text = new JLabel(" 用 户 名 :");
		UserName = new JTextField(12);
		one.setOpaque(true);
		one.setLayout(null);
		text.setFont(font);
		text.setBounds(250, 2, 100, 20);
		UserName.setBounds(350, 2, 150, 20);

		one.add(text);
		one.add(UserName);

		JPanel two = new JPanel();
		text = new JLabel("  密    码 :");
		UserPassWord1 = new JPasswordField(12);
		two.setOpaque(true);
		two.setLayout(null);
		text.setFont(font);
		text.setBounds(250, 2, 100, 20);
		UserPassWord1.setBounds(350, 2, 150, 20);

		two.add(text);
		two.add(UserPassWord1);
		
		JPanel three = new JPanel();
		three.setOpaque(true);
		text = new JLabel("确认密码 :");
		UserPassWord2 = new JPasswordField(12);

		three.setLayout(null);
		text.setFont(font);
		text.setBounds(250, 2, 100, 20);
		UserPassWord2.setBounds(350, 2, 150, 20);

		three.add(text);
		three.add(UserPassWord2);

		JPanel four = new JPanel();
		four.setOpaque(true);
		text = new JLabel("用户权限 :");
		String[] array = { "普通用户", "管理员", "超级管理" };
		UserType = new JComboBox(array);

		four.setLayout(null);
		text.setFont(font);
		text.setBounds(250, 2, 100, 20);
		UserType.setBounds(350, 2, 150, 20);

		four.add(text);
		four.add(UserType);

		JPanel five = new JPanel();
		five.setOpaque(true);
		textmsg = new JLabel();
		textmsg.setFont(new Font("微软雅黑", Font.BOLD, 14));
		five.add(textmsg);

		UserMain.add(one1);
		UserMain.add(one);
		UserMain.add(two);
		UserMain.add(three);
		UserMain.add(four);
		UserMain.add(five);

		JPanel jp3 = new JPanel();
		jp3.setOpaque(true);//变成透明的
		UserbtnEnter = new JButton("注  册");
		UserbtnEnter.setEnabled(false);
		UserbtnEnter.setFont(font1);
		UserbtnReset = new JButton("重  置");
		UserbtnReset.setFont(font1);
		jp3.add(UserbtnEnter);
		jp3.add(UserbtnReset);
		UserMain.add(jp3, BorderLayout.SOUTH);

		UserRegisterAction ra = new UserRegisterAction(this);
		UserbtnEnter.addActionListener(ra);
		UserbtnReset.addActionListener(ra);
		UserRFocus urf = new UserRFocus(this);
		UserName.addFocusListener(urf);
		UserPassWord1.addFocusListener(urf);
		UserPassWord2.addFocusListener(urf);
		UserType.addFocusListener(urf);
		return UserMain;
	}

}
