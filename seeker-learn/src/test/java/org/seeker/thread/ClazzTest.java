package org.seeker.thread;

public class ClazzTest{
    private static final long flag=1000L*2L;


    public synchronized static void a(String str) throws Exception {
        System.out.println(str+"a");
        Thread.sleep(flag);
    }

    public void b(String str) throws Exception {
            Thread.sleep(flag);
        synchronized (ClazzTest.class){
            System.out.println(str+"b");
            Thread.sleep(flag);
        }
    }
    public static void c(String str) throws Exception {
        Thread.sleep(100);
        System.out.println(str+"c");
    }



    public static void main(String[] args) throws Exception {
        Demo1 t=new Demo1(new ClazzTest());
        new Thread(t).start();
        Demo2 t2=new Demo2();
        new Thread(t2).start();
//        t.b("主线程");
        ClazzTest.c("主线程");
    }
}

class Demo1 implements Runnable{
    private ClazzTest ct;

    public Demo1(ClazzTest ct) {
        this.ct = ct;
    }

    @Override
    public void run() {
        try {
            this.ct.b("线程Demo1访问");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
class Demo2 implements Runnable{

    @Override
    public void run() {
        try {
            ClazzTest.a("线程Demo2访问");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
