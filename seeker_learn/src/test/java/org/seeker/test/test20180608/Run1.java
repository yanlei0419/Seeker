package seeker.test.test20180608;

//资源类
class MyService {
    private String lock = "123";

    public void testMethod() {
        try {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " begin "
                        + System.currentTimeMillis());
                System.out.println(Thread.currentThread().getName() + " begin "
                        +lock);
                lock = "456";
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + "   end "
                        + System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

//线程B
class ThreadB extends Thread {

    private MyService service;

    public ThreadB(MyService service) {
        super();
        this.service = service;
    }

    @Override
    public void run() {
        service.testMethod();
    }
}

//线程A
class ThreadA extends Thread {

    private MyService service;

    public ThreadA(MyService service) {
        super();
        this.service = service;
    }

    @Override
    public void run() {
        service.testMethod();
    }
}

//测试
public class Run1 {
    public static void main(String[] args) throws InterruptedException {

        //临界资源
        MyService service = new MyService();

        //线程A
        ThreadA a = new ThreadA(service);
        a.setName("A");

        //线程B
        ThreadB b = new ThreadB(service);
        b.setName("B");

        a.start();
//        Thread.sleep(50);// 存在50毫秒
        b.start();
    }
}/* Output(循环): 
       A begin 1484319778766
       B begin 1484319778815
       A   end 1484319780766
       B   end 1484319780815
 *///:~