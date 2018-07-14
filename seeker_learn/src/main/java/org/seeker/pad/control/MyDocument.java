package org.vegetto.pad.control;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.vegetto.pad.view.MyTextView;

public class MyDocument implements DocumentListener{
	MyTextView mf;

	public MyDocument(MyTextView mf) {
		this.mf = mf;
	}

	@Override
	//插入信息
	public void insertUpdate(DocumentEvent e) {
		mf.itemSaveAs.setEnabled(true);
		mf.itemSave.setEnabled(true);
	}

	@Override
	//	删除信息
	public void removeUpdate(DocumentEvent e) {	
		mf.itemSaveAs.setEnabled(true);
		mf.itemSave.setEnabled(true);
	}

	@Override
	//更改信息
	public void changedUpdate(DocumentEvent e) {	
		mf.itemSaveAs.setEnabled(true);
		mf.itemSave.setEnabled(true);
	}

}
