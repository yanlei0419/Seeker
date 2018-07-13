package org.vegetto.coding.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.vegetto.coding.excel.entity.VeColumn;
import org.vegetto.coding.pdm.xml.MyTable;
import org.vegetto.coding.pdm.xml.MyTables;
import org.vegetto.coding.util.InitCodeConfig;
import org.vegetto.coding.util.excel.ExcelUtil;
import org.vegetto.coding.util.print.PrintUtils;

public class CodeExcel {


	/**
	 * 导入 excel
	 * 
	 * @param inputstream
	 *            : 文件输入流
	 * @param pojoClass
	 *            : 对应的导入对象 (每行记录)
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static MyTables toExcelByIs(InputStream inputstream) {
		MyTables tabs = new MyTables();
		try {
		
			Map map=ExcelUtil.getMapByClazz(VeColumn.class);
			// // 得到工作表
			HSSFWorkbook book = new HSSFWorkbook(inputstream);
			
			for (int i = 0; i < book.getNumberOfSheets(); i++) {
				// // 得到第一页
				HSSFSheet sheet = book.getSheetAt(i);
				MyTable table=getMyTableByRow(sheet,map);
				tabs.addTable(table);
				PrintUtils.S("表明 =-=-=-="+table.getTableName()+"=-=-=-=-="+table.getClassDesc());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return tabs;
	}
	
	private  static MyTable getMyTableByRow(HSSFSheet sheet,Map map) throws Exception{
		MyTable table=new MyTable();
		// // 得到第一面的所有行
		Iterator<Row> rows = sheet.rowIterator();
		//NO.1
		Map<Integer,String> tableMap=ExcelUtil.getValueByRow(rows.next(), 2);//表名
		table.setTableName(tableMap.get(2));
		//NO.2
		Map<Integer,String> pageMap=ExcelUtil.getValueByRow(rows.next(), 2);//包名
		table.setPackagePath(pageMap.get(2));
		//NO.3
		Map<Integer,String> classMap=ExcelUtil.getValueByRow(rows.next(), 2);//类名
		table.setClassName(classMap.get(2));
		//NO.4
		Map<Integer,String> classDescMap=ExcelUtil.getValueByRow(rows.next(), 2);//模块名称
		table.setClassDesc(classDescMap.get(2));
		//NO.5
		Map<Integer,String> urlMap=ExcelUtil.getValueByRow(rows.next(), 2);//web路径
		table.setUrlPath(urlMap.get(2));
//		Map<Integer,String> keyMap=ExcelUtil.getValueByRow(rows.next(), 2);//主键
//		table.setPrimaryKey(keyMap.get(2));
		
		//NO.6
		for(int i=0;i<InitCodeConfig.excelNullNext;i++){
			rows.next();//空一行
		}
		
		// 得到第一行，也就是标题行
		Map<Integer,String> titlemap = ExcelUtil.getTitleByRow(rows.next());
		map.put("titlemap", titlemap);
		while (rows.hasNext()) {
			Row row = rows.next();
			VeColumn col =new VeColumn();
			ExcelUtil.getEntityByRow(col, map, row);
			if(col.getIsKey()!=null&&"T".equals(col.getIsKey())){
				table.setPrimaryKey(col.getTableColName());
				col.setShowStyle("hidden");
				col.setEditStyle("hidden");
			}
			table.addColumn(col.getMyColumn());
		}
		return table;
	}

	/**
	 * 导入 excel
	 * 
	 * @param file
	 *            : Excel文件
	 * @param pojoClass
	 *            : 对应的导入对象 (每行记录)
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static MyTables importExcel(File file) {
		try {
			// 将传入的File构造为FileInputStream;
			FileInputStream in = new FileInputStream(file);
			return toExcelByIs(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	
	
	
}
