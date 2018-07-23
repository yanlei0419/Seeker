package org.seeker.thread.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyResource {
    private Lock lock=new ReentrantLock();
    private List list=new ArrayList();
    private Condition r=lock.newCondition();
    private Condition w=lock.newCondition();
    private Condition a=lock.newCondition();
    private int flag=4;
    
    
    
    
    public void add(){
        lock.lock();
        try {
            if (list.size()<=flag) {
                String val=UUID.randomUUID().toString();
                list.add(val);
                System.out.println("添加一个商品"+val);
                r.signalAll();
            } else {
                System.out.println("商品已满");
                w.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    
    public void sub(){
        lock.lock();
        try {
            if (list.size()>0) {
                System.out.println("消费一个商品"+list.remove(0));
                w.signalAll();
            } else {
                System.out.println("商品已空");
                r.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] a){
        MyResource my = new MyResource();
        Thread t1 = new Thread(new Consumer(my));
        Thread t2 = new Thread(new Produce(my));
        t2.start();
        t1.start();

    }

}
