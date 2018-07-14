package org.seeker.test.test20180516;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

import java.util.List;

public class OGNLTest2 {
    public static void main(String[] args) throws OgnlException {

        // 新建一个学校对象
        College college = new College();
        college.setName("NEU");

        // 新建一个学生对象
        Student stu = new Student();
        stu.setName("Livia");
        stu.setCollege(college);
        stu.setGentle("boy");

        // 构建一个OgnlContext对象,并将上述学校和学生对象放入Ognl上下文环境中
        OgnlContext context = new OgnlContext();
        context.put("college", college);
        context.put("stu", stu);

        // 将学生对象设置为根对象
        context.setRoot(stu);

        // 访问实例方法
        Object expression1 = Ognl.parseExpression("getGentle()");
        Object length1 = Ognl.getValue(expression1, context, context.getRoot());
        Object expression2 = Ognl.parseExpression("#college.name.length()");
        Object length2 = Ognl.getValue(expression2, context, context.getRoot());
        System.out.println(length1);
        System.out.println(length2);

        // 访问静态方法
        Object expression3 = Ognl.parseExpression("@java.lang.Math@max(2,4)");
        Object length3 = Ognl.getValue(expression3, context, context.getRoot());
        Object expression4 = Ognl
                .parseExpression("@java.lang.String@valueOf(name.length())");
        Object length4 = Ognl.getValue(expression4, context, context.getRoot());
        System.out.println(length3);
        System.out.println(length4);

        // 访问构造方法：通过Ognl表达式构建一个LinkedList对象，注意使用全类名
        Object expression5 = Ognl.parseExpression("new java.util.LinkedList()");
        List list = (List)Ognl.getValue(expression5, context, context.getRoot());
        list.add("list");
        list.add("rico"); 
        System.out.println(list);
    }
}/* Output: 
        boy
        3
        4
        5
        [list, rico]
 *///:~