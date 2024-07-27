package leetcode.medium;

import java.util.ArrayList;
import java.util.List;


/**
 * n 座城市，从 0 到 n-1 编号，其间共有 n-1 条路线。因此，要想在两座不同城市之间旅行只有唯一一条路线可供选择（路线网形成一颗树）。
 * 去年，交通运输部决定重新规划路线，以改变交通拥堵的状况。
 * 路线用 connections 表示，其中 connections[i] = [a, b] 表示从城市 a 到 b 的一条有向路线。
 * 今年，城市 0 将会举办一场大型比赛，很多游客都想前往城市 0 。
 * 请你帮助重新规划路线方向，使每个城市都可以访问城市 0 。返回需要变更方向的最小路线数。
 * 题目数据 保证 每个城市在重新规划路线方向后都能到达城市 0 。
 * 示例 1：
 * 输入：n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
 * 输出：3
 * 解释：更改以红色显示的路线的方向，使每个城市都可以到达城市 0 。
 * 示例 2：
 * 输入：n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
 * 输出：2
 * 解释：更改以红色显示的路线的方向，使每个城市都可以到达城市 0 。
 * 示例 3：
 * 输入：n = 3, connections = [[1,0],[2,0]]
 * 输出：0
 */
public class Solution_1466 {
    //dfs
    public int minReorder(int n, int[][] connections) {
        List<int[]>[] e = new List[n];
        for (int i = 0; i < n; i++) {
            e[i] = new ArrayList<int[]>();
        }
        for (int[] edge : connections) {
            e[edge[0]].add(new int[]{edge[1], 1});
            e[edge[1]].add(new int[]{edge[0], 0});
        }
        return dfs(0, -1, e);
    }

    public int dfs(int x, int parent, List<int[]>[] e) {
        int res = 0;
        for (int[] edge : e[x]) {
            if (edge[0] == parent) {
                continue;
            }
            res += edge[1] + dfs(edge[0], x, e);
        }
        return res;
    }
}

/*
class Solution {
    private int count; // 变更方向的路线数

    public int minReorder(int n, int[][] connections) {
        // 构建有向图
        List<List<Integer>> graph = buildGraph(n, connections);
        // 记录节点的访问状态
        boolean[] visited = new boolean[n];
        // 从节点0开始深度优先搜索
        dfs(graph, visited, 0);
        // 返回变更方向的路线数
        return count;
    }

    private void dfs(List<List<Integer>> graph, boolean[] visited, int city) {
        // 标记当前节点为已访问
        visited[city] = true;
        for (int neighbor : graph.get(city)) {
            // 如果邻居节点未被访问
            if (!visited[Math.abs(neighbor)]) {
                // 这里就体现出来有向的特性了,我们这个def方法,是从城市0的方向往外走的,因为整个结构是树(哪怕是多叉树,也不会有环的存在),因此从任意一个城市到达另一个城市,经过的城市是唯一的
                // ,如果neighbor>0,就说过没办法绕过其他城市抵达目标城市,必须要改路,因此count++
                if (neighbor > 0) {
                    // 需要变更方向的路线数增加
                    count++;
                }
                // 继续深度优先搜索邻居节点
                dfs(graph, visited, Math.abs(neighbor));
            }
        }
    }

    private List<List<Integer>> buildGraph(int n, int[][] connections) {
        // 用邻接表表示有向图
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            // 初始化每个节点的邻居列表
            graph.add(new ArrayList<>());
        }
        for (int[] connection : connections) {
            int from = connection[0];
            int to = connection[1];
            // 添加正向路线
            graph.get(from).add(to);
            // 这里有个取巧的地方,添加反向路线，使用负数表示
            graph.get(to).add(-from);
        }
        return graph;
    }
}
*/

/*
class Solution {
    //bfs
    public int minReorder(int n, int[][] connections) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] uv : connections) {
            graph.get(uv[0]).add(new int[] {uv[1], 1});
            graph.get(uv[1]).add(new int[] {uv[0], 0});
        }

        int res = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        boolean[] visited = new boolean[n];
        visited[0] = true;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int[] v : graph.get(u)) {
                if (visited[v[0]]) {
                    continue;
                }
                visited[v[0]] = true;
                res += v[1];
                queue.offer(v[0]);
            }
        }

        return res;
    }
}
*/
