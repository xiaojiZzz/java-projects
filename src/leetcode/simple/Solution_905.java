package leetcode.simple;


/**
 * 给你一个整数数组 nums，将 nums 中的的所有偶数元素移动到数组的前面，后跟所有奇数元素。
 * 返回满足此条件的 任一数组 作为答案。
 * 示例 1：
 * 输入：nums = [3,1,2,4]
 * 输出：[2,4,3,1]
 * 解释：[4,2,3,1]、[2,4,1,3] 和 [4,2,1,3] 也会被视作正确答案。
 * 示例 2：
 * 输入：nums = [0]
 * 输出：[0]
 */
public class Solution_905 {
    public int[] sortArrayByParity(int[] nums) {
        int r = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] % 2 == 1 && r > i) {
                int tmp = nums[r];
                nums[r] = nums[i];
                nums[i] = tmp;
                r--;
            }
            if (r <= i) {
                break;
            }
        }
        return nums;
    }
}

/*
class Solution {
    public int[] sortArrayByParity(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        int left = 0, right = n - 1;
        for (int num : nums) {
            if (num % 2 == 0) {
                res[left++] = num;
            } else {
                res[right--] = num;
            }
        }
        return res;
    }
}
*/
