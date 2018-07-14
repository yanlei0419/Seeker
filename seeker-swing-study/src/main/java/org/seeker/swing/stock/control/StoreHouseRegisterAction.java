package org.seeker.swing.stock.control;

import org.seeker.swing.stock.view.StoreHouseRegister;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;


public class StoreHouseRegisterAction implements ActionListener {
	StoreHouseRegister shr;

	public StoreHouseRegisterAction(StoreHouseRegister shr) {
		this.shr = shr;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == shr.StoreHousebtnReset) {
			System.out.println("点击重置按钮");
			shr.Stype.setSelectedIndex(0);
			shr.Sname.setText("");
			shr.area.setText("");
			shr.textmsg.setText("");
		}
		if (e.getSource() == shr.StoreHousebtnEnter) {

			if (!shr.Sname.getText().equals("") && !shr.Stype.getSelectedItem().toString().equals("----请选择----") && !shr.area.getText().equals("")) {
				// 插入信息
				if (1 == 1) {
					System.out.println("插入成功");

					JOptionPane.showMessageDialog(shr, "新仓库: " + shr.Sname.getText() + "  添加成功");
					shr.Stype.setSelectedIndex(0);
					shr.Sname.setText("");
					shr.area.setText("");
					shr.textmsg.setText("");
				} else {
					JOptionPane.showMessageDialog(shr, "新仓库: " + shr.Sname.getText() + "  添加失败");
				}
			} else {
				JOptionPane.showMessageDialog(shr, "数据填写不完整 , 请重新检查");
			}

		}
	}
}
