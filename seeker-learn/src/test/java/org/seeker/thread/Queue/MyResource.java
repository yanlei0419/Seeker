package org.seeker.thread.Queue;


import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MyResource {
    private final int flag = 3;
    private BlockingQueue<String> list=new LinkedBlockingQueue(flag);

    
    
    
    public void add() throws InterruptedException {
        String val=UUID.randomUUID().toString();
        list.put(val);
        System.out.println("添加一个商品"+val);
        
        
    }
    
    public void sub() throws InterruptedException {
            System.out.println("消费一个商品>>>>"+list.take());
    }

    public static void main(String[] a){
        MyResource my = new MyResource();
        Thread t1 = new Thread(new Consumer(my));
        Thread t2 = new Thread(new Produce(my));
        t1.start();
        t2.start();

    }

}
