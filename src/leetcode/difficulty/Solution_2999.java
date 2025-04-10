package leetcode.difficulty;

import java.util.Arrays;

/**
 * 统计强大整数的数目
 * 给你三个整数 start ，finish 和 limit 。同时给你一个下标从 0 开始的字符串 s ，表示一个 正 整数。
 * 如果一个 正 整数 x 末尾部分是 s （换句话说，s 是 x 的 后缀），且 x 中的每个数位至多是 limit ，那么我们称 x 是 强大的 。
 * 请你返回区间 [start..finish] 内强大整数的 总数目 。
 * 如果一个字符串 x 是 y 中某个下标开始（包括 0 ），到下标为 y.length - 1 结束的子字符串，那么我们称 x 是 y 的一个后缀。
 * 比方说，25 是 5125 的一个后缀，但不是 512 的后缀。
 * 示例 1：
 * 输入：start = 1, finish = 6000, limit = 4, s = "124"
 * 输出：5
 * 解释：区间 [1..6000] 内的强大数字为 124 ，1124 ，2124 ，3124 和 4124 。这些整数的各个数位都 <= 4 且 "124" 是它们的后缀。
 * 注意 5124 不是强大整数，因为第一个数位 5 大于 4 。
 * 这个区间内总共只有这 5 个强大整数。
 * 示例 2：
 * 输入：start = 15, finish = 215, limit = 6, s = "10"
 * 输出：2
 * 解释：区间 [15..215] 内的强大整数为 110 和 210 。这些整数的各个数位都 <= 6 且 "10" 是它们的后缀。
 * 这个区间总共只有这 2 个强大整数。
 * 示例 3：
 * 输入：start = 1000, finish = 2000, limit = 4, s = "3000"
 * 输出：0
 * 解释：区间 [1000..2000] 内的整数都小于 3000 ，所以 "3000" 不可能是这个区间内任何整数的后缀。
 * 提示：
 * 1 <= start <= finish <= 1015
 * 1 <= limit <= 9
 * 1 <= s.length <= floor(log10(finish)) + 1
 * s 数位中每个数字都小于等于 limit 。
 * s 不包含任何前导 0 。
 */
public class Solution_2999 {
    public long numberOfPowerfulInt(long start, long finish, int limit, String s) {
        String low = Long.toString(start);
        String high = Long.toString(finish);
        int n = high.length();
        low = "0".repeat(n - low.length()) + low; // 补前导零，和 high 对齐
        long[] memo = new long[n];
        Arrays.fill(memo, -1);
        return dfs(0, true, true, low.toCharArray(), high.toCharArray(), limit, s.toCharArray(), memo);
    }

    private long dfs(int i, boolean limitLow, boolean limitHigh, char[] low, char[] high, int limit, char[] s, long[] memo) {
        if (i == high.length) {
            return 1;
        }

        if (!limitLow && !limitHigh && memo[i] != -1) {
            return memo[i]; // 之前计算过
        }

        // 第 i 个数位可以从 lo 枚举到 hi
        // 如果对数位还有其它约束，应当只在下面的 for 循环做限制，不应修改 lo 或 hi
        int lo = limitLow ? low[i] - '0' : 0;
        int hi = limitHigh ? high[i] - '0' : 9;

        long res = 0;
        if (i < high.length - s.length) { // 枚举这个数位填什么
            for (int d = lo; d <= Math.min(hi, limit); d++) {
                res += dfs(i + 1, limitLow && d == lo, limitHigh && d == hi, low, high, limit, s, memo);
            }
        } else { // 这个数位只能填 s[i-diff]
            int x = s[i - (high.length - s.length)] - '0';
            if (lo <= x && x <= Math.min(hi, limit)) {
                res = dfs(i + 1, limitLow && x == lo, limitHigh && x == hi, low, high, limit, s, memo);
            }
        }

        if (!limitLow && !limitHigh) {
            memo[i] = res; // 记忆化 (i,false,false)
        }
        return res;
    }
}
