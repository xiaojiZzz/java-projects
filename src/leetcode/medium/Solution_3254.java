package leetcode.medium;

import java.util.Arrays;

/**
 * 长度为 K 的子数组的能量值 I
 * 给你一个长度为 n 的整数数组 nums 和一个正整数 k 。
 * 一个数组的 能量值 定义为：
 * 如果 所有 元素都是依次 连续 且 上升 的，那么能量值为 最大 的元素。
 * 否则为 -1 。
 * 你需要求出 nums 中所有长度为 k 的子数组的能量值。
 * 请你返回一个长度为 n - k + 1 的整数数组 results ，其中 results[i] 是子数组 nums[i..(i + k - 1)] 的能量值。
 * 示例 1：
 * 输入：nums = [1,2,3,4,3,2,5], k = 3
 * 输出：[3,4,-1,-1,-1]
 * 解释：
 * nums 中总共有 5 个长度为 3 的子数组：
 * [1, 2, 3] 中最大元素为 3 。
 * [2, 3, 4] 中最大元素为 4 。
 * [3, 4, 3] 中元素 不是 连续的。
 * [4, 3, 2] 中元素 不是 上升的。
 * [3, 2, 5] 中元素 不是 连续的。
 * 示例 2：
 * 输入：nums = [2,2,2,2,2], k = 4
 * 输出：[-1,-1]
 * 示例 3：
 * 输入：nums = [3,2,3,2,3,2], k = 2
 * 输出：[-1,3,-1,3,-1]
 * 提示：
 * 1 <= n == nums.length <= 500
 * 1 <= nums[i] <= 105
 * 1 <= k <= n
 */
public class Solution_3254 {
    public int[] resultsArray(int[] nums, int k) {
        int n = nums.length;
        if (n == 1 || k == 1) {
            return nums;
        }
        int left = 0;
        int[] ans = new int[n - k + 1];
        Arrays.fill(ans, -1);
        for (int right = 1; right < n; right++) {
            if (nums[right] != nums[right - 1] + 1) {
                left = right;
            }
            if (right >= k - 1) {
                if (right - left + 1 >= k) {
                    ans[right - k + 1] = nums[right];
                    left++;
                }
            }
        }
        return ans;
    }
}

/*
class Solution {
    public int[] resultsArray(int[] nums, int k) {
        int[] ans = new int[nums.length - k + 1];
        Arrays.fill(ans, -1);
        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            cnt = i == 0 || nums[i] == nums[i - 1] + 1 ? cnt + 1 : 1;
            if (cnt >= k) {
                ans[i - k + 1] = nums[i];
            }
        }
        return ans;
    }
}
*/