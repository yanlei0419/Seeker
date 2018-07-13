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

import org.vegetto.swing.stock.control.StypeMouse;
import org.vegetto.swing.stock.control.StypeShowAction;
import org.vegetto.swing.stock.util.Sys;
import org.vegetto.swing.stock.util.TableTools;

@SuppressWarnings("serial")
public class StypeShow extends JFrame {
	// 声明
	public JButton StypeBtnShow, StypeBtnDelete;
	public JLabel text;
	public JComboBox StypeName;
	public JPanel StypeMain, StypeUP, StypeCenter,StypeEnd;
	public JTable Stypetable;
	public JScrollPane Stypejsp;
	public DefaultTableModel Stypedtm;
	public JMenuItem delete, update, copy, paste, cut,updateall;
	public JPopupMenu jpm;
	private String UserType;
	
	public StypeShow(String UserType) {
		this.UserType = UserType;

	}

	public JPanel toShow() {
		StypeMain = new JPanel();
		StypeMain.setLayout(new BorderLayout());
		this.getContentPane();
		// 上面输入框 显示按钮和
		StypeUP = new JPanel();
		Font font = new Font("微软雅黑", Font.BOLD, 16);
		Font font1 = new Font("微软雅黑", Font.BOLD, 14);

		text = new JLabel("商品类型 :");
		text.setFont(font);
		TableTools StStype = new TableTools(Sys.l);

		StypeName = new JComboBox(StStype.toRow());
		StypeName.setFont(font1);
		StypeUP.add(text);
		StypeUP.add(StypeName);
	
		//StypeUP.add(StypeBtnDelete);
		StypeMain.add(StypeUP, BorderLayout.NORTH);
		
		StypeEnd=new JPanel();
		StypeBtnShow = new JButton("显示全部");
		StypeBtnShow.setFont(font1);
		StypeEnd.add(StypeBtnShow);
		//StypeBtnDelete = new JButton("综合查询");
		//StypeBtnDelete.setFont(font1);
		StypeMain.add(StypeEnd, BorderLayout.SOUTH);
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

		StypeCenter = new JPanel();
		StypeCenter.setLayout(new BorderLayout());

		// 查询结果
		TableTools st = new TableTools(Sys.l);
		// 结果集放进dtm中
		Stypedtm = new DefaultTableModel(st.toData(), st.toTitle());

		Stypetable = new JTable(Stypedtm) {
			// 重写显示方法
			public boolean isCellEditable(int row, int col) {
				if (col == 0) {
					return false;
				} else {
					return true;
				}
			}
		};
		

		Stypetable.setFont(font1);
		Stypejsp = new JScrollPane(Stypetable);
		StypeCenter.add(Stypejsp);

		// 按钮变灰
		Stypetable.setEnabled(false);


		if (UserType.equalsIgnoreCase("4")) {
			Stypetable.setEnabled(true);

			updateall.setEnabled(true);
			delete.setEnabled(true);
			update.setEnabled(true);
			copy.setEnabled(true);
			paste.setEnabled(true);
			cut.setEnabled(true);
		}

		StypeMain.add(StypeCenter, BorderLayout.CENTER);

		// 按钮事件绑定
		// 绑定事件声明
		StypeShowAction shsa = new StypeShowAction(this);
		StypeBtnShow.addActionListener(shsa);

		StypeName.addActionListener(shsa);
		delete.addActionListener(shsa);
		update.addActionListener(shsa);
		updateall.addActionListener(shsa);
		StypeMouse shm =new StypeMouse(this);
		Stypetable.addMouseListener(shm);
		
		return StypeMain;
	}

}
