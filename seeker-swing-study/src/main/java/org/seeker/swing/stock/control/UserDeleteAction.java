package org.seeker.swing.stock.control;

import org.seeker.swing.stock.po.Users;
import org.seeker.swing.stock.util.Sys;
import org.seeker.swing.stock.util.TableTools;
import org.seeker.swing.stock.view.UserDelete;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserDeleteAction implements ActionListener {
	UserDelete ud;

	public UserDeleteAction(UserDelete ud) {
		this.ud = ud;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ud.UserDeleteBtnShow) {// 显示全部

		
			TableTools st = new TableTools(Sys.l);
			ud.UserDeletedtm.setDataVector(st.toData(), st.toTitle());
			System.out.println("查询成功");

		} else if (e.getSource() == ud.UserDeleteTextId) {
			Users u=new Users();
			u.setUsername(ud.UserDeleteTextId.getSelectedItem().toString());
			
			TableTools st = new TableTools(Sys.l);
			ud.UserDeletedtm.setDataVector(st.toData(), st.toTitle());
			
			
		} else if (e.getSource() == ud.UserDeleteBtnDelete) {
			int count = ud.UserDeletedtm.getRowCount();

			for (int i = 0; i < count; i++) {
				String username = (String) ud.UserDeletedtm.getValueAt(i, 1);
				String password = (String) ud.UserDeletedtm.getValueAt(i, 2);
				String stype = (String) ud.UserDeletedtm.getValueAt(i, 3);
				Users u=new Users();
				u.setPassword(password);
				u.setUsername(username);
				u.setUsertype(Integer.parseInt(stype));
				if (1 == 1) {
					JOptionPane.showMessageDialog(ud, "["+username + "] 修改成功");

				} else {
					JOptionPane.showMessageDialog(ud, "["+username + "] 修改失败");
				}

			}
			// 更新数据空中的数据
			TableTools st = new TableTools(Sys.l);
			ud.UserDeletedtm.setDataVector(st.toData(), st.toTitle());
		} else if (e.getSource() == ud.delete) {

			int NumRow = ud.UserDeletetable.getSelectedRow();
			if (NumRow >= 0) {
				if (JOptionPane.showConfirmDialog(ud, "是否删除所选考试记录，一旦删除无法恢复！",
						"提示", JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
					String uid = (String) ud.UserDeletedtm
							.getValueAt(NumRow, 0);
					Users u=new Users();
					u.setUserid(Integer.parseInt(uid));
					if (1 == 1) {
						JOptionPane.showMessageDialog(ud, "\t删除成功!!!");

					} else {
						JOptionPane.showMessageDialog(ud, "\t删除失败!!!");
					}
				}
				int rowNum = ud.UserDeletetable.getSelectedRow();
				ud.UserDeletedtm.removeRow(rowNum);

			}

		} else if (e.getSource() == ud.update) {
			// 获取点击行数
			int NumRow = ud.UserDeletetable.getSelectedRow();
			// 获取这一行的数据
			String username = (String) ud.UserDeletedtm.getValueAt(NumRow, 1);
			String password = (String) ud.UserDeletedtm.getValueAt(NumRow, 2);
//			String stype = (String) ud.UserDeletedtm.getValueAt(NumRow, 3);
			Users u=new Users();
			u.setPassword(password);
			u.setUsername(username);
//			u.setUsertype(Integer.parseInt(stype));
			if (1 == 1) {
				JOptionPane.showMessageDialog(ud, "["+username + "] 修改成功");

			} else {
				JOptionPane.showMessageDialog(ud, "["+username + "] 修改失败");
			}
			// 更新数据空中的数据
			TableTools st = new TableTools(Sys.l);
			ud.UserDeletedtm.setDataVector(st.toData(), st.toTitle());

		}
	}
}
