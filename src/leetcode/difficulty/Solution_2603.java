package leetcode.difficulty;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 给你一个 n 个节点的无向无根树，节点编号从 0 到 n - 1 。给你整数 n 和一个长度为 n - 1 的二维整数数组 edges ，
 * 其中 edges[i] = [ai, bi] 表示树中节点 ai 和 bi 之间有一条边。再给你一个长度为 n 的数组 coins ，
 * 其中 coins[i] 可能为 0 也可能为 1 ，1 表示节点 i 处有一个金币。
 * 一开始，你需要选择树中任意一个节点出发。你可以执行下述操作任意次：
 * 收集距离当前节点距离为 2 以内的所有金币，或者
 * 移动到树中一个相邻节点。
 * 你需要收集树中所有的金币，并且回到出发节点，请你返回最少经过的边数。
 * 如果你多次经过一条边，每一次经过都会给答案加一。
 * 示例 1：
 * 输入：coins = [1,0,0,0,0,1], edges = [[0,1],[1,2],[2,3],[3,4],[4,5]]
 * 输出：2
 * 解释：从节点 2 出发，收集节点 0 处的金币，移动到节点 3 ，收集节点 5 处的金币，然后移动回节点 2 。
 * 示例 2：
 * 输入：coins = [0,0,0,1,1,0,0,1], edges = [[0,1],[0,2],[1,3],[1,4],[2,5],[5,6],[5,7]]
 * 输出：2
 * 解释：从节点 0 出发，收集节点 4 和 3 处的金币，移动到节点 2 处，收集节点 7 处的金币，移动回节点 0 。
 */
public class Solution_2603 {
    public int collectTheCoins(int[] coins, int[][] edges) {
        int n = coins.length;
        List<Integer> g[] = new ArrayList[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        var deg = new int[n];
        for (var e : edges) {
            int x = e[0], y = e[1];
            g[x].add(y);
            g[y].add(x); // 建图
            deg[x]++;
            deg[y]++; // 统计每个节点的度数（邻居个数）
        }

        int leftEdges = n - 1; // 剩余边数
        // 拓扑排序，去掉没有金币的子树
        var q = new ArrayDeque<Integer>();
        for (int i = 0; i < n; i++) {
            if (deg[i] == 1 && coins[i] == 0) { // 没有金币的叶子
                q.add(i);
            }
        }
        while (!q.isEmpty()) {
            leftEdges--; // 删除节点到其父节点的边
            for (int y : g[q.poll()]) {
                if (--deg[y] == 1 && coins[y] == 0) { // 没有金币的叶子
                    q.add(y);
                }
            }
        }

        // 再次拓扑排序
        for (int i = 0; i < n; i++) {
            if (deg[i] == 1 && coins[i] == 1) { // 有金币的叶子（判断 coins[i] 是避免把没有金币的叶子也算进来）
                q.add(i);
            }
        }
        leftEdges -= q.size(); // 删除所有叶子（到其父节点的边）
        for (int x : q) { // 遍历所有叶子
            for (int y : g[x]) {
                if (--deg[y] == 1) { // y 现在是叶子了
                    leftEdges--; // 删除 y（到其父节点的边）
                }
            }
        }
        return Math.max(leftEdges * 2, 0);
    }
}

/*
class Solution {
    public int collectTheCoins(int[] coins, int[][] edges) {
        int n = coins.length;
        List<Integer>[] g = new List[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new ArrayList<Integer>();
        }
        int[] degree = new int[n];
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1];
            g[x].add(y);
            g[y].add(x);
            ++degree[x];
            ++degree[y];
        }

        int rest = n;
        */
/* 删除树中所有无金币的叶子节点，直到树中所有的叶子节点都是含有金币的 *//*

        Queue<Integer> queue = new ArrayDeque<Integer>();
        for (int i = 0; i < n; ++i) {
            if (degree[i] == 1 && coins[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int u = queue.poll();
            --degree[u];
            --rest;
            for (int v : g[u]) {
                --degree[v];
                if (degree[v] == 1 && coins[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        */
/* 删除树中所有的叶子节点, 连续删除2次 *//*

        for (int x = 0; x < 2; ++x) {
            queue = new ArrayDeque<Integer>();
            for (int i = 0; i < n; ++i) {
                if (degree[i] == 1) {
                    queue.offer(i);
                }
            }
            while (!queue.isEmpty()) {
                int u = queue.poll();
                --degree[u];
                --rest;
                for (int v : g[u]) {
                    --degree[v];
                }
            }
        }

        return rest == 0 ? 0 : (rest - 1) * 2;
    }
}
*/

/*
class Solution {

    int[] gCoins;
    List<List<Integer>> g = new ArrayList<>();
    int[] gCost;
    int[][] dp;

    int[] dfs(int u, int fa) {
        int c0 = gCoins[u];
        int c1 = 0, c2 = 0;

        List<Integer> es = g.get(u);

        int h = 0;
        for (int v : es) {
            if (v == fa) continue;
            int[] cres = dfs(v, u);

            c1 += cres[0];
            c2 += cres[1];

            if (gCost[v] > 0) {
                h += gCost[v] + 2;
            } else if (cres[2] > 0) {
                h += 2;
            }
        }

        dp[u] = new int[]{c0, c1, c2};
        gCost[u] = h;
        return dp[u];
    }


    int gAns = Integer.MAX_VALUE;

    // *) 继续换根处理
    void dfs2(int u, int fa, int h, int[] path) {

        List<Integer> es = g.get(u);

        int nh = 0;
        if (h > 0) {
            nh = gCost[u] + h + 2;
            gAns = Math.min(gAns, gCost[u] + h + 2);
        } else {
            nh = gCost[u] + ((path[2] > 0) ? 2 : 0);
            gAns = Math.min(gAns, gCost[u] + ((path[2] > 0) ? 2 : 0));
        }

        int[] npath = new int[]{
                dp[u][0], dp[u][1] + path[0], dp[u][2] + path[1]
        };


        for (int v : es) {
            if (v == fa) continue;

            int[] cdp = dp[v];
            int cnh = nh;
            if (gCost[v] > 0) {
                cnh -= gCost[v] + 2;
            } else if (cdp[2] > 0) {
                cnh -= 2;
            }

            int[] cnpath = new int[]{
                    npath[0], npath[1] - cdp[0], npath[2] - cdp[1]
            };

            dfs2(v, u, cnh, cnpath);
        }

    }

    public int collectTheCoins(int[] coins, int[][] edges) {

        int n = coins.length;
        for (int i = 0; i < n; i++) {
            g.add(new ArrayList<>());
        }
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            g.get(u).add(v);
            g.get(v).add(u);
        }

        this.gCoins = coins;
        this.dp = new int[n][4];
        gCost = new int[n];

        dfs(0, -1);
        dfs2(0, -1, 0, new int[]{0, 0, 0});

        return gAns;
    }
}
*/
