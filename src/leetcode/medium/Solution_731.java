package leetcode.medium;

import java.util.*;

/**
 * 我的日程安排表 II
 * 实现一个程序来存放你的日程安排。如果要添加的时间内不会导致三重预订时，则可以存储这个新的日程安排。
 * 当三个日程安排有一些时间上的交叉时（例如三个日程安排都在同一时间内），就会产生 三重预订。
 * 事件能够用一对整数 startTime 和 endTime 表示，在一个半开区间的时间 [startTime, endTime) 上预定。
 * 实数 x 的范围为  startTime <= x < endTime。
 * 实现 MyCalendarTwo 类：
 * MyCalendarTwo() 初始化日历对象。
 * boolean book(int startTime, int endTime) 如果可以将日程安排成功添加到日历中而不会导致三重预订，返回 true。
 * 否则，返回 false 并且不要将该日程安排添加到日历中。
 * 示例 1：
 * 输入：
 * ["MyCalendarTwo", "book", "book", "book", "book", "book", "book"]
 * [[], [10, 20], [50, 60], [10, 40], [5, 15], [5, 10], [25, 55]]
 * 输出：
 * [null, true, true, true, false, true, true]
 * 解释：
 * MyCalendarTwo myCalendarTwo = new MyCalendarTwo();
 * myCalendarTwo.book(10, 20); // 返回 True，能够预定该日程。
 * myCalendarTwo.book(50, 60); // 返回 True，能够预定该日程。
 * myCalendarTwo.book(10, 40); // 返回 True，该日程能够被重复预定。
 * myCalendarTwo.book(5, 15);  // 返回 False，该日程导致了三重预定，所以不能预定。
 * myCalendarTwo.book(5, 10); // 返回 True，能够预定该日程，因为它不使用已经双重预订的时间 10。
 * myCalendarTwo.book(25, 55); // 返回 True，能够预定该日程，因为时间段 [25, 40) 将被第三个日程重复预定，
 * 时间段 [40, 50) 将被单独预定，而时间段 [50, 55) 将被第二个日程重复预定。
 * 提示：
 * 0 <= start < end <= 109
 * 最多调用 book 1000 次。
 */
public class Solution_731 {
    // 直接模拟
    class MyCalendarTwo {
        List<int[]> booked;
        List<int[]> overlaps;

        public MyCalendarTwo() {
            booked = new ArrayList<>();
            overlaps = new ArrayList<>();
        }

        public boolean book(int startTime, int endTime) {
            for (int[] arr : overlaps) {
                int start = arr[0], end = arr[1];
                if (start < endTime && startTime < end) {
                    return false;
                }
            }
            for (int[] arr : booked) {
                int start = arr[0], end = arr[1];
                if (start < endTime && startTime < end) {
                    overlaps.add(new int[]{Math.max(start, startTime), Math.min(end, endTime)});
                }
            }
            booked.add(new int[]{startTime, endTime});
            return true;
        }
    }
}

/*
// 差分
class MyCalendarTwo {
    TreeMap<Integer, Integer> cnt;

    public MyCalendarTwo() {
        cnt = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        int maxBook = 0;
        cnt.put(start, cnt.getOrDefault(start, 0) + 1);
        cnt.put(end, cnt.getOrDefault(end, 0) - 1);
        for (Map.Entry<Integer, Integer> entry : cnt.entrySet()) {
            int freq = entry.getValue();
            maxBook += freq;
            if (maxBook > 2) {
                cnt.put(start, cnt.get(start) - 1);
                cnt.put(end, cnt.get(end) + 1);
                return false;
            }
        }
        return true;
    }
}
*/

/*
// 动态开点线段树
class MyCalendarTwo {
    Map<Integer, int[]> tree;

    public MyCalendarTwo() {
        tree = new HashMap<Integer, int[]>();
    }

    public boolean book(int start, int end) {
        update(start, end - 1, 1, 0, 1000000000, 1);
        tree.putIfAbsent(1, new int[2]);
        if (tree.get(1)[0] > 2) {
            update(start, end - 1, -1, 0, 1000000000, 1);
            return false;
        }
        return true;
    }

    public void update(int start, int end, int val, int l, int r, int idx) {
        if (r < start || end < l) {
            return;
        }
        tree.putIfAbsent(idx, new int[2]);
        if (start <= l && r <= end) {
            tree.get(idx)[0] += val;
            tree.get(idx)[1] += val;
        } else {
            int mid = (l + r) >> 1;
            update(start, end, val, l, mid, 2 * idx);
            update(start, end, val, mid + 1, r, 2 * idx + 1);
            tree.putIfAbsent(2 * idx, new int[2]);
            tree.putIfAbsent(2 * idx + 1, new int[2]);
            tree.get(idx)[0] = tree.get(idx)[1] + Math.max(tree.get(2 * idx)[0], tree.get(2 * idx + 1)[0]);
        }
    }
}
*/