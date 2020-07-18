class Solution {
    public int rob(int[] nums) {
        if(null == nums || 0 == nums.length) {
            return 0;
        }

        int len = nums.length;
        if(1 == len) {
            return nums[0];
        }

        int last1 = nums[0];
        int last2 = Math.max(nums[0], nums[1]);

        for(int i = 2; i < len; i++) {
            int max = Math.max(last1 + nums[i], last2);
            last1 = last2;
            last2 = max;
        }
        return last2;
    }
}