package com.lei.concurrentandjvm.juc2041;

import com.lei.concurrentandjvm.jvmgcjmm2044.T;

import java.util.concurrent.*;

/*  ForkJoinPool
    ForkJoinTask
    RecursiveTask

        (I) Executor <- (I) ExecutorService <- (C) AbstractExecutorService <- (C) ForkJoinPool

        ForkJoinPool池中的线程执行任务；分支合并: 所以有返回值; 所以池中的线程应该实现Callable接口而不是Runnable接口,
        返回值本来为FutureTask类型: 在ForkJoin框架下面有其他封装: 返回为 ForkJoinTask 类型;

        public abstract class ForkJoinTask<V> implements Future<V>, Serializable
        public abstract class RecursiveTask<V> extends ForkJoinTask<V>

        而Future<V>是Callable的返回值类型，Future<V>: @see FutureTask;
        public interface RunnableFuture<V> extends Runnable, Future<V>
        public class FutureTask<V> implements RunnableFuture<V>

*/
class MyTask extends RecursiveTask<Integer> {

    public static final Integer ADJUST_VALUE = 10;
    private int begin;
    private int end;
    private int result;

    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if (end - begin <= ADJUST_VALUE) {
            for (int i = begin; i <= end; i++) {
                result += i;
            }
        }
        else {
            int middle = (begin + end) / 2;
            MyTask task01 = new MyTask(begin, middle);
            MyTask task02 = new MyTask(middle+1, end);
            task01.fork(); // fork, recursion
            task02.fork();
            result = task01.join() + task02.join(); // join, add all
        }
        return result;
    }
}


public class ForkJoinDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        MyTask myTask = new MyTask(0, 100);

        ForkJoinPool threadPool = new ForkJoinPool();

        // ForkJoinTask实现了Future接口，Future接口算完之后直接get获得返回值
        /*  Method submit extends base method Executor.execute(java.lang.Runnable)
            by creating and returning a Future that can be used to cancel execution and/or wait for completion
            线程池执行用submit或者execute方法，其中无返回值用execute方法，方法中实现Runnable接口, submit提交任务返回Future类型结果
        */
        ForkJoinTask<Integer> forkJoinTask = threadPool.submit(myTask);

        System.out.println(forkJoinTask.get());

        threadPool.shutdown();
    }
}
