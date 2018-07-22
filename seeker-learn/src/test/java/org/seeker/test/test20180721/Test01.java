package org.seeker.test.test20180721;


public class Test01 {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            int i=0;
            @Override
            public void run() {
                while(!Thread.currentThread().isInterrupted()){
                    System.out.println(i++);
                }
            }
        });
        t1.start();

        Thread.sleep(5000);

        t1.interrupt();


    }
}
