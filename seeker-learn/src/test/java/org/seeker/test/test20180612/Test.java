package org.seeker.test.test20180612;

public class Test {

    public enum ColorSelect {
        red, green, yellow, blue;
    }

    private static ColorSelect getE() {
        ColorSelect c = ColorSelect.blue;
        return c;
    }

    public static void main(String[] args) {



        System.out.println(ColorSelect.red);
        System.out.println(ColorSelect.yellow);
        System.out.println(ColorSelect.green);
        System.out.println(ColorSelect.blue);


        Object o = getE();
        if (o instanceof Enum) {
            System.out.println(o);
        }
    }
}