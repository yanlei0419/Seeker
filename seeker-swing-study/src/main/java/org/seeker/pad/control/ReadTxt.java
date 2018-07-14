package org.seeker.pad.control;

import org.seeker.pad.view.MyTextView;

import java.io.*;


public  class ReadTxt {
	private MyTextView mf;

	public ReadTxt(MyTextView mf) {
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
