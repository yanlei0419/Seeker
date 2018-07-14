package seeker.test.test20180516;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

public class OGNLTest {

    public static void main(String[] args) throws OgnlException {

        // 新建一个学校对象
        College college = new College();
        college.setName("TJU");

        // 新建一个学生对象
        Student stu = new Student();
        stu.setName("Rico");

        // 构建一个OgnlContext对象 ,并将上述学校、学生对象放入Ognl上下文环境(本质是一个Map)中
        OgnlContext context = new OgnlContext();
        context.put("college", college);
        context.put("stu", stu);

        // 将学生设置为根对象
        context.setRoot(stu);

        // 构建Ognl表达式的树状表示
        Object expression1 = Ognl.parseExpression("#college.name");
        Object expression2 = Ognl.parseExpression("name");
        Object expression3 = Ognl.parseExpression("#stu.name");

        // 根据Ognl表达式给Java对象设置值,将TJU改为NEU
        Ognl.setValue(expression1, context, context.getRoot(), "NEU");

        // 根据Ognl表达式获取Java对象的(属性)值
        Object collegeName = Ognl.getValue(expression1, context,
                context.getRoot());
        Object stuName2 = Ognl
                .getValue(expression2, context, context.getRoot());
        Object stuName3 = Ognl
                .getValue(expression3, context, context.getRoot());

        System.out.println(collegeName);
        System.out.println(stuName2);
        System.out.println(stuName3);
    }
}/* Output: 
        NEU0
        Rico
        Rico
 *///:~