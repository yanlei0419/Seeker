package junit.mybatis;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.seeker.common.junit.BaseJunit;
import org.vegetto.mybatis.user.biz.PmsUserBiz;
import org.vegetto.mybatis.user.dao.PmsUserDao;
import org.vegetto.mybatis.user.entity.PmsUser;

public class PmsUserDaoTest  extends BaseJunit {
	public static void main(String[] args) throws SQLException {
		PmsUser p = new PmsUser();
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/spring.xml");

		PmsUserBiz biz = (PmsUserBiz) ctx.getBean("pmsUserBiz");

		// DataSource ds=(DataSource) ctx.getBean("dataSource");
		// Connection con = ds.getConnection();
		// ResultSet rs =
		// con.createStatement().executeQuery("select * from edu_edmo_pms_user");
		// while(rs.next()){
		// System.out.println(rs.getString(1)+"-=-=-=-=-"+rs.getByte(2));
		// }

		// SqlSessionTemplate sst=(SqlSessionTemplate)
		// ctx.getBean("sessionTemplate");
		// List<PmsUser> list=sst.selectList("listAll");
		PmsUserDao dao = (PmsUserDao) ctx.getBean("pmsUserDao");
		List<PmsUser> list = dao.findAll();
		for (PmsUser p1 : list) {
			System.out.println(p1.getId());
			System.out.println(p1.getCreateTime());
		}
		p.setUserName("测试");
		p.setUserNo("5");
		p.setUserPwd("123");
		p.setUserType("1");
		p.setId(Long.valueOf("10000"));
		p.setIsChangedPwd(true);
		p.setStatus(100);
		p.setLastLoginTime(new Date());
		p.setCreateTime(new Date());
		p.setPwdErrorCount(0);
		p.setPwdErrorTime(new Date());
		// dao.insert(p);
		biz.create(p);

	}
}
