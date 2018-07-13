package org.vegetto.dao.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.sql.DataSource;

import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.vegetto.entity.SUser;


public class Test {
	private ApplicationContext ac;
	@BeforeClass
	public void setUpBeforeClass() throws Exception {
		ac= new ClassPathXmlApplicationContext("spring.xml");
//		ApplicationContext ctx= new ClassPathXmlApplicationContext("../resources/spring.xml");
		
//		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
	}
	@BeforeClass
	@org.junit.Test
	public void my() throws Exception{
		ApplicationContext ctx= new ClassPathXmlApplicationContext("spring.xml");
//		String sql = "select age,name,id,nickName from vegetto";
//		DataSource dataSource=(DataSource) ctx.getBean("dataSource");
//
//		Connection con = dataSource.getConnection();
//		
//		PreparedStatement ps = null;
//		ps = con.prepareStatement(sql.toString());
//		ResultSet rs = null;
//		rs = ps.executeQuery();
//		//rs.next();
////		Vegetto ve = new Vegetto();
//		List<SUser> list= ResultToEnitiy.toListEntity(rs, SUser.class);
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i).toString());
//		}
//		ve.setAge(Integer.valueOf(abc));
//		ve=(Vegetto) ResultToEnitiy.getObjectByResult(rs, ve.getClass());
	}
	
	
	public static void main(String[] args) throws Exception {
		ApplicationContext ctx= new ClassPathXmlApplicationContext("spring.xml");
		String sql = "select age,name,id,nickName from vegetto";
		DataSource dataSource=(DataSource) ctx.getBean("dataSource");

		Connection con = dataSource.getConnection();
		
		PreparedStatement ps = null;
		ps = con.prepareStatement(sql.toString());
		ResultSet rs = null;
		rs = ps.executeQuery();
		//rs.next();
//		Vegetto ve = new Vegetto();
	
//		ve.setAge(Integer.valueOf(abc));
//		ve=(Vegetto) ResultToEnitiy.getObjectByResult(rs, ve.getClass());
	}
}
