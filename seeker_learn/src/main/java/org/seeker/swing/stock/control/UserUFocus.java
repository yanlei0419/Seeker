package org.vegetto.swing.stock.control;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.vegetto.swing.stock.po.Users;
import org.vegetto.swing.stock.view.UserUpdata;

public class UserUFocus implements FocusListener {
	

	UserUpdata uu;

	public UserUFocus(UserUpdata uu) {
		this.uu = uu;
	}

	@Override
	public void focusGained(FocusEvent e) {// 获得焦点
	

	}

	@SuppressWarnings({ "deprecation", "static-access" })
	@Override
	public void focusLost(FocusEvent e) {// 失去焦点

		if (e.getSource() == uu.UserUpdataPassWord1) {//判断UserUpdataPassWord1是否与查询的结果一致
			Users u=new Users();
			u.setPassword(uu.UserName);
			u.setUsername(uu.UserUpdataPassWord1.getText().trim());
						
				if (true) {
					// 空判断
					uu.textmsg.setFont(new Font("", Font.BOLD, 20));
					uu.textmsg.setForeground(new Color(0).GREEN);
					uu.textmsg.setText("旧密码正确");					
				} else {
					uu.textmsg.setFont(new Font("", Font.BOLD, 20));
					uu.textmsg.setForeground(new Color(0).RED);
					uu.textmsg.setText("旧密码不正确");
				}
			

		} else if (e.getSource() == uu.UserUpdataPassWord2) {
			if (uu.UserUpdataPassWord2.getText().length() == 0
					|| uu.UserUpdataPassWord2.getText().length() < 6
					|| uu.UserUpdataPassWord2.getText().length() > 16) {
				uu.textmsg.setFont(new Font("", Font.BOLD, 20));
				uu.textmsg.setForeground(new Color(0).RED);
				uu.textmsg.setText("密码不能为空 , 且密码必须大于6位 , 小于16位");
			} else {
				uu.textmsg.setFont(new Font("", Font.BOLD, 20));
				uu.textmsg.setForeground(new Color(0).GREEN);
				uu.textmsg.setText("密码可用");
			}
		} else if (e.getSource() == uu.UserUpdataPassWord3) {
			if (!uu.UserUpdataPassWord3.getText().equals(uu.UserUpdataPassWord3.getText())) {
				uu.textmsg.setFont(new Font("", Font.BOLD, 20));
				uu.textmsg.setForeground(new Color(0).RED);
				uu.textmsg.setText("两次密码不一致");
			}
		}

	}
}
