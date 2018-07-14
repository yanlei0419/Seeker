package org.seeker.swing.stock.view;


import org.seeker.swing.stock.control.StoreInRegisterAction;
import org.seeker.swing.stock.util.Sys;
import org.seeker.swing.stock.util.TableTools;

import javax.swing.*;
import java.awt.*;


@SuppressWarnings("serial")
public class StoreInRegister extends JFrame{
	// 声明
	public JButton StoreInbtnEnter, StoreInbtnReset;
	public JLabel text,textmsg;
	public JTextField snum,sempname,stype;
	public JComboBox sname,pname;

	public StoreInRegister() {
	
	}

	public JPanel RegisterMenu() {
		// 上面	
		JPanel ProductMain = new JPanel();
		GridLayout grid = new GridLayout(10, 1);
		ProductMain.setLayout(grid);
		this.getContentPane();
		Font font1 =new Font("微软雅黑", Font.BOLD, 14);
		Font font =new Font("微软雅黑", Font.BOLD, 16);
		JPanel jp1 = new JPanel();
		text = new JLabel("商品基本信息录入");
		text.setFont(new Font("微软雅黑", Font.BOLD, 30));
		jp1.add(text);
		ProductMain.add(jp1);
	
		

		JPanel three = new JPanel();
		text = new JLabel("商品名称 :");
		text.setFont(font);
		TableTools stPname =new TableTools(Sys.l);
		
		pname = new JComboBox(stPname.toRow());
		pname.setFont(font1);
		three.setLayout(null);
	
		text.setBounds(250, 2, 100, 20);
		pname.setBounds(350, 2, 150, 20);
		
		three.add(text);
		three.add(pname);
		
		JPanel six = new JPanel();
		text = new JLabel("商品类型 :");
		text.setFont(font);
		stype=new JTextField();
		stype.setEnabled(false);
		
		stype.setFont(font1);
		six.setLayout(null);		
		text.setBounds(250, 2, 100, 20);
		stype.setBounds(350, 2, 150, 20);

		six.add(text);
		six.add(stype);
		
		
		JPanel seven = new JPanel();
		text = new JLabel("仓库名称 :");
		text.setFont(font);
		sname=new JComboBox();
		sname.addItem("----请选择----");
		sname.setFont(font1);
		seven.setLayout(null);		
		text.setBounds(250, 2, 100, 20);
		sname.setBounds(350, 2, 150, 20);

		seven.add(text);
		seven.add(sname);
		
		
		

		JPanel four = new JPanel();
		text = new JLabel("入库数量 :");
		text.setFont(font);
		snum = new JTextField(12);		
		four.setLayout(null);		
		text.setBounds(250, 2, 100, 20);
		snum.setBounds(350, 2, 150, 20);
		snum.setFont(font1);
		four.add(text);
		four.add(snum);
		
		
		
		
		
		JPanel five = new JPanel();
		text = new JLabel("   记帐员 :");
		text.setFont(font);
		sempname = new JTextField(12);
		
		five.setLayout(null);		
		text.setBounds(250, 2, 100, 20);
		sempname.setBounds(350, 2, 150, 20);
		sempname.setFont(font1);
		five.add(text);
		five.add(sempname);
		
	
		
		textmsg=new JLabel();
		JPanel eight= new JPanel();	
		eight.add(textmsg);
		
		JPanel one =new JPanel();
		text =new JLabel();
		one.add(text);
		
		ProductMain.add(one);
		ProductMain.add(three);
		ProductMain.add(six);
		ProductMain.add(seven);		

		ProductMain.add(four);
		ProductMain.add(five);
	
		ProductMain.add(eight);

		

		JPanel jp3 = new JPanel();
		StoreInbtnEnter = new JButton("添  加");
		StoreInbtnEnter.setFont(font1);
		StoreInbtnReset = new JButton("重  置");
		StoreInbtnReset.setFont(font1);
		jp3.add(StoreInbtnEnter);
		jp3.add(StoreInbtnReset);
		ProductMain.add(jp3);
		
		StoreInRegisterAction pra =new StoreInRegisterAction(this);
		StoreInbtnEnter.addActionListener(pra);
		StoreInbtnReset.addActionListener(pra);
		pname.addActionListener(pra);
		stype.addActionListener(pra);
		sname.addActionListener(pra);
		return ProductMain;		
	}
}
