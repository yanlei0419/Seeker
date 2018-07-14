package org.seeker.swing.stock.control;

import org.seeker.swing.stock.util.CopyTxt;
import org.seeker.swing.stock.util.ReadTxt;
import org.seeker.swing.stock.view.MainMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainMenuAction implements ActionListener {
	MainMenu mm;
	private String UserName, UserType;

	public MainMenuAction(MainMenu mm) {
		this.mm = mm;
		this.UserName = mm.getUserName();
		this.UserType = mm.getUserType();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mm.btnNew) {// 记事本新建
			mm.card.show(mm.jpMain, "notepad");
			System.out.println("新建");
			mm.jta.setText("");
			mm.card.show(mm.jpMain, "notepad");

			// mm.card.show(mm.jpMain, "info");
		} else if (e.getSource() == mm.btnnNew) {

			mm.card.show(mm.jpMain, "wage");

		} else if (e.getSource() == mm.UseritemExit) {

			System.exit(0);

		} else if (e.getSource() == mm.UseritemNew) {// 用户注册

			mm.card.show(mm.jpMain, "UserJpRegister");

		} else if (e.getSource() == mm.UseritemShow) {// 管理员删除

			mm.card.show(mm.jpMain, "UserJpDelete");

		} else if (e.getSource() == mm.UseritemUpdata) {// 用户修改

			mm.card.show(mm.jpMain, "UserJpUpdata");

		} 
		/*else if (e.getSource() == mm.ProductRegister) {// 材料信息添加

			mm.card.show(mm.jpMain, "ProductJpRegister");

		} else if (e.getSource() == mm.ProductShow) {// 材料信息删除
			// 将表中的数据全部更新到数据库

			mm.card.show(mm.jpMain, "ProductJpShow");

		} else if (e.getSource() == mm.StorehouseRegister) {// 仓库信息添加

			mm.card.show(mm.jpMain, "StorehouseJpRegister");

		} else if (e.getSource() == mm.StorehouseShow) {// 仓库信息删除

			mm.card.show(mm.jpMain, "StorehouseJpshow");

		} else if (e.getSource() == mm.StoreInRegister) {// 入库信息添加

			mm.card.show(mm.jpMain, "StoreInJpRegister");

		} else if (e.getSource() == mm.StoreInShow) {// 入库信息删除

			mm.card.show(mm.jpMain, "StoreInJpShow");

		} else if (e.getSource() == mm.TakeOutRegister) {// 出库信息添加

			mm.card.show(mm.jpMain, "TakeOutJpRegister");

		} else if (e.getSource() == mm.TakeOutShow) {// 出库信息删除

			mm.card.show(mm.jpMain, "TakeOutJpShow");

		} else if (e.getSource() == mm.ClientRegister) {// 客户信息添加

			mm.card.show(mm.jpMain, "ClientJpRegister");

		} else if (e.getSource() == mm.ClientShow) {// 客户信息删除
			Thread t1 =new Thread(new ClientShow(UserType));
			t1.start();
			mm.card.show(mm.jpMain, "ClientJpShow");

		} else if (e.getSource() == mm.StypeRegister) {// 类型添加

			mm.card.show(mm.jpMain, "StypeJpRegister");

		} else if (e.getSource() == mm.StypeShow) {// 类型删除

			mm.card.show(mm.jpMain, "StypeJpShow");

		} else if (e.getSource() == mm.StoreShow) {
			// mm.StoreJpShow = new StoreShow(UserType).toShow();
			mm.card.show(mm.jpMain, "StoreJpShow");

		} */
		else if (e.getSource() == mm.btnssearch) {

			mm.card.show(mm.jpMain, "Select");

		} else if (e.getSource() == mm.btnNotepad) {// 记事本

			mm.card.show(mm.jpMain, "notepad");

		} else if (e.getSource() == mm.btnSave) {// 存储

			CopyTxt ct = new CopyTxt(mm);
			if (mm.file == null) { // 如果为空
				ct.txtSave(mm.jta.getText(), mm.savefile());
			} else {
				ct.txtSave(mm.jta.getText(), mm.getPath());
			}
			// 改变标题信息
			// mm.setTitle("记事本 -"+mm.Path);
		} else if (e.getSource() == mm.btnSearch) {// 打开
			mm.card.show(mm.jpMain, "notepad");
			ReadTxt rt = new ReadTxt(mm);
			rt.txtOpen(mm.openfile());
			// 改变标题信息
			// mm.setTitle("记事本 -"+mm.Path );

			mm.btnSave.setEnabled(false);// 不能点击
			mm.btnPrint.setEnabled(false);// 不能点击

		} else if (e.getSource() == mm.btnPrint) {// 另存为

			CopyTxt ct = new CopyTxt(mm);
			ct.txtSave(mm.jta.getText(), mm.savefile());
			System.out.println("另存储为");

		} else if (e.getSource() == mm.btnRefresh) {
			System.out.println("刷新完成");
			mm.dispose();
			new MainMenu(UserName, UserType).setVisible(true);
		} else if (e.getSource() == mm.btnExit) {//退出
			System.exit(0);
		}

	}
}
