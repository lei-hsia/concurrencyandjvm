package com.lei.concurrentandjvm.juc2041;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/*
    异步调用: CompletableFuture    runAsync, supplyAsync: 异步调用的另一个线程在方法体中，一般用lambda表达式
*/
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 1. 没有返回值
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "没有返回值, update mysql ok");
        });
        // 这里的get并不是获得(有)返回值, 而是让另一个线程执行完毕，然后返回那个线程的结果
        voidCompletableFuture.get();

        System.out.println();


        // 2. 有返回值
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            // System.out.println(Thread.currentThread().getName() + "有返回值，insert mysql ok");
            int a = 10/ 0;
            return 1024;
        });

        integerCompletableFuture.whenComplete((u, v) -> { // u: whenComplete时调用得到的结果; v:出现异常时得到的结果
            System.out.println("******u \t" + u);
            System.out.println("******v \t" + v);
        }).exceptionally(f -> {
            System.out.println(f.getMessage());
            return 404;
        });
    }

}
