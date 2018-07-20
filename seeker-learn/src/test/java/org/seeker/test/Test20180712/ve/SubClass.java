package org.seeker.test.Test20180712.ve;

public class SubClass extends SClass{
    static{
        System.out.println("SubClass init");
    }

    static int a;
    final static int b=0;

    public SubClass(){
        System.out.println("init SubClass");
    }
}