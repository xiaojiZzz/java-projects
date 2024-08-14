package leetcode.medium;

/**
 * 如果数组的每一对相邻元素都是两个奇偶性不同的数字，则该数组被认为是一个 特殊数组 。
 * 你有一个整数数组 nums 和一个二维整数矩阵 queries，对于 queries[i] = [fromi, toi]，请你帮助你检查子数组
 * nums[fromi..toi] 是不是一个 特殊数组 。
 * 返回布尔数组 answer，如果 nums[fromi..toi] 是特殊数组，则 answer[i] 为 true ，否则，answer[i] 为 false 。
 * 示例 1：
 * 输入：nums = [3,4,1,2,6], queries = [[0,4]]
 * 输出：[false]
 * 解释：
 * 子数组是 [3,4,1,2,6]。2 和 6 都是偶数。
 * 示例 2：
 * 输入：nums = [4,3,1,6], queries = [[0,2],[2,3]]
 * 输出：[false,true]
 * 解释：
 * 子数组是 [4,3,1]。3 和 1 都是奇数。因此这个查询的答案是 false。
 * 子数组是 [1,6]。只有一对：(1,6)，且包含了奇偶性不同的数字。因此这个查询的答案是 true。
 * 提示：
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 * 1 <= queries.length <= 105
 * queries[i].length == 2
 * 0 <= queries[i][0] <= queries[i][1] <= nums.length - 1
 */
public class Solution_3152 {
    public boolean[] isArraySpecial(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] preSum = new int[n];
        preSum[0] = 1;
        for (int i = 1; i < n; i++) {
            if ((nums[i - 1] ^ nums[i]) % 2 == 1) {
                preSum[i] = preSum[i - 1] + 1;
            } else {
                preSum[i] = preSum[i - 1];
            }
        }
        int m = queries.length;
        boolean[] ans = new boolean[m];
        for (int i = 0; i < m; i++) {
            int x = queries[i][0], y = queries[i][1];
            if (x == 0) {
                if (y == preSum[y] - preSum[x]) {
                    ans[i] = true;
                }
            } else {
                if (y - x == preSum[y] - preSum[x]) {
                    ans[i] = true;
                }
            }
        }
        return ans;
    }
}

/*
class Solution {
    public boolean[] isArraySpecial(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < n; i++) {
            if (((nums[i] ^ nums[i - 1]) & 1) != 0) {
                dp[i] = dp[i - 1] + 1;
            }
        }

        boolean[] res = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int x = queries[i][0], y = queries[i][1];
            res[i] = dp[y] >= y - x + 1;
        }
        return res;
    }
}
*/

/*
class Solution {
    public boolean[] isArraySpecial(int[] nums, int[][] queries) {
        int[] s = new int[nums.length];
        for (int i = 1; i < nums.length; i++) {
            s[i] = s[i - 1] + (nums[i - 1] % 2 == nums[i] % 2 ? 1 : 0);
        }
        boolean[] ans = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] q = queries[i];
            ans[i] = s[q[0]] == s[q[1]];
        }
        return ans;
    }
}
*/