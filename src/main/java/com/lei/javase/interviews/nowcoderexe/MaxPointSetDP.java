package com.lei.javase.interviews.nowcoderexe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MaxPointSetDP{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = Integer.parseInt(scan.nextLine());
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            String[] line = scan.nextLine().split(" ");
            arr[i][0] = Integer.parseInt(line[0]);
            arr[i][1] = Integer.parseInt(line[1]);
        }

        // 按x的坐标从小到大排序
        Arrays.sort(arr, (o1, o2) -> o1[0] - o2[0]);

        ArrayList<int[]> list = new ArrayList<>();
        // 使用动态规划从后往前
        int last_max = -1; // 记录当前下标后面的最大值
        for (int i = n - 1; i >= 0; i--) {
            // 如果后面的y值没有大于当前y值的就添加进去
            if (arr[i][1] > last_max) list.add(0, arr[i]);
            last_max = Math.max(last_max, arr[i][1]); // 更新最大值
        }

        for (int[] temp : list) {
            System.out.println(temp[0] + " " + temp[1]);
        }
    }
}