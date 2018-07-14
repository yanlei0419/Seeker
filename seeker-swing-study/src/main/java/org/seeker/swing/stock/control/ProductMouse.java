package org.seeker.swing.stock.control;

import org.seeker.swing.stock.view.ProductShow;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ProductMouse extends MouseAdapter {

	ProductShow cs;

	public ProductMouse(ProductShow cs) {

		this.cs = cs;
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getButton() == MouseEvent.BUTTON3) {
			cs.jpm.show(cs.ProductShowtable, e.getX() + 10, e.getY() + 10);

		} else if (e.getButton() == MouseEvent.BUTTON1) {
			int NumRow = cs.ProductShowtable.getSelectedRow();
			System.out.println(NumRow);
		}
	}
}
