package org.seeker.thread;

public class Test02 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t1.wait();
        Thread.sleep(15000);
        t1.interrupt();
//        t1.notify();




    }
    
}
