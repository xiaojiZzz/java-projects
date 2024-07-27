package leetcode.medium;


/**
 * 给你一个整数 total ，表示你拥有的总钱数。同时给你两个整数 cost1 和 cost2 ，分别表示一支钢笔和一支铅笔的价格。
 * 你可以花费你部分或者全部的钱，去买任意数目的两种笔。
 * 请你返回购买钢笔和铅笔的 不同方案数目 。
 * 示例 1：
 * 输入：total = 20, cost1 = 10, cost2 = 5
 * 输出：9
 * 解释：一支钢笔的价格为 10 ，一支铅笔的价格为 5 。
 * - 如果你买 0 支钢笔，那么你可以买 0 ，1 ，2 ，3 或者 4 支铅笔。
 * - 如果你买 1 支钢笔，那么你可以买 0 ，1 或者 2 支铅笔。
 * - 如果你买 2 支钢笔，那么你没法买任何铅笔。
 * 所以买钢笔和铅笔的总方案数为 5 + 3 + 1 = 9 种。
 */
public class Solution_2240 {
    public long waysToBuyPensPencils(int total, int cost1, int cost2) {
        int pen_num = total / cost1;
        long sum = 0;
        for (int i = 0; i <= pen_num; i++) {
            sum += (total - i * cost1) / cost2 + 1;
        }
        return sum;
    }
}

/*
class Solution {
    //类欧几里得算法
    public long waysToBuyPensPencils(int total, int cost1, int cost2) {
        int n = total / cost1, b = total % cost1;
        return euclid(cost1, b, cost2, n) + n + 1;
    }

    public long euclid(int a, int b, int c, int n) {
        if (n == 0 || a == 0) {
            return (long) b / c;
        }
        if (a >= c || b >= c) {
            return (long) n * (n + 1) / 2 * (a / c) + (long) (n + 1) * (b / c) + euclid(a % c, b % c, c, n);
        }
        int m = (a * n + b) / c;
        return (long) n * m - euclid(c, c - b - 1, a, m - 1);
    }
}
*/
