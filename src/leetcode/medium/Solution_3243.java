package leetcode.medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 新增道路查询后的最短距离 I
 * 给你一个整数 n 和一个二维整数数组 queries。
 * 有 n 个城市，编号从 0 到 n - 1。初始时，每个城市 i 都有一条单向道路通往城市 i + 1（ 0 <= i < n - 1）。
 * queries[i] = [ui, vi] 表示新建一条从城市 ui 到城市 vi 的单向道路。每次查询后，你需要找到从城市 0 到城市 n - 1 的最短路径的长度。
 * 返回一个数组 answer，对于范围 [0, queries.length - 1] 中的每个 i，
 * answer[i] 是处理完前 i + 1 个查询后，从城市 0 到城市 n - 1 的最短路径的长度。
 * 示例 1：
 * 输入： n = 5, queries = [[2, 4], [0, 2], [0, 4]]
 * 输出： [3, 2, 1]
 * 解释：
 * 新增一条从 2 到 4 的道路后，从 0 到 4 的最短路径长度为 3。
 * 新增一条从 0 到 2 的道路后，从 0 到 4 的最短路径长度为 2。
 * 新增一条从 0 到 4 的道路后，从 0 到 4 的最短路径长度为 1。
 * 示例 2：
 * 输入： n = 4, queries = [[0, 3], [0, 2]]
 * 输出： [1, 1]
 * 解释：
 * 新增一条从 0 到 3 的道路后，从 0 到 3 的最短路径长度为 1。
 * 新增一条从 0 到 2 的道路后，从 0 到 3 的最短路径长度仍为 1。
 * 提示：
 * 3 <= n <= 500
 * 1 <= queries.length <= 500
 * queries[i].length == 2
 * 0 <= queries[i][0] < queries[i][1] < n
 * 1 < queries[i][1] - queries[i][0]
 * 查询中没有重复的道路。
 */
public class Solution_3243 {
    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        List<Integer>[] adjacentArr = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjacentArr[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            adjacentArr[i].add(i + 1);
        }
        int queriesCount = queries.length;
        int[] answer = new int[queriesCount];
        for (int i = 0; i < queriesCount; i++) {
            int u = queries[i][0], v = queries[i][1];
            adjacentArr[u].add(v);
            answer[i] = bfs(adjacentArr, n);
        }
        return answer;
    }

    public int bfs(List<Integer>[] adjacentArr, int n) {
        boolean[] visited = new boolean[n];
        visited[0] = true;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(0);
        int steps = -1;
        int distance = -1;
        while (!queue.isEmpty() && distance < 0) {
            steps++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int city = queue.poll();
                if (city == n - 1) {
                    distance = steps;
                }
                List<Integer> adjacent = adjacentArr[city];
                for (int next : adjacent) {
                    if (!visited[next]) {
                        visited[next] = true;
                        queue.offer(next);
                    }
                }
            }
        }
        return steps;
    }
}

/*
class Solution {
    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        int m = queries.length;
        int[] ans = new int[m];

        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            if (i < n - 1) {
                graph[i].add(new int[]{i + 1, 1});
            }
        }

        for (int i = 0; i < m; i++) {
            int x = queries[i][0], y = queries[i][1];
            graph[x].add(new int[]{y, 1});
            ans[i] = dijkstra(graph, n, n - 1);
        }

        return ans;
    }

    private int dijkstra(List<int[]>[] graph, int n, int end) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE / 2);
        dist[0] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{0, 0});

        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            int u = node[0];
            int d = node[1];

            if (d > dist[u]) {
                continue;
            }

            for (int[] neighbor : graph[u]) {
                int v = neighbor[0];
                int weight = neighbor[1];
                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.add(new int[]{v, dist[v]});
                }
            }
        }

        return dist[end] == Integer.MAX_VALUE / 2 ? -1 : dist[end];
    }
}
*/

/*
class Solution {
    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        List<Integer>[] from = new ArrayList[n];
        Arrays.setAll(from, i -> new ArrayList<>());
        int[] f = new int[n];
        for (int i = 1; i < n; i++) {
            f[i] = i;
        }

        int[] ans = new int[queries.length];
        for (int qi = 0; qi < queries.length; qi++) {
            int l = queries[qi][0];
            int r = queries[qi][1];
            from[r].add(l);
            if (f[l] + 1 < f[r]) {
                f[r] = f[l] + 1;
                for (int i = r + 1; i < n; i++) {
                    f[i] = Math.min(f[i], f[i - 1] + 1);
                    for (int j : from[i]) {
                        f[i] = Math.min(f[i], f[j] + 1);
                    }
                }
            }
            ans[qi] = f[n - 1];
        }
        return ans;
    }
}
*/
