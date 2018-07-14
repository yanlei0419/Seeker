package org.seeker.coding.sql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.seeker.coding.sql.entity.SqlColumn;



public class SQLCode {
	
	public static SqlColumn getSqlColumn(DatabaseMetaData dbMeta){
		SqlColumn sqlColumn=new SqlColumn();
		return sqlColumn;
	}

	public static void tableDesc(String tableName) throws Exception {
		String sql = "select * from " + tableName;
		tableName=tableName.toUpperCase();
		//TODO 数据库连接
		Connection con = DBConnection.getConnection();
		DatabaseMetaData dbMeta = con.getMetaData();
		PreparedStatement ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS );

		ResultSet tableRs= dbMeta.getTables("", "", "vegetto", new String[]{"TABLE"});
		while (tableRs.next()) {
			System.err.println("****** Comment ******");
			System.err.println("TABLE_NAME : " + tableRs.getString(3));
			System.err.println("****** ******* ******");
		}

		ResultSet pkRSet = dbMeta.getPrimaryKeys(null, null,"SYS_USER" );
		while (pkRSet.next()) {
			System.err.println("****** Comment ******");
			System.err.println("TABLE_CAT : " + pkRSet.getObject(1));
			System.err.println("TABLE_SCHEM: " + pkRSet.getObject(2));
			System.err.println("TABLE_NAME : " + pkRSet.getObject(3));
			System.err.println("COLUMN_NAME: " + pkRSet.getObject(4));
			System.err.println("KEY_SEQ : " + pkRSet.getObject(5));
			System.err.println("PK_NAME : " + pkRSet.getObject(6));
			System.err.println("****** ******* ******");
		}

	}
}
// rs.getMetaData().getColumnCount();//取得指定数据表的字段总数，返回值为Int型
//
// rs.getMetaData().getColumnName(n);//取得第n个字段的名称，返回值为String型
//
// rs.getMetaData().getColumnLabel(n);//返回n所对应的列的显示标题
//
// rs.getMetaData().getColumnDisplaySize(n);//缺的第n个字段的长度，返回值为Int型
//
// rs.getMetaData().getColumnTypeName（n）;//返回第n个字段的数据类型
//
// rs.getMetaData().isReadOnly(n);//返回该n所对应的列是否只读.
//
// rs.getMetaData().isNullable(n)返回该n所对应的列是否可以为空.
//
// Rs.getMetaData().getSchemaName(n)n列的模式
//
// Rs.getMetaData().getPrecision(n);取得第n列字段类型长度的精确度
//
// Rs.getMetaDta().getScale(n);第n列小数点后的位数
//
// Rs.getMetaData().isAutoIncrement(n);第n列是否为自动递增
//
// Rs.getMetaData().isCurrency(n);是否为货币类型
//
// Rs.getMetaData().isSearchable(n);n列能否出现在where语句中.

// rs.getMetaData().getColumnCount();//取得指定数据表的字段总数，返回值为Int型
//
// rs.getMetaData().getColumnName(n);//取得第n个字段的名称，返回值为String型
//
// rs.getMetaData().getColumnLabel(n);//返回n所对应的列的显示标题
//
// rs.getMetaData().getColumnDisplaySize(n);//缺的第n个字段的长度，返回值为Int型
//
// rs.getMetaData().getColumnTypeName（n）;//返回第n个字段的数据类型
//
// rs.getMetaData().isReadOnly(n);//返回该n所对应的列是否只读.
//
// rs.getMetaData().isNullable(n)返回该n所对应的列是否可以为空.
//
// Rs.getMetaData().getSchemaName(n)n列的模式
//
// Rs.getMetaData().getPrecision(n);取得第n列字段类型长度的精确度
//
// Rs.getMetaDta().getScale(n);第n列小数点后的位数
//
// Rs.getMetaData().isAutoIncrement(n);第n列是否为自动递增
//
// Rs.getMetaData().isCurrency(n);是否为货币类型
//
// Rs.getMetaData().isSearchable(n);n列能否出现在where语句中.

