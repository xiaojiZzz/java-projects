package leetcode.medium;

import java.util.HashMap;


/**
 * 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。
 * 构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。
 * 新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。
 * 复制链表中的指针都不应指向原链表中的节点 。
 * 例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。
 * 返回复制链表的头节点。
 * 用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：
 * val：一个表示 Node.val 的整数。
 * random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
 * 你的代码 只 接受原链表的头节点 head 作为传入参数。
 * 示例 1：
 * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 示例 2：
 * 输入：head = [[1,1],[2,1]]
 * 输出：[[1,1],[2,1]]
 * 示例 3：
 * 输入：head = [[3,null],[3,0],[3,null]]
 * 输出：[[3,null],[3,0],[3,null]]
 */
public class Solution_138 {
    private HashMap<Node, Node> map = new HashMap<>();

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        if (!map.containsKey(head)) {
            Node node = new Node(head.val);
            map.put(head, node);
            node.next = copyRandomList(head.next);
            node.random = copyRandomList(head.random);
            return node;
        }
        return map.get(head);
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    /*
    class Solution {
        public Node copyRandomList(Node head) {
            if (head == null) return null;
            Node cur = head;
            Map<Node, Node> map = new HashMap<>();
            // 3. 复制各节点，并建立 “原节点 -> 新节点” 的 Map 映射
            while (cur != null) {
                map.put(cur, new Node(cur.val));
                cur = cur.next;
            }
            cur = head;
            // 4. 构建新链表的 next 和 random 指向
            while (cur != null) {
                map.get(cur).next = map.get(cur.next);
                map.get(cur).random = map.get(cur.random);
                cur = cur.next;
            }
            // 5. 返回新链表的头节点
            return map.get(head);
        }
    }
    */

    /*
    class Solution {
        public Node copyRandomList(Node head) {
            if (head == null) {
                return null;
            }

            // 复制每个节点，把新节点直接插到原节点的后面
            for (Node cur = head; cur != null; cur = cur.next.next) {
                cur.next = new Node(cur.val, cur.next);
            }

            // 遍历交错链表中的原链表节点
            for (Node cur = head; cur != null; cur = cur.next.next) {
                if (cur.random != null) {
                    // 要复制的 random 是 cur.random 的下一个节点
                    cur.next.random = cur.random.next;
                }
            }

            // 把交错链表分离成两个链表
            Node newHead = head.next;
            Node cur = head;
            for (; cur.next.next != null; cur = cur.next) {
                Node copy = cur.next;
                cur.next = copy.next; // 恢复原节点的 next
                copy.next = copy.next.next; // 设置新节点的 next
            }
            cur.next = null; // 恢复原节点的 next
            return newHead;
        }
    }
    */
}