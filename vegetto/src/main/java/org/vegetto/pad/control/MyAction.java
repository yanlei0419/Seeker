package org.vegetto.pad.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.vegetto.pad.view.MyTextView;

public class MyAction implements ActionListener {
	MyTextView mf;

	public MyAction(MyTextView mf) {
		this.mf = mf;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/*
		 * if(e.getSource() == mf.txtUid || e.getSource() == mf.btnOK)
		 * JOptionPane.showMessageDialog(mf, ); else System.exit(0);
		 */
		if (e.getSource() == mf.itemNew) {
			System.out.println("新建");
			mf.jta.setText("");
		} else if (e.getSource() == mf.itemSaveAs) {	
			
			CopyTxt ct =new CopyTxt(mf);			
			ct.txtSave(mf.jta.getText(),mf.savefile());
			System.out.println("存储");		
			mf.setTitle("记事本 -"+mf.Path );
			
		} else if(e.getSource() == mf.itemSave){
			CopyTxt ct =new CopyTxt(mf);
			if(mf.file==null){							
				ct.txtSave(mf.jta.getText(), mf.savefile());
			}else{
				ct.txtSave(mf.jta.getText(), mf.Path);
			}			
			//改变标题信息
			mf.setTitle("记事本 -"+mf.Path);
			
		}else if (e.getSource() == mf.itemOpen) {		//打开
			ReadTxt rt=new ReadTxt(mf);
			rt.txtOpen(mf.openfile());			
			//改变标题信息
			mf.setTitle("记事本 -"+mf.Path );
			
			mf.itemSave.setEnabled(false);//不能点击			
			mf.itemSaveAs.setEnabled(false);//不能点击
			
			System.out.println("打开");
		}else if(e.getSource()==mf.itemExit){
			System.exit(0);
		}
	}
}



//String  defaultDisk = "e:/";
//
//String defaultFileName = "track.java";
//
//JFileChooser jFileChooser1 = new JFileChooser();//实例选择器
//
//      jFileChooser1.setCurrentDirectory(new File(defaultDisk));//设置默认目录 打开直接默认E盘
//
//     jFileChooser1.setFileSelectionMode(JFileChooser.SAVE_DIALOG) ;//保存 这里去掉 否则默认文件名无法使用
//     jFileChooser1.setDialogTitle("保存文件");     //自定义选择框标题
//     jFileChooser1.setSelectedFile(new File(defaultFileName)); //设置默认文件名
//     jFileChooser1.showOpenDialog(my);
//    int ch = jFileChooser1.showSaveDialog(my);//这里也不能使用showOpenDialog和showSaveDialog
//
//     int ch = jFileChooser1.showDialog(my, "保存文件");//这行代码取代showOpenDialog和showSaveDialog
//
//如果还想限制文件类型
//
//private String saveType[] = {"txt","java"};  //
//
//jFileChooser1.setFileFilter(new FileNameExtensionFilter("TXT & JAVA FILE", saveType));
