package leetcode.contest.weekly_410;

/**
 * 给你一个长度为 n 的 正 整数数组 nums 。
 * 如果两个 非负 整数数组 (arr1, arr2) 满足以下条件，我们称它们是 单调 数组对：
 * 两个数组的长度都是 n 。
 * arr1 是单调 非递减 的，换句话说 arr1[0] <= arr1[1] <= ... <= arr1[n - 1] 。
 * arr2 是单调 非递增 的，换句话说 arr2[0] >= arr2[1] >= ... >= arr2[n - 1] 。
 * 对于所有的 0 <= i <= n - 1 都有 arr1[i] + arr2[i] == nums[i] 。
 * 请你返回所有 单调 数组对的数目。
 * 由于答案可能很大，请你将它对 109 + 7 取余 后返回。
 * 示例 1：
 * 输入：nums = [2,3,2]
 * 输出：4
 * 解释：
 * 单调数组对包括：
 * ([0, 1, 1], [2, 2, 1])
 * ([0, 1, 2], [2, 2, 0])
 * ([0, 2, 2], [2, 1, 0])
 * ([1, 2, 2], [1, 1, 0])
 * 示例 2：
 * 输入：nums = [5,5,5,5]
 * 输出：126
 * 提示：
 * 1 <= n == nums.length <= 2000
 * 1 <= nums[i] <= 50
 */
public class Solution_3250 {
    public int countOfPairs(int[] nums) {
        int n = nums.length;
        int mod = 1000000007;
        // dp[i][j] 表示第 i 个位置，arr1[i] = j, 所满足规则的个数，arr2[i] 可以用 nums[i] - arr1[i] 表示，所以省略一个位置
        int[][] dp = new int[n][51];
        for (int i = 0; i <= nums[0]; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= nums[i]; j++) {
                // 计算 arr1[i] = j 时，前一个 arr1[i - 1] 能取到的最大数
                int max = Math.min(j, nums[i - 1] - nums[i] + j);
                for (int k = 0; k <= max; k++) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][k]) % mod;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i <= nums[n - 1]; i++) {
            ans = (ans + dp[n - 1][i]) % mod;
        }
        return ans;
    }
}

/*
class Solution {
    private int mod = 1000000007;
    private int[][] memo;

    public int countOfPairs(int[] nums) {
        int n = nums.length;
        memo = new int[n][nums[n - 1] + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        int res = 0;
        for (int i = 0; i <= nums[n - 1]; i++) {
            res = (res + dfs(nums, n - 1, i)) % mod;
        }
        return res;
    }

    private int dfs(int[] nums, int i, int j) {
        if (i == 0) {
            return 1;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        int max = Math.min(j, nums[i - 1] - nums[i] + j);
        int res = 0;
        for (int k = 0; k <= max; k++) {
            res = (res + dfs(nums, i - 1, k)) % mod;
        }
        return memo[i][j] = res;
    }
}
*/