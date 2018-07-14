package org.seeker.coding.pdm.pdm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Tables
{
    private Map tableMap = new HashMap();
    private List tables = new ArrayList();

    public Tables()
    {
    }

    public void addTable(Table t) {
        if (t != null) {
            tableMap.put(t.getId(), t);
            tables.add(t);
        }
    }

    public Table getTableById(String id) {
        if (tableMap.containsKey(id)) {
            return (Table) tableMap.get(id);
        } else {
            return null;
        }
    }
    
    public String toDef() {
        StringBuffer rtn = new StringBuffer();
        rtn.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
        rtn.append("<Tables>\r\n");
        for (Iterator it = tables.iterator(); it.hasNext();) {
            Table item = (Table)it.next();
            //tlw 2013.4.18 这里判断，如果用powerdisigner设计表是没有写类名，则不生成到xml文件中（这种情况是不需要自动生成的文件）
            if(item.getComment().split("::").length<3) {
            	System.out.println(item.getName()+"				---- 不需要生成 或 不符合设计要求");
            	continue;
            }	
            rtn.append(item.toXml());
        }
        rtn.append("</Tables>");
        return rtn.toString();
    }

    public String toString() {
        StringBuffer rtn = new StringBuffer();
        for (Iterator it = tables.iterator(); it.hasNext();) {
            Table item = (Table)it.next();
            rtn.append(item.toString()).append("\r\n");
        }
        return rtn.toString();
    }
}
