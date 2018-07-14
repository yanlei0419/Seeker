package seeker.test.Test20180712;

import org.seeker.entity.User;

public class Test {
    public static void main(String[] args) {
        User u=new User();
        System.out.println(u.getClass().getClassLoader());
        Object o=new Object();

        System.out.println(o.getClass().getClassLoader());
        System.out.println(System.getProperty("java.class.path"));
    }

}
