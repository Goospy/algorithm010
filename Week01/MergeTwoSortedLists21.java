package geek.algo;

import com.goospy.common.ListNode;

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
