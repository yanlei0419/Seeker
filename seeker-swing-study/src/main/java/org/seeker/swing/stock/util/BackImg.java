package org.seeker.swing.stock.util;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BackImg {
//	ImageIcon imgback = new ImageIcon(this.getClass().getResource(
//			"/images/onepiece003.jpg"));
	public BackImg(){
		
	}
	public JPanel toShow(final String path){
		@SuppressWarnings("serial")
		JPanel back = new JPanel() {			
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon img=new ImageIcon(this.getClass().getResource(path));				
				g.drawImage(img.getImage(), 0, 0, this);
			}
		};
		return back;
	}
}
