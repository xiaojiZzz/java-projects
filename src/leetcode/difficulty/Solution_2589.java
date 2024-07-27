package leetcode.difficulty;

import java.util.Arrays;


/**
 * 你有一台电脑，它可以 同时 运行无数个任务。给你一个二维整数数组 tasks ，其中 tasks[i] = [starti, endi, durationi] 表示
 * 第 i 个任务需要在 闭区间 时间段 [starti, endi] 内运行 durationi 个整数时间点（但不需要连续）。
 * 当电脑需要运行任务时，你可以打开电脑，如果空闲时，你可以将电脑关闭。
 * 请你返回完成所有任务的情况下，电脑最少需要运行多少秒。
 * 示例 1：
 * 输入：tasks = [[2,3,1],[4,5,1],[1,5,2]]
 * 输出：2
 * 解释：
 * - 第一个任务在闭区间 [2, 2] 运行。
 * - 第二个任务在闭区间 [5, 5] 运行。
 * - 第三个任务在闭区间 [2, 2] 和 [5, 5] 运行。
 * 电脑总共运行 2 个整数时间点。
 * 示例 2：
 * 输入：tasks = [[1,3,2],[2,5,3],[5,6,2]]
 * 输出：4
 * 解释：
 * - 第一个任务在闭区间 [2, 3] 运行
 * - 第二个任务在闭区间 [2, 3] 和 [5, 5] 运行。
 * - 第三个任务在闭区间 [5, 6] 运行。
 * 电脑总共运行 4 个整数时间点。
 */
public class Solution_2589 {
    public int findMinimumTime(int[][] tasks) {
        int n = tasks.length;
        Arrays.sort(tasks, (a, b) -> a[1] - b[1]);
        int[] run = new int[tasks[n - 1][1] + 1];
        int res = 0;
        for (int i = 0; i < n; i++) {
            int start = tasks[i][0], end = tasks[i][1], duration = tasks[i][2];
            for (int j = start; j <= end; j++) {
                duration -= run[j];
            }
            res += Math.max(duration, 0);
            for (int j = end; j >= 0 && duration > 0; j--) {
                if (run[j] == 0) {
                    duration--;
                    run[j] = 1;
                }
            }
        }
        return res;
    }
}

/*
class Solution {
    public int findMinimumTime(int[][] tasks) {
        Arrays.sort(tasks, (a, b) -> a[1] - b[1]);
        // 栈中保存闭区间左右端点，栈底到栈顶的区间长度的和
        List<int[]> st = new ArrayList<>();
        st.add(new int[]{-2, -2, 0}); // 哨兵，保证不和任何区间相交
        for (int[] t : tasks) {
            int start = t[0], end = t[1], d = t[2];
            int[] e = st.get(lowerBound(st, start) - 1);
            d -= st.get(st.size() - 1)[2] - e[2]; // 去掉运行中的时间点
            if (start <= e[1]) { // start 在区间 st[i] 内
                d -= e[1] - start + 1; // 去掉运行中的时间点
            }
            if (d <= 0) {
                continue;
            }
            while (end - st.get(st.size() - 1)[1] <= d) { // 剩余的 d 填充区间后缀
                e = st.remove(st.size() - 1);
                d += e[1] - e[0] + 1; // 合并区间
            }
            st.add(new int[]{end - d + 1, end, st.get(st.size() - 1)[2] + d});
        }
        return st.get(st.size() - 1)[2];
    }

    // 开区间二分
    private int lowerBound(List<int[]> st, int target) {
        int left = -1, right = st.size(); // 开区间 (left, right)
        while (left + 1 < right) { // 区间不为空
            // 循环不变量：
            // st[left] < target
            // st[right] >= target
            int mid = (left + right) >>> 1;
            if (st.get(mid)[0] < target) {
                left = mid; // 范围缩小到 (mid, right)
            } else {
                right = mid; // 范围缩小到 (left, mid)
            }
        }
        return right; // 或者 left+1
    }
}
*/
