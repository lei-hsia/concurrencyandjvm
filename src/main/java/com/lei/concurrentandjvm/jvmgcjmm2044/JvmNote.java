package com.lei.concurrentandjvm.jvmgcjmm2044;

/*
    1. JVM 系统架构图］

    2. classloader
        System.out.println(objectDemo.getClass().getClassLoader().getParent().getParent());
        System.out.println(objectDemo.getClass().getClassLoader().getParent());
        System.out.println(objectDemo.getClass().getClassLoader());

        2.1 有哪几种类的加载器
        2.2 双亲委派机制
        2.3 沙箱安全机制

    3. native
        3.1 native是一个关键字
        3.2 声明有，但是没有实现，为什么

    4. PC寄存器
        记录方法之间的调用和执行顺序，类似排班值日表
        用来储存指向下一条指令的地址，i.e. 要执行的指令代码
        是当前线程执行的字节码的行号指示器

    5. 方法区(模版工厂)
         5.1 存储了每个类的结构信息, e.g. Car Class, 而car.class在硬盘上，没进JVM
         5.2 方法区是规范(Interface), 在不同VM中的实现是不同的，e.g. 永久代(PermGen space)7 vs 元空间(Metaspace)8
              i.e. 方法区 f = new 永久代()
                   方法区 f = new 元空间()

    6. stack
        6.1 栈管运行，堆管存储
        6.2 栈内存，生命周期跟随线程的声明周期，不存在GC
        6.3 存储: 8种基本数据类型＋引用对象类型＋实例方法
        6.4 方法压栈:每一个方法就是一个栈帧:java:方法＝栈帧: main方法总是在栈底(栈1), method1(栈2) ...
                    顶部的栈帧执行完毕之后出栈然后下面的栈帧执行，过渡所用类似PC寄存器
        6.5 栈帧(入栈的方法)保存: 局部变量，栈操作(Operand Stack):入栈出栈操作，栈文件：类文件(方法中生成的新的类), 方法
        6.6 Exception in thread "main" java.lang.StackOverflowError: 是一个错误
        6.7 HotSpot: 是最主流的JVM的实现；reference存的是对象的地址, 使用指针的方式访问对象->java堆; 堆中存放类元数据(Class模版)的地址

    7. heap
        Eden满了，开启 GC = YGC = 轻GC: Eden全部清空: 只有此时仍然被引用的对象不会被GC而是放入S0区，S0满了GC此时被引用的进入S1区
        Old/Tenure养老区，满了开启 MajorGC/FullGC, FullGC多次发现养老区还是满了不能进行对象的存储: OOMError
            java.lang.OutOfMemoryError: heap space异常，说明堆内存不够: jvm堆内存不够(-Xms,-Xmx设置);或者因为代码中创建了大量大对象，并且长时间不能被回收

    8. pass-by-value: 没有pass-by-reference:学院派说法，但是实际上分这两个比较好理解；

    9. MinorGC: 复制->清空->互换
           9.1 Eden, FromSurvivor复制到ToSurvivor, 年龄+1
           9.2 清空Eden, FromSurvivor
           9.3 因为from区空了，所以肯定有From和To区的交换 (部分对象在from和to区中复制来复制去，交换默认15次如果还存活，就转移到老年代)

    10. heap ---> 对象的生命周期 ---> OOM

    11. GC四大算法: 引用计数法，YoungGen复制算法，Mark-Sweep(标记清除/标清), mark-compact:  计数，复制，标清，标整
            分代收集: YoungGen: 复制; OldGen: 标清／标整

            内存效率(时间复杂度): 复制 > 标清 > 标整
            内存整齐度: 复制 ＝ 标整 > 标清
            内存利用率: 标整 = 标清 > 复制

       没有最好的算法，只有最合适: 所以是分带收集:
            YoungGen: 区域小，对象存活率低; 复制也无妨
            OldGen: 区域大，存活率高, 复制就不好了，而是标清和标整的混合使用

* */

public class JvmNote {

    public void inHeap(){} // store in heap

    public static void onlyOne(){} // just one, for all instance variables

    // Exception in thread "main" java.lang.StackOverflowError
    private static void m1() {
        //System.out.println(">> 222");
        //System.out.println("*******m1");
        //System.out.println(">> 333");
        // m1();
    }

    public static void main(String[] args) {
        JvmNote jvmNote  = new JvmNote();
        System.out.println("1111");
        m1();
        System.out.println("4444");
    }



}
