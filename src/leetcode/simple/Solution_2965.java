package leetcode.simple;

import java.util.HashSet;
import java.util.Set;


/**
 * 给你一个下标从 0 开始的二维整数矩阵 grid，大小为 n * n ，其中的值在 [1, n2] 范围内。
 * 除了 a 出现 两次，b 缺失 之外，每个整数都 恰好出现一次 。
 * 任务是找出重复的数字a 和缺失的数字 b 。
 * 返回一个下标从 0 开始、长度为 2 的整数数组 ans ，其中 ans[0] 等于 a ，ans[1] 等于 b 。
 * 示例 1：
 * 输入：grid = [[1,3],[2,2]]
 * 输出：[2,4]
 * 解释：数字 2 重复，数字 4 缺失，所以答案是 [2,4] 。
 * 示例 2：
 * 输入：grid = [[9,1,7],[8,9,2],[3,4,6]]
 * 输出：[9,5]
 * 解释：数字 9 重复，数字 5 缺失，所以答案是 [9,5] 。
 */
public class Solution_2965 {
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        Set<Integer> set = new HashSet<>();
        int n = grid.length;
        int a = -1, b = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (set.contains(grid[i][j])) {
                    a = grid[i][j];
                } else {
                    set.add(grid[i][j]);
                }
            }
        }
        for (int i = 1; i <= n * n; i++) {
            if (!set.contains(i)) {
                b = i;
                break;
            }
        }
        return new int[]{a, b};
    }
}

/*
class Solution {
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        // 数学，利用 1 - n 之和 和 1 - n^2 之和 来解决
        int n = grid.length;
        int m = n * n;
        int d1 = -m * (m + 1) / 2;
        long d2 = (long) -m * (m + 1) * (m * 2 + 1) / 6;
        for (int[] row : grid) {
            for (int x : row) {
                d1 += x;
                d2 += x * x;
            }
        }
        int d = (int) (d2 / d1);
        return new int[]{(d + d1) / 2, (d - d1) / 2};
    }
}
*/

/*
class Solution {
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int n = grid.length;
        int xor = 0;
        for (int i = 1; i <= n * n; i++) {
            xor ^= i;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                xor ^= grid[i][j];
            }
        }
        xor = xor == Integer.MIN_VALUE ? Integer.MIN_VALUE : (xor & -xor);
        int a = 0, b = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((grid[i][j] & xor) != 0) {
                    a ^= grid[i][j];
                } else {
                    b ^= grid[i][j];
                }
            }
        }
        for (int i = 1; i <= n * n; i++) {
            if ((i & xor) != 0) {
                a ^= i;
            } else {
                b ^= i;
            }
        }
        return new int[]{a, b};
    }
}
*/
