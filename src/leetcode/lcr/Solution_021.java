package leetcode.lcr;

import java.util.Deque;
import java.util.LinkedList;


/**
 * 给定一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * 示例 1：
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 * 示例 2：
 */
public class Solution_021 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        Deque<ListNode> stack = new LinkedList<ListNode>();
        ListNode cur = dummy;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        for (int i = 0; i < n; ++i) {
            stack.pop();
        }
        ListNode prev = stack.peek();
        prev.next = prev.next.next;
        ListNode ans = dummy.next;
        return ans;
    }
}


//public class Solution_021 {
//    public ListNode removeNthFromEnd(ListNode head, int n) {
//        if (head == null) {
//            return null;
//        }
//        ListNode res1 = new ListNode(0);
//        res1.next = head;
//        ListNode res2 = res1;
//        ListNode res = res1;
//        for (int i = 0; i < n; i++) {
//            if (res2.next != null) {
//                res2 = res2.next;
//            } else {
//                return null;
//            }
//        }
//        while (res2.next != null) {
//            res1 = res1.next;
//            res2 = res2.next;
//        }
//        res1.next = res1.next.next;
//        return res.next;
//    }
//}