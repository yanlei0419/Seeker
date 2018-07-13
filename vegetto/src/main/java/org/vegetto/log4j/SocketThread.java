///*
// * 创建日期 2015-2-3
// *
// * TODO 要更改此生成的文件的模板，请转至
// * 窗口 － 首选项 － Java － 代码样式 － 代码模板
// */
//package org.vegetto.log4j;
//
//import java.io.IOException;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//
//import javax.servlet.ServletContext;
//
//import org.apache.log4j.Logger;
//
//import com.cbhb.opiWeb.common.PropertiesUtils;
//
///**
// * @author qms
// * 2015-2-4 10:40
// * TODO 要更改此生成的类型注释的模板，请转至
// * 窗口 － 首选项 － Java － 代码样式 － 代码模板
// */
//public class SocketThread extends Thread {
//	private static Logger logger = Logger.getLogger(SocketThread.class);
//	private ServletContext servletContext;
//	private ServerSocket serverSocket = null;
//	private static ScheduledExecutorService executorPool;
//	public static int  localPort = 0;
//	private int maxConn=30;//缺省值
//	
//	public SocketThread(ServerSocket serverSocket, ServletContext servletContext) {
//		this.servletContext = servletContext;
//		String port = PropertiesUtils.getProperty("SERVER_PORT");
////		String backlog = PropertiesUtils.getProperty("SERVER_BACKLOG");
//		String bsMaxConn = PropertiesUtils.getProperty("BS_MAX_CONN");
//		if(bsMaxConn != null && !"".equals(bsMaxConn.trim()) && !"0".equals(bsMaxConn.trim())) {
//			maxConn = Integer.parseInt(bsMaxConn);
//		}
//		executorPool = Executors.newScheduledThreadPool(maxConn);
//		logger.info("初始化线程池成功,线程数[" + maxConn + "]");
//		
//		try {
//			this.serverSocket = new ServerSocket(Integer.parseInt(port));
//			logger.info("消息接收服务已启动，监听端口[" + port + "]，等待接收报文......");
//		} catch (Exception e) {
//			e.printStackTrace();
//			String msg = "初始化Socket服务失败！请确认端口[" + port + "]是否已 被占用";
//			logger.error(msg, e);
//			throw new RuntimeException(msg, e);//抛出该异常是为了停止服务，程序不用走下去了
//		}
//		
//	}
//	
//	
//	public void run() {
//		while (!serverSocket.isClosed()) { 
//			try {
//				Socket socket = serverSocket.accept();
//				localPort = serverSocket.getLocalPort();
//				logger.info("当前连接端口为：" + localPort);
//				if (socket != null){
//					if(logger.isDebugEnabled()){
//						logger.info(socket.getRemoteSocketAddress() + " 已连接上服务器......");
//					}	
//					executorPool.execute(new ProcessSocketData(socket, this.servletContext));
//				}
//			} catch (IOException e) {
//				logger.error("服务端接收请求异常！", e);
//			}
//		}
//	}
//	public void closeServerSocket() {
//		try {
//			if (serverSocket != null && !serverSocket.isClosed()){
//				executorPool.shutdown();
//				serverSocket.close();
//				logger.info("成功关闭服务端线程");
//			}
//		} catch (IOException e) {
//			logger.error("关闭服务端线程异常！", e);
//		}
//	}
//}
