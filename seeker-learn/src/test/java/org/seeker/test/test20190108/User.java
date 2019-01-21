package org.seeker.test.test20190108;

public class User {

    public  String username;
    public  String password;
    public final Org o=new Org();

    public static class User1{

    }

    public static void main(String[] args) {
        String str1 = "hello";
        String str2 = "he" + new String("llo");
        System.err.println(str1 == str2);
        int i=0;
//        User u=new User();

//        while(true){
//
//            System.out.println("第一层");
//            a:while(true){
//                i++;
//                System.out.println("第二层");
//                if(i>10){
//                    while(true){
//                        System.out.println("3333333333333333333");
//                        break a;
//                    }
//                }
//
//            }
//            break;
//        }
        System.out.println(a());

    }

    static int a(){
        try {
            return 1;
        }finally{
            return 2;
        }
    }

}
