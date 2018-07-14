package org.seeker.test.test20180509;

public class Son extends Parent{
    static{
        System.out.println("Son static");
    }
    {
        System.out.println("Son 块");
    }

    public Son() {
        System.out.println("Son c");
    }


    public static void main(String[] args) {
        Son s=new Son();
    }
}
