package leetcode.simple;


/**
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 请必须使用时间复杂度为 O(logn) 的算法。
 * 示例 1:
 * 输入: nums = [1,3,5,6], target = 5
 * 输出: 2
 * 示例 2:
 * 输入: nums = [1,3,5,6], target = 2
 * 输出: 1
 */
public class Solution_35 { //二分查找
    public int searchInsert(int[] nums, int target) {
        int a = 0;
        int b = nums.length - 1;
        int c = 0;
        while (a <= b) {
            c = (a + b) / 2;
            if (target < nums[c]) {
                b = c - 1;
            } else if (target > nums[c]) {
                a = c + 1;
            } else {
                return c;
            }
        }
        return a;
    }
}
