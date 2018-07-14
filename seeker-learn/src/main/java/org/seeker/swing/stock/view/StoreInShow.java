package org.vegetto.swing.stock.view;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.vegetto.swing.stock.control.StoreInShowAction;
import org.vegetto.swing.stock.util.Sys;
import org.vegetto.swing.stock.util.TableTools;

@SuppressWarnings("serial")
public class StoreInShow extends JFrame {
	// 声明
	public JButton StoreInShowBtnShow,select;
	public JLabel text;
	public JComboBox StoreInShowInId, StoreInShowStart, StoreInShowEnd;
	public JPanel StoreInShowMain, StoreInShowUP, StoreInShowCenter,StoreInShow;
	public JTable StoreInShowtable;
	public JScrollPane StoreInShowjsp;
	public DefaultTableModel StoreInShowdtm;
	// 绑定事件声明
	
	public JPanel toShow() {
		StoreInShowMain = new JPanel();
		StoreInShowMain.setLayout(new BorderLayout());
		this.getContentPane();
		// 上面输入框 显示按钮和
		StoreInShowUP = new JPanel();
		

		Font font = new Font("微软雅黑", Font.BOLD, 16);
		Font font1 = new Font("微软雅黑", Font.BOLD, 14);

	
		text = new JLabel("商品名称 :");
		text.setFont(font);
		//商品名称的
		TableTools stProduct = new TableTools(Sys.l);
		StoreInShowInId = new JComboBox(stProduct.toRow());
		StoreInShowInId.setFont(font1);

		StoreInShowBtnShow = new JButton("显示全部");
		StoreInShowBtnShow.setFont(font);

		StoreInShowUP.add(text);
		StoreInShowUP.add(StoreInShowInId);		
	
		

	
		text = new JLabel("距现在 :");
		StoreInShowUP.add(text);
		text.setFont(font);
		String[] array = { "----请输入月份----", "1", "2", "3", "4", "5", "6", "7",
				"8", "9", "10", "11", "12", "更早以前" };
		StoreInShowStart = new JComboBox(array);
		StoreInShowStart.setFont(font1);

		StoreInShowUP.add(StoreInShowStart);
	
		StoreInShowMain.add(StoreInShowUP, BorderLayout.NORTH);
		StoreInShow=new JPanel();
		select =new JButton("综合查询");
		select.setFont(font);
		
		
		StoreInShow.add(StoreInShowBtnShow);
		text =new JLabel("                                                                    ");
		StoreInShow.add(text);
		StoreInShow.add(select);
		StoreInShowMain.add(StoreInShow, BorderLayout.SOUTH);
		StoreInShowCenter = new JPanel();
		StoreInShowCenter.setLayout(new BorderLayout());
		//向表中传入数据
		
		TableTools st = new TableTools(Sys.l);
		StoreInShowdtm = new DefaultTableModel(st.toData(), st.toTitle());
		StoreInShowtable = new JTable(StoreInShowdtm);
		StoreInShowtable.setFont(font1);
		StoreInShowjsp = new JScrollPane(StoreInShowtable);
		StoreInShowCenter.add(StoreInShowjsp);
		StoreInShowtable.setEnabled(false);
		st.toTable(StoreInShowtable);//自动非陪列宽
		// 按钮事件绑定
		StoreInShowAction psa = new StoreInShowAction(this);
		StoreInShowBtnShow.addActionListener(psa);
		StoreInShowInId.addActionListener(psa);
		StoreInShowStart.addActionListener(psa);
		select.addActionListener(psa);
		StoreInShowMain.add(StoreInShowCenter, BorderLayout.CENTER);
		return StoreInShowMain;
	}

}
