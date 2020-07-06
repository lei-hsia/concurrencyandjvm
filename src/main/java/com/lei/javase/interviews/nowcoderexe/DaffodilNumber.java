package com.lei.javase.interviews.nowcoderexe;

/*
*   题目描述
春天是鲜花的季节，水仙花就是其中最迷人的代表，数学上有个水仙花数，他是这样定义的：“水仙花数”是指一个三位数，
* 它的各位数字的立方和等于其本身，比如：153=1^3+5^3+3^3。 现在要求输出所有在m和n范围内的水仙花数。

输入描述:
输入数据有多组，每组占一行，包括两个整数m和n（100<=m<=n<=999）。
输出描述:
对于每个测试实例，要求输出所有在给定范围内的水仙花数，就是说，输出的水仙花数必须大于等于m,并且小于等于n，
* 如果有多个，则要求从小到大排列在一行内输出，之间用一个空格隔开;如果给定的范围内不存在水仙花数，则输出no;每个测试实例的输出占一行。
示例1
输入          输出
100 120       no
300 380       370 371
* */

import java.util.*;

public class DaffodilNumber{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int min, max, a, b, c, tmp;
        boolean enter;
        while(in.hasNext()){
            min = in.nextInt();  max= in.nextInt(); enter = false;
            for(int i = min; i <= max; ++i) {
                a = (i/100); b = (i/10)%10; c = i%10;
                tmp = a*a*a + b*b*b + c*c*c;
                if (tmp == i){
                    enter = true;
                    System.out.print(i + " ");
                }
            }
            if (enter) {  // 注意这里关于换行的标志位的使用
                System.out.println();
            } else {
                System.out.println("no");
            }


        }

    }
}