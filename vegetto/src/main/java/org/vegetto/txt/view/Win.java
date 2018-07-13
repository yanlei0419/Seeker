package org.vegetto.txt.view;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Win extends JFrame {
	ImageIcon logo = new ImageIcon(this.getClass().getResource(
			"/images/image01.gif"));
	public JTextArea jta;
	public JButton Enter, Reset, Options, Import;
	public JScrollPane jsp;
	private String path;
	public JFileChooser jfcOpen;
	public JTextField res;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Win() {
		Font font = new Font("微软雅黑", Font.BOLD, 14);
		this.setTitle("roll点");
		this.setSize(350, 350);
		this.setDefaultCloseOperation(2);
		this.setIconImage(logo.getImage());
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		JPanel main = new JPanel();
		main.setLayout(null);
		this.setContentPane(main);

		jta = new JTextArea();
		jta.setFont(font);
		jsp = new JScrollPane(jta);
		jta.setEditable(false);
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jsp.setBounds(20, 30, 180, 240);
		main.add(jsp);

		Enter = new JButton(" 摇   奖 ");
		Enter.setFont(font);
		Enter.setBounds(220, 30, 100, 30);
		main.add(Enter);

		Import = new JButton("导入文件");
		Import.setFont(font);
		Import.setBounds(220, 80, 100, 30);
		main.add(Import);

		Reset = new JButton(" 重    置 ");
		Reset.setFont(font);
		Reset.setBounds(220, 130, 100, 30);
		main.add(Reset);
		
		Options = new JButton("设置参数");
		Options.setFont(font);
		Options.setBounds(220, 180, 100, 30);
		main.add(Options);
		
		res=new JTextField("5");
		res.setFont(font);
		res.setEditable(false);
		res.setBounds(220, 230, 100, 30);
		main.add(res);

		WinAction wa = new WinAction(this);
		Reset.addActionListener(wa);
		Options.addActionListener(wa);
		Import.addActionListener(wa);
		Enter.addActionListener(wa);
	}

	// 文件打开
	public String openfile() {
		// 首先是创建JFileChooser 对象，里面带个参数，表示默认打开的目录，这里是默认打开当前文件所在的目录
		jfcOpen = new JFileChooser("d:/");
		// 添加excel文件的过滤器
		jfcOpen.addChoosableFileFilter(new ExcelFileFilter("txt"));

		/*
		 * 使用showOpenDialog()方法，显示出打开选择文件的窗口，当选择了某个文件后，或者关闭此窗口那么都会返回一个
		 * 整型数值，如果返回的是0，代表已经选择了某个文件。如果返回1代表选择了取消按钮或者直接关闭了窗口
		 */
		int result = jfcOpen.showOpenDialog(null);

		// JFileChooser.APPROVE_OPTION是个整型常量，代表0。就是说当返回0的值我们才执行相关操作，否则什么也不做。
		if (result == JFileChooser.APPROVE_OPTION) {
			// 获得你选择的文件绝对路径。并输出。当然，我们获得这个路径后还可以做很多的事。
			this.path = jfcOpen.getSelectedFile().getAbsolutePath();

			return path;
		} else {
			System.out.println("你已取消并关闭了窗口！");
		}
		return null;
	}

	
}
