package leetcode.simple;


/**
 * 给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表 。
 * 示例：
 * 输入：head = [1,1,2,3,3]
 * 输出：[1,2,3]
 */

public class Solution_83 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.next.val != cur.val) {
                cur = cur.next;
            } else {
                cur.next = cur.next.next;
            }
        }
        return head;
    }
}

/*递归
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        // 递归
        head.next = deleteDuplicates(head.next);
        return head.next != null && head.val == head.next.val ? head.next : head;
    }
}*/
