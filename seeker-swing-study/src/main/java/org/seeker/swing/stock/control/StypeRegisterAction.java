package org.seeker.swing.stock.control;

import org.seeker.swing.stock.view.StypeRegister;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;


public class StypeRegisterAction implements ActionListener {
	StypeRegister sr;

	public StypeRegisterAction(StypeRegister sr) {
		this.sr = sr;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == sr.StypebtnEnter) {

			if (!sr.name.getText().trim().equals("")) {
				if (1 == 1) {
					JOptionPane.showMessageDialog(sr, sr.name.getText().trim() + "  添加完成");
					sr.name.setText("");
					sr.textmsg.setText("");
				} else {
					JOptionPane.showMessageDialog(sr, sr.name.getText().trim() + "  添加完成");
				}
				sr.name.setText("");
			} else {
				JOptionPane.showMessageDialog(sr, " 数据填写不完整   !!!!!!!");
			}

		} else if (e.getSource() == sr.StypebtnReset) {
			sr.name.setText("");
			sr.textmsg.setText("");
		}
	}
}
