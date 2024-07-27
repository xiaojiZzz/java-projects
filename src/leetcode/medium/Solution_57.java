package leetcode.medium;

import java.util.ArrayList;


/**
 * 给你一个 无重叠的 ，按照区间起始端点排序的区间列表。
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 * 示例 1：
 * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出：[[1,5],[6,9]]
 * 示例 2：
 * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出：[[1,2],[3,10],[12,16]]
 * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 */
public class Solution_57 {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int left = newInterval[0], right = newInterval[1];
        boolean is = false;
        ArrayList<int[]> list = new ArrayList<>();
        for (int[] interval : intervals) {
            if (interval[0] > right) {
                if (!is) {
                    list.add(new int[]{left, right});
                    is = true;
                }
                list.add(interval);
            } else if (interval[1] < left) {
                list.add(new int[]{interval[0], interval[1]});
            } else {
                left = Math.min(interval[0], left);
                right = Math.max(interval[1], right);
            }
        }
        if (!is) {
            list.add(new int[]{left, right});
        }
        return list.toArray(new int[list.size()][]);
    }
}

/*
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();
        int i = 0;
        while (i < intervals.length && newInterval[0] > intervals[i][1]) {
            res.add(intervals[i]);
            i++;
        }
        int[] tmp = new int[]{newInterval[0], newInterval[1]};
        while (i < intervals.length && newInterval[1] >= intervals[i][0]) {
            tmp[0] = Math.min(tmp[0], intervals[i][0]);
            tmp[1] = Math.max(tmp[1], intervals[i][1]);
            i++;
        }
        res.add(tmp);
        while (i < intervals.length) {
            res.add(intervals[i]);
            i++;
        }
        return res.toArray(new int[0][]);
    }
}
*/

/*
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> list = new ArrayList<>();
        int a = newInterval[0], b = newInterval[1];
        for (int[] interval : intervals) {
            int x = interval[0], y = interval[1];
            if (x > b) {
                list.add(new int[]{a, b});
                a = x;
                b = y;
            } else if (x >= a) {
                b = Math.max(b, y);
            } else if (y < a) {
                list.add(new int[]{x, y});
            } else {
                a = Math.min(a, x);
                b = Math.max(b, y);
            }
        }
        list.add(new int[]{a, b});
        return list.toArray(new int[list.size()][]);
    }
}
*/
