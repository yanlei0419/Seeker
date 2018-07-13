package org.vegetto.dao.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.vegetto.demo.SUserDao;
import org.vegetto.entity.SUser;

public class DaoTest {
	private ApplicationContext ac;
	public void setUpBeforeClass() throws Exception {
		ac= new ClassPathXmlApplicationContext("spring.xml");
//		ApplicationContext ctx= new ClassPathXmlApplicationContext("../resources/spring.xml");
		
//		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
	}
	
	
	public static void main(String[] args) throws Exception {
		ApplicationContext ctx= new ClassPathXmlApplicationContext("spring.xml");
	
//		DataSource dataSource=(DataSource) ctx.getBean("dataSource");

//		Connection con = dataSource.getConnection();
//		Connection con = DBConnection.getConnection();
//		
//		PreparedStatement ps = null;
//		ps = con.prepareStatement(sql.toString());
//		ResultSet rs = null;
//		rs = ps.executeQuery();
//		//rs.next();
		SUserDao baseDao=new SUserDao();
		List<SUser> list=baseDao.getList();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
		SUser s=baseDao.getEntity();
		System.out.println(s.getId()+s.getPassword()+s.getUserName());
	}
}
