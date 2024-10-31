package leetcode.medium;

import java.util.Arrays;

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
 * 1 <= rewardValues.length <= 2000
 * 1 <= rewardValues[i] <= 2000
 */
public class Solution_3180 {
    public int maxTotalReward(int[] rewardValues) {
        int[] nums = Arrays.stream(rewardValues).distinct().sorted().toArray();
        int n = nums.length;
        int max = nums[n - 1] << 1;
        boolean[][] dp = new boolean[n + 1][max];
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            int x = nums[i - 1];
            for (int j = 0; j <= 2 * x - 1; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= x) {
                    dp[i][j] |= dp[i - 1][j - x];
                }
            }
        }
        int ans = max - 1;
        while (!dp[n][ans]) {
            ans--;
        }
        return ans;
    }
}

/*
class Solution {
    // 优化空间复杂度
    public int maxTotalReward(int[] rewardValues) {
        int[] nums = Arrays.stream(rewardValues).distinct().sorted().toArray();
        int n = nums.length;
        int max = nums[n - 1] << 1;
        boolean[] dp = new boolean[max];
        dp[0] = true;
        for (int num : nums) {
            for (int j = 2 * num - 1; j >= num; j--) {
                dp[j] |= dp[j - num];
            }
        }
        int ans = max - 1;
        while (!dp[ans]) {
            ans--;
        }
        return ans;
    }
}
*/
