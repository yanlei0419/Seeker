package org.seeker.thread.t20190312.service.impl;

import org.seeker.thread.t20190312.service.IRelaService;

public class RelaServiceImpl implements IRelaService {





    @Override
    public String handleTask(String name,String poolName) {
        long l=(int)Math.random();
        try {
            Thread.sleep(l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String val=poolName+">>>>"+name+">>>handleTask>>>"+l;
        System.out.println(val);
        return val;
    }
}
