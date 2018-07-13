package org.vegetto.txt.ve.pack;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TxT {
	public String FeadFile(String path){
		File file =new File(path);
		//字符流
		/*try {		
			FileReader fr = new FileReader(file);
			BufferedReader  br= new BufferedReader (fr);
			String txt;			
			while((txt=br.readLine())!=null){
			
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
		}
		*/
		try {
			FileInputStream fis =new FileInputStream(file);
			byte[] buffer = new byte[(int) file.length()];
			fis.read(buffer);
			String  str=new String(buffer);
			fis.close();
			return str;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "1";
	}
	
	
	public static void main(String[] args) {
		TxT txt =new TxT();
		String str=txt.FeadFile("D:/abc.java");
		str.toCharArray();
	}

}
