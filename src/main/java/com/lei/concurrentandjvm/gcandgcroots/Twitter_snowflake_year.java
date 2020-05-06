package com.lei.concurrentandjvm.gcandgcroots;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Twitter_snowflake_year {
    public static void main(String[] args) {
        System.out.println("11111111111111111111111111111111111111111".length()); // 41-bit timestamp
        long time = 2199023255551L;

        Date date = new Date();
        date.setTime(time);

        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(date));

        System.out.println(date.getTime());

    }
}
