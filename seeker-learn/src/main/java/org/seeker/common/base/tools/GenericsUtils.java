package org.seeker.common.base.tools;
 import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
@SuppressWarnings("unchecked")
  public class GenericsUtils {  
      /**   
       * 通过反射,获得定义Class时声明的父类的范型参数的类型.   
       * 如public BookManager extends GenricManager&lt;Book&gt;   
       *   
      * @param clazz The class to introspect   
     * @return the first generic declaration, or &lt;code&gt;Object.class&lt;/code&gt; if cannot be determined   
      */  

	public static Class getSuperClassGenricType(Class clazz) {  
         return getSuperClassGenricType(clazz, 0);  
     }  
 
     /**   
      * 通过反射,获得定义Class时声明的父类的范型参数的类型.   
19      * 如public BookManager extends GenricManager&lt;Book&gt;   
20      *   
21      * @param clazz clazz The class to introspect   
22      * @param index the Index of the generic ddeclaration,start from 0.   
23      */  
     public static Class getSuperClassGenricType(Class clazz, int index) throws IndexOutOfBoundsException {  
  
         Type genType = clazz.getGenericSuperclass();  
  
        if (!(genType instanceof ParameterizedType)) {  
            return Object.class;  
        }  
  
       Type[] params = ((ParameterizedType) genType).getActualTypeArguments();  
         if (index >= params.length || index < 0) {  
          return Object.class;  
         }  
         if (!(params[index] instanceof Class)) {  
            return Object.class;  
         }  
         return (Class) params[index];  
     }  
 }