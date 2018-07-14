package org.seeker.swing.stock.control;

import org.seeker.swing.stock.util.Sys;
import org.seeker.swing.stock.util.TableTools;
import org.seeker.swing.stock.view.SelectShow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;


public class SelectShowAction implements ActionListener {
	SelectShow ss;

	public SelectShowAction(SelectShow ss) {
		this.ss = ss;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ss.SelectShowBtnShow) {// x显示全部
			ss.SelectShowStart.setSelectedIndex(0);
			ss.SelectShowtId.setSelectedIndex(0);
			ss.SelectStype.setSelectedIndex(0);

			// 查询语句

			TableTools st = new TableTools(Sys.l);
			ss.SelectShowdtm.setDataVector(st.toData(), st.toTitle());
			st.toTable(ss.SelectShowtable);

		} else if (e.getSource() == ss.SelectShowtId) {
			String str = "";
			if (!ss.SelectShowtId.getSelectedItem().toString()
					.equals("----请选择----")) {
				if (ss.SelectShowtId.getSelectedItem().toString()
						.equals("缺货状态")) {

					str = "and product.pnum<product.plow and product.pnum>0";

				} else if (ss.SelectShowtId.getSelectedItem().toString()
						.equals("正常状态")) {

					str = " and product.pnum>=product.plow and product.pnum<=product.phigh";

				} else if (ss.SelectShowtId.getSelectedItem().toString()
						.equals("溢出状态")) {

					str = " and product.pnum>product.phigh ";

				} else if (ss.SelectShowtId.getSelectedItem().toString()
						.equals("存货已置空")) {

					str = "and product.pnum=0";

				}
			}
			TableTools st = new TableTools(Sys.l);
			ss.SelectShowdtm.setDataVector(st.toData(), st.toTitle());
			st.toTable(ss.SelectShowtable);

		} else if (e.getSource() == ss.SelectShowStart) { // 名称

			TableTools st = new TableTools(Sys.l);
			ss.SelectShowdtm.setDataVector(st.toData(), st.toTitle());
			st.toTable(ss.SelectShowtable);

		} else if (e.getSource() == ss.SelectStype) {// 类型

			TableTools st = new TableTools(Sys.l);
			ss.SelectShowdtm.setDataVector(st.toData(), st.toTitle());
			//自动分配列宽
			st.toTable(ss.SelectShowtable);

		} else if (e.getSource() == ss.SelectBtn) {
			if (!ss.SelectStype.getSelectedItem().toString()
					.equals("----请选择----")
					&& !ss.SelectShowtId.getSelectedItem().toString()
							.equals("----请选择----")) {
				
				String str = "";
				if (ss.SelectShowtId.getSelectedItem().toString()
						.equals("缺货状态")) {

					str = "and product.pnum<product.plow and product.pnum>0";

				} else if (ss.SelectShowtId.getSelectedItem().toString()
						.equals("正常状态")) {

					str = " and product.pnum>=product.plow and product.pnum<=product.phigh";

				} else if (ss.SelectShowtId.getSelectedItem().toString()
						.equals("溢出状态")) {

					str = " and product.pnum>product.phigh ";

				} else if (ss.SelectShowtId.getSelectedItem().toString()
						.equals("存货为0")) {

					str = "and product.pnum=0";

				}
				//接受结果集
				
				
				TableTools st = new TableTools(Sys.l);
				ss.SelectShowdtm.setDataVector(st.toData(), st.toTitle());
				st.toTable(ss.SelectShowtable);//自动分配列宽

			} else {
				JOptionPane.showMessageDialog(ss, "请选择商品类型 , 或者商品状态");
			}

		}
		
	}
}
