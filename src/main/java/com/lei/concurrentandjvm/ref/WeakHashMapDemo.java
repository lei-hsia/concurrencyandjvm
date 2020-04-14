package com.lei.concurrentandjvm.ref;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/*  假如有一个应用需要读取大量的图片:
        - 每次读取图片都从硬盘读取会严重影响性能
        - 如果一次性全部加载到内存可能产生OOM

    可以使用软引用: 思路: 用一个hashmap保存图片的路径和相应图片对象关联的软引用之间的映射关系，内存不足时，
                        JVM自动回收这些缓存图片对象占用的空间，避免OOM
       Map<String, SoftReference<Bitmap>> imageCache = new HashMap<String, SoftReference<Bitmap>>();
*
    补充: HashMap源码中，实际上KV键值对是一个Node，Node存入map中之后，就和KV无关了，所以KV的改变不影响map中的entry,
        i.e. 一般的map，key被discarded，不影响这个key对应的map中的键值对；但是WeakHashMap就不是这样了
* */
public class WeakHashMapDemo {
    public static void main(String[] args) {
        myHashMap();
        myWeakHashMap();
    }

    private static void myHashMap() {
        Map<Integer, String> map = new HashMap<>();
        Integer key = new Integer(1);
        map.put(key, "AA");
        System.out.println(map);
        key = null;
        System.out.println(map); // key赋值为null，不影响已经另单独作为Node的KV对

        System.gc();
        System.out.println(map); // gc: key没有了，但是map中的Node还在
    }

    private static void myWeakHashMap() {
        WeakHashMap<Integer, String> map = new WeakHashMap<>();
        Integer key = new Integer(2);
        map.put(key, "BB");
        System.out.println(map);
        key = null;
        System.out.println(map); // key赋值为null，不影响已经另单独作为Node的KV对

        System.gc();
        System.out.println(map); // gc: key没了，map中的那个node也没有了
    }
}
