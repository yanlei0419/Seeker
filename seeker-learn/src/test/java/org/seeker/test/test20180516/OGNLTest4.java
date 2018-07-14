package org.seeker.test.test20180516;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

public class OGNLTest4 {
    public static void main(String[] args) throws OgnlException {  

        Student s1 = new Student("Tom", 22, 170.3);  
        Student s2 = new Student("Jack", 21, 176.2);  
        Student s3 = new Student("Tomas", 23, 180.1);  
        Student s4 = new Student("Lucy", 20, 163.3);  

        List<Student> stus = new ArrayList<Student>();  
        Collections.addAll(stus, s1, s2, s3, s4);  

        // 新建OgnlContext对象  
        OgnlContext context = new OgnlContext();  
        context.put("stus", stus);  

        // 过滤（filtering）,collection.{? expression}  
        // 利用过滤获取身高在175以上的所有学生集合  
        // 输出结果：[Student [name=Jack, age=21, height=176.2], Student [name=Tomas, age=23, height=180.1]]  
        System.out.println(Ognl.getValue("#stus.{? #this.height > 175.0}", context, context.getRoot()));  

        // 过滤（filtering）,collection.{^ expression}  
        // 利用过滤获取身高在175以上的所有学生集合中第一个元素  
        // 输出结果：[Student [name=Jack, age=21, height=176.2]]  
        System.out.println(Ognl.getValue("#stus.{^ #this.height > 175.0}", context, context.getRoot()));  

        // 过滤（filtering）,collection.{$ expression}  
        // 利用过滤获取身高在175以上的所有学生集合的最后一个元素  
        // 输出结果：[Student [name=Tomas, age=23, height=180.1]]  
        System.out.println(Ognl.getValue("#stus.{$ #this.height > 175.0}", context, context.getRoot()));  

        // 投影（projection）, collection. {expression}  
        // 获取集合中的所有学生的姓名  
        // 输出结果：[Tom, Jack, Tomas, Lucy]  
        System.out.println(Ognl.getValue("#stus.{name}", context, context.getRoot()));  
    }  
}/* Output: 
        [Student [name=Jack, height=176.2, age=21], Student [name=Tomas, height=180.1, age=23]]
        [Student [name=Jack, height=176.2, age=21]]
        [Student [name=Tomas, height=180.1, age=23]]
        [Tom, Jack, Tomas, Lucy]
 *///:~