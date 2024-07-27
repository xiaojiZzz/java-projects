package leetcode.medium;


/**
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 * 示例 1：
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 * 示例 2：
 * 输入：head = [-1,5,3,4,0]
 * 输出：[-1,0,3,4,5]
 * 示例 3：
 * 输入：head = []
 * 输出：[]
 */
public class Solution_148 {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        fast = slow.next;
        slow.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(fast);
        ListNode vHead = new ListNode(-1);
        ListNode cur = vHead;
        while (left != null && right != null) {
            if (left.val <= right.val) {
                cur.next = left;
                left = left.next;
            } else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        cur.next = left != null ? left : right;

        return vHead.next;
    }
}

/*
class Solution {
    //归并排序
    public ListNode sortList(ListNode head) {
        return sort(head, null);
    }

    public ListNode sort(ListNode head, ListNode tail) {
        if (head == null) {
            return head;
        }
        if (head.next == tail) {
            head.next = null;
            return head;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast != tail && fast.next != tail) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode node1 = sort(head, slow);
        ListNode node2 = sort(slow, tail);
        return merge(node1, node2);
    }

    public ListNode merge(ListNode node1, ListNode node2) {
        ListNode dummy = new ListNode();
        ListNode tmp = dummy;
        while (node1 != null && node2 != null) {
            if (node1.val <= node2.val) {
                tmp.next = node1;
                node1 = node1.next;
            } else {
                tmp.next = node2;
                node2 = node2.next;
            }
            tmp = tmp.next;
        }
        if (node1 != null) {
            tmp.next = node1;
        }
        if (node2 != null) {
            tmp.next = node2;
        }
        return dummy.next;
    }
}
*/

/*
class Solution {
    public ListNode sortList(ListNode head) {
        return quickSort(head);
    }

    public ListNode quickSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode h1 = new ListNode();
        ListNode h2 = new ListNode();
        ListNode h3 = new ListNode();
        ListNode t1 = h1;
        ListNode t2 = h2;
        ListNode t3 = h3;
        ListNode curr = head;
        int pivot = getMid(head).val;  // 用中间节点的原因是，如果每次用最左边的结点，对于纯递增和纯递减的case就退化为O(n)

        while (curr != null) {
            ListNode next = curr.next;
            if (curr.val < pivot) {
                curr.next = null;
                t1.next = curr;
                t1 = t1.next;
            } else if (curr.val == pivot) {
                curr.next = null;
                t2.next = curr;
                t2 = t2.next;
            } else {
                curr.next = null;
                t3.next = curr;
                t3 = t3.next;
            }
            curr = next;
        }

        // < 的链表和 > 的链表分别快排
        h1 = quickSort(h1.next);
        h3 = quickSort(h3.next);

        // h1链表不一定有元素 h2链表一定有元素 先把h2、h3连起来
        h2 = h2.next;
        t2.next = h3;
        // 如果h1链表为空 直接返回h2即可
        // 否则找到h1链表的结尾，连上h2的头
        if (h1 == null) {
            return h2;
        } else {
            t1 = h1;
            // 找到t1链表的结尾
            while (t1.next != null) {
                t1 = t1.next;
            }
            t1.next = h2;
            return h1;
        }
    }

    public ListNode getMid(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

}
*/