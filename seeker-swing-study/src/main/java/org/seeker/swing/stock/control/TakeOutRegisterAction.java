package org.seeker.swing.stock.control;

import org.seeker.swing.stock.view.TakeOutRegister;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TakeOutRegisterAction implements ActionListener {
	TakeOutRegister tor;

	public TakeOutRegisterAction(TakeOutRegister tor) {
		this.tor = tor;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == tor.pname) {//二级联动下拉式菜单
			if (!tor.pname.getSelectedItem().toString().equals("----请选择----")) {
				
				tor.sname.removeAllItems();
				tor.sname.addItem("----请选择----");				
				
				tor.sname.addItem(System.currentTimeMillis());
			}
		} else if (e.getSource() == tor.TakeOutRegisterbtnReset) {//重置信息

			tor.tempname.setText("");
			tor.tnum.setText("");
			tor.department.setText("");
			tor.num.setText("");
			tor.sname.setSelectedIndex(0);
			tor.pname.setSelectedIndex(0);

		} else if (e.getSource() == tor.TakeOutRegisterbtnEnter) {//添加信息

			if (!tor.tempname.getText().equals("")
					&& !tor.tnum.getText().equals("")
					&& !tor.department.getText().equals("")
					&&!tor.sname.getSelectedItem().toString().equals("----请选择----")
					&&!tor.pname.getSelectedItem().toString().equals("----请选择----")) {
				
				if (3 == 3) {
					JOptionPane.showMessageDialog(tor, "出库信息添加完成");
					System.out.println("插入成功");
				} else {
					JOptionPane.showMessageDialog(tor, "出库信息添加失败");
				}
			} else {
				JOptionPane.showMessageDialog(tor, "数据填写不完整 , 请重新检查");
			}
			tor.tempname.setText("");
			tor.tnum.setText("");
			tor.department.setText("");
			tor.num.setText("");
			tor.sname.setSelectedIndex(0);
			tor.pname.setSelectedIndex(0);

		}
	}
}
