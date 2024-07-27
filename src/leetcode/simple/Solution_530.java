package leetcode.simple;


/**
 * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
 * 差值是一个正数，其数值等于两值之差的绝对值。
 * 示例 1：
 * 输入：root = [4,2,6,1,3]
 * 输出：1
 * 示例 2：
 * 输入：root = [1,0,48,null,null,12,49]
 * 输出：1
 */
public class Solution_530 {

    private int min = Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root) {
        if (root == null) {
            return 0;
        }
        getMin(root, root.val);
        getMinimumDifference(root.left);
        getMinimumDifference(root.right);
        return min;
    }

    public void getMin(TreeNode node, int val) {
        TreeNode left = node.left;
        while (left != null && left.right != null) {
            left = left.right;
        }
        if (left != null) {
            min = Math.min(min, Math.abs(left.val - val));
        }
        TreeNode right = node.right;
        while (right != null && right.left != null) {
            right = right.left;
        }
        if (right != null) {
            min = Math.min(min, Math.abs(right.val - val));
        }
    }
}

/*
class Solution {
    int pre;
    int ans;

    public int getMinimumDifference(TreeNode root) {
        ans = Integer.MAX_VALUE;
        pre = -1;
        dfs(root);
        return ans;
    }

    public void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        if (pre == -1) {
            pre = root.val;
        } else {
            ans = Math.min(ans, root.val - pre);
            pre = root.val;
        }
        dfs(root.right);
    }
}
*/
