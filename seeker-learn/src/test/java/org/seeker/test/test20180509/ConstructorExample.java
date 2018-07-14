package org.seeker.test.test20180509;

//父类
class Foo {
    int i = 1;

    Foo() {
        System.out.println(i);          //   -----------(1)
        int x = getValue();
        System.out.println(x);         //    -----------(2)
    }

    {
        i = 2;
    }

    protected int getValue() {
        return i;
    }
}

//子类
class Bar extends Foo {
    int j = 1;

    Bar() {
        j = 2;
    }

    {
        j = 3;
    }

    @Override
    protected int getValue() {
        return j;
    }
}

public class ConstructorExample {
    public static void main(String... args) {
        Bar bar = new Bar();
        System.out.println(bar.getValue());           //  -----------(3)
    }
}/* Output: 
            2
            0
            2

            这样程序就好看多了，我们一眼就可以观察出程序的输出结果。在通过使用Bar类的构造方法new一个Bar类的实例时，
            首先会调用Foo类构造函数，因此(1)处输出是2，这从Foo类构造函数的等价变换中可以直接看出。(2)处输出是0，为什么呢？
            因为在执行Foo的构造函数的过程中，由于Bar重载了Foo中的getValue方法，所以根据Java的多态特性可以知道，
            其调用的getValue方法是被Bar重载的那个getValue方法。但由于这时Bar的构造函数还没有被执行，
            因此此时j的值还是默认值0，因此(2)处输出是0。最后，在执行(3)处的代码时，由于bar对象已经创建完成，
            所以此时再访问j的值时，就得到了其初始化后的值2，这一点可以从Bar类构造函数的等价变换中直接看出。
 *///:~