package org.seeker.lock;


public class Consumer implements Runnable {
    private MyRescoure list;

    public Consumer(MyRescoure list) {
        this.list = list;
    }


    @Override
    public void run() {
        while (true){
            list.sub();
        }
    }
}
