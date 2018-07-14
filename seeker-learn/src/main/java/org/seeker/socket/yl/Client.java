package org.vegetto.socket.yl;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client implements Runnable {
	private boolean connected = false;
	private Socket s = null;
	private DataInputStream dis = null;
	private DataOutputStream dos = null;

	public Client(Socket s, boolean connected) {
		this.s = s;
		this.connected = connected;
	}

	public void run() {
		InetAddress ip = null;
		int port = 9999;
		try {
			if (connected) {
				dis = new DataInputStream(s.getInputStream());
				dos = new DataOutputStream(s.getOutputStream());
				ip = s.getInetAddress();
				port = s.getPort();
			}
			while (connected) {
				String line = dis.readUTF();
				if (line.equals("###Exit###")) {
					SocketHandler.getClients().remove(this); // 当接收到推出的消息，移除客户端记录
					line = "Bye-Bye!";
					connected = false;
				}
				System.out.println("From: (" + ip.toString() + ":" + port + "): " + line);
				for (int i = 0; i <SocketHandler.getClients().size(); i++) {
					if (this.equals(SocketHandler.getClients().get(i))) {
						continue;
					} else {
						SocketHandler.getClients().get(i).dos.writeUTF("From: (" + ip.toString() + ":" + port + "): " + line + "/n");
					}
				}
			}

		} catch (IOException ioe) {
			System.out.println("客户端连接出错！");
			ioe.printStackTrace();
		}
	}
}