package seeker.test.test20180503;

import java.util.Random;

public class Test04 {
    private static final Random rand=new Random();
    private static final int i4 = rand.nextInt(20);
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(i4);
        }
    }
}
