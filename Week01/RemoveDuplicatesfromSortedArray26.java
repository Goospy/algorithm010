public class RemoveDuplicatesfromSortedArray26 {

    public int removeDuplicates(int[] nums) {
        if(null == nums || 0 == nums.length) {
            return 0;
        }
        if(1 == nums.length) {
            return 1;
        }

        int res = 1, j = 1;
        /**
         * 用j记录下一个不重复数字应该插入的下标，分两种情况：
         * 1.当前数字与前一数字相同，j不变
         * 2.当前数字与前一数字不相同，当前数字移动到j位置，j+1。
         */
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] != nums[i - 1]) {
                res++;
                nums[j] = nums[i];
                j++;
            }
        }
        return res;
    }

}
