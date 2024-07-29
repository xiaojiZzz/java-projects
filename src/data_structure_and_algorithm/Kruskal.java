package data_structure_and_algorithm;

import java.util.Comparator;
import java.util.List;

// 可以用于求解最小生成树，以边为基础
public class Kruskal {
    private int n;
    private List<Edge> edges;
    private int minWeightSum = 0;

    public Kruskal(int n, List<Edge> edges) {
        this.n = n;
        this.edges = edges;
    }

    public int getMST() {
        // 利用并查集来组成 MST
        UnionFind mst = new UnionFind(n);
        edges.sort(Comparator.comparingInt(a -> a.val));
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
