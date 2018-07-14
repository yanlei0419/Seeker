package org.seeker.swing.stock.view;

import org.seeker.swing.stock.control.StoreShowAction;
import org.seeker.swing.stock.util.Sys;
import org.seeker.swing.stock.util.TableTools;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


@SuppressWarnings("serial")
public class StoreShow extends JFrame {
	// 声明
	public JButton StoreBtnShow,select;
	public JLabel text;
	public JComboBox StoreId,StoreSname;
	public JPanel StoreShowMain, StoreShowUP, StoreShowCenter,StoreShowEnd;
	public JTable StoreShowtable;
	public JScrollPane StoreShowjsp;
	public DefaultTableModel StoreShowdtm;

	//private String UserType;
	// 绑定事件声明
	
	public StoreShow(){
		//this.UserType= UserType;
		
	}

	public JPanel toShow() {

		Font font = new Font("微软雅黑", Font.BOLD, 16);
		Font font1 = new Font("微软雅黑", Font.BOLD, 14);
		StoreShowMain = new JPanel();
		StoreShowMain.setLayout(new BorderLayout());
		this.getContentPane();
		// 上面输入框 显示按钮和
		StoreShowUP = new JPanel();

		TableTools st =new TableTools(Sys.l);
		text = new JLabel("商品名称 :");
		text.setFont(font);
		StoreId = new JComboBox(st.toRow());
		StoreId.setFont(font1);
		StoreBtnShow = new JButton("显示全部");
		StoreBtnShow.setFont(font);
		StoreShowUP.add(text);
		StoreShowUP.add(StoreId);
		
		TableTools sname =new TableTools(Sys.l);
		StoreSname=new JComboBox(sname.toRow());
		StoreSname.setFont(font1);
		StoreShowUP.add(text);
		StoreShowUP.add(StoreSname);
	
		
		StoreShowMain.add(StoreShowUP, BorderLayout.NORTH);

		StoreShowEnd=new JPanel();
		//select=new JButton("综合查询");
		//select.setFont(font);
		StoreShowEnd.add(StoreBtnShow);
		//StoreShowEnd.add(select);
		StoreShowMain.add(StoreShowEnd, BorderLayout.SOUTH);
		StoreShowCenter = new JPanel();
		//select =new JButton("综合查询");
		//select.setFont(font);
		StoreShowCenter.setLayout(new BorderLayout());
		
		
	
		TableTools st1=new TableTools(Sys.l);
		
		
		StoreShowdtm = new DefaultTableModel(st1.toData(),st1.toTitle());
		StoreShowtable = new JTable(StoreShowdtm);
		st1.toTable(StoreShowtable);
		StoreShowtable.setFont(font1);
		
		StoreShowjsp = new JScrollPane(StoreShowtable);
		StoreShowCenter.add(StoreShowjsp);
		//按钮变灰
		StoreShowtable.setEnabled(false);
		
		// 按钮事件绑定
		StoreShowAction ssa= new StoreShowAction(this);
		StoreBtnShow.addActionListener(ssa);
		StoreSname.addActionListener(ssa);
		StoreId.addActionListener(ssa);
//		select.addActionListener(ssa);
		
		

		StoreShowMain.add(StoreShowCenter, BorderLayout.CENTER);
		
		
		return StoreShowMain;
	}

	

}
