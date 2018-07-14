package org.seeker.swing.stock.view;

import org.seeker.swing.stock.control.StoreHouseRFocus;
import org.seeker.swing.stock.control.StoreHouseRegisterAction;
import org.seeker.swing.stock.util.Sys;
import org.seeker.swing.stock.util.TableTools;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class StoreHouseRegister extends JFrame{
	// 声明
	public JButton StoreHousebtnEnter, StoreHousebtnReset;
	public JLabel text,textmsg;
	public JTextField Sname,area;
	public JComboBox Stype;
	
	
	public StoreHouseRegister() {
	
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
		text = new JLabel("添 加 仓 库 基 本 信 息");
		text.setFont(new Font("微软雅黑", Font.BOLD, 30));
		jp1.add(text);
		StorehouseMain.add(jp1);

		JPanel one =new JPanel();
		text = new JLabel();
		one.add(text);
		
	
		JPanel two = new JPanel();
		text = new JLabel("仓库名称 :");
		
		Sname= new JTextField(12);
		
		two.setLayout(null);
		text.setFont(font);
		text.setBounds(250, 2, 100, 20);
		Sname.setBounds(350, 2, 150, 20);

		two.add(text);
		two.add(Sname);

		JPanel three = new JPanel();
		text = new JLabel("存货种类:");	
		
		
	
		TableTools st =new TableTools(Sys.l);
		
		Stype = new JComboBox(st.toRow());
		three.setLayout(null);
		text.setFont(font);
		text.setBounds(250, 2, 100, 20);
		Stype.setBounds(350, 2, 150, 20);
		Stype.setFont(font1);
		three.add(text);
		three.add(Stype);
		
		
		JPanel five = new JPanel();
		text = new JLabel("存储空间:");	
		area = new JTextField(12); 
		
		five.setLayout(null);
		text.setFont(font);
		text.setBounds(250, 2, 100, 20);
		area.setBounds(350, 2, 150, 20);
		
		five.add(text);
		five.add(area);
		
		JPanel four =new JPanel();
		textmsg =new JLabel();
		textmsg.setFont(font);
		four.add(textmsg);
		
		StorehouseMain.add(one);
		StorehouseMain.add(two);
		StorehouseMain.add(three);
		StorehouseMain.add(five);
		StorehouseMain.add(four);

		JPanel jp3 = new JPanel();
		StoreHousebtnEnter = new JButton("添  加");
		StoreHousebtnEnter.setFont(font1);
		StoreHousebtnReset = new JButton("重  置");
		StoreHousebtnReset.setFont(font1);
		jp3.add(StoreHousebtnEnter);
		jp3.add(StoreHousebtnReset);
		StorehouseMain.add(jp3);
		
		StoreHouseRegisterAction shra =new StoreHouseRegisterAction(this);
		StoreHousebtnEnter.addActionListener(shra);
		StoreHousebtnReset.addActionListener(shra);
		
		//鼠标监听事件
		Sname.addFocusListener(new StoreHouseRFocus(this));
		
		return StorehouseMain;		
	}
}
