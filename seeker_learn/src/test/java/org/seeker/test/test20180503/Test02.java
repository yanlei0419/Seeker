package seeker.test.test20180503;

public class Test02 {

    public static void main(String[] args) {
        System.out.println(0x90);
        for (byte i=Byte.MIN_VALUE;i<Byte.MAX_VALUE;i++){
            System.out.println(i);

            if(i==0x90){
                System.out.println("1111");
            }
        }
    }
}
