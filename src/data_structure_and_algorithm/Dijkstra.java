package data_structure_and_algorithm;

import java.util.*;

// 可用于求解单源最短路径问题 不能解决带负权值的图和带负权回路的图
// 朴素 Dijkstra 适用于稠密图 时间复杂度 o(n^2) n 为节点数
public class Dijkstra {
    private int n;
    // graph: int[i][j] = weight, i -> j : weight
    private int[][] graph;

    public Dijkstra(int[][] graph) {
        this.graph = graph;
        this.n = graph.length;
    }

    // 返回 dist[] 单源最短路径 + path[] 前驱点
    public int[][] dijkstra(int x) {
        boolean[] visited = new boolean[n];
        int[] dist = new int[n];
        int[] path = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE / 2); // 防止溢出
        // 到起点的最短路径为0
        dist[x] = 0;
        // 没有前驱节点，设为 -1
        path[x] = -1;
        // 有多少点就迭代多少次
        for (int i = 0; i < n; i++) {
            // 每次找到未更新点中最短距离最小的点 t
            int t = -1;
            for (int j = 0; j < n; j++) {
                if (!visited[j] && (t == -1 || dist[j] < dist[t])) {
                    t = j;
                }
            }
            visited[t] = true;
            // 用点 t 的最小距离来更新其他未更新的点
            for (int k = 0; k < n; k++) {
                if (!visited[k]) {
                    if (dist[k] > dist[t] + graph[t][k]) {
                        dist[k] = dist[t] + graph[t][k];
                        path[k] = t;
                    }
                }
            }
        }
        return new int[][]{dist, path};
    }
}

// 堆优化的 Dijkstra 算法，适用于稀疏图，时间复杂度为 o(eloge) e 为边数
class DijkstraUseHeap {
    private int n;
    // edges[i] = [ui, vi, lengthi]
    private int[][] edges;

    public DijkstraUseHeap(int n, int[][] edges) {
        this.n = n;
        this.edges = edges;
    }

    // 返回 dist[] 单源最短路径 + path[] 前驱点
    public int[][] dijkstra(int x) {
        List<int[]>[] lists = new ArrayList[n];
        Arrays.setAll(lists, v -> new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], length = edge[2];
            lists[u].add(new int[]{v, length});
            // 这里用无向图表示
            lists[v].add(new int[]{u, length});
        }
        int[] dist = new int[n];
        int[] path = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE / 2); // 防止溢出
        // 到起点的最短路径为0
        dist[x] = 0;
        // 没有前驱节点，设为 -1
        path[x] = -1;
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        // 到源点 x 的距离为 0
        priorityQueue.offer(new int[]{x, 0});
        while (!priorityQueue.isEmpty()) {
            int[] poll = priorityQueue.poll();
            int u = poll[0], dis = poll[1];
            // 之前出过堆，且到源点的距离更长了，直接跳过
            if (dis > dist[u]) {
                continue;
            }
            for (int[] ints : lists[u]) {
                int v = ints[0];
                int newDis = dis + ints[1];
                if (newDis < dist[v]) {
                    dist[v] = newDis;
                    path[v] = u;
                    priorityQueue.offer(new int[]{v, newDis});
                }
            }
        }
        return new int[][]{dist, path};
    }
}