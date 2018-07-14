package org.seeker.swing.stock.util;

import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Clock extends JFrame implements Runnable {
	//声明组件
	JLabel Clock;
	//构造

	public JLabel toShow(){
		Clock = new JLabel("00:00:00",JLabel.CENTER);
		Clock.setFont(new Font("微软雅黑",Font.BOLD,20));  //设置标签的字体
		
		Clock.setOpaque(true);  //透明设置
		this.getContentPane().add(Clock);
		
		Thread td = new Thread(this);
		td.start();
		
		return Clock;
	}
	@Override
	public void run() {	
		while(true){
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat(" y 年  M 月 d 日   aa HH:mm:ss");
			Clock.setText("现在时刻 : "+sdf.format(now));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}	
}
