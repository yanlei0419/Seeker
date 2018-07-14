package org.seeker.swing.stock.control;


import org.seeker.swing.stock.dbcutil.DBConn;
import org.seeker.swing.stock.view.ProductRegister;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
public class ProdcutRFocus implements FocusListener {
	ProductRegister pr;
	public ProdcutRFocus(ProductRegister pr) {
		this.pr = pr;
	}

	

	@Override
	public void focusGained(FocusEvent e) {// 获得焦点
		}

	@SuppressWarnings("static-access")
	@Override
	public void focusLost(FocusEvent e) {// 失去焦点
		
		try {
			if (true) {	
				pr.textmsg.setFont(new Font("",Font.BOLD,20));
				pr.textmsg.setForeground(new Color(0).GREEN);
				pr.textmsg.setText("商品可以注册");
			} else {
				pr.textmsg.setFont(new Font("",Font.BOLD,20));
				pr.textmsg.setForeground(new Color(0).red);
				pr.textmsg.setText("商品已经存在,请检查后重新填写");
				pr.pname.setText("");
				pr.Pprice.setText("");
				pr.Plow.setText("");
				pr.Phigh.setText("");
				pr.Cname.setSelectedIndex(0);
				pr.Stype.setSelectedIndex(0);
			}
			new DBConn().closeConnection();
		} catch (HeadlessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	}
}
