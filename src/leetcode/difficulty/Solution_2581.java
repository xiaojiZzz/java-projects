package leetcode.difficulty;

import java.util.*;


/**
 * Alice 有一棵 n 个节点的树，节点编号为 0 到 n - 1 。
 * 树用一个长度为 n - 1 的二维整数数组 edges 表示，其中 edges[i] = [ai, bi] ，表示树中节点 ai 和 bi 之间有一条边。
 * Alice 想要 Bob 找到这棵树的根。她允许 Bob 对这棵树进行若干次 猜测 。每一次猜测，Bob 做如下事情：
 * 选择两个 不相等 的整数 u 和 v ，且树中必须存在边 [u, v] 。
 * Bob 猜测树中 u 是 v 的 父节点 。
 * Bob 的猜测用二维整数数组 guesses 表示，其中 guesses[j] = [uj, vj] 表示 Bob 猜 uj 是 vj 的父节点。
 * Alice 非常懒，她不想逐个回答 Bob 的猜测，只告诉 Bob 这些猜测里面 至少 有 k 个猜测的结果为 true 。
 * 给你二维整数数组 edges ，Bob 的所有猜测和整数 k ，请你返回可能成为树根的 节点数目 。如果没有这样的树，则返回 0。
 * 示例 1：
 * 输入：edges = [[0,1],[1,2],[1,3],[4,2]], guesses = [[1,3],[0,1],[1,0],[2,4]], k = 3
 * 输出：3
 * 解释：
 * 根为节点 0 ，正确的猜测为 [1,3], [0,1], [2,4]
 * 根为节点 1 ，正确的猜测为 [1,3], [1,0], [2,4]
 * 根为节点 2 ，正确的猜测为 [1,3], [1,0], [2,4]
 * 根为节点 3 ，正确的猜测为 [1,0], [2,4]
 * 根为节点 4 ，正确的猜测为 [1,3], [1,0]
 * 节点 0 ，1 或 2 为根时，可以得到 3 个正确的猜测。
 * 示例 2：
 * 输入：edges = [[0,1],[1,2],[2,3],[3,4]], guesses = [[1,0],[3,4],[2,1],[3,2]], k = 1
 * 输出：5
 * 解释：
 * 根为节点 0 ，正确的猜测为 [3,4]
 * 根为节点 1 ，正确的猜测为 [1,0], [3,4]
 * 根为节点 2 ，正确的猜测为 [1,0], [2,1], [3,4]
 * 根为节点 3 ，正确的猜测为 [1,0], [2,1], [3,2], [3,4]
 * 根为节点 4 ，正确的猜测为 [1,0], [2,1], [3,2]
 * 任何节点为根，都至少有 1 个正确的猜测。
 */
public class Solution_2581 {
    private List<Integer>[] g;
    private Set<Long> set = new HashSet<>();
    private int ans, cnt0, k;

    public int rootCount(int[][] edges, int[][] guesses, int k) {
        this.k = k;
        g = new ArrayList[edges.length + 1];
        Arrays.setAll(g, v -> new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            g[u].add(v);
            g[v].add(u);
        }
        for (int[] guess : guesses) {
            int x = guess[0], y = guess[1];
            set.add((long) x << 32 | y);
        }
        dfs(0, -1);
        count(0, -1, cnt0);
        return ans;
    }

    private void dfs(int x, int fa) {
        for (Integer y : g[x]) {
            if (y != fa) {
                if (set.contains((long) x << 32 | y)) {
                    cnt0++;
                }
                dfs(y, x);
            }
        }
    }

    private void count(int x, int fa, int cnt) {
        if (cnt >= k) {
            ans++;
        }
        for (Integer y : g[x]) {
            int c = cnt;
            if (y != fa) {
                if (set.contains((long) x << 32 | y)) {
                    c--;
                }
                if (set.contains((long) y << 32 | x)) {
                    c++;
                }
                count(y, x, c);
            }
        }
    }
}
