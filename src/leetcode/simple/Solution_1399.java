package leetcode.simple;

/**
 * 统计最大组的数目
 * 给你一个整数 n 。请你先求出从 1 到 n 的每个整数 10 进制表示下的数位和（每一位上的数字相加），然后把数位和相等的数字放到同一个组中。
 * 请你统计每个组中的数字数目，并返回数字数目并列最多的组有多少个。
 * 示例 1：
 * 输入：n = 13
 * 输出：4
 * 解释：总共有 9 个组，将 1 到 13 按数位求和后这些组分别是：
 * [1,10]，[2,11]，[3,12]，[4,13]，[5]，[6]，[7]，[8]，[9]。总共有 4 个组拥有的数字并列最多。
 * 示例 2：
 * 输入：n = 2
 * 输出：2
 * 解释：总共有 2 个大小为 1 的组 [1]，[2]。
 * 示例 3：
 * 输入：n = 15
 * 输出：6
 * 示例 4：
 * 输入：n = 24
 * 输出：5
 * 提示：
 * 1 <= n <= 10^4
 */
public class Solution_1399 {
    public int countLargestGroup(int n) {
        int m = String.valueOf(n).length();
        int[] cnt = new int[m * 9 + 1]; // 数位和 <= 9m
        int maxCnt = 0;
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            int ds = calcDigitSum(i);
            cnt[ds]++;
            // 维护 maxCnt 以及 maxCnt 的出现次数
            if (cnt[ds] > maxCnt) {
                maxCnt = cnt[ds];
                ans = 1;
            } else if (cnt[ds] == maxCnt) {
                ans++;
            }
        }
        return ans;
    }

    private int calcDigitSum(int num) {
        int ds = 0;
        while (num > 0) {
            ds += num % 10;
            num /= 10;
        }
        return ds;
    }
}

/*
class Solution {
    public int countLargestGroup(int n) {
        char[] s = String.valueOf(n).toCharArray();
        int m = s.length;
        int[][] memo = new int[m][m * 9 + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        int maxCnt = 0;
        int ans = 0;
        for (int target = 1; target <= m * 9; target++) { // 枚举目标数位和
            int cnt = dfs(0, target, true, s, memo);
            if (cnt > maxCnt) {
                maxCnt = cnt;
                ans = 1;
            } else if (cnt == maxCnt) {
                ans++;
            }
        }
        return ans;
    }

    private int dfs(int i, int left, boolean limitHigh, char[] s, int[][] memo) {
        if (i == s.length) {
            return left == 0 ? 1 : 0;
        }
        if (!limitHigh && memo[i][left] != -1) {
            return memo[i][left];
        }

        int hi = limitHigh ? s[i] - '0' : 9; // 当前数位至多填 hi
        int res = 0;
        for (int d = 0; d <= Math.min(hi, left); d++) { // 枚举当前数位填 d
            res += dfs(i + 1, left - d, limitHigh && d == hi, s, memo);
        }

        if (!limitHigh) {
            memo[i][left] = res;
        }
        return res;
    }
}
*/