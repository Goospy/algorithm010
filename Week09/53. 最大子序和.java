class Solution {
    public int maxSubArray(int[] nums) {
        if(null == nums || 0 == nums.length) {
            return 0;
        }
        int len = nums.length;
        if(1 == len) {
            return nums[0];
        }

        int max = Integer.MIN_VALUE, tmp = 0;
        for(int i = 0; i < len; i++) {
            tmp = Math.max(tmp + nums[i], nums[i]);
            max = Math.max(max, tmp);
        }
        return max;
    }
}