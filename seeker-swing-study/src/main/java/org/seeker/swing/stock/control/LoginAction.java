package org.seeker.swing.stock.control;

import org.seeker.swing.stock.po.Users;
import org.seeker.swing.stock.view.Login;
import org.seeker.swing.stock.view.MainMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginAction implements ActionListener {
	Login lg;

	public LoginAction(Login lg) {
		this.lg = lg;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
//		UserSel sel = new UserSel();
		

		// 重置按钮
		if (e.getSource() == lg.jbtwo) {
			lg.username.setText("");
			lg.password.setText("");
//			ResultSet rs =sel.SelAll();
//			TableTools tt=new TableTools(rs);
//			Vector<Vector<String>> data =tt.toData();
		
		}
		else if (e.getSource() == lg.jbone || e.getSource() == lg.username
				|| e.getSource() == lg.password) {// 登陆按钮
			
			System.out.println("点击确定按钮");
			
		
			
			if (!lg.username.getText().trim().equals("")|| !lg.password.getText().equals("")) {
				String username =lg.username.getText().trim();
				String password=lg.password.getText().trim();
				Users u=new Users();
				u.setUsername(username);
				u.setPassword(password);
				
				System.out.println(lg.username.getText()+"           "+lg.password.getText());
				MainMenu mm = new MainMenu(lg.username.getText(), "1");
//				int flag = sel.Insert(u);
//				System.out.println(flag);
//				ResultSet rs = sel.Sel(u);
//				try {
//					if (rs.next()) {
//						String stype = rs.getString(1);
//						System.out.println(stype);
//						mm.setVisible(true);
//						
//						lg.dispose();
//						System.out.println("登录成功");
//						// lg.setVisible(false);
//
//						lg.username.setText("");
//						lg.password.setText("");
//						System.out.println("登录成功");
//					} else {
//						lg.username.setText("");
//						lg.password.setText("");
//						JOptionPane.showMessageDialog(lg, "登录失败");
//					}
//				} catch (SQLException e1) {
					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
			} else {
				JOptionPane.showMessageDialog(lg, "帐号密码不能为空");
			}
		}
//		sel.getDbc().closeConnection();
	}
}
