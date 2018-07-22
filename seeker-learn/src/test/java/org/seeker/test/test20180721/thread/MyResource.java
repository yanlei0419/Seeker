package org.seeker.test.test20180721.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MyResource {
    private List list=new ArrayList();
    private final int flag=3;


    public void add() throws InterruptedException {
        synchronized (list){
            if(list.size()<flag){
                String val=UUID.randomUUID().toString();
                System.out.println("添加一个商品>>>>"+val);
                list.add(val);
                list.notifyAll();
            }else{
                System.out.println("商品已满");
                list.wait();
            }

        }

    }

    public void sub() throws InterruptedException {
        synchronized (list){
            if(list.size()>=flag-2){
                System.out.println("消费一个商品>>>>"+list.remove(0));
                list.notifyAll();
            }else{
                System.out.println("商品已空");
                list.wait();
            }
        }

    }

    public static void main(String[] args) {
        MyResource my=new MyResource();
        Thread t1 = new Thread(new Consumer(my));
        Thread t2 = new Thread(new Produce(my));
        t1.start();
        t2.start();

    }




}
