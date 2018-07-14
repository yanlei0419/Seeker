package org.seeker.test.Test20180712;

//线程类
class MyThread extends Thread {
    // 既然使用 synchronized关键字 ，就没必要使用 volatile关键字了
    public static volatile int count;

    //注意必须添加 static 关键字，这样synchronized 与 static 锁的就是 Mythread.class 对象了，
    //也就达到同步效果了
    private static void addCount() {
        for (int i = 0; i < 100; i++) {
            count++;
        }
        System.out.println("count=" + count);
    }

    @Override
    public void run() {
        addCount();
    }
}

//测试类
public class Run {
    public static void main(String[] args) {
        //创建 100个线程并启动
        MyThread[] mythreadArray = new MyThread[100];
        for (int i = 0; i < 100; i++) {
            mythreadArray[i] = new MyThread();
        }

        for (int i = 0; i < 100; i++) {
            mythreadArray[i].start();
        }
    }
}