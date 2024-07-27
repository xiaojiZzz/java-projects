package leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，
 * 并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 * 示例 1：
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2：
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 */
public class Solution_56 {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<int[]> list = new ArrayList<>();
        int a = intervals[0][0], b = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            int x = intervals[i][0], y = intervals[i][1];
            if (x > b) {
                list.add(new int[]{a, b});
                a = x;
                b = y;
            } else {
                b = Math.max(b, y);
            }
        }
        list.add(new int[]{a, b});
        return list.toArray(new int[list.size()][]);
    }
}
