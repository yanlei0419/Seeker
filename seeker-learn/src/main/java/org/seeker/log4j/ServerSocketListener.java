///*
// * 创建日期 2015-2-3
// *
// * TODO 要更改此生成的文件的模板，请转至
// * 窗口 － 首选项 － Java － 代码样式 － 代码模板
// */
package org.seeker.log4j;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//
//import org.apache.log4j.Logger;
//
//import com.cbhb.opiWeb.mapper.socket.SocketThread;
//import com.cbhb.opiWeb.mapper.socket.SocketThread2;
//import com.cbhb.opiWeb.mapper.socket.UploadSocketThread;
//
//
//
///**
// * @author qms
// * 2015-2-4 10:28
// * TODO 要更改此生成的类型注释的模板，请转至
// * 窗口 － 首选项 － Java － 代码样式 － 代码模板
// */
//public class ServerSocketListener implements ServletContextListener {
//	private static Logger logger = Logger.getLogger(ServerSocketListener.class);
//	private SocketThread socketThread1; // 非深圳黄金
//	private SocketThread2 socketThread2; // 深圳黄金
//	private UploadSocketThread uploadSocket; // 非深圳黄金(下载文件)
//	/* （非 Javadoc）
//	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
//	 */
//	public void contextInitialized(ServletContextEvent e) {
//		logger.info("start SocketListenerServer!");
//		ServletContext context=e.getServletContext();
//		
//		logger.info("============================非深圳黄金==================================");
//		if(socketThread1==null){
//			socketThread1 = new SocketThread(null, context);
//			socketThread1.start(); // servlet上下文初始化时启动socket服务端线程
//		}
//		logger.info("============================非深圳黄金==================================");
//
//		logger.info("============================深圳黄金====================================");
//		if(socketThread2==null){
//			socketThread2 = new SocketThread2(null, context);
//			socketThread2.start(); // servlet上下文初始化时启动socket服务端线程
//		}
//		logger.info("============================深圳黄金====================================");
//		
//		logger.info("============================非深圳黄金====================================");
//		if(uploadSocket==null){
//			uploadSocket = new UploadSocketThread(null, context);
//			uploadSocket.start(); // servlet上下文初始化时启动socket服务端线程
//		}
//		logger.info("============================非深圳黄金====================================");
//	}
//
//	/* （非 Javadoc）
//	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
//	 */
//	public void contextDestroyed(ServletContextEvent e) {
//		logger.info("stop SocketListenerServer!");
//		logger.info("============================非深圳黄金==================================");
//		if (socketThread1 != null && socketThread1.isAlive()) {
//			socketThread1.closeServerSocket();
//			socketThread1.interrupt();
//		}
//		logger.info("============================非深圳黄金==================================");
//		
//		logger.info("============================深圳黄金====================================");
//		if (socketThread2 != null && socketThread2.isAlive()) {
//			socketThread2.closeServerSocket();
//			socketThread2.interrupt();
//		}
//		logger.info("============================深圳黄金====================================");
//		
//		logger.info("============================非深圳黄金====================================");
//		if (uploadSocket != null && uploadSocket.isAlive()) {
//			uploadSocket.closeServerSocket();
//			uploadSocket.interrupt();
//		}
//		logger.info("============================非深圳黄金====================================");
//	}
//
//	
//}
