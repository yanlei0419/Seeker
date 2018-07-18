package org.seeker.lock;


public class Producer implements Runnable {
    private MyRescoure list;
    public Producer(MyRescoure list) {
        this.list=list;
    }

    @Override
    public void run() {
        while (true){
            list.add();
        }
    }
}
