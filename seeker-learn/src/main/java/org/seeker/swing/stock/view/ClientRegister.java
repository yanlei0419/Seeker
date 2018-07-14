package org.vegetto.swing.stock.view;


import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.vegetto.swing.stock.control.ClientRFocus;
import org.vegetto.swing.stock.control.ClientRegisterAction;

@SuppressWarnings("serial")
public class ClientRegister extends JFrame{
	// 声明
	public JButton ClientbtnEnter, ClientbtnReset;
	public JLabel text,textmsg;
	public JTextField cname,caddress,contact,phone;
	

	public ClientRegister() {
	
	}

	public JPanel RegisterMenu() {
		// 上面	
		JPanel ClientMain = new JPanel();
		GridLayout grid = new GridLayout(10, 1);
		ClientMain.setLayout(grid);
		this.getContentPane();
		Font font =new Font("微软雅黑", Font.BOLD, 16);
		Font font1 =new Font("微软雅黑", Font.BOLD, 14);
		JPanel jp1 = new JPanel();
		text = new JLabel("添 加 供 应 商 基 本 信 息");
		text.setFont(new Font("微软雅黑", Font.BOLD, 30));
		jp1.add(text);
		ClientMain.add(jp1);

		
		
		JPanel one =new JPanel();
		text = new JLabel();
		one.add(text);
		
		JPanel two = new JPanel();
		text = new JLabel("供应商 :");
		
		cname= new JTextField(12);
		
		two.setLayout(null);
		text.setFont(font);
		text.setBounds(250, 2, 100, 20);
		cname.setBounds(350, 2, 150, 20);

		two.add(text);
		two.add(cname);

		JPanel three = new JPanel();
		text = new JLabel("供应商地址 :");
	
		caddress = new JTextField(12); 
		
		three.setLayout(null);
		text.setFont(font);
		text.setBounds(220, 2, 100, 20);
		caddress.setBounds(350, 2, 150, 20);
		
		three.add(text);
		three.add(caddress);
		
		JPanel five = new JPanel();
		text = new JLabel("负责人 :");
		
		contact = new JTextField(12); 
		
		five.setLayout(null);
		text.setFont(font);
		text.setBounds(250, 2, 100, 20);
		contact.setBounds(350, 2, 150, 20);
		
		five.add(text);
		five.add(contact);
		
		JPanel six = new JPanel();
		text = new JLabel("联系电话 :");
	
		phone = new JTextField(12); 
		six.add(text);
		six.add(phone);
		
		six.setLayout(null);
		text.setFont(font);
		text.setBounds(230, 2, 100, 20);
		phone.setBounds(350, 2, 150, 20);
		
		JPanel four =new JPanel();
		textmsg =new JLabel();
		textmsg.setFont(font);
		four.add(textmsg);
		
		ClientMain.add(one);
		ClientMain.add(two);
		ClientMain.add(three);
		ClientMain.add(five);
		ClientMain.add(six);
		ClientMain.add(four);
		

		

		JPanel jp3 = new JPanel();
		ClientbtnEnter = new JButton("添  加");
		ClientbtnEnter.setFont(font1);
		ClientbtnReset = new JButton("重  置");
		ClientbtnReset.setFont(font1);
		jp3.add(ClientbtnEnter);
		jp3.add(ClientbtnReset);
		ClientMain.add(jp3);
		
		ClientRegisterAction cra =new ClientRegisterAction(this);
		ClientbtnEnter.addActionListener(cra);
		ClientbtnReset.addActionListener(cra);
		ClientRFocus cr =new ClientRFocus(this);
		cname.addFocusListener(cr);
		return ClientMain;		
	}
}
