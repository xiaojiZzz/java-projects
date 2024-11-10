package leetcode.medium;

/**
 * 有序数组中的单一元素
 * 给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。
 * 请你找出并返回只出现一次的那个数。
 * 你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。
 * 示例 1:
 * 输入: nums = [1,1,2,3,3,4,4,8,8]
 * 输出: 2
 * 示例 2:
 * 输入: nums =  [3,3,7,7,10,11,11]
 * 输出: 10
 * 提示:
 * 1 <= nums.length <= 105
 * 0 <= nums[i] <= 105
 */
public class Solution_540 {
    public int singleNonDuplicate(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if ((mid & 1) == 1) {
                mid--;
            }
            if (nums[mid] == nums[mid + 1]) {
                left = mid + 2;
            } else {
                right = mid;
            }
        }
        return nums[left];
    }
}

/*
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            // 另一种写法
            if (nums[mid] == nums[mid ^ 1]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return nums[low];
    }
}
*/

/*
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int left = -1;
        int right = nums.length / 2;
        while (left + 1 < right) {
            int mid = (left + right) >>> 1;
            if (nums[mid * 2] != nums[mid * 2 + 1]) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return nums[right * 2];
    }
}
*/