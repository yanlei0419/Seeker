package org.seeker.swing.stock.control;

import org.seeker.swing.stock.view.StoreInRegister;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;


public class StoreInRegisterAction implements ActionListener {
	StoreInRegister sr;

	public StoreInRegisterAction(StoreInRegister sr) {
		this.sr = sr;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == sr.pname) {
			if(!sr.pname.getSelectedItem().toString().equals("----请选择----")){	
				sr.sname.removeAllItems();
				sr.sname.addItem("----请选择----");
				sr.sname.setSelectedIndex(0);
			}
			
		} else if (e.getSource() == sr.StoreInbtnReset) {

			sr.sempname.setText("");
			sr.snum.setText("");
			sr.sname.setSelectedIndex(0);
			sr.pname.setSelectedIndex(0);
			sr.stype.setText("");
		} else if (e.getSource() == sr.StoreInbtnEnter) {//添加
			System.out.println(sr.sname.getSelectedItem().toString());
			if(!sr.sname.getSelectedItem().toString().equals("----请选择----")&&!sr.pname.getSelectedItem().toString().equals("")){
				if (!sr.sempname.getText().equals("")
						&& !sr.snum.getText().equals("")) {

					if (3 == 3) {
						JOptionPane.showMessageDialog(sr, "入库信息添加完成");

					} else {
						JOptionPane.showMessageDialog(sr, "入库信息录入不完整");
					}
					sr.sempname.setText("");
					sr.snum.setText("");
					sr.sname.setSelectedIndex(0);
					sr.pname.setSelectedIndex(0);
					sr.stype.setText("");
				} else {
					JOptionPane.showMessageDialog(sr, "数据填写不完整 , 请重新检查");
				}

			}else{
				JOptionPane.showMessageDialog(sr, "请选择入库仓库!!!!!!!!!!!!!");
			}
		
		}

	}
}
