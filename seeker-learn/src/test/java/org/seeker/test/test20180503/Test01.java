package seeker.test.test20180503;

public class Test01 {
    static boolean a(int a){
//        return a%2==1;//负数求余为 -1  不是正1
//        return a%2!=0;//写法1
        return (a&1)==1;//写法2
    }

    public static void main(String[] args) throws InterruptedException {
//        for (int i = Integer.MIN_VALUE; i <=Integer.MAX_VALUE ; ++i) {
// 因为 ++1 先加后运算   int 最大值 +1 超出范围 会变成 int最小值 -负数 会出现无线循环的情况
//            boolean b=a(i);
//            System.out.println(String.format("i=%d,a=%b",i,b));
//        }
        for (int i = Integer.MAX_VALUE-10; i <Integer.MAX_VALUE ; ++i) {
            boolean b=a(i);
            System.out.println(String.format("i=%d,a=%b",i,b));
            Thread.sleep(1000);
        }
//        for (int i = Integer.MAX_VALUE-10; i <Integer.MAX_VALUE ; ++i) {
//            boolean b=a(i);
//            System.out.println(String.format("i=%d,a=%b",i,b));
//            Thread.sleep(1000);
//
//        }
    }
}
