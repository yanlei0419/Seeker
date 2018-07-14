package seeker.test.test20180509;

public class Parent {
    static{
        System.out.println("p static");
    }
    {
        System.out.println("p 块");
    }

    public Parent() {
        System.out.println("p C");
    }
}
