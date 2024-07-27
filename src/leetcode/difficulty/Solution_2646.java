package leetcode.difficulty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 现有一棵无向、无根的树，树中有 n 个节点，按从 0 到 n - 1 编号。
 * 给你一个整数 n 和一个长度为 n - 1 的二维整数数组 edges ，其中 edges[i] = [ai, bi] 表示树中节点 ai 和 bi 之间存在一条边。
 * 每个节点都关联一个价格。给你一个整数数组 price ，其中 price[i] 是第 i 个节点的价格。
 * 给定路径的 价格总和 是该路径上所有节点的价格之和。
 * 另给你一个二维整数数组 trips，其中 trips[i] = [starti, endi] 表示您从节点 starti 开始第 i 次旅行，并通过任何你喜欢的路径前往节点 endi 。
 * 在执行第一次旅行之前，你可以选择一些 非相邻节点 并将价格减半。
 * 返回执行所有旅行的最小价格总和。
 * 示例 1：
 * 输入：n = 4, edges = [[0,1],[1,2],[1,3]], price = [2,2,10,6], trips = [[0,3],[2,1],[2,3]]
 * 输出：23
 * 解释：
 * 上图表示将节点 2 视为根之后的树结构。第一个图表示初始树，第二个图表示选择节点 0 、2 和 3 并使其价格减半后的树。
 * 第 1 次旅行，选择路径 [0,1,3] 。路径的价格总和为 1 + 2 + 3 = 6 。
 * 第 2 次旅行，选择路径 [2,1] 。路径的价格总和为 2 + 5 = 7 。
 * 第 3 次旅行，选择路径 [2,1,3] 。路径的价格总和为 5 + 2 + 3 = 10 。
 * 所有旅行的价格总和为 6 + 7 + 10 = 23 。可以证明，23 是可以实现的最小答案。
 * 示例 2：
 * 输入：n = 2, edges = [[0,1]], price = [2,2], trips = [[0,0]]
 * 输出：1
 * 解释：
 * 上图表示将节点 0 视为根之后的树结构。第一个图表示初始树，第二个图表示选择节点 0 并使其价格减半后的树。
 * 第 1 次旅行，选择路径 [0] 。路径的价格总和为 1 。
 * 所有旅行的价格总和为 1 。可以证明，1 是可以实现的最小答案。
 */
public class Solution_2646 {
    private List<Integer>[] g;
    private int[] price, cnt;
    private int end;

    public int minimumTotalPrice(int n, int[][] edges, int[] price, int[][] trips) {
        g = new ArrayList[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (int[] e : edges) {
            int x = e[0], y = e[1];
            g[x].add(y);
            g[y].add(x);
        }

        cnt = new int[n];
        for (int[] t : trips) {
            end = t[1];
            dfs(t[0], -1);
        }

        this.price = price;
        int[] res = dp(0, -1);
        return Math.min(res[0], res[1]);
    }

    private boolean dfs(int x, int fa) {
        if (x == end) {
            cnt[x]++;
            return true; // 找到 end
        }
        for (int y : g[x]) {
            if (y != fa && dfs(y, x)) {
                cnt[x]++; // x 是 end 的祖先节点，也就在路径上
                return true;
            }
        }
        return false; // 未找到 end
    }

    // 类似 337. 打家劫舍 III
    private int[] dp(int x, int fa) {
        int notHalve = price[x] * cnt[x]; // x 不变
        int halve = notHalve / 2; // x 减半
        for (int y : g[x]) {
            if (y != fa) {
                int[] res = dp(y, x); // 计算 y 不变/减半的最小价值总和
                notHalve += Math.min(res[0], res[1]); // x 不变，那么 y 可以不变，可以减半，取这两种情况的最小值
                halve += res[0]; // x 减半，那么 y 只能不变
            }
        }
        return new int[]{notHalve, halve};
    }
}
