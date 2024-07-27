package leetcode.medium;

import java.util.Arrays;


/**
 * 给你一个二维整数数组 ranges ，其中 ranges[i] = [starti, endi] 表示 starti 到 endi 之间（包括二者）的所有整数都包含在第 i 个区间中。
 * 你需要将 ranges 分成 两个 组（可以为空），满足：
 * 每个区间只属于一个组。
 * 两个有 交集 的区间必须在 同一个 组内。
 * 如果两个区间有至少 一个 公共整数，那么这两个区间是 有交集 的。
 * 比方说，区间 [1, 3] 和 [2, 5] 有交集，因为 2 和 3 在两个区间中都被包含。
 * 请你返回将 ranges 划分成两个组的 总方案数 。由于答案可能很大，将它对 109 + 7 取余 后返回。
 * 示例 1：
 * 输入：ranges = [[6,10],[5,15]]
 * 输出：2
 * 解释：
 * 两个区间有交集，所以它们必须在同一个组内。
 * 所以有两种方案：
 * - 将两个区间都放在第 1 个组中。
 * - 将两个区间都放在第 2 个组中。
 * 示例 2：
 * 输入：ranges = [[1,3],[10,20],[2,5],[4,8]]
 * 输出：4
 * 解释：
 * 区间 [1,3] 和 [2,5] 有交集，所以它们必须在同一个组中。
 * 同理，区间 [2,5] 和 [4,8] 也有交集，所以它们也必须在同一个组中。
 * 所以总共有 4 种分组方案：
 * - 所有区间都在第 1 组。
 * - 所有区间都在第 2 组。
 * - 区间 [1,3] ，[2,5] 和 [4,8] 在第 1 个组中，[10,20] 在第 2 个组中。
 * - 区间 [1,3] ，[2,5] 和 [4,8] 在第 2 个组中，[10,20] 在第 1 个组中。
 */
public class Solution_2580 {

    static final int MOD = 1000000007;

    public int countWays(int[][] ranges) {
        Arrays.sort(ranges, (a, b) -> a[0] - b[0]);
        int n = ranges.length;
        int res = 1;
        for (int i = 0; i < n; ) {
            int r = ranges[i][1];
            int j = i + 1;
            while (j < n && ranges[j][0] <= r) {
                r = Math.max(r, ranges[j][1]);
                j++;
            }
            res = res * 2 % MOD;
            i = j;
        }
        return res;
    }
}

/*
class Solution {
    // 并查集超时
    private static int size;

    public int countWays(int[][] ranges) {
        int mode = 1000000007;
        int n = ranges.length;
        int[] parent = new int[n];
        int[] height = new int[n];
        size = n;
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            height[i] = 1;
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                union(ranges, parent, height, i, j);
            }
        }
        long ans = 1;
        for (int i = 0; i < size; i++) {
            ans *= 2;
            ans %= mode;
        }
        return (int) ans;
    }

    public int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }

    public void union(int[][] ranges, int[] parent, int[] height, int x, int y) {
        int x1 = ranges[x][0], x2 = ranges[x][1];
        int y1 = ranges[y][0], y2 = ranges[y][1];
        if (!((x1 >= y1 && x1 <= y2) || (y1 >= x1 && y1 <= x2))) {
            return;
        }
        int rootX = find(parent, x);
        int rootY = find(parent, y);
        if (rootX != rootY) {
            if (height[rootX] > height[rootY]) {
                parent[rootY] = rootX;
            } else if (height[rootX] < height[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                height[rootX]++;
            }
            size--;
        }
    }
}
*/
