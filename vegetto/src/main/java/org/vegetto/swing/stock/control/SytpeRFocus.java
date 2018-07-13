package org.vegetto.swing.stock.control;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;

import org.vegetto.swing.stock.view.StypeRegister;

public class SytpeRFocus implements FocusListener {
	StypeRegister sr;
	public SytpeRFocus(StypeRegister sr) {
		this.sr = sr;
	}

	

	@Override
	public void focusGained(FocusEvent e) {// 获得焦点
		

	}

	@SuppressWarnings("static-access")
	@Override
	public void focusLost(FocusEvent e) {// 失去焦点
		try {
			if (true) {
				sr.textmsg.setFont(new Font("",Font.BOLD,20));
				sr.textmsg.setForeground(new Color(0).GREEN);
				sr.textmsg.setText("物品类型可以注册");
			
			}else{
				sr.textmsg.setFont(new Font("",Font.BOLD,20));
				sr.textmsg.setForeground(new Color(0).red);
				sr.textmsg.setText("此物品类型已经存在,请检查后重新填写");
				sr.name.setText("");	
			}
		} catch (HeadlessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
