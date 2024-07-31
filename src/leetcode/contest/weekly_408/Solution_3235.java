package leetcode.contest.weekly_408;

/**
 * 判断矩形的两个角落是否可达
 * 给你两个正整数 X 和 Y 和一个二维整数数组 circles ，其中 circles[i] = [xi, yi, ri] 表示一个圆心在 (xi, yi) 半径为 ri 的圆。
 * 坐标平面内有一个左下角在原点，右上角在 (X, Y) 的矩形。
 * 你需要判断是否存在一条从左下角到右上角的路径满足：路径 完全 在矩形内部，不会 触碰或者经过 任何 圆的内部和边界，
 * 同时 只 在起点和终点接触到矩形。
 * 如果存在这样的路径，请你返回 true ，否则返回 false 。
 * 示例 1：
 * 输入：X = 3, Y = 4, circles = [[2,1,1]]
 * 输出：true
 * 解释：
 * 黑色曲线表示一条从 (0, 0) 到 (3, 4) 的路径。
 * 示例 2：
 * 输入：X = 3, Y = 3, circles = [[1,1,2]]
 * 输出：false
 * 解释：
 * 不存在从 (0, 0) 到 (3, 3) 的路径。
 * 示例 3：
 * 输入：X = 3, Y = 3, circles = [[2,1,1],[1,2,1]]
 * 输出：false
 * 解释：
 * 不存在从 (0, 0) 到 (3, 3) 的路径。
 * 提示：
 * 3 <= X, Y <= 109
 * 1 <= circles.length <= 1000
 * circles[i].length == 3
 * 1 <= xi, yi, ri <= 109
 */
public class Solution_3235 {

    class UnionFind {
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
    }

    // ps：这题判题案例都是默认圆心在矩形内部，所以按照这个条件进行解题
    public boolean canReachCorner(int X, int Y, int[][] circles) {
        int n = circles.length;
        UnionFind unionFind = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            long x = circles[i][0], y = circles[i][1], r = circles[i][2];
            for (int j = 0; j < n; j++) {
                long x2 = circles[j][0], y2 = circles[j][1], r2 = circles[j][2];
                if (Math.pow(x - x2, 2) + Math.pow(y - y2, 2) <= Math.pow(r + r2, 2)) {
                    unionFind.union(i, j);
                }
            }
        }
        int[] parent = unionFind.parent;
        for (int i = 0; i < n; i++) {
            int idx = parent[i];
            int lMin = circles[idx][0] - circles[idx][2], lMax = circles[idx][0] + circles[idx][2];
            int rMin = circles[idx][1] - circles[idx][2], rMax = circles[idx][1] + circles[idx][2];
            for (int j = 0; j < n; j++) {
                if (parent[j] == idx) {
                    lMin = Math.min(lMin, circles[j][0] - circles[j][2]);
                    lMax = Math.max(lMax, circles[j][0] + circles[j][2]);
                    rMin = Math.min(rMin, circles[j][1] - circles[j][2]);
                    rMax = Math.max(rMax, circles[j][1] + circles[j][2]);
                }
            }
            if ((lMin <= 0 && lMax >= X) || (rMin <= 0 && rMax >= Y) ||
                    (lMin <= X && lMax >= X && rMin <= Y && rMax >= Y) || (lMin <= 0 && rMin <= 0)) {
                return false;
            }
        }
        return true;
    }
}

class UnionFind {
    private final int[] fa;

    public UnionFind(int size) {
        fa = new int[size];
        for (int i = 1; i < size; i++) {
            fa[i] = i;
        }
    }

    public int find(int x) {
        if (fa[x] != x) {
            fa[x] = find(fa[x]);
        }
        return fa[x];
    }

    public void merge(int x, int y) {
        fa[find(x)] = find(y);
    }
}

/*
class Solution {
    public boolean canReachCorner(int x, int y, int[][] circles) {
        int n = circles.length;
        // 并查集中的 n 表示左边界或上边界，n+1 表示下边界或右边界
        UnionFind uf = new UnionFind(n + 2);
        for (int i = 0; i < n; i++) {
            int[] c = circles[i];
            int ox = c[0], oy = c[1], r = c[2];
            if (ox <= r || oy + r >= y) { // 圆 i 和左边界或上边界有交集
                uf.merge(i, n);
            }
            if (oy <= r || ox + r >= x) { // 圆 i 和下边界或右边界有交集
                uf.merge(i, n + 1);
            }
            for (int j = 0; j < i; j++) {
                int[] q = circles[j];
                if ((long) (ox - q[0]) * (ox - q[0]) + (long) (oy - q[1]) * (oy - q[1]) <= (long) (r + q[2]) * (r + q[2])) {
                    uf.merge(i, j); // 圆 i 和圆 j 有交集
                }
            }
            // 如果节点 n 和 n+1 在并查集的同一个连通块中，说明把从左下角到右上角的路给封死了，直接返回 false
            if (uf.find(n) == uf.find(n + 1)) { // 无法到达终点
                return false;
            }
        }
        return true;
    }
}
*/
