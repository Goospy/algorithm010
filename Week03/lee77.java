package com.goospy.leetcode.week;

import java.util.ArrayList;
import java.util.List;

public class lee77 {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        backTrace(result, list, n, 1, k);
        return result;
    }

    private void backTrace(List<List<Integer>> result, List<Integer> list, int n, int start, int k) {
        if(k == 0) {
            result.add(new ArrayList<>(list));
            return;
        }

        for(int i = start; i <= n; i++) {
            list.add(i);
            backTrace(result, list, n, i + 1, k - 1);
            list.remove(list.size() - 1);
        }
    }

}
