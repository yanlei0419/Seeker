package org.seeker.swing.stock.view;

import org.seeker.swing.stock.control.ProdcutRFocus;
import org.seeker.swing.stock.control.ProductRegisterAction;
import org.seeker.swing.stock.util.Sys;
import org.seeker.swing.stock.util.TableTools;

import javax.swing.*;
import java.awt.*;


@SuppressWarnings("serial")
public class ProductRegister extends JFrame{
	// 声明
	public JButton ProductbtnEnter, ProductbtnReset;
	public JLabel text,textmsg;
	public JTextField Pid,pname,Pprice, Plow,Phigh;
	public JComboBox Stype ,Cname;
	
	public ProductRegister() {
	}

	public JPanel RegisterMenu() {
		
		
		// 上面	
		JPanel ProductMain = new JPanel();
		GridLayout grid = new GridLayout(12, 1);
		ProductMain.setLayout(grid);
		this.getContentPane();
		Font font =new Font("微软雅黑", Font.BOLD, 16);
		Font font1 =new Font("微软雅黑", Font.BOLD, 14);
		JPanel jp1 = new JPanel();
		text = new JLabel("商品基本信息录入");
		text.setFont(new Font("微软雅黑", Font.BOLD, 30));
		jp1.add(text);
		ProductMain.add(jp1);

		
		
	
		JPanel two = new JPanel();
		text = new JLabel("   商品名称 :");
		text.setFont(font);
		pname = new JTextField(12);
		
		two.setLayout(null);

		text.setBounds(250, 2, 100, 20);
		pname.setBounds(350, 2, 150, 20);

		two.add(text);
		two.add(pname);

		JPanel three = new JPanel();
		text = new JLabel("   参考价钱 :");
		text.setFont(font);
		Pprice = new JTextField(12); 
		
		three.setLayout(null);
		
		text.setBounds(250, 2, 100, 20);
		Pprice.setBounds(350, 2, 150, 20);
		
		three.add(text);
		three.add(Pprice);

		JPanel four = new JPanel();
		text = new JLabel("最低库存量 :");
		text.setFont(font);
		Plow = new JTextField(12);
		
		four.setLayout(null);
		
		text.setBounds(250, 2, 100, 20);
		Plow.setBounds(350, 2, 150, 20);

		four.add(text);
		four.add(Plow);
		
		JPanel five = new JPanel();
		text = new JLabel("最高库存量 :");
		text.setFont(font);
		Phigh = new JTextField(12);
		
		five.setLayout(null);		
		text.setBounds(250, 2, 100, 20);
		Phigh.setBounds(350, 2, 150, 20);
		
		five.add(text);
		five.add(Phigh);
		
		
		JPanel six = new JPanel();	
		text = new JLabel("   商品类型 :");
		text.setFont(font);
		
		//商品类型下拉式菜单
		TableTools St =new TableTools(Sys.l);
		
		Stype = new JComboBox(St.toRow());
		Stype.setFont(font1);
		
		six.setLayout(null);		
		text.setBounds(250, 2, 100, 20);
		Stype.setBounds(350, 2, 150, 20);		
		
		six.add(text);
		six.add(Stype);
		
		
	
		//查询并向下拉式菜单中赋值
	
		TableTools StClient =new TableTools(Sys.l);
		
		text = new JLabel("      供应商 :");
		text.setFont(font);
		Cname =new JComboBox(StClient.toRow());
		Cname.setFont(font1);
		JPanel seven= new JPanel();	
		
		seven.setLayout(null);		
		text.setBounds(250, 2, 100, 20);
		Cname.setBounds(350, 2, 150, 20);		
		
		seven.add(text);
		seven.add(Cname);
		
		textmsg=new JLabel();
		JPanel eight= new JPanel();	
		eight.add(textmsg);
		
		JPanel one =new JPanel();
		text=new JLabel();
		one.add(text);
		ProductMain.add(one);
		ProductMain.add(two);
		ProductMain.add(three);
		ProductMain.add(four);
		ProductMain.add(five);
		ProductMain.add(six);
		
		ProductMain.add(seven);
		ProductMain.add(eight);

		

		JPanel jp3 = new JPanel();
		ProductbtnEnter = new JButton("添  加");
		ProductbtnEnter.setFont(font1);
		ProductbtnReset = new JButton("重  置");
		ProductbtnReset.setFont(font1);
		jp3.add(ProductbtnEnter);
		jp3.add(ProductbtnReset);
		ProductMain.add(jp3);
		
		ProductRegisterAction pra =new ProductRegisterAction(this);
		ProductbtnEnter.addActionListener(pra);
		ProductbtnReset.addActionListener(pra);
		Stype.addActionListener(pra);
		Cname.addActionListener(pra);
		ProdcutRFocus prf=new ProdcutRFocus(this);
		pname.addFocusListener(prf);
		return ProductMain;		
	}
}
