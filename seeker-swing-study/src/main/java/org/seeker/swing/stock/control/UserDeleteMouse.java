package org.seeker.swing.stock.control;


import org.seeker.swing.stock.view.UserDelete;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class UserDeleteMouse extends MouseAdapter {

	UserDelete ud;
	
	public UserDeleteMouse(UserDelete	ud){		
		this.ud=ud;
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getButton()==MouseEvent.BUTTON3){
			ud.jpm.show(ud.UserDeletetable,e.getX()+10,e.getY()+10);
		
		}else if(e.getButton()==MouseEvent.BUTTON1){
			int NumRow=ud.UserDeletetable.getSelectedRow();
			System.out.println(NumRow);
		}
	}

}
