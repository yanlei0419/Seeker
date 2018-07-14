package org.seeker.swing.stock.control;

import org.seeker.swing.stock.util.Sys;
import org.seeker.swing.stock.util.TableTools;
import org.seeker.swing.stock.view.StoreShow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class StoreShowAction implements ActionListener {
	StoreShow ss;

	public StoreShowAction(StoreShow ss) {
		this.ss = ss;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ss.StoreBtnShow) {

			TableTools st = new TableTools(Sys.l);
			ss.StoreShowdtm.setDataVector(st.toData(), st.toTitle());
		} else if (e.getSource() == ss.StoreId) {
			if (!ss.StoreId.getSelectedItem().toString().equals("----请选择----")) {
				TableTools st = new TableTools(Sys.l);
				ss.StoreShowdtm.setDataVector(st.toData(), st.toTitle());
			} /*else {
				JOptionPane.showMessageDialog(ss, "请选择[商品名称]");
			}*/
		}else if(e.getSource()==ss.StoreSname){
			if (!ss.StoreSname.getSelectedItem().toString().equals("----请选择----")) {
				TableTools st = new TableTools(Sys.l);
				ss.StoreShowdtm.setDataVector(st.toData(), st.toTitle());
			} 
		}else if(e.getSource()==ss.select){
			TableTools st = new TableTools(Sys.l);
			ss.StoreShowdtm.setDataVector(st.toData(), st.toTitle());
		}
	}
}
