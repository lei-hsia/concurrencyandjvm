package com.lei.javase.interviews.nowcoderexe;

import java.util.LinkedList;

public class Median {

    LinkedList<Integer> list = new LinkedList<>(); // incr

    public void Insert(Integer num) {
        if (list.size()==0 || num < list.getFirst()) {
            list.addFirst(num);
        }
        else {
            boolean insertFlag = true;
            for(Integer i : list) {
                if (num < i) {
                    int index = list.indexOf(i);
                    list.add(index, num);
                    insertFlag = false;
                    break; // 直接跳出循环
                }
            }
            // 如果for里面就插入了num这里就不插入，否则插入
            if (insertFlag) {
                list.addLast(num);
            }
        }
    }

    public Double GetMedian() {
        if (list.size() == 0) return null;
        if (list.size() % 2 == 0) {
            int index = list.size()/2;
            return ((double) (list.get(index) + list.get(index - 1)))/2;
        }

        return (double) (list.get(list.size() + 1) / 2 - 1);
    }

    
}