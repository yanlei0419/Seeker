/* 
 *Create on Jan 9, 009 
 *Copyright 009 Quasar00650All Rights reserved 
 * 
 *weizhaozhe 
 */
package org.vegetto.socket.yl;

/** 
 * 这个聊天室程序是基于TCP Socket的，其中，为了消除IO阻塞，采用了多线程的方式。 
 *  
 * @author Quasar00650 
 * @since Jan 9, 009 
 *  
 */
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class ChatServer {
	private boolean started = false;
	private ServerSocket ss = null;
	private Client c = null;

	public ChatServer() {
		try {
			ss = new ServerSocket(SocketConfig.port);
			started = true;
		} catch (IOException ioe) {
			System.out.println("对不起，服务器不能启动！");
			ioe.printStackTrace();
			System.exit(-1);
		}
	}

	public void start() {
		try {
			Socket s = this.getSs().accept();
			System.out.println("Here comes a client! ");
			if (s != null) {
				c = new Client(s, true);
				SocketHandler.setClient(c);
				new Thread(c).start(); // 线程始终没能停止！！！?
				c = null;
			}
		} catch (IOException e) {
			System.out.println("连接错误！");
			e.printStackTrace();
		}
	}


	


	public boolean isStarted() {
		return started;
	}

	public void setStarted(boolean started) {
		this.started = started;
	}

	public ServerSocket getSs() {
		return ss;
	}

	public void setSs(ServerSocket ss) {
		this.ss = ss;
	}

	


}
