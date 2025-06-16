package leetcode.medium;


/**
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 示例 1：
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 */
public class Solution_2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            if (l1 != null) {
                carry += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                carry += l2.val;
                l2 = l2.next;
            }
            cur = cur.next = new ListNode(carry % 10);
            carry /= 10;
        }
        return dummy.next;
    }
}

/*
class Solution {
    // 递归
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return anNode(l1, l2, 0);
    }

    private ListNode anNode(ListNode l1, ListNode l2, int tmp) {
        if (l1 != null || l2 != null) {
            int num = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + tmp;
            return new ListNode(num % 10, anNode(l1 == null ? null : l1.next, l2 == null ? null : l2.next, num / 10));
        } else if (tmp != 0) {
            return new ListNode(tmp);
        }
        return null;
    }
}
*/
