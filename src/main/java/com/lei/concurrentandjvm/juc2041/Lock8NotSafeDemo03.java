package com.lei.concurrentandjvm.juc2041;

import java.util.concurrent.TimeUnit;

class Phone {
    public static synchronized void sendEmail() throws Exception { // static: Phone.class
        TimeUnit.SECONDS.sleep(4);
        System.out.println("**** send email");
    }

    public synchronized void sendSMS() throws Exception {
        System.out.println(">>>> send SMS");
    }

    public void useCharger() {
        System.out.println("~~~~ hello ");
    }
}

/*
 *   synchronized的三种用法:
 *   1. 普通同步方法 锁的是当前实例对象
 *   2. 同步代码块   锁的是括号中的配置的对象
 *   3. 对于静态同步方法，锁的是当前类的Class对象
 *
 *   new this: 锁的是一个手机对象     (没有包含与被包含，只不过是不同)
 *   static:  锁的是手机模版
 * */


/*  8锁问题:
    1.普通调用，先发送邮件还是短信
    2.邮件方法中等待4秒，先发送邮件还是短信
          > 一个资源类，同一时间只能有一个线程进入资源类访问其中一个synchronized方法; synchronized锁住的是当前对象this(对象锁),其他线程不能进入当前对象访问其他synchronized方法
          > 此时因为main中AB之间等待0.2秒，所以肯定是A先得到资源, 所以A拿到锁this，那么B只能等待A执行完之后再执行
    3.普通方法sayHello, 先发送邮件还是短信
          > 是同一个资源类，但是B线程并不和A争抢资源,一个用手机一个用充电器，所以B不受影响; i.e. A锁了当前对象，但是B并不用A锁起来A对象的资源
    4. 2部手机, 先发送邮件还是短信
          > 比上面的更明显，根本就是两个资源，所以SMS先

    5. 两个静态同步方法，同一部手机，先发送邮件还是短信
    6. 两个静态同步方法，2部手机，先发送邮件还是短信
          > static,对应类，所以synchronized加锁的就不是当前对象this，而是属于当前对象全局的class这个类，所以这个类，就只有一个资源;
          i.e. 对象锁和全局锁的区别； static加上之后锁的是整个Class对象(不是class,学了JVM之后知道加载进内存之后是Class对象);
          Class文件对应的是"手机模版"

    7. 一个静态同步方法，一个普通方法，同一部手机，先发送邮件还是短信 ---> 两把锁，锁的是不同的对象
    8. 一个静态同步方法，一个普通方法，2部手机，先发送邮件还是短信
          > 7. 一个是Class全局锁，一个是当前对象的对象锁this，相互之间没有关系，所以send SMS先;
          > 8. 跟上面的其实没有区别，this对象换了一个，另一个是Class, 所以结果跟上面一样 (不同的2个手机，和手机模版)

    3,5,6,7,8: 不是send Email在前面的情况: 要么是根本不争抢资源，要么是锁根本不是同一个锁, 锁住各自自己的资源也没关系
    锁不同: static方法锁的是Class全局锁，普通方法synchronized锁的是this对象(当前对象，就是当前资源类)

      不同的锁: this/Class
* */
public class Lock8NotSafeDemo03 {
    public static void main(String[] args) throws Exception {
        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
            try {
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"A").start();

        Thread.sleep(100);

        new Thread(() -> {
            try {
                phone.sendSMS();
                //phone2.sendSMS();
                //phone.useCharger();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "B").start();
    }
}
