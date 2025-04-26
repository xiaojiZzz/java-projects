package leetcode.difficulty;

import java.util.ArrayList;
import java.util.List;

/**
 * 统计理想数组的数目
 * 给你两个整数 n 和 maxValue ，用于描述一个 理想数组 。
 * 对于下标从 0 开始、长度为 n 的整数数组 arr ，如果满足以下条件，则认为该数组是一个 理想数组 ：
 * 每个 arr[i] 都是从 1 到 maxValue 范围内的一个值，其中 0 <= i < n 。
 * 每个 arr[i] 都可以被 arr[i - 1] 整除，其中 0 < i < n 。
 * 返回长度为 n 的 不同 理想数组的数目。由于答案可能很大，返回对 109 + 7 取余的结果。
 * 示例 1：
 * 输入：n = 2, maxValue = 5
 * 输出：10
 * 解释：存在以下理想数组：
 * - 以 1 开头的数组（5 个）：[1,1]、[1,2]、[1,3]、[1,4]、[1,5]
 * - 以 2 开头的数组（2 个）：[2,2]、[2,4]
 * - 以 3 开头的数组（1 个）：[3,3]
 * - 以 4 开头的数组（1 个）：[4,4]
 * - 以 5 开头的数组（1 个）：[5,5]
 * 共计 5 + 2 + 1 + 1 + 1 = 10 个不同理想数组。
 * 示例 2：
 * 输入：n = 5, maxValue = 3
 * 输出：11
 * 解释：存在以下理想数组：
 * - 以 1 开头的数组（9 个）：
 * - 不含其他不同值（1 个）：[1,1,1,1,1]
 * - 含一个不同值 2（4 个）：[1,1,1,1,2], [1,1,1,2,2], [1,1,2,2,2], [1,2,2,2,2]
 * - 含一个不同值 3（4 个）：[1,1,1,1,3], [1,1,1,3,3], [1,1,3,3,3], [1,3,3,3,3]
 * - 以 2 开头的数组（1 个）：[2,2,2,2,2]
 * - 以 3 开头的数组（1 个）：[3,3,3,3,3]
 * 共计 9 + 1 + 1 = 11 个不同理想数组。
 * 提示：
 * 2 <= n <= 104
 * 1 <= maxValue <= 104
 */
public class Solution_2338 {
    private static final int MOD = 1_000_000_007;
    private static final int MAX_N = 10_000;
    private static final int MAX_E = 13;

    private static final List<Integer>[] EXP = new ArrayList[MAX_N + 1];
    private static final int[][] C = new int[MAX_N + MAX_E][MAX_E + 1];

    private static boolean done = false;

    private void init() {
        // 这样写比 static block 更快
        if (done) {
            return;
        }
        done = true;

        // EXP[x] 为 x 分解质因数后，每个质因数的指数
        for (int x = 1; x < EXP.length; x++) {
            EXP[x] = new ArrayList<>();
            int t = x;
            for (int i = 2; i * i <= t; i++) {
                int e = 0;
                for (; t % i == 0; t /= i) {
                    e++;
                }
                if (e > 0) {
                    EXP[x].add(e);
                }
            }
            if (t > 1) {
                EXP[x].add(1);
            }
        }

        // 预处理组合数
        for (int i = 0; i < MAX_N + MAX_E; i++) {
            C[i][0] = 1;
            for (int j = 1; j <= Math.min(i, MAX_E); j++) {
                C[i][j] = (C[i - 1][j] + C[i - 1][j - 1]) % MOD;
            }
        }
    }

    public int idealArrays(int n, int maxValue) {
        init();
        long ans = 0;
        for (int x = 1; x <= maxValue; x++) {
            long mul = 1;
            for (int e : EXP[x]) {
                mul = mul * C[n + e - 1][e] % MOD;
            }
            ans += mul;
        }
        return (int) (ans % MOD);
    }
}
