package org.vegetto.swing.stock.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.vegetto.swing.stock.util.Sys;
import org.vegetto.swing.stock.util.TableTools;
import org.vegetto.swing.stock.view.TakeOutShow;

public class TakeOutShowAction implements ActionListener {
	TakeOutShow tos;

	public TakeOutShowAction(TakeOutShow tos) {
		this.tos = tos;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == tos.TakeOutShowBtnShow) {// x显示全部
			// 查询语句

			TableTools st = new TableTools(Sys.l);
			tos.TakeOutShowdtm.setDataVector(st.toData(), st.toTitle());
			st.toTable(tos.TakeOutShowtable);//自动分配列宽
		} else if (e.getSource() == tos.TakeOutShowtId) {
			if(!tos.TakeOutShowtId.getSelectedItem().toString().equals("----请选择----")){
				TableTools st = new TableTools(Sys.l);
				tos.TakeOutShowdtm.setDataVector(st.toData(), st.toTitle());
				st.toTable(tos.TakeOutShowtable);//自动分配列宽
			}/*else{
				JOptionPane.showMessageDialog(tos, "请选择[商品名称]");
			}*/
			
		} else if (e.getSource() == tos.TakeOutShowStart) {
			if (!tos.TakeOutShowStart.getSelectedItem().toString()
					.equals("----请输入月份----")) {
				if (!tos.TakeOutShowStart.getSelectedItem().toString()
						.equals("更早以前")) {
					TableTools st = new TableTools(Sys.l);
					tos.TakeOutShowdtm.setDataVector(st.toData(), st.toTitle());
					st.toTable(tos.TakeOutShowtable);//自动分配列宽
				} else if (tos.TakeOutShowStart.getSelectedItem().toString()
						.equals("更早以前")) {
					TableTools st = new TableTools(Sys.l);
					tos.TakeOutShowdtm.setDataVector(st.toData(), st.toTitle());
					st.toTable(tos.TakeOutShowtable);//自动分配列宽
				}
			} else if (tos.TakeOutShowStart.getSelectedItem().toString()
					.equals("----请输入月份----")) {
				JOptionPane.showMessageDialog(tos,
						"          请选择月份 !!!!!!!!!!!!!");
			}
		}else if(e.getSource()==tos.Select){//综合查询
			
			TableTools st = new TableTools(Sys.l);
			tos.TakeOutShowdtm.setDataVector(st.toData(), st.toTitle());
			st.toTable(tos.TakeOutShowtable);//自动分配列宽
			
		}
	}
}
