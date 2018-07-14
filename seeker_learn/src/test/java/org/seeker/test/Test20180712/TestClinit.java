package seeker.test.Test20180712;

public class TestClinit {
    public static void main(String[] args) throws Exception {
        Class<?> aClass = Class.forName("seeker.test.Test20180712.ABC");
//        ABC o = (ABC) aClass.newInstance();
//        System.out.println(ABC.a);
    }
}
class ABC {
    //加载-验证-准备-解析-初始化
    //加载-连接-初始化  连接=验证-准备-解析
    /**
     *赋值是在 准备和 初始化做的
     * 准备阶段将类中的属性付初始值 0或者null
     * 初始化阶段 将执行类静态属性静态块的执行  按照代码顺序执行
     *  首先是 父类的静态属性 静态块
     *  然后是 子类的静态属性 静态块
     *  父类块
     *  父类的构造方法
     *  子类的块
     *  子类的构造方法
     *
     *  子类访问父类的静态属性
     *
     * */



    static {
        System.out.println("this is static block");
    }
    public static int a = getNum();
    public static int getNum() {
        System.out.println("getNum");
        
        return 1;
    }
}
