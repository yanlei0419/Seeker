package org.vegetto.swing.stock.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

@SuppressWarnings("serial")
public class TableTools extends JFrame {
	// 声明控件

	public Vector<String> title, row;
	public Vector<Vector<String>> data;

	private List<String> rs;

	// 带结果集构造方法
	public TableTools(List<String>  rs) {
		this.rs = rs;
	}

	// 传送查询出来的表头
	public Vector<String> toTitle() {
		//			// 处理结果
		//			ResultSetMetaData rmd = rs.getMetaData();
					title = new Vector<String>();
					data = new Vector<Vector<String>>();
					// 标题栏
		//			for (int i = 1; i <= rmd.getColumnCount(); i++) {
		//				title.add(rmd.getColumnName(i));
		//				// System.out.println(rmd.getColumnName(i));
					title.add("测试");
		//			}
					return title;

	}

	// 传入每次更新的二维集合
	public Vector<Vector<String>> toData() {
		// 处理结果
		// ResultSetMetaData rmd = rs.getMetaData();
		data = new Vector<Vector<String>>();
		// 处理结果
		// while (rs.next()) {
		// row = new Vector<String>();
		// for (int j = 1; j <= rmd.getColumnCount(); j++) {
		// row.add(rs.getString(j));
		// System.out.println(rs.getString(j));
		// }
		// data.add(row);
		// }
		return data;

	}

	// 自动分配列宽的函数
	public void toTable(JTable table) {
		JTableHeader header = table.getTableHeader(); // 表头
		int rowCount = table.getRowCount(); // 表格的行数
		TableColumnModel cm = table.getColumnModel(); // 表格的列模型
		for (int i = 0; i < cm.getColumnCount(); i++) { // 循环处理每一列
			TableColumn column = cm.getColumn(i); // 第i个列对象
			int width = (int) header.getDefaultRenderer().getTableCellRendererComponent(table, column.getIdentifier(), false, false, -1, i).getPreferredSize().getWidth(); // 用表头的绘制器计算第i列表头的宽度
			for (int row = 0; row < rowCount; row++) { // 循环处理第i列的每一行，用单元格绘制器计算第i列第row行的单元格宽度
				int preferedWidth = (int) table.getCellRenderer(row, i).getTableCellRendererComponent(table, table.getValueAt(row, i), false, false, row, i).getPreferredSize().getWidth();
				width = Math.max(width, preferedWidth); // 取最大的宽度
			}
			column.setPreferredWidth(width + table.getIntercellSpacing().width); // 设置第i列的首选宽度
		}
		table.doLayout(); // 按照刚才设置的宽度重新布局各个列
	}

	// 将查询出来的结果转换成集合存储
	public Vector<String> toRow() {
		// 处理结果
		row = new Vector<String>();
		row.add("----请选择----");
		row.addAll(rs);
		// 处理结果
		// while (rs.next()) {
		// row.add(rs.getString(1));
		// }
		return row;
	}
}
