package org.seeker.swing.stock.control;

import org.seeker.swing.stock.util.Sys;
import org.seeker.swing.stock.util.TableTools;
import org.seeker.swing.stock.view.StypeShow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;


public class StypeShowAction implements ActionListener {
	StypeShow ss;

	public StypeShowAction(StypeShow ss) {
		this.ss = ss;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ss.StypeBtnShow) {// 显示全部
			ss.StypeName.setSelectedIndex(0);
			TableTools st = new TableTools(Sys.l);
			ss.Stypedtm.setDataVector(st.toData(), st.toTitle());

		} else if (e.getSource() == ss.updateall) {// 修改
			int count = ss.Stypedtm.getRowCount();
			for (int i = 0; i < count; i++) {
				String id = (String) ss.Stypedtm.getValueAt(i, 0);
				String name = (String) ss.Stypedtm.getValueAt(i, 1);
				if (1 == 1) {
					JOptionPane.showMessageDialog(ss, "          " + name
							+ "   修改成功");
				} else {
					JOptionPane.showMessageDialog(ss, "          " + name
							+ "   修改失败");
				}
			}
		} else if (e.getSource() == ss.delete) {
			int row =ss.Stypetable.getSelectedRow();
			if (row >= 0) {
				if (JOptionPane.showConfirmDialog(ss, "是否删除所选考试记录，一旦删除无法恢复！",
						"提示", JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {

					String id = (String) ss.Stypedtm.getValueAt(row, 0);

					if (1 == 1) {
						JOptionPane.showMessageDialog(ss, "删除成功");
					} else {
						JOptionPane.showMessageDialog(ss, "删除失败");
					}
				}
			}
			// 显示rownum 行
			ss.Stypedtm.removeRow(row);
		} else if (e.getSource() == ss.update) {
			// 获取点击行数
			int row = ss.Stypetable.getSelectedRow();
			if (row >= 0) {
				String id = (String) ss.Stypedtm.getValueAt(row, 0);
				String name = (String) ss.Stypedtm.getValueAt(row, 1);

				if (1 == 1) {
					JOptionPane.showMessageDialog(ss, "          " + name
							+ "   修改成功");
				} else {
					JOptionPane.showMessageDialog(ss, "          " + name
							+ "   修改失败");
				}
			} else {
				JOptionPane.showMessageDialog(ss, "请点中要修改的行数!!!!");
			}

		} else if (e.getSource() == ss.StypeName) {

			// 得道结果集
			if (!ss.StypeName.getSelectedItem().toString()
					.equals("----请选择----")) {

				TableTools st = new TableTools(Sys.l);
				ss.Stypedtm.setDataVector(st.toData(), st.toTitle());
			}/*else{
				JOptionPane.showMessageDialog(ss, "请选择[类型]");
			}*/

		}
	}
}
