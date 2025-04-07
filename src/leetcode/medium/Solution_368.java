package leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 最大整除子集
 * 给你一个由 无重复 正整数组成的集合 nums ，请你找出并返回其中最大的整除子集 answer ，子集中每一元素对 (answer[i], answer[j]) 都应当满足：
 * answer[i] % answer[j] == 0 ，或
 * answer[j] % answer[i] == 0
 * 如果存在多个有效解子集，返回其中任何一个均可。
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[1,2]
 * 解释：[1,3] 也会被视为正确答案。
 * 示例 2：
 * 输入：nums = [1,2,4,8]
 * 输出：[1,2,4,8]
 * 提示：
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 2 * 109
 * nums 中的所有整数 互不相同
 */
public class Solution_368 {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int len = nums.length;
        Arrays.sort(nums);

        // 第 1 步：动态规划找出最大子集的个数、最大子集中的最大整数
        int[] dp = new int[len];
        Arrays.fill(dp, 1);
        int maxSize = 1;
        int maxVal = dp[0];
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                // 题目中说「没有重复元素」很重要
                if (nums[i] % nums[j] == 0) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            if (dp[i] > maxSize) {
                maxSize = dp[i];
                maxVal = nums[i];
            }
        }

        // 第 2 步：倒推获得最大子集
        List<Integer> res = new ArrayList<>();
        if (maxSize == 1) {
            res.add(nums[0]);
            return res;
        }

        for (int i = len - 1; i >= 0 && maxSize > 0; i--) {
            if (dp[i] == maxSize && maxVal % nums[i] == 0) {
                res.add(nums[i]);
                maxVal = nums[i];
                maxSize--;
            }
        }
        return res;
    }
}

/*
class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);

        int n = nums.length;
        int[] memo = new int[n];
        int[] from = new int[n];
        Arrays.fill(from, -1);
        int maxF = 0;
        int maxI = 0;

        for (int i = 0; i < n; i++) {
            int f = dfs(i, nums, memo, from);
            if (f > maxF) {
                maxF = f;
                maxI = i; // 最长合法子序列的最后一个数的下标
            }
        }

        List<Integer> path = new ArrayList<>(maxF); // 预分配空间
        for (int i = maxI; i >= 0; i = from[i]) {
            path.add(nums[i]);
        }
        return path; // 不需要 reverse，任意顺序返回均可
    }

    private int dfs(int i, int[] nums, int[] memo, int[] from) {
        if (memo[i] > 0) { // 之前计算过
            return memo[i];
        }
        int res = 0;
        for (int j = 0; j < i; j++) {
            if (nums[i] % nums[j] != 0) {
                continue;
            }
            int f = dfs(j, nums, memo, from);
            if (f > res) {
                res = f;
                from[i] = j; // 记录最佳转移来源
            }
        }
        return memo[i] = res + 1; // 记忆化
    }
}
*/

/*
class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);

        int n = nums.length;
        int[] f = new int[n];
        int[] from = new int[n];
        Arrays.fill(from, -1);
        int maxI = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && f[j] > f[i]) {
                    f[i] = f[j];
                    from[i] = j; // 记录最佳转移来源
                }
            }
            f[i]++;
            if (f[i] > f[maxI]) {
                maxI = i; // 最长合法子序列的最后一个数的下标
            }
        }

        List<Integer> path = new ArrayList<>(f[maxI]); // 预分配空间
        for (int i = maxI; i >= 0; i = from[i]) {
            path.add(nums[i]);
        }
        return path; // 不需要 reverse，任意顺序返回均可
    }
}
*/