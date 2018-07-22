package org.seeker.test.test20180721;

import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class Test implements Runnable{
    private final long flag=1000L*2L;


    public  void a(String str) throws InterruptedException {
        System.out.println(str+"a");
        Thread.sleep(flag);
    }

    public  void b(String str) throws InterruptedException {
        System.out.println(str+"b");
        Thread.sleep(flag);
    }


    @Override
    public void run() {
        try {
            this.b("线程访问");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Test t=new Test();
        new Thread(t).start();
        t.a("主线程");
    }
}
