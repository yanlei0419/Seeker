package org.vegetto.swing.stock.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.vegetto.swing.stock.view.MainMenu;

public  class ReadTxt {
	private   MainMenu mf;

	public ReadTxt(MainMenu mf) {
		this.mf = mf;
	}
	public  void txtOpen(String str) {
		try {
			File file =new File(str);
			FileReader fr = new FileReader(file);
			BufferedReader  br= new BufferedReader (fr);
			String txt;
			
			while((txt=br.readLine())!=null){
				mf.jta.append(txt+"\n");					
				System.out.println(txt);			
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception e){
			
		}

	}

}
