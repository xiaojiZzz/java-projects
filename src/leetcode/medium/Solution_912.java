package leetcode.medium;

import java.util.Arrays;


/**
 * 给你一个整数数组 nums，请你将该数组升序排列。
 * 示例 1：
 * 输入：nums = [5,2,3,1]
 * 输出：[1,2,3,5]
 * 示例 2：
 * 输入：nums = [5,1,1,2,0,0]
 * 输出：[0,0,1,1,2,5]
 */
public class Solution_912 {
    public int[] sortArray(int[] nums) {
        Arrays.sort(nums);
        return nums;
    }
    // 用快排、归并排序、推排序等，详细见算法包
}
