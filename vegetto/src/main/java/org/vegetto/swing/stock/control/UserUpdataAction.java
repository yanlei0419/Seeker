package org.vegetto.swing.stock.control;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.vegetto.swing.stock.po.Users;
import org.vegetto.swing.stock.view.UserUpdata;

public class UserUpdataAction implements ActionListener {
	UserUpdata uu;

	public UserUpdataAction(UserUpdata uu) {
		this.uu = uu;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == uu.UserUpdatabtnReset) {// 重置按钮

			uu.UserUpdataPassWord1.setText("");
			uu.UserUpdataPassWord2.setText("");
			uu.UserUpdataPassWord3.setText("");

		}
		if (e.getSource() == uu.UserUpdatabtnEnter) {// 注册按钮
			// 查询出来的结果
			Users u=new Users();
			u.setPassword(uu.UserName);
			u.setUsername(uu.UserUpdataPassWord2.getText().trim());
			// System.out.println(uu.UserName);
			try {
				if (true) {
					// 空判断
					if (!uu.UserUpdataPassWord1.getText().equals("") && !uu.UserUpdataPassWord2.getText().equals("") && !uu.UserUpdataPassWord3.getText().equals("")) {
						// 判断两次密码是否一致
						if (uu.UserUpdataPassWord2.getText().trim().equalsIgnoreCase(uu.UserUpdataPassWord3.getText().trim())) {
							Users u1=new Users();
							u1.setPassword(uu.UserName);
							u1.setUsername(uu.UserUpdataPassWord2.getText().trim());

							if (1 == 1) {
								JOptionPane.showMessageDialog(uu, "用户 :  " + uu.UserName + "  密码修改成功");
								uu.UserUpdataPassWord1.setText("");
								uu.UserUpdataPassWord2.setText("");
								uu.UserUpdataPassWord3.setText("");

								/*
								 * uu.dispose(); new Login().setVisible(true);
								 */
							} else {
								JOptionPane.showMessageDialog(uu, "用户 :  " + uu.UserName + "  密码修改失败");
							}
						} else {
							JOptionPane.showMessageDialog(uu, "两次密码不一致");
						}
					} else {
						JOptionPane.showMessageDialog(uu, "用户名,密码,权限不能为空");
					}
				} else {
					uu.textmsg.setText("旧密码不正确");
				}
			} catch (HeadlessException e1) {

				e1.printStackTrace();
			}

		}
	}
}