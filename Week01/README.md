#学习笔记

##作业：
###1.用 add first 或 add last 这套新的 API 改写 Deque 的代码

(```)
Deque<String> deque = new LinkedList<>();

//deque.push("a");
//deque.push("b");
//deque.push("c");
deque.addFirst("a");
deque.addFirst("b");
deque.addFirst("c");

System.out.println(deque);

String str = deque.peek();
System.out.println(str);
System.out.println(deque);

while(deque.size() > 0) {
//System.out.println(deque.pop());
System.out.println(deque.removeFirst());
}

System.out.println(deque);
(```)

###2.分析 Queue 和 Priority Queue 的源码
Queue是一个接口，定义了队列的操作规范，而PriorityQueue是Queue的一个实现，内部的话是通过一个一维数组实现，除了Queue接口之外，PriorityQueue还实现了其他集合的功能，区别在于PriorityQueue的add方法和offer方法是一个方法，都不会抛出异常，添加失败只返回false，和Queue定义的add和offer接口规范不太一致。

###3.删除排序数组中的重复项
(```)
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

(```)

###4.旋转数组
(```)
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
(```)

###5.合并两个有序链表
(```)
/**
 * 1.解法一：递归，递归调用mergeTwoLists方法，将较小的节点的next作为新参数递归合并。
 * 2.解法二：迭代，新建head节点，遍历两个链表，将当前节点的较小节点加入到head节点的下一节点，
 *          直至其中一个链表到达终点
 */
public class MergeTwoSortedLists21 {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(null == l1 && null == l2) {
            return null;
        }

        if(null == l1) {
            return l2;
        }

        if(null == l2) {
            return l1;
        }

        ListNode head = new ListNode(-1);
        ListNode temp = head;
        while(null != l1 && null != l2) {
            if(l1.val < l2.val) {
                temp.next = l1;
                l1 = l1.next;
                temp = temp.next;
            } else {
                temp.next = l2;
                l2 = l2.next;
                temp = temp.next;
            }
        }
        if(null != l1) {
            temp.next = l1;
        } else {
            temp.next = l2;
        }

        return head.next;
    }

}
(```)


###6.合并两个有序数组
(```)
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
(```)


###7.两数之和
(```)
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
(```)


###8.移动零
(```)
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

(```)


###9.加一
(```)
/**
 * 区分数字9与非9即可，进位处理。
 */
public class PlusOne66 {

    public int[] plusOne(int[] digits) {
        for(int i = digits.length - 1; i >= 0; i--) {
            if(9 == digits[i]) {
                digits[i] = 0;
            } else {
                digits[i] = digits[i] + 1;
                return digits;
            }
        }

        int[] newDigits = new int[digits.length + 1];
        newDigits[0] = 1;
        return newDigits;
    }

}
(```)

###10.设计循环双端队列
(```)
/**
 * 一开始用双向链表实现了。。
 */
public class MyCircularDeque {

    private int[] data;

    private int cap, head, tail;

    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque(int k) {
        cap = k + 1;
        head = 0;
        tail = 0;
        data = new int[cap];
    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if(isFull()) {
            return false;
        }

        head = (head - 1 + cap) % cap;
        data[head] = value;
        return true;
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if(isFull()) {
            return false;
        }

        data[tail] = value;
        tail = (tail + 1) % cap;
        return true;
    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if(isEmpty()) {
            return false;
        }

        head = (head + 1) % cap;
        return true;
    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if(isEmpty()) {
            return false;
        }

        tail = (tail - 1 + cap) % cap;
        return true;
    }

    /** Get the front item from the deque. */
    public int getFront() {
        if(isEmpty()) {
            return -1;
        }

        return data[head];
    }

    /** Get the last item from the deque. */
    public int getRear() {
        if(isEmpty()) {
            return -1;
        }

        return data[(tail - 1 + cap) % cap];
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return head == tail;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return (tail + 1) % cap == head;
    }

}
(```)

###11.接雨水
(```)
//这道题挺难的，解法也挺多，需要多重复几遍，只是选了个自己理解比较好的答案放这了，还得多看几遍。
//题解链接：https://leetcode-cn.com/problems/trapping-rain-water/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-8/
public int trap(int[] height) {
    int sum = 0;
    int[] max_left = new int[height.length];
    int[] max_right = new int[height.length];
    
    for (int i = 1; i < height.length - 1; i++) {
        max_left[i] = Math.max(max_left[i - 1], height[i - 1]);
    }
    for (int i = height.length - 2; i >= 0; i--) {
        max_right[i] = Math.max(max_right[i + 1], height[i + 1]);
    }
    for (int i = 1; i < height.length - 1; i++) {
        int min = Math.min(max_left[i], max_right[i]);
        if (min > height[i]) {
            sum = sum + (min - height[i]);
        }
    }
    return sum;
}
(```)













