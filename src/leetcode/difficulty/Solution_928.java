package leetcode.difficulty;

import java.util.*;


/**
 * 给定一个由 n 个节点组成的网络，用 n x n 个邻接矩阵 graph 表示。在节点网络中，
 * 只有当 graph[i][j] = 1 时，节点 i 能够直接连接到另一个节点 j。
 * 一些节点 initial 最初被恶意软件感染。只要两个节点直接连接，且其中至少一个节点受到恶意软件的感染，那么两个节点都将被恶意软件感染。
 * 这种恶意软件的传播将继续，直到没有更多的节点可以被这种方式感染。
 * 假设 M(initial) 是在恶意软件停止传播之后，整个网络中感染恶意软件的最终节点数。
 * 我们可以从 initial 中删除一个节点，并完全移除该节点以及从该节点到任何其他节点的任何连接。
 * 请返回移除后能够使 M(initial) 最小化的节点。如果有多个节点满足条件，返回索引 最小的节点 。
 * 示例 1：
 * 输入：graph = [[1,1,0],[1,1,0],[0,0,1]], initial = [0,1]
 * 输出：0
 * 示例 2：
 * 输入：graph = [[1,1,0],[1,1,1],[0,1,1]], initial = [0,1]
 * 输出：1
 * 示例 3：
 * 输入：graph = [[1,1,0,0],[1,1,1,0],[0,1,1,1],[0,0,1,1]], initial = [0,1]
 * 输出：1
 */
public class Solution_928 {
    public int minMalwareSpread(int[][] graph, int[] initial) {
        int N = graph.length;
        int[] clean = new int[N];
        Arrays.fill(clean, 1);
        for (int x : initial)
            clean[x] = 0;

        ArrayList<Integer>[] infectedBy = new ArrayList[N];
        for (int i = 0; i < N; ++i)
            infectedBy[i] = new ArrayList();

        for (int u : initial) {
            Set<Integer> seen = new HashSet();
            dfs(graph, clean, u, seen);
            for (int v : seen)
                infectedBy[v].add(u);
        }

        int[] contribution = new int[N];
        for (int v = 0; v < N; ++v)
            if (infectedBy[v].size() == 1)
                contribution[infectedBy[v].get(0)]++;

        Arrays.sort(initial);
        int ans = initial[0], ansSize = -1;
        for (int u : initial) {
            int score = contribution[u];
            if (score > ansSize) {
                ans = u;
                ansSize = score;
            }
        }
        return ans;
    }

    public void dfs(int[][] graph, int[] clean, int u, Set<Integer> seen) {
        for (int v = 0; v < graph.length; ++v)
            if (graph[u][v] == 1 && clean[v] == 1 && !seen.contains(v)) {
                seen.add(v);
                dfs(graph, clean, v, seen);
            }
    }
}

/*
class Solution {
    public int minMalwareSpread(int[][] graph, int[] initial) {
        int N = graph.length;
        DSU dsu = new DSU(N);

        int[] clean = new int[N];
        Arrays.fill(clean, 1);
        for (int x : initial) clean[x] = 0;

        for (int u = 0; u < N; ++u)
            if (clean[u] == 1)
                for (int v = u + 1; v < N; ++v)
                    if (clean[v] == 1)
                        if (graph[u][v] == 1)
                            dsu.union(u, v);

        int[] count = new int[N];
        Map<Integer, Set<Integer>> nodeToCompo = new HashMap();
        for (int u : initial) {
            Set<Integer> components = new HashSet();
            for (int v = 0; v < N; ++v)
                if (clean[v] == 1) {
                    if (graph[u][v] == 1)
                        components.add(dsu.find(v));
                }

            nodeToCompo.put(u, components);
            for (int c : components)
                count[c]++;
        }
        Arrays.sort(initial);
        int ans = -1, ansSize = -1;
        for (int u : initial) {
            Set<Integer> components = nodeToCompo.get(u);
            int score = 0;
            for (int c : components)
                if (count[c] == 1)
                    score += dsu.size(c);

            if (score > ansSize) {
                ansSize = score;
                ans = u;
            }
        }
        return ans;
    }
}


class DSU {
    int[] p, sz;

    DSU(int N) {
        p = new int[N];
        for (int x = 0; x < N; ++x)
            p[x] = x;

        sz = new int[N];
        Arrays.fill(sz, 1);
    }

    public int find(int x) {
        if (p[x] != x)
            p[x] = find(p[x]);
        return p[x];
    }

    public void union(int x, int y) {
        int xr = find(x);
        int yr = find(y);
        p[xr] = yr;
        sz[yr] += sz[xr];
    }

    public int size(int x) {
        return sz[find(x)];
    }
}
*/
