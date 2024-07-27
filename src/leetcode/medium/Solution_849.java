package leetcode.medium;


/**
 * 给你一个数组 seats 表示一排座位，其中 seats[i] = 1 代表有人坐在第 i 个座位上，seats[i] = 0 代表座位 i 上是空的（下标从 0 开始）。
 * 至少有一个空座位，且至少有一人已经坐在座位上。
 * 亚历克斯希望坐在一个能够使他与离他最近的人之间的距离达到最大化的座位上。
 * 返回他到离他最近的人的最大距离。
 * 示例 1：
 * 输入：seats = [1,0,0,0,1,0,1]
 * 输出：2
 * 解释：
 * 如果亚历克斯坐在第二个空位（seats[2]）上，他到离他最近的人的距离为 2 。
 * 如果亚历克斯坐在其它任何一个空位上，他到离他最近的人的距离为 1 。
 * 因此，他到离他最近的人的最大距离是 2 。
 */
public class Solution_849 {
    public int maxDistToClosest(int[] seats) {
        int max = 0;
        int id = 0;
        while (seats[id++] == 0) {
            max++;
        }
        int maxLen = max;
        max = 0;
        id = seats.length - 1;
        while (seats[id--] == 0) {
            max++;
        }
        maxLen = Math.max(max, maxLen);
        int cnt = 0;
        int maxLen2 = 0;
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 0) {
                cnt++;
            } else {
                cnt = 0;
            }
            maxLen2 = Math.max(cnt, maxLen2);
        }
        return Math.max((maxLen2 + 1) / 2, maxLen);
    }
}
