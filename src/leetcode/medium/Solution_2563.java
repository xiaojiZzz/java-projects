package leetcode.medium;

import java.util.Arrays;

/**
 * 给你一个下标从 0 开始、长度为 n 的整数数组 nums ，和两个整数 lower 和 upper ，返回 公平数对的数目 。
 * 如果 (i, j) 数对满足以下情况，则认为它是一个 公平数对 ：
 * 0 <= i < j < n，且
 * lower <= nums[i] + nums[j] <= upper
 * 示例 1：
 * 输入：nums = [0,1,7,4,4,5], lower = 3, upper = 6
 * 输出：6
 * 解释：共计 6 个公平数对：(0,3)、(0,4)、(0,5)、(1,3)、(1,4) 和 (1,5) 。
 * 示例 2：
 * 输入：nums = [1,7,9,2,5], lower = 11, upper = 11
 * 输出：1
 * 解释：只有单个公平数对：(2,3) 。
 * 提示：
 * 1 <= nums.length <= 105
 * nums.length == n
 * -109 <= nums[i] <= 109
 * -109 <= lower <= upper <= 109
 */
public class Solution_2563 {
    public long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        int n = nums.length;
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int l = binarySearch(nums, i, lower - nums[i]);
            int r = binarySearch(nums, i, upper - nums[i] + 1);
            ans += r - l;
        }
        return ans;
    }

    private int binarySearch(int[] nums, int right, int x) {
        int left = 0;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}

/*
class Solution {
    public long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        long ans = 0;
        int l = nums.length;
        int r = nums.length;
        for (int j = 0; j < nums.length; j++) {
            while (r > 0 && nums[r - 1] > upper - nums[j]) {
                r--;
            }
            while (l > 0 && nums[l - 1] >= lower - nums[j]) {
                l--;
            }
            // 在方法一中，二分的结果必须 <= j，方法二同理
            ans += Math.min(r, j) - Math.min(l, j);
        }
        return ans;
    }
}
*/

/*
class Solution {
    public long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        return count(nums, upper) - count(nums, lower - 1);
    }

    private long count(int[] nums, int upper) {
        long res = 0;
        int j = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            while (j > i && nums[j] > upper - nums[i]) {
                j--;
            }
            if (j == i) {
                break;
            }
            res += j - i;
        }
        return res;
    }
}
*/

/*
class Solution {
    public long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        return count(nums, upper) - count(nums, lower - 1);
    }

    private long count(int[] nums, int upper) {
        long res = 0;
        int i = 0;
        int j = nums.length - 1;
        while (i < j) {
            if (nums[i] + nums[j] <= upper) {
                res += j - i;
                i++;
            } else {
                j--;
            }
        }
        return res;
    }
}
*/