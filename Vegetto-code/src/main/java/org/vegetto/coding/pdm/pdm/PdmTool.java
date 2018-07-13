package org.vegetto.coding.pdm.pdm;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.digester.Digester;
import org.vegetto.coding.util.InitCodeConfig;
import org.xml.sax.SAXException;

public class PdmTool {
    public static Map typedic = null;
    public static Map valdic = null;
   
    static {
        typedic = new HashMap();
        typedic.put("INTEGER", "int");
        typedic.put("NUMBER", "int,double");
        typedic.put("FLOAT", "double");
        typedic.put("DECIMAL", "double");
        typedic.put("DOUBLE", "double");
        typedic.put("VARCHAR2", "string");
        typedic.put("VARCHAR", "string");
        typedic.put("TEXT", "string");
        typedic.put("CLOB", "clob");
        typedic.put("BLOB", "blob");
        typedic.put("DATE", "date");
        typedic.put("DATETIME", "date");
        typedic.put("TIMESTAMP", "date");
        typedic.put("CHAR", "string");

        valdic = new HashMap();
        valdic.put("int", "-1");
        valdic.put("double", "0.0");
        valdic.put("string", "\"\"");
        valdic.put("clob", "\" \"");
        valdic.put("blob", "new byte[0]");
        valdic.put("date", "new java.util.Date()");
    }
    //解析pdm文件，创建一个xml文件
    public static void pdm2Xml(String pdm_path, String xml_path) throws IOException {
    	//String xmlfile = "F:\\test\\util\\cms.pdm";
        String xmlfile = pdm_path;
        /*if (args.length > 0) {
            xmlfile = args[0];
        } else {
            System.out.println("?");
            return;
        }*/
        Tables ts = parserDefFile(xmlfile);
        //System.out.println(ts.toString());
        String str = ts.toDef();//解析pdm后的xml文件
        //System.out.println(str);//str中是生成的xml文件内容
        File f = new File(xml_path);
        if(!f.exists()) f.createNewFile();
        FileOutputStream pw = new FileOutputStream(f);
        pw.write(str.getBytes("UTF-8")); 
        pw.flush();
        pw.close();
    }

    public static String strTitle(String str) {
        if (str != null && str.length() > 0) {
            return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
        }
        return "";
    }

    public static String getTitleName(String name) {
        StringBuffer rtn = new StringBuffer();
        if (name.indexOf("_") >= 0) {
            String[] arr = name.split("_");
            for (int i = 0; i < arr.length; i ++) {
                rtn.append(strTitle(arr[i]));
            }
        } else {
            rtn.append(strTitle(name));
        }
        return rtn.toString();
    }

    public static Tables parserDefFile(String filename) {
        Digester digester = new Digester();
        digester.setValidating(false);
        digester.addObjectCreate("Model/o:RootObject/c:Children/o:Model/c:Tables", InitCodeConfig.pdmPageName+"Tables");
        digester.addSetProperties("Model/o:RootObject/c:Children/o:Model/c:Tables");

        digester.addObjectCreate("Model/o:RootObject/c:Children/o:Model/c:Tables/o:Table", InitCodeConfig.pdmPageName+"Table");
        digester.addSetProperties("Model/o:RootObject/c:Children/o:Model/c:Tables/o:Table");
        digester.addSetProperties("Model/o:RootObject/c:Children/o:Model/c:Tables/o:Table", "Id", "id");
        digester.addBeanPropertySetter("Model/o:RootObject/c:Children/o:Model/c:Tables/o:Table/a:Name", "name");
        digester.addBeanPropertySetter("Model/o:RootObject/c:Children/o:Model/c:Tables/o:Table/a:Code", "code");
        digester.addBeanPropertySetter("Model/o:RootObject/c:Children/o:Model/c:Tables/o:Table/a:Comment", "comment");
        digester.addSetNext("Model/o:RootObject/c:Children/o:Model/c:Tables/o:Table", "addTable", InitCodeConfig.pdmPageName+ "Table");

        digester.addObjectCreate("Model/o:RootObject/c:Children/o:Model/c:Tables/o:Table/c:Columns/o:Column",  InitCodeConfig.pdmPageName+"Column");
        digester.addSetProperties("Model/o:RootObject/c:Children/o:Model/c:Tables/o:Table/c:Columns/o:Column");
        digester.addSetProperties("Model/o:RootObject/c:Children/o:Model/c:Tables/o:Table/c:Columns/o:Column", "Id", "id");
        digester.addBeanPropertySetter("Model/o:RootObject/c:Children/o:Model/c:Tables/o:Table/c:Columns/o:Column/a:Name", "name");
        digester.addBeanPropertySetter("Model/o:RootObject/c:Children/o:Model/c:Tables/o:Table/c:Columns/o:Column/a:Code", "code");
        digester.addBeanPropertySetter("Model/o:RootObject/c:Children/o:Model/c:Tables/o:Table/c:Columns/o:Column/a:DataType", "type");
        //digester.addBeanPropertySetter("Model/o:RootObject/c:Children/o:Model/c:Tables/o:Table/c:Columns/o:Column/a:Length", "length");
        digester.addBeanPropertySetter("Model/o:RootObject/c:Children/o:Model/c:Tables/o:Table/c:Columns/o:Column/a:Comment", "comment");
        digester.addBeanPropertySetter("Model/o:RootObject/c:Children/o:Model/c:Tables/o:Table/c:Columns/o:Column/a:Mandatory", "mandatory");
        digester.addBeanPropertySetter("Model/o:RootObject/c:Children/o:Model/c:Tables/o:Table/c:Columns/o:Column/a:DefaultValue", "defaultValue");
        digester.addSetProperties("Model/o:RootObject/c:Children/o:Model/c:Tables/o:Table/c:PrimaryKey/o:Key", "Ref", "pkeyid");
        digester.addSetNext("Model/o:RootObject/c:Children/o:Model/c:Tables/o:Table/c:Columns/o:Column", "addColumn", InitCodeConfig.pdmPageName+ "Column");

        digester.addObjectCreate("Model/o:RootObject/c:Children/o:Model/c:Tables/o:Table/c:Keys/o:Key",  InitCodeConfig.pdmPageName+"Key");
        digester.addSetProperties("Model/o:RootObject/c:Children/o:Model/c:Tables/o:Table/c:Keys/o:Key");
        digester.addSetProperties("Model/o:RootObject/c:Children/o:Model/c:Tables/o:Table/c:Keys/o:Key", "Id", "id");
        digester.addSetProperties("Model/o:RootObject/c:Children/o:Model/c:Tables/o:Table/c:Keys/o:Key/c:Key.Columns/o:Column", "Ref", "column");
        digester.addSetNext("Model/o:RootObject/c:Children/o:Model/c:Tables/o:Table/c:Keys/o:Key", "addKey",  InitCodeConfig.pdmPageName+"Key");
        try {
        	File file=new File(filename);
            Tables ts = (Tables) digester.parse(file);
            return ts;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return null;
    }
}
