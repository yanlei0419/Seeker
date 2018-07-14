package seeker.test.test20180709;

import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapTest {
    public static void main(String[] args) {
        Map<String,String> map=new Hashtable<>();
        Map<String,String> map1=new ConcurrentHashMap<>();

//        map.put(null,"111");
//        map1.put(null,"111");


        int a=128;
        byte b= (byte) a;
        
        System.out.println(b);


    }
}
