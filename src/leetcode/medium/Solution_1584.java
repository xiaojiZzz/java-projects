package leetcode.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * 给你一个points 数组，表示 2D 平面上的一些点，其中 points[i] = [xi, yi] 。
 * 连接点 [xi, yi] 和点 [xj, yj] 的费用为它们之间的 曼哈顿距离 ：|xi - xj| + |yi - yj| ，其中 |val| 表示 val 的绝对值。
 * 请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有 一条简单路径时，才认为所有点都已连接。
 * 示例 1：
 * 输入：points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
 * 输出：20
 * 解释：
 * 我们可以按照上图所示连接所有点得到最小总费用，总费用为 20 。
 * 注意到任意两个点之间只有唯一一条路径互相到达。
 * 示例 2：
 * 输入：points = [[3,12],[-2,5],[-4,1]]
 * 输出：18
 * 示例 3：
 * 输入：points = [[0,0],[1,1],[1,0],[-1,1]]
 * 输出：4
 * 示例 4：
 * 输入：points = [[-1000000,-1000000],[1000000,1000000]]
 * 输出：4000000
 * 示例 5：
 * 输入：points = [[0,0]]
 * 输出：0
 */
public class Solution_1584 {
    //Kruskal
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                // 添加第i个点和第j个点组成的边
                edges.add(new Edge(i, j, Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1])));
            }
        }
        return new Kruskal(n, edges).getMST();
    }

    //并查集
    public class UnionFind {
        private int[] parent;
        private int[] height;
        private int count;

        public UnionFind(int n) {
            this.count = n;
            this.parent = new int[n];
            this.height = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                height[i] = 1;
            }
        }

        public int getCount() {
            return count;
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                if (height[rootX] > height[rootY]) {
                    parent[rootY] = rootX;
                } else if (height[rootX] < height[rootY]) {
                    parent[rootX] = rootY;
                } else {
                    parent[rootY] = rootX;
                    height[rootX]++;
                }
            }
        }

        public boolean isConnected(int x, int y) {
            return find(x) == find(y);
        }
    }

    public class Kruskal {
        private int n;
        private List<Edge> edges;
        private int minWeightSum = 0;

        public Kruskal(int n, List<Edge> edges) {
            this.n = n;
            this.edges = edges;
        }

        public int getMST() {
            UnionFind mst = new UnionFind(n);
            Collections.sort(edges, (a, b) -> a.val - b.val);
            for (Edge edge : edges) {
                int a = mst.find(edge.a);
                int b = mst.find(edge.b);
                if (a != b) {
                    mst.union(a, b);
                    minWeightSum += edge.val;
                }
            }
            return minWeightSum;
        }
    }

    private class Edge {
        int a;
        int b;
        int val;

        public Edge(int a, int b, int val) {
            this.a = a;
            this.b = b;
            this.val = val;
        }
    }
}

/*
class Solution {
    //Prim
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        if (n == 1) {
            return 0;
        }
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int distance = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                graph[i][j] = distance;
                graph[j][i] = distance;
            }
        }
        Prim prim = new Prim(graph);
        prim.getMST();
        return prim.minWeightSum;
    }

    class Prim {
        private int n;
        private boolean[] inMST;
        //graph: int[i][j] = weight, i -> j : weight
        private int[][] graph;
        private int minWeightSum = 0;

        public Prim(int[][] graph) {
            this.graph = graph;
            this.n = graph.length;
            this.inMST = new boolean[n];
        }

        public void getMST() {
            //更新未选中点到选中点的最短距离
            int[] distance = new int[n];
            Arrays.fill(distance, Integer.MAX_VALUE);
            inMST[0] = true;
            for (int i = 1; i < n; i++) {
                distance[i] = graph[i][0];
            }
            //选中的个数
            int num = 1;
            while (num < n) {
                int minIndex = 0;
                int minDistance = Integer.MAX_VALUE;
                //获取最短distance
                for (int i = 0; i < n; i++) {
                    if (!inMST[i] && minDistance > distance[i]) {
                        minDistance = distance[i];
                        minIndex = i;
                    }
                }
                minWeightSum += minDistance;
                inMST[minIndex] = true;
                num++;
                //更新distance
                for (int i = 0; i < n; i++) {
                    if (!inMST[i]) {
                        distance[i] = Math.min(distance[i], graph[i][minIndex]);
                    }
                }
            }
        }
    }
}
*/

/*
class Solution {
    public int minCostConnectPoints(int[][] points) {
        // 转化成无向图邻接表的形式
        List<int[]>[] graph = buildGraph(points);
        // 执行 Prim 算法
        Prim prim = new Prim(graph);
        return prim.minWeightSum;
    }

    // 转化成无向图邻接表的形式
    public List<int[]>[] buildGraph(int[][] points) {
        // 图中共有 n 个节点
        int n = points.length;
        List<int[]>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (i == j) {
                    continue;
                }
                int x1 = points[i][0], y1 = points[i][1];
                int x2 = points[j][0], y2 = points[j][1];
                int weight = Math.abs(x1 - x2) + Math.abs(y1 - y2);
                // 用 points 中的索引表示坐标点
                // 无向图其实就是双向图
                // 一条边表示为 int[]{from, to, weight}
                graph[i].add(new int[]{i, j, weight});
                graph[j].add(new int[]{j, i, weight});
            }
        }
        return graph;
    }

    class Prim {
        // 核心数据结构，存储横切边的优先级队列
        // 按照边的权重从小到大排序
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> (a[2] - b[2]));
        // 类似 visited 数组的作用，记录哪些节点已经成为最小生成树的一部分
        boolean[] inMST;
        // 记录最小生成树的权重和
        int minWeightSum = 0;
        // graph 是用邻接表表示的一幅图，
        // graph[s] 记录节点 s 所有相邻的边，
        // 三元组 int[]{from, to, weight} 表示一条边
        List<int[]>[] graph;

        public Prim(List<int[]>[] graph) {
            this.graph = graph;
            inMST = new boolean[graph.length];
            // 随便从一个点开始切分都可以，从节点 0 开始
            cut(0);
            inMST[0] = true;
            // 不断进行切分，向最小生成树中添加边
            while (!pq.isEmpty()) {
                int[] edge = pq.poll();
                int to = edge[1];
                int weight = edge[2];
                if (inMST[to]) {
                    // 节点 to 已经在最小生成树中，跳过
                    // 否则这条边会产生环
                    continue;
                }
                // 将边 edge 加入最小生成树
                // 节点 to 加入后，进行新一轮切分，会产生更多横切边
                cut(to);
                inMST[to] = true;
                minWeightSum += weight;
            }
        }

        // 将 s 的横切边加入优先队列
        public void cut(int v) {
            List<int[]> edges = graph[v];
            // 遍历 s 的邻边
            for (int[] edge : edges) {
                if (inMST[edge[1]]) {
                    // 相邻接点 to 已经在最小生成树中，跳过
                    // 否则这条边会产生环
                    continue;
                }
                // 加入横切边队列
                pq.add(edge);
            }
        }
    }
}
*/
