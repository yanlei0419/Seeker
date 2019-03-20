package org.seeker.thread.t20190312.common.db;

import com.alibaba.druid.pool.DruidDataSource;
import sun.applet.Main;

public class DB {
    public static DruidDataSource ds;
    static{
        ds=new DruidDataSource();
        ds.setDriverClassName("org.sqlite.JDBC");
        ds.setUrl("jdbc:sqlite:D:\\sqlite_sm.db");
        ds.setUsername("");
        ds.setPassword("");
    }


    public static void main(String[] args) {
        System.out.println(ds);
    }
}
