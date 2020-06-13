package geek.algo;

/**
 * 1.两次循环查找答案，时间复杂度最高,o(n^2);
 * 2.哈希表查找，需要用HashMap来存储数字与index的映射关系
 * 3.左右双指针向中间缩进查找
 */
public class TwoSum1 {

    public int[] twoSum(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while(left < right) {
            int cur = nums[left] + nums[right];
            if(cur == target) {
                return new int[]{left, right};
            } else if(cur < target) {
                left++;
            } else {
                right--;
            }
        }
        return null;
    }

}
