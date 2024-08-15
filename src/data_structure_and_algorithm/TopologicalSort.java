package data_structure_and_algorithm;

import java.util.*;

// 拓扑排序
public class TopologicalSort {
    private int n;
    // edges[i] = [ai, bi]: ai -> bi
    private int[][] edges;
    // 拓扑排序结果
    private int[] result;

    public TopologicalSort(int n, int[][] edges) {
        this.n = n;
        this.edges = edges;
        result = new int[n];
    }

    public int[] topologicalSort() {
        // 入度
        int[] indeg = new int[n];
        List<Integer>[] edgeLists = new ArrayList[n];
        Arrays.setAll(edgeLists, v -> new ArrayList<>());
        for (int[] edge : edges) {
            edgeLists[edge[0]].add(edge[1]);
            indeg[edge[1]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indeg[i] == 0) {
                queue.offer(i);
            }
        }
        int index = 0;
        while (!queue.isEmpty()) {
            int v = queue.poll();
            result[index++] = v;
            for (int u : edgeLists[v]) {
                indeg[u]--;
                if (indeg[u] == 0) {
                    queue.offer(u);
                }
            }
        }
        return result;
    }
}
