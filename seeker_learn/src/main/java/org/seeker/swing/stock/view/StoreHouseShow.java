package org.vegetto.swing.stock.view;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.vegetto.swing.stock.control.StoreHouseMouse;
import org.vegetto.swing.stock.control.StoreHouseShowAction;
import org.vegetto.swing.stock.util.Sys;
import org.vegetto.swing.stock.util.TableTools;

@SuppressWarnings("serial")
public class StoreHouseShow extends JFrame {
	// 声明
	public JButton StoreHouseBtnShow, StoreHouseBtnDelete;
	public JLabel text;
	public JComboBox StoreHouseSname,StoreHouseStype;
	public JPanel StoreHouseMain, StoreHouseUP, StoreHouseCenter,StoreHouseEnd;
	public JTable StoreHousetable;
	public JScrollPane StoreHousejsp;
	public DefaultTableModel StoreHousedtm;

	public JMenuItem delete, update, copy, paste, cut, updateall;
	public JPopupMenu jpm;
	private String UserType;
	
	Font font = new Font("微软雅黑", Font.BOLD, 16);
	Font font1 = new Font("微软雅黑", Font.BOLD, 14);

	
	public StoreHouseShow(final String UserType) {
		this.UserType = UserType;

	}

	public JPanel toShow() {
		StoreHouseMain = new JPanel();
		StoreHouseMain.setLayout(new BorderLayout());
		this.getContentPane();
		
		StoreHouseUP = new JPanel();
		

		text = new JLabel("仓库名称 :");
		text.setFont(font);		
		//仓库名称下拉式菜单
		TableTools StoreHouse = new TableTools(Sys.l);
		StoreHouseSname = new JComboBox(StoreHouse.toRow());
		StoreHouseSname.setFont(font1);
		StoreHouseUP.add(text);
		StoreHouseUP.add(StoreHouseSname);
		
		text = new JLabel("商品类型:");
		text.setFont(font);		
		//商品类型的下拉式菜单
		TableTools Stype = new TableTools(Sys.l);
		StoreHouseStype=new JComboBox(Stype.toRow());
		StoreHouseStype.setFont(font1);
		StoreHouseUP.add(text);
		StoreHouseUP.add(StoreHouseStype);
		
		StoreHouseMain.add(StoreHouseUP, BorderLayout.NORTH);
		
		StoreHouseEnd=new JPanel();		
		StoreHouseBtnShow = new JButton("显示全部");
		StoreHouseBtnShow.setFont(font);
		StoreHouseEnd.add(StoreHouseBtnShow);
		StoreHouseMain.add(StoreHouseEnd, BorderLayout.SOUTH);

		// 右键菜单
		jpm = new JPopupMenu();
		delete = new JMenuItem("        删除        ");
		delete.setFont(font1);
		update = new JMenuItem("        修改");
		update.setFont(font1);
		updateall = new JMenuItem("     修改全部");
		updateall.setFont(font1);
		copy = new JMenuItem("        复制");
		copy.setFont(font1);
		paste = new JMenuItem("        粘帖");
		paste.setFont(font1);
		cut = new JMenuItem("        剪切");
		cut.setFont(font1);

		delete.setEnabled(false);
		update.setEnabled(false);
		updateall.setEnabled(false);
		copy.setEnabled(false);
		paste.setEnabled(false);
		cut.setEnabled(false);
		// 右键菜单
		jpm.add(cut);
		jpm.addSeparator();
		jpm.add(delete);
		jpm.add(update);
		jpm.add(updateall);
		jpm.addSeparator();
		jpm.add(copy);
		jpm.add(paste);

		StoreHouseCenter = new JPanel();
		StoreHouseCenter.setLayout(new BorderLayout());

		// 查询结果
		TableTools st = new TableTools(Sys.l);
		// 结果集放进dtm中
		StoreHousedtm = new DefaultTableModel(st.toData(), st.toTitle());

		StoreHousetable = new JTable(StoreHousedtm) {
			// 重写显示方法
			public boolean isCellEditable(int row, int col) {
				if (col == 0 || col == 1) {
					return false;
				} else {
					return true;
				}
			}
		};

		StoreHousetable.setFont(font1);
		StoreHousejsp = new JScrollPane(StoreHousetable);
		StoreHouseCenter.add(StoreHousejsp);

		// 按钮变灰
		StoreHousetable.setEnabled(false);


		if (UserType.equalsIgnoreCase("4")) {
			StoreHousetable.setEnabled(true);
			
			delete.setEnabled(true);
			update.setEnabled(true);
			copy.setEnabled(true);
			paste.setEnabled(true);
			cut.setEnabled(true);
			updateall.setEnabled(true);
		}

		StoreHouseMain.add(StoreHouseCenter, BorderLayout.CENTER);

		// 绑定事件声明
		StoreHouseShowAction shsa = new StoreHouseShowAction(this);
		StoreHouseBtnShow.addActionListener(shsa);
		StoreHouseSname.addActionListener(shsa);
		delete.addActionListener(shsa);
		update.addActionListener(shsa);
		updateall.addActionListener(shsa);
		StoreHouseStype.addActionListener(shsa);
		
		//鼠标监听事件
		StoreHousetable.addMouseListener(new StoreHouseMouse(this));
		//sel.getDbc().closeConnection();
		//ss.getDbc().closeConnection();
		return StoreHouseMain;
	}

}
