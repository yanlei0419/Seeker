package org.vegetto.swing.stock.control;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.vegetto.swing.stock.view.StoreHouseShow;

public class StoreHouseMouse extends MouseAdapter {

	StoreHouseShow cs;

	public StoreHouseMouse(StoreHouseShow cs) {

		this.cs = cs;
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getButton() == MouseEvent.BUTTON3) {
			cs.jpm.show(cs.StoreHousetable, e.getX() + 10, e.getY() + 10);

		} else if (e.getButton() == MouseEvent.BUTTON1) {
			int NumRow = cs.StoreHousetable.getSelectedRow();
			System.out.println(NumRow);
		}
	}
}
