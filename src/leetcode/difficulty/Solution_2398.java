package leetcode.difficulty;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 预算内的最多机器人数目
 * 你有 n 个机器人，给你两个下标从 0 开始的整数数组 chargeTimes 和 runningCosts ，两者长度都为 n 。
 * 第 i 个机器人充电时间为 chargeTimes[i] 单位时间，花费 runningCosts[i] 单位时间运行。再给你一个整数 budget 。
 * 运行 k 个机器人 总开销 是 max(chargeTimes) + k * sum(runningCosts) ，其中 max(chargeTimes) 是这 k 个机器人中最大充电时间，
 * sum(runningCosts) 是这 k 个机器人的运行时间之和。
 * 请你返回在 不超过 budget 的前提下，你 最多 可以 连续 运行的机器人数目为多少。
 * 示例 1：
 * 输入：chargeTimes = [3,6,1,3,4], runningCosts = [2,1,3,4,5], budget = 25
 * 输出：3
 * 解释：
 * 可以在 budget 以内运行所有单个机器人或者连续运行 2 个机器人。
 * 选择前 3 个机器人，可以得到答案最大值 3 。总开销是 max(3,6,1) + 3 * sum(2,1,3) = 6 + 3 * 6 = 24 ，小于 25 。
 * 可以看出无法在 budget 以内连续运行超过 3 个机器人，所以我们返回 3 。
 * 示例 2：
 * 输入：chargeTimes = [11,12,19], runningCosts = [10,8,7], budget = 19
 * 输出：0
 * 解释：即使运行任何一个单个机器人，还是会超出 budget，所以我们返回 0 。
 * 提示：
 * chargeTimes.length == runningCosts.length == n
 * 1 <= n <= 5 * 104
 * 1 <= chargeTimes[i], runningCosts[i] <= 105
 * 1 <= budget <= 1015
 */
public class Solution_2398 {
    public int maximumRobots(int[] chargeTimes, int[] runningCosts, long budget) {
        int ans = 0;
        int left = 0;
        long sum = 0;
        Deque<Integer> q = new ArrayDeque<>();
        for (int right = 0; right < chargeTimes.length; right++) {
            while (!q.isEmpty() && chargeTimes[right] >= chargeTimes[q.peekLast()]) {
                q.pollLast();
            }
            q.addLast(right);
            sum += runningCosts[right];
            while (!q.isEmpty() && chargeTimes[q.peekFirst()] + (right - left + 1) * sum > budget) {
                if (q.peekFirst() == left) {
                    q.pollFirst();
                }
                sum -= runningCosts[left++];
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }
}

/*
import java.util.*;
// 题目改成子序列：使用反悔贪心
public class Solution {
    public int maximumRobots(int[] a, int[] b, long budget) {
        int n = a.length;
        Integer[] ids = new Integer[n];
        for (int i = 0; i < n; i++) {
            ids[i] = i;
        }

        Arrays.sort(ids, (i, j) -> {
            if (a[i] == a[j]) return Integer.compare(b[i], b[j]);
            return Integer.compare(a[i], a[j]);
        });

        PriorityQueue<Long> q1 = new PriorityQueue<>();
        PriorityQueue<Long> q2 = new PriorityQueue<>(Collections.reverseOrder());
        long sum = 0;
        int res = 0;

        for (int id : ids) {
            long mx = a[id], rc = b[id];
            if (mx + (res + 1) * (sum + rc) <= budget) {
                q2.add(rc);
                sum += rc;
                res++;
            } else {
                q1.add(rc);
                while (!q2.isEmpty() && q1.peek() < q2.peek()) {
                    sum = sum + q1.peek() - q2.peek();
                    q2.add(q1.poll());
                    q1.add(q2.poll());
                }
                while (!q1.isEmpty() && mx + (res + 1) * (sum + q1.peek()) <= budget) {
                    q2.add(q1.poll());
                    sum += q2.peek();
                    res++;
                }
            }
        }
        return res;
    }
}
*/