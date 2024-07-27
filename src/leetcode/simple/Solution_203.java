package leetcode.simple;


/**
 * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
 * 示例 1：
 * 输入：head = [1,2,6,3,4,5,6], val = 6
 * 输出：[1,2,3,4,5]
 * 示例 2：
 * 输入：head = [], val = 1
 * 输出：[]
 * 示例 3：
 * 输入：head = [7,7,7,7], val = 7
 * 输出：[]
 */
public class Solution_203 {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0, head);
        ListNode tmp = dummy;
        ListNode next = dummy.next;
        while (next != null) {
            if (next.val == val) {
                tmp.next = next.next;
            } else {
                tmp = tmp.next;
            }
            next = next.next;
        }
        return dummy.next;
    }
}

/*
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;
    }
}
*/
