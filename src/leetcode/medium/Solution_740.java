package leetcode.medium;

/**
 * 删除并获得点数
 * 给你一个整数数组 nums ，你可以对它进行一些操作。
 * 每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除 所有 等于 nums[i] - 1 和 nums[i] + 1 的元素。
 * 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
 * 示例 1：
 * 输入：nums = [3,4,2]
 * 输出：6
 * 解释：
 * 删除 4 获得 4 个点数，因此 3 也被删除。
 * 之后，删除 2 获得 2 个点数。总共获得 6 个点数。
 * 示例 2：
 * 输入：nums = [2,2,3,3,3,4]
 * 输出：9
 * 解释：
 * 删除 3 获得 3 个点数，接着要删除两个 2 和 4 。
 * 之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
 * 总共获得 9 个点数。
 * 提示：
 * 1 <= nums.length <= 2 * 104
 * 1 <= nums[i] <= 104
 */
public class Solution_740 {
    public int deleteAndEarn(int[] nums) {
        int[] dp = new int[10002];
        int[] cnt = new int[10001];
        for (int num : nums) {
            cnt[num] += num;
        }
        for (int i = 1; i <= 10000; i++) {
            dp[i + 1] = Math.max(dp[i], dp[i - 1] + cnt[i]);
        }
        return dp[10001];
    }
}

/*
class Solution {
    public int deleteAndEarn(int[] nums) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int num : nums) {
            cnt.merge(num, 1, Integer::sum);
        }

        int n = cnt.size();
        int[] a = new int[n];
        int k = 0;
        for (int x : cnt.keySet()) {
            a[k++] = x;
        }
        Arrays.sort(a);

        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        return dfs(a, cnt, memo, n - 1);
    }

    private int dfs(int[] a, Map<Integer, Integer> cnt, int[] memo, int i) {
        if (i < 0) {
            return 0;
        }
        if (memo[i] != -1) {
            return memo[i];
        }
        int x = a[i];
        int j = i;
        while (j > 0 && a[j - 1] >= x - 1) {
            j--;
        }
        return memo[i] = Math.max(dfs(a, cnt, memo, i - 1), dfs(a, cnt, memo, j - 1) + x * cnt.get(x));
    }
}
*/

/*
class Solution {
    public int deleteAndEarn(int[] nums) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int num : nums) {
            cnt.merge(num, 1, Integer::sum);
        }

        int n = cnt.size();
        int[] a = new int[n];
        int k = 0;
        for (int x : cnt.keySet()) {
            a[k++] = x;
        }
        Arrays.sort(a);

        int[] dp = new int[n + 1];
        int j = 0;
        for (int i = 0; i < n; i++) {
            int x = a[i];
            while (a[j] < x - 1) {
                j++;
            }
            dp[i + 1] = Math.max(dp[i], dp[j] + x * cnt.get(x));
        }
        return dp[n];
    }
}
*/
