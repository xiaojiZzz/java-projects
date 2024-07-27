package leetcode.medium;


/**
 * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
 * 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * 不允许修改 链表。
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：返回索引为 1 的链表节点
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 */
public class Solution_142 {
    public ListNode detectCycle(ListNode head) {
        //双指针
        if (head == null || head.next == null) {
            return null;
        }
        ListNode first = head;
        ListNode slow = head;
        do {
            first = first.next.next;
            slow = slow.next;
            if (first == null) {
                return null;
            }
        } while (first != slow && first.next != null);
        first = head;
        while (first != slow) {
            first = first.next;
            slow = slow.next;
            if (slow == null) {
                return null;
            }
        }
        return slow;
    }
}

/*
class Solution {
    public ListNode detectCycle(ListNode head) {
        HashSet<ListNode> visited = new HashSet<>();
        ListNode pos = head;
        while (pos != null) {
            if (visited.contains(pos)) {
                return pos;
            } else {
                visited.add(pos);
            }
            pos = pos.next;
        }
        return null;
    }
}
*/
