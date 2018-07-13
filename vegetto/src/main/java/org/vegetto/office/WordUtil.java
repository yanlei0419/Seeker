package org.vegetto.office;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.POIXMLProperties.CoreProperties;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class WordUtil {
	
	/**
	 *    XWPFParagraph：代表一个段落。

       XWPFRun：代表具有相同属性的一段文本。

       XWPFTable：代表一个表格。

       XWPFTableRow：表格的一行。

       XWPFTableCell：表格对应的一个单元格。
	 * @throws Exception 
	 */

	 
	   /**
	    * 通过XWPFWordExtractor访问XWPFDocument的内容
	    * @throws Exception
	    */
	   public static void ReadByExtractor(String path) throws Exception {
	      InputStream is = new FileInputStream(path);
	      XWPFDocument doc = new XWPFDocument(is);
	      XWPFWordExtractor extractor = new XWPFWordExtractor(doc);
	      String text = extractor.getText();
	      System.out.println(text);
	      CoreProperties coreProps = extractor.getCoreProperties();
	      printCoreProperties(coreProps);
	      close(is);
	   }
	  
	   /**
	    * 输出CoreProperties信息
	    * @param coreProps
	    */
	   private static void printCoreProperties(CoreProperties coreProps) {
	      System.out.println(coreProps.getCategory());   //分类
	      System.out.println(coreProps.getCreator()); //创建者
	      System.out.println(coreProps.getCreated()); //创建时间
	      System.out.println(coreProps.getTitle());   //标题
	   }
	  

	   
	   /**
	    * 通过XWPFDocument对内容进行访问。对于XWPF文档而言，用这种方式进行读操作更佳。
	    * @throws Exception
	    */
	   public static void ReadByDoc(String path) throws Exception {
	      InputStream is = new FileInputStream(path);
	      XWPFDocument doc = new XWPFDocument(is);
	      List<XWPFParagraph> paras = doc.getParagraphs();
	      for (XWPFParagraph para : paras) {
	         //当前段落的属性
//	       CTPPr pr = para.getCTP().getPPr();
	         System.out.println(para.getText());
	      }
	      //获取文档中所有的表格
	      List<XWPFTable> tables = doc.getTables();
	      List<XWPFTableRow> rows;
	      List<XWPFTableCell> cells;
	      for (XWPFTable table : tables) {
	         //表格属性
//	       CTTblPr pr = table.getCTTbl().getTblPr();
	         //获取表格对应的行
	         rows = table.getRows();
	         for (XWPFTableRow row : rows) {
	            //获取行对应的单元格
	            cells = row.getTableCells();
	            for (XWPFTableCell cell : cells) {
	                System.out.println(cell.getText());;
	            }
	         }
	      }
	      close(is);
	   }
	  
	   /**
	    * 关闭输入流
	    * @param is
	    */
	   private static void close(InputStream is) {
	      if (is != null) {
	         try {
	            is.close();
	         } catch (IOException e) {
	            e.printStackTrace();
	         }
	      }
	   }


}
