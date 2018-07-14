package org.seeker.swing.stock.control;

import org.seeker.swing.stock.util.Sys;
import org.seeker.swing.stock.util.TableTools;
import org.seeker.swing.stock.view.StoreInShow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;


public class StoreInShowAction implements ActionListener {
	StoreInShow sis;

	public StoreInShowAction(StoreInShow sis) {
		this.sis = sis;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == sis.StoreInShowBtnShow) {
			TableTools st = new TableTools(Sys.l);
			st.toTable(sis.StoreInShowtable);// 自动分配列宽
		} else if (e.getSource() == sis.StoreInShowInId) {
			if (!sis.StoreInShowInId.getSelectedItem().toString()
					.equals("----请选择----")) {

				TableTools st = new TableTools(Sys.l);
				sis.StoreInShowdtm.setDataVector(st.toData(), st.toTitle());
				st.toTable(sis.StoreInShowtable);// 自动分配列宽
			} /*else {
				JOptionPane.showMessageDialog(sis, "请选择[商品名称]");
			}*/

		} else if (e.getSource() == sis.StoreInShowStart) {
			if (!sis.StoreInShowStart.getSelectedItem().toString()
					.equals("----请输入月份----")) {
				if (!sis.StoreInShowStart.getSelectedItem().toString()
						.equals("更早以前")) {

					TableTools st = new TableTools(Sys.l);
					// 更新dtm中的数据
					sis.StoreInShowdtm.setDataVector(st.toData(), st.toTitle());
					st.toTable(sis.StoreInShowtable);// 自动分配列宽
				} else if (sis.StoreInShowStart.getSelectedItem().toString()
						.equals("更早以前")) {

					TableTools st = new TableTools(Sys.l);
					sis.StoreInShowdtm.setDataVector(st.toData(), st.toTitle());
					st.toTable(sis.StoreInShowtable);// 自动分配列宽
				}

			} else if (sis.StoreInShowStart.getSelectedItem().toString()
					.equals("----请输入月份----")) {
				JOptionPane.showMessageDialog(sis, "  请选择月份!!!!!!!!!");

			}
		}else if(e.getSource()==sis.select){
			TableTools st = new TableTools(Sys.l);
			sis.StoreInShowdtm.setDataVector(st.toData(), st.toTitle());
			st.toTable(sis.StoreInShowtable);// 自动分配列宽
		}
	}
}
