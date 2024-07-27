package leetcode.simple;

import java.util.*;


/**
 * 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
 * 示例：
 * 输入：root = [1,null,2,3]
 * 输出：[1,3,2]
 */
public class Solution_94 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            ans.add(root.val);
            root = root.right;
        }
        return ans;
    }
}

/*
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        res(root, list);
        return list;
    }

    public void res(TreeNode node, ArrayList list) {
        if (node == null) {
            return;
        } else {
            res(node.left, list);
            list.add(node.val);
            res(node.right, list);
        }
    }
}
*/
