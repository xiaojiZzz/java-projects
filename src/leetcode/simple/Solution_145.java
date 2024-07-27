package leetcode.simple;

import java.util.*;


/**
 * 给你一棵二叉树的根节点 root ，返回其节点值的 后序遍历 。
 * 示例 1：
 * 输入：root = [1,null,2,3]
 * 输出：[3,2,1]
 */
public class Solution_145 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            res.add(node.val);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        Collections.reverse(res);
        return res;
    }
}

/*
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        res(root, list);
        return list;
    }

    public void res(TreeNode node, List list) {
        if (node == null) {
            return;
        } else {
            res(node.left, list);
            res(node.right, list);
            list.add(node.val);
        }
    }
}
*/
