package org.seeker.swing.stock.view;

import org.seeker.swing.stock.control.UserDeleteAction;
import org.seeker.swing.stock.control.UserDeleteMouse;
import org.seeker.swing.stock.util.Sys;
import org.seeker.swing.stock.util.TableTools;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

@SuppressWarnings("serial")
public class UserDelete extends JFrame {

	public JPopupMenu jpm;
	public JMenuItem delete, update, copy, paste, cut;
	// 声明
	public JButton UserDeleteBtnShow, UserDeleteBtnDelete;
	public JLabel text;
	public JComboBox UserDeleteTextId;
	public JPanel UserDeleteMain, UserDeleteUP, UserDeleteCenter;
	public JTable UserDeletetable;
	public JScrollPane UserDeletejsp;
	public DefaultTableModel UserDeletedtm;
	private String usertype;
	public UserDelete(final String usertype){
		this.usertype=usertype;
	}

	public JPanel UserDeleteMenu() {
		
		
		UserDeleteMain = new JPanel();
		UserDeleteMain.setLayout(new BorderLayout());
		this.getContentPane();

		Font font = new Font("微软雅黑", Font.BOLD, 16);
		Font font1 = new Font("微软雅黑", Font.BOLD, 14);
		// 上面输入框 显示按钮和
		UserDeleteUP = new JPanel();

		text = new JLabel("管理员帐号:");
		text.setFont(font);
		TableTools tt =new TableTools(Sys.l);
		UserDeleteTextId = new JComboBox(tt.toRow());
		UserDeleteTextId.setFont(font1);
		UserDeleteBtnShow = new JButton("显示全部");
		UserDeleteBtnShow.setFont(font1);
		UserDeleteBtnDelete = new JButton("修改全部");
		UserDeleteBtnDelete.setFont(font1);
		UserDeleteBtnDelete.setEnabled(false);
		UserDeleteUP.add(text);
		UserDeleteUP.add(UserDeleteTextId);
		UserDeleteUP.add(UserDeleteBtnShow);
		UserDeleteUP.add(UserDeleteBtnDelete);
		UserDeleteMain.add(UserDeleteUP, BorderLayout.NORTH);

		// 中间显示表格

		// 右键菜单

		jpm = new JPopupMenu();
		// jmiCheXiao1,jmiJianQian1,jmiFuZhi1,jmiNianTie1,jmiDelete1,jmiChaZhao1;
		delete = new JMenuItem("        删除              ");
		delete.setFont(font1);
		update = new JMenuItem("        修改");
		update.setFont(font1);
		copy = new JMenuItem("        复制");
		copy.setFont(font1);
		paste = new JMenuItem("        粘帖");
		paste.setFont(font1);
		cut = new JMenuItem("        剪切");
		cut.setFont(font1);
		
		delete.setEnabled(false);
		update.setEnabled(false);
		copy.setEnabled(false);
		paste.setEnabled(false);
		cut.setEnabled(false);

		// 右键菜单
		jpm.add(cut);
		jpm.addSeparator();
		jpm.add(delete);
		jpm.add(update);
		jpm.addSeparator();
		jpm.add(copy);
		jpm.add(paste);

		// 得道结果集

		UserDeleteCenter = new JPanel();
		UserDeleteCenter.setLayout(new BorderLayout());

		
		TableTools st = new TableTools(Sys.l);

		UserDeletedtm = new DefaultTableModel(st.toData(), st.toTitle());
		UserDeletetable = new JTable(UserDeletedtm){
			//重写显示方法
			public boolean  isCellEditable(int row, int col) {
				if (col == 0||col==1) {
					return false;
				} else {
					return true;
				}
			}		
		};
		
		UserDeletetable.setFont(font1);
		
		UserDeletejsp = new JScrollPane(UserDeletetable) ;
		UserDeleteCenter.add(UserDeletejsp);

		UserDeleteMain.add(UserDeleteCenter, BorderLayout.CENTER);
		UserDeleteMouse udm = new UserDeleteMouse(this);
		UserDeletetable.addMouseListener(udm);
		// 绑定事件声明
		UserDeleteAction da = new UserDeleteAction(this);
		// 按钮事件绑定
		UserDeleteBtnShow.addActionListener(da);
		UserDeleteBtnDelete.addActionListener(da);
		UserDeleteTextId.addActionListener(da);
		delete.addActionListener(da);
		update.addActionListener(da);
		
		
		if(usertype.equals("4")){
			delete.setEnabled(true);
			update.setEnabled(true);
			copy.setEnabled(true);
			paste.setEnabled(true);
			cut.setEnabled(true);
			UserDeleteBtnDelete.setEnabled(true);
		}
		return UserDeleteMain;
	}

}
