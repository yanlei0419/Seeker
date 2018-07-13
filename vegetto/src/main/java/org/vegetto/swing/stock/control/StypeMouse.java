package org.vegetto.swing.stock.control;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.vegetto.swing.stock.view.StypeShow;

public class StypeMouse extends MouseAdapter {

	StypeShow cs;

	public StypeMouse(StypeShow cs) {

		this.cs = cs;
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getButton() == MouseEvent.BUTTON3) {
			cs.jpm.show(cs.Stypetable, e.getX() + 10, e.getY() + 10);

		} else if (e.getButton() == MouseEvent.BUTTON1) {
			int NumRow = cs.Stypetable.getSelectedRow();
			System.out.println(NumRow);
		}
	}
}
