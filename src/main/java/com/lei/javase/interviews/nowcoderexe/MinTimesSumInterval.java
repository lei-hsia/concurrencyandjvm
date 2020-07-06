package com.lei.javase.interviews.nowcoderexe;

// 求区间最小值*区间和;

/*给定一个数组序列,需要求选出一个区间,使得该区间是所有区间中经过如下计算的值最大的一个：

区间中的最小数*区间所有数的和

最后程序输出经过计算后的最大值即可，不需要输出具体的区间。如给定序列 [6 2 1]则根据上述公式,可得到所有可以选定各个区间的计算值:
[6] = 6 * 6 = 36;
[2] = 2 * 2 = 4;
[1] = 1 * 1 = 1;
[6,2] = 2 * 8 = 16;
[2,1] = 1 * 3 = 3;
[6, 2, 1] = 1 * 9 = 9;

从上述计算可见选定区间[6]，计算值为36， 则程序输出为36。

区间内的所有数字都在[0, 100]的范围内;

输入描述:
第一行输入数组序列个数，第二行输入数组序列。
输出描述:
输出数组经过计算后的最大值。
示例1
输入      输出
3         36
6 2 1
* */

/*
 * n的范围[1,500000]太大，直接dp会超时
 *
 * 因为区间计算值都是由 区间最小值*区间和 得到的，
 * 所以在给定区间最小值的情况下，最优的情况一定是这个区间尽可能的扩展，
 * 直至区间的最小值不满足给定的最小值。
 * (扩展的过程因为满足 区间最小值 >= 给定区间最小值 的情况下，
 *      区间和一直增大，所以最终结果也增大)
 *
 * 又因为所有的数字都在[0, 100]的范围内，
 * 所以只需要遍历每一个数字为区间最小值的情况即可。
 *
 * 例如有序列[11, 12, 13, 1, 20, 21, 2, 30]，设i表示给定的区间最小值
 * 当i=10时，即规定 区间最小值 >= 10，
 *     满足区间的条件有[11, 12, 13], [20, 21], [30]
 *     对应的计算值为 11*(11+12+13), 20*(20+21), 30*30
 *     在给定i=10的情况下，最优值为max(11*(11+12+13), 20*(20+21), 30*30)
 * 所以对i所有可能的情况都遍历一遍就能确定最终的最优值，i属于[0, 100]
 *
 */
import java.util.*;

public class MinTimesSumInterval{
    public static void main(String[] args) {
        Scanner in =  new Scanner(System.in);
        int len = in.nextInt();
        int[] arr = new int[len];
        for(int i = 0; i < len; ++i) {
            arr[i] = in.nextInt();
        } // arr

        long res = 0;
        //for (int i = 0; i < len; ++i) { // 对每个区间长度，都需要找到这个区间中的最小值
        //    max = Math.max(max, arr[i] * arr[i]);
        //}
        for(int i = 0; i <= 100; ++i){ // min of interval
            int min = Integer.MAX_VALUE;
            long sum = 0;
            for (int j = 0; j < len; ++j) {
                if (arr[j] >= i){
                    sum += arr[j]; //不用更新max,min不变,延伸区间就行
                    min = Math.min(min, arr[j]);
                    //res = Math.max(res, min * sum); 没有必要,因为sum增加肯定一直增加
                } else {
                    res = Math.max(res, min * sum); // tmp max
                    sum = 0;
                    min = Integer.MAX_VALUE;
                }
            }
            res = Math.max(res, min * sum);
        }
        System.out.println(res);
    }
}