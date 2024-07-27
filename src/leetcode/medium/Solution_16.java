package leetcode.medium;

import java.util.Arrays;


/**
 * 给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个整数，使它们的和与 target 最接近。
 * 返回这三个数的和。
 * 假定每组输入只存在恰好一个解。
 * 示例 1：
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 * 示例 2：
 * 输入：nums = [0,0,0], target = 1
 * 输出：0
 */
public class Solution_16 {
    public int threeSumClosest(int[] nums, int target) {
        int len = nums.length, diff = Integer.MAX_VALUE, res = 0;
        Arrays.sort(nums);
        for (int i = 0; i < len - 2; i++) {
            int left = i + 1, right = len - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > target) {
                    if (sum - target < diff) {
                        diff = sum - target;
                        res = sum;
                    }
                    right--;
                } else if (sum == target) {
                    return target;
                } else {
                    if (target - sum < diff) {
                        diff = target - sum;
                        res = sum;
                    }
                    left++;
                }
            }
        }
        return res;
    }
}
