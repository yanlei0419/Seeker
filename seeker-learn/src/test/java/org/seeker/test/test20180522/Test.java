package org.seeker.test.test20180522;

public class Test {
    public static void main(String[] args) {

        System.out.println(Test.class.getClassLoader().getResourceAsStream(""));
        System.out.println(Test.class.getResourceAsStream("/"));

        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);

        String str2 = new StringBuilder("java").toString();
        System.out.println(str2.intern() == str2);
    }
}

