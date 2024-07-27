package leetcode.medium;


/**
 * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
 * 示例 1：
 * 输入：n = 3
 * 输出：5
 * 示例 2：
 * 输入：n = 1
 * 输出：1
 */
public class Solution_96 {
    public int numTrees(int n) {
        int[] G = new int[n + 1];
        G[0] = 1;
        G[1] = 1;

        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j <= i; ++j) {
                G[i] += G[j - 1] * G[i - j];
            }
        }
        return G[n];
    }
}

/*
class Solution {
    private int[] memo;

    public int numTrees(int n) {
        memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return dfs(n);
    }

    public int dfs(int n) {
        if (n <= 1) {
            return 1;
        }
        if (memo[n] != -1) {
            return memo[n];
        }
        int res = 0;
        for (int i = 1; i <= n; i++) {
            res += dfs(i - 1) * dfs(n - i);
        }
        return memo[n] = res;
    }
}
*/
