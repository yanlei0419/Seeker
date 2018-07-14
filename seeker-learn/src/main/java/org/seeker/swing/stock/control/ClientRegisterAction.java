package org.vegetto.swing.stock.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.vegetto.swing.stock.view.ClientRegister;

public class ClientRegisterAction implements ActionListener {
	ClientRegister cr;

	public ClientRegisterAction(ClientRegister cr) {
		this.cr = cr;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cr.ClientbtnReset) {//重置
			cr.cname.setText("");
			cr.caddress.setText("");
			cr.contact.setText("");
			cr.textmsg.setText("");
			cr.phone.setText("");
		} else if (e.getSource() == cr.ClientbtnEnter) {//注册
			if (!cr.cname.getText().equals("")
					&& !cr.caddress.getText().equals("")
					&& !cr.contact.getText().equals("")
					&& !cr.phone.getText().equals("")) {
				
				// 插入信息
				System.out.println(cr.cname.getText().trim()
						+ cr.caddress.getText().trim()
						+ cr.contact.getText().trim()
						+ cr.phone.getText().trim());

				if (1 == 1) {
					JOptionPane.showMessageDialog(cr,
							"新供应商: " + cr.cname.getText() + "  更新成功");
					cr.cname.setText("");
					cr.caddress.setText("");
					cr.contact.setText("");
					cr.phone.setText("");
					cr.textmsg.setText("");

				} else {
					JOptionPane.showMessageDialog(cr,
							"新供应商: " + cr.cname.getText() + "  更新失败");
				}				
			} else {
				JOptionPane.showMessageDialog(cr, "数据填写不完整 , 请重新检查!!!");
			}
		}
	}
}
