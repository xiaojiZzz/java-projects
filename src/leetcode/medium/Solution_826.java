package leetcode.medium;

import java.util.Arrays;
import java.util.PriorityQueue;


/**
 * 你有 n 个工作和 m 个工人。给定三个数组： difficulty, profit 和 worker ，其中:
 * difficulty[i] 表示第 i 个工作的难度，profit[i] 表示第 i 个工作的收益。
 * worker[i] 是第 i 个工人的能力，即该工人只能完成难度小于等于 worker[i] 的工作。
 * 每个工人 最多 只能安排 一个 工作，但是一个工作可以 完成多次 。
 * 举个例子，如果 3 个工人都尝试完成一份报酬为 $1 的同样工作，那么总收益为 $3 。如果一个工人不能完成任何工作，他的收益为 $0 。
 * 返回 在把工人分配到工作岗位后，我们所能获得的最大利润 。
 * 示例 1：
 * 输入: difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
 * 输出: 100
 * 解释: 工人被分配的工作难度是 [4,4,6,6] ，分别获得 [20,20,30,30] 的收益。
 * 示例 2:
 * 输入: difficulty = [85,47,57], profit = [24,66,99], worker = [40,25,25]
 * 输出: 0
 */
public class Solution_826 {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        Arrays.sort(worker);
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int n = difficulty.length, m = worker.length;
        for (int i = 0; i < n; i++) {
            priorityQueue.add(new int[]{difficulty[i], profit[i]});
        }
        int ans = 0;
        int max = 0;
        for (int i = 0; i < m; i++) {
            while (!priorityQueue.isEmpty() && priorityQueue.peek()[0] <= worker[i]) {
                int[] poll = priorityQueue.poll();
                max = Math.max(max, poll[1]);
            }
            ans += max;
        }
        return ans;
    }
}

/*
class Solution {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int[] maxProfit = new int[100001];
        // 对 difficulty 去重，得到每个 difficulty 数值的最大 profit
        for (int i = 0; i < difficulty.length; i++) {
            if (maxProfit[difficulty[i]] < profit[i]) maxProfit[difficulty[i]] = profit[i];
        }
        int max = 0;
        // 对每个 difficulty，记录 <= difficulty 的最大 profit
        for (int i = 0; i < maxProfit.length; i++) {
            if (maxProfit[i] < max) maxProfit[i] = max;
            else max = maxProfit[i];
        }
        int ans = 0;
        for (int i : worker) {
            ans += maxProfit[i];
        }
        return ans;
    }
}
*/
