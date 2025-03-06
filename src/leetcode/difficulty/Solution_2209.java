package leetcode.difficulty;

import java.util.Arrays;
import java.util.Map;

/**
 * 用地毯覆盖后的最少白色砖块
 * 给你一个下标从 0 开始的 二进制 字符串 floor ，它表示地板上砖块的颜色。
 * floor[i] = '0' 表示地板上第 i 块砖块的颜色是 黑色 。
 * floor[i] = '1' 表示地板上第 i 块砖块的颜色是 白色 。
 * 同时给你 numCarpets 和 carpetLen 。你有 numCarpets 条 黑色 的地毯，每一条 黑色 的地毯长度都为 carpetLen 块砖块。
 * 请你使用这些地毯去覆盖砖块，使得未被覆盖的剩余 白色 砖块的数目 最小 。地毯相互之间可以覆盖。
 * 请你返回没被覆盖的白色砖块的 最少 数目。
 * 示例 1：
 * 输入：floor = "10110101", numCarpets = 2, carpetLen = 2
 * 输出：2
 * 解释：
 * 上图展示了剩余 2 块白色砖块的方案。
 * 没有其他方案可以使未被覆盖的白色砖块少于 2 块。
 * 示例 2：
 * 输入：floor = "11111", numCarpets = 2, carpetLen = 3
 * 输出：0
 * 解释：
 * 上图展示了所有白色砖块都被覆盖的一种方案。
 * 注意，地毯相互之间可以覆盖。
 * 提示：
 * 1 <= carpetLen <= floor.length <= 1000
 * floor[i] 要么是 '0' ，要么是 '1' 。
 * 1 <= numCarpets <= 1000
 */
public class Solution_2209 {
    public int minimumWhiteTiles(String floor, int numCarpets, int carpetLen) {
        int n = floor.length();
        int[][] memo = new int[numCarpets + 1][n];
        for (int[] ints : memo) {
            Arrays.fill(ints, -1);
        }
        return dfs(numCarpets, n - 1, carpetLen, memo, floor.toCharArray());
    }

    private int dfs(int i, int j, int carpetLen, int[][] memo, char[] chars) {
        if (j < i * carpetLen) {
            return 0;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        int res = dfs(i, j - 1, carpetLen, memo, chars) + chars[j] - '0';
        if (i > 0) {
            res = Math.min(res, dfs(i - 1, j - carpetLen, carpetLen, memo, chars));
        }
        return memo[i][j] = res;
    }
}

/*
class Solution {
    public int minimumWhiteTiles(String floor, int numCarpets, int carpetLen) {
        int n = floor.length();
        int[][] dp = new int[numCarpets + 1][n];
        char[] chars = floor.toCharArray();
        dp[0][0] = chars[0] - '0';
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + chars[i] - '0';
        }
        for (int i = 1; i <= numCarpets; i++) {
            for (int j = i * carpetLen; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j - carpetLen], dp[i][j - 1] + chars[j] - '0');
            }
        }
        return dp[numCarpets][n - 1];
    }
}
*/

/*
class Solution {
    public int minimumWhiteTiles(String floor, int numCarpets, int carpetLen) {
        int m = floor.length();
        if (numCarpets * carpetLen >= m) {
            return 0;
        }

        char[] s = floor.toCharArray();
        int[] f = new int[m];
        f[0] = s[0] - '0';
        for (int j = 1; j < m; j++) {
            f[j] = f[j - 1] + (s[j] - '0');
        }
        for (int i = 1; i <= numCarpets; i++) {
            int[] nf = new int[m];
            for (int j = carpetLen * i; j < m; j++) {
                nf[j] = Math.min(nf[j - 1] + (s[j] - '0'), f[j - carpetLen]);
            }
            f = nf;
        }
        return f[m - 1];
    }
}
*/
