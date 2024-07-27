package leetcode.difficulty;

import java.util.Arrays;


/**
 * 我们定义 arr 是 山形数组 当且仅当它满足：
 * arr.length >= 3
 * 存在某个下标 i （从 0 开始） 满足 0 < i < arr.length - 1 且：
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 * 给你整数数组 nums ，请你返回将 nums 变成 山形状数组 的 最少 删除次数。
 * 示例 1：
 * 输入：nums = [1,3,1]
 * 输出：0
 * 解释：数组本身就是山形数组，所以我们不需要删除任何元素。
 * 示例 2：
 * 输入：nums = [2,1,1,5,6,2,3,1]
 * 输出：3
 * 解释：一种方法是将下标为 0，1 和 5 的元素删除，剩余元素为 [1,5,6,3,1] ，是山形数组。
 */
public class Solution_1671 {
    public int minimumMountainRemovals(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(left, 1);
        Arrays.fill(right, 1);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    left[i] = Math.max(left[i], left[j] + 1);
                }
            }
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = n - 1; j > i; j--) {
                if (nums[i] > nums[j]) {
                    right[i] = Math.max(right[i], right[j] + 1);
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < n - 1; i++) {
            if (left[i] != 1 && right[i] != 1) {
                ans = Math.min(ans, (n - (left[i] + right[i] - 1)));
            }
        }
        return ans;
    }
}

/*
class Solution {
    //二分
    public int minimumMountainRemovals(int[] nums) {
        int n = nums.length;
        int[] suf = new int[n];
        List<Integer> g = new ArrayList<>();
        for (int i = n - 1; i > 0; i--) {
            int x = nums[i];
            int j = lowerBound(g, x);
            if (j == g.size()) {
                g.add(x);
            } else {
                g.set(j, x);
            }
            suf[i] = j + 1; // 从 nums[i] 开始的最长严格递减子序列的长度
        }

        int mx = 0;
        g.clear();
        for (int i = 0; i < n - 1; i++) {
            int x = nums[i];
            int j = lowerBound(g, x);
            if (j == g.size()) {
                g.add(x);
            } else {
                g.set(j, x);
            }
            int pre = j + 1; // 在 nums[i] 结束的最长严格递增子序列的长度
            if (pre >= 2 && suf[i] >= 2) {
                mx = Math.max(mx, pre + suf[i] - 1); // 减去重复的 nums[i]
            }
        }
        return n - mx;
    }

    private int lowerBound(List<Integer> g, int target) {
        int left = -1, right = g.size(); // 开区间 (left, right)
        while (left + 1 < right) { // 区间不为空
            // 循环不变量：
            // nums[left] < target
            // nums[right] >= target
            int mid = (left + right) >>> 1;
            if (g.get(mid) < target) {
                left = mid; // 范围缩小到 (mid, right)
            } else {
                right = mid; // 范围缩小到 (left, mid)
            }
        }
        return right; // 或者 left+1
    }
}
*/
