package leetcode.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


/**
 * 给定一个二叉树：
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL 。
 * 初始状态下，所有 next 指针都被设置为 NULL 。
 * 示例 1：
 * 输入：root = [1,2,3,4,5,null,7]
 * 输出：[1,#,2,3,#,4,5,7,#]
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。
 * 序列化输出按层序遍历顺序（由 next 指针连接），'#' 表示每层的末尾。
 * 示例 2：
 * 输入：root = []
 * 输出：[]
 */
public class Solution_117 {
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        Queue<Node> nodes = new LinkedList<>();
        nodes.offer(root);
        while (!nodes.isEmpty()) {
            int nums = nodes.size();
            ArrayList<Node> list = new ArrayList<>();
            for (int i = 0; i < nums; i++) {
                Node node = nodes.poll();
                if (node.left != null) {
                    nodes.offer(node.left);
                }
                if (node.right != null) {
                    nodes.offer(node.right);
                }
                list.add(node);
            }
            for (int i = 0; i < nums - 1; i++) {
                list.get(i).next = list.get(i + 1);
            }
            list.get(nums - 1).next = null;
        }
        return root;
    }
}

/*
class Solution {
    public Node connect(Node root) {
        if (root == null)
            return root;
        //cur我们可以把它看做是每一层的链表
        Node cur = root;
        while (cur != null) {
            //遍历当前层的时候，为了方便操作在下一
            //层前面添加一个哑结点（注意这里是访问
            //当前层的节点，然后把下一层的节点串起来）
            Node dummy = new Node(0);
            //pre表示访下一层节点的前一个节点
            Node pre = dummy;
            //然后开始遍历当前层的链表
            while (cur != null) {
                if (cur.left != null) {
                    //如果当前节点的左子节点不为空，就让pre节点
                    //的next指向他，也就是把它串起来
                    pre.next = cur.left;
                    //然后再更新pre
                    pre = pre.next;
                }
                //同理参照左子树
                if (cur.right != null) {
                    pre.next = cur.right;
                    pre = pre.next;
                }
                //继续访问这一行的下一个节点
                cur = cur.next;
            }
            //把下一层串联成一个链表之后，让他赋值给cur，
            //后续继续循环，直到cur为空为止
            cur = dummy.next;
        }
        return root;
    }
}
*/

/*
class Solution {
    private final List<Node> pre = new ArrayList<>();

    public Node connect(Node root) {
        dfs(root, 0); // 根节点的深度为 0
        return root;
    }

    private void dfs(Node node, int depth) {
        if (node == null) {
            return;
        }
        if (depth == pre.size()) { // node 是这一层最左边的节点
            pre.add(node);
        } else { // pre[depth] 是 node 左边的节点
            pre.get(depth).next = node; // node 左边的节点指向 node
            pre.set(depth, node);
        }
        dfs(node.left, depth + 1);
        dfs(node.right, depth + 1);
    }
}
*/
