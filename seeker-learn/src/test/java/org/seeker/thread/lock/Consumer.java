package org.seeker.thread.lock;

public class Consumer implements Runnable {
    private MyResource list;

    public Consumer(MyResource list) {
        this.list = list;
    }


    @Override
    public void run() {
        while (true) {
//            try {
//                Thread.sleep(400);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            list.sub();
        }

    }
}
