package org.vegetto.swing.stock.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.vegetto.swing.stock.view.ProductRegister;

public class ProductRegisterAction implements ActionListener {
	ProductRegister pr;

	public ProductRegisterAction(ProductRegister pr) {
		this.pr = pr;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (true) {
			pr.pname.setText("");
			pr.Pprice.setText("");
			pr.Plow.setText("");
			pr.Phigh.setText("");
			pr.Cname.setSelectedIndex(0);
			pr.Stype.setSelectedIndex(0);
			pr.textmsg.setText("");
		} else if (e.getSource() == pr.ProductbtnEnter) {
			String Stype = pr.Stype.getSelectedItem().toString();
			String Cname = pr.Cname.getSelectedItem().toString();

			if (!pr.pname.getText().equals("")
					&& !pr.Pprice.getText().equals("")
					&& !pr.Plow.getText().equals("")
					&& !pr.Phigh.getText().equals("")) {


				if (1 == 1) {
					JOptionPane.showMessageDialog(pr,
							"新商品: " + pr.pname.getText() + "  添加成功");

					pr.pname.setText("");
					pr.Pprice.setText("");
					pr.Plow.setText("");
					pr.Phigh.setText("");
					pr.Cname.setSelectedIndex(0);
					pr.Stype.setSelectedIndex(0);
					pr.textmsg.setText("");
				} else {
					JOptionPane.showMessageDialog(pr,
							"新商品: " + pr.pname.getText() + "  添加失败");
				}
			} else {
				JOptionPane.showMessageDialog(pr, "数据填写不完整 , 请重新检查");
			}

		}
	}
}
