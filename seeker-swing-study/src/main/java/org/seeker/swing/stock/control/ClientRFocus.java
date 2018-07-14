package org.seeker.swing.stock.control;

import org.seeker.swing.stock.view.ClientRegister;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

	public class ClientRFocus implements FocusListener {
	ClientRegister cr;

	public ClientRFocus(ClientRegister cr) {
		this.cr = cr;
	}

	@Override
	public void focusGained(FocusEvent e) {// 获得焦点

	}

	@SuppressWarnings("static-access")
	@Override
	public void focusLost(FocusEvent e) {// 失去焦点
		if (e.getSource() == cr.cname) {
			// 查询姓名获取一个值 

			if (true) {
				cr.textmsg.setFont(new Font("", Font.BOLD, 20));
				cr.textmsg.setForeground(new Color(0).GREEN);
				cr.textmsg.setText("此公司可以注册");
			} else {
				cr.textmsg.setFont(new Font("", Font.BOLD, 20));
				cr.textmsg.setForeground(new Color(0).red);
				cr.textmsg.setText("此公司已是供应商,请检查后重新填写!!!");

			}
		}
	}
}
