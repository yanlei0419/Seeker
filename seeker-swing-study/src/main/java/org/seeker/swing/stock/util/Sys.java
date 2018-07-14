package org.seeker.swing.stock.util;

import java.util.ArrayList;
import java.util.List;

public class Sys {
	public static List<String> l=new ArrayList<String>();
	static{
		for (int i = 0; i <5; i++) {
			l.add( "测试"+System.currentTimeMillis());
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}	

}
