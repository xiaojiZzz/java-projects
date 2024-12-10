package leetcode.medium;

/**
 * 骑士拨号器
 * 象棋骑士有一个独特的移动方式，它可以垂直移动两个方格，水平移动一个方格，或者水平移动两个方格，垂直移动一个方格(两者都形成一个 L 的形状)。
 * 象棋骑士可能的移动方式如下图所示:
 * 我们有一个象棋骑士和一个电话垫，如下所示，骑士只能站在一个数字单元格上(即蓝色单元格)。
 * 给定一个整数 n，返回我们可以拨多少个长度为 n 的不同电话号码。
 * 你可以将骑士放置在任何数字单元格上，然后你应该执行 n - 1 次移动来获得长度为 n 的号码。所有的跳跃应该是有效的骑士跳跃。
 * 因为答案可能很大，所以输出答案模 109 + 7.
 * 示例 1：
 * 输入：n = 1
 * 输出：10
 * 解释：我们需要拨一个长度为1的数字，所以把骑士放在10个单元格中的任何一个数字单元格上都能满足条件。
 * 示例 2：
 * 输入：n = 2
 * 输出：20
 * 解释：我们可以拨打的所有有效号码为[04, 06, 16, 18, 27, 29, 34, 38, 40, 43, 49, 60, 61, 67, 72, 76, 81, 83, 92, 94]
 * 示例 3：
 * 输入：n = 3131
 * 输出：136006598
 * 解释：注意取模
 * 提示：
 * 1 <= n <= 5000
 */
public class Solution_935 {
    public int knightDialer(int n) {
        int[][] cnt = new int[][]{{4, 6}, {6, 8}, {7, 9}, {4, 8}, {0, 3, 9}, {}, {0, 1, 7}, {2, 6}, {1, 3}, {2, 4}};
        int[][] memo = new int[10][n];
        int mod = 1000000007;
        int res = 0;
        for (int i = 0; i < 10; i++) {
            res = (res + dfs(1, n, i, cnt, memo, mod)) % mod;
        }
        return res;
    }

    private int dfs(int cur, int n, int idx, int[][] cnt, int[][] memo, int mod) {
        if (cur == n) {
            return 1;
        }
        if (memo[idx][cur] > 0) {
            return memo[idx][cur];
        }
        int res = 0;
        for (int num : cnt[idx]) {
            res = (res + dfs(cur + 1, n, num, cnt, memo, mod)) % mod;
        }
        return memo[idx][cur] = res;
    }
}

/*
class Solution {
    public int knightDialer(int n) {
        int[][] cnt = new int[][]{{4, 6}, {6, 8}, {7, 9}, {4, 8}, {0, 3, 9}, {}, {0, 1, 7}, {2, 6}, {1, 3}, {2, 4}};
        int mod = 1000000007;
        int[][] dp = new int[10][n + 1];
        for (int i = 0; i < 10; i++) {
            dp[i][1] = 1;
        }
        for (int step = 2; step <= n; step++) {
            for (int i = 0; i < 10; i++) {
                for (int num : cnt[i]) {
                    dp[i][step] = (dp[i][step] + dp[num][step - 1]) % mod;
                }
            }
        }
        int res = 0;
        for (int i = 0; i < 10; i++) {
            res = (res + dp[i][n]) % mod;
        }
        return res;
    }
}
*/
