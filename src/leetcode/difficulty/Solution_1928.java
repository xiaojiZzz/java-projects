package leetcode.difficulty;

import java.util.Arrays;

/**
 * 规定时间内到达终点的最小花费
 * 一个国家有 n 个城市，城市编号为 0 到 n - 1 ，题目保证 所有城市 都由双向道路 连接在一起 。道路由二维整数数组 edges 表示，
 * 其中 edges[i] = [xi, yi, timei] 表示城市 xi 和 yi 之间有一条双向道路，耗费时间为 timei 分钟。
 * 两个城市之间可能会有多条耗费时间不同的道路，但是不会有道路两头连接着同一座城市。
 * 每次经过一个城市时，你需要付通行费。通行费用一个长度为 n 且下标从 0 开始的整数数组 passingFees 表示，
 * 其中 passingFees[j] 是你经过城市 j 需要支付的费用。
 * 一开始，你在城市 0 ，你想要在 maxTime 分钟以内 （包含 maxTime 分钟）到达城市 n - 1 。
 * 旅行的 费用 为你经过的所有城市 通行费之和 （包括 起点和终点城市的通行费）。
 * 给你 maxTime，edges 和 passingFees ，请你返回完成旅行的 最小费用 ，如果无法在 maxTime 分钟以内完成旅行，请你返回 -1 。
 * 示例 1：
 * 输入：maxTime = 30, edges = [[0,1,10],[1,2,10],[2,5,10],[0,3,1],[3,4,10],[4,5,15]], passingFees = [5,1,2,20,20,3]
 * 输出：11
 * 解释：最优路径为 0 -> 1 -> 2 -> 5 ，总共需要耗费 30 分钟，需要支付 11 的通行费。
 * 示例 2：
 * 输入：maxTime = 29, edges = [[0,1,10],[1,2,10],[2,5,10],[0,3,1],[3,4,10],[4,5,15]], passingFees = [5,1,2,20,20,3]
 * 输出：48
 * 解释：最优路径为 0 -> 3 -> 4 -> 5 ，总共需要耗费 26 分钟，需要支付 48 的通行费。
 * 你不能选择路径 0 -> 1 -> 2 -> 5 ，因为这条路径耗费的时间太长。
 * 示例 3：
 * 输入：maxTime = 25, edges = [[0,1,10],[1,2,10],[2,5,10],[0,3,1],[3,4,10],[4,5,15]], passingFees = [5,1,2,20,20,3]
 * 输出：-1
 * 解释：无法在 25 分钟以内从城市 0 到达城市 5 。
 * 提示：
 * 1 <= maxTime <= 1000
 * n == passingFees.length
 * 2 <= n <= 1000
 * n - 1 <= edges.length <= 1000
 * 0 <= xi, yi <= n - 1
 * 1 <= timei <= 1000
 * 1 <= passingFees[j] <= 1000
 * 图中两个节点之间可能有多条路径。
 * 图中不含有自环。
 */
public class Solution_1928 {
    public int minCost(int maxTime, int[][] edges, int[] passingFees) {
        int n = passingFees.length;
        int[][] f = new int[maxTime + 1][n];
        for (int i = 0; i <= maxTime; i++) {
            Arrays.fill(f[i], Integer.MAX_VALUE / 2);
        }
        f[0][0] = passingFees[0];
        for (int t = 1; t <= maxTime; t++) {
            for (int[] edge : edges) {
                int i = edge[0], j = edge[1], time = edge[2];
                if (time <= t) {
                    f[t][i] = Math.min(f[t][i], f[t - time][j] + passingFees[i]);
                    f[t][j] = Math.min(f[t][j], f[t - time][i] + passingFees[j]);
                }
            }
        }
        int ans = Integer.MAX_VALUE / 2;
        for (int t = 1; t <= maxTime; t++) {
            ans = Math.min(ans, f[t][n - 1]);
        }
        return ans == Integer.MAX_VALUE / 2 ? -1 : ans;
    }
}

/*
class Solution {
    public int minCost(int maxTime, int[][] edges, int[] passingFees) {
        int n = passingFees.length;
        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            graph[e[0]].add(new int[]{e[1], e[2]});
            graph[e[1]].add(new int[]{e[0], e[2]});
        }
        // dist[x][y] 为从起点 0 到点 x,耗时为 y 的最小费用
        int[][] dist = new int[n][maxTime + 1];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE / 2);
        }
        dist[0][0] = passingFees[0];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[]{0, passingFees[0], 0});
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int currId = curr[0], currCost = curr[1], currDist = curr[2];
            if (currId == n - 1) return currCost;
            if (currDist > maxTime) continue;
            for (int[] neighbor : graph[currId]) {
                int nextId = neighbor[0], time = neighbor[1];
                int nextCost = currCost + passingFees[nextId], nextDist = currDist + time;
                if (nextDist <= maxTime && nextCost < dist[nextId][nextDist]) {
                    // for 循环优化之后不必要的入队操作
                    for (int i = nextDist; i <= maxTime; i++) {
                        if (dist[nextId][i] > nextCost) {
                            dist[nextId][i] = nextCost;
                        } else {
                            break;
                        }
                    }
                    pq.add(new int[]{nextId, nextCost, nextDist});
                }
            }
        }
        return -1;
    }
}
*/