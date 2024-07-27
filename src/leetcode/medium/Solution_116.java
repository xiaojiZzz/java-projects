package leetcode.medium;

import java.util.Deque;
import java.util.LinkedList;


/**
 * 给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * 初始状态下，所有 next 指针都被设置为 NULL。
 * 示例 1：
 * 输入：root = [1,2,3,4,5,6,7]
 * 输出：[1,#,2,3,#,4,5,6,7,#]
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点.
 * 序列化的输出按层序遍历排列，同一层节点由 next 指针连接，'#' 标志着每一层的结束。
 */
public class Solution_116 {
    public Node connect(Node root) {
        Deque<Node> queue = new LinkedList<>();
        if (root == null) {
            return root;
        }
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                size--;
                Node node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                if (size == 0) {
                    node.next = null;
                    break;
                }
                Node tmp = queue.poll();
                node.next = tmp;
                queue.addFirst(tmp);
            }
        }
        return root;
    }
}

/*
class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        // 从根节点开始
        Node leftmost = root;
        while (leftmost.left != null) {
            // 遍历这一层节点组织成的链表，为下一层的节点更新 next 指针
            Node head = leftmost;
            while (head != null) {
                // CONNECTION 1
                head.left.next = head.right;
                // CONNECTION 2
                if (head.next != null) {
                    head.right.next = head.next.left;
                }
                // 指针向后移动
                head = head.next;
            }
            // 去下一层的最左的节点
            leftmost = leftmost.left;
        }
        return root;
    }
}
*/

/*
class Solution {
    //递归
    public Node connect(Node root) {
        dfs(root);
        return root;
    }

    void dfs(Node root) {
        if (root == null) {
            return;
        }
        Node left = root.left;
        Node right = root.right;
        //配合动画演示理解这段，以root为起点，将整个纵深这段串联起来
        while (left != null) {
            left.next = right;
            left = left.right;
            right = right.left;
        }
        //递归的调用左右节点，完成同样的纵深串联
        dfs(root.left);
        dfs(root.right);
    }
}
*/

/*
class Solution {
    public Node connect(Node root) {
        if (root == null || root.left == null)
            return root;

        // 当前节点左孩子的next指向其右孩子
        root.left.next = root.right;

        // 当前节点右孩子指向其兄弟节点的左孩子
        if (root.next != null) {
            root.right.next = root.next.left;
        }
        connect(root.left);
        connect(root.right);
        return root;
    }
}
*/
