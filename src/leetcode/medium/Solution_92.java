package leetcode.medium;


/**
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。
 * 请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 * 示例 1：
 * 输入：head = [1,2,3,4,5], left = 2, right = 4
 * 输出：[1,4,3,2,5]
 */
public class Solution_92 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // 定义一个dummyHead, 方便处理
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode p0 = dummyHead;
        for (int i = 0; i < left - 1; i++) {
            p0 = p0.next;
        }
        ListNode pre = null;
        ListNode cur = p0.next;
        for (int i = 0; i < right - left + 1; i++) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        p0.next.next = cur;
        p0.next = pre;
        return dummyHead.next;
    }
}

/*
public class Solution_92 {
    //双指针
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // 定义一个dummyHead, 方便处理
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        // 初始化指针
        ListNode g = dummyHead;
        ListNode p = dummyHead.next;

        // 将指针移到相应的位置
        for (int step = 0; step < left - 1; step++) {
            g = g.next;
            p = p.next;
        }
        // 头插法插入节点
        for (int i = 0; i < right - left; i++) {
            ListNode removed = p.next;
            p.next = p.next.next;

            removed.next = g.next;
            g.next = removed;
        }

        return dummyHead.next;
    }
}
*/

/*
public class Solution_92 {
    //拆成三个链表，中间反转后拼接
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right || head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode lTmp = dummy;
        ListNode rTmp = dummy;
        ListNode tmp = null;
        for (int i = 1; i < left; i++) {
            lTmp = lTmp.next;
        }
        for (int i = 0; i < right; i++) {
            rTmp = rTmp.next;
        }
        tmp = lTmp.next;
        ListNode end = rTmp.next;
        lTmp.next = null;
        rTmp.next = null;
        ListNode curr = reverseList(tmp);
        lTmp.next = curr;
        tmp.next = end;
        return dummy.next;
    }

    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode res = new ListNode();
        res.next = head;
        ListNode tmp = head.next;
        while (tmp != null) {
            head.next = tmp.next;
            tmp.next = res.next;
            res.next = tmp;
            tmp = head.next;
        }
        return res.next;
    }
}
*/

/*
class Solution {

    ListNode successor = null; // 后驱节点

    //递归
    ListNode reverseBetween(ListNode head, int m, int n) {
        // base case
        if (m == 1) {
            return reverseN(head, n);
        }
        // 前进到反转的起点触发 base case
        head.next = reverseBetween(head.next, m - 1, n - 1);
        return head;
    }

    // 反转以 head 为起点的 n 个节点，返回新的头结点
    ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            // 记录第 n + 1 个节点
            successor = head.next;
            return head;
        }
        // 以 head.next 为起点，需要反转前 n - 1 个节点
        ListNode last = reverseN(head.next, n - 1);

        head.next.next = head;
        // 让反转之后的 head 节点和后面的节点连起来
        head.next = successor;
        return last;
    }
}
*/
