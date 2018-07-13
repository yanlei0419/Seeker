package org.vegetto.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class MyThread extends Thread {
	/**
	 * 任务分配线程从中获取任务
	 */
	public final static BlockingQueue<String> newDrWorkTaskidQueue = new ArrayBlockingQueue<String>(8);
	/**
	 * 线程安全的i++
	 */
	public static AtomicInteger undoneSize = new AtomicInteger(7);

	@Override
	public void run() {
		System.out.println("===-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-===");
		System.out.println(Thread.currentThread().getName()+"  =-=-=  正在执行。。。");
		System.out.println(this.getName() + "正在执行。。。");
		System.out.println("===-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-===");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("线程"+this.getName()+"结束");
	}
}
