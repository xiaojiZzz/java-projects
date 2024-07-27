package leetcode.medium;

import java.util.HashMap;
import java.util.Map;


/**
 * 给你一个下标从 0 开始的整数数组 nums ，它包含 n 个 互不相同 的正整数。如果 nums 的一个排列满足以下条件，我们称它是一个特别的排列：
 * 对于 0 <= i < n - 1 的下标 i ，要么 nums[i] % nums[i+1] == 0 ，要么 nums[i+1] % nums[i] == 0 。
 * 请你返回特别排列的总数目，由于答案可能很大，请将它对 109 + 7 取余 后返回。
 * 示例 1：
 * 输入：nums = [2,3,6]
 * 输出：2
 * 解释：[3,6,2] 和 [2,6,3] 是 nums 两个特别的排列。
 * 示例 2：
 * 输入：nums = [1,4,3]
 * 输出：2
 * 解释：[3,1,4] 和 [4,1,3] 是 nums 两个特别的排列。
 */
public class Solution_2741 {
    public int specialPerm(int[] nums) {
        int n = nums.length;
        Map<String, Integer> memo = new HashMap<>();
        boolean[] visited = new boolean[nums.length];
        return dfs(nums, 0, -1, visited, 0, memo);

    }

    // dfs(depth, prevPos, u) 定义为：
    // 0到第depth个位置的特殊排列最后一位下标为为prevPos，且排列对应的二进制占位为u，确定depth位的特殊排列的总数目。
    private int dfs(int[] nums, int depth, int prevPos, boolean[] visited, int u, Map<String, Integer> memo) {
        if (depth == nums.length) {
            return 1;
        }
        // 以 prevPos 和 二进制占位为key，只要二进制占位相同，数组长度也必然是相同的。
        String key = prevPos + "#" + u;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                if (prevPos == -1 || nums[prevPos] % nums[i] == 0 || nums[i] % nums[prevPos] == 0) {
                    visited[i] = true;
                    res = (res + dfs(nums, depth + 1, i, visited, u | (1 << i), memo)) % 1000000007;
                    visited[i] = false;
                }
            }
        }
        memo.put(key, res);
        return res;
    }
}

/*
class Solution {
    // 压状 dp
    public int specialPerm(int[] nums) {
        int n = nums.length;
        int u = (1 << n) - 1;
        long[][] f = new long[u][n];
        Arrays.fill(f[0], 1L);
        for (int s = 1; s < u; s++) {
            for (int i = 0; i < n; i++) {
                if ((s >> i & 1) != 0) {
                    continue;
                }
                for (int j = 0; j < n; j++) {
                    if ((s >> j & 1) != 0 && (nums[i] % nums[j] == 0 || nums[j] % nums[i] == 0)) {
                        f[s][i] += f[s ^ (1 << j)][j];
                    }
                }
            }
        }
        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans += f[u ^ (1 << i)][i];
        }
        return (int) (ans % 1_000_000_007);
    }
}
*/

/*
class Solution {
    public int specialPerm(int[] nums) {
        int n = nums.length;
        int u = (1 << n) - 1;
        long[][] memo = new long[u][n];
        for (long[] row : memo) {
            Arrays.fill(row, -1); // -1 表示没有计算过
        }
        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans += dfs(u ^ (1 << i), i, nums, memo);
        }
        return (int) (ans % 1_000_000_007);
    }

    private long dfs(int s, int i, int[] nums, long[][] memo) {
        if (s == 0) {
            return 1; // 找到一个特别排列
        }
        if (memo[s][i] != -1) { // 之前计算过
            return memo[s][i];
        }
        long res = 0;
        for (int j = 0; j < nums.length; j++) {
            if ((s >> j & 1) > 0 && (nums[i] % nums[j] == 0 || nums[j] % nums[i] == 0)) {
                res += dfs(s ^ (1 << j), j, nums, memo);
            }
        }
        return memo[s][i] = res; // 记忆化
    }
}
*/
