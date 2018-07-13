package org.vegetto.coding.pdm.pdm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Table {
	//以下几个属性用来存储从pdm文件中解析到的数据
    private String id = "";
    private String name = "";
    private String code = "";
    private String comment = "";//写的注释
    private String pkeyid = "";//主键
    
	//生成新的xml时要用到的属性
    private String dbName = "";//数据库名
    private String className = "";//java类名
    private String classDesc = "";//java类中文名
    private String primaryKey = "";//主键列名
    private String tableName = "";//表名
    private String tableDesc = "";//表中文名
    private String packagePath = "";//包路径 创建java文件时会自动在该路径前加com.dhcc
    private String urlPath = "";//jsp文件路径，相对于WebRoot的路径
    
    //下面这几个还没搞清楚
    private Map columnMap = new HashMap();
    private List columns = new ArrayList();
    private List keys = new ArrayList();
    

    //set get 方法
    public void setId(String val) { id = val; }
    public String getId() { return id; }
    
    public void setName(String val) { 
    	name = val;
    	tableDesc = name;
    }
    public String getName() { return name; }
    
    public void setCode(String val) { 
    	code = val;
    	tableName = code;
    }
    public String getCode() { return code; }
    
    //commet在set的时候，自动为其他几个属性赋值
    public void setComment(String val) {
        comment = val.replaceAll("\r|\n", "");
        String[] arr = val.split("::");
        if (arr.length >= 4) {
        	classDesc = arr[0];//java类中文说明
            packagePath = arr[2];//包路径
            urlPath = arr[3];//jsp文件路径
            String[] arr1 = arr[1].split(",");
            if (arr1.length == 1) {
            	dbName = arr1[0];//数据库库名
            } else if (arr1.length == 2) {
                dbName = arr1[0];//数据库库名
                className = arr1[1];//java类名
            }
        }
    }
    public String getComment() { return comment; }

    //获得主键
    public void setPkeyid(String val) {
    	pkeyid = val;
        for (Iterator it = keys.iterator(); it.hasNext();) {
            Key k = (Key)it.next();
            if (k.getId().equals(pkeyid)) {
                for (Iterator kit = k.getColumns().iterator(); kit.hasNext();) {
                    Column c = getColumnById((String)kit.next());
                    if (c != null) {
                        c.setSign("k");
                        primaryKey = c.getPname();
                    }
                }
                break;
            }
        }
    }
    public String getPkeyid() { return pkeyid; }
    
    public void addKey(Key t) {
        if (t != null) {
            keys.add(t);
        }
    }

    public void addColumn(Column t) {
        if (t != null) {
            columnMap.put(t.getId(), t);
            columns.add(t);
        }
    }
    
    public Column getColumnById(String id) {
        if (columnMap.containsKey(id)) {
            return (Column) columnMap.get(id);
        } else {
            return null;
        }
    }

    public String toString() {
        StringBuffer rtn = new StringBuffer();
        rtn.append("Table name:" + name + "|").append("code:" + code + "|").append("comment:" + comment + "\r\n");
        for (Iterator it = columns.iterator(); it.hasNext();) {
            Column item = (Column)it.next();
            rtn.append(item.toString()).append("\r\n");
        }
        return rtn.toString();
    }

    /*public String toDef() {
        StringBuffer rtn = new StringBuffer();
        if (className.length() == 0) {
        	className = code;
        }
        if (dbname.length() > 0) {
            code = dbname + "." + code;
        }
        rtn.append("#\r\n");
        rtn.append("jdbc/ds," + classname).append("," + code.toLowerCase());
        if (comment == null || comment.length() == 0)
            rtn.append("," + name + "\r\n");
        else
            rtn.append("," + comment + "\r\n");
        for (Iterator it = columns.iterator(); it.hasNext();) {
            Column item = (Column)it.next();
            rtn.append(item.toDef());
        }
        //rtn.append("\r\n");
        return rtn.toString();
    }*/
    
    /**
     * 生成Xml格式的文件
     * @return
     */
    public String toXml() {
    	StringBuffer rtn = new StringBuffer();
        rtn.append("	<Table>\r\n");
        rtn.append("		<DBName>"+ dbName +"</DBName>\r\n");
        rtn.append("		<ClassName>"+ className +"</ClassName>\r\n");
        rtn.append("		<ClassDesc>"+ classDesc +"</ClassDesc>\r\n");
        rtn.append("		<TableName>"+ tableName +"</TableName>\r\n");
        rtn.append("		<TableDesc>"+ tableDesc +"</TableDesc>\r\n");
        rtn.append("		<PrimaryKey>"+ primaryKey +"</PrimaryKey>\r\n");
        rtn.append("		<PackagePath>"+ packagePath +"</PackagePath>\r\n");
        rtn.append("		<UrlPath>"+ urlPath +"</UrlPath>\r\n");
        rtn.append("		<Columns>\r\n");
        
        for (Iterator it = columns.iterator(); it.hasNext();) {
            Column item = (Column)it.next();
            rtn.append(item.toXml());
        }
        
        rtn.append("		</Columns>\r\n");
        rtn.append("	</Table>\r\n");
        return rtn.toString();
    }
}
