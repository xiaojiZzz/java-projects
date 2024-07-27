package leetcode.contest.weekly_407;

/**
 * 使数组等于目标数组所需的最少操作次数
 * 给你两个长度相同的正整数数组 nums 和 target。
 * 在一次操作中，你可以选择 nums 的任何子数组，并将该子数组内的每个元素的值增加或减少 1。
 * 返回使 nums 数组变为 target 数组所需的 最少 操作次数。
 * 示例 1：
 * 输入： nums = [3,5,1,2], target = [4,6,2,4]
 * 输出： 2
 * 解释：
 * 执行以下操作可以使 nums 等于 target：
 * - nums[0..3] 增加 1，nums = [4,6,2,3]。
 * - nums[3..3] 增加 1，nums = [4,6,2,4]。
 * 示例 2：
 * 输入： nums = [1,3,2], target = [2,1,4]
 * 输出： 5
 * 解释：
 * 执行以下操作可以使 nums 等于 target：
 * - nums[0..0] 增加 1，nums = [2,3,2]。
 * - nums[1..1] 减少 1，nums = [2,2,2]。
 * - nums[1..1] 减少 1，nums = [2,1,2]。
 * - nums[2..2] 增加 1，nums = [2,1,3]。
 * - nums[2..2] 增加 1，nums = [2,1,4]。
 * 提示：
 * 1 <= nums.length == target.length <= 105
 * 1 <= nums[i], target[i] <= 108
 */
public class Solution_3229 {
    public long minimumOperations(int[] nums, int[] target) {
        long s = target[0] - nums[0];
        long ans = Math.abs(s);
        for (int i = 1; i < nums.length; i++) {
            int k = (target[i] - target[i - 1]) - (nums[i] - nums[i - 1]);
            if (k > 0) {
                ans += s >= 0 ? k : Math.max(k + s, 0);
            } else {
                ans -= s <= 0 ? k : Math.min(k + s, 0);
            }
            s += k;
        }
        return ans;
    }
}
