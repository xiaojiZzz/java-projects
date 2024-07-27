package leetcode.difficulty;

import java.util.Arrays;

/**
 * 关闭分部的可行集合数目
 * 一个公司在全国有 n 个分部，它们之间有的有道路连接。一开始，所有分部通过这些道路两两之间互相可以到达。
 * 公司意识到在分部之间旅行花费了太多时间，所以它们决定关闭一些分部（也可能不关闭任何分部），
 * 同时保证剩下的分部之间两两互相可以到达且最远距离不超过 maxDistance 。
 * 两个分部之间的 距离 是通过道路长度之和的 最小值 。
 * 给你整数 n ，maxDistance 和下标从 0 开始的二维整数数组 roads ，
 * 其中 roads[i] = [ui, vi, wi] 表示一条从 ui 到 vi 长度为 wi的 无向 道路。
 * 请你返回关闭分部的可行方案数目，满足每个方案里剩余分部之间的最远距离不超过 maxDistance。
 * 注意，关闭一个分部后，与之相连的所有道路不可通行。
 * 注意，两个分部之间可能会有多条道路。
 * 示例 1：
 * 输入：n = 3, maxDistance = 5, roads = [[0,1,2],[1,2,10],[0,2,10]]
 * 输出：5
 * 解释：可行的关闭分部方案有：
 * - 关闭分部集合 [2] ，剩余分部为 [0,1] ，它们之间的距离为 2 。
 * - 关闭分部集合 [0,1] ，剩余分部为 [2] 。
 * - 关闭分部集合 [1,2] ，剩余分部为 [0] 。
 * - 关闭分部集合 [0,2] ，剩余分部为 [1] 。
 * - 关闭分部集合 [0,1,2] ，关闭后没有剩余分部。
 * 总共有 5 种可行的关闭方案。
 * 示例 2：
 * 输入：n = 3, maxDistance = 5, roads = [[0,1,20],[0,1,10],[1,2,2],[0,2,2]]
 * 输出：7
 * 解释：可行的关闭分部方案有：
 * - 关闭分部集合 [] ，剩余分部为 [0,1,2] ，它们之间的最远距离为 4 。
 * - 关闭分部集合 [0] ，剩余分部为 [1,2] ，它们之间的距离为 2 。
 * - 关闭分部集合 [1] ，剩余分部为 [0,2] ，它们之间的距离为 2 。
 * - 关闭分部集合 [0,1] ，剩余分部为 [2] 。
 * - 关闭分部集合 [1,2] ，剩余分部为 [0] 。
 * - 关闭分部集合 [0,2] ，剩余分部为 [1] 。
 * - 关闭分部集合 [0,1,2] ，关闭后没有剩余分部。
 * 总共有 7 种可行的关闭方案。
 * 示例 3：
 * 输入：n = 1, maxDistance = 10, roads = []
 * 输出：2
 * 解释：可行的关闭分部方案有：
 * - 关闭分部集合 [] ，剩余分部为 [0] 。
 * - 关闭分部集合 [0] ，关闭后没有剩余分部。
 * 总共有 2 种可行的关闭方案。
 * 提示：
 * 1 <= n <= 10
 * 1 <= maxDistance <= 105
 * 0 <= roads.length <= 1000
 * roads[i].length == 3
 * 0 <= ui, vi <= n - 1
 * ui != vi
 * 1 <= wi <= 1000
 * 一开始所有分部之间通过道路互相可以到达。
 */
public class Solution_2959 {
    public int numberOfSets(int n, int maxDistance, int[][] roads) {
        int[][] g = new int[n][n];
        for (int[] ints : g) {
            Arrays.fill(ints, Integer.MAX_VALUE / 2);
        }
        for (int[] road : roads) {
            int u = road[0], v = road[1], w = road[2];
            g[u][v] = g[v][u] = Math.min(g[u][v], w);
            g[u][u] = g[v][v] = 0;
        }
        int ans = 0;
        for (int i = 0; i < (1 << n); i++) {
            boolean[] removed = new boolean[n];
            for (int j = 0; j < n; j++) {
                if (((i >> j) & 1) == 1) {
                    removed[j] = true;
                }
            }
            int[][] graphCopy = new int[n][n];
            for (int j = 0; j < n; j++) {
                System.arraycopy(g[j], 0, graphCopy[j], 0, n);
            }
            if (new Floyd(graphCopy, removed, maxDistance).floyd()) {
                ans++;
            }
        }
        return ans;
    }

    class Floyd {
        private int n;
        private int[][] graph;
        boolean[] removed;
        int maxDistance;

        public Floyd(int[][] graph, boolean[] removed, int maxDistance) {
            this.n = graph.length;
            this.graph = graph;
            this.removed = removed;
            this.maxDistance = maxDistance;
        }

        public boolean floyd() {
            for (int k = 0; k < n; k++) {
                if (removed[k]) {
                    continue;
                }
                for (int i = 0; i < n; i++) {
                    if (removed[i]) {
                        continue;
                    }
                    for (int j = 0; j < n; j++) {
                        if (removed[j]) {
                            continue;
                        }
                        if (graph[i][j] > graph[i][k] + graph[k][j]) {
                            graph[i][j] = graph[i][k] + graph[k][j];
                        }
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (!removed[i] && !removed[j] && graph[i][j] > maxDistance) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
}

/*
class Solution {
    // 状压 dp
    public int numberOfSets(int n, int maxDistance, int[][] roads) {
        int[][] g = new int[n][n];
        for (int[] row : g) {
            Arrays.fill(row, Integer.MAX_VALUE / 2);
        }
        for (int[] e : roads) {
            int x = e[0];
            int y = e[1];
            int wt = e[2];
            g[x][y] = Math.min(g[x][y], wt);
            g[y][x] = Math.min(g[y][x], wt);
        }

        int ans = 1; // s=0 一定满足要求
        int[][][] f = new int[1 << n][n][n];
        for (int[][] matrix : f) {
            for (int[] row : matrix) {
                Arrays.fill(row, Integer.MAX_VALUE / 2);
            }
        }
        f[0] = g;
        for (int s = 1; s < (1 << n); s++) {
            int k = Integer.numberOfTrailingZeros(s);
            int t = s ^ (1 << k);
            boolean ok = true;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    f[s][i][j] = Math.min(f[t][i][j], f[t][i][k] + f[t][k][j]);
                    if (ok && j < i && (s >> i & 1) != 0 && (s >> j & 1) != 0 && f[s][i][j] > maxDistance) {
                        ok = false;
                    }
                }
            }
            ans += ok ? 1 : 0;
        }
        return ans;
    }
}
*/
