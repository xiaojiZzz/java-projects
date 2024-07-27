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
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode listNode = new ListNode();
        ListNode list = listNode;
        int flag = 0;
        while (l1 != null && l2 != null) {
            listNode.next = new ListNode();
            listNode = listNode.next;
            int sum = l1.val + l2.val + flag;
            listNode.val = sum % 10;
            flag = sum / 10;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            listNode.next = new ListNode();
            listNode = listNode.next;
            int sum = l1.val + flag;
            listNode.val = sum % 10;
            flag = sum / 10;
            l1 = l1.next;
        }
        while (l2 != null) {
            listNode.next = new ListNode();
            listNode = listNode.next;
            int sum = l2.val + flag;
            listNode.val = sum % 10;
            flag = sum / 10;
            l2 = l2.next;
        }
        if (flag == 1) {
            listNode.next = new ListNode(1);
        }
        return list.next;
    }
}

/*
class Solution {
    // 递归
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int tmp = 0;
        return anNode(l1, l2, tmp);
    }

    private ListNode anNode(ListNode l1, ListNode l2, int tmp) {
        if (l1 != null || l2 != null) {
            int num = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + tmp;
            return new ListNode(num % 10, anNode(l1 == null ? null : l1.next, l2 == null ? null : l2.next, num / 10));
        } else {
            if (tmp != 0) {
                return new ListNode(tmp);
            }
        }
        return null;
    }
}
*/
