package geek.algo;

/**
 * 1.统计非0的数字，将非0的数字先放到数组前面，后面的数字置为0
 * 2.双指针，p记录非0数字的下标，q记录当前数字下标，当前数字不为0则交换p、q下标对应数字，p、q自增，否则q自增。
 */
public class MoveZeroes283 {

    public void moveZeroes(int[] nums) {
        int j = 0;
        for(int i = 0; i < nums.length; i++) {
            if(0 != nums[i]) {
                nums[j] = nums[i];
                if(i != j) {
                    nums[i] = 0;
                }
                j++;
            }
        }
    }

}
