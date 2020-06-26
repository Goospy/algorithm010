package com.goospy.leetcode.week;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class lee46And47 {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        backTrace(result, new ArrayList<>(), nums, new boolean[nums.length]);
        return result;
    }

    private void backTrace(List<List<Integer>> result, List<Integer> list, int[] nums, boolean[] used) {
        if(list.size() == nums.length) {
            result.add(new ArrayList<>(list));
        } else {
            for(int i = 0; i < nums.length; i++) {
                //当元素被用过或者重复了，就不对该重复元素进行dfs
                if(used[i] || (i > 0 && !used[i - 1] && nums[i] == nums[i - 1])) continue;
                list.add(nums[i]);
                used[i] = true;
                backTrace(result, list, nums, used);
                used[i] = false;
                list.remove(list.size() - 1);
            }
        }
    }

}
