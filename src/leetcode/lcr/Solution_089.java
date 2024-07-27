package leetcode.lcr;


/**
 * 同leetcode 198.打家劫舍
 */
public class Solution_089 {
    //动态规划
    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        int[][] dp = new int[len][2];
        dp[0][0] = 0;
        dp[0][1] = nums[0];
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = dp[i - 1][0] + nums[i];
        }
        return Math.max(dp[len - 1][0], dp[len - 1][1]);
    }
}

/*
class Solution {
    //动态规划，空间优化
    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        int[] dp = new int[2];
        dp[0] = 0;
        dp[1] = nums[0];
        int pre0 = dp[0], pre1 = dp[1];
        for (int i = 1; i < len; i++) {
            dp[0] = Math.max(dp[0], pre1);
            dp[1] = pre0 + nums[i];
            pre0 = dp[0];
            pre1 = dp[1];
        }
        return Math.max(dp[0], dp[1]);
    }
}
*/

/*
class Solution {
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        int[] dp = new int[N + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int k = 2; k <= N; k++) {
            dp[k] = Math.max(dp[k - 1], nums[k - 1] + dp[k - 2]);
        }
        return dp[N];
    }
}
*/

/*
class Solution {
    public int rob(int[] nums) {
        int prev = 0;
        int curr = 0;

        // 每次循环，计算“偷到当前房子为止的最大金额”
        for (int i : nums) {
            // 循环开始时，curr 表示 dp[k-1]，prev 表示 dp[k-2]
            // dp[k] = max{ dp[k-1], dp[k-2] + i }
            int temp = Math.max(curr, prev + i);
            prev = curr;
            curr = temp;
            // 循环结束时，curr 表示 dp[k]，prev 表示 dp[k-1]
        }

        return curr;
    }
}
*/
