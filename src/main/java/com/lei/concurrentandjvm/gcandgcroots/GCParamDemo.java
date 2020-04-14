package com.lei.concurrentandjvm.gcandgcroots;

public class GCParamDemo {
    public static void main(String[] args) {

        /*  -XX:InitialHeapSize=268435456
            -XX:MaxHeapSize=4294967296
            -XX:+PrintCommandLineFlags
            -XX:+UseCompressedClassPointers
            -XX:+UseCompressedOops
            -XX:+UseParallelGC
        * */
        long maxMemory = Runtime.getRuntime().maxMemory();
        long totalMemory = Runtime.getRuntime().totalMemory();
        System.out.println("TOTAL_MEMORY(-Xms) = "+totalMemory+"字节, "+(totalMemory/(double)(1024*1024)) +"MB (默认1/64)");
        System.out.println("MAX_MEMORY(-Xmx) = "+maxMemory+"字节, "+(maxMemory/(double)(1024*1024)) +"MB (默认1/4)");
    }
}
