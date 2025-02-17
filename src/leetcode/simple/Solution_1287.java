package leetcode.simple;

/**
 * 有序数组中出现次数超过25%的元素
 * 给你一个非递减的 有序 整数数组，已知这个数组中恰好有一个整数，它的出现次数超过数组元素总数的 25%。
 * 请你找到并返回这个整数
 * 示例：
 * 输入：arr = [1,2,2,6,6,6,6,7,10]
 * 输出：6
 * 提示：
 * 1 <= arr.length <= 10^4
 * 0 <= arr[i] <= 10^5
 */
public class Solution_1287 {
    public int findSpecialInteger(int[] arr) {
        int n = arr.length, cnt = n / 4;
        for (int i = 0; i < n - cnt; i++) {
            if (arr[i] == arr[i + cnt]) {
                return arr[i];
            }
        }
        return 0;
    }
}

/*
class Solution {
    public int findSpecialInteger(int[] arr) {
        int m = arr.length / 4;
        for (int i : new int[]{m, m * 2 + 1}) {
            int x = arr[i];
            int j = lowerBound(arr, x);
            if (arr[j + m] == x) {
                return x;
            }
        }
        return arr[m * 3 + 2];
    }

    // lowerBound 返回最小的满足 nums[i] >= target 的下标 i
    // 如果数组为空，或者所有数都 < target，则返回 nums.length
    // 要求 nums 是非递减的，即 nums[i] <= nums[i + 1]
    // 原理见 https://www.bilibili.com/video/BV1AP41137w7/
    private int lowerBound(int[] nums, int target) {
        int left = -1;
        int right = nums.length; // 开区间 (left, right)
        while (left + 1 < right) { // 区间不为空
            // 循环不变量：
            // nums[left] < target
            // nums[right] >= target
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid; // 范围缩小到 (left, mid)
            } else {
                left = mid; // 范围缩小到 (mid, right)
            }
        }
        // 循环结束后 left+1 = right
        // 此时 nums[left] < target 而 nums[right] >= target
        // 所以 right 就是第一个 >= target 的元素下标
        return right;
    }
}
*/