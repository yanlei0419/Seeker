package org.seeker.thread.t20190312.entity;

import org.seeker.thread.t20190312.service.IRelaService;

import java.util.concurrent.CountDownLatch;

public class RelaRunable implements Runnable {

    private IRelaService s;
    private CountDownLatch cd;
    private String sql;

    public RelaRunable(IRelaService s, CountDownLatch cd, String sql) {
        this.s = s;
        this.cd = cd;
        this.sql = sql;
    }

    @Override
    public void run() {
        s.handleTask(this.sql);
        this.cd.countDown();

    }
}
