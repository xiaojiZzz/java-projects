package leetcode.simple;


/**
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 * 示例 1：
 * 输入：head = [1,2,2,1]
 * 输出：true
 */
public class Solution_234 {
    //双指针
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        // 找到前半部分链表的尾节点并反转后半部分链表
        ListNode firstHalfEnd = endOfFirstHalf(head);
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);

        // 判断是否回文
        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        boolean result = true;
        while (result && p2 != null) {
            if (p1.val != p2.val) {
                result = false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        // 还原链表并返回结果
        firstHalfEnd.next = reverseList(secondHalfStart);
        return result;
    }

    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    private ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}

/*
public class Solution_234 {
    private ListNode frontPointer;

    //递归
    private boolean recursivelyCheck(ListNode currentNode) {
        if (currentNode != null) {
            if (!recursivelyCheck(currentNode.next)) {
                return false;
            }
            if (currentNode.val != frontPointer.val) {
                return false;
            }
            frontPointer = frontPointer.next;
        }
        return true;
    }

    public boolean isPalindrome(ListNode head) {
        frontPointer = head;
        return recursivelyCheck(head);
    }
}
*/

/*
public class Solution_234 {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode res = new ListNode();
        res.next = head;
        ListNode listNode = new ListNode(-1);
        ListNode node = listNode;
        while (head != null) {
            node.next = new ListNode(head.val);
            head = head.next;
            node = node.next;
        }
        head = res.next;
        ListNode tmp = head.next;
        while (tmp != null) {
            head.next = tmp.next;
            tmp.next = res.next;
            res.next = tmp;
            tmp = head.next;
        }
        ListNode newHead1 = res.next;
        ListNode newHead2 = listNode.next;
        while (newHead1 != null) {
            if (newHead1.val != newHead2.val) {
                return false;
            }
            newHead1 = newHead1.next;
            newHead2 = newHead2.next;
        }
        return true;
    }
}
*/

/*
public class Solution_234 {
    public boolean isPalindrome(ListNode head) {
        List<Integer> vals = new ArrayList<Integer>();

        // 将链表的值复制到数组中
        ListNode currentNode = head;
        while (currentNode != null) {
            vals.add(currentNode.val);
            currentNode = currentNode.next;
        }

        // 使用双指针判断是否回文
        int front = 0;
        int back = vals.size() - 1;
        while (front < back) {
            if (!vals.get(front).equals(vals.get(back))) {
                return false;
            }
            front++;
            back--;
        }
        return true;
    }
}
*/

/*
class Solution {
    //利用栈
    public boolean isPalindrome(ListNode head) {
        int len = 0;
        ListNode temp = head;
        Stack<Integer> stack = new Stack<>();
        while (temp != null) {
            len++;
            temp = temp.next;
        }
        temp = head;
        for (int i = 0; i < len / 2; i++) {
            stack.push(temp.val);
            temp = temp.next;
        }
        if (len % 2 != 0) temp = temp.next;
        while (temp != null) {
            if (temp.val != stack.pop()) return false;
            temp = temp.next;
        }
        return true;
    }
}
*/
