package leetcode.medium;

import java.util.Arrays;

/**
 * 给你一个整数 k 和一个整数 x 。整数 num 的价值是它的二进制表示中在 x，2x，3x 等位置处设置位的数目（从最低有效位开始）。
 * 下面的表格包含了如何计算价值的例子。
 * x	num	Binary Representation	Price
 * 1	13	000001101	3
 * 2	13	000001101	1
 * 2	233	011101001	3
 * 3	13	000001101	1
 * 3	362	101101010	2
 * num 的 累加价值 是从 1 到 num 的数字的 总 价值。如果 num 的累加价值小于或等于 k 则被认为是 廉价 的。
 * 请你返回 最大 的廉价数字。
 * 示例 1：
 * 输入：k = 9, x = 1
 * 输出：6
 * 解释：由下表所示，6 是最大的廉价数字。
 * x	num	Binary Representation	Price	Accumulated Price
 * 1	1	001	1	1
 * 1	2	010	1	2
 * 1	3	011	2	4
 * 1	4	100	1	5
 * 1	5	101	2	7
 * 1	6	110	2	9
 * 1	7	111	3	12
 * 示例 2：
 * 输入：k = 7, x = 2
 * 输出：9
 * 解释：由下表所示，9 是最大的廉价数字。
 * x	num	Binary Representation	Price	Accumulated Price
 * 2	1	0001	0	0
 * 2	2	0010	1	1
 * 2	3	0011	1	2
 * 2	4	0100	0	2
 * 2	5	0101	0	2
 * 2	6	0110	1	3
 * 2	7	0111	1	4
 * 2	8	1000	1	5
 * 2	9	1001	1	6
 * 2	10	1010	2	8
 * 提示：
 * 1 <= k <= 10^15
 * 1 <= x <= 8
 */
public class Solution_3007 {

    private int x;

    public long findMaximumNumber(long k, int x) {
        this.x = x;
        long left = 0, right = (k + 1) << x;
        while (left < right) {
            long mid = left + (right - left) / 2;
            if (countDigitOne(mid) < k + 1) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left - 1;
    }

    private long countDigitOne(long n) {
        int len = 64 - Long.numberOfLeadingZeros(n);
        long[][] memo = new long[len][len];
        for (int i = 0; i < len; i++) {
            Arrays.fill(memo[i], -1);
        }
        return dfs(0, 0, true, n, memo, len);
    }

    // dfs 表示在前 i 位有 cnt 个 1 的前提下，我们能构造出的数中的 1 的个数总和
    private long dfs(int i, int cnt, boolean isLimited, long num, long[][] memo, int len) {
        if (i == len) {
            return cnt;
        }
        if (!isLimited && memo[i][cnt] != -1) {
            return memo[i][cnt];
        }
        long res = 0;
        int up = isLimited ? (int) (num >> (len - 1 - i) & 1) : 1;
        for (int j = 0; j <= up; j++) {
            res += dfs(i + 1, j == 1 && (len - i) % x == 0 ? cnt + 1 : cnt, isLimited && j == up, num, memo, len);
        }
        if (!isLimited) {
            memo[i][cnt] = res;
        }
        return res;
    }
}
