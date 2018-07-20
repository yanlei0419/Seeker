package org.seeker.test.test20180720;

public class Test {
    public static void main(String[] args) {
        String s=new String("a");
        String s1=s.intern();
        String s2="a";
        System.out.println(s1==s2);//true






//        String str2 = "ab";  //1个对象
//        String str3 = "cd";  //1个对象
//        String str4 = str2+str3;
//        String str5 = "abcd";
//        System.out.println("str4 = str5 : " + (str4==str5)); // false



//  (1). 调用 String 类的静态方法 String.valueOf() 将 str2 转换为字符串表示；
//　(2). JVM 在堆中创建一个 StringBuilder对象，同时用str2指向转换后的字符串对象进行初始化；　
//　(3). 调用StringBuilder对象的append方法完成与str3所指向的字符串对象的合并；
//　(4). 调用 StringBuilder 的 toString() 方法在堆中创建一个 String对象；
//　(5). 将刚刚生成的String对象的堆地址存赋给局部变量引用str4。
    }
}
