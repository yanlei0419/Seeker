package org.seeker.swing.stock.control;

import org.seeker.swing.stock.util.Sys;
import org.seeker.swing.stock.util.TableTools;
import org.seeker.swing.stock.view.ClientShow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;


public class ClientShowAction implements ActionListener {
	ClientShow cs;

	public ClientShowAction(ClientShow cs) {
		this.cs = cs;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cs.ClientShowCId) {
			if (!cs.ClientShowCId.getSelectedItem().toString().equals("----请选择----")) {
				// 得道结果集
				TableTools st = new TableTools(Sys.l);

				cs.ClientShowdtm.setDataVector(st.toData(), st.toTitle());
			} /*
			 * else { if (cs.ClientShowCId.getSelectedItem().toString()
			 * .equals("----请选择----")) { if (e.getSource() ==
			 * cs.ClientShowBtnShow) {
			 * 
			 * cs.ClientShowCId.setSelectedIndex(0); } } else {
			 * JOptionPane.showMessageDialog(cs, "请选择[供应商]"); } }
			 */

		} else if (e.getSource() == cs.updateall) {

			int count = cs.ClientShowtable.getRowCount();

			for (int i = 0; i < count; i++) {
				String cid = (String) cs.ClientShowdtm.getValueAt(i, 0);
				String cname = (String) cs.ClientShowdtm.getValueAt(i, 1);
				String caddress = (String) cs.ClientShowdtm.getValueAt(i, 2);
				String contact = (String) cs.ClientShowdtm.getValueAt(i, 3);
				String phone = (String) cs.ClientShowdtm.getValueAt(i, 4);

				if (1 == 1) {
					JOptionPane.showMessageDialog(cs, "[" + cname + "] 修改成功");

				} else {
					JOptionPane.showMessageDialog(cs, "[" + cname + "] 修改失败");
				}

			}
			// 更新数据空中的数据
			TableTools st = new TableTools(Sys.l);
			cs.ClientShowdtm.setDataVector(st.toData(), st.toTitle());

		} else if (e.getSource() == cs.ClientShowBtnShow) {

			cs.ClientShowCId.setSelectedIndex(0);

			// 将表中的数据全部更新到数据库
			TableTools st = new TableTools(Sys.l);
			cs.ClientShowdtm.setDataVector(st.toData(), st.toTitle());

		} else if (e.getSource() == cs.delete) {// 删除
			// 获取当前选择行数
			int NumRow = cs.ClientShowtable.getSelectedRow();
			if (NumRow >= 0) {
				if (JOptionPane.showConfirmDialog(cs, "是否删除所选考试记录，一旦删除无法恢复！", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {

					String cid = (String) cs.ClientShowdtm.getValueAt(NumRow, 0);

					if (1 == 1) {
						JOptionPane.showMessageDialog(cs, "删除成功!!!");
					} else {
						JOptionPane.showMessageDialog(cs, "删除失败!!!");
					}
					// 更新数据信息

				}
				cs.ClientShowdtm.removeRow(NumRow);
			} else {
				JOptionPane.showMessageDialog(cs, "获取行数失败 , 请选择");
			}
		} else if (e.getSource() == cs.update) {// 修改
			// 获取当前选择行数
			int NumRow = cs.ClientShowtable.getSelectedRow();
			String cid = (String) cs.ClientShowdtm.getValueAt(NumRow, 0);
			String cname = (String) cs.ClientShowdtm.getValueAt(NumRow, 1);
			String caddress = (String) cs.ClientShowdtm.getValueAt(NumRow, 2);
			String contact = (String) cs.ClientShowdtm.getValueAt(NumRow, 3);
			String phone = (String) cs.ClientShowdtm.getValueAt(NumRow, 4);


			if (1 == 1) {
				JOptionPane.showMessageDialog(cs, "[" + cname + "] 修改成功");

			} else {
				JOptionPane.showMessageDialog(cs, "[" + cname + "] 修改失败");
			}
			TableTools st = new TableTools(Sys.l);
			cs.ClientShowdtm.setDataVector(st.toData(), st.toTitle());
		}
	}
}