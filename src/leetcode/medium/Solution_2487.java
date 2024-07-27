package leetcode.medium;

import java.util.Deque;
import java.util.LinkedList;


/**
 * 给你一个链表的头节点 head 。
 * 移除每个右侧有一个更大数值的节点。
 * 返回修改后链表的头节点 head 。
 * 示例 1：
 * 输入：head = [5,2,13,3,8]
 * 输出：[13,8]
 * 解释：需要移除的节点是 5 ，2 和 3 。
 * - 节点 13 在节点 5 右侧。
 * - 节点 13 在节点 2 右侧。
 * - 节点 8 在节点 3 右侧。
 * 示例 2：
 * 输入：head = [1,1,1,1]
 * 输出：[1,1,1,1]
 * 解释：每个节点的值都是 1 ，所以没有需要移除的节点。
 */
public class Solution_2487 {
    public ListNode removeNodes(ListNode head) {
        Deque<ListNode> stack = new LinkedList<>();
        ListNode p = new ListNode(100001);
        stack.push(p);
        while (head != null) {
            if (stack.peek().val < head.val) {
                while (stack.peek().val < head.val) {
                    stack.pop();
                }
                stack.peek().next = head;
            }
            stack.peek().next = head;
            stack.push(head);
            head = head.next;
        }
        return p.next;
    }
}

/*
class Solution {
    public ListNode removeNodes(ListNode head) {
        Deque<ListNode> stack = new ArrayDeque<ListNode>();
        for (; head != null; head = head.next) {
            stack.push(head);
        }
        for (; !stack.isEmpty(); stack.pop()) {
            if (head == null || stack.peek().val >= head.val) {
                stack.peek().next = head;
                head = stack.peek();
            }
        }
        return head;
    }
}
*/

/*
class Solution {
    public ListNode removeNodes(ListNode head) {
        if (head == null) {
            return null;
        }
        head.next = removeNodes(head.next);
        if (head.next != null && head.val < head.next.val) {
            return head.next;
        } else {
            return head;
        }
    }
}
*/
