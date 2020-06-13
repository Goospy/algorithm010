package geek.algo;

/**
 * 1.解法一: 循环每次右移一位，移动k次
 * 2.解法二: 用一个新数组
 * 3.解法三: 遍历一次，用临时变量循环替换元素，要注意环形时终结，使用新的起始点。
 */
public class RotateArray189 {

    public void rotate(int[] nums, int k) {
        if(null == nums || 0 == nums.length || 0 == k%nums.length) {
            return;
        }

        int len = nums.length;
        int newK = k % len;
        int count = 0;
        for(int i = 0; count < len; i++) {
            int last = nums[i];
            int idx = i;
            do {
                int nextIdx = (idx + newK) % len;
                int temp = nums[nextIdx];
                nums[nextIdx] = last;
                last = temp;
                idx = nextIdx;
                count++;
            } while(i != idx);
        }
    }

}
