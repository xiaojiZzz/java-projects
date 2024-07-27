package leetcode.medium;

import java.util.Arrays;


/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，
 * 这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
 * 示例 1：
 * 输入：nums = [2,3,2]
 * 输出：3
 * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 */
public class Solution_213 {
    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        return Math.max(myRob(Arrays.copyOfRange(nums, 0, nums.length - 1)),
                myRob(Arrays.copyOfRange(nums, 1, nums.length)));
    }

    private int myRob(int[] nums) {
        int pre = 0, cur = 0, tmp;
        for (int num : nums) {
            tmp = cur;
            cur = Math.max(pre + num, cur);
            pre = tmp;
        }
        return cur;
    }
}

/*
class Solution {
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        // 由于偷了最后一个，第一个就不能投，偷了第一个，最后一个就不能偷，那么使用两个dp,一个dp从第一个开始，一个dp从第二个开始，最后判断哪个最大。
        int[] dp0 = new int[nums.length];
        int[] dp1 = new int[nums.length];
        dp0[0] = nums[0];
        dp0[1] = nums[0];
        dp1[1] = nums[1];
        for (int i = 2; i < nums.length; i++) {
            dp0[i] = Math.max(dp0[i - 2] + nums[i], dp0[i - 1]);
            dp1[i] = Math.max(dp1[i - 2] + nums[i], dp1[i - 1]);
        }
        return Math.max(dp0[nums.length - 2], dp1[nums.length - 1]);
    }
}
*/

/*
class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        return Math.max(nums[0] + rob1(nums, 2, n - 1), rob1(nums, 1, n));
    }

    // 198. 打家劫舍
    private int rob1(int[] nums, int start, int end) { // [start,end) 左闭右开
        int f0 = 0, f1 = 0;
        for (int i = start; i < end; ++i) {
            int newF = Math.max(f1, f0 + nums[i]);
            f0 = f1;
            f1 = newF;
        }
        return f1;
    }
}
*/
