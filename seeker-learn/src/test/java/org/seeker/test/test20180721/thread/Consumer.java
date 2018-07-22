package org.seeker.test.test20180721.thread;

import java.util.List;

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
    public static void main(String[] a){
        System.out.println(111);
    }
}
