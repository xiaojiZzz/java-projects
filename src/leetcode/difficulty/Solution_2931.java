package leetcode.difficulty;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 购买物品的最大开销
 * 给你一个下标从 0 开始大小为 m * n 的整数矩阵 values ，表示 m 个不同商店里 m * n 件不同的物品。
 * 每个商店有 n 件物品，第 i 个商店的第 j 件物品的价值为 values[i][j] 。
 * 除此以外，第 i 个商店的物品已经按照价值非递增排好序了，也就是说对于所有 0 <= j < n - 1 都有 values[i][j] >= values[i][j + 1] 。
 * 每一天，你可以在一个商店里购买一件物品。具体来说，在第 d 天，你可以：
 * 选择商店 i 。
 * 购买数组中最右边的物品 j ，开销为 values[i][j] * d 。
 * 换句话说，选择该商店中还没购买过的物品中最大的下标 j ，并且花费 values[i][j] * d 去购买。
 * 注意，所有物品都视为不同的物品。比方说如果你已经从商店 1 购买了物品 0 ，你还可以在别的商店里购买其他商店的物品 0 。
 * 请你返回购买所有 m * n 件物品需要的 最大开销 。
 * 示例 1：
 * 输入：values = [[8,5,2],[6,4,1],[9,7,3]]
 * 输出：285
 * 解释：第一天，从商店 1 购买物品 2 ，开销为 values[1][2] * 1 = 1 。
 * 第二天，从商店 0 购买物品 2 ，开销为 values[0][2] * 2 = 4 。
 * 第三天，从商店 2 购买物品 2 ，开销为 values[2][2] * 3 = 9 。
 * 第四天，从商店 1 购买物品 1 ，开销为 values[1][1] * 4 = 16 。
 * 第五天，从商店 0 购买物品 1 ，开销为 values[0][1] * 5 = 25 。
 * 第六天，从商店 1 购买物品 0 ，开销为 values[1][0] * 6 = 36 。
 * 第七天，从商店 2 购买物品 1 ，开销为 values[2][1] * 7 = 49 。
 * 第八天，从商店 0 购买物品 0 ，开销为 values[0][0] * 8 = 64 。
 * 第九天，从商店 2 购买物品 0 ，开销为 values[2][0] * 9 = 81 。
 * 所以总开销为 285 。
 * 285 是购买所有 m * n 件物品的最大总开销。
 * 示例 2：
 * 输入：values = [[10,8,6,4,2],[9,7,5,3,2]]
 * 输出：386
 * 解释：第一天，从商店 0 购买物品 4 ，开销为 values[0][4] * 1 = 2 。
 * 第二天，从商店 1 购买物品 4 ，开销为 values[1][4] * 2 = 4 。
 * 第三天，从商店 1 购买物品 3 ，开销为 values[1][3] * 3 = 9 。
 * 第四天，从商店 0 购买物品 3 ，开销为 values[0][3] * 4 = 16 。
 * 第五天，从商店 1 购买物品 2 ，开销为 values[1][2] * 5 = 25 。
 * 第六天，从商店 0 购买物品 2 ，开销为 values[0][2] * 6 = 36 。
 * 第七天，从商店 1 购买物品 1 ，开销为 values[1][1] * 7 = 49 。
 * 第八天，从商店 0 购买物品 1 ，开销为 values[0][1] * 8 = 64 。
 * 第九天，从商店 1 购买物品 0 ，开销为 values[1][0] * 9 = 81 。
 * 第十天，从商店 0 购买物品 0 ，开销为 values[0][0] * 10 = 100 。
 * 所以总开销为 386 。
 * 386 是购买所有 m * n 件物品的最大总开销。
 * 提示：
 * 1 <= m == values.length <= 10
 * 1 <= n == values[i].length <= 104
 * 1 <= values[i][j] <= 106
 * values[i] 按照非递增顺序排序。
 */
public class Solution_2931 {
    public long maxSpending(int[][] values) {
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        int m = values.length, n = values[0].length;
        for (int i = 0; i < m; i++) {
            priorityQueue.add(new int[]{values[i][n - 1], i, n - 1});
        }
        long ans = 0;
        int day = 1;
        while (!priorityQueue.isEmpty()) {
            int[] poll = priorityQueue.poll();
            ans += (long) poll[0] * day++;
            int end = poll[2];
            if (end > 0) {
                int row = poll[1];
                priorityQueue.add(new int[]{values[row][end - 1], row, end - 1});
            }
        }
        return ans;
    }
}

/*
// 另一种写法
class Solution {
    public long maxSpending(int[][] values) {
        int m = values.length;
        int n = values[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> values[a[0]][a[1]] - values[b[0]][b[1]]);
        for (int i = 0; i < m; i++) {
            pq.offer(new int[]{i, n - 1});
        }

        long ans = 0;
        for (int d = 1; d <= m * n; d++) {
            int[] p = pq.poll();
            int i = p[0];
            int j = p[1];
            ans += (long) values[i][j] * d;
            if (j > 0) {
                pq.offer(new int[]{i, j - 1});
            }
        }
        return ans;
    }
}
*/

/*
class Solution {
    public long maxSpending(int[][] values) {
        int m = values.length;
        int n = values[0].length;
        int[] a = new int[m * n];
        for (int i = 0; i < m; i++) {
            System.arraycopy(values[i], 0, a, i * n, n);
        }
        Arrays.sort(a);

        long ans = 0;
        for (int i = 0; i < a.length; i++) {
            ans += (long) a[i] * (i + 1);
        }
        return ans;
    }
}
*/