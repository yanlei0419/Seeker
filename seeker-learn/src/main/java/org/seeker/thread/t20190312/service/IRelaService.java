package org.seeker.thread.t20190312.service;

import org.seeker.thread.t20190312.entity.RelaTaskPo;

public interface IRelaService {
    Boolean handleTask(RelaTaskPo po, String poolName);


}
