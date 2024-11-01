package leetcode.difficulty;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 给你一个整数数组 rewardValues，长度为 n，代表奖励的值。
 * 最初，你的总奖励 x 为 0，所有下标都是 未标记 的。你可以执行以下操作 任意次 ：
 * 从区间 [0, n - 1] 中选择一个 未标记 的下标 i。
 * 如果 rewardValues[i] 大于 你当前的总奖励 x，则将 rewardValues[i] 加到 x 上（即 x = x + rewardValues[i]），并 标记 下标 i。
 * 以整数形式返回执行最优操作能够获得的 最大 总奖励。
 * 示例 1：
 * 输入：rewardValues = [1,1,3,3]
 * 输出：4
 * 解释：
 * 依次标记下标 0 和 2，总奖励为 4，这是可获得的最大值。
 * 示例 2：
 * 输入：rewardValues = [1,6,4,3,2]
 * 输出：11
 * 解释：
 * 依次标记下标 0、2 和 1。总奖励为 11，这是可获得的最大值。
 * 提示：
 * 1 <= rewardValues.length <= 5 * 104
 * 1 <= rewardValues[i] <= 5 * 104
 */
public class Solution_3181 {
    public int maxTotalReward(int[] rewardValues) {
        int m = 0;
        for (int v : rewardValues) {
            m = Math.max(m, v);
        }

        // 优化
        Set<Integer> set = new HashSet<>();
        for (int v : rewardValues) {
            if (v == m - 1) {
                return m * 2 - 1;
            }
            if (set.contains(v)) {
                continue;
            }
            if (set.contains(m - 1 - v)) {
                return m * 2 - 1;
            }
            set.add(v);
        }

        Arrays.sort(rewardValues);
        int pre = 0;
        BigInteger f = BigInteger.ONE;
        for (int v : rewardValues) {
            if (v == pre) {
                continue;
            }
            BigInteger mask = BigInteger.ONE.shiftLeft(v).subtract(BigInteger.ONE);
            f = f.or(f.and(mask).shiftLeft(v));
            pre = v;
        }
        return f.bitLength() - 1;
    }
}
