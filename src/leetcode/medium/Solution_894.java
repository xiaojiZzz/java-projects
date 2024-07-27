package leetcode.medium;

import java.util.ArrayList;
import java.util.List;


/**
 * 给你一个整数 n ，请你找出所有可能含 n 个节点的 真二叉树 ，并以列表形式返回。答案中每棵树的每个节点都必须符合 Node.val == 0 。
 * 答案的每个元素都是一棵真二叉树的根节点。你可以按 任意顺序 返回最终的真二叉树列表。
 * 真二叉树 是一类二叉树，树中每个节点恰好有 0 或 2 个子节点。
 * 示例 1：
 * 输入：n = 7
 * 输出：[[0,0,0,null,null,0,0,null,null,0,0],[0,0,0,null,null,0,0,0,0],
 * [0,0,0,0,0,0,0],[0,0,0,0,0,null,null,null,null,0,0],[0,0,0,0,0,null,null,0,0]]
 * 示例 2：
 * 输入：n = 3
 * 输出：[[0,0,0]]
 */
public class Solution_894 {
    public List<TreeNode> allPossibleFBT(int n) {
        List<TreeNode> ans = new ArrayList<>();
        if (n % 2 == 0) {
            return ans;
        } else if (n == 1) {
            ans.add(new TreeNode(0));
            return ans;
        }
        for (int i = 1; i < n; i += 2) {
            List<TreeNode> left = allPossibleFBT(i);
            List<TreeNode> right = allPossibleFBT(n - i - 1);
            for (TreeNode node1 : left) {
                for (TreeNode node2 : right) {
                    TreeNode node = new TreeNode(0, node1, node2);
                    ans.add(node);
                }
            }
        }
        return ans;
    }
}

/*
class Solution {
    public List<TreeNode> allPossibleFBT(int n) {
        if (n % 2 == 0) {
            return new ArrayList<TreeNode>();
        }

        List<TreeNode>[] dp = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            dp[i] = new ArrayList<TreeNode>();
        }
        dp[1].add(new TreeNode(0));
        for (int i = 3; i <= n; i += 2) {
            for (int j = 1; j < i; j += 2) {
                for (TreeNode leftSubtree : dp[j]) {
                    for (TreeNode rightSubtrees : dp[i - 1 - j]) {
                        TreeNode root = new TreeNode(0, leftSubtree, rightSubtrees);
                        dp[i].add(root);
                    }
                }
            }
        }
        return dp[n];
    }
}
*/
