package org.seeker.thread.t20190312.entity;

import org.seeker.thread.t20190312.RelaThreadPool;
import org.seeker.thread.t20190312.service.IRelaService;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class RelaRunable implements Runnable {


    private IRelaService s;
    private CountDownLatch cd;
    private final RelaTaskPo po;
    private List<RelaTaskDependLinkedPo> list;

    public RelaRunable(IRelaService s, RelaTaskPo po) {
        this.s = s;
        this.po = po;
        this.list = po.getDependTaskId();
    }

    @Override
    public void run() {
        if (null != list &&!list.isEmpty()) {//没有依赖关系直接执行任务

            //有依赖关系
            RelaTaskPo taskPo = null;
            for (int i = 0; i < list.size(); i++) {
                taskPo = RelaThreadPool.getPool().getTaskInfoByTaskId(list.get(i).getRelaDependTaskId());
                synchronized (taskPo){
                    if (!taskPo.getThreadStatus()) {//如果依赖关系中有未完成的任务执行 跳出循环 等待
                        try {
                            taskPo.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }

        boolean relaStatusFlag = s.handleTask(this.po, Thread.currentThread().getName());
        synchronized (po){
            if (relaStatusFlag) {
                this.po.setThreadStatus(relaStatusFlag);
                po.notifyAll();
            } else {
                //将任务重新提交到线程池中
            }
        }


    }
}
