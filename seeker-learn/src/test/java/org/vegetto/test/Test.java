package org.vegetto.test;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
	public static void main(String[] args) throws Exception {
//		String str=PropertiesReader.getPorperty("package");
//		System.out.println(str);
//		Map<String, String> map =PropertiesReader.getProperties();
//		for (String ve : map.keySet()) {
//			System.out.println(map.get(ve));
//		} 
		PrintWriter pw=new PrintWriter(new File("d:\\abc.txt"));
		String out="";
		long i=0;
		while(true){
			Thread.sleep(1000);
			out="["+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"] =-=-=-=-=-=-= "+i;
			System.out.println(out);
//			pw.write(out);
			pw.println(out);
			i++;
			pw.flush();
		}
//		pw.close();
	}
}
