package leetcode.medium;

import java.util.List;

/**
 * 统计趣味子数组的数目
 * 给你一个下标从 0 开始的整数数组 nums ，以及整数 modulo 和整数 k 。
 * 请你找出并统计数组中 趣味子数组 的数目。
 * 如果 子数组 nums[l..r] 满足下述条件，则称其为 趣味子数组 ：
 * 在范围 [l, r] 内，设 cnt 为满足 nums[i] % modulo == k 的索引 i 的数量。并且 cnt % modulo == k 。
 * 以整数形式表示并返回趣味子数组的数目。
 * 注意：子数组是数组中的一个连续非空的元素序列。
 * 示例 1：
 * 输入：nums = [3,2,4], modulo = 2, k = 1
 * 输出：3
 * 解释：在这个示例中，趣味子数组分别是：
 * 子数组 nums[0..0] ，也就是 [3] 。
 * - 在范围 [0, 0] 内，只存在 1 个下标 i = 0 满足 nums[i] % modulo == k 。
 * - 因此 cnt = 1 ，且 cnt % modulo == k 。
 * 子数组 nums[0..1] ，也就是 [3,2] 。
 * - 在范围 [0, 1] 内，只存在 1 个下标 i = 0 满足 nums[i] % modulo == k 。
 * - 因此 cnt = 1 ，且 cnt % modulo == k 。
 * 子数组 nums[0..2] ，也就是 [3,2,4] 。
 * - 在范围 [0, 2] 内，只存在 1 个下标 i = 0 满足 nums[i] % modulo == k 。
 * - 因此 cnt = 1 ，且 cnt % modulo == k 。
 * 可以证明不存在其他趣味子数组。因此，答案为 3 。
 * 示例 2：
 * 输入：nums = [3,1,9,6], modulo = 3, k = 0
 * 输出：2
 * 解释：在这个示例中，趣味子数组分别是：
 * 子数组 nums[0..3] ，也就是 [3,1,9,6] 。
 * - 在范围 [0, 3] 内，只存在 3 个下标 i = 0, 2, 3 满足 nums[i] % modulo == k 。
 * - 因此 cnt = 3 ，且 cnt % modulo == k 。
 * 子数组 nums[1..1] ，也就是 [1] 。
 * - 在范围 [1, 1] 内，不存在下标满足 nums[i] % modulo == k 。
 * - 因此 cnt = 0 ，且 cnt % modulo == k 。
 * 可以证明不存在其他趣味子数组，因此答案为 2 。
 * 提示：
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 109
 * 1 <= modulo <= 109
 * 0 <= k < modulo
 */
public class Solution_2845 {
    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        int n = nums.size();
        int[] sum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + (nums.get(i) % modulo == k ? 1 : 0);
        }

        int[] cnt = new int[Math.min(n + 1, modulo)];
        long ans = 0;
        for (int s : sum) {
            if (s >= k) {
                ans += cnt[(s - k) % modulo];
            }
            cnt[s % modulo]++;
        }
        return ans;
    }
}

/*
class Solution {
    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        int[] cnt = new int[Math.min(nums.size() + 1, modulo)];
        cnt[0] = 1; // 单独统计 s[0]=0
        long ans = 0;
        int s = 0;
        for (int x : nums) {
            if (x % modulo == k) {
                s++;
            }
            if (s >= k) {
                ans += cnt[(s - k) % modulo];
            }
            cnt[s % modulo]++;
        }
        return ans;
    }
}
*/