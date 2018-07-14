///*
// * 创建日期 2015-2-3
// *
// * TODO 要更改此生成的文件的模板，请转至
// * 窗口 － 首选项 － Java － 代码样式 － 代码模板
// */
package org.seeker.log4j;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.io.Reader;
//import java.io.Writer;
//import java.net.Socket;
//
//import javax.servlet.ServletContext;
//
//import org.apache.log4j.Logger;
//
//import com.cbhb.opiWeb.pm.route.PmManageService;
//
///**
// * @author qms
// * 2015-2-4 14:34
// * TODO 要更改此生成的类型注释的模板，请转至
// * 窗口 － 首选项 － Java － 代码样式 － 代码模板
// */
//public class ProcessSocketData implements Runnable {
//	private static Logger logger = Logger.getLogger(ProcessSocketData.class);
//	private Socket socket;
//	private ServletContext servletContext;
//	public ProcessSocketData() {
//		super();
//	}
//
//	public ProcessSocketData(Socket socket, ServletContext servletContext) {
//		this.socket = socket;
//		this.servletContext = servletContext;
//	}
//	
//	public void run() {
//		try {
//			handleSocket();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}  
//	}
//	/**
//	 * ***************************************************************************
//	    Description  : 与客户端socket进行通信
//	    Input        :     
//	    Output       : None
//	    Return Value : public
//	    Calls        : 
//	    Called By    : 
//	    
//	    History      :
//	    Date         : 2015-2-5
//	    Author       : msqi
//	    Modification : Created function
//	   ****************************************************************************
//	 */ 
//    private void handleSocket() throws Exception {
//    	logger.info("====================================start==========================================");
//    	logger.info("开始读取客户端请求报文");
//		Reader reader = new InputStreamReader(socket.getInputStream());
//		Writer writer = new OutputStreamWriter(socket.getOutputStream());
//		String resxml = null;
//		try {
//			/* BEGIN: Update by msqi, 2015/07/09   PS:修改读报文逻辑*/
//			int l = -1;
//			char chars[] = new char[64];
//			StringBuilder sbody = new StringBuilder();
//			String temp = null;
//			int len = 0;
//			while ((l = reader.read(chars)) != -1) {
//				sbody.append(new String(chars, 0, l));
//				if (sbody.toString().length() >= 8) {
//					if (len == 0) {
//						temp = sbody.substring(0, 8);
//						len = Integer.parseInt(temp);
//					}
//					if (sbody.toString().substring(8).getBytes().length == len) {
//						break;
//					}
//				}
//			}
//			logger.info("成功读取客户端请求报文: " + sbody.toString());
//			PmManageService manageService = new PmManageService();
//			resxml = manageService.manageRoute(sbody.toString());
//		} catch (Exception e) {
//			logger.error("处理客户端请求异常！", e);
//			resxml = "异常报文";
//		}finally{
//			logger.info("向客户端发送响应报文: " + resxml);
//			writer.write(resxml);
//			if(writer != null){
//				writer.close();
//			}
//			if(reader != null){
//				reader.close();
//			}
//			if(socket != null){
//				socket.close();
//			}
//			logger.info("====================================end============================================");
//		}
//	} 
//}
