package leetcode.medium;

import java.util.Arrays;


/**
 * 给你一个整数数组 nums ，如果 nums 至少 包含 2 个元素，你可以执行以下操作中的 任意 一个：
 * 选择 nums 中最前面两个元素并且删除它们。
 * 选择 nums 中最后两个元素并且删除它们。
 * 选择 nums 中第一个和最后一个元素并且删除它们。
 * 一次操作的 分数 是被删除元素的和。
 * 在确保 所有操作分数相同 的前提下，请你求出 最多 能进行多少次操作。
 * 请你返回按照上述要求 最多 可以进行的操作次数。
 * 示例 1：
 * 输入：nums = [3,2,1,2,3,4]
 * 输出：3
 * 解释：我们执行以下操作：
 * - 删除前两个元素，分数为 3 + 2 = 5 ，nums = [1,2,3,4] 。
 * - 删除第一个元素和最后一个元素，分数为 1 + 4 = 5 ，nums = [2,3] 。
 * - 删除第一个元素和最后一个元素，分数为 2 + 3 = 5 ，nums = [] 。
 * 由于 nums 为空，我们无法继续进行任何操作。
 * 示例 2：
 * 输入：nums = [3,2,6,1,4]
 * 输出：2
 * 解释：我们执行以下操作：
 * - 删除前两个元素，分数为 3 + 2 = 5 ，nums = [6,1,4] 。
 * - 删除最后两个元素，分数为 1 + 4 = 5 ，nums = [6] 。
 * 至多进行 2 次操作。
 */
public class Solution_3040 {
    public int maxOperations(int[] nums) {
        int n = nums.length;
        int[][] memo = new int[n][n];
        int ans = 0;
        util(memo);
        ans = Math.max(dfs(nums, 2, n - 1, nums[0] + nums[1], memo), ans);
        util(memo);
        ans = Math.max(dfs(nums, 1, n - 2, nums[0] + nums[n - 1], memo), ans);
        util(memo);
        ans = Math.max(dfs(nums, 0, n - 3, nums[n - 2] + nums[n - 1], memo), ans);
        return ans;
    }

    private void util(int[][] memo) {
        for (int i = 0; i < memo.length; i++) {
            Arrays.fill(memo[i], -1);
        }
    }

    private int dfs(int[] nums, int left, int right, int sum, int[][] memo) {
        if (left >= right) {
            return 0;
        }
        if (memo[left][right] != -1) {
            return memo[left][right];
        }
        int ans = 0;
        if (nums[left] + nums[left + 1] == sum) {
            ans = Math.max(dfs(nums, left + 2, right, sum, memo) + 1, ans);
        }
        if (nums[left] + nums[right] == sum) {
            ans = Math.max(dfs(nums, left + 1, right - 1, sum, memo) + 1, ans);
        }
        if (nums[right] + nums[right - 1] == sum) {
            ans = Math.max(dfs(nums, left, right - 2, sum, memo) + 1, ans);
        }
        memo[left][right] = ans;
        return ans;
    }
}

/*
class Solution {
    public int maxOperations(int[] nums) {
        int n = nums.length;
        int res1 = helper(nums, 2, n - 1, nums[0] + nums[1]); // 删除前两个数
        int res2 = helper(nums, 0, n - 3, nums[n - 2] + nums[n - 1]); // 删除后两个数
        int res3 = helper(nums, 1, n - 2, nums[0] + nums[n - 1]); // 删除第一个和最后一个数
        return Math.max(res1, Math.max(res2, res3)) + 1; // 加上第一次操作
    }

    private int helper(int[] nums, int start, int end, int target) {
        int n = nums.length;
        int[][] f = new int[n + 1][n + 1];
        for (int i = end - 1; i >= start; i--) {
            for (int j = i + 1; j <= end; j++) {
                if (nums[i] + nums[i + 1] == target) { // 删除前两个数
                    f[i][j + 1] = Math.max(f[i][j + 1], f[i + 2][j + 1] + 1);
                }
                if (nums[j - 1] + nums[j] == target) { // 删除后两个数
                    f[i][j + 1] = Math.max(f[i][j + 1], f[i][j - 1] + 1);
                }
                if (nums[i] + nums[j] == target) { // 删除第一个和最后一个数
                    f[i][j + 1] = Math.max(f[i][j + 1], f[i + 1][j] + 1);
                }
            }
        }
        return f[start][end + 1];
    }
}
*/
