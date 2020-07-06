package com.lei.javase.interviews.nowcoderexe;

/*
* P为给定的二维平面整数点集。定义P中某点x，如果x满足P中任意点都不在x的右上方区域内（横纵坐标都大于x），
* 则称其为“最大的”。求出所有“最大的”点的集合。（所有点的横坐标和纵坐标都不重复,坐标轴范围在[0, 1e9]内）
*请实现代码找到集合P中的所有”最大“点的集合并输出。
输入描述:
第一行输入点集的个数N， 接下来N行，每行两个数字代表点的X轴和Y轴。1 ≤ n ≤ 500000
输出描述:
输出“最大的”点集合， 按照X轴从小到大的方式输出，每行两个数字分别代表点的X轴和Y轴。
示例1
输入
复制
5
1 2
5 3
4 6
7 5
9 0
输出
复制
4 6
7 5
9 0
备注:
输出结果按照x轴排序
*
* */
import java.util.*;

public class MaxPointSet{

    // static main函数中要用:Node类应该是static的; non-static variable this cannot be referenced from a static context
    static class Node {
        public int x;
        public int y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int total = in.nextInt();
        int[] hor = new int[total];
        int[] ver = new int[total];

        for(int i =0; i < total; ++i) {
            hor[i] = in.nextInt();
            ver[i] = in.nextInt();
        }

        List<Node> list = new ArrayList<>();
        for(int i = 0; i < total; ++i) {
            boolean isMax = true;
            for(int j = 0; j < total; ++j) {
                if (i != j) {
                    if (hor[i] < hor[j] && ver[i] < ver[j]) {
                        isMax = false;
                        break; // 不可能在max集合,直接跳出循环
                    }
                }
            }
            if(isMax) {
                list.add(new Node(hor[i], ver[i]));
            }
        }

        Collections.sort(list, new Comparator<Node>(){ // 创建一个Comparator对象, Comparator有泛型,匿名内部类重写compare方法
            public int compare(Node n1, Node n2) {
                return n1.x - n2.x;
            }
        });

        for(Node n : list) {
            System.out.println(n.x + " " + n.y);
        }
    }

}