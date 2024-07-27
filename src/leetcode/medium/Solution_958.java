package leetcode.medium;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 给你一棵二叉树的根节点 root ，请你判断这棵树是否是一棵 完全二叉树 。
 * 在一棵 完全二叉树 中，除了最后一层外，所有层都被完全填满，并且最后一层中的所有节点都尽可能靠左。最后一层（第 h 层）中可以包含 1 到 2h 个节点。
 * 示例 1：
 * 输入：root = [1,2,3,4,5,6]
 * 输出：true
 * 解释：最后一层前的每一层都是满的（即，节点值为 {1} 和 {2,3} 的两层），且最后一层中的所有节点（{4,5,6}）尽可能靠左。
 * 示例 2：
 * 输入：root = [1,2,3,4,5,null,7]
 * 输出：false
 * 解释：值为 7 的节点不满足条件「节点尽可能靠左」。
 * 提示：
 * 树中节点数目在范围 [1, 100] 内
 * 1 <= Node.val <= 1000
 */
public class Solution_958 {
    public boolean isCompleteTree(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int floor = 0;
        boolean flag = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            if (size != Math.pow(2, floor)) {
                flag = false;
            }
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (poll.left == null && poll.right != null) {
                    return false;
                }
                if (poll.left != null) {
                    if (!flag) {
                        return false;
                    }
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
                if (poll.right == null) {
                    flag = false;
                }
            }
            floor++;
        }
        return true;
    }
}

/*
class Solution {
    public boolean isCompleteTree(TreeNode root) {
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addLast(root);
        boolean flag = true;
        while (!deque.isEmpty()) {
            TreeNode node = deque.pollLast();
            if (node == null) {
                flag = false;
            } else {
                // 如果在非空节点前出现了空节点那么则为false
                if (!flag) {
                    return false;
                }
                deque.addFirst(node.left);
                deque.addFirst(node.right);
            }
        }
        return true;
    }
}
*/
