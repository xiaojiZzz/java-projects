package leetcode.simple;


/**
 * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
 * 示例 1：
 * 输入：nums = [-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 * 解释：平方后，数组变为 [16,1,0,9,100]
 * 排序后，数组变为 [0,1,9,16,100]
 * 示例 2：
 * 输入：nums = [-7,-3,2,3,11]
 * 输出：[4,9,9,49,121]
 */
public class Solution_977 {
    public int[] sortedSquares(int[] nums) {
        int i = nums.length - 1;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] >= 0) {
                i = j;
                break;
            }
        }
        int[] ans = new int[nums.length];
        int j = i - 1;
        for (int k = 0; k < nums.length; k++) {
            if (j >= 0 && i < nums.length) {
                if (-nums[j] < nums[i]) {
                    ans[k] = nums[j] * nums[j];
                    j--;
                } else {
                    ans[k] = nums[i] * nums[i];
                    i++;
                }
            } else if (j >= 0) {
                ans[k] = nums[j] * nums[j];
                j--;
            } else {
                ans[k] = nums[i] * nums[i];
                i++;
            }
        }
        return ans;
    }
}

/*
class Solution {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 0, j = n - 1, pos = n - 1; i <= j; ) {
            if (nums[i] * nums[i] > nums[j] * nums[j]) {
                ans[pos] = nums[i] * nums[i];
                ++i;
            } else {
                ans[pos] = nums[j] * nums[j];
                --j;
            }
            --pos;
        }
        return ans;
    }
}
*/
