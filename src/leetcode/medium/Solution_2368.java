package leetcode.medium;

import java.util.*;


/**
 * 现有一棵由 n 个节点组成的无向树，节点编号从 0 到 n - 1 ，共有 n - 1 条边。
 * 给你一个二维整数数组 edges ，长度为 n - 1 ，其中 edges[i] = [ai, bi] 表示树中节点 ai 和 bi 之间存在一条边。
 * 另给你一个整数数组 restricted 表示 受限 节点。
 * 在不访问受限节点的前提下，返回你可以从节点 0 到达的 最多 节点数目。
 * 注意，节点 0 不 会标记为受限节点。
 * 示例 1：
 * 输入：n = 7, edges = [[0,1],[1,2],[3,1],[4,0],[0,5],[5,6]], restricted = [4,5]
 * 输出：4
 * 解释：上图所示正是这棵树。
 * 在不访问受限节点的前提下，只有节点 [0,1,2,3] 可以从节点 0 到达。
 * 示例 2：
 * 输入：n = 7, edges = [[0,1],[0,2],[0,5],[0,4],[3,2],[6,5]], restricted = [4,2,1]
 * 输出：3
 * 解释：上图所示正是这棵树。
 * 在不访问受限节点的前提下，只有节点 [0,5,6] 可以从节点 0 到达。
 */
public class Solution_2368 {
    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        List<Integer>[] lists = new ArrayList[n];
        Arrays.setAll(lists, v -> new ArrayList<>());
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1];
            lists[x].add(y);
            lists[y].add(x);
        }
        Set<Integer> set = new HashSet<>();
        for (int i : restricted) {
            set.add(i);
        }
        List<Integer> list = new ArrayList<>();
        dfs(lists, list, 0, -1, set);
        return list.size();
    }

    public void dfs(List<Integer>[] lists, List<Integer> list, int x, int fa, Set<Integer> restricted) {
        list.add(x);
        for (Integer y : lists[x]) {
            if (y != fa && !restricted.contains(y)) {
                dfs(lists, list, y, x, restricted);
            }
        }
    }
}

/*
class Solution {
    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        boolean[] isrestricted = new boolean[n];
        for (int x : restricted) {
            isrestricted[x] = true;
        }

        UnionFind uf = new UnionFind(n);
        for (int[] v : edges) {
            if (isrestricted[v[0]] || isrestricted[v[1]]) {
                continue;
            }
            uf.merge(v[0], v[1]);
        }
        return uf.count();
    }
}

class UnionFind {
    private int[] f;
    private int[] rank;

    public UnionFind(int n) {
        f = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            f[i] = i;
        }
    }

    public void merge(int x, int y) {
        int rx = find(x);
        int ry = find(y);
        if (rx != ry) {
            if (rank[rx] > rank[ry]) {
                f[ry] = rx;
            } else if (rank[rx] < rank[ry]) {
                f[rx] = ry;
            } else {
                f[ry] = rx;
                rank[rx]++;
            }
        }
    }

    public int find(int x) {
        if (x != f[x]) {
            x = find(f[x]);
        }
        return f[x];
    }

    public int count() {
        int cnt = 0;
        int rt = find(0);
        for (int i = 0; i < f.length; i++) {
            if (rt == find(i)) {
                cnt++;
            }
        }
        return cnt;
    }
}
*/
