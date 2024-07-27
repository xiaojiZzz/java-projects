package leetcode.difficulty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 给你一张 无向 图，图中有 n 个节点，节点编号从 0 到 n - 1 （都包括）。同时给你一个下标从 0 开始的整数数组 values ，
 * 其中 values[i] 是第 i 个节点的 价值 。同时给你一个下标从 0 开始的二维整数数组 edges ，其中 edges[j] = [uj, vj, timej] 表示
 * 节点 uj 和 vj 之间有一条需要 timej 秒才能通过的无向边。最后，给你一个整数 maxTime 。
 * 合法路径 指的是图中任意一条从节点 0 开始，最终回到节点 0 ，且花费的总时间 不超过 maxTime 秒的一条路径。
 * 你可以访问一个节点任意次。一条合法路径的 价值 定义为路径中 不同节点 的价值 之和 （每个节点的价值 至多 算入价值总和中一次）。
 * 请你返回一条合法路径的 最大 价值。
 * 注意：每个节点 至多 有 四条 边与之相连。
 * 示例 1：
 * 输入：values = [0,32,10,43], edges = [[0,1,10],[1,2,15],[0,3,10]], maxTime = 49
 * 输出：75
 * 解释：
 * 一条可能的路径为：0 -> 1 -> 0 -> 3 -> 0 。总花费时间为 10 + 10 + 10 + 10 = 40 <= 49 。
 * 访问过的节点为 0 ，1 和 3 ，最大路径价值为 0 + 32 + 43 = 75 。
 * 示例 2：
 * 输入：values = [5,10,15,20], edges = [[0,1,10],[1,2,10],[0,3,10]], maxTime = 30
 * 输出：25
 * 解释：
 * 一条可能的路径为：0 -> 3 -> 0 。总花费时间为 10 + 10 = 20 <= 30 。
 * 访问过的节点为 0 和 3 ，最大路径价值为 5 + 20 = 25 。
 * 示例 3：
 * 输入：values = [1,2,3,4], edges = [[0,1,10],[1,2,11],[2,3,12],[1,3,13]], maxTime = 50
 * 输出：7
 * 解释：
 * 一条可能的路径为：0 -> 1 -> 3 -> 1 -> 0 。总花费时间为 10 + 13 + 13 + 10 = 46 <= 50 。
 * 访问过的节点为 0 ，1 和 3 ，最大路径价值为 1 + 2 + 4 = 7 。
 * 示例 4：
 * 输入：values = [0,1,2], edges = [[1,2,10]], maxTime = 10
 * 输出：0
 * 解释：
 * 唯一一条路径为 0 。总花费时间为 0 。
 * 唯一访问过的节点为 0 ，最大路径价值为 0 。
 */
public class Solution_2065 {

    private int ans = 0;

    public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
        int n = values.length;
        int[] visit = new int[n];
        List<int[]>[] g = new ArrayList[n];
        Arrays.setAll(g, v -> new ArrayList<>());
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1], t = edge[2];
            g[x].add(new int[]{y, t});
            g[y].add(new int[]{x, t});
        }
        int val = values[0];
        ans = values[0];
        visit[0]++;
        for (int[] ints : g[0]) {
            int y = ints[0], t = ints[1];
            dfs(g, values, visit, val, y, maxTime - t);
        }
        return ans;
    }

    private void dfs(List<int[]>[] g, int[] values, int[] visit, int val, int des, int maxTime) {
        if (maxTime < 0) {
            return;
        }
        if (des == 0) {
            ans = Math.max(ans, val);
        }
        visit[des]++;
        val += visit[des] == 1 ? values[des] : 0;
        for (int[] ints : g[des]) {
            int y = ints[0], t = ints[1];
            dfs(g, values, visit, val, y, maxTime - t);
        }
        visit[des]--;
    }
}
