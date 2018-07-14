package org.seeker.swing.stock.control;

import org.seeker.swing.stock.util.Sys;
import org.seeker.swing.stock.util.TableTools;
import org.seeker.swing.stock.view.StoreHouseShow;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;


public class StoreHouseShowAction implements ActionListener {
	StoreHouseShow shs;

	public StoreHouseShowAction(StoreHouseShow shs) {
		this.shs = shs;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == shs.StoreHouseBtnShow) {// 显示全部
			shs.StoreHouseSname.setSelectedIndex(0);
			TableTools st = new TableTools(Sys.l);
			shs.StoreHousedtm.setDataVector(st.toData(), st.toTitle());

		} else if (e.getSource() == shs.updateall) {// 修改
			int count = shs.StoreHousedtm.getRowCount();// 获得表格的行数
			for (int i = 0; i < count; i++) {
				String sid = (String) shs.StoreHousedtm.getValueAt(i, 0);
				String sname = (String) shs.StoreHousedtm.getValueAt(i, 1);
				String stype = (String) shs.StoreHousedtm.getValueAt(i, 2);
				try {
					if (true) {
						if (1 == 1) {
							JOptionPane.showMessageDialog(shs, "          [" + sname + "]   修改成功");
						} else {
							JOptionPane.showMessageDialog(shs, "          [" + sname + "]   修改失败");
						}
					} else {
						JOptionPane.showMessageDialog(shs, "          [" + sname + "]要修改的 [" + stype + "]  类型不存在");
					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} else if (e.getSource() == shs.delete) {
			int row = shs.StoreHousetable.getSelectedRow();
			if (row >= 0) {
				if (JOptionPane.showConfirmDialog(shs, "是否删除所选考试记录，一旦删除无法恢复！", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {

					String sid = (String) shs.StoreHousedtm.getValueAt(row, 0);

					if (1 == 1) {
						JOptionPane.showMessageDialog(shs, "删除成功");
					} else {
						JOptionPane.showMessageDialog(shs, "删除失败");
					}
				}
			}
			// 更新数据信息
			// 获取当前行数

			// 显示rownum 行
			shs.StoreHousedtm.removeRow(row);
		} else if (e.getSource() == shs.update) {
			// 获取点击行数
			int row = shs.StoreHousetable.getSelectedRow();

			if (row >= 0) {
				String sid = (String) shs.StoreHousedtm.getValueAt(row, 0);
				String sname = (String) shs.StoreHousedtm.getValueAt(row, 1);
				String stype = (String) shs.StoreHousedtm.getValueAt(row, 2);
				try {
					if (true) {
						if (1 == 1) {
							JOptionPane.showMessageDialog(shs, "          [" + sname + "]   修改成功");
						} else {
							JOptionPane.showMessageDialog(shs, "          [" + sname + "]   修改失败");
						}
					} else {
						JOptionPane.showMessageDialog(shs, "          [" + stype + "]   的商品类型不存在");
					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(shs, "请点中要修改的行数!!!!");
			}

		} else if (e.getSource() == shs.StoreHouseSname) {

			// 得道结果集
			if (!shs.StoreHouseSname.getSelectedItem().toString().equals("----请选择----")) {
				System.out.println(shs.StoreHouseSname.getSelectedItem().toString());

				TableTools st = new TableTools(Sys.l);
				shs.StoreHousedtm.setDataVector(st.toData(), st.toTitle());
			} /*
			 * else { JOptionPane.showMessageDialog(shs, "请选择 [供应商]"); }
			 */
		} else if (e.getSource() == shs.StoreHouseStype) {
			if (!shs.StoreHouseStype.getSelectedItem().toString().equals("----请选择----")) {
				TableTools st = new TableTools(Sys.l);
				shs.StoreHousedtm.setDataVector(st.toData(), st.toTitle());
			} /*
			 * else { JOptionPane.showMessageDialog(shs, "请选择 [类型]"); }
			 */
		}
	}

}
