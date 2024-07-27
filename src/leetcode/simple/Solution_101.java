package leetcode.simple;

import java.util.LinkedList;


/**
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 * 示例：
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 */
public class Solution_101 {
    //迭代
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        } else if (root.left == null && root.right == null) {
            return true;
        } else if (root.left == null || root.right == null) {
            return false;
        }
        LinkedList<TreeNode> treeNodes = new LinkedList<>();
        treeNodes.offer(root.left);
        treeNodes.offer(root.right);
        while (!treeNodes.isEmpty()) {
            TreeNode poll1 = treeNodes.poll();
            TreeNode poll2 = treeNodes.poll();
            if (poll1.val != poll2.val) {
                return false;
            }
            if (poll1.left == null ^ poll2.right == null) {
                return false;
            } else if (poll1.right == null ^ poll2.left == null) {
                return false;
            }
            if (poll1.left != null) {
                treeNodes.offer(poll1.left);
                treeNodes.offer(poll2.right);
            }
            if (poll1.right != null) {
                treeNodes.offer(poll1.right);
                treeNodes.offer(poll2.left);
            }
        }
        return true;
    }
}

/*
class Solution {
    //递归
    public boolean isSymmetric(TreeNode root) {
        return isSym(root, root);
    }

    public boolean isSym(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        } else if (p.val != q.val) {
            return false;
        } else {
            return isSym(p.left, q.right) && isSym(p.right, q.left);
        }
    }
}
*/
