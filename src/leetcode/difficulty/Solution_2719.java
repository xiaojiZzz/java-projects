package leetcode.difficulty;

import java.util.Arrays;


/**
 * 给你两个数字字符串 num1 和 num2 ，以及两个整数 max_sum 和 min_sum 。如果一个整数 x 满足以下条件，我们称它是一个好整数：
 * num1 <= x <= num2
 * min_sum <= digit_sum(x) <= max_sum.
 * 请你返回好整数的数目。答案可能很大，请返回答案对 109 + 7 取余后的结果。
 * 注意，digit_sum(x) 表示 x 各位数字之和。
 * 示例 1：
 * 输入：num1 = "1", num2 = "12", min_num = 1, max_num = 8
 * 输出：11
 * 解释：总共有 11 个整数的数位和在 1 到 8 之间，分别是 1,2,3,4,5,6,7,8,10,11 和 12 。所以我们返回 11 。
 * 示例 2：
 * 输入：num1 = "1", num2 = "5", min_num = 1, max_num = 5
 * 输出：5
 * 解释：数位和在 1 到 5 之间的 5 个整数分别为 1,2,3,4 和 5 。所以我们返回 5 。
 */
public class Solution_2719 {
    public int count(String num1, String num2, int minSum, int maxSum) {
        int n = num2.length();
        num1 = "0".repeat(n - num1.length()) + num1; // 补前导零，和 num2 对齐

        int[][] memo = new int[n][Math.min(9 * n, maxSum) + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return dfs(0, 0, true, true, num1.toCharArray(), num2.toCharArray(), minSum, maxSum, memo);
    }

    private int dfs(int i, int sum, boolean limitLow, boolean limitHigh, char[] num1, char[] num2, int minSum, int maxSum, int[][] memo) {
        if (sum > maxSum) { // 非法
            return 0;
        }
        if (i == num2.length) {
            return sum >= minSum ? 1 : 0;
        }
        if (!limitLow && !limitHigh && memo[i][sum] != -1) {
            return memo[i][sum];
        }

        int lo = limitLow ? num1[i] - '0' : 0;
        int hi = limitHigh ? num2[i] - '0' : 9;

        int res = 0;
        for (int d = lo; d <= hi; d++) { // 枚举当前数位填 d
            res = (res + dfs(i + 1, sum + d, limitLow && d == lo, limitHigh && d == hi,
                    num1, num2, minSum, maxSum, memo)) % 1_000_000_007;
        }

        if (!limitLow && !limitHigh) {
            memo[i][sum] = res;
        }
        return res;
    }
}

/*
class Solution {
    private static final int MOD = 1_000_000_007;

    public int count(String num1, String num2, int minSum, int maxSum) {
        int ans = calc(num2, minSum, maxSum) - calc(num1, minSum, maxSum) + MOD; // 避免负数
        int sum = 0;
        for (char c : num1.toCharArray()) {
            sum += c - '0';
        }
        if (minSum <= sum && sum <= maxSum) {
            ans++; // num1 是合法的，补回来
        }
        return ans % MOD;
    }

    private int calc(String s, int minSum, int maxSum) {
        int n = s.length();
        int[][] memo = new int[n][Math.min(9 * n, maxSum) + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dfs(0, 0, true, s.toCharArray(), minSum, maxSum, memo);
    }

    private int dfs(int i, int sum, boolean isLimit, char[] s, int minSum, int maxSum, int[][] memo) {
        if (sum > maxSum) { // 非法
            return 0;
        }
        if (i == s.length) {
            return sum >= minSum ? 1 : 0;
        }
        if (!isLimit && memo[i][sum] != -1) {
            return memo[i][sum];
        }

        int up = isLimit ? s[i] - '0' : 9;
        int res = 0;
        for (int d = 0; d <= up; d++) { // 枚举当前数位填 d
            res = (res + dfs(i + 1, sum + d, isLimit && (d == up), s, minSum, maxSum, memo)) % MOD;
        }

        if (!isLimit) {
            memo[i][sum] = res;
        }
        return res;
    }
}
*/
