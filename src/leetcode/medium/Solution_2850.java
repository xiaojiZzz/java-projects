package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 将石头分散到网格图的最少移动次数
 * 给你一个大小为 3 * 3 ，下标从 0 开始的二维整数矩阵 grid ，分别表示每一个格子里石头的数目。
 * 网格图中总共恰好有 9 个石头，一个格子里可能会有 多个 石头。
 * 每一次操作中，你可以将一个石头从它当前所在格子移动到一个至少有一条公共边的相邻格子。
 * 请你返回每个格子恰好有一个石头的 最少移动次数 。
 * 示例 1：
 * 输入：grid = [[1,1,0],[1,1,1],[1,2,1]]
 * 输出：3
 * 解释：让每个格子都有一个石头的一个操作序列为：
 * 1 - 将一个石头从格子 (2,1) 移动到 (2,2) 。
 * 2 - 将一个石头从格子 (2,2) 移动到 (1,2) 。
 * 3 - 将一个石头从格子 (1,2) 移动到 (0,2) 。
 * 总共需要 3 次操作让每个格子都有一个石头。
 * 让每个格子都有一个石头的最少操作次数为 3 。
 * 示例 2：
 * 输入：grid = [[1,3,0],[1,0,0],[1,0,3]]
 * 输出：4
 * 解释：让每个格子都有一个石头的一个操作序列为：
 * 1 - 将一个石头从格子 (0,1) 移动到 (0,2) 。
 * 2 - 将一个石头从格子 (0,1) 移动到 (1,1) 。
 * 3 - 将一个石头从格子 (2,2) 移动到 (1,2) 。
 * 4 - 将一个石头从格子 (2,2) 移动到 (2,1) 。
 * 总共需要 4 次操作让每个格子都有一个石头。
 * 让每个格子都有一个石头的最少操作次数为 4 。
 * 提示：
 * grid.length == grid[i].length == 3
 * 0 <= grid[i][j] <= 9
 * grid 中元素之和为 9 。
 */
public class Solution_2850 {
    public int minimumMoves(int[][] grid) {
        int n = grid.length;
        List<int[]> from = new ArrayList<>();
        List<int[]> to = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 1) {
                    from.add(new int[]{i, j});
                } else if (grid[i][j] == 0) {
                    to.add(new int[]{i, j});
                }
            }
        }
        return dfs(grid, from, to, 0, 0);
    }

    private int dfs(int[][] grid, List<int[]> from, List<int[]> to, int idx, int sum) {
        if (idx == to.size()) {
            return sum;
        }
        int ans = Integer.MAX_VALUE;
        int[] arr = to.get(idx);
        for (int[] ints : from) {
            int i = ints[0], j = ints[1];
            if (grid[i][j] > 1) {
                grid[i][j]--;
                int tmpSum = sum + Math.abs(arr[0] - i) + Math.abs(arr[1] - j);
                ans = Math.min(ans, dfs(grid, from, to, idx + 1, tmpSum));
                grid[i][j]++;
            }
        }
        return ans;
    }
}

/*
class Solution {
    // 全排列
    public int minimumMoves(int[][] grid) {
        List<int[]> from = new ArrayList<>();
        List<int[]> to = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] > 1) {
                    for (int k = 1; k < grid[i][j]; k++) {
                        from.add(new int[]{i, j});
                    }
                } else if (grid[i][j] == 0) {
                    to.add(new int[]{i, j});
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for (List<int[]> from2 : permutations(from)) {
            int total = 0;
            for (int i = 0; i < from2.size(); i++) {
                int[] f = from2.get(i);
                int[] t = to.get(i);
                total += Math.abs(f[0] - t[0]) + Math.abs(f[1] - t[1]);
            }
            ans = Math.min(ans, total);
        }
        return ans;
    }

    private List<List<int[]>> permutations(List<int[]> arr) {
        List<List<int[]>> result = new ArrayList<>();
        permute(arr, 0, result);
        return result;
    }

    private void permute(List<int[]> arr, int start, List<List<int[]>> result) {
        if (start == arr.size()) {
            result.add(new ArrayList<>(arr));
        }
        for (int i = start; i < arr.size(); i++) {
            swap(arr, start, i);
            permute(arr, start + 1, result);
            swap(arr, start, i);
        }
    }

    private void swap(List<int[]> arr, int i, int j) {
        int[] temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }
}
*/

/*
class Solution {
    public int minimumMoves(int[][] grid) {
        List<int[]> left = new ArrayList<>();
        List<int[]> right = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (grid[i][j] == 0) {
                    left.add(new int[]{i, j});
                } else {
                    for (int k = 1; k < grid[i][j]; ++k) {
                        right.add(new int[]{i, j});
                    }
                }
            }
        }
        int n = left.size();
        int[] f = new int[1 << n];
        Arrays.fill(f, 1 << 30);
        f[0] = 0;
        for (int i = 1; i < 1 << n; ++i) {
            int k = Integer.bitCount(i);
            for (int j = 0; j < n; ++j) {
                if ((i >> j & 1) == 1) {
                    f[i] = Math.min(f[i], f[i ^ (1 << j)] + cal(left.get(k - 1), right.get(j)));
                }
            }
        }
        return f[(1 << n) - 1];
    }

    private int cal(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }
}
*/
