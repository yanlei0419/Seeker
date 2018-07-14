package org.vegetto.proxy.main;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test {

	public static void main(String[] args) throws Exception {
		String pwd = "",sa="",print="";
		String ss = "0123456789abcdefghjkmnpqrstuvwxyzABCDEFGHIJKLMNPQRSTUVXXYZ";
//		System.out.println(ss.length());
		int iRan = 10;
		int nLen = 6;
		Random ran = new Random();
//		Set<String> set=null;
		List<String> list = new ArrayList<String>();
		boolean flag = true;
		long a = System.currentTimeMillis(), b = 0, w = 0,k=1;
		double ve = 0;
		String path="d:\\le.txt";
		File file=new File(path);
		FileOutputStream out=new FileOutputStream(file);
		BufferedOutputStream bos=new BufferedOutputStream(out);
//		BufferedWriter bw=new BufferedWriter
		while (flag) {
			pwd = "";
			for (int ii = nLen; ii > 0; ii--) {
				iRan = ran.nextInt(ss.length());
				// System.out.println(iRan);
				pwd = pwd + ss.substring(iRan, iRan + 1);
			}
//			System.out.println("pwd==>>[" + (list.size()+1) + "]<<==" + pwd);
//			flag = !list.contains(pwd);
			
			if (!list.contains(pwd)) {
				list.add(pwd);
				print=pwd+"\r\n";
				writerContent(print, bos);
			}else{
				b = System.currentTimeMillis();
				w = b - a;
				ve = w / 1000.00d;
				System.out.println("ve>>>" + ve + "s");
				sa="ve>>>" + ve + "s\r\n";
				writerContent(sa, bos);
				for (int i = 0; i < list.size(); i++) {
					if (pwd.equals(list.get(i))) {
						sa="["+pwd+"]====["+list.get(i)+"]>>>"+list.size()+"\r\nThe."+(i+1)+"\r\n";
						System.out.println(sa);
						writerContent(sa, bos);
					}
				}
				k++;
				a = System.currentTimeMillis();
//				if(list.size()>1000000){
					//set=new HashSet<String>(list);
//					break;
			}
		}
		
		bos.flush();
		bos.close();
//		System.out.println("list==>>"+list.size());
		//System.out.println("set==>>"+set.size());
	}
	
	public static void writerContent(String writer,BufferedOutputStream bos){
		try {
			bos.write(writer.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
