package org.vegetto.pad.control;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.vegetto.pad.view.MyTextView;

public class MyMouse extends MouseAdapter{
	MyTextView mt;
	
	public MyMouse(MyTextView mt){
		
		this.mt=mt;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getButton()==MouseEvent.BUTTON3){
			mt.jpm.show(mt.jta,e.getX()+10,e.getY()+10);
		}
		
	}

	

}
