package org.seeker.test.test20180508;

public class Test01 {
    static boolean a(int a){
        return a%2!=0;
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = Integer.MIN_VALUE; i <=Integer.MAX_VALUE ; ++i) {
            boolean b=a(i);
            System.out.println(String.format("i=%d,a=%b",i,b));

        }
    }
}
