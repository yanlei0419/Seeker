package org.seeker.test.test20180713.demo04;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test {
    private Lock lock = new ReentrantLock();
    public static void main(String[] args)  {
        Test test = new Test();
        MyThread thread1 = new MyThread(test,"A");
        MyThread thread2 = new MyThread(test,"B");
        thread1.start();
        thread2.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        thread2.interrupt();
    }  

    public void insert(Thread thread) throws InterruptedException{
        //注意，如果需要正确中断等待锁的线程，必须将获取锁放在外面，然后将 InterruptedException 抛出
        lock.lockInterruptibly(); 
        try {  
            System.out.println("线程 " + thread.getName()+"得到了锁...");
            long startTime = System.currentTimeMillis();
            for(    ;     ;) {              // 耗时操作
                if(System.currentTimeMillis() - startTime >= Integer.MAX_VALUE)
                    break;
                //插入数据
            }
        }finally {
            System.out.println(Thread.currentThread().getName()+"执行finally...");
            lock.unlock();
            System.out.println("线程 " + thread.getName()+"释放了锁");
        } 
        System.out.println("over");
    }
}

class MyThread extends Thread {
    private Test test = null;

    public MyThread(Test test,String name) {
        super(name);
        this.test = test;
    }

    @Override
    public void run() {
        try {
            test.insert(Thread.currentThread());
        } catch (InterruptedException e) {
            System.out.println("线程 " + Thread.currentThread().getName() + "被中断...");
        }
    }
}



/*
*运行上述代码之后，发现 thread2 能够被正确中断，放弃对任务的执行。特别需要注意的是，
* 如果需要正确中断等待锁的线程，必须将获取锁放在外面（try 语句块外），
* 然后将 InterruptedException 抛出。如果不这样做，像如下代码所示：
*
* 　注意，上述代码就将锁的获取操作放在try语句块里，则必定会执行finally语句块中的解锁操作。
* 在 准备获取锁的 线程B 被中断后，再执行解锁操作就会抛出 IllegalMonitorStateException，
* 因为该线程并未获得到锁却执行了解锁操作。
*
* */