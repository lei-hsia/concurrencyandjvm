package com.lei.concurrentandjvm.juc2041;

import java.util.concurrent.*;

public class MyThreadPoolDemo {
    public static void main(String[] args) {
        // Executors: 工具类, 类似Arrays, Collections

        /*public static ExecutorService newFixedThreadPool(int nThreads) {
            return new ThreadPoolExecutor(nThreads, nThreads,
                    0L, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<Runnable>());
        }*/
        //ExecutorService es = Executors.newFixedThreadPool(5);

        /*public static ExecutorService newSingleThreadExecutor() {
            return new Executors.FinalizableDelegatedExecutorService
                    (new ThreadPoolExecutor(1, 1,
                            0L, TimeUnit.MILLISECONDS,
                            new LinkedBlockingQueue<Runnable>()));
        }*/
        //ExecutorService es = Executors.newSingleThreadExecutor();

        /*public static ExecutorService newCachedThreadPool() {
            return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                    60L, TimeUnit.SECONDS,
                    new SynchronousQueue<Runnable>());
        }*/

        /*  public ThreadPoolExecutor(int corePoolSize,
            int maximumPoolSize,
            long keepAliveTime,   3,4: 过剩策略
            TimeUnit unit,
            BlockingQueue<Runnable> workQueue, 5: 不够策略: 阻塞
            ThreadFactory threadFactory,
            RejectedExecutionHandler handler)

            4步骤: 1. corePoolSize, 2. 来了多于corePoolSize的数量，去BlockingQueue等待，3. BQ满了: corePoolSize扩容到max, BlockingQueue
            的线程就去到max整个线程池中办理; 3. 又来一大堆: max了, BlockingQueue也满了: 4. 开始RejectedExecutionHandler中的handler来处理
        */

        //ExecutorService es = Executors.newCachedThreadPool(); 现实中都是自定义 线程池

        System.out.println(Runtime.getRuntime().availableProcessors());

        ExecutorService es = new ThreadPoolExecutor(
                2,
                5,
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(2), // 必须指明，默认最大为Integer.MAX_VALUE
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());


        try {
            // 模拟10个顾客来到银行办理业务，银行有5个工作人员办理
            for (int i = 1; i <= 10; i++) {
                //TimeUnit.SECONDS.sleep(1);
                es.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务");
                });
            }
        } finally {
            es.shutdown();
        }

    }
}
