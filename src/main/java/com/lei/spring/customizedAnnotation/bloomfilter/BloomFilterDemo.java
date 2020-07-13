package com.lei.spring.customizedAnnotation.bloomfilter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.Funnels;

import java.util.ArrayList;
import java.util.List;

public class BloomFilterDemo {

    private static int size = 1000000;

    /* BitArray: 用途是判断一条数据在不在一个集合：把所有的*/
    private static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), size, 0.001);

    public static void main(String[] args) {
        for (int i = 0; i <= size; i++) {
            bloomFilter.put(i);
        }

        List<Integer> list = new ArrayList<>(10000);

        for (int i = size + 10000; i < size + 20000 ; i++) {
            if (bloomFilter.mightContain(i)) {
                list.add(i);
            }
        }

        System.out.println("误判数量: " + list.size());
    }
}
