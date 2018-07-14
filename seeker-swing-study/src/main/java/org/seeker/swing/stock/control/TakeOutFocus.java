package org.seeker.swing.stock.control;

import org.seeker.swing.stock.view.TakeOutRegister;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class TakeOutFocus implements FocusListener {
	TakeOutRegister tor;

	public TakeOutFocus(TakeOutRegister tor) {
		this.tor = tor;
	}

	@Override
	public void focusGained(FocusEvent e) {// 获得焦点
		if (e.getSource() == tor.tnum) {
			tor.num.setText(System.currentTimeMillis()+"");

		}

	}

	@Override
	public void focusLost(FocusEvent e) {// 失去焦点
		if (e.getSource() == tor.tnum) {
			boolean flag = true;
			String num = tor.num.getText();
			String tnum = null;
			if (!tor.tnum.getText().equals("")) {		
				
				 tnum = tor.tnum.getText();

				char[] array2 = tnum.toCharArray();

				for (int i = 0; i < array2.length; i++) {
					if (!Character.isDigit(array2[i])) {
						System.out.println("不是数字 : " + array2[i]);
						flag = false;
					}
				}
			}else if(tor.tnum.getText().equals("")){
				tnum="0";
			}else {
				JOptionPane.showMessageDialog(tor, "出库数量填写有问题,请检查后填写");
			}
			// 判断是否将num 转换成字符串
			if (flag == true) {
				// 将 num 和 tnum 两个字符串转换成数字
				int a = Integer.parseInt(num);
				int b = Integer.parseInt(tnum);
				int c = a - b;
				System.out.println(c);

				if (c >= 0) {
					// 将数字转换成字符串
					String str = String.valueOf(c);

					tor.num.setText(str);
				} else if (c < 0) {
					JOptionPane.showMessageDialog(tor, tor.sname
							.getSelectedItem().toString()
							+ " :  仓库没有这么多库存,请重新填写,或者更换仓库查看");
				}

			} else {
				JOptionPane.showMessageDialog(tor, tnum + "不是数字");
			}

		}

	}
}
