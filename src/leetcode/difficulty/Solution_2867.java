package leetcode.difficulty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 给你一棵 n 个节点的无向树，节点编号为 1 到 n 。
 * 给你一个整数 n 和一个长度为 n - 1 的二维整数数组 edges ，其中 edges[i] = [ui, vi] 表示节点 ui 和 vi 在树中有一条边。
 * 请你返回树中的 合法路径数目 。
 * 如果在节点 a 到节点 b 之间 恰好有一个 节点的编号是质数，那么我们称路径 (a, b) 是 合法的 。
 * 注意：
 * 路径 (a, b) 指的是一条从节点 a 开始到节点 b 结束的一个节点序列，序列中的节点 互不相同 ，且相邻节点之间在树上有一条边。
 * 路径 (a, b) 和路径 (b, a) 视为 同一条 路径，且只计入答案 一次 。
 * 示例 1：
 * 输入：n = 5, edges = [[1,2],[1,3],[2,4],[2,5]]
 * 输出：4
 * 解释：恰好有一个质数编号的节点路径有：
 * - (1, 2) 因为路径 1 到 2 只包含一个质数 2 。
 * - (1, 3) 因为路径 1 到 3 只包含一个质数 3 。
 * - (1, 4) 因为路径 1 到 4 只包含一个质数 2 。
 * - (2, 4) 因为路径 2 到 4 只包含一个质数 2 。
 * 只有 4 条合法路径。
 * 示例 2：
 * 输入：n = 6, edges = [[1,2],[1,3],[2,4],[3,5],[3,6]]
 * 输出：6
 * 解释：恰好有一个质数编号的节点路径有：
 * - (1, 2) 因为路径 1 到 2 只包含一个质数 2 。
 * - (1, 3) 因为路径 1 到 3 只包含一个质数 3 。
 * - (1, 4) 因为路径 1 到 4 只包含一个质数 2 。
 * - (1, 6) 因为路径 1 到 6 只包含一个质数 3 。
 * - (2, 4) 因为路径 2 到 4 只包含一个质数 2 。
 * - (3, 6) 因为路径 3 到 6 只包含一个质数 3 。
 * 只有 6 条合法路径。
 */
public class Solution_2867 {
    private final static int MX = (int) 1e5;
    private final static boolean[] np = new boolean[MX + 1]; // 质数=false 非质数=true

    static {
        np[1] = true;
        for (int i = 2; i * i <= MX; i++) {
            if (!np[i]) {
                for (int j = i * i; j <= MX; j += i) {
                    np[j] = true;
                }
            }
        }
    }

    public long countPaths(int n, int[][] edges) {
        List<Integer>[] g = new ArrayList[n + 1];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (var e : edges) {
            int x = e[0], y = e[1];
            g[x].add(y);
            g[y].add(x);
        }

        long ans = 0;
        int[] size = new int[n + 1];
        var nodes = new ArrayList<Integer>();
        for (int x = 1; x <= n; x++) {
            if (np[x]) { // 跳过非质数
                continue;
            }
            int sum = 0;
            for (int y : g[x]) { // 质数 x 把这棵树分成了若干个连通块
                if (!np[y]) {
                    continue;
                }
                if (size[y] == 0) { // 尚未计算过
                    nodes.clear();
                    dfs(y, -1, g, nodes); // 遍历 y 所在连通块，在不经过质数的前提下，统计有多少个非质数
                    for (int z : nodes) {
                        size[z] = nodes.size();
                    }
                }
                // 这 size[y] 个非质数与之前遍历到的 sum 个非质数，两两之间的路径只包含质数 x
                ans += (long) size[y] * sum;
                sum += size[y];
            }
            ans += sum; // 从 x 出发的路径
        }
        return ans;
    }

    private void dfs(int x, int fa, List<Integer>[] g, List<Integer> nodes) {
        nodes.add(x);
        for (int y : g[x]) {
            if (y != fa && np[y]) {
                dfs(y, x, g, nodes);
            }
        }
    }
}

/*
class PrimeTable {
    private final boolean[] prime;

    public PrimeTable(int n) {
        prime = new boolean[n + 1];
        Arrays.fill(prime, true);
        prime[0] = false;
        prime[1] = false;
        for (int i = 2; i <= n; ++i) {
            if (prime[i]) {
                for (int j = i + i; j <= n; j += i) {
                    prime[j] = false;
                }
            }
        }
    }

    public boolean isPrime(int x) {
        return prime[x];
    }
}

class UnionFind {
    private final int[] p;
    private final int[] size;

    public UnionFind(int n) {
        p = new int[n];
        size = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
            size[i] = 1;
        }
    }

    public int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    public boolean union(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa == pb) {
            return false;
        }
        if (size[pa] > size[pb]) {
            p[pb] = pa;
            size[pa] += size[pb];
        } else {
            p[pa] = pb;
            size[pb] += size[pa];
        }
        return true;
    }

    public int size(int x) {
        return size[find(x)];
    }
}

class Solution {
    private static final PrimeTable PT = new PrimeTable(100010);

    public long countPaths(int n, int[][] edges) {
        List<Integer>[] g = new List[n + 1];
        Arrays.setAll(g, i -> new ArrayList<>());
        UnionFind uf = new UnionFind(n + 1);
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            g[u].add(v);
            g[v].add(u);
            if (!PT.isPrime(u) && !PT.isPrime(v)) {
                uf.union(u, v);
            }
        }
        long ans = 0;
        for (int i = 1; i <= n; ++i) {
            if (PT.isPrime(i)) {
                long t = 0;
                for (int j : g[i]) {
                    if (!PT.isPrime(j)) {
                        long cnt = uf.size(j);
                        ans += cnt * t;
                        t += cnt;
                    }
                }
                ans += t;
            }
        }
        return ans;
    }
}
*/
