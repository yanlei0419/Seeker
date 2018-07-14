package org.seeker.office;

import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import java.io.File;
import java.io.FileInputStream;

public class PdfUtil {
	/**
	 * 通过pdf文件获取 pdf文件内容
	 * @param f
	 * @return
	 * @throws Exception
	 */
	private static String getContent(File f) throws Exception {
		FileInputStream fis = new FileInputStream(f);
		PDFParser p = new PDFParser(fis);
		p.parse();
		PDDocument pdd = p.getPDDocument();
		PDFTextStripper ts = new PDFTextStripper();
		String c = ts.getText(pdd);
		System.out.println(c);
		pdd.close();
		fis.close();
		return c;
	}
	
	/**
	 * 读取pdf文件
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static String readPdf(String path) throws Exception{
		File f=new File(path);
		return getContent(f);
	}

}
