package leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 请你设计一个数据结构，它能求出给定子数组内一个给定值的 频率 。
 * 子数组中一个值的 频率 指的是这个子数组中这个值的出现次数。
 * 请你实现 RangeFreqQuery 类：
 * RangeFreqQuery(int[] arr) 用下标从 0 开始的整数数组 arr 构造一个类的实例。
 * int query(int left, int right, int value) 返回子数组 arr[left...right] 中 value 的 频率 。
 * 一个 子数组 指的是数组中一段连续的元素。arr[left...right] 指的是 nums 中包含下标 left 和 right 在内 的中间一段连续元素。
 * 示例 1：
 * 输入：
 * ["RangeFreqQuery", "query", "query"]
 * [[[12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]], [1, 2, 4], [0, 11, 33]]
 * 输出：
 * [null, 1, 2]
 * 解释：
 * RangeFreqQuery rangeFreqQuery = new RangeFreqQuery([12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]);
 * rangeFreqQuery.query(1, 2, 4); // 返回 1 。4 在子数组 [33, 4] 中出现 1 次。
 * rangeFreqQuery.query(0, 11, 33); // 返回 2 。33 在整个子数组中出现 2 次。
 * 提示：
 * 1 <= arr.length <= 105
 * 1 <= arr[i], value <= 104
 * 0 <= left <= right < arr.length
 * 调用 query 不超过 105 次。
 */
public class Solution_2080 {
    class RangeFreqQuery {
        private final Map<Integer, List<Integer>> pos = new HashMap<>();

        public RangeFreqQuery(int[] arr) {
            for (int i = 0; i < arr.length; i++) {
                pos.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
            }
        }

        public int query(int left, int right, int value) {
            List<Integer> a = pos.get(value);
            if (a == null) {
                return 0;
            }
            // > right 等价于 >= right+1
            return lowerBound(a, right + 1) - lowerBound(a, left);
        }

        private int lowerBound(List<Integer> a, int target) {
            // 开区间 (left, right)
            int left = -1;
            int right = a.size();
            while (left + 1 < right) { // 区间不为空
                // 循环不变量：
                // a[left] < target
                // a[right] >= target
                int mid = (left + right) >>> 1;
                if (a.get(mid) < target) {
                    left = mid; // 范围缩小到 (mid, right)
                } else {
                    right = mid; // 范围缩小到 (left, mid)
                }
            }
            return right; // right 是最小的满足 a[right] >= target 的下标
        }
    }
}
