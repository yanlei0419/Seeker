package org.seeker.test.test20180512;

import java.util.Arrays;

public class Test02 {
    public static void main(String[] args) {
        int [] i={1,2,3,4,5,6,7,8,9,0};
        int[] a=new int[i.length*2];
        System.arraycopy(i,1,a,5,3);

        System.out.println(Arrays.toString(i));
        System.out.println(Arrays.toString(a));

        String s="闫磊";
        System.out.println(s.codePointAt(1));

        System.out.println(s.length());
        StringBuilder sb=new StringBuilder(s);
        System.out.println(sb.length());

    }
}
