package org.seeker.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyRescoure {
    private Lock lock=new ReentrantLock();
    private List list=new ArrayList();
    private Condition c = lock.newCondition();
    private Condition p = lock.newCondition();
    private int flag=3;


    void add(){
        lock.lock();
        try{
            if(list.size()<=flag){
                list.add("食物");
                System.out.println("生产食物"+list.size());
                c.signalAll();
            }else{
                System.out.println("生产等待");
                p.await();
            }
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally{
            lock.unlock();
        }
    }
    void sub(){
        lock.lock();
        try{
            if(list.size()>0){
                list.remove(0);
                System.out.println("消费食物"+list.size());
                p.signalAll();
            }else{
                System.out.println("消费等待");
                c.await();
            }
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally{
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        MyRescoure m=new MyRescoure();
        Producer p=new Producer(m);
        new Thread(p).start();


        Consumer c=new Consumer(m);
        new Thread(c).start();
        Consumer c1=new Consumer(m);
        new Thread(c1).start();
        Consumer c2=new Consumer(m);
        new Thread(c2).start();
    }

}
