package leetcode.medium;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 吃苹果的最大数目
 * 有一棵特殊的苹果树，一连 n 天，每天都可以长出若干个苹果。在第 i 天，树上会长出 apples[i] 个苹果，
 * 这些苹果将会在 days[i] 天后（也就是说，第 i + days[i] 天时）腐烂，变得无法食用。
 * 也可能有那么几天，树上不会长出新的苹果，此时用 apples[i] == 0 且 days[i] == 0 表示。
 * 你打算每天 最多 吃衡一个苹果来保证营养均。注意，你可以在这 n 天之后继续吃苹果。
 * 给你两个长度为 n 的整数数组 days 和 apples ，返回你可以吃掉的苹果的最大数目。
 * 示例 1：
 * 输入：apples = [1,2,3,5,2], days = [3,2,1,4,2]
 * 输出：7
 * 解释：你可以吃掉 7 个苹果：
 * - 第一天，你吃掉第一天长出来的苹果。
 * - 第二天，你吃掉一个第二天长出来的苹果。
 * - 第三天，你吃掉一个第二天长出来的苹果。过了这一天，第三天长出来的苹果就已经腐烂了。
 * - 第四天到第七天，你吃的都是第四天长出来的苹果。
 * 示例 2：
 * 输入：apples = [3,0,0,0,0,2], days = [3,0,0,0,0,2]
 * 输出：5
 * 解释：你可以吃掉 5 个苹果：
 * - 第一天到第三天，你吃的都是第一天长出来的苹果。
 * - 第四天和第五天不吃苹果。
 * - 第六天和第七天，你吃的都是第六天长出来的苹果。
 * 提示：
 * apples.length == n
 * days.length == n
 * 1 <= n <= 2 * 104
 * 0 <= apples[i], days[i] <= 2 * 104
 * 只有在 apples[i] = 0 时，days[i] = 0 才成立
 */
public class Solution_1705 {
    public int eatenApples(int[] apples, int[] days) {
        int n = apples.length;
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        int ans = 0;
        for (int i = 0; i < n || !priorityQueue.isEmpty(); i++) {
            // 必须是 while 循环，把已经腐烂的删掉，才能这一天吃到苹果
            while (!priorityQueue.isEmpty() && priorityQueue.peek()[1] <= i) {
                priorityQueue.poll();
            }
            if (i < n && apples[i] > 0) {
                priorityQueue.add(new int[]{apples[i], i + days[i]});
            }
            if (!priorityQueue.isEmpty()) {
                ans++;
                if (--priorityQueue.peek()[0] == 0) {
                    priorityQueue.poll();
                }
            }
        }
        return ans;
    }
}

/*
// 优化
class Solution {
    public int eatenApples(int[] apples, int[] days) {
        int ans = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int i = 0;
        for (; i < apples.length; i++) {
            while (!pq.isEmpty() && pq.peek()[0] == i) { // 已腐烂
                pq.poll();
            }
            if (apples[i] > 0) {
                pq.offer(new int[]{i + days[i], apples[i]});
            }
            if (!pq.isEmpty()) {
                // 吃一个最早腐烂的苹果
                ans++;
                if (--pq.peek()[1] == 0) {
                    pq.poll();
                }
            }
        }

        while (true) {
            while (!pq.isEmpty() && pq.peek()[0] <= i) { // 已腐烂
                pq.poll();
            }
            if (pq.isEmpty()) {
                return ans;
            }
            int[] top = pq.poll();
            int k = Math.min(top[1], top[0] - i);
            ans += k;
            i += k;
        }
    }
}
*/