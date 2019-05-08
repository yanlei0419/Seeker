package org.seeker.test.test20190121;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Test {

    static class Inner{
        static int one=5;
        static final int two=5;
        static final int three=new Integer(5);

    }

    public void Test(){

    }

    public static void main(String[] args) {
        System.out.println(Test.Inner.one);
        System.out.println(Test.Inner.two);
        System.out.println(Test.Inner.three);
        
        int j=0;
        for (int i = 0; i < 100; i++) {
            j=j++;
        }
        System.out.println(j);

        int [] a=new int[]{1,2,3,4};
        List list= Arrays.asList(a);
        System.out.println(list.size());
        int [] b= (int[]) list.get(0);
        System.out.println(Arrays.toString(b));

        new Date().getTime();
        Calendar.getInstance().getTime().getTime();
//        new Timestamp().getTime();


        String str1='a'+3+"hello";
        String str2 ="hello"+'a'+3;
        System.out.println(str1);
        System.out.println(str2);
        System.out.println('a'+0);
        System.out.println('a');

        double d1=2.0,d2=2.1;
        double d3=d2-d1;
        System.out.println(d3);
        System.out.println(d2-d1); 
        
        String s = null;
        System.out.println("s="+s);
        
        System.out.println("5"+2);


    }
    static String val="1";
}
