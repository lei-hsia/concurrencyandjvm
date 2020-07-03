package com.lei.concurrentandjvm.sorting;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {1, 4, 5, 67, 2, 7, 8, 6, 9, 44};

        quicksort(arr, 0, arr.length - 1);

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
    }

    private static void quicksort(int[] arr, int l, int r) {

        int i = l;
        int j = r;

        int pivot = arr[(l + r)/2];

        // i=j: 表示两端逼近重合: 要过界才停止
        while (i <= j) {

            while (arr[i] < pivot) ++i;
            while (arr[j] > pivot) --j;

            if (i <= j) {
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                ++i;
                --j;
            }
        }

        if (l < j) quicksort(arr, l, j);
        if (r > i) quicksort(arr, i, r);
    }
}
