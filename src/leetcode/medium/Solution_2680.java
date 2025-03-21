package leetcode.medium;

/**
 * 给你一个下标从 0 开始长度为 n 的整数数组 nums 和一个整数 k 。每一次操作中，你可以选择一个数并将它乘 2 。
 * 你最多可以进行 k 次操作，请你返回 nums[0] | nums[1] | ... | nums[n - 1] 的最大值。
 * a | b 表示两个整数 a 和 b 的 按位或 运算。
 * 示例 1：
 * 输入：nums = [12,9], k = 1
 * 输出：30
 * 解释：如果我们对下标为 1 的元素进行操作，新的数组为 [12,18] 。此时得到最优答案为 12 和 18 的按位或运算的结果，也就是 30 。
 * 示例 2：
 * 输入：nums = [8,1,2], k = 2
 * 输出：35
 * 解释：如果我们对下标 0 处的元素进行操作，得到新数组 [32,1,2] 。此时得到最优答案为 32|1|2 = 35 。
 * 提示：
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 109
 * 1 <= k <= 15
 */
public class Solution_2680 {
    public long maximumOr(int[] nums, int k) {
        int n = nums.length;
        int[] suf = new int[n];
        for (int i = n - 2; i >= 0; i--) {
            suf[i] = suf[i + 1] | nums[i + 1];
        }
        long ans = 0, pre = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, pre | suf[i] | (long) nums[i] << k);
            pre |= nums[i];
        }
        return ans;
    }
}
