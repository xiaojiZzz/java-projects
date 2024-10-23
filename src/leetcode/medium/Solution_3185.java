package leetcode.medium;

/**
 * 构成整天的下标对数目 II
 * 给你一个整数数组 hours，表示以 小时 为单位的时间，返回一个整数，表示满足 i < j 且 hours[i] + hours[j] 构成整天的下标对 i, j 的数目。
 * 整天 定义为时间持续时间是 24 小时的 整数倍 。
 * 例如，1 天是 24 小时，2 天是 48 小时，3 天是 72 小时，以此类推。
 * 示例 1：
 * 输入： hours = [12,12,30,24,24]
 * 输出： 2
 * 解释：
 * 构成整天的下标对分别是 (0, 1) 和 (3, 4)。
 * 示例 2：
 * 输入： hours = [72,48,24,3]
 * 输出： 3
 * 解释：
 * 构成整天的下标对分别是 (0, 1)、(0, 2) 和 (1, 2)。
 * 提示：
 * 1 <= hours.length <= 5 * 105
 * 1 <= hours[i] <= 109
 */
public class Solution_3185 {
    public long countCompleteDayPairs(int[] hours) {
        int n = hours.length;
        long ans = 0;
        long[] cnt = new long[24];
        for (int hour : hours) {
            ans += cnt[(24 - hour % 24) % 24];
            cnt[hour % 24]++;
        }
        return ans;
    }
}

/*
class Solution {
    public long countCompleteDayPairs(int[] hours) {
        // 哈希计数
        long[] cnt = new long[24];
        for (int h : hours) {  // 哈希统计
            cnt[h % 24]++;
        }
        long ans = 0;
        // 让 1 到 11 与对应数字进行配对累加
        for (int i = 1; i < 12; i++) {
            ans += cnt[i] * cnt[24 - i];
        }
        // 对 0 和 12 单独处理，因为它们是和自身结合
        ans += cnt[0] * (cnt[0] - 1) / 2;
        ans += cnt[12] * (cnt[12] - 1) / 2;
        return ans;
    }
}
*/
