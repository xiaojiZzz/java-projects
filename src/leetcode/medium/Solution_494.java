package leetcode.medium;

import java.util.Arrays;


/**
 * 给你一个非负整数数组 nums 和一个整数 target 。
 * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 * 示例 1：
 * 输入：nums = [1,1,1,1,1], target = 3
 * 输出：5
 * 解释：一共有 5 种方法让最终目标和为 3 。
 * -1 + 1 + 1 + 1 + 1 = 3
 * +1 - 1 + 1 + 1 + 1 = 3
 * +1 + 1 - 1 + 1 + 1 = 3
 * +1 + 1 + 1 - 1 + 1 = 3
 * +1 + 1 + 1 + 1 - 1 = 3
 * 示例 2：
 * 输入：nums = [1], target = 1
 * 输出：1
 */
public class Solution_494 {
    // 使用负数来建立 dp 数组，时间复杂度比使用正数的低
    public int findTargetSumWays(int[] nums, int target) {
        int sum = Arrays.stream(nums).sum();
        if ((sum - target) % 2 != 0 || sum - target < 0) {
            return 0;
        }
        int pos = (sum - target) / 2;
        int[] dp = new int[pos + 1];
        dp[0] = 1;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = pos; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
            }
        }
        return dp[pos];
    }
}

/*
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        // 将0入队列
        queue.offer(0);
        for (int num : nums) {
            int n = queue.size();
            for (int j = 0; j < n; j++) {
                int x = queue.poll();
                queue.offer(x + num);
                queue.offer(x - num);
            }
        }
        // 此时queue中全部为叶子结点
        for (int x : queue) {
            if (x == target)
                count++;
        }
        // 返回结果
        return count;
    }
}
*/
