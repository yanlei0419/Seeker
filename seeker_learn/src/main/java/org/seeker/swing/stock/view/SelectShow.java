package org.vegetto.swing.stock.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.vegetto.swing.stock.control.SelectShowAction;
import org.vegetto.swing.stock.util.Sys;
import org.vegetto.swing.stock.util.TableTools;

@SuppressWarnings("serial")
public class SelectShow extends JFrame {
	// 声明
	public JButton SelectShowBtnShow,SelectBtn;
	public JLabel text;
	public JComboBox SelectShowtId, SelectShowStart ,SelectStype;
	public JPanel SelectShowMain, SelectShowUP, SelectShowCenter,SelectShowEnd;
	public JTable SelectShowtable;
	public JScrollPane SelectShowjsp;
	public DefaultTableModel SelectShowdtm;

	public JPanel toShow() {
		SelectShowMain = new JPanel();
		SelectShowMain.setLayout(new BorderLayout());
		this.getContentPane();
		Font font = new Font("微软雅黑", Font.BOLD, 16);
		Font font1 = new Font("微软雅黑", Font.BOLD, 14);
		// 上面输入框 显示按钮和
		SelectShowUP = new JPanel();
		GridLayout grid = new GridLayout(2, 1);
		SelectShowUP.setLayout(grid);

		text = new JLabel("商品状态 :");
		text.setFont(font);

		String[] array = { "----请选择----", "缺货状态", "正常状态", "溢出状态", "存货已置空" };

		SelectShowtId = new JComboBox(array);
		SelectShowtId.setFont(font1);
	
		JPanel one = new JPanel();

		one.add(text);
		one.add(SelectShowtId);
		
		text = new JLabel("商品类型 :");
		text.setFont(font);

		TableTools tt=new TableTools(Sys.l);
		SelectStype = new JComboBox(tt.toRow());
		SelectStype.setFont(font1);
		SelectBtn=new JButton("综合查询");
		SelectBtn.setFont(font);
		one.add(text);
		one.add(SelectStype);
		//one.add(SelectBtn);
		
	
		
		JPanel two = new JPanel();

		text = new JLabel("商品名称 :");
		text.setFont(font);
		two.add(text);

		
		SelectShowBtnShow = new JButton("显示全部");
		SelectShowBtnShow.setFont(font);
		
		TableTools st = new TableTools(Sys.l);
		SelectShowStart = new JComboBox(st.toRow());
		SelectShowStart.setFont(font1);

		text.setFont(font);

		two.add(SelectShowStart);
	//	two.add(SelectShowBtnShow);

		SelectShowUP.add(one);
		SelectShowUP.add(two);
		
		SelectShowMain.add(SelectShowUP, BorderLayout.NORTH);
		
		text = new JLabel("                                                            ");
		text.setFont(font);
		SelectShowEnd=new JPanel();
		SelectShowEnd.add(SelectShowBtnShow);
		SelectShowEnd.add(text);
		SelectShowEnd.add(SelectBtn);
		SelectShowMain.add(SelectShowEnd, BorderLayout.SOUTH);
		
		SelectShowCenter = new JPanel();
		SelectShowCenter.setLayout(new BorderLayout());

		TableTools st1 = new TableTools(Sys.l);

		SelectShowdtm = new DefaultTableModel(st1.toData(), st1.toTitle());
		SelectShowtable = new JTable(SelectShowdtm);
		SelectShowtable.setFont(font1);
		SelectShowjsp = new JScrollPane(SelectShowtable);
		SelectShowCenter.add(SelectShowjsp);
		SelectShowtable.setEnabled(false);
		st1.toTable(SelectShowtable);
		SelectShowMain.add(SelectShowCenter, BorderLayout.CENTER);
		// 绑定事件声明
		SelectShowAction psa = new SelectShowAction(this);
		SelectShowBtnShow.addActionListener(psa);
		SelectShowStart.addActionListener(psa);
		SelectShowtId.addActionListener(psa);
		SelectStype.addActionListener(psa);
		SelectBtn.addActionListener(psa);
		return SelectShowMain;
	}

}
