package com.lei.concurrentandjvm.juc2041;

/*
*   queue是空的，从queue中获取ele会阻塞；满的，添加ele阻塞 （注意还是有两端的FIFO结构)
*   多线程: 阻塞<=>某些判断条件之后，线程执行挂起；然后在满足某些条件之后，不挂起了，唤醒重新执行
*   为什么需要BlockingQueue(I) ? 因为这样就不用管什么时候需要阻塞线程/唤醒线程，BlockingQueue都办好了
*   --- concurrent包带来的好处
*
*   C:
*   ArrayBlockingQueue, LinkedBlockingQueue, PriorityBlockingQueue, DelayQueue,
*   SynchronousQueue, LinkedTransferQueue, LinkedBlockingDeque
*
*   LC: 1188
*
*   add/remove/element: 不能add/remove: IllegalStateException: Queue full
*   offer/poll/peek:  返回true/false
*   put(e)/take: 阻塞
*
* */
public class BlockingQueueDemo {
}
