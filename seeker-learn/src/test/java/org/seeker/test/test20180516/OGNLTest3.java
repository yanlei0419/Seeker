package seeker.test.test20180516;

import java.util.*;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

public class OGNLTest3 {

    public static void main(String[] args) throws OgnlException {

        OgnlContext context = new OgnlContext();

        // 处理数组类型
        String[] strs = new String[] { "a", "b", "c" };
        context.put("strs", strs);
        System.out.println(Ognl.getValue("#strs[2]", context, context.getRoot()));

        // 处理List类型
        List<String> words = new ArrayList<String>();
        words.add("rico");
        words.add("livia");
        words.add("neu");
        context.put("words", words);
        System.out.println(Ognl.getValue("#words[0]", context,context.getRoot()));


        // 处理Map类型
        Map<String,String> map = new HashMap<String,String>();
        map.put("ad", "d");
        map.put("Rico", "China");
        map.put("campus", "neu");
        context.put("map", map);
        System.out.println(Ognl.getValue("#map['Rico']",context, context.getRoot()));



        // 处理Set类型:由于Set的无序性，所以不能通过这种访问Set（只能迭代输出）,会抛出 ognl.NoSuchPropertyException;
        Set<String> set = new HashSet<String>();
        set.add("rico");
        set.add("livia");
        set.add("neu");
        context.put("set", set);
        System.out.println(Ognl.getValue("#set[2]", context, context.getRoot()));
    }
}/* Output: 
        c
        rico
        China
        Exception in thread "main" ognl.NoSuchPropertyException: java.util.HashSet.2
 *///:~