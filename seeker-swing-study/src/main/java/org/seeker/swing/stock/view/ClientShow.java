package org.seeker.swing.stock.view;

import org.seeker.swing.stock.control.ClientShowAction;
import org.seeker.swing.stock.control.ClientShowMouse;
import org.seeker.swing.stock.util.Sys;
import org.seeker.swing.stock.util.TableTools;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


@SuppressWarnings("serial")
public class ClientShow extends JFrame  implements Runnable{
	// 声明
	public JButton ClientShowBtnShow, ClientShowBtnDelete;
	public JLabel text;
	public JComboBox ClientShowCId;
	public JPanel ClientShowMain, ClientShowUP, ClientShowCenter,ClientShowEnd;
	public JTable ClientShowtable;
	public JScrollPane ClientShowjsp;
	public DefaultTableModel ClientShowdtm;
	public JMenuItem delete, update, copy, paste, cut,updateall;
	public JPopupMenu jpm;
	private String UserType;
	
	public ClientShow(String UserType){
		this.UserType= UserType;
		
	}

	public JPanel toShow() {

		Font font = new Font("微软雅黑", Font.BOLD, 16);
		Font font1 = new Font("微软雅黑", Font.BOLD, 14);
		ClientShowMain = new JPanel();
		ClientShowMain.setLayout(new BorderLayout());
		this.getContentPane();
		// 上面输入框 显示按钮和
		ClientShowUP = new JPanel();

		TableTools StClient = new TableTools(Sys.l);

		text = new JLabel("供应商名称 :");
		text.setFont(font);
		ClientShowCId = new JComboBox(StClient.toRow());
		ClientShowCId.setFont(font1);
		
		
		ClientShowBtnShow = new JButton("显示全部");
		ClientShowBtnShow.setFont(font);
//		ClientShowBtnDelete = new JButton("综合查询");
//		ClientShowBtnDelete.setFont(font);
		
	

		ClientShowUP.add(text);
		ClientShowUP.add(ClientShowCId);		
		ClientShowMain.add(ClientShowUP, BorderLayout.NORTH);
		ClientShowEnd=new JPanel();
		ClientShowEnd.add(ClientShowBtnShow);
//		ClientShowEnd.add(ClientShowBtnDelete);
		ClientShowMain.add(ClientShowEnd,BorderLayout.SOUTH);

		// 右键菜单
		jpm = new JPopupMenu();		
		delete = new JMenuItem("        删除        ");		
		delete.setFont(font1);
		update = new JMenuItem("        修改");
		update.setFont(font1);
		updateall=new JMenuItem("     修改全部");
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
		// 得道结果集

		ClientShowCenter = new JPanel();
		ClientShowCenter.setLayout(new BorderLayout());
	
		TableTools st =new TableTools(Sys.l);
		
		
		ClientShowdtm = new DefaultTableModel(st.toData(),st.toTitle());
		ClientShowtable = new JTable(ClientShowdtm){
			//重写显示方法
			public boolean  isCellEditable(int row, int col) {
				if (col == 0) {
					return false;
				} else {
					return true;
				}
			}		
		};
		
		ClientShowtable.setFont(font1);
		
		ClientShowjsp = new JScrollPane(ClientShowtable);
		ClientShowCenter.add(ClientShowjsp);
		//按钮变灰
		ClientShowtable.setEnabled(false);
//		ClientShowBtnDelete.setEnabled(false);
	
		// 按钮事件绑定
	
		ClientShowAction shsa = new ClientShowAction(this);
		ClientShowBtnShow.addActionListener(shsa);
//		ClientShowBtnDelete.addActionListener(shsa);
		ClientShowCId.addActionListener(shsa);
		delete.addActionListener(shsa);
		update.addActionListener(shsa);
		updateall.addActionListener(shsa);
		
		ClientShowMouse csm=new ClientShowMouse(this);
		ClientShowtable.addMouseListener(csm);

		ClientShowMain.add(ClientShowCenter, BorderLayout.CENTER);
		
		if(UserType.equals("4")){
//			ClientShowBtnDelete.setEnabled(true);
			ClientShowtable.setEnabled(true);
			delete.setEnabled(true);
			update.setEnabled(true);
			updateall.setEnabled(true);
		}
		return ClientShowMain;
	}

	@Override
	public void run() {
	
		try {
			Thread.sleep(100);
			System.out.println(111);
			/*ClientSel cs= new ClientSel();
			TableTools st =new TableTools(cs.SelAll());
			ClientShowdtm.setDataVector(st.toData(),st.toTitle());*/
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	

}
