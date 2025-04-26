package leetcode.difficulty;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 和至少为 K 的最短子数组
 * 给你一个整数数组 nums 和一个整数 k ，找出 nums 中和至少为 k 的 最短非空子数组 ，并返回该子数组的长度。如果不存在这样的 子数组 ，返回 -1 。
 * 子数组 是数组中 连续 的一部分。
 * 示例 1：
 * 输入：nums = [1], k = 1
 * 输出：1
 * 示例 2：
 * 输入：nums = [1,2], k = 4
 * 输出：-1
 * 示例 3：
 * 输入：nums = [2,-1,2], k = 3
 * 输出：3
 * 提示：
 * 1 <= nums.length <= 105
 * -105 <= nums[i] <= 105
 * 1 <= k <= 109
 */
public class Solution_862 {
    // 单调队列
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length, ans = n + 1;
        long[] preSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i <= n; i++) {
            long sum = preSum[i];
            while (!deque.isEmpty() && sum - preSum[deque.peekFirst()] >= k) {
                ans = Math.min(ans, i - deque.pollFirst());
            }
            while (!deque.isEmpty() && sum <= preSum[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.addLast(i);
        }
        return ans > n ? -1 : ans;
    }
}
