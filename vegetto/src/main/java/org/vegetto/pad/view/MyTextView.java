package org.vegetto.pad.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileSystemView;

import org.vegetto.pad.control.MyAction;
import org.vegetto.pad.control.MyDocument;
import org.vegetto.pad.control.MyMouse;

@SuppressWarnings("serial")
public class MyTextView extends JFrame {
	// 声明组建
	public String Path;
	public JMenuBar jMenuBar;
	public JMenu txtFile, txtNew, txtGeshi, txtChakan, txtHelp;
	public JMenuItem itemNew, itemOpen, itemSave, itemSaveAs, itemYeMianSheZhi, itemPrintf, itemExit;
	public JMenuItem jmiCheXiao, jmiJianQian, jmiFuZhi, jmiNianTie, jmiDelete, jmiChaZhao;
	public JTextArea jta;
	public JMenuItem jmiCheXiao1, jmiJianQian1, jmiFuZhi1, jmiNianTie1, jmiDelete1, jmiChaZhao1;

	public JPopupMenu jpm;
	// 文件路径
	public JFileChooser jfcOpen, jfcSave;
	// 图片声明
	ImageIcon logo = new ImageIcon(this.getClass().getResource("/images/image01.jpg"));

	// 文件声明
	public File file = null;
	private FileSystemView fsv = FileSystemView.getFileSystemView();

	// 构造方法
	public MyTextView() {
		// 窗体属性
		this.setTitle("记事本");
		this.setSize(600, 500);
		this.setDefaultCloseOperation(2);
		this.setLocationRelativeTo(null);
		this.setIconImage(logo.getImage());
		Font font = new Font("Porky's", 0, 20);
		this.setFont(font);

		// 菜单
		MyJmenu();
		Ymenu();

		// 多行文本框杨
		jta = new JTextArea();
		JScrollPane jsp = new JScrollPane(jta);// 滚动条
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.getContentPane().add(jsp, BorderLayout.CENTER);
		jta.setLineWrap(true);// 激活自动换行功能
		jta.setWrapStyleWord(true);// 激活断行不断字功能

		// jta.setFont(font);
		// 随着屏幕确定字符数
		jta.setTabSize(120);

		// 时间绑定
		MyAction ma = new MyAction(this);
		itemNew.addActionListener(ma);
		itemOpen.addActionListener(ma);
		itemSave.addActionListener(ma);
		itemSaveAs.addActionListener(ma);
		itemExit.addActionListener(ma);

		// 鼠标监听事件 多行文本框
		MyMouse mm = new MyMouse(this);
		jta.addMouseListener(mm);
		//文本框的监听
		MyDocument md = new MyDocument(this);
		jta.getDocument().addDocumentListener(md);
	}

	// 文件保存
	public String savefile() {

		String FileName = "Vegetto.txt";
		jfcSave = new JFileChooser(fsv.getDefaultDirectory());

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
		// FileSystemView fsv =FileSystemView.getFileSystemView();
		// fsv.getHomeDirectory(); //这便是读取桌面路径的方法了
		// getDefaultDirectory() //返回文件选择器的用户默认起始目录。
		// fsv.getDefaultDirectory() ; //这便是读取我的文档路径的方法
		// 首先是创建JFileChooser 对象，里面带个参数，表示默认打开的目录，这里是默认打开当前文件所在的目录
		jfcOpen = new JFileChooser(fsv.getHomeDirectory());
		// 添加excel文件的过滤器
//		jfcOpen.addChoosableFileFilter(new ExcelFileFilter("txt"));
//		jfcOpen.addChoosableFileFilter(new ExcelFileFilter("java"));
//		jfcOpen.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

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

	// 主菜单
	public void MyJmenu() {
		// 组建实例化
		jMenuBar = new JMenuBar();
		this.setJMenuBar(jMenuBar);
		jpm = new JPopupMenu();

		txtFile = new JMenu("\t文件(F)");
		jMenuBar.add(txtFile);
		txtFile.setMnemonic(KeyEvent.VK_F);

		itemNew = new JMenuItem("        新  建         ");
		itemOpen = new JMenuItem("        打  开");
		itemSave = new JMenuItem("        保  存");
		itemSave.setEnabled(false);// 不能点击
		itemSaveAs = new JMenuItem("        另存为");
		itemSaveAs.setEnabled(false);// 不能点击
		itemYeMianSheZhi = new JMenuItem("        页面设置");
		itemPrintf = new JMenuItem("        打  印");
		itemExit = new JMenuItem("        退  出");

		txtFile.add(itemNew);
		txtFile.add(itemOpen);
		txtFile.add(itemSave);
		txtFile.add(itemSaveAs);
		txtFile.addSeparator();// 分割线
		txtFile.add(itemYeMianSheZhi);
		txtFile.add(itemPrintf);
		txtFile.addSeparator();
		txtFile.add(itemExit);

		txtNew = new JMenu("\t编辑(E)");
		jMenuBar.add(txtNew);
		txtNew.setMnemonic(KeyEvent.VK_E);

		jmiCheXiao = new JMenuItem("        撤  销          ");
		jmiJianQian = new JMenuItem("        剪  切");
		jmiFuZhi = new JMenuItem("        复  制");
		jmiNianTie = new JMenuItem("        粘  帖");
		jmiDelete = new JMenuItem("        删  除");
		jmiChaZhao = new JMenuItem("        查  找");

		txtNew.add(jmiCheXiao);
		txtNew.addSeparator();
		txtNew.add(jmiJianQian);
		txtNew.add(jmiFuZhi);
		txtNew.add(jmiNianTie);
		txtNew.add(jmiDelete);
		txtNew.addSeparator();
		txtNew.add(jmiChaZhao);

		txtGeshi = new JMenu("\t格式(O)");
		jMenuBar.add(txtGeshi);
		txtGeshi.setMnemonic(KeyEvent.VK_O);

		txtChakan = new JMenu("\t查看(V)");
		jMenuBar.add(txtChakan);
		txtChakan.setMnemonic(KeyEvent.VK_V);

		txtHelp = new JMenu("\t帮助(H)");
		jMenuBar.add(txtGeshi);
		txtHelp.setMnemonic(KeyEvent.VK_H);
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

}