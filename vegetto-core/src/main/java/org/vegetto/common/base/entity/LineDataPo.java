package org.vegetto.common.base.entity;

public class LineDataPo {
	
	private String filePath;//文件路径
	private String fileName;//文件名称
	private String lineData;//行数据
	private int line;//行号
	private String userId;
	private int nullLine=0;//前后空行
	
	public int getNullLine() {
		return nullLine;
	}
	public void setNullLine(int nullLine) {
		this.nullLine = nullLine;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getLine() {
		return line;
	}
	public void setLine(int line) {
		this.line = line;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getLineData() {
		return lineData;
	}
	public void setLineData(String lineData) {
		this.lineData = lineData;
	}
	

}
