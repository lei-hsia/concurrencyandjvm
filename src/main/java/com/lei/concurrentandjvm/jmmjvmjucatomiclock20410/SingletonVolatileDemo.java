package com.lei.concurrentandjvm.jmmjvmjucatomiclock20410;

public class SingletonVolatileDemo {
    private static volatile SingletonVolatileDemo instance = null; // 因为后面也看到就是一个类只创建一个实例变量，所以用static修饰

    private SingletonVolatileDemo() {
        System.out.println(Thread.currentThread().getName()+"\t ich bin constructor");
    }

    /*单例模式，私有化构造方法，在getInstance方法中创建一个对象，只允许没有的时候创建一个，这样达到单例模式
    静态方法, 类名.static方法

    实际上没有外面的if(instance==null)也可以, 只是在new对象的时候需要上锁，第一个if只是为了优化性能

    instance = new SingletonVolatileDemo(); 分为三步:
        1. memory = allocate();
        2. instance(memory);    // 初始化对象(memory内存中的对象)
        3. instance = memory;   // instance(引用)指向某一个内存地址，此时instance != null

        如果2, 3发生指令重拍, instance首先指向memory, 而memory所在的内存中应该有的对象并没有完成初始化,
        那么此时instance指向的实际上是null，即使本来不应该的; 那么if判断的时候就还是null，会继续调用构造函数，单例模式就失效了
        所以这个对象本身如果禁止指令重拍就好了：这个对象定义为volatile

    单例 <= (获得对象: DCL Double Check Lock锁 + volatile定义对象)
    */
    public static SingletonVolatileDemo getInstance(){
        if (instance == null) {
            synchronized (SingletonVolatileDemo.class) { // 静态锁，给new对象的过程上锁
                if (instance == null) {
                    instance = new SingletonVolatileDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        // 单线程下的单例模式
        /*System.out.println(SingletonVolatileDemo.getInstance() == SingletonVolatileDemo.getInstance());
        System.out.println(SingletonVolatileDemo.getInstance() == SingletonVolatileDemo.getInstance());
        System.out.println(SingletonVolatileDemo.getInstance() == SingletonVolatileDemo.getInstance());*/

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                SingletonVolatileDemo.getInstance();  // 由打印结果:说明多线程环境下,不同的线程一共多次调用了构造方法，所以单例模式在这样的多线程环境下不行了
            }, String.valueOf(i)).start();
        }

    }
}
