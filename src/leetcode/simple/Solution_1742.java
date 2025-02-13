package leetcode.simple;

/**
 * 你在一家生产小球的玩具厂工作，有 n 个小球，编号从 lowLimit 开始，到 highLimit 结束（包括 lowLimit 和 highLimit ，
 * 即 n == highLimit - lowLimit + 1）。另有无限数量的盒子，编号从 1 到 infinity 。
 * 你的工作是将每个小球放入盒子中，其中盒子的编号应当等于小球编号上每位数字的和。
 * 例如，编号 321 的小球应当放入编号 3 + 2 + 1 = 6 的盒子，而编号 10 的小球应当放入编号 1 + 0 = 1 的盒子。
 * 给你两个整数 lowLimit 和 highLimit ，返回放有最多小球的盒子中的小球数量。
 * 如果有多个盒子都满足放有最多小球，只需返回其中任一盒子的小球数量。
 * 示例 1：
 * 输入：lowLimit = 1, highLimit = 10
 * 输出：2
 * 解释：
 * 盒子编号：1 2 3 4 5 6 7 8 9 10 11 ...
 * 小球数量：2 1 1 1 1 1 1 1 1 0  0  ...
 * 编号 1 的盒子放有最多小球，小球数量为 2 。
 * 示例 2：
 * 输入：lowLimit = 5, highLimit = 15
 * 输出：2
 * 解释：
 * 盒子编号：1 2 3 4 5 6 7 8 9 10 11 ...
 * 小球数量：1 1 1 1 2 2 1 1 1 0  0  ...
 * 编号 5 和 6 的盒子放有最多小球，每个盒子中的小球数量都是 2 。
 * 示例 3：
 * 输入：lowLimit = 19, highLimit = 28
 * 输出：2
 * 解释：
 * 盒子编号：1 2 3 4 5 6 7 8 9 10 11 12 ...
 * 小球数量：0 1 1 1 1 1 1 1 1 2  0  0  ...
 * 编号 10 的盒子放有最多小球，小球数量为 2 。
 * 提示：
 * 1 <= lowLimit <= highLimit <= 105
 */
public class Solution_1742 {
    public int countBalls(int lowLimit, int highLimit) {
        int ans = 0;
        int[] cnt = new int[46]; // 99999 的数位和 = 45
        for (int i = lowLimit; i <= highLimit; i++) {
            int s = 0;
            for (int x = i; x > 0; x /= 10) {
                s += x % 10;
            }
            cnt[s]++;
            ans = Math.max(ans, cnt[s]);
        }
        return ans;
    }
}

/*
class Solution {
    private final static int[][] s = new int[100_001][46];

    static {
        for (int i = 1; i < s.length; i++) {
            System.arraycopy(s[i - 1], 0, s[i], 0, s[i].length);
            int sum = 0;
            for (int x = i; x > 0; x /= 10) {
                sum += x % 10;
            }
            s[i][sum]++;
        }
    }

    public int countBalls(int lowLimit, int highLimit) {
        int ans = 0;
        for (int j = 1; j < s[0].length; j++) {
            ans = Math.max(ans, s[highLimit][j] - s[lowLimit - 1][j]);
        }
        return ans;
    }
}
*/

/*
class Solution {
    public int countBalls(int lowLimit, int highLimit) {
        String highS = String.valueOf(highLimit);
        int n = highS.length();
        String lowS = String.valueOf(lowLimit);
        lowS = "0".repeat(n - lowS.length()) + lowS; // 补前导零，和 highS 对齐

        int m = highS.charAt(0) - '0' + (n - 1) * 9; // 数位和的上界
        int[][] memo = new int[n][m + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1); // -1 表示没有计算过
        }

        int ans = 0;
        for (int j = 1; j <= m; j++) {
            ans = Math.max(ans, dfs(0, j, true, true, lowS.toCharArray(), highS.toCharArray(), memo));
        }
        return ans;
    }

    private int dfs(int i, int j, boolean limitLow, boolean limitHigh, char[] lowS, char[] highS, int[][] memo) {
        if (i == highS.length) { // 填完了
            return j == 0 ? 1 : 0;
        }
        if (!limitLow && !limitHigh && memo[i][j] != -1) { // 之前计算过
            return memo[i][j];
        }

        int lo = limitLow ? lowS[i] - '0' : 0;
        int hi = limitHigh ? highS[i] - '0' : 9;

        int res = 0;
        for (int d = lo; d <= Math.min(hi, j); d++) { // 枚举当前数位填 d，但不能超过 j
            res += dfs(i + 1, j - d, limitLow && d == lo, limitHigh && d == hi, lowS, highS, memo);
        }

        if (!limitLow && !limitHigh) {
            memo[i][j] = res; // 记忆化
        }
        return res;
    }
}
*/