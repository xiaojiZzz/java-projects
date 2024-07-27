package leetcode.simple;


/**
 * 给定二叉树的根节点 root ，返回所有左叶子之和。
 * 示例 1：
 * 输入: root = [3,9,20,null,null,15,7]
 * 输出: 24
 * 解释: 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
 * 示例 2:
 * 输入: root = [1]
 * 输出: 0
 */
public class Solution_404 {

    private int ans;

    public int sumOfLeftLeaves(TreeNode root) {
        if (root.left == null && root.right == null) {
            return 0;
        }
        dfs(root.left, true);
        dfs(root.right, false);
        return ans;
    }

    public void dfs(TreeNode treeNode, boolean flag) {
        if (treeNode == null) {
            return;
        }
        if (flag && treeNode.left == null && treeNode.right == null) {
            ans += treeNode.val;
            return;
        }
        dfs(treeNode.left, true);
        dfs(treeNode.right, false);
    }
}
