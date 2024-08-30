package leetcode.medium;

/**
 * 所有数对中数位不同之和
 * 你有一个数组 nums ，它只包含 正 整数，所有正整数的数位长度都 相同 。
 * 两个整数的 数位不同 指的是两个整数 相同 位置上不同数字的数目。
 * 请你返回 nums 中 所有 整数对里，数位不同之和。
 * 示例 1：
 * 输入：nums = [13,23,12]
 * 输出：4
 * 解释：
 * 计算过程如下：
 * - 13 和 23 的数位不同为 1 。
 * - 13 和 12 的数位不同为 1 。
 * - 23 和 12 的数位不同为 2 。
 * 所以所有整数数对的数位不同之和为 1 + 1 + 2 = 4 。
 * 示例 2：
 * 输入：nums = [10,10,10,10]
 * 输出：0
 * 解释：
 * 数组中所有整数都相同，所以所有整数数对的数位不同之和为 0 。
 * 提示：
 * 2 <= nums.length <= 105
 * 1 <= nums[i] < 109
 * nums 中的整数都有相同的数位长度。
 */
public class Solution_3153 {
    public long sumDigitDifferences(int[] nums) {
        int n = nums.length;
        long ans = 0;
        while (nums[0] > 0) {
            int[] idx = new int[10];
            for (int i = 0; i < n; i++) {
                idx[nums[i] % 10]++;
                nums[i] /= 10;
            }
            int[] preSum = new int[10];
            preSum[0] = idx[0];
            for (int i = 1; i < 10; i++) {
                preSum[i] = preSum[i - 1] + idx[i];
            }
            for (int i = 0; i < 9; i++) {
                ans += (long) idx[i] * (preSum[9] - preSum[i]);
            }
        }
        return ans;
    }
}

/*
class Solution {
    public long sumDigitDifferences(int[] nums) {
        long ans = 0;
        int[][] cnt = new int[Integer.toString(nums[0]).length()][10];
        for (int k = 0; k < nums.length; k++) {
            int x = nums[k];
            for (int i = 0; x > 0; x /= 10, i++) {
                ans += k - cnt[i][x % 10]++;
            }
        }
        return ans;
    }
}
*/