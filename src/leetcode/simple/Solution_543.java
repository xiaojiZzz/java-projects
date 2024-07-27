package leetcode.simple;

import java.util.LinkedList;
import java.util.Queue;


/**
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 * 示例 :
 * 给定二叉树
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 */
public class Solution_543 {
    //基础版
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return 0;
        }
        Queue<TreeNode> listNodes = new LinkedList<>();
        int max = 0;
        listNodes.offer(root);
        while (!listNodes.isEmpty()) {
            TreeNode node = listNodes.poll();
            if (node.left != null || node.right != null) {
                int leftLen = maxLen(node.left);
                int rightLen = maxLen(node.right);
                max = Math.max(max, (leftLen + rightLen));
                if (node.left != null) {
                    listNodes.offer(node.left);
                }
                if (node.right != null) {
                    listNodes.offer(node.right);
                }
            }
        }
        return max;
    }

    public int maxLen(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(maxLen(node.left), maxLen(node.right)) + 1;
    }
}

/*
class Solution {
    int ans = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return ans - 1;
    }

    public int depth(TreeNode node) {
        if (node == null) {
            return 0; // 访问到空节点了，返回0
        }
        int L = depth(node.left); // 左儿子为根的子树的深度
        int R = depth(node.right); // 右儿子为根的子树的深度
        ans = Math.max(ans, L + R + 1); // 计算d_node即L+R+1 并更新ans
        return Math.max(L, R) + 1; // 返回该节点为根的子树的深度
    }
}
*/
