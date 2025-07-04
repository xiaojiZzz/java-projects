package leetcode.medium;

import java.util.Arrays;

/**
 * 统计最大元素出现至少 K 次的子数组
 * 给你一个整数数组 nums 和一个 正整数 k 。
 * 请你统计有多少满足 「 nums 中的 最大 元素」至少出现 k 次的子数组，并返回满足这一条件的子数组的数目。
 * 子数组是数组中的一个连续元素序列。
 * 示例 1：
 * 输入：nums = [1,3,2,3,3], k = 2
 * 输出：6
 * 解释：包含元素 3 至少 2 次的子数组为：[1,3,2,3]、[1,3,2,3,3]、[3,2,3]、[3,2,3,3]、[2,3,3] 和 [3,3] 。
 * 示例 2：
 * 输入：nums = [1,4,2,1], k = 3
 * 输出：0
 * 解释：没有子数组包含元素 4 至少 3 次。
 * 提示：
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 106
 * 1 <= k <= 105给你一个整数数组 nums 和一个 正整数 k 。
 * 请你统计有多少满足 「 nums 中的 最大 元素」至少出现 k 次的子数组，并返回满足这一条件的子数组的数目。
 * 子数组是数组中的一个连续元素序列。
 * 示例 1：
 * 输入：nums = [1,3,2,3,3], k = 2
 * 输出：6
 * 解释：包含元素 3 至少 2 次的子数组为：[1,3,2,3]、[1,3,2,3,3]、[3,2,3]、[3,2,3,3]、[2,3,3] 和 [3,3] 。
 * 示例 2：
 * 输入：nums = [1,4,2,1], k = 3
 * 输出：0
 * 解释：没有子数组包含元素 4 至少 3 次。
 * 提示：
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 106
 * 1 <= k <= 105
 */
public class Solution_2962 {
    public long countSubarrays(int[] nums, int k) {
        int max = Arrays.stream(nums).max().getAsInt();
        int left = 0, cnt = 0;
        long ans = 0;
        for (int num : nums) {
            if (num == max) {
                cnt++;
            }
            while (cnt >= k) {
                if (nums[left] == max) {
                    cnt--;
                }
                left++;
            }
            ans += left;
        }
        return ans;
    }
}
