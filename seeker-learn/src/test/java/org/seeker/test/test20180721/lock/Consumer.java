package org.seeker.test.test20180721.lock;

public class Consumer implements Runnable {
    private MyResource list;

    public Consumer(MyResource list) {
        this.list = list;
    }


    @Override
    public void run() {
        while(true){
                list.sub();
        }

    }
}
