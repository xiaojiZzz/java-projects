package leetcode.medium;


/**
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 * 示例 1：
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[4,5,1,2,3]
 * 示例 2：
 * 输入：head = [0,1,2], k = 4
 * 输出：[2,0,1]
 */
public class Solution_61 {
    public ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1, head);
        int n = 0;
        while (head != null) {
            n++;
            head = head.next;
        }
        head = dummy.next;
        k = k % n;
        if (k == 0) {
            return dummy.next;
        }
        ListNode pre = dummy;
        ListNode cur = dummy.next;
        for (int i = 0; i < n - k; i++) {
            pre = pre.next;
            cur = cur.next;
        }
        pre.next = null;
        dummy.next = cur;
        for (int i = 1; i < k; i++) {
            cur = cur.next;
        }
        cur.next = head;
        return dummy.next;
    }
}
