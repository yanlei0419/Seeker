package org.seeker.proxy.book.impl;


import org.seeker.proxy.book.MyDao;

public class MyDaoImpl implements MyDao {

	public void add() {
		System.out.println("add");
	}

	public void update() {
		System.out.println("update");
	}

	public Object load(String param) {
		System.out.println("load");
		return null;
	}

}
