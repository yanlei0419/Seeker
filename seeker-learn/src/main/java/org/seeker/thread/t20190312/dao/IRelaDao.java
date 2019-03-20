package org.seeker.thread.t20190312.dao;

import org.seeker.thread.t20190312.entity.RelaTaskPo;

import java.sql.SQLException;
import java.util.List;

public interface IRelaDao {

    List<RelaTaskPo> getList(RelaTaskPo po) throws SQLException ;


}
