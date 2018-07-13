package org.vegetto.coding.pdm.pdm;

import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.regexp.internal.RE;
import com.sun.org.apache.regexp.internal.RESyntaxException;

public class Column {

    private String id = "";
    private String name = "";
    private String code = "";
    private String type = "VARCHAR2";
    private String comment = "";
    private String mandatory = "0";
    private String length = "0";
    private int scale = 0;
    private String ptype = "string";
    private String defaultval = "\"\"";
    private String showstyle = "text";
    private String editstyle = "text";
    private String sign = "";
    private String pname = "";
    private String power = "";
    private String isnull = "y";//是否允许为空
    
    private String propertyDesc = "";//java属性说明
    private String relateInfo = "";//与其他表的关联信息
    

    public void setId(String val) { id = val; }
    public String getId() { return id; }

    public void setSign(String val) {
        if (sign.indexOf(val) < 0) {
            sign = sign + val;
        }
    }
    public void setName(String val) {
        name = val;
    }
    public String getName() { return name; }

    public void setCode(String val) {
        code = val;
        if (pname.length() == 0) {
            pname = code;
        }
    }
    public String getCode() { return code; }
    
    public String getPname(){ return pname; }

    public void setType(String val) {
        String patternStr = "(\\w+)(\\(([0-9]+)(,([0-9]+))*\\))*";
        List l = new ArrayList();

        RE pattern = null;
		try {
			pattern = new RE(patternStr);
		} catch (RESyntaxException e) {
			e.printStackTrace();
		}

        if (val != null && pattern.match(val)) {
            for (int i = 0; i < 6; i ++) {
                String s = pattern.getParen(i);
                if (s == null)
                    break;
                if (i % 2 == 1)
                    l.add(s);
            }
        }
        //System.out.println(l);
        if (l.size() > 0) {
            type = (String)l.get(0);
        }
        if (l.size() > 1) {
            length = (String)l.get(1);
        }
        if (l.size() > 2) {
            scale = Integer.parseInt((String)l.get(2));
        }
        ptype = (String)PdmTool.typedic.get(type.toUpperCase());
        if(ptype == null) {
        	System.out.println("---------- > " + type + "  不存在");
        }
        if (ptype.equals("int,double")) {
            if (scale > 0) {
                ptype = "double";
                length = "" + scale;
            } else {
                ptype = "int";
            }
        }
        if (defaultval.equals("\"\"")) {
            defaultval = (String)PdmTool.valdic.get(ptype);
        }
    }
    public String getType() { return type; }

    public void setComment(String val) {
        comment = val;
        String[] arr = val.split("::");
        if (arr.length >= 3) {
            comment = arr[0];
            propertyDesc = arr[2];
            String[] arr1 = arr[1].split(",");
            if (arr1.length == 2) {
                showstyle = arr1[0];
                editstyle = arr1[1];
            } else if (arr1.length == 3) {
                showstyle = arr1[0];
                editstyle = arr1[1];
                relateInfo = arr1[2];//从其他表中取值的定义方式
            }
            int pos = showstyle.indexOf("*");
            if (pos >= 0) {
                setSign("i");
                //showstyle = showstyle.substring(0, pos) + showstyle.substring(pos + 1);
            }
            pos = editstyle.indexOf("*");
            if (pos >= 0) {
                //editstyle = editstyle.substring(0, pos);
                power = editstyle.substring(pos + 1);
            }
        }

    }
    public String getComment() { return comment; }

    public void setLength(String val) { length = val; }
    public String getLength() { return length; }

    public void setDefaultValue(String val) { defaultval = val; }
    public String getDefaultValue() { return defaultval; }

    public void setMandatory(String val) {
        mandatory = val;
        if (mandatory.equals("1")) {
            isnull = "n";
        }
    }
    public String getMandatory() { return mandatory; }

    public String toDef() {
        StringBuffer rtn = new StringBuffer();
        rtn.append(sign);
        System.out.println(" -----   sign="+sign +"			pname="+pname);
        rtn.append(",,,," + pname).append("," + code.toLowerCase());
        rtn.append("," + ptype).append("," + length).append("," + isnull).append("," + defaultval).append("," + showstyle).append("," + editstyle).append("," + power).append("," + comment).append("\r\n");
        return rtn.toString();
    }
    /**
     * @return
     */
    public String toXml() {
    	StringBuffer rtn = new StringBuffer();
    	rtn.append("			<Column>\r\n");
    	rtn.append("				<PropertyName>"+pname+"</PropertyName>\r\n");//属性名
    	rtn.append("				<PropertyCnName>"+comment+"</PropertyCnName>\r\n");//属性中文名[页面显示会用到]
    	rtn.append("				<PropertyType>"+ptype+"</PropertyType>\r\n");//属性类型
    	rtn.append("				<PropertyDesc>"+propertyDesc+"</PropertyDesc>\r\n");//属性说明信息
    	rtn.append("				<ShowStyle>"+showstyle+"</ShowStyle>\r\n");//属性显示方式
    	rtn.append("				<EditStyle>"+editstyle+"</EditStyle>\r\n");//属性编辑方式
    	rtn.append("				<RelateInfo>"+relateInfo+"</RelateInfo>\r\n");//关联信息
    	rtn.append("				<FieldName>"+code+"</FieldName>\r\n");//
    	rtn.append("				<FieldDesc>"+name+"</FieldDesc>\r\n");//
    	rtn.append("				<FieldLength>"+length+"</FieldLength>\r\n");//字段长度
    	rtn.append("				<FieldIsNull>"+isnull+"</FieldIsNull>\r\n");//字段是否可为空
    	rtn.append("				<FieldDefaultValue>"+defaultval+"</FieldDefaultValue>\r\n");//字段默认值
    	rtn.append("			</Column>\r\n");
    	return rtn.toString();
    }

    public String toString() {
        StringBuffer rtn = new StringBuffer();
        rtn.append("Column name:" + name + "|").append("code:" + code + "|").append("type:" + type + "|").append("mandatory:" + mandatory + "|").append("comment:" + comment);
        return rtn.toString();
    }
}
