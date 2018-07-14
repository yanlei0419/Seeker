package seeker.test.Test20180712.ve;

public class ConstClass{

    static{
        System.out.println("ConstClass init!");
    }

    public static final String CONSTANT = "hello world";
}

class NotInitialization1{
    public static void main(String[] args){
        System.out.println(ConstClass.CONSTANT);
    }
}/* Output: 
        hello world
 *///:~