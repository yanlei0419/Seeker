package org.seeker.test.test20180920;

import java.io.*;

public class Test {
    public static void main(String[] args) throws Exception {
        File file =new File("resources.properties");
//        OutputStream os=new FileOutputStream(file);
//        InputStream is=new FileInputStream(file);
        BufferedReader br=new BufferedReader(new FileReader(file));
        String val=null;
        while((val=br.readLine())!=null){
            System.out.println(val);

        }
    }
}
