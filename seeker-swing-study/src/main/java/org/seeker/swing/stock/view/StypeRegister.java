package org.seeker.swing.stock.view;

import org.seeker.swing.stock.control.StypeRegisterAction;
import org.seeker.swing.stock.control.SytpeRFocus;

import javax.swing.*;
import java.awt.*;



@SuppressWarnings("serial")
public class StypeRegister extends JFrame{
	// 声明
	public JButton StypebtnEnter, StypebtnReset;
	public JLabel text,textmsg;
	public JTextField id,name;
	
	

	public StypeRegister() {
	
	}

	public JPanel RegisterMenu() {
		// 上面	
		JPanel StorehouseMain = new JPanel();
		GridLayout grid = new GridLayout(10, 1);
		StorehouseMain.setLayout(grid);
		this.getContentPane();
		Font font =new Font("微软雅黑", Font.BOLD, 16);
		Font font1 =new Font("微软雅黑", Font.BOLD, 14);
		JPanel jp1 = new JPanel();
		text = new JLabel("添 加 商 品 类 型");
		text.setFont(new Font("微软雅黑", Font.BOLD, 30));
		jp1.add(text);
		StorehouseMain.add(jp1);

		JPanel one =new JPanel();
		text = new JLabel();
		one.add(text);
		JPanel ten =new JPanel();
		ten.add(text);
	
		JPanel two = new JPanel();
		text = new JLabel("类型名称 :");
		
		name= new JTextField(12);
		
		two.setLayout(null);
		text.setFont(font);
		text.setBounds(250, 2, 100, 20);
		name.setBounds(350, 2, 150, 20);
		two.add(text);
		two.add(name);
		JPanel four =new JPanel();
		textmsg =new JLabel();
		textmsg.setFont(font);
		four.add(textmsg);
		
		StorehouseMain.add(one);
		StorehouseMain.add(ten);
		StorehouseMain.add(two);		
		StorehouseMain.add(four);
		

		

		JPanel jp3 = new JPanel();
		StypebtnEnter = new JButton("添  加");
		StypebtnEnter.setFont(font1);
		StypebtnReset = new JButton("重  置");
		StypebtnReset.setFont(font1);
		jp3.add(StypebtnEnter);
		jp3.add(StypebtnReset);
		StorehouseMain.add(jp3);
		
		StypeRegisterAction shra =new StypeRegisterAction(this);
		StypebtnEnter.addActionListener(shra);
		StypebtnReset.addActionListener(shra);
		
		SytpeRFocus srf =new SytpeRFocus(this);
		
		name.addFocusListener(srf);
		return StorehouseMain;		
	}
}
