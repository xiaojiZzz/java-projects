package leetcode.medium;

import java.util.*;

/**
 * 网络延迟时间
 * 有 n 个网络节点，标记为 1 到 n。
 * 给你一个列表 times，表示信号经过 有向 边的传递时间。
 * times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点， wi 是一个信号从源节点传递到目标节点的时间。
 * 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。
 * 示例 1：
 * 输入：times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
 * 输出：2
 * 示例 2：
 * 输入：times = [[1,2,1]], n = 2, k = 1
 * 输出：1
 * 示例 3：
 * 输入：times = [[1,2,1]], n = 2, k = 2
 * 输出：-1
 * 提示：
 * 1 <= k <= n <= 100
 * 1 <= times.length <= 6000
 * times[i].length == 3
 * 1 <= ui, vi <= n
 * ui != vi
 * 0 <= wi <= 100
 * 所有 (ui, vi) 对都 互不相同（即，不含重复边）
 */
public class Solution_743 {
    public int networkDelayTime(int[][] times, int n, int k) {
        int[][] graph = new int[n + 1][n + 1];
        for (int[] ints : graph) {
            Arrays.fill(ints, Integer.MAX_VALUE / 2);
        }
        for (int[] time : times) {
            int u = time[0], v = time[1], w = time[2];
            graph[u][v] = w;
        }
        int[][] d = dijkstra(k, n + 1, graph);
        int[] dis = d[0];
        int[] path = d[1];
        for (int i = 1; i <= n; i++) {
            if (path[i] == 0) {
                return -1;
            }
        }
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, dis[i]);
        }
        return ans;
    }

    public int[][] dijkstra(int x, int n, int[][] graph) {
        boolean[] visited = new boolean[n];
        int[] dist = new int[n];
        int[] path = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE / 2); // 防止溢出
        // 到起点的最短路径为0
        dist[x] = 0;
        // 没有前驱节点，设为 -1
        path[x] = -1;
        // 有多少点就迭代多少次
        for (int i = 1; i < n; i++) {
            // 每次找到未更新点中最短距离最小的点 t
            int t = -1;
            for (int j = 1; j < n; j++) {
                if (!visited[j] && (t == -1 || dist[j] < dist[t])) {
                    t = j;
                }
            }
            visited[t] = true;
            // 用点 t 的最小距离来更新其他未更新的点
            for (int k = 1; k < n; k++) {
                if (!visited[k]) {
                    if (dist[k] > dist[t] + graph[t][k]) {
                        dist[k] = dist[t] + graph[t][k];
                        path[k] = t;
                    }
                }
            }
        }
        return new int[][]{dist, path};
    }
}

/*
class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        int[][] d = dijkstra(k, n + 1, times);
        int[] dis = d[0];
        int[] path = d[1];
        for (int i = 1; i <= n; i++) {
            if (path[i] == 0) {
                return -1;
            }
        }
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, dis[i]);
        }
        return ans;
    }

    public int[][] dijkstra(int x, int n, int[][] edges) {
        List<int[]>[] lists = new ArrayList[n];
        Arrays.setAll(lists, v -> new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], length = edge[2];
            lists[u].add(new int[]{v, length});
        }
        int[] dist = new int[n];
        int[] path = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE / 2); // 防止溢出
        // 到起点的最短路径为0
        dist[x] = 0;
        // 没有前驱节点，设为 -1
        path[x] = -1;
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        // 到源点 x 的距离为 0
        priorityQueue.offer(new int[]{x, 0});
        while (!priorityQueue.isEmpty()) {
            int[] poll = priorityQueue.poll();
            int u = poll[0], dis = poll[1];
            // 之前出过堆，且到源点的距离更长了，直接跳过
            if (dis > dist[u]) {
                continue;
            }
            for (int[] ints : lists[u]) {
                int v = ints[0];
                int newDis = dis + ints[1];
                if (newDis < dist[v]) {
                    dist[v] = newDis;
                    path[v] = u;
                    priorityQueue.offer(new int[]{v, newDis});
                }
            }
        }
        return new int[][]{dist, path};
    }
}
*/
