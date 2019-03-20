package org.seeker.thread.t20190312.service.impl;

import org.seeker.thread.t20190312.entity.RelaTaskPo;
import org.seeker.thread.t20190312.service.IRelaService;

public class RelaServiceImpl implements IRelaService {





    @Override
    public Boolean handleTask(RelaTaskPo po, String poolName) {
        try{
            long l=(int)Math.random();
            try {
                Thread.sleep(l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String val=poolName+">>>>"+po.getTaskName()+">>>handleTask>>>"+l;
            System.out.println(val);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
