package leetcode.medium;


/**
 * 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。
 * 除了 root 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 * 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
 * 给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。
 * 示例 1:
 * 输入: root = [3,2,3,null,3,null,1]
 * 输出: 7
 * 解释: 小偷一晚能够盗取的最高金额 3 + 3 + 1 = 7
 */
public class Solution_337 {
    // 动态规划，递归，二叉树的后序遍历
    public int rob(TreeNode root) {
        return Math.max(robSolution(root)[0], robSolution(root)[1]);
    }

    public int[] robSolution(TreeNode root) {
        if (root == null) {
            return new int[2];
        }
        int[] res = new int[2];
        int[] left = robSolution(root.left);
        int[] right = robSolution(root.right);
        // 不偷
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        // 偷
        res[1] = left[0] + right[0] + root.val;
        return res;
    }
}

/*
class Solution {
    // 递归，解决重叠子问题，利用哈希表来记录计算过的节点
    public int rob(TreeNode root) {
        HashMap<TreeNode, Integer> memo = new HashMap<>();
        return robSolution(root, memo);
    }

    public int robSolution(TreeNode root, HashMap<TreeNode, Integer> memo) {
        if (root == null) {
            return 0;
        }
        if (memo.containsKey(root)) {
            return memo.get(root);
        }
        int money = root.val;
        if (root.left != null) {
            money += robSolution(root.left.left, memo) + robSolution(root.left.right, memo);
        }
        if (root.right != null) {
            money += robSolution(root.right.left, memo) + robSolution(root.right.right, memo);
        }
        int res = Math.max(money, robSolution(root.left, memo) + robSolution(root.right, memo));
        memo.put(root, res);
        return res;
    }
}
*/
