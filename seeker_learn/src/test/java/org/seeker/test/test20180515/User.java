package seeker.test.test20180515;

public class User{
    private final String s;

    public User(String s) {
        this.s = s;
    }

    public String getS() {
        return s;
    }


    public static void main(String[] args) {
        String s0 = "kvill";
        String s1 = new String("kvill");
        String s2 = "kv" + new String("ill");

        String s = "ill";
        String s3 = "kv" + s;


        System.out.println(s0 == s1);       // false
        System.out.println(s0 == s2);       // false
        System.out.println(s1 == s2);       // false
        System.out.println(s0 == s3);       // false
        System.out.println(s1 == s3);       // false
        System.out.println(s2 == s3);       // false

    }
}
