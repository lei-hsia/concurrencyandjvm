package com.lei.concurrentandjvm.juc2041;

import java.util.Arrays;
import java.util.List;

class User {
    private Integer id;
    private String userName;
    private int age;

    public User(Integer id, String userName, int age) {
        this.id = id;
        this.userName = userName;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}

/*
    找出下面这样的数据: 偶数id, 年龄>24, 用户名转为大写，用户名字母倒排序, 只输出一个用户名
* */
public class StreamUserDemo {
    public static void main(String[] args) {
        /*  Stream(流)到底是什么: 数据渠道，用于操作数据源生成的元素序列； 集合讲的是元素，流讲的是计算
            Stream自己不储存元素，不改变源对象，会返回一个持有结果的新Stream

            数据源: list/array;  list/array.stream()->数据源变为stream -> collect(Collectors.toList()): 重新变为list
        * */

        User user1 = new User(11, "a", 23);
        User user2 = new User(12, "b", 24);
        User user3 = new User(13, "c", 24);
        User user4 = new User(14, "d", 28);
        User user5 = new User(16, "e", 26);

        List<User> list = Arrays.asList(user1, user2, user3, user4, user5);

        //Stream<T> filter(Predicate<? super T> predicate);
        list.stream().filter(u -> {return u.getId() % 2 == 0; })
                     .filter(user -> {return user.getAge()>24;})
                     .map(user -> {return user.getUserName().toUpperCase();}) // 输入user，输出String
                     .sorted(((o1, o2) -> {return o2.compareTo(o1);})) // new Comparator重写compare方法:匿名内部类: 可以用lambda表达式
                     .limit(1)
                    .forEach(System.out::println);
    }
}
