package org.seeker.thread;

public class ObjectTest{
    private final long flag=1000L*2L;


    public synchronized void a(String str) throws InterruptedException {
        System.out.println(str+"a");
        Thread.sleep(flag);
    }

    public void b(String str) throws InterruptedException {
        synchronized (this){
            System.out.println(str+"b");
            Thread.sleep(flag);
        }
    }


    public static void main(String[] args) throws InterruptedException {
        ObjectTest t=new ObjectTest();
        Demo3 t3=new Demo3(t);
        new Thread(t3).start();


        ObjectTest t2=new ObjectTest();
        Demo4 t4=new Demo4(t2);
        new Thread(t4).start();
    }
}

class Demo3 implements Runnable{
    private ObjectTest ct;

    public Demo3(ObjectTest ct) {
        this.ct = ct;
    }

    @Override
    public void run() {
        try {
            this.ct.b("线程Demo3访问");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
class Demo4 implements Runnable{
    private ObjectTest ct;

    public Demo4(ObjectTest ct) {
        this.ct = ct;
    }

    @Override
    public void run() {
        try {
            this.ct.b("线程Demo4访问");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}