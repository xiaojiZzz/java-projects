package leetcode.medium;

/**
 * 最低票价
 * 在一个火车旅行很受欢迎的国度，你提前一年计划了一些火车旅行。
 * 在接下来的一年里，你要旅行的日子将以一个名为 days 的数组给出。每一项是一个从 1 到 365 的整数。
 * 火车票有 三种不同的销售方式 ：
 * 一张 为期一天 的通行证售价为 costs[0] 美元；
 * 一张 为期七天 的通行证售价为 costs[1] 美元；
 * 一张 为期三十天 的通行证售价为 costs[2] 美元。
 * 通行证允许数天无限制的旅行。 例如，如果我们在第 2 天获得一张 为期 7 天 的通行证，
 * 那么我们可以连着旅行 7 天：第 2 天、第 3 天、第 4 天、第 5 天、第 6 天、第 7 天和第 8 天。
 * 返回 你想要完成在给定的列表 days 中列出的每一天的旅行所需要的最低消费 。
 * 示例 1：
 * 输入：days = [1,4,6,7,8,20], costs = [2,7,15]
 * 输出：11
 * 解释：
 * 例如，这里有一种购买通行证的方法，可以让你完成你的旅行计划：
 * 在第 1 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 1 天生效。
 * 在第 3 天，你花了 costs[1] = $7 买了一张为期 7 天的通行证，它将在第 3, 4, ..., 9 天生效。
 * 在第 20 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 20 天生效。
 * 你总共花了 $11，并完成了你计划的每一天旅行。
 * 示例 2：
 * 输入：days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
 * 输出：17
 * 解释：
 * 例如，这里有一种购买通行证的方法，可以让你完成你的旅行计划：
 * 在第 1 天，你花了 costs[2] = $15 买了一张为期 30 天的通行证，它将在第 1, 2, ..., 30 天生效。
 * 在第 31 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 31 天生效。
 * 你总共花了 $17，并完成了你计划的每一天旅行。
 * 提示：
 * 1 <= days.length <= 365
 * 1 <= days[i] <= 365
 * days 按顺序严格递增
 * costs.length == 3
 * 1 <= costs[i] <= 1000
 */
public class Solution_983 {
    public int mincostTickets(int[] days, int[] costs) {
        int n = days.length;
        int[] dp = new int[366];
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                dp[days[i]] = dp[days[i - 1]] + costs[0];
            } else {
                dp[days[i]] = costs[0];
            }
            if (days[i] >= 7) {
                int tmp = days[i] - 7;
                while (tmp >= 0 && dp[tmp] == 0) {
                    tmp--;
                }
                dp[days[i]] = Math.min(tmp < 0 ? costs[1] : dp[tmp] + costs[1], dp[days[i]]);
            } else {
                dp[days[i]] = Math.min(dp[days[i]], costs[1]);
            }
            if (days[i] >= 30) {
                int tmp = days[i] - 30;
                while (tmp >= 0 && dp[tmp] == 0) {
                    tmp--;
                }
                dp[days[i]] = Math.min(tmp < 0 ? costs[2] : dp[tmp] + costs[2], dp[days[i]]);
            } else {
                dp[days[i]] = Math.min(dp[days[i]], costs[2]);
            }
        }
        return dp[days[n - 1]];
    }
}

/*
class Solution {
    public int mincostTickets(int[] days, int[] costs) {
        int n = days[days.length - 1];
        int[] dp = new int[n + 1];
        for (int i = 1, idx = 0; i <= n; i++) {
            if (i < days[idx]) {
                dp[i] = dp[i - 1];
                continue;
            }
            dp[i] = dp[i - 1] + costs[0];
            dp[i] = Math.min(dp[i], dp[Math.max(0, i - 7)] + costs[1]);
            dp[i] = Math.min(dp[i], dp[Math.max(0, i - 30)] + costs[2]);
            idx++;
        }
        return dp[n];
    }
}
*/

/*
class Solution {
    public int mincostTickets(int[] days, int[] costs) {
        int lastDay = days[days.length - 1];
        boolean[] isTravel = new boolean[lastDay + 1];
        for (int d : days) {
            isTravel[d] = true;
        }
        int[] memo = new int[lastDay + 1];
        return dfs(lastDay, isTravel, costs, memo);
    }

    private int dfs(int i, boolean[] isTravel, int[] costs, int[] memo) {
        if (i <= 0) {
            return 0;
        }
        if (memo[i] > 0) { // 之前计算过
            return memo[i];
        }
        if (!isTravel[i]) {
            return memo[i] = dfs(i - 1, isTravel, costs, memo);
        }
        return memo[i] = Math.min(dfs(i - 1, isTravel, costs, memo) + costs[0],
                         Math.min(dfs(i - 7, isTravel, costs, memo) + costs[1],
                                  dfs(i - 30, isTravel, costs, memo) + costs[2]));
    }
}
*/

/*
class Solution {
    public int mincostTickets(int[] days, int[] costs) {
        int lastDay = days[days.length - 1];
        boolean[] isTravel = new boolean[lastDay + 1];
        for (int d : days) {
            isTravel[d] = true;
        }
        int[] f = new int[lastDay + 1];
        for (int i = 1; i <= lastDay; i++) {
            if (!isTravel[i]) {
                f[i] = f[i - 1];
            } else {
                f[i] = Math.min(f[i - 1] + costs[0],
                       Math.min(f[Math.max(i - 7, 0)] + costs[1],
                                f[Math.max(i - 30, 0)] + costs[2]));
            }
        }
        return f[lastDay];
    }
}
*/

/*
class Solution {
    // 不需要考虑最后 days[n - 1] 的大小，只和 n 有关
    public int mincostTickets(int[] days, int[] costs) {
        int n = days.length;
        int[] f = new int[n + 1];
        int j = 0;
        int k = 0;
        for (int i = 0; i < n; i++) {
            int d = days[i];
            while (days[j] <= d - 7) {
                j++;
            }
            while (days[k] <= d - 30) {
                k++;
            }
            f[i + 1] = Math.min(f[i] + costs[0], Math.min(f[j] + costs[1], f[k] + costs[2]));
        }
        return f[n];
    }
}
*/