package leetcode.medium;

import java.util.ArrayList;


/**
 * 给你一个整数 n ，表示一张 无向图 中有 n 个节点，编号为 0 到 n - 1 。同时给你一个二维整数数组 edges ，
 * 其中 edges[i] = [ai, bi] 表示节点 ai 和 bi 之间有一条 无向 边。
 * 请你返回 无法互相到达 的不同 点对数目 。
 * 示例 1：
 * 输入：n = 3, edges = [[0,1],[0,2],[1,2]]
 * 输出：0
 * 解释：所有点都能互相到达，意味着没有点对无法互相到达，所以我们返回 0 。
 * 示例 2：
 * 输入：n = 7, edges = [[0,2],[0,5],[2,4],[1,6],[5,4]]
 * 输出：14
 * 解释：总共有 14 个点对互相无法到达：
 * [[0,1],[0,3],[0,6],[1,2],[1,3],[1,4],[1,5],[2,3],[2,6],[3,4],[3,5],[3,6],[4,6],[5,6]]
 * 所以我们返回 14 。
 */
public class Solution_2316 {
    public long countPairs(int n, int[][] edges) {
        ArrayList<Integer>[] arrayLists = new ArrayList[n];
        ArrayList<Integer> cnt = new ArrayList<>();
        boolean[] visited = new boolean[n];
        long result = 0;
        for (int i = 0; i < n; i++) {
            arrayLists[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            arrayLists[edge[0]].add(edge[1]);
            arrayLists[edge[1]].add(edge[0]);
        }
        for (int i = 0; i < n; i++) {
            if (visited[i] == false) {
                visited[i] = true;
                int count = count(i, arrayLists, visited);
                cnt.add(count);
            }
        }
        if (cnt.size() == 1) {
            return 0;
        }
        int size = cnt.size();
        int[] arr1 = new int[size];
        int[] arr2 = new int[size];
        for (int i = 0; i < size; i++) {
            arr1[i] = cnt.get(i);
        }
        arr2[size - 1] = arr1[size - 1];
        for (int i = size - 2; i > 0; i--) {
            arr2[i] = arr1[i] + arr2[i + 1];
        }
        for (int i = 0; i < size - 1; i++) {
            result = result + arr1[i] * arr2[i + 1];
        }
        return result;
    }

    private int count(int i, ArrayList<Integer>[] arrayLists, boolean[] visited) {
        int cnt = 1;
        ArrayList<Integer> arrayList = arrayLists[i];
        for (Integer integer : arrayList) {
            if (visited[integer] == false) {
                visited[integer] = true;
                cnt += count(integer, arrayLists, visited);
            }
        }
        return cnt;
    }
}

/*
class Solution {
    //优化求res
    public long countPairs(int n, int[][] edges) {
        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (int[] e : edges) {
            int x = e[0], y = e[1];
            g[x].add(y);
            g[y].add(x); // 建图
        }

        boolean[] vis = new boolean[n];
        long ans = 0;
        for (int i = 0, total = 0; i < n; i++) {
            if (!vis[i]) { // 未访问的点：说明找到了一个新的连通块
                int size = dfs(i, g, vis);
                ans += (long) size * total;
                total += size;
            }
        }
        return ans;
    }

    private int dfs(int x, List<Integer>[] g, boolean[] vis) {
        vis[x] = true; // 避免重复访问同一个点
        int size = 1;
        for (int y : g[x]) {
            if (!vis[y]) {
                size += dfs(y, g, vis);
            }
        }
        return size;
    }
}
*/

/*
class Solution {
    public long countPairs(int n, int[][] edges) {
        UF uf = new UF(n);
        for (int[] edge : edges) {
            uf.union(edge[0], edge[1]);
        }
        int[] size = uf.size();
        // 记录所有分支的大小
        List<Integer> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            // 找到节点 i 的根节点
            // 注意：只有每个连通分量的根节点的 size[] 才可以代表该连通分量中的节点数
            int p = uf.find(i);
            // 已经加入 list 的节点直接跳过
            if (!set.contains(p)) list.add(size[p]);
            set.add(p);
        }
        long ans = 0;
        // 计算结果
        for (int sz : list) ans += (long) sz * (n - sz);
        // 注意 ➗ 2
        return ans / 2;
    }
}
*/
/* ------------ 并查集模版 ------------ *//*

class UF {
    private int count;
    private int[] parent;
    private int[] size;
    public UF(int n) {
        this.count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return ;
        // 平衡性优化
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        } else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        this.count--;
    }
    public boolean connected(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        return rootP == rootQ;
    }
    public int count() {
        return this.count;
    }
    // 增加了一个函数
    // 返回 size[]
    public int[] size() {
        return this.size;
    }
    public int find(int x) {
        // 路径压缩
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
}
*/
