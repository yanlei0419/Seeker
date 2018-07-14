package org.vegetto.swing.stock.control;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.vegetto.swing.stock.view.ClientShow;

public class ClientShowMouse extends MouseAdapter {

	ClientShow cs;

	public ClientShowMouse(ClientShow cs) {

		this.cs = cs;
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getButton() == MouseEvent.BUTTON3) {
			cs.jpm.show(cs.ClientShowtable, e.getX() + 10, e.getY() + 10);

		} else if (e.getButton() == MouseEvent.BUTTON1) {
			int NumRow = cs.ClientShowtable.getSelectedRow();
			System.out.println(NumRow);
		}
	}
}
