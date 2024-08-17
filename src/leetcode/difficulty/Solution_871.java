package leetcode.difficulty;

/**
 * 最低加油次数
 * 汽车从起点出发驶向目的地，该目的地位于出发位置东面 target 英里处。
 * 沿途有加油站，用数组 stations 表示。
 * 其中 stations[i] = [positioni, fueli] 表示第 i 个加油站位于出发位置东面 positioni 英里处，并且有 fueli 升汽油。
 * 假设汽车油箱的容量是无限的，其中最初有 startFuel 升燃料。它每行驶 1 英里就会用掉 1 升汽油。
 * 当汽车到达加油站时，它可能停下来加油，将所有汽油从加油站转移到汽车中。
 * 为了到达目的地，汽车所必要的最低加油次数是多少？如果无法到达目的地，则返回 -1 。
 * 注意：如果汽车到达加油站时剩余燃料为 0，它仍然可以在那里加油。如果汽车到达目的地时剩余燃料为 0，仍然认为它已经到达目的地。
 * 示例 1：
 * 输入：target = 1, startFuel = 1, stations = []
 * 输出：0
 * 解释：可以在不加油的情况下到达目的地。
 * 示例 2：
 * 输入：target = 100, startFuel = 1, stations = [[10,100]]
 * 输出：-1
 * 解释：无法抵达目的地，甚至无法到达第一个加油站。
 * 示例 3：
 * 输入：target = 100, startFuel = 10, stations = [[10,60],[20,30],[30,30],[60,40]]
 * 输出：2
 * 解释：
 * 出发时有 10 升燃料。
 * 开车来到距起点 10 英里处的加油站，消耗 10 升燃料。将汽油从 0 升加到 60 升。
 * 然后，从 10 英里处的加油站开到 60 英里处的加油站（消耗 50 升燃料），
 * 并将汽油从 10 升加到 50 升。然后开车抵达目的地。
 * 沿途在两个加油站停靠，所以返回 2 。
 * 提示：
 * 1 <= target, startFuel <= 109
 * 0 <= stations.length <= 500
 * 1 <= positioni < positioni+1 < target
 * 1 <= fueli < 109
 */
public class Solution_871 {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        int n = stations.length;
        // dp[i][j] 表示到第 i 个加油站加 j 次油后能到达的最远距离
        int[][] dp = new int[n + 1][n + 1];
        dp[0][0] = startFuel;

        for (int i = 1; i <= n; i++) {
            int dist = stations[i - 1][0];
            int fuel = stations[i - 1][1];
            for (int j = 0; j <= i; j++) {
                // 如果不加油
                if (dp[i - 1][j] >= dist) {
                    dp[i][j] = dp[i - 1][j];
                }
                // 如果加油
                if (j > 0 && dp[i - 1][j - 1] >= dist) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + fuel);
                }
            }
        }
        // 找到能到达目标位置的最小加油次数
        for (int j = 0; j <= n; j++) {
            if (dp[n][j] >= target) {
                return j;
            }
        }
        return -1;
    }
}

/*
class Solution {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        int n = stations.length;
        long[] dp = new long[n + 1];
        dp[0] = startFuel;
        for (int i = 0; i < n; i++) {
            for (int j = i; j >= 0; j--) {
                if (dp[j] >= stations[i][0]) {
                    dp[j + 1] = Math.max(dp[j + 1], dp[j] + stations[i][1]);
                }
            }
        }
        for (int i = 0; i <= n; i++) {
            if (dp[i] >= target) {
                return i;
            }
        }
        return -1;
    }
}
*/

/*
class Solution {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> b - a);
        int n = stations.length, idx = 0;
        int remain = startFuel, loc = 0, ans = 0;
        while (loc < target) {
            if (remain == 0) {
                if (!q.isEmpty() && ++ans >= 0) {
                    remain += q.poll();
                } else {
                    return -1;
                }
            }
            loc += remain;
            remain = 0;
            while (idx < n && stations[idx][0] <= loc) q.add(stations[idx++][1]);
        }
        return ans;
    }
}
*/
