package org.seeker.thread.t20190312;


import org.seeker.thread.t20190312.service.IRelaService;
import org.seeker.thread.t20190312.service.impl.RelaServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class RelaThreadPool {

    public static class SingleRelaThreadPool{
        public static RelaThreadPool pool=new RelaThreadPool();
    }

    /**
     * ThreadPoolExecutor
     *
     * int corePoolSize                     线程池核心线程数
     * int maximumPoolSize                  线程池最大数
     * long keepAliveTime                   空闲线程存活时间
     * TimeUnit unit                        时间单位
     * BlockingQueue<Runnable> workQueue    线程池所使用的缓冲队列
     * ThreadFactory threadFactory          线程池创建线程使用的工厂
     *  RejectedExecutionHandler handler    线程池对拒绝任务的处理策略
     */
    private ExecutorService executorService = new ThreadPoolExecutor(100, 500, 1, TimeUnit.SECONDS, new LinkedBlockingDeque());

    public static RelaThreadPool getPool(){
        return SingleRelaThreadPool.pool;
    }

    private RelaThreadPool() {
    }

    public <T> List<T> addTask(List<Callable<T>> tasks) {
        List<Future<T>> futureList = null;
        List<T> results = null;
        try {
            futureList = executorService.invokeAll(tasks);
            results = new ArrayList<>();
            for (Future<T> future : futureList) {
                results.add(future.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return results;
    }
    public void submitTask(Runnable tasks) {
        executorService.submit(tasks);
    }


    public static void main(String[] args) {
        RelaThreadPool pool=RelaThreadPool.getPool();
        CountDownLatch cd= new CountDownLatch(5);
        IRelaService service=new RelaServiceImpl();






    }
}
