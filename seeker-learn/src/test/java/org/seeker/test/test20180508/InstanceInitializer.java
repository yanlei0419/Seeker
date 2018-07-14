package org.seeker.test.test20180508;

import org.seeker.common.base.entity.Page;

public class InstanceInitializer extends Page {
    private int i = 1;

    private int j = getI();  

    public InstanceInitializer() {
        i = 2;
    }

    private int getI() {  
        return i;  
    }  

    public static void main(String[] args) {  
        InstanceInitializer ii = new InstanceInitializer();  
        System.out.println(ii.j);  
    }

    public InstanceInitializer(int i) {
        this.i = i;
    }
}