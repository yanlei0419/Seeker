package org.seeker.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestFixedThreadPool {
	public static void main(String[] args) throws InterruptedException {
//		 创建一个可重用固定线程数的线程池
		ExecutorService pool = Executors.newFixedThreadPool(10);
//		 创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口
		int count = 1;
		while (true) {
			Thread t = new MyThread();
			t.setName("t" + count);
			pool.execute(t);
			count++;
			Thread.sleep(1000);
		}
		
//		Thread t1 = new MyThread();
//		Thread t2 = new MyThread();
//		Thread t3 = new MyThread();
//		Thread t4 = new MyThread();
//		Thread t5 = new MyThread();
//		 将线程放入池中进行执行
		
//		pool.execute(t1);
//		pool.execute(t2);
//		pool.execute(t3);
//		pool.execute(t4);
//		pool.execute(t5);
//		 关闭线程池

//		pool.shutdown();
	}
}
