package org.vegetto.swing.stock.control;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.vegetto.swing.stock.view.MainMenu;




public class MainMenuDocument implements DocumentListener{
	MainMenu bo;

	public MainMenuDocument(MainMenu bo) {
		this.bo = bo;
	}

	@Override
	//插入信息
	public void insertUpdate(DocumentEvent e) {		 
		bo.btnSave.setEnabled(true);	
		bo.btnRefresh.setEnabled(true);
	}

	@Override
	//	删除信息
	public void removeUpdate(DocumentEvent e) {	
		bo.btnSave.setEnabled(true);	
		bo.btnRefresh.setEnabled(true);
	}

	@Override
	//更改信息
	public void changedUpdate(DocumentEvent e) {	
		bo.btnSave.setEnabled(true);	
		bo.btnRefresh.setEnabled(true);
	}

}
