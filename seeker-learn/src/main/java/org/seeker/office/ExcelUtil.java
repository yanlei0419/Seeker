package org.seeker.office;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

public class ExcelUtil {
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
	public static List<Map<String, String>> inExcel(InputStream inputstream) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			// // 得到工作表
			HSSFWorkbook book = new HSSFWorkbook(inputstream);
			// sheet
			for (int i = 0; i < book.getNumberOfSheets(); i++) {
				// // 得到第一页
				HSSFSheet sheet = book.getSheetAt(i);
				// // 得到第一面的所有行
				Iterator<Row> rows = sheet.rowIterator();
				// 得到第一行的所有列
				for (Row row : sheet) {
					Iterator<Cell> cellTitle = row.cellIterator();
					// 从标题第一列开始
					// 循环标题所有的列
					int a = 1;
					Map<String, String> entity = new HashMap<String, String>();
					while (cellTitle.hasNext()) {
						Cell cell = cellTitle.next();
						cell.setCellType(Cell.CELL_TYPE_STRING);
						String value = cell.getStringCellValue();
						entity.put(a++ + "", value);
					}
					list.add(entity);
				}
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void outExcelNew(File file, String[] titleRow, List<Map<String, String>> list) throws Exception {

		Workbook wb = null;
		Sheet sheet = null;
		// 创建工作文档对象
		if (!file.exists()) {
			wb = new HSSFWorkbook();
			// wb = new XSSFWorkbook();
			// 创建sheet对象
			sheet = (Sheet) wb.createSheet("sheet1");
			OutputStream outputStream = new FileOutputStream(file);
			wb.write(outputStream);
			outputStream.flush();
			outputStream.close();

		} else {
			wb = new HSSFWorkbook();
			// wb = new XSSFWorkbook();
		}
		// 创建sheet对象
		if (sheet == null) {
			sheet = (Sheet) wb.createSheet("sheet1");
		}

		// 添加表头
		Row row = sheet.createRow(0);
		Cell cell = row.createCell(0);
		row.setHeight((short) 540);
		cell.setCellValue("导出列表"); // 创建第一行

		CellStyle style = wb.createCellStyle(); // 样式对象
		// 设置单元格的背景颜色为淡蓝色
		style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);

		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 垂直
		style.setAlignment(CellStyle.ALIGN_CENTER);// 水平
		style.setWrapText(true);// 指定当单元格内容显示不下时自动换行

		cell.setCellStyle(style); // 样式，居中

		Font font = wb.createFont();
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		font.setFontName("宋体");
		font.setFontHeight((short) 280);
		style.setFont(font);
		// 单元格合并
		// 四个参数分别是：起始行，起始列，结束行，结束列
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));
		sheet.autoSizeColumn(5200);

		row = sheet.createRow(1); // 创建第二行
		for (int i = 0; i < titleRow.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(titleRow[i]);
			cell.setCellStyle(style); // 样式，居中
			sheet.setColumnWidth(i, 20 * 256);
		}
		row.setHeight((short) 540);

		// 循环写入行数据
		for (int i = 0; i < list.size(); i++) {
			Map<String, String> map = list.get(i);
			Set<String> set = map.keySet();

			row = (Row) sheet.createRow(i + 2);
			row.setHeight((short) 500);

			for (String s : set) {
				int keyI = Integer.parseInt(s);
				row.createCell(keyI).setCellValue(map.get(s));
			}
		}

		// 创建文件流
		OutputStream stream = new FileOutputStream(file);
		// 写入数据
		wb.write(stream);
		// 关闭文件流
		stream.close();
	}
}
