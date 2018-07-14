package org.seeker.swing.stock.control;

import org.seeker.swing.stock.po.Users;
import org.seeker.swing.stock.view.UserRegister;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class UserRFocus implements FocusListener {
	UserRegister re;

	public UserRFocus(UserRegister re) {
		this.re = re;
	}

	

	@SuppressWarnings({ "deprecation", "static-access" })
	@Override
	public void focusGained(FocusEvent e) {// 获得焦点
		if (e.getSource() == re.UserType) {
			if (!re.UserPassWord1.getText().equals("")
					&& re.UserPassWord1.getText().length() > 6
					&& re.UserPassWord1.getText().length() < 16) {

				re.textmsg.setFont(new Font("", Font.BOLD, 20));
				re.textmsg.setForeground(new Color(0).GREEN);
				re.textmsg.setText("可以注册");
				re.UserbtnEnter.setEnabled(true);
			} else {
				re.textmsg.setFont(new Font("", Font.BOLD, 20));
				re.textmsg.setForeground(new Color(0).RED);
				re.textmsg.setText("不可以注册,请检查后提交");
			}
		}

	}

	@SuppressWarnings({ "deprecation", "static-access" })
	@Override
	public void focusLost(FocusEvent e) {// 失去焦点

		if (e.getSource() == re.UserName) {
			Users u=new Users();
			u.setUsername(re.UserName.getText());
			if (false) {
				re.textmsg.setFont(new Font("", Font.BOLD, 20));
				re.textmsg.setForeground(new Color(0).RED);
				re.textmsg.setText("用户名已注册");
			} else if (re.UserName.getText().length() == 0) {
				re.textmsg.setFont(new Font("", Font.BOLD, 20));
				re.textmsg.setForeground(new Color(0).RED);
				re.textmsg.setText("用户名不能为空");
			} else if (re.UserName.getText().length() > 0) {
				re.textmsg.setFont(new Font("", Font.BOLD, 20));
				re.textmsg.setForeground(new Color(0).GREEN);
				re.textmsg.setText("用户名可用");
			}

		} else if (e.getSource() == re.UserPassWord1) {

			if (re.UserPassWord1.getText().length() == 0
					|| re.UserPassWord1.getText().length() < 6
					|| re.UserPassWord1.getText().length() > 16) {
				re.textmsg.setFont(new Font("", Font.BOLD, 20));
				re.textmsg.setForeground(new Color(0).RED);
				re.textmsg.setText("密码不能为空 , 且密码必须大于6位 , 小于16位");
			} else {
				re.textmsg.setFont(new Font("", Font.BOLD, 20));
				re.textmsg.setForeground(new Color(0).GREEN);
				re.textmsg.setText("密码可用");
			}
		} else if (e.getSource() == re.UserPassWord2) {

			if (!re.UserPassWord1.getText().equals(re.UserPassWord2.getText())) {
				re.textmsg.setFont(new Font("", Font.BOLD, 20));
				re.textmsg.setForeground(new Color(0).RED);
				re.textmsg.setText("两次密码不一致");
			}
		} 
	}
}
