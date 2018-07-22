package org.seeker.test.test20180721.Queue;


public class Consumer implements Runnable {
    private MyResource list;

    public Consumer(MyResource list) {
        this.list = list;
    }


    @Override
    public void run() {
        while(true){
            try {
                list.sub();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
