package org.vegetto.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.vegetto.common.base.entity.UserPo;

public class TestMybatis {

    SqlSessionFactory sqlSessionFactory;
    
    @Before
    public void initFactory() throws IOException
    {
        String resource = "sqlMapConfig.xml";

        InputStream inputStream = Resources.getResourceAsStream(resource);

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }
    
    @Test
    public  void testListAll()
    {
        SqlSession session=sqlSessionFactory.openSession();
        List<UserPo> UserPos=session.selectList("cn.itcast.mybatis.listAll");
        System.out.println(UserPos.size());
    }
    @Test
    public void testQueryOne()
    {
        SqlSession session=sqlSessionFactory.openSession();
        UserPo UserPo=session.selectOne("cn.itcast.mybatis.getOne", 1);
        System.out.println(UserPo);
    }
    //事务需要程序员处理
    @Test
    public void testInsertOne()
    {
        UserPo u=new UserPo();
//        u.setId(UUID.randomUUID().toString());
//        u.setName("sbsbb");
//        u.setAge(18);
//        u.setAddress("china");
        SqlSession session=sqlSessionFactory.openSession();
        int count=session.insert("cn.itcast.mybatis.insertOne", u);
        session.commit();
        System.out.println(count);
    }
    
    @Test
    public void testUpdateOne()
    {
        SqlSession session=sqlSessionFactory.openSession();
        UserPo u=new UserPo();
        //u=session.selectOne("cn.itcast.mybatis.getOne", "2");
//        u.setId("2");
//        u.setName("clclclclclcfei");
//        u.setAge(100);
//        u.setAddress("USA");
        int count=session.update("cn.itcast.mybatis.updateOne", u);
        session.commit();
        System.out.println(count);
    }
    @Test
    public void testDeleteOne()
    {
        SqlSession session=sqlSessionFactory.openSession();
        UserPo u=new UserPo();
        u.setId("2");
        int count=session.delete("cn.itcast.mybatis.deleteOne", u);
        session.commit();
        System.out.println(count);
    }
    
    
    
}