package com.lei.concurrentandjvm.juc2041;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;


public class StreamDemo {
    public static void main(String[] args) {

        // 直接new接口，就是匿名内部类; 凡是匿名内部类，都可以用lambda表达式替换
        /*Function<String, Integer> function = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return null;
            }
        };*/

        // 1. 函数式接口: Function<T, R>
         Function<String, Integer> function = s -> {return Integer.parseInt(s)+88; };
        /* Function<String, Integer> function = Integer::valueOf;
            如果就只是parseInt, 用::进行缩写: replace lambda with method reference
         */
        System.out.println(function.apply("1"));

        // 2. 判断式接口: Predicate<T>
        /*Predicate<String> predicate =  new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return false;
            }
        };*/
        // Predicate<String> predicate1 = s -> {return s.isEmpty(); };
        Predicate<String> predicate = String::isEmpty;
        System.out.println(predicate.test("2345"));  // 本身返回值不打印

        // 3. 消费型接口
        /*Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {

            }
        };*/
        // Consumer<String> consumer1 = s -> { System.out.println(s); };
        Consumer<String> consumer = System.out::println;
        consumer.accept("print this");

        // 4. 供给型接口
        /*Supplier<String> supplier = new Supplier<String>() {
            @Override
            public String get() {
                return null;
            }
        };*/
        Supplier<String> supplier = () -> {return "a string"; };
        System.out.println(supplier.get()); // 本身无返回值
    }
}
