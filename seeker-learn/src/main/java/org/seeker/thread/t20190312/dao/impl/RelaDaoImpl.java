package org.seeker.thread.t20190312.dao.impl;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.seeker.thread.t20190312.common.db.RelaBaseDao;
import org.seeker.thread.t20190312.dao.IRelaDao;
import org.seeker.thread.t20190312.entity.RelaTaskPo;

import java.sql.SQLException;
import java.util.List;

public class RelaDaoImpl extends RelaBaseDao implements IRelaDao   {


    public List<RelaTaskPo> getList(RelaTaskPo po) throws SQLException {
        String sql = "select * from rela_task_info ";
        Object[] param = new Object[] {};
        ResultSetHandler<RelaTaskPo> listh=new BeanListHandler(RelaTaskPo.class);
        List<RelaTaskPo> list=(List<RelaTaskPo>)super.executeQuery(sql,param,listh);
        return list;
    }

    public static void main(String[] args) throws SQLException {
        IRelaDao dao=new RelaDaoImpl();
        List<RelaTaskPo> list = dao.getList(new RelaTaskPo());
        System.out.println(list);
    }









}
