package org.seeker.coding.pdm.xml;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MyTables {
	private Map tableMap = new LinkedHashMap();
    private List<MyTable> tables = new ArrayList();
    
    public void addTable(MyTable t) {
        if (t != null) {
            tableMap.put(t.getClassName(), t);
            tables.add(t);
        }
    }
    
	public Map getTableMap() {
		return tableMap;
	}
	public void setTableMap(Map tableMap) {
		this.tableMap = tableMap;
	}
	public List<MyTable> getTables() {
		return tables;
	}
	public void setTables(List<MyTable> tables) {
		this.tables = tables;
	}
}
