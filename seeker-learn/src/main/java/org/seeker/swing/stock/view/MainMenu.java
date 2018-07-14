package org.vegetto.swing.stock.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

import org.vegetto.swing.stock.control.MainMenuAction;
import org.vegetto.swing.stock.control.MainMenuDocument;
import org.vegetto.swing.stock.control.MainMenuMouse;
import org.vegetto.swing.stock.util.BackImg;
import org.vegetto.swing.stock.util.Clock;
import org.vegetto.swing.stock.util.ExcelFileFilter;

@SuppressWarnings("serial")
public class MainMenu extends JFrame {
	private String Path, UserName, UserType;

	public String getPath() {
		return Path;
	}

	// 声明组件
	public JFileChooser jfcSave, jfcOpen;
	public JMenuBar MainBar, GoodsMsgBar; // bar
	public JMenu menuUser, menuClient, menuStorehouse, menuProduct,
			menuStoreIn, menuTakeOut, menuGoodsMsg, menuMsg, menuStype,menuHelp,menugy; // 主菜单

	// 右键菜单
	public JMenuItem jmiCheXiao1, jmiJianQian1, jmiFuZhi1, jmiNianTie1,
			jmiChaZhao1, jmiDelete1;
	public JPopupMenu jpm;

	// 用户下拉式菜单
	public JMenuItem UseritemNew, UseritemUpdata, UseritemShow, UseritemExit; // 菜单项
	// 卡片 面板
	public JPanel UserJpDelete, UserJpRegister, UserJpUpdata;

	// 供应商下拉式菜单
	public JMenuItem ClientShow, ClientRegister;
	// 卡片 面板
	public JPanel ClientJpShow, ClientJpRegister;

	// 仓库信息下拉式菜单
	public JMenuItem StorehouseShow, StorehouseRegister;
	// 卡片 面板
	public JPanel StorehouseJpshow, StorehouseJpRegister;

	// 商品下拉式菜单
	public JMenuItem ProductShow, ProductRegister;
	// 卡片 面板
	public JPanel ProductJpShow, ProductJpRegister;

	// 出库下拉式菜单
	public JMenuItem TakeOutShow, TakeOutRegister;
	// 卡片 面板
	public JPanel TakeOutJpShow, TakeOutJpRegister;

	// 入库下拉式菜单
	public JMenuItem StoreInShow, StoreInRegister;
	// 卡片 面板
	public JPanel StoreInJpShow, StoreInJpRegister;

	// 类型下拉式菜单
	public JMenuItem StypeShow, StypeRegister;
	// 卡片 面板
	public JPanel StypeJpShow, StypeJpRegister;

	// 类型下拉式菜单
	public JMenuItem StoreShow;
	// 卡片 面板
	public JPanel StoreJpShow;
	//综合查询
	public JPanel Select;

	// 工具声明
	public JToolBar toolbar, toolsDI;
	public JLabel labelBack, txt;
	public JButton btnNew, btnSearch, btnssearch, btnnNew, btnExit, btnSave,
			btnRefresh, btnPrint, btnNotepad;

	// 卡片声明,布局应设为全局
	public CardLayout card;
	public JPanel NewInfo, NewWage, NewNotepad, jpMain, UserJp;
	public JTextArea jta;

	public File file = null;

	public JScrollPane jsp;

	// 绑定事件声明
	
	public JMenuItem delete, update, copy, paste, cut;


	// 构造方法

	Font font = new Font("微软雅黑", Font.BOLD, 15);
	Font font1 = new Font("微软雅黑", Font.BOLD, 13);

	public MainMenu(String UserName, String UserType) {
		this.UserName = UserName;
		this.UserType = UserType;
		String stype="";
		if(UserType.equals("1")){
			stype="普通用户";
		}else if(UserType.equals("3")){
			stype="管理员用户";
		}else if(UserType.equals("4")){
			stype="超级管理员用户";
		}
		// 窗体属性
		String title = "库存管理系统 : 欢迎  "+stype+" : " + UserName + "  登录 ";

		this.setTitle(title);

		this.setDefaultCloseOperation(3);
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setFont(font);
		this.setIconImage(logo.getImage());//logo
		
		
		
		MyMenu();//主菜单
		MyTool();//工具栏
		Center();//中间的部门
		di();//状态栏
		
		// 多行文本框杨
		jta = new JTextArea();
		jsp = new JScrollPane(jta);// 滚动条
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jta.setLineWrap(true);// 激活自动换行功能
		jta.setWrapStyleWord(true);// 激活断行不断字功能
		jta.setFont(font);
		// 随着屏幕确定字符数
		jta.setTabSize(120);
		NewNotepad.add(jta);
		// 右键菜单
		jpm = new JPopupMenu();		
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
		
		
		
		// 事件绑定

		MainMenuAction bo = new MainMenuAction(this);
		btnNew.addActionListener(bo);
		btnSave.addActionListener(bo);
		btnRefresh.addActionListener(bo);
		btnSearch.addActionListener(bo);
		btnPrint.addActionListener(bo);
		btnnNew.addActionListener(bo);
		btnssearch.addActionListener(bo);
	
		btnNotepad.addActionListener(bo);
		// 仓库
		StorehouseRegister.addActionListener(bo);
		StorehouseShow.addActionListener(bo);
		// 入库
		StoreInRegister.addActionListener(bo);
		StoreInShow.addActionListener(bo);
		// 出库
		TakeOutRegister.addActionListener(bo);
		TakeOutShow.addActionListener(bo);
		// 客户
		ClientRegister.addActionListener(bo);
		ClientShow.addActionListener(bo);
		// 用户
		UseritemUpdata.addActionListener(bo);
		UseritemNew.addActionListener(bo);
		UseritemShow.addActionListener(bo);
		UseritemExit.addActionListener(bo);
		// 材料信息
		ProductRegister.addActionListener(bo);
		ProductShow.addActionListener(bo);

		StypeRegister.addActionListener(bo);
		StypeShow.addActionListener(bo);
		StoreShow.addActionListener(bo);
		
		btnssearch.addActionListener(bo);
		btnExit.addActionListener(bo);
		MainMenuDocument md = new MainMenuDocument(this);
		jta.getDocument().addDocumentListener(md);
		
		
		MainMenuMouse mmm = new MainMenuMouse(this);
		jta.addMouseListener(mmm);
		
		
	

	}

	// 卡片布局
	public void Center() {
		
		// 绘图
		JPanel back = new BackImg().toShow("/images/911.jpg");
		//卡片主菜单
		jpMain = new JPanel();
		card = new CardLayout();
		jpMain.setBackground(Color.BLACK);
		jpMain.setLayout(card);

		this.getContentPane().add(jpMain, BorderLayout.CENTER);
		jpMain.add(back, BorderLayout.CENTER);

		// 新建员工信息
		NewInfo = new JPanel();
		NewInfo.setLayout(new BorderLayout());
		jpMain.add("info", NewInfo);

		// 用户注册
		UserJpRegister = new UserRegister().RegisterMenu();
		jpMain.add("UserJpRegister", UserJpRegister);

		// 用户修改
		UserJpUpdata = new UserUpdata(UserName).UpdataMenu();
		jpMain.add("UserJpUpdata", UserJpUpdata);

		// 用户删除
		UserJpDelete = new UserDelete(UserType).UserDeleteMenu();
		jpMain.add("UserJpDelete", UserJpDelete);

/*		// 材料的添加
		ProductJpRegister = new ProductRegister().RegisterMenu();
		jpMain.add("ProductJpRegister", ProductJpRegister);

		// 材料删除
		ProductJpShow = new ProductShow(UserType).toShow();
		jpMain.add("ProductJpShow", ProductJpShow);

		// 仓库的添加
		StorehouseJpRegister = new StoreHouseRegister().RegisterMenu();
		jpMain.add("StorehouseJpRegister", StorehouseJpRegister);
		// 仓库删除
		StorehouseJpshow = new StoreHouseShow(UserType).toShow();
		jpMain.add("StorehouseJpshow", StorehouseJpshow);

		// 出库添加
		TakeOutJpRegister = new TakeOutRegister().RegisterMenu();
		jpMain.add("TakeOutJpRegister", TakeOutJpRegister);
		// 出库删除
		TakeOutJpShow = new TakeOutShow().toShow();
		jpMain.add("TakeOutJpShow", TakeOutJpShow);

		// 入库添加
		StoreInJpRegister = new StoreInRegister().RegisterMenu();
		jpMain.add("StoreInJpRegister", StoreInJpRegister);
		// 入库删除
		StoreInJpShow = new StoreInShow().toShow();
		jpMain.add("StoreInJpShow", StoreInJpShow);

		// 客户添加
		ClientJpRegister = new ClientRegister().RegisterMenu();
		jpMain.add("ClientJpRegister", ClientJpRegister);
		// 客户删除
		ClientJpShow = new ClientShow(UserType).toShow();
		jpMain.add("ClientJpShow", ClientJpShow);

		// 类型添加
		StypeJpRegister = new StypeRegister().RegisterMenu();
		jpMain.add("StypeJpRegister", StypeJpRegister);
		// 类型删除
		StypeJpShow = new StypeShow(UserType).toShow();
		jpMain.add("StypeJpShow", StypeJpShow);
		
		//库存
		StoreJpShow =  new StoreShow().toShow();
		jpMain.add("StoreJpShow", StoreJpShow);*/
		
		
		
		//综合查询
		Select =new SelectShow().toShow();
		jpMain.add("Select", Select);

		// 工资新建
		NewWage = new JPanel();
		NewWage.setBackground(Color.RED);
		NewWage.setLayout(new BorderLayout());
		jpMain.add("wage", NewWage);

		// 记事本
		NewNotepad = new JPanel();
		jpMain.add("notepad", NewNotepad);
		NewNotepad.setLayout(new BorderLayout());
		
		
	

	}

	// 主菜单
	public void MyMenu() {

		// 声明菜单的容器
		MainBar = new JMenuBar();
		// 添加菜单bar
		this.setJMenuBar(MainBar);
		// 建立主菜单_文件菜单

		menuUser = new JMenu("\t系统 (S)\t");
		menuUser.setFont(font);
		MainBar.add(menuUser);

		menuUser.setMnemonic(KeyEvent.VK_S);
		// 建立菜单项
		UseritemNew = new JMenuItem("添加新用户");
		UseritemNew.setFont(font);
		menuUser.add(UseritemNew);

		UseritemUpdata = new JMenuItem("修改密码");
		UseritemUpdata.setFont(font);
		menuUser.add(UseritemUpdata);

		UseritemShow = new JMenuItem("管理员信息浏览");
		UseritemShow.setFont(font);
		menuUser.add(UseritemShow);

		// 添加分隔线
		menuUser.addSeparator();

		UseritemExit = new JMenuItem("退出系统");
		UseritemExit.setFont(font);
		menuUser.add(UseritemExit);

		// 建立主菜单_格式菜单
		menuMsg = new JMenu("\t基本信息管理 (E)\t");
		// menuFormat.setEnabled(false); //是字体变灰不能被选中
		menuMsg.setFont(font);
		menuMsg.setMnemonic(KeyEvent.VK_E);
		MainBar.add(menuMsg);
		// 仓库信息
		menuStorehouse = new JMenu("仓库管理");
		menuStorehouse.setFont(font);
		menuMsg.add(menuStorehouse);

		StorehouseRegister = new JMenuItem("添加新仓库信息");
		StorehouseRegister.setFont(font);
		menuStorehouse.add(StorehouseRegister);

		StorehouseShow = new JMenuItem("仓库信息浏览");
		StorehouseShow.setFont(font);
		menuStorehouse.add(StorehouseShow);

		// 材料信息
		menuProduct = new JMenu("商品管理");
		menuProduct.setFont(font);
		menuMsg.add(menuProduct);

		ProductRegister = new JMenuItem("添加新商品信息");
		ProductRegister.setFont(font);
		menuProduct.add(ProductRegister);

		ProductShow = new JMenuItem("商品信息浏览");
		ProductShow.setFont(font);
		menuProduct.add(ProductShow);

		// 供应商信息
		menuClient = new JMenu("供应商管理");
		menuClient.setFont(font);
		menuMsg.add(menuClient);

		ClientRegister = new JMenuItem("添加新供应商信息");
		ClientRegister.setFont(font);
		menuClient.add(ClientRegister);

		ClientShow = new JMenuItem("供应商信息浏览");
		ClientShow.setFont(font);
		menuClient.add(ClientShow);
		// 商品类型
		menuStype = new JMenu("商品类型管理");
		menuStype.setFont(font);
		menuMsg.add(menuStype);

		StypeRegister = new JMenuItem("添加商品类型信息");
		StypeRegister.setFont(font);
		StypeRegister.setEnabled(false);
		menuStype.add(StypeRegister);
		StypeShow = new JMenuItem("商品类型信息浏览");
		StypeShow.setFont(font);
		menuStype.add(StypeShow);

		menuGoodsMsg = new JMenu("\t库存管理 (S)\t");
		menuGoodsMsg.setFont(font);
		menuMsg.setMnemonic(KeyEvent.VK_S);
		MainBar.add(menuGoodsMsg);
		
		//库存信息浏览
		StoreShow =new JMenuItem("库存信息浏览");
		StoreShow.setFont(font);
		menuGoodsMsg.add(StoreShow);
		
		
		// 出库列表
		menuTakeOut = new JMenu("出库管理");
		menuTakeOut.setFont(font);
		menuGoodsMsg.add(menuTakeOut);

		TakeOutRegister = new JMenuItem("添加出库信息");
		TakeOutRegister.setFont(font);
		menuTakeOut.add(TakeOutRegister);

		TakeOutShow = new JMenuItem("出库信息浏览");
		TakeOutShow.setFont(font);
		menuTakeOut.add(TakeOutShow);

		menuStoreIn = new JMenu("入库管理");
		menuStoreIn.setFont(font);
		menuGoodsMsg.add(menuStoreIn);

		StoreInRegister = new JMenuItem("添加入库信息");
		StoreInRegister.setFont(font);
		menuStoreIn.add(StoreInRegister);

		StoreInShow = new JMenuItem("入库信息浏览");
		StoreInShow.setFont(font);
		menuStoreIn.add(StoreInShow);
		
		
		menuHelp=new JMenu("帮助(H)");
		menuHelp.setMnemonic(KeyEvent.VK_H);
		menuHelp.setFont(font);
		MainBar.add(menuHelp);
		
		
		menugy=new JMenu("关于(G)");
		menuHelp.setMnemonic(KeyEvent.VK_G);
		menugy.setFont(font);
		MainBar.add(menugy);
		// 权限 显示
		UseritemNew.setEnabled(false);
		UseritemShow.setEnabled(false);
		StorehouseRegister.setEnabled(false);
		ProductRegister.setEnabled(false);
		ClientRegister.setEnabled(false);
		StoreInRegister.setEnabled(false);
		TakeOutRegister.setEnabled(false);
		TakeOutRegister.setEnabled(false);

		if (UserType.equalsIgnoreCase("4")) {
			UseritemShow.setEnabled(true);
			UseritemNew.setEnabled(true);
			StorehouseRegister.setEnabled(true);
			ProductRegister.setEnabled(true);
			ClientRegister.setEnabled(true);
			StoreInRegister.setEnabled(true);
			TakeOutRegister.setEnabled(true);
			TakeOutRegister.setEnabled(true);
			StypeRegister.setEnabled(true);
		}else if (UserType.equalsIgnoreCase("3")) {
			//UseritemShow.setEnabled(true);
			UseritemNew.setEnabled(true);
			StorehouseRegister.setEnabled(true);
			ProductRegister.setEnabled(true);
			ClientRegister.setEnabled(true);
			StoreInRegister.setEnabled(true);
			TakeOutRegister.setEnabled(true);
			TakeOutRegister.setEnabled(true);
			StypeRegister.setEnabled(true);
		}
	}

	// 工具栏
	public void MyTool() {
		toolbar = new JToolBar();
		this.getContentPane().add(toolbar, BorderLayout.NORTH);

//		// 颜色实例化
//		Color color = new Color(188, 255, 14);
//		// 转换背景颜色
//		toolbar.setBackground(color);
		// 新建
		btnNew = new JButton(imgNew);
		toolbar.add(btnNew);
		btnNew.setToolTipText("  记事本新建  ");

		// 保存
		btnSave = new JButton(imgSave);
		toolbar.add(btnSave);
		btnSave.setEnabled(false);
		btnSave.setToolTipText("   记事本保存   ");
		
		// 刷新
		btnRefresh = new JButton(imgRefresh);
		toolbar.add(btnRefresh);
		btnRefresh.setToolTipText("   系统刷新   ");
		toolbar.addSeparator();

		// 替换
		btnSearch = new JButton(imgSearch);
		toolbar.add(btnSearch);
		btnSearch.setToolTipText("   记事本打开   ");
		
		// 打印
		btnPrint = new JButton(imgPrint);
		toolbar.add(btnPrint);
		btnPrint.setEnabled(false);
		btnPrint.setToolTipText("   记事本另存为   ");
		
		// 新建工资
		btnnNew = new JButton(imgnNew);
		toolbar.add(btnnNew);
		btnnNew.setToolTipText("   暂无功能   ");
		btnnNew.setEnabled(false);
		// 查询
		btnssearch = new JButton(imgssearch);
		toolbar.add(btnssearch);
		btnssearch.setToolTipText("   商品状态查询   ");
		
		toolbar.addSeparator();
		// 记事本
		btnNotepad = new JButton(imgNotepad);
		btnNotepad.setToolTipText("   小记事本   ");
		toolbar.add(btnNotepad);
		// 退出
		btnExit = new JButton(imgExit);
		btnExit.setToolTipText("   退出系统   ");
		toolbar.add(btnExit);
		// 关键
		toolbar.setFloatable(false);
	}

	public String getUserName() {
		return UserName;
	}

	public String getUserType() {
		return UserType;
	}

	public void di() {

		toolsDI = new JToolBar();
		txt = new Clock().toShow();
		txt.setFont(font);
		this.getContentPane().add(toolsDI, BorderLayout.SOUTH);
		toolsDI.add(txt);
		// 工具栏不能移动
		toolsDI.setFloatable(false);
	}

	public void PersonMenu() {

	}

	

	// 文件保存
	public String savefile() {

		String FileName = "Vegetto.txt";
		jfcSave = new JFileChooser("e:/");

		// jfcSave.setVisible(true);
		// 设置默认文件名
		jfcSave.setSelectedFile(new File(FileName));

		// 添加excel文件的过滤器
		jfcSave.addChoosableFileFilter(new ExcelFileFilter("txt"));

		// 添加excel文件的过滤器
		jfcSave.addChoosableFileFilter(new ExcelFileFilter("java"));
		// 使用showOpenDialog()方法，显示出打开选择文件的窗口，当选择了某个文件后，或者关闭此窗口那么都会返回一个
		// 整型数值，如果返回的是0，代表已经选择了某个文件。如果返回1代表选择了取消按钮或者直接关闭了窗口
		int result = jfcSave.showSaveDialog(null);
		// JFileChooser.APPROVE_OPTION是个整型常量，代表0。就是说当返回0的值我们才执行相关操作，否则什么也不做。
		if (result == JFileChooser.APPROVE_OPTION) {
			// 获得你选择的文件绝对路径。并输出。当然，我们获得这个路径后还可以做很多的事。
			String path = jfcSave.getSelectedFile().getAbsolutePath();
			System.out.println(path);
			this.Path = path;
			file = new File(path);
			return path;
		} else {
			System.out.println("你已取消并关闭了窗口！");
		}
		return null;

	}

	// 文件打开
	public String openfile() {
		// 首先是创建JFileChooser 对象，里面带个参数，表示默认打开的目录，这里是默认打开当前文件所在的目录
		jfcOpen = new JFileChooser("e:/");
		// 添加excel文件的过滤器
		jfcOpen.addChoosableFileFilter(new ExcelFileFilter("txt"));
		jfcOpen.addChoosableFileFilter(new ExcelFileFilter("java"));

		/*
		 * 使用showOpenDialog()方法，显示出打开选择文件的窗口，当选择了某个文件后，或者关闭此窗口那么都会返回一个
		 * 整型数值，如果返回的是0，代表已经选择了某个文件。如果返回1代表选择了取消按钮或者直接关闭了窗口
		 */
		int result = jfcOpen.showOpenDialog(null);

		// JFileChooser.APPROVE_OPTION是个整型常量，代表0。就是说当返回0的值我们才执行相关操作，否则什么也不做。
		if (result == JFileChooser.APPROVE_OPTION) {
			// 获得你选择的文件绝对路径。并输出。当然，我们获得这个路径后还可以做很多的事。
			String path = jfcOpen.getSelectedFile().getAbsolutePath();
			System.out.println(path);
			this.Path = path;
			file = new File(path);
			return path;
		} else {
			System.out.println("你已取消并关闭了窗口！");
		}
		return null;
	}

	// 右键菜单
	public void Ymenu() {
		// jmiCheXiao1,jmiJianQian1,jmiFuZhi1,jmiNianTie1,jmiDelete1,jmiChaZhao1;
		jmiCheXiao1 = new JMenuItem("        撤销              ");
		jmiJianQian1 = new JMenuItem("        剪切");
		jmiFuZhi1 = new JMenuItem("        复制");
		jmiNianTie1 = new JMenuItem("        粘帖");
		jmiDelete1 = new JMenuItem("        删除");
		jmiChaZhao1 = new JMenuItem("        查找");
		// 右键菜单
		jpm.add(jmiCheXiao1);
		jpm.addSeparator();
		jpm.add(jmiJianQian1);
		jpm.add(jmiFuZhi1);
		jpm.add(jmiNianTie1);
		jpm.add(jmiDelete1);
		jpm.addSeparator();
		jpm.add(jmiChaZhao1);
	}

	// 图片声明
	ImageIcon imgNew = new ImageIcon(this.getClass().getResource(
			"/images/list.gif"));
	ImageIcon imgExit = new ImageIcon(this.getClass().getResource(
			"/images/exit.gif"));
	ImageIcon imgSearch = new ImageIcon(this.getClass().getResource(
			"/images/Search.gif"));
	ImageIcon imgssearch = new ImageIcon(this.getClass().getResource(
			"/images/ssearch.gif"));
	ImageIcon imgnNew = new ImageIcon(this.getClass().getResource(
			"/images/slist.gif"));
	ImageIcon imgback = new ImageIcon(this.getClass().getResource(
			"/images/onepiece008.jpg"));
	ImageIcon logo = new ImageIcon(this.getClass().getResource(
			"/images/logo.gif"));
	ImageIcon imgSave = new ImageIcon(this.getClass().getResource(
			"/images/export.gif"));
	ImageIcon imgRefresh = new ImageIcon(this.getClass().getResource(
			"/images/Refresh.gif"));
	ImageIcon imgPrint = new ImageIcon(this.getClass().getResource(
			"/images/Print.gif"));
	ImageIcon imgNotepad = new ImageIcon(this.getClass().getResource(
			"/images/image01.gif"));
}
