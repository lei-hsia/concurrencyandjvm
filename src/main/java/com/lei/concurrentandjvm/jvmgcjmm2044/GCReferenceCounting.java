package com.lei.concurrentandjvm.jvmgcjmm2044;

public class GCReferenceCounting {

    //private byte[] bigSize = new byte[2 * 1024 * 1024];
    Object object = null;

    public static void main(String[] args) {
        GCReferenceCounting countA = new GCReferenceCounting();
        GCReferenceCounting countB = new GCReferenceCounting();
        countA.object = countB;
        countB.object = countA;
        countA = null;
        countB = null;

        System.gc(); // 手动开启GC，但是这行代码不是立刻开启GC
    }
}
