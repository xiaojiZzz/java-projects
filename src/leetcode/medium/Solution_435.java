package leetcode.medium;

import java.util.Arrays;

/**
 * 无重叠区间
 * 给定一个区间的集合 intervals ，其中 intervals[i] = [starti, endi] 。返回 需要移除区间的最小数量，使剩余区间互不重叠 。
 * 示例 1:
 * 输入: intervals = [[1,2],[2,3],[3,4],[1,3]]
 * 输出: 1
 * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 * 示例 2:
 * 输入: intervals = [ [1,2], [1,2], [1,2] ]
 * 输出: 2
 * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
 * 示例 3:
 * 输入: intervals = [ [1,2], [2,3] ]
 * 输出: 0
 * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
 * 提示:
 * 1 <= intervals.length <= 105
 * intervals[i].length == 2
 * -5 * 104 <= starti < endi <= 5 * 104
 */
public class Solution_435 {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            } else {
                return a[1] - b[1];
            }
        });
        int n = intervals.length;
        int ans = 0;
        int start = intervals[0][0], end = intervals[0][1];
        for (int i = 1; i < n; i++) {
            int x = intervals[i][0], y = intervals[i][1];
            if (x == start) {
                ans++;
            } else if (x < end) {
                if (y < end) {
                    end = y;
                }
                ans++;
            } else {
                start = x;
                end = y;
            }
        }
        return ans;
    }
}

/*
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
        int n = intervals.length;
        int right = intervals[0][1];
        int ans = 1;
        for (int i = 1; i < n; ++i) {
            if (intervals[i][0] >= right) {
                ++ans;
                right = intervals[i][1];
            }
        }
        return n - ans;
    }
}
*/

/*
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int result = 0;
        for (int i = 1; i < intervals.length; i++) {
            int[] pre = intervals[i - 1];
            int[] cur = intervals[i];
            if (cur[0] < pre[1]) {
                result++;
                intervals[i][1] = Math.min(pre[1], cur[1]);
            }
        }
        return result;
    }
}
*/
