package com.lei.javase.interviews.nowcoderexe;

/*
*   题目描述
数列的定义如下：数列的第一项为n，以后各项为前一项的平方根，求数列的前m项的和。

输入描述:
输入数据有多组，每组占一行，由两个整数n（n<10000）和m(m<1000)组成，n和m的含义如前所述。
输出描述:
对于每组输入数据，输出该数列的和，每个测试实例占一行，要求精度保留2位小数。

* 示例1
输入          输出
81 4         94.73
2 2          3.41
* */
import java.util.Scanner;

public class NumberArrayPrint {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        double num, res = 0; int count = 0;
        while(in.hasNext()) {  // 注意输入的 hasNext, nextDouble, nextInt的用法
            num = in.nextDouble();
            count = in.nextInt();
            res = 0;
            for(int i = 0; i < count; ++i) {
                res += num;
                num = Math.sqrt(num);
            }
            System.out.printf("%.2f\n", res);  // printf = print formatted string; println: print a string
        }
    }
}
