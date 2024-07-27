package leetcode.difficulty;


/**
 * 二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列中 至多出现一次 。
 * 该路径 至少包含一个 节点，且不一定经过根节点。
 * 路径和 是路径中各节点值的总和。
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 * 示例 1：
 * 输入：root = [1,2,3]
 * 输出：6
 * 解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
 * 示例 2：
 * 输入：root = [-10,9,20,null,null,15,7]
 * 输出：42
 * 解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
 */
public class Solution_124 {
    private int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        max(root);
        return max;
    }

    private int max(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftMax = max(node.left);
        int rightMax = max(node.right);
        max = Math.max(max, node.val + leftMax + rightMax);
        return Math.max(node.val + Math.max(leftMax, rightMax), 0);
    }
}
