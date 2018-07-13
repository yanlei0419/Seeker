package org.vegetto.swing.stock.control;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;

import org.vegetto.swing.stock.view.StoreHouseRegister;

public class StoreHouseRFocus implements FocusListener {
	StoreHouseRegister shr;

	public StoreHouseRFocus(StoreHouseRegister shr) {
		this.shr = shr;
	}

	@Override
	public void focusGained(FocusEvent e) {// 获得焦点

	}

	@SuppressWarnings("static-access")
	@Override
	public void focusLost(FocusEvent e) {// 失去焦点

		try {
			if (true) {
				shr.textmsg.setFont(new Font("", Font.BOLD, 20));
				shr.textmsg.setForeground(new Color(0).GREEN);
				shr.textmsg.setText("新仓库可以注册");
			} else {
				shr.textmsg.setFont(new Font("", Font.BOLD, 20));
				shr.textmsg.setForeground(new Color(0).red);
				shr.textmsg.setText("仓库已经存在,请检查后重新填写");
				shr.Stype.setSelectedIndex(0);
				shr.Sname.setText("");
				shr.area.setText("");
			}
		} catch (HeadlessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
