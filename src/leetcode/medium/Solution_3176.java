package leetcode.medium;

/**
 * 给你一个整数数组 nums 和一个 非负 整数 k 。如果一个整数序列 seq 满足在范围下标范围 [0, seq.length - 2] 中
 * 存在 不超过 k 个下标 i 满足 seq[i] != seq[i + 1] ，那么我们称这个整数序列为 好 序列。
 * 请你返回 nums 中 好子序列的最长长度
 * 示例 1：
 * 输入：nums = [1,2,1,1,3], k = 2
 * 输出：4
 * 解释：
 * 最长好子序列为 [1,2,1,1,3] 。
 * 示例 2：
 * 输入：nums = [1,2,3,4,5,1], k = 0
 * 输出：2
 * 解释：
 * 最长好子序列为 [1,2,3,4,5,1] 。
 * 提示：
 * 1 <= nums.length <= 500
 * 1 <= nums[i] <= 109
 * 0 <= k <= min(nums.length, 25)
 */
public class Solution_3176 {
    public int maximumLength(int[] nums, int k) {
        int n = nums.length;
        int[][] dp = new int[n][k + 1];
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                for (int p = 0; p < i; p++) {
                    if (nums[i] != nums[p]) {
                        if (j > 0) {
                            dp[i][j] = Math.max(dp[i][j], dp[p][j - 1] + 1);
                        }
                    } else {
                        dp[i][j] = Math.max(dp[i][j], dp[p][j] + 1);
                    }
                }
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }
}
