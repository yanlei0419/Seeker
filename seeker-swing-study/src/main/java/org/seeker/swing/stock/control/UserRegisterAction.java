package org.seeker.swing.stock.control;

import org.seeker.swing.stock.po.Users;
import org.seeker.swing.stock.view.UserRegister;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UserRegisterAction implements ActionListener {
	UserRegister re;

	public UserRegisterAction(UserRegister re) {
		this.re = re;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == re.UserbtnReset) {
			re.UserName.setText("");
			re.UserPassWord1.setText("");
			re.UserPassWord2.setText("");
			re.UserType.setSelectedIndex(0);
		}
		if (e.getSource() == re.UserbtnEnter) {
			// 非空判断
			if (!re.UserName.getText().equals("") && !re.UserPassWord1.getText().equals("") && !re.UserPassWord2.getText().equals("")) {

				String str = "";
				if (re.UserType.getSelectedItem().toString().equals("超级管理员")) {

					str = "4";

				} else if (re.UserType.getSelectedItem().toString().equals("管理员")) {

					str = "3";

				} else if (re.UserType.getSelectedItem().toString().equals("普通用户")) {

					str = "1";
				}
				Users u = new Users();
				u.setPassword(re.UserName.getText().trim());
				u.setUsername(re.UserPassWord1.getText().trim());

				if (1 == 1) {
					JOptionPane.showMessageDialog(re, "新用户  " + re.UserName.getText() + "  注册成功");
					re.UserName.setText("");
					re.UserPassWord1.setText("");
					re.UserPassWord2.setText("");
					re.UserType.setSelectedIndex(0);
					re.textmsg.setText("");
				} else {
					JOptionPane.showMessageDialog(re, "新用户  " + re.UserName.getText() + "  注册失败");
				}

			}

		}
	}
}
