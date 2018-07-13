package org.vegetto.txt.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JOptionPane;

import org.vegetto.txt.ve.pack.TxT;

public class WinAction implements ActionListener {
	Win w;
	String[] array = null;
	

	public WinAction(Win w) {
		this.w = w;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == w.Enter) {// 确定
			boolean flag1 = true, flag3 = true;
			
			// 判断是否位数字
			if (!w.res.getText().equals("")) {
				char[] array2 = w.res.getText().toCharArray();
				for (int i = 0; i < array2.length; i++) {
					if (!Character.isDigit(array2[i])) {
						flag1 = false;
					}
				}
				if (flag1 == true) {
					Random rd = new Random();
					TxT txt = new TxT();
					String str = txt.FeadFile(w.getPath());
					//String str = txt.FeadFile("E:/abx.txt");
					array = str.split(",");
					int i = 0;
					String str1 = "";
					int ve=Integer.parseInt(w.res.getText());
					int[] ay = new int[ve];
					while (i <Integer.parseInt(w.res.getText()) ) {
						
						int a = rd.nextInt(array.length);
						System.out.println("随机数"+i+"       "+a);
						// 进入循环判断是否和数组中的数据相等
					
						if (i!=0) {
							
							for (int j = 0; j <i ; j++) {
								//System.out.println(ay[j]);
								if (ay[j] == a) {
									flag3 = false;
									//System.out.println(ay[j]);
								}
							}
						}
						System.out.println(flag3);
						if (flag3 == true) {
							ay[i] = a;
							//System.out.println("+"+a);
							//System.out.println("数组"+ay[i]);
							str1 = str1 + array[a] + "\n";
							i++;
						}
						flag3 = true;
					}
					w.jta.setText(str1 + "\n");
				} else {
					JOptionPane.showMessageDialog(w, "参数设置不是数字");
				}
			} else if (w.res.getText().equals("")) {
				JOptionPane.showMessageDialog(w, "没有设置参数");
			}

			w.res.setEditable(false);
		} else if (e.getSource() == w.Import) {// 导入
			w.setPath(w.openfile());
			w.res.setEditable(false);
		} else if (e.getSource() == w.Reset) {// 重置
			w.jta.setText("");
			w.res.setText("");
			w.res.setEditable(false);
		} else if (e.getSource() == w.Options) {// 设置
			w.res.setEditable(true);
		}
	}
}
