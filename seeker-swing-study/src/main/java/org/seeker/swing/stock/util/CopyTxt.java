package org.seeker.swing.stock.util;

import org.seeker.swing.stock.view.MainMenu;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


public class CopyTxt {
	MainMenu mf;

	public CopyTxt(MainMenu mf) {
		this.mf = mf;
	}
	
	public void txtSave(String txt,String save) {
		try {			
			FileWriter fw = new FileWriter(save);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(txt);
			bw.newLine();

			System.out.println("写入成功");
			bw.close();
			fw.close();
			//mf.itemSaveAs.setEnabled(false);
			mf.btnSave.setEnabled(false);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			System.out.println("保存失败");
		}
	}
	
	public static void cut(String txt,String save) {
		try {			
			FileWriter fw = new FileWriter(save);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(txt);
			bw.newLine();

			System.out.println("写入成功");
			bw.close();
			fw.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			System.out.println("保存失败");
		}
	}

}
