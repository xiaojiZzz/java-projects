package leetcode.difficulty;

import java.util.HashMap;
import java.util.Map;

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
 * 1 <= nums.length <= 5 * 103
 * 1 <= nums[i] <= 109
 * 0 <= k <= min(50, nums.length)
 */
public class Solution_3177 {
    public int maximumLength(int[] nums, int k) {
        Map<Integer, int[]> dp = new HashMap<>();
        int[] zd = new int[k + 1];
        for (int v : nums) {
            int[] tmp = dp.computeIfAbsent(v, i -> new int[k + 1]);
            for (int j = 0; j <= k; j++) {
                tmp[j] = tmp[j] + 1;
                if (j > 0) {
                    tmp[j] = Math.max(tmp[j], zd[j - 1] + 1);
                }
            }
            for (int j = 0; j <= k; j++) {
                zd[j] = Math.max(zd[j], tmp[j]);
            }
        }
        return zd[k];
    }
}
