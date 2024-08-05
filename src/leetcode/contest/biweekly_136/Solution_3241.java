package leetcode.contest.biweekly_136;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_3241 {
    public int[] timeTaken(int[][] edges) {
        List<Integer>[] g = new ArrayList[edges.length + 1];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] e : edges) {
            int x = e[0], y = e[1];
            g[x].add(y);
            g[y].add(x);
        }

        // nodes[x] 保存子树 x 的最大深度 maxD，次大深度 maxD2，以及最大深度要往儿子 my 走
        int[][] nodes = new int[g.length][3];
        dfs(0, -1, g, nodes);

        int[] ans = new int[g.length];
        reRoot(0, -1, 0, g, nodes, ans);
        return ans;
    }

    private int dfs(int x, int fa, List<Integer>[] g, int[][] nodes) {
        int maxD = 0, maxD2 = 0, my = 0;
        for (int y : g[x]) {
            if (y == fa) {
                continue;
            }
            int depth = dfs(y, x, g, nodes) + (y + 1) % 2 + 1; // 从 x 出发，往 my 方向的最大深度
            if (depth > maxD) {
                maxD2 = maxD;
                maxD = depth;
                my = y;
            } else if (depth > maxD2) {
                maxD2 = depth;
            }
        }
        nodes[x][0] = maxD;
        nodes[x][1] = maxD2;
        nodes[x][2] = my;
        return maxD;
    }

    private void reRoot(int x, int fa, int fromUp, List<Integer>[] g, int[][] nodes, int[] ans) {
        int maxD = nodes[x][0], maxD2 = nodes[x][1], my = nodes[x][2];
        ans[x] = Math.max(fromUp, maxD);
        for (int y : g[x]) {
            if (y != fa) {
                reRoot(y, x, Math.max(fromUp, (y == my ? maxD2 : maxD)) + (x + 1) % 2 + 1, g, nodes, ans);
            }
        }
    }
}
