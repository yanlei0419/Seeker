package org.vegetto.coding.pdm.xml;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.digester.Digester;
import org.vegetto.coding.util.InitCodeConfig;
import org.xml.sax.SAXException;


public class XmlTool {

	/**
	 * @param args
	 * @throws SAXException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException, SAXException {
		MyTables ts = parserXml("f:\\tables.xml");
		System.out.println("ts = "+ts);
		System.out.println("ts.length = "+ts.getTableMap().size());
		Set<Map.Entry<String, MyTable>> set =  ts.getTableMap().entrySet();
		Iterator it = set.iterator();
		while(it.hasNext()) {
			Map.Entry<String, MyTable> entry = (Map.Entry<String, MyTable>)it.next();
			System.out.println("key="+entry.getKey());
			System.out.println("table="+entry.getValue());
			Set<Map.Entry<String, MyColumn>> columnMap = entry.getValue().getColumnMap().entrySet();
			Iterator it1 = columnMap.iterator();
			while(it1.hasNext()) {
				Map.Entry<String, MyColumn> entry1 = (Map.Entry<String, MyColumn>)it1.next();
			}
		}
		
		/*CreateJava cj = new CreateJava();
		List<MyTable> tables = ts.getTables();
		for(MyTable table : tables) {
			cj.startCreate(table, "f:/autoCreate1/sun/");
		}*/
		//cj.startCreate((MyTable)ts.getTables().get(0), "f:/");
	}
	
	
	//瑙ｆ瀽xml鏂囦欢锛屼繚瀛樺埌涓�釜MyTables瀵硅薄涓�
	public static MyTables xml2MyTables(String xml_path) throws IOException, SAXException {
		return parserXml(xml_path);
	}
	
	
	//浠巟ml鏂囦欢涓В鏋愬嚭鏉ワ紝骞朵繚瀛樺埌MyTables瀵硅薄涓�
	private static MyTables parserXml(String filename) throws IOException, SAXException {
		Digester digester = new Digester();
        digester.setValidating(false);
        digester.addObjectCreate("Tables",  InitCodeConfig.xmlPageName+"MyTables");
        digester.addSetProperties("Tables");
        
        //瑙ｆ瀽xml涓殑table瀵硅薄
        digester.addObjectCreate("Tables/Table",  InitCodeConfig.xmlPageName+"MyTable");
        digester.addBeanPropertySetter("Tables/Table/DBName", "dbName");
        digester.addBeanPropertySetter("Tables/Table/ClassName", "className");
        digester.addBeanPropertySetter("Tables/Table/ClassDesc", "classDesc");
        digester.addBeanPropertySetter("Tables/Table/TableName", "tableName");
        digester.addBeanPropertySetter("Tables/Table/TableDesc", "tableDesc");
        digester.addBeanPropertySetter("Tables/Table/PrimaryKey", "primaryKey");
        digester.addBeanPropertySetter("Tables/Table/PackagePath", "packagePath");
        digester.addBeanPropertySetter("Tables/Table/UrlPath", "urlPath");
        digester.addSetNext("Tables/Table", "addTable", InitCodeConfig.xmlPageName+"MyTable");
        
        //瑙ｆ瀽xml涓璫olumn瀵硅薄
        digester.addObjectCreate("Tables/Table/Columns/Column", InitCodeConfig.xmlPageName+"MyColumn");
        digester.addBeanPropertySetter("Tables/Table/Columns/Column/PropertyName", "propertyName");
        digester.addBeanPropertySetter("Tables/Table/Columns/Column/PropertyCnName", "propertyCnName");
        digester.addBeanPropertySetter("Tables/Table/Columns/Column/PropertyDesc", "propertyDesc");
        digester.addBeanPropertySetter("Tables/Table/Columns/Column/PropertyType", "propertyType");
        digester.addBeanPropertySetter("Tables/Table/Columns/Column/ShowStyle", "showStyle");
        digester.addBeanPropertySetter("Tables/Table/Columns/Column/EditStyle", "editStyle");
        digester.addBeanPropertySetter("Tables/Table/Columns/Column/RelateInfo", "relateInfo");
        digester.addBeanPropertySetter("Tables/Table/Columns/Column/FieldName", "fieldName");
        digester.addBeanPropertySetter("Tables/Table/Columns/Column/FieldDesc", "fieldDesc");
        digester.addBeanPropertySetter("Tables/Table/Columns/Column/FieldLength", "fieldLength");
        digester.addBeanPropertySetter("Tables/Table/Columns/Column/FieldIsNull", "fieldIsNull");
        digester.addBeanPropertySetter("Tables/Table/Columns/Column/FieldDefaultValue", "fieldDefaultValue");
        digester.addSetNext("Tables/Table/Columns/Column", "addColumn",  InitCodeConfig.xmlPageName+"MyColumn");
        
        
        MyTables ts = (MyTables) digester.parse(filename);
        return ts;
	}

}
