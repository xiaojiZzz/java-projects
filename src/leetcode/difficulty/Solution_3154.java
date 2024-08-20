package leetcode.difficulty;

import java.util.HashMap;
import java.util.Map;

/**
 * 到达第 K 级台阶的方案数
 * 给你有一个 非负 整数 k 。有一个无限长度的台阶，最低 一层编号为 0 。
 * Alice 有一个整数 jump ，一开始值为 0 。Alice 从台阶 1 开始，可以使用 任意 次操作，目标是到达第 k 级台阶。
 * 假设 Alice 位于台阶 i ，一次 操作 中，Alice 可以：
 * 向下走一级到 i - 1 ，但该操作 不能 连续使用，如果在台阶第 0 级也不能使用。
 * 向上走到台阶 i + 2jump 处，然后 jump 变为 jump + 1 。
 * 请你返回 Alice 到达台阶 k 处的总方案数。
 * 注意，Alice 可能到达台阶 k 处后，通过一些操作重新回到台阶 k 处，这视为不同的方案。
 * 示例 1：
 * 输入：k = 0
 * 输出：2
 * 解释：
 * 2 种到达台阶 0 的方案为：
 * Alice 从台阶 1 开始。
 * 执行第一种操作，从台阶 1 向下走到台阶 0 。
 * Alice 从台阶 1 开始。
 * 执行第一种操作，从台阶 1 向下走到台阶 0 。
 * 执行第二种操作，向上走 20 级台阶到台阶 1 。
 * 执行第一种操作，从台阶 1 向下走到台阶 0 。
 * 示例 2：
 * 输入：k = 1
 * 输出：4
 * 解释：
 * 4 种到达台阶 1 的方案为：
 * Alice 从台阶 1 开始，已经到达台阶 1 。
 * Alice 从台阶 1 开始。
 * 执行第一种操作，从台阶 1 向下走到台阶 0 。
 * 执行第二种操作，向上走 20 级台阶到台阶 1 。
 * Alice 从台阶 1 开始。
 * 执行第二种操作，向上走 20 级台阶到台阶 2 。
 * 执行第一种操作，向下走 1 级台阶到台阶 1 。
 * Alice 从台阶 1 开始。
 * 执行第一种操作，从台阶 1 向下走到台阶 0 。
 * 执行第二种操作，向上走 20 级台阶到台阶 1 。
 * 执行第一种操作，向下走 1 级台阶到台阶 0 。
 * 执行第二种操作，向上走 21 级台阶到台阶 2 。
 * 执行第一种操作，向下走 1 级台阶到台阶 1 。
 * 提示：
 * 0 <= k <= 109
 */
public class Solution_3154 {
    public int waysToReachStair(int k) {
        Map<Long, Integer> memo = new HashMap<>();
        return dfs(1, 0, 0, k, memo);
    }

    private int dfs(int i, int j, int f, int k, Map<Long, Integer> memo) {
        if (i > k + 1) {
            return 0;
        }
        long key = (long) i << 32 | (long) j << 5 | f;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        int res = i == k ? 1 : 0;
        res += dfs(i + (1 << j), j + 1, 0, k, memo);
        if (i > 0 && f == 0) {
            res += dfs(i - 1, j, 1, k, memo);
        }
        memo.put(key, res);
        return res;
    }
}

/*
class Solution {
    private static final int MX = 31;
    private static final int[][] c = new int[MX][MX];

    static {
        for (int i = 0; i < MX; i++) {
            c[i][0] = c[i][i] = 1;
            for (int j = 1; j < i; j++) {
                c[i][j] = c[i - 1][j - 1] + c[i - 1][j];
            }
        }
    }

    public int waysToReachStair(int k) {
        int ans = 0;
        for (int j = 0; j < 30; j++) {
            int m = (1 << j) - k;
            if (0 <= m && m <= j + 1) {
                ans += c[j + 1][m];
            }
        }
        return ans;
    }
}
*/

/*
class Solution {
    private static final int MX = 31;
    private static final int[][] c = new int[MX][MX];

    static {
        for (int i = 0; i < MX; i++) {
            c[i][0] = c[i][i] = 1;
            for (int j = 1; j < i; j++) {
                c[i][j] = c[i - 1][j - 1] + c[i - 1][j];
            }
        }
    }

    // 上面的优化
    public int waysToReachStair(int k) {
        int ans = 0;
        for (int j = 32 - Integer.numberOfLeadingZeros(Math.max(k - 1, 0)); (1 << j) - k <= j + 1; j++) {
            ans += c[j + 1][(1 << j) - k];
        }
        return ans;
    }
}
*/