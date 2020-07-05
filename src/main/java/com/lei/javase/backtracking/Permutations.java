package com.lei.javase.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Permutations {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        dfs(list, new ArrayList<>(), nums);
        return list;
    }

    private void dfs(List<List<Integer>> list, List<Integer> arr, int[] nums) {
        
    }
}
