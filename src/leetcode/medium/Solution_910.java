package leetcode.medium;

import java.util.*;

/**
 * 最小差值 II
 * 给你一个整数数组 nums，和一个整数 k 。
 * 对于每个下标 i（0 <= i < nums.length），将 nums[i] 变成 nums[i] + k 或 nums[i] - k 。
 * nums 的 分数 是 nums 中最大元素和最小元素的差值。
 * 在更改每个下标对应的值之后，返回 nums 的最小 分数 。
 * 示例 1：
 * 输入：nums = [1], k = 0
 * 输出：0
 * 解释：分数 = max(nums) - min(nums) = 1 - 1 = 0 。
 * 示例 2：
 * 输入：nums = [0,10], k = 2
 * 输出：6
 * 解释：将数组变为 [2, 8] 。分数 = max(nums) - min(nums) = 8 - 2 = 6 。
 * 示例 3：
 * 输入：nums = [1,3,6], k = 3
 * 输出：3
 * 解释：将数组变为 [4, 6, 3] 。分数 = max(nums) - min(nums) = 6 - 3 = 3 。
 * 提示：
 * 1 <= nums.length <= 104
 * 0 <= nums[i] <= 104
 * 0 <= k <= 104
 */
public class Solution_910 {
    public int smallestRangeII(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = nums[n - 1] - nums[0];
        for (int i = 0; i < n - 1; i++) {
            int min = Math.min(nums[0] + k, nums[i + 1] - k);
            int max = Math.max(nums[i] + k, nums[n - 1] - k);
            ans = Math.min(ans, max - min);
        }
        return ans;
    }
}

/*
class Solution {
    public int smallestRangeII(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> count = new HashMap<>();
        List<int[]> points = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            points.add(new int[]{nums[i] - k, i});
            points.add(new int[]{nums[i] + k, i});
        }
        points.sort(Comparator.comparingInt(a -> a[0]));
        int ans = Integer.MAX_VALUE;
        for (int i = 0, j = 0; j < points.size(); j++) {
            count.put(points.get(j)[1], count.getOrDefault(points.get(j)[1], 0) + 1);
            while (count.size() == n) {
                ans = Math.min(ans, points.get(j)[0] - points.get(i)[0]);
                int idx = points.get(i++)[1];
                count.put(idx, count.get(idx) - 1);
                if (count.get(idx) == 0) {
                    count.remove(idx);
                }
            }
        }
        return ans;
    }
}
*/
