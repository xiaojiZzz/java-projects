package leetcode.difficulty;

/**
 * 大数组元素的乘积
 * 一个非负整数 x 的 强数组 指的是满足元素为 2 的幂且元素总和为 x 的最短有序数组。
 * 下表说明了如何确定 强数组 的示例。可以证明，x 对应的强数组是独一无二的。
 * 数字	二进制表示	强数组
 * 1	00001	[1]
 * 8	01000	[8]
 * 10	01010	[2, 8]
 * 13	01101	[1, 4, 8]
 * 23	10111	[1, 2, 4, 16]
 * 我们将每一个升序的正整数 i （即1，2，3等等）的 强数组 连接得到数组 big_nums ，
 * big_nums 开始部分为 [1, 2, 1, 2, 4, 1, 4, 2, 4, 1, 2, 4, 8, ...] 。
 * 给你一个二维整数数组 queries ，其中 queries[i] = [fromi, toi, modi] ，
 * 你需要计算 (big_nums[fromi] * big_nums[fromi + 1] * ... * big_nums[toi]) % modi 。
 * 请你返回一个整数数组 answer ，其中 answer[i] 是第 i 个查询的答案。
 * 示例 1：
 * 输入：queries = [[1,3,7]]
 * 输出：[4]
 * 解释：
 * 只有一个查询。
 * big_nums[1..3] = [2,1,2] 。它们的乘积为 4。结果为 4 % 7 = 4。
 * 示例 2：
 * 输入：queries = [[2,5,3],[7,7,4]]
 * 输出：[2,2]
 * 解释：
 * 有两个查询。
 * 第一个查询：big_nums[2..5] = [1,2,4,1] 。它们的乘积为 8 。结果为  8 % 3 = 2。
 * 第二个查询：big_nums[7] = 2 。结果为 2 % 4 = 2。
 * 提示：
 * 1 <= queries.length <= 500
 * queries[i].length == 3
 * 0 <= queries[i][0] <= queries[i][1] <= 1015
 * 1 <= queries[i][2] <= 105
 */
public class Solution_3145 {
    public int[] findProductsOfElements(long[][] queries) {
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            long[] q = queries[i];
            long er = sumE(q[1] + 1);
            long el = sumE(q[0]);
            ans[i] = pow(2, er - el, q[2]);
        }
        return ans;
    }

    private long sumE(long k) {
        long res = 0;
        long n = 0;
        long cnt1 = 0; // 之前填的 1 的个数
        long sumI = 0; // 之前填的 1 的幂次之和
        for (long i = 63 - Long.numberOfLeadingZeros(k + 1); i > 0; i--) {
            long c = (cnt1 << i) + (i << (i - 1)); // 新增的幂次个数
            if (c <= k) {
                k -= c;
                res += (sumI << i) + ((i * (i - 1) / 2) << (i - 1));
                sumI += i;
                cnt1++;
                n |= 1L << i; // 填 1
            }
        }
        // 最低位单独计算
        if (cnt1 <= k) {
            k -= cnt1;
            res += sumI;
            n |= 1; // 最低位填 1
        }
        // 剩余的 k 个幂次，由 n 的低 k 个 1 补充
        while (k-- > 0) {
            res += Long.numberOfTrailingZeros(n);
            n &= n - 1; // 去掉最低位的 1（置为 0）
        }
        return res;
    }

    private int pow(long x, long n, long mod) {
        long res = 1 % mod;
        for (; n > 0; n /= 2) {
            if (n % 2 == 1) {
                res = res * x % mod;
            }
            x = x * x % mod;
        }
        return (int) res;
    }
}
