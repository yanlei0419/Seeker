package org.vegetto.swing.stock.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

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

import org.vegetto.swing.stock.control.ProductMouse;
import org.vegetto.swing.stock.control.ProductShowAction;
import org.vegetto.swing.stock.util.Sys;
import org.vegetto.swing.stock.util.TableTools;
@SuppressWarnings("serial")
public class ProductShow extends JFrame {
	// 声明
	public JButton ProductShowBtnShow, ProductShowBtnSelect;
	public JLabel text;
	public JComboBox ProductShowpPname,ProductShowStype,ProductShowCname;
	public JPanel ProductShowMain, ProductShowUP, ProductShowCenter,ProductShowEnd;
	public JTable ProductShowtable;
	public JScrollPane ProductShowjsp;
	public DefaultTableModel ProductShowdtm;
	public JMenuItem delete, update, copy, paste, cut, updateall;
	public JPopupMenu jpm;
	private String UserType;
	
	public ProductShow(final String UserType) {
		this.UserType = UserType;
	}

	public JPanel toShow() {

		Font font = new Font("微软雅黑", Font.BOLD, 16);
		Font font1 = new Font("微软雅黑", Font.BOLD, 14);
		ProductShowMain = new JPanel();
		ProductShowMain.setLayout(new BorderLayout());
		this.getContentPane();
		// 上面输入框 显示按钮和
		ProductShowUP = new JPanel();
		GridLayout grid = new GridLayout(2, 1);
		ProductShowUP.setLayout(grid);
		
		JPanel one =new JPanel();
		
		text = new JLabel("商品名称 : ");
		text.setFont(font);
		//商品名称下拉式菜单		
		TableTools StProduct = new TableTools(Sys.l);
		ProductShowpPname = new JComboBox(StProduct.toRow());
		ProductShowpPname.setFont(font1);
		one.add(text);
		one.add(ProductShowpPname);	
		
		JPanel two =new JPanel();
		text = new JLabel("商品类型 : ");
		text.setFont(font);
		//商品类型下拉式菜单		
		TableTools st = new TableTools(Sys.l);
		ProductShowStype=new JComboBox(st.toRow());
		ProductShowStype.setFont(font1);
		two.add(text);		
		two.add(ProductShowStype);	
		text = new JLabel("供应商 : ");
		text.setFont(font);
		//供应商下拉式菜单
		TableTools stcname = new TableTools(Sys.l);
		ProductShowCname=new JComboBox(stcname.toRow());
		ProductShowCname.setFont(font1);
		two.add(text);		
		two.add(ProductShowCname);	
		ProductShowUP.add(one);
		ProductShowUP.add(two);
		ProductShowMain.add(ProductShowUP, BorderLayout.NORTH);
		
		//最下面的按钮
		ProductShowEnd=new JPanel();
		ProductShowBtnShow = new JButton("全部显示");
		ProductShowBtnShow.setFont(font);
		text =new JLabel("                                                                   ");
		ProductShowBtnSelect = new JButton("综合查询");
		ProductShowBtnSelect.setFont(font);
		ProductShowEnd.add(ProductShowBtnShow);
		ProductShowEnd.add(text);
		ProductShowEnd.add(ProductShowBtnSelect);
		ProductShowMain.add(ProductShowEnd, BorderLayout.SOUTH);

		// 中间显示表格
		ProductShowCenter = new JPanel();
		ProductShowCenter.setLayout(new BorderLayout());
		
		TableTools table = new TableTools(Sys.l);
		ProductShowdtm = new DefaultTableModel(table.toData(), table.toTitle());
		
		ProductShowtable = new JTable(ProductShowdtm) {
			// 重写显示方法
			public boolean isCellEditable(int row, int col) {
				if (col == 0 || col == 1 || col == 6) {
					return false;
				} else {
					return true;
				}
			}
		};
		ProductShowtable.setFont(font1);		
		
		ProductShowjsp = new JScrollPane(ProductShowtable);
		ProductShowCenter.add(ProductShowjsp);
		// 按钮变灰
		ProductShowtable.setEnabled(false);
	
		ProductShowMain.add(ProductShowCenter, BorderLayout.CENTER);

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
		//按钮不能点击
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

		// 按钮时间绑定
		ProductShowAction psa = new ProductShowAction(this);
		ProductShowBtnShow.addActionListener(psa);
		ProductShowBtnSelect.addActionListener(psa);
		ProductShowCname.addActionListener(psa);
		ProductShowpPname.addActionListener(psa);
		delete.addActionListener(psa);
		update.addActionListener(psa);	
		updateall.addActionListener(psa);
		ProductShowStype.addActionListener(psa);
		//鼠标监听事件
		ProductShowtable.addMouseListener(new ProductMouse(this));

		if (UserType.equals("4")) {
			ProductShowtable.setEnabled(true);
			delete.setEnabled(true);
			update.setEnabled(true);
			copy.setEnabled(true);
			paste.setEnabled(true);
			cut.setEnabled(true);
			updateall.setEnabled(true);
		}
		return ProductShowMain;
	}

}
