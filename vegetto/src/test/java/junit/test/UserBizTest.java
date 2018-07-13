package junit.test;


import java.applet.Applet;

import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserBizTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext ac= new ClassPathXmlApplicationContext("../resources/spring.xml");
		
	}

}
