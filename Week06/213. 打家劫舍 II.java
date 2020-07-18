class Solution {
    public int rob(int[] nums) {
        if(null == nums || 0 == nums.length) {
            return 0;
        }

        int len = nums.length;
        if(1 == len) {
            return nums[0];
        }
        if(2 == len) {
            return Math.max(nums[0], nums[1]);
        }

        return Math.max(rob(nums, 0, len - 1), rob(nums, 1, len));
    }

    public int rob(int[] nums, int left, int right) {
        int last1 = nums[left];
        int last2 = Math.max(last1, nums[left + 1]);

        for(int i = left + 2; i < right; i++) {
            int max = Math.max(last1 + nums[i], last2);
            last1 = last2;
            last2 = max;
        }
        return last2;
    }
}