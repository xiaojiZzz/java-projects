package leetcode.difficulty;

/**
 * 统计定界子数组的数目
 * 给你一个整数数组 nums 和两个整数 minK 以及 maxK 。
 * nums 的定界子数组是满足下述条件的一个子数组：
 * 子数组中的 最小值 等于 minK 。
 * 子数组中的 最大值 等于 maxK 。
 * 返回定界子数组的数目。
 * 子数组是数组中的一个连续部分。
 * 示例 1：
 * 输入：nums = [1,3,5,2,7,5], minK = 1, maxK = 5
 * 输出：2
 * 解释：定界子数组是 [1,3,5] 和 [1,3,5,2] 。
 * 示例 2：
 * 输入：nums = [1,1,1,1], minK = 1, maxK = 1
 * 输出：10
 * 解释：nums 的每个子数组都是一个定界子数组。共有 10 个子数组。
 * 提示：
 * 2 <= nums.length <= 105
 * 1 <= nums[i], minK, maxK <= 106
 */
public class Solution_2444 {
    public long countSubarrays(int[] nums, int minK, int maxK) {
        long res = 0;
        int border = -1, last_min = -1, last_max = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < minK || nums[i] > maxK) {
                last_max = -1;
                last_min = -1;
                border = i;
            }
            if (nums[i] == minK) {
                last_min = i;
            }
            if (nums[i] == maxK) {
                last_max = i;
            }
            if (last_min != -1 && last_max != -1) {
                res += Math.min(last_min, last_max) - border;
            }
        }
        return res;
    }
}
