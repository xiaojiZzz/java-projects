package leetcode.medium;


/**
 * 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
 * 示例 1：
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 * 示例 2：
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 */
public class Solution_55 {
    public boolean canJump(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return true;
        }
        int s = n - 2, e = n - 1;
        while (s >= 0) {
            if (s == 0 && nums[0] >= e - s) {
                return true;
            }
            if (nums[s] >= e - s) {
                e = s;
            }
            s--;
        }
        return false;
    }
}

/*
class Solution {
    public boolean canJump(int[] nums) {
        int step = 0, n = nums.length;
        for (int i = 0; i <= step; i++) {
            step = Math.max(step, i + nums[i]);
            if (step >= n - 1) {
                return true;
            }
        }
        return false;
    }
}
*/

/*
class Solution {
    public boolean canJump(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return true;
        }
        if (nums[0] == 0) {
            return false;
        }
        int[] dp = new int[n];
        dp[0] = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], i + nums[i]);
            if (dp[i] >= n - 1) {
                return true;
            }
            if (dp[i] == i) {
                return false;
            }
        }
        return dp[n - 1] >= n - 1;
    }
}
*/