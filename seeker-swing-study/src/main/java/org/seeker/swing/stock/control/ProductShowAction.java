package org.seeker.swing.stock.control;

import org.seeker.swing.stock.util.Sys;
import org.seeker.swing.stock.util.TableTools;
import org.seeker.swing.stock.view.ProductShow;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;


public class ProductShowAction implements ActionListener {
	ProductShow ps;

	public ProductShowAction(ProductShow ps) {
		this.ps = ps;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ps.ProductShowBtnShow) {// 显示全部
			ps.ProductShowCname.setSelectedIndex(0);
			ps.ProductShowpPname.setSelectedIndex(0);
			ps.ProductShowStype.setSelectedIndex(0);

			TableTools st = new TableTools(Sys.l);
			ps.ProductShowdtm.setDataVector(st.toData(), st.toTitle());

		} else if (e.getSource() == ps.ProductShowpPname) {// 全部修改
			if (!ps.ProductShowpPname.getSelectedItem().toString()
					.equals("----请选择----")) {
				TableTools st = new TableTools(Sys.l);
				ps.ProductShowdtm.setDataVector(st.toData(), st.toTitle());
			}/*else{
				JOptionPane.showMessageDialog(ps, "请选择[商品名称]");
			}*/
		} else if (e.getSource() == ps.updateall) {
			int count = ps.ProductShowdtm.getRowCount();
			for (int i = 0; i < count; i++) {
				String pid = (String) ps.ProductShowdtm.getValueAt(i, 0);
				String pname = (String) ps.ProductShowdtm.getValueAt(i, 1);
				String pprice = (String) ps.ProductShowdtm.getValueAt(i, 2);
				String plow = (String) ps.ProductShowdtm.getValueAt(i, 3);
				String phigh = (String) ps.ProductShowdtm.getValueAt(i, 4);
				String cname = (String) ps.ProductShowdtm.getValueAt(i, 5);
				String name = (String) ps.ProductShowdtm.getValueAt(i, 7);

				try {
					if (true) {
						if (1 == 1) {
							JOptionPane.showMessageDialog(ps, "["+pname + "]修改成功");

						} else {
							JOptionPane.showMessageDialog(ps, "["+pname + "]修改失败");
						}
					} 
//					else if (!rsstype.next()) {
//						JOptionPane.showMessageDialog(ps, pname
//								+ "   要修改的 [ 商品类型 ] 不存在");
//					} else if (!rscname.next()) {
//						JOptionPane.showMessageDialog(ps, pname
//								+ "   要修改的 [ 供应商不 ] 存在");
//					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			TableTools st = new TableTools(Sys.l);
			ps.ProductShowdtm.setDataVector(st.toData(), st.toTitle());
		} else if (e.getSource() == ps.delete) {
			int row = ps.ProductShowtable.getSelectedRow();
			if (row >= 0) {
				if (JOptionPane.showConfirmDialog(ps, "是否删除所选考试记录，一旦删除无法恢复！",
						"提示", JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
					String pid = (String) ps.ProductShowdtm.getValueAt(row, 0);
//					int flag = sel.Delete(pid);
					if (1 == 1) {
						JOptionPane.showMessageDialog(ps, "\t删除成功!!!");
					} else {
						JOptionPane.showMessageDialog(ps, "\t删除失败!!!");
					}
				} else {
					JOptionPane.showMessageDialog(ps, "\t删除失败!!!,请重新填写");
				}
			} else {
				JOptionPane.showMessageDialog(ps, "获取行数失败 , 请选择");
			}
			ps.ProductShowdtm.removeRow(row);
		} else if (e.getSource() == ps.update) {
			int row = ps.ProductShowtable.getSelectedRow();
			if (row >= 0) {

				String pid = (String) ps.ProductShowdtm.getValueAt(row, 0);
				String pname = (String) ps.ProductShowdtm.getValueAt(row, 1);
				String pprice = (String) ps.ProductShowdtm.getValueAt(row, 2);
				String plow = (String) ps.ProductShowdtm.getValueAt(row, 3);
				String phigh = (String) ps.ProductShowdtm.getValueAt(row, 4);
				String cname = (String) ps.ProductShowdtm.getValueAt(row, 5);
				String name = (String) ps.ProductShowdtm.getValueAt(row, 7);

				try {
					if (true) {
						if (1 == 1) {
							JOptionPane.showMessageDialog(ps, "["+pname + "] 修改成功");
						} else {
							JOptionPane.showMessageDialog(ps, "["+pname + "] 修改失败");
						}
					} 
//					else if (!rsstype.next()) {
//						JOptionPane.showMessageDialog(ps, pname
//								+ "   要修改的 [ 商品类型 ] 不存在");
//					} else if (!rscname.next()) {
//						JOptionPane.showMessageDialog(ps, pname
//								+ "   要修改的 [ 供应商 ] 不存在");
//					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(ps, "获取行数失败 , 请选择");
			}

			TableTools st = new TableTools(Sys.l);
			ps.ProductShowdtm.setDataVector(st.toData(), st.toTitle());
		} else if (e.getSource() == ps.ProductShowBtnSelect) {// 综合查询

			TableTools st = new TableTools(Sys.l);
			ps.ProductShowdtm.setDataVector(st.toData(), st.toTitle());

		} else if (e.getSource() == ps.ProductShowCname) {
			if (!ps.ProductShowCname.getSelectedItem().toString()
					.equals("----请选择----")) {
				TableTools st = new TableTools(Sys.l);
				ps.ProductShowdtm.setDataVector(st.toData(), st.toTitle());
			} /*else {
				JOptionPane.showMessageDialog(ps, "请选择[供应商]");
			}*/

		} else if (e.getSource() == ps.ProductShowStype) {
			if (!ps.ProductShowStype.getSelectedItem().toString()
					.equals("----请选择----")) {

				TableTools st = new TableTools(Sys.l);
				ps.ProductShowdtm.setDataVector(st.toData(), st.toTitle());
			} /*else {
				JOptionPane.showMessageDialog(ps, "请选择[类型]");
			}
*/
		}

	}
}
