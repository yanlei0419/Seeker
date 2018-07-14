package org.seeker.test.Test20180712;

import java.net.URL;
import java.net.URLClassLoader;  

public class ClassLoaderTest {  

    /** 
     * @param args the command line arguments 
     */  
    public static void main(String[] args) {  
        try {  
            URL[] extURLs = ((URLClassLoader) ClassLoader.getSystemClassLoader().getParent()).getURLs();  
            for (int i = 0; i < extURLs.length; i++) {  
                System.out.println(extURLs[i]);  
            }  
        } catch (Exception e) {  
            //…  
        }  
    }  
}