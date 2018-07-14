//测试main函数   
//QuartzTest.java   
package org.seeker.quartz;


import java.text.SimpleDateFormat;
import java.util.Date;  
  
public class QuartzTest {  
  
    /** *//** 
     * @param args 
     */  
    public static void main(String[] args) {  
        // TODO Auto-generated method stub   
        SimpleDateFormat DateFormat = new SimpleDateFormat("yyyyMMddHHmmss");  
        Date d = new Date();  
        String returnstr = DateFormat.format(d);          
          
        TestJob job = new TestJob();
        String job_name ="定时任务";  
        try {  
            System.out.println(returnstr+ "【系统启动】");  
            QuartzManager.addJob(job_name,job,"0/2 * * * * ?"); //每2秒钟执行一次
              
       }  catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
}  
