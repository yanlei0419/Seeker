package org.seeker.swing.stock.view;

import org.seeker.swing.stock.control.TakeOutFocus;
import org.seeker.swing.stock.control.TakeOutRegisterAction;
import org.seeker.swing.stock.util.Sys;
import org.seeker.swing.stock.util.TableTools;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;


@SuppressWarnings("serial")
public class TakeOutRegister extends JFrame{
	// 声明
	public JButton TakeOutRegisterbtnEnter, TakeOutRegisterbtnReset;
	public JLabel text,textmsg;
	public JTextField department,tempname,tnum,num;
	public JComboBox sname,pname;
	public Vector<String> msg;
	public TakeOutRegister() {
	
	}

	public JPanel RegisterMenu() {
		// 上面	
		JPanel ProductMain = new JPanel();
		GridLayout grid = new GridLayout(10, 1);
		ProductMain.setLayout(grid);
		this.getContentPane();
		Font font =new Font("微软雅黑", Font.BOLD, 16);
		Font font1 =new Font("微软雅黑", Font.BOLD, 14);
		JPanel jp1 = new JPanel();
		text = new JLabel("添加商品信息出库信息");
		text.setFont(new Font("微软雅黑", Font.BOLD, 30));
		jp1.add(text);
		ProductMain.add(jp1);
	
		JPanel two = new JPanel();
		text = new JLabel("仓库名称 :");
	
		
		
	
		//仓库表的下拉式菜单
		sname =new JComboBox();		
		sname.addItem("----请选择----");
		
		sname.setFont(font1);
		two.setLayout(null);
		text.setFont(font);
		text.setBounds(250, 2, 100, 20);
		sname.setBounds(350, 2, 150, 20);

		two.add(text);
		two.add(sname);
		
		
		
		
		JPanel three = new JPanel();
		text = new JLabel("商品名称 :");
		text.setFont(font);
		//库存查询
		TableTools stProduct =new TableTools(Sys.l);
		
		pname = new JComboBox(stProduct.toRow());
		
		pname.setFont(font1);
		three.setLayout(null);		
		text.setBounds(250, 2, 100, 20);
		pname.setBounds(350, 2, 150, 20);
		
		three.add(text);
		three.add(pname);
		
		//剩余数量
		JPanel ten = new JPanel();
		text = new JLabel("剩余数量 :");
		text.setFont(font);
		num = new JTextField(12);
		
		ten.setLayout(null);		
		text.setBounds(250, 2, 100, 20);
		num.setBounds(350, 2, 150, 20);
		num.setEditable(false);
		ten.add(text);
		ten.add(num);
		
		//出库数量
		JPanel four = new JPanel();
		text = new JLabel("出库数量 :");
		text.setFont(font);
		tnum = new JTextField(12);
		
		four.setLayout(null);		
		text.setBounds(250, 2, 100, 20);
		tnum.setBounds(350, 2, 150, 20);

		four.add(text);
		four.add(tnum);
		//领取人
		JPanel five = new JPanel();
		text = new JLabel("  领取人 :");
		text.setFont(font);
		tempname = new JTextField(12);
		
		five.setLayout(null);		
		text.setBounds(250, 2, 100, 20);
		tempname.setBounds(350, 2, 150, 20);
		
		five.add(text);
		five.add(tempname);
		//部门
		JPanel six = new JPanel();
		text = new JLabel("领取部门 :");
		text.setFont(font);
		department = new JTextField(12);
		
		six.setLayout(null);		
		text.setBounds(250, 2, 100, 20);
		department.setBounds(350, 2, 150, 20);
		
		six.add(text);
		six.add(department);
		
	
		
		textmsg=new JLabel();
		JPanel eight= new JPanel();	
		eight.add(textmsg);
		
		JPanel one =new JPanel();
		text = new JLabel();
		one.add(text);
		
		ProductMain.add(one);
		ProductMain.add(three);
		ProductMain.add(two);		
		ProductMain.add(ten);	
		ProductMain.add(four);
		ProductMain.add(five);
		ProductMain.add(six);
	
		ProductMain.add(eight);

		

		JPanel jp3 = new JPanel();
		TakeOutRegisterbtnEnter = new JButton("添  加");
		TakeOutRegisterbtnEnter.setFont(font1);
		TakeOutRegisterbtnReset = new JButton("重  置");
		TakeOutRegisterbtnReset.setFont(font1);
		jp3.add(TakeOutRegisterbtnEnter);
		jp3.add(TakeOutRegisterbtnReset);
		ProductMain.add(jp3);
		
		TakeOutRegisterAction pra =new TakeOutRegisterAction(this);
		pname.addActionListener(pra);
		sname.addActionListener(pra);
		TakeOutRegisterbtnEnter.addActionListener(pra);
		TakeOutRegisterbtnReset.addActionListener(pra);
		
		TakeOutFocus tof =new TakeOutFocus(this);
		tnum.addFocusListener(tof);
		return ProductMain;		
	}
}
