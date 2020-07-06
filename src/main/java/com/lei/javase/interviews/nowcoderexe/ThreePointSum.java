package com.lei.javase.interviews.nowcoderexe;

import java.util.*;


/*  输入描述:
第一行包含空格分隔的两个数字 N和D(1 ≤ N ≤ 1000000; 1 ≤ D ≤ 1000000)

第二行包含N个建筑物的的位置，每个位置用一个整数（取值区间为[0, 1000000]）表示，从小到大排列（将字节跳动大街看做一条数轴）
输出描述:
一个数字，表示不同埋伏方案的数量。结果可能溢出，请对 99997867 取模
示例1
输入
4 3
1 2 3 4
输出
4
说明
可选方案 (1, 2, 3), (1, 2, 4), (1, 3, 4), (2, 3, 4)
示例2
输入
5 19
1 10 20 30 50
输出
1
说明
可选方案 (1, 10, 20)
* */
public class ThreePointSum{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long n = in.nextLong(), d = in.nextLong(), res = 0;
        long[] arr = new long[(int) n];

        for(int i = 0, j = 0; i < n; ++i) {
            arr[i] = in.nextInt();
            while(i >= 2 && arr[i] - arr[j] > d){
                j++;
            }
            res += C(i - j);
        }

        System.out.println(res % 99997867);
    }

    public static long C(long n) {
        return n*(n-1)/2;
    }
}
