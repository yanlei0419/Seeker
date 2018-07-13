package org.vegetto.file;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.vegetto.common.util.WriteFileUtil;
import org.vegetto.file.entity.LineDataPo;

public class HandlyLineData {
	public static Set<String> set = new HashSet<String>();
	public static Set<LineDataPo> fileNameSet = new HashSet<LineDataPo>();
	private static int count = 0;
	public final static String sgin = ".log";
	public final static String filePath = "e:/logs/";
	static{
		set.add("empty");
		set.add("userEmpty");
	}

	public static void handly(String lineData, int line, String fileName) throws IOException {
		fileName="[" + fileName + "]=-=";
		LineDataPo dataPo = new LineDataPo();
		dataPo.setFilePath(filePath);
		dataPo.setLineData(lineData);
		dataPo.setLine(line);
		int start = lineData.indexOf("Operate["), end = lineData.indexOf("]");
		if (start == -1 || end == -1) {
			dataPo.setFileName(fileName+"empty" + sgin);
			dataPo.setUserId("empty");
			fileNameSet.add(dataPo);
			outFileContent(dataPo);
			return;
		}
		// 用户名
		String tempUser = lineData.substring(start + 8, end).trim();
		if ("".equals(tempUser.trim())) {
			dataPo.setFileName(fileName+"userEmpty" + sgin);
			dataPo.setUserId("userEmpty");
			fileNameSet.add(dataPo);
		} else {
			 set.add(tempUser);
			dataPo.setFileName(fileName+tempUser + sgin);
			dataPo.setUserId(tempUser);
			fileNameSet.add(dataPo);
		}
		outFileContent(dataPo);
	}

	private static void outFileContent(LineDataPo po) throws IOException {
		System.out.println("write line : " + po.getLine());
		po.setLineData("line =-=-=" + po.getLine() + " : " + po.getLineData());
		WriteFileUtil.writeFile(po);
	}

	public static void handlyLog4j(String lineData, int line, String fileName) throws IOException {
		fileName="	[	" + fileName + "		]	=-=";
		LineDataPo dataPo = new LineDataPo();
		dataPo.setFilePath(filePath);
		dataPo.setLineData(lineData);
		dataPo.setLine(line);
		if (lineData.contains("INFO")) {
			dataPo.setFileName(fileName+"INFO" + sgin);
			dataPo.setUserId("INFO");
		} else if (lineData.contains("DEBUG")) {
			dataPo.setFileName(fileName+"DEBUG" + sgin);
			dataPo.setUserId("DEBUG");
		} else if (lineData.contains("ERROR")) {
			dataPo.setFileName(fileName+"ERROR" + sgin);
			dataPo.setUserId("ERROR");
		} else {
			dataPo.setFileName(fileName+"empty" + sgin);
			dataPo.setUserId("empty");
		}
		outFileContent(dataPo);
	}
}
