package leetcode.medium;

import java.util.List;

/**
 * 数组列表中的最大距离
 * 给定 m 个数组，每个数组都已经按照升序排好序了。
 * 现在你需要从两个不同的数组中选择两个整数（每个数组选一个）并且计算它们的距离。两个整数 a 和 b 之间的距离定义为它们差的绝对值 |a-b| 。
 * 返回最大距离。
 * 示例 1：
 * 输入：[[1,2,3],[4,5],[1,2,3]]
 * 输出：4
 * 解释：
 * 一种得到答案 4 的方法是从第一个数组或者第三个数组中选择 1，同时从第二个数组中选择 5 。
 * 示例 2：
 * 输入：arrays = [[1],[1]]
 * 输出：0
 * 提示：
 * m == arrays.length
 * 2 <= m <= 105
 * 1 <= arrays[i].length <= 500
 * -104 <= arrays[i][j] <= 104
 * arrays[i] 以 升序 排序。
 * 所有数组中最多有 105 个整数。
 */
public class Solution_624 {
    public int maxDistance(List<List<Integer>> arrays) {
        int min = Integer.MAX_VALUE, nextMin = Integer.MAX_VALUE, minId = 0;
        int max = Integer.MIN_VALUE, nextMax = Integer.MIN_VALUE, maxId = 0;
        for (int i = 0; i < arrays.size(); i++) {
            List<Integer> array = arrays.get(i);
            int minNum = array.get(0), maxNum = array.get(array.size() - 1);
            if (minNum < min) {
                nextMin = min;
                min = minNum;
                minId = i;
            } else {
                nextMin = Math.min(nextMin, minNum);
            }
            if (maxNum > max) {
                nextMax = max;
                max = maxNum;
                maxId = i;
            } else {
                nextMax = Math.max(nextMax, maxNum);
            }
        }
        return minId == maxId ? Math.max(max - nextMin, nextMax - min) : max - min;
    }
}

/*
class Solution {
    public int maxDistance(List<List<Integer>> arrays) {
        int ans = 0;
        int mn = Integer.MAX_VALUE / 2; // 防止减法溢出
        int mx = Integer.MIN_VALUE / 2;
        for (List<Integer> a : arrays) {
            int x = a.get(0);
            int y = a.get(a.size() - 1);
            ans = Math.max(ans, Math.max(y - mn, mx - x));
            mn = Math.min(mn, x);
            mx = Math.max(mx, y);
        }
        return ans;
    }
}
*/