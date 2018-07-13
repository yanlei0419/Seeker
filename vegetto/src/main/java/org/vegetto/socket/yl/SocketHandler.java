package org.vegetto.socket.yl;

import java.util.ArrayList;
import java.util.List;


public class SocketHandler {

	private static  List<Client> clients = new ArrayList<Client>();
	public static void main(String args[]) {
		ChatServer cs = new ChatServer();

		while (cs.isStarted()) {
			cs.start();
		}
	}
	public static List<Client> getClients() {
		return clients;
	}

//	public static void setClients(List<Client> clients) {
//		clients = clients;
//	}
	public static void setClient(Client client) {
		clients.add(client);
	}
}
