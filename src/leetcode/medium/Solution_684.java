package leetcode.medium;

/**
 * 冗余连接
 * 树可以看成是一个连通且 无环 的 无向 图。
 * 给定往一棵 n 个节点 (节点值 1～n) 的树中添加一条边后的图。添加的边的两个顶点包含在 1 到 n 中间，且这条附加的边不属于树中已存在的边。
 * 图的信息记录于长度为 n 的二维数组 edges ，edges[i] = [ai, bi] 表示图中在 ai 和 bi 之间存在一条边。
 * 请找出一条可以删去的边，删除后可使得剩余部分是一个有着 n 个节点的树。如果有多个答案，则返回数组 edges 中最后出现的那个。
 * 示例 1：
 * 输入: edges = [[1,2], [1,3], [2,3]]
 * 输出: [2,3]
 * 示例 2：
 * 输入: edges = [[1,2], [2,3], [3,4], [1,4], [1,5]]
 * 输出: [1,4]
 * 提示:
 * n == edges.length
 * 3 <= n <= 1000
 * edges[i].length == 2
 * 1 <= ai < bi <= edges.length
 * ai != bi
 * edges 中无重复元素
 * 给定的图是连通的
 */
public class Solution_684 {

    private int[] parent;
    private int[] height;

    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        parent = new int[n + 1];
        height = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
            height[i] = 1;
        }
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1];
            if (isConnected(x, y)) {
                return edge;
            }
            union(x, y);
        }
        return new int[]{};
    }

    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    private void union(int x, int y) {
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

    private boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }
}
