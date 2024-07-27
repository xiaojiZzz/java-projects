package leetcode.medium;


/**
 * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
 * 每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:
 * 0 <= j <= nums[i]
 * i + j < n
 * 返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
 * 示例 1:
 * 输入: nums = [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * 示例 2:
 * 输入: nums = [2,3,0,1,4]
 * 输出: 2
 */
public class Solution_45 {
    public int jump(int[] nums) {
        int ans = 0;
        int idx = nums.length - 1;
        while (idx > 0) {
            for (int i = 0; i <= idx; i++) {
                if (i + nums[i] >= idx) {
                    ans++;
                    idx = i;
                }
            }
        }
        return ans;
    }
}

/*
class Solution {
    public int jump(int[] nums) {
        int ans = 0;
        int idx = nums.length - 1;
        while (idx > 0) {
            int e = idx;
            for (int i = idx; i >= 0; i--) {
                if (i + nums[i] >= e) {
                    idx = i;
                }
            }
            ans++;
        }
        return ans;
    }
}
*/

/*
class Solution {
    public int jump(int[] nums) {
        int n = nums.length;
        int step = 0, right = 0, ans = 0;
        // 注意小于 n - 1，不然需要写更多的逻辑
        for (int i = 0; i < n - 1; i++) {
            right = Math.max(right, i + nums[i]);
            if (i == step) {
                ans++;
                step = right;
            }
        }
        return ans;
    }
}
*/

/*
class Solution {
    public int jump(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = 0;
        for (int i = 1; i < n; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (j + nums[j] >= i) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[n - 1];
    }
}
*/
