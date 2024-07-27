package leetcode.medium;

import java.util.LinkedList;
import java.util.Queue;


/**
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 * 示例 1：
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 * 示例 2：
 * 输入：head = []
 * 输出：[]
 * 示例 3：
 * 输入：head = [1]
 * 输出：[1]
 */
public class Solution_24 {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode();
        ListNode idx = dummy;
        Queue<ListNode> listNodes = new LinkedList<>();
        while (head != null) {
            listNodes.offer(head);
            head = head.next;
        }
        while (listNodes.size() > 1) {
            ListNode node1 = listNodes.poll();
            ListNode node2 = listNodes.poll();
            idx.next = node2;
            node2.next = node1;
            node1.next = null;
            idx = idx.next.next;
        }
        if (listNodes.size() == 1) {
            idx.next = listNodes.poll();
        }
        return dummy.next;
    }
}

/*
class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head.next;
        head.next = swapPairs(newHead.next);
        newHead.next = head;
        return newHead;
    }
}
*/

/*
class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode temp = dummyHead;
        while (temp.next != null && temp.next.next != null) {
            ListNode node1 = temp.next;
            ListNode node2 = temp.next.next;
            temp.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            temp = node1;
        }
        return dummyHead.next;
    }
}
*/
