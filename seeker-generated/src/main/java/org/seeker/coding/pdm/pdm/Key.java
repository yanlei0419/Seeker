package org.seeker.coding.pdm.pdm;

import java.util.ArrayList;
import java.util.List;

public class Key
{

    private String id = "";
    private List columns = new ArrayList();

    public Key()
    {
    }

    public void setId(String val) { id = val; }
    public String getId() { return id; }

    public void setColumn(String val) {
        if (val != null) {
            columns.add(val);
        }
    }
    public List getColumns() { return columns; }
}
