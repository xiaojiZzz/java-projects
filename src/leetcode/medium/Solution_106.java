package leetcode.medium;

import java.util.HashMap;
import java.util.Map;


/**
 * 给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历，请你构造并返回这颗 二叉树 。
 * 示例 1:
 * 输入：inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
 * 输出：[3,9,20,null,null,15,7]
 */
public class Solution_106 {
    private Map<Integer, Integer> indexMap;

    //递归
    public TreeNode myBuildTree(int[] postorder, int[] inorder, int postorder_left, int postorder_right, int inorder_left, int inorder_right) {
        if (postorder_left > postorder_right) {
            return null;
        }

        // 后序遍历中的最后一个节点就是根节点
        int postorder_root = postorder_right;
        // 在中序遍历中定位根节点
        int inorder_root = indexMap.get(postorder[postorder_root]);

        // 先把根节点建立出来
        TreeNode root = new TreeNode(postorder[postorder_root]);
        // 得到右子树中的节点数目
        int size_right_subtree = inorder_right - inorder_root;
        root.left = myBuildTree(postorder, inorder, postorder_left, postorder_right - 1 - size_right_subtree, inorder_left, inorder_root - 1);
        root.right = myBuildTree(postorder, inorder, postorder_right - size_right_subtree, postorder_right - 1, inorder_root + 1, inorder_right);
        return root;
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int n = postorder.length;
        // 构造哈希映射，帮助我们快速定位根节点
        indexMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return myBuildTree(postorder, inorder, 0, n - 1, 0, n - 1);
    }
}

/*
class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (postorder == null || postorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[postorder.length - 1]);
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        stack.push(root);
        int inorderIndex = inorder.length - 1;
        for (int i = postorder.length - 2; i >= 0; i--) {
            int postorderVal = postorder[i];
            TreeNode node = stack.peek();
            if (node.val != inorder[inorderIndex]) {
                node.right = new TreeNode(postorderVal);
                stack.push(node.right);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex--;
                }
                node.left = new TreeNode(postorderVal);
                stack.push(node.left);
            }
        }
        return root;
    }
}
*/
