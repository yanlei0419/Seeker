package org.seeker.test.test20180721.lock;


public class Produce implements Runnable {

    private MyResource list;

    public Produce(MyResource list) {
        this.list = list;
    }

    @Override
    public void run() {
        while (true){
//            try {
//                Thread.sleep(400);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            list.add();
        }

    }
}
