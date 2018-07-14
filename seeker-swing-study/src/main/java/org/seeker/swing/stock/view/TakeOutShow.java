package org.seeker.swing.stock.view;

import org.seeker.swing.stock.control.TakeOutShowAction;
import org.seeker.swing.stock.util.Sys;
import org.seeker.swing.stock.util.TableTools;

import javax.swing.table.DefaultTableModel;


@SuppressWarnings("serial")
public class TakeOutShow extends JFrame {
	// 声明
	public JButton TakeOutShowBtnShow,Select;
	public JLabel text;
	public JComboBox TakeOutShowtId, TakeOutShowStart;
	public JPanel TakeOutShowMain, TakeOutShowUP, TakeOutShowCenter,TakeOutShowEnd;
	public JTable TakeOutShowtable;
	public JScrollPane TakeOutShowjsp;
	public DefaultTableModel TakeOutShowdtm;
	public JPanel toShow() {
		TakeOutShowMain = new JPanel();
		TakeOutShowMain.setLayout(new BorderLayout());
		this.getContentPane();
		Font font = new Font("微软雅黑", Font.BOLD, 16);
		Font font1 = new Font("微软雅黑", Font.BOLD, 14);
		// 上面输入框 显示按钮和
		TakeOutShowUP = new JPanel();

		text = new JLabel("商品名称:");
		text.setFont(font);
		//向下拉式菜单填写数据
		TableTools st =new TableTools(Sys.l);
		TakeOutShowtId = new JComboBox(st.toRow());
		TakeOutShowtId.setFont(font1);
		TakeOutShowBtnShow = new JButton("显示全部");
		TakeOutShowBtnShow.setFont(font);

		TakeOutShowUP.add(text);
		TakeOutShowUP.add(TakeOutShowtId);
		text = new JLabel("距现在 :");
		text.setFont(font);
		TakeOutShowUP.add(text);
		String[] array = { "----请输入月份----", "1", "2", "3", "4", "5", "6", "7",
				"8", "9", "10", "11", "12", "更早以前" };
		TakeOutShowStart = new JComboBox(array);
		TakeOutShowStart.setFont(font1);
		text.setFont(font);
		TakeOutShowUP.add(TakeOutShowStart);
		
		TakeOutShowMain.add(TakeOutShowUP, BorderLayout.NORTH);
		Select =new JButton("综合查询");
		Select.setFont(font);
		TakeOutShowEnd=new JPanel();
		TakeOutShowEnd.add(TakeOutShowBtnShow);
		text =new JLabel("                                                                    ");
		TakeOutShowEnd.add(text);
		TakeOutShowEnd.add(Select);
		TakeOutShowMain.add(TakeOutShowEnd, BorderLayout.SOUTH);

		TakeOutShowCenter = new JPanel();
		TakeOutShowCenter.setLayout(new BorderLayout());

		TableTools st1 = new TableTools(Sys.l);

		TakeOutShowdtm = new DefaultTableModel(st1.toData(), st1.toTitle());
		TakeOutShowtable = new JTable(TakeOutShowdtm);
		TakeOutShowtable.setFont(font1);
		TakeOutShowjsp = new JScrollPane(TakeOutShowtable);
		TakeOutShowCenter.add(TakeOutShowjsp);
		TakeOutShowtable.setEnabled(false);
		st.toTable(TakeOutShowtable);
		TakeOutShowMain.add(TakeOutShowCenter, BorderLayout.CENTER);
		
		// 绑定事件声明
		TakeOutShowAction psa = new TakeOutShowAction(this);
		TakeOutShowBtnShow.addActionListener(psa);
		TakeOutShowStart.addActionListener(psa);
		TakeOutShowtId.addActionListener(psa);
		Select.addActionListener(psa);
		return TakeOutShowMain;
	}

}
