package leetcode.medium;


/**
 * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
 * 示例 1:
 * 输入: nums = [1,2,3,4,5,6,7], k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右轮转 1 步: [7,1,2,3,4,5,6]
 * 向右轮转 2 步: [6,7,1,2,3,4,5]
 * 向右轮转 3 步: [5,6,7,1,2,3,4]
 * 示例 2:
 * 输入：nums = [-1,-100,3,99], k = 2
 * 输出：[3,99,-1,-100]
 * 解释:
 * 向右轮转 1 步: [99,-1,-100,3]
 * 向右轮转 2 步: [3,99,-1,-100]
 */
public class Solution_189 {
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        if (len <= k) {
            k = k % len;
        }
        if (k == 0) {
            return;
        }
        int[] tmp = new int[k];
        for (int i = len - k; i < len; i++) {
            tmp[i - len + k] = nums[i];
        }
        for (int i = len - 1; i >= k; i--) {
            nums[i] = nums[i - k];
        }
        for (int i = 0; i < k; i++) {
            nums[i] = tmp[i];
        }
    }
}

/*
class Solution {
    public void rotate(int[] nums, int k) {
        int l = nums.length;
        rotateNums(nums, 0, l - 1);
        rotateNums(nums, 0, k % l - 1);
        rotateNums(nums, k % l, l - 1);

    }

    public void rotateNums(int[] nums, int left, int right) {
        int temp = 0;
        while (left <= right) {
            temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }
}
*/
