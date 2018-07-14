//package org.vegetto.mima;
//
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//
///**
// * @author Shuni Tang
// * @date 2016年9月5日 下午12:21:14
// * @parameter startup mina
// */
//
//public class InitListener implements ServletContextListener {
//	private Server server = new Server();
//
//	public void contextDestroyed(ServletContextEvent arg0) {
//	}
//
//	public void contextInitialized(ServletContextEvent sce) {
//		try {
//			server.start();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//}