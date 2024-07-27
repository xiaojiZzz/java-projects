package leetcode.medium;


/**
 * 给你一个整数 n 表示一棵 满二叉树 里面节点的数目，节点编号从 1 到 n 。
 * 根节点编号为 1 ，树中每个非叶子节点 i 都有两个孩子，分别是左孩子 2 * i 和右孩子 2 * i + 1 。
 * 树中每个节点都有一个值，用下标从 0 开始、长度为 n 的整数数组 cost 表示，其中 cost[i] 是第 i + 1 个节点的值。
 * 每次操作，你可以将树中 任意 节点的值 增加 1 。你可以执行操作 任意 次。
 * 你的目标是让根到每一个 叶子结点 的路径值相等。请你返回 最少 需要执行增加操作多少次。
 * 注意：
 * 满二叉树 指的是一棵树，它满足树中除了叶子节点外每个节点都恰好有 2 个子节点，且所有叶子节点距离根节点距离相同。
 * 路径值 指的是路径上所有节点的值之和。
 * 示例 1：
 * 输入：n = 7, cost = [1,5,2,2,3,3,1]
 * 输出：6
 * 解释：我们执行以下的增加操作：
 * - 将节点 4 的值增加一次。
 * - 将节点 3 的值增加三次。
 * - 将节点 7 的值增加两次。
 * 从根到叶子的每一条路径值都为 9 。
 * 总共增加次数为 1 + 3 + 2 = 6 。
 * 这是最小的答案。
 * 示例 2：
 * 输入：n = 3, cost = [5,3,3]
 * 输出：0
 * 解释：两条路径已经有相等的路径值，所以不需要执行任何增加操作。
 */
public class Solution_2673 {
    public int minIncrements(int n, int[] cost) {
        int ans = 0;
        int idx = n / 2 - 1;
        for (int i = idx; i >= 0; i--) {
            int left = cost[2 * i + 1];
            int right = cost[2 * i + 2];
            ans += left >= right ? left - right : right - left;
            cost[i] += left >= right ? left : right;
        }
        return ans;
    }
}

/*
class Solution {
    private int res = 0;    // 最小操作数

    public int minIncrements(int n, int[] cost) {
        dfs(1, n, cost);    // 从根节点开始搜索
        return res;
    }

    // 返回以i为根的子树，到达所有叶子节点的路径值都相等的那个路径值
    private int dfs(int i, int n, int[] cost) {
        if (2 * i > n) {
            return cost[i - 1];    // 叶子节点，直接返回节点值
        }
        int leftPathValue = dfs(2 * i, n, cost);        // 获取左子树的路径值
        int rightPathValue = dfs(2 * i + 1, n, cost);   // 获取右子树的路径值
        res += Math.max(leftPathValue, rightPathValue) - Math.min(leftPathValue, rightPathValue); // 少的往多的增加
        return cost[i - 1] + Math.max(leftPathValue, rightPathValue);    // 两个子树的路径值都变成多的那个，再加上i自己的节点值，即为i为根的子树的路径值
    }
}
*/
