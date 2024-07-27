package data_structure_and_algorithm;

@SuppressWarnings({"all"})
//并查集
public class UnionFind {
    private int[] parent;
    private int[] height;
    private int count;

    public UnionFind(int n) {
        this.parent = new int[n];
        this.height = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            height[i] = 1;
        }
        this.count = n;
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
            count--;
        }
    }

    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }
}
