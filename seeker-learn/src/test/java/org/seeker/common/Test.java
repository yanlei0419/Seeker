package org.seeker.common;

import java.util.ArrayList;
import java.util.List;

import org.seeker.common.util.config.SysConstant;

public class Test {
	static void add(List<String> list){
		list.add("1");
		list.add("2");
		list.add("3");
	}
	public static void main(String[] args) {
		List<String> list=new ArrayList<String>();
		list.add("6");
		add(list);
		for (String string : list) {
			System.out.println(string);
		}
		System.out.println(SysConstant.jdbcType);
	}
}
