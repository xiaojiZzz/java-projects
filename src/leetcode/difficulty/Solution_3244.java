package leetcode.difficulty;

/**
 * 给你一个整数 n 和一个二维整数数组 queries。
 * 有 n 个城市，编号从 0 到 n - 1。初始时，每个城市 i 都有一条单向道路通往城市 i + 1（ 0 <= i < n - 1）。
 * queries[i] = [ui, vi] 表示新建一条从城市 ui 到城市 vi 的单向道路。每次查询后，你需要找到从城市 0 到城市 n - 1 的最短路径的长度。
 * 所有查询中不会存在两个查询都满足 queries[i][0] < queries[j][0] < queries[i][1] < queries[j][1]。
 * 返回一个数组 answer，
 * 对于范围 [0, queries.length - 1] 中的每个 i，answer[i] 是处理完前 i + 1 个查询后，从城市 0 到城市 n - 1 的最短路径的长度。
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
 * 提示:
 * 3 <= n <= 105
 * 1 <= queries.length <= 105
 * queries[i].length == 2
 * 0 <= queries[i][0] < queries[i][1] < n
 * 1 < queries[i][1] - queries[i][0]
 * 查询中不存在重复的道路。
 * 不存在两个查询都满足 i != j 且 queries[i][0] < queries[j][0] < queries[i][1] < queries[j][1]。
 */
public class Solution_3244 {
    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        UnionFind uf = new UnionFind(n - 1);
        int[] ans = new int[queries.length];
        int cnt = n - 1; // 并查集连通块个数
        for (int qi = 0; qi < queries.length; qi++) {
            int l = queries[qi][0], r = queries[qi][1] - 1;
            int fr = uf.find(r);
            for (int i = uf.find(l); i < r; i = uf.find(i + 1)) {
                uf.fa[i] = fr;
                cnt--;
            }
            ans[qi] = cnt;
        }
        return ans;
    }

    class UnionFind {
        public final int[] fa;

        public UnionFind(int size) {
            fa = new int[size];
            for (int i = 1; i < size; i++) {
                fa[i] = i;
            }
        }

        public int find(int x) {
            if (fa[x] != x) {
                fa[x] = find(fa[x]);
            }
            return fa[x];
        }
    }
}

/*
class Solution {
    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        int[] nxt = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            nxt[i] = i + 1;
        }

        int[] ans = new int[queries.length];
        int cnt = n - 1;
        for (int qi = 0; qi < queries.length; qi++) {
            int l = queries[qi][0];
            int r = queries[qi][1];
            if (nxt[l] > 0 && nxt[l] < r) {
                for (int i = nxt[l]; i < r;) {
                    cnt--;
                    int tmp = nxt[i];
                    nxt[i] = 0;
                    i = tmp;
                }
                nxt[l] = r;
            }
            ans[qi] = cnt;
        }
        return ans;
    }
}
*/

/*
// 上面的另一种写法
class Solution {
    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        int[] nxt = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            nxt[i] = i + 1;
        }

        int[] ans = new int[queries.length];
        int cnt = n - 1;
        for (int qi = 0; qi < queries.length; qi++) {
            int i = queries[qi][0];
            int r = queries[qi][1];
            while (nxt[i] < r) {
                cnt--;
                int tmp = nxt[i];
                nxt[i] = r;
                i = tmp;
            }
            ans[qi] = cnt;
        }
        return ans;
    }
}
*/