class Solution {
    public int maxProduct(int[] nums) {
        if(null == nums || 0 == nums.length) {
            return 0;
        }
        int len = nums.length;
        if(1 == len) {
            return nums[0];
        }

        int max = Integer.MIN_VALUE, posMax = 1, negMax = 1;
        for(int i = 0; i < len; i++) {
            if(nums[i] < 0) {
                int temp = posMax;
                posMax = negMax;
                negMax = temp;
            }

            posMax = Math.max(posMax*nums[i], nums[i]);
            negMax = Math.min(negMax*nums[i], nums[i]);

            max = Math.max(max, posMax);
        }
        return max;
    }
}