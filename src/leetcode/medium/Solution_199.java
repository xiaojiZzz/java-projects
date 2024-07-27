package leetcode.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 * 示例 1:
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1,3,4]
 * 示例 2:
 * 输入: [1,null,3]
 * 输出: [1,3]
 * 示例 3:
 * 输入: []
 * 输出: []
 */
public class Solution_199 {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return list;
        }
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode node = queue.poll();
                size--;
                if (size == 0) {
                    list.add(node.val);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return list;
    }
}

/*
class Solution {
    private List<Integer> list = new ArrayList<>();

    public List<Integer> rightSideView(TreeNode root) {
        f(root, 0);
        return list;
    }

    private void f(TreeNode node, int depth) {
        if (node == null) {
            return;
        }
        if (depth == list.size()) {
            list.add(node.val);
        }
        f(node.right, depth + 1);
        f(node.left, depth + 1);
    }
}
*/
