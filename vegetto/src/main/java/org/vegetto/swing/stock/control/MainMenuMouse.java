package org.vegetto.swing.stock.control;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.vegetto.swing.stock.view.MainMenu;

public class MainMenuMouse extends MouseAdapter {
	MainMenu mt;

	public MainMenuMouse(MainMenu mt) {

		this.mt = mt;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == mt.jta) {
			if (e.getButton() == MouseEvent.BUTTON3) {
				System.out.println("单机右键");
				mt.jpm.show(mt.jta, e.getX() + 10, e.getY() + 10);
			}
		}
	}

}
