package leetcode.difficulty;

import java.util.Arrays;

/**
 * 有一个 m x n 大小的矩形蛋糕，需要切成 1 x 1 的小块。
 * 给你整数 m ，n 和两个数组：
 * horizontalCut 的大小为 m - 1 ，其中 horizontalCut[i] 表示沿着水平线 i 切蛋糕的开销。
 * verticalCut 的大小为 n - 1 ，其中 verticalCut[j] 表示沿着垂直线 j 切蛋糕的开销。
 * 一次操作中，你可以选择任意不是 1 x 1 大小的矩形蛋糕并执行以下操作之一：
 * 沿着水平线 i 切开蛋糕，开销为 horizontalCut[i] 。
 * 沿着垂直线 j 切开蛋糕，开销为 verticalCut[j] 。
 * 每次操作后，这块蛋糕都被切成两个独立的小蛋糕。
 * 每次操作的开销都为最开始对应切割线的开销，并且不会改变。
 * 请你返回将蛋糕全部切成 1 x 1 的蛋糕块的 最小 总开销。
 * 示例 1：
 * 输入：m = 3, n = 2, horizontalCut = [1,3], verticalCut = [5]
 * 输出：13
 * 解释：
 * 沿着垂直线 0 切开蛋糕，开销为 5 。
 * 沿着水平线 0 切开 3 x 1 的蛋糕块，开销为 1 。
 * 沿着水平线 0 切开 3 x 1 的蛋糕块，开销为 1 。
 * 沿着水平线 1 切开 2 x 1 的蛋糕块，开销为 3 。
 * 沿着水平线 1 切开 2 x 1 的蛋糕块，开销为 3 。
 * 总开销为 5 + 1 + 1 + 3 + 3 = 13 。
 * 示例 2：
 * 输入：m = 2, n = 2, horizontalCut = [7], verticalCut = [4]
 * 输出：15
 * 解释：
 * 沿着水平线 0 切开蛋糕，开销为 7 。
 * 沿着垂直线 0 切开 1 x 2 的蛋糕块，开销为 4 。
 * 沿着垂直线 0 切开 1 x 2 的蛋糕块，开销为 4 。
 * 总开销为 7 + 4 + 4 = 15 。
 * 提示：
 * 1 <= m, n <= 105
 * horizontalCut.length == m - 1
 * verticalCut.length == n - 1
 * 1 <= horizontalCut[i], verticalCut[i] <= 103
 */
public class Solution_3219 {
    public long minimumCost(int m, int n, int[] horizontalCut, int[] verticalCut) {
        Arrays.sort(horizontalCut);
        Arrays.sort(verticalCut);
        int idx1 = m - 2, idx2 = n - 2;
        long mul1 = 1, mul2 = 1;
        long ans = 0;
        while (idx1 >= 0 && idx2 >= 0) {
            int cut1 = horizontalCut[idx1];
            int cut2 = verticalCut[idx2];
            if (cut1 > cut2) {
                ans += horizontalCut[idx1] * mul1;
                idx1--;
                mul2++;
            } else {
                ans += verticalCut[idx2] * mul2;
                idx2--;
                mul1++;
            }
        }
        while (idx1 >= 0) {
            ans += horizontalCut[idx1] * mul1;
            idx1--;
        }
        while (idx2 >= 0) {
            ans += verticalCut[idx2] * mul2;
            idx2--;
        }
        return ans;
    }
}

/*
class Solution {
    public long minimumCost(int m, int n, int[] horizontalCut, int[] verticalCut) {
        Arrays.sort(horizontalCut); // 下面倒序遍历
        Arrays.sort(verticalCut);
        long ans = 0;
        int i = 0;
        int j = 0;
        while (i < m - 1 || j < n - 1) {
            if (j == n - 1 || i < m - 1 && horizontalCut[i] < verticalCut[j]) {
                ans += horizontalCut[i++] * (n - j); // 上下连边
            } else {
                ans += verticalCut[j++] * (m - i); // 左右连边
            }
        }
        return ans;
    }
}
*/