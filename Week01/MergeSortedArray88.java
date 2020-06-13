package geek.algo;

/**
 * 1.直接使用库函数System.arraycopy(nums2, 0, nums1, m, n); 合并后重新排序
 * 2.双指针从两个数组的头节点进行合并，需要申请新数组或者插入前移动nums1元素
 * 3.最棒的还是双指针从两个数组的尾节点进行合并，时间空间复杂度都是最低
 *
 * 本人用的是方法2。。
 *
 */
public class MergeSortedArray88 {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int start = 0;
        for(int i = 0; i < n; i++) {
            int j = start;
            for(; j < m + i; j++) {
                start = j;
                if(nums1[j] > nums2[i]) {
                    //将第j至m+i位往右移动一位
                    for(int k = m + i; k > j; k--) {
                        nums1[k] = nums1[k - 1];
                    }
                    nums1[j] = nums2[i];
                    break;
                }
            }
            if(j == m + i) {
                nums1[m + i] = nums2[i];
            }
        }
    }

}
