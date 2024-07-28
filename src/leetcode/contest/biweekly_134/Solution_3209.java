package leetcode.contest.biweekly_134;

/**
 * 子数组按位与值为 K 的数目
 * 给你一个整数数组 nums 和一个整数 k ，请你返回 nums 中有多少个子数组
 * 满足：子数组中所有元素按位 AND 的结果为 k 。
 * 示例 1：
 * 输入：nums = [1,1,1], k = 1
 * 输出：6
 * 解释：
 * 所有子数组都只含有元素 1 。
 * 示例 2：
 * 输入：nums = [1,1,2], k = 1
 * 输出：3
 * 解释：
 * 按位 AND 值为 1 的子数组包括：[1,1,2], [1,1,2], [1,1,2] 。
 * 示例 3：
 * 输入：nums = [1,2,3], k = 2
 * 输出：2
 * 解释：
 * 按位 AND 值为 2 的子数组包括：[1,2,3], [1,2,3] 。
 * 提示：
 * 1 <= nums.length <= 105
 * 0 <= nums[i], k <= 109
 */
public class Solution_3209 {
    public long countSubarrays(int[] nums, int k) {
        int n = nums.length;
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            for (int j = i - 1; j >= 0 && (nums[j] & x) != nums[j]; j--) {
                nums[j] &= x;
            }
            ans += binarySearch(nums, i, k + 1) - binarySearch(nums, i, k);
        }
        return ans;
    }

    private int binarySearch(int[] nums, int right, int target) {
        int left = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}

/*
class Solution {
    public long countSubarrays(int[] nums, int k) {
        long ans = 0;
        int left = 0;
        int right = 0;
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            for (int j = i - 1; j >= 0 && (nums[j] & x) != nums[j]; j--) {
                nums[j] &= x;
            }
            while (left <= i && nums[left] < k) {
                left++;
            }
            while (right <= i && nums[right] <= k) {
                right++;
            }
            ans += right - left;
        }
        return ans;
    }
}
*/

/*
class Solution {
    public long countSubarrays(int[] nums, int k) {
        long ans = 0;
        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            cnt += x == k ? 1 : 0;
            for (int j = i - 1; j >= 0 && (nums[j] & x) != nums[j]; j--) {
                cnt -= nums[j] == k ? 1 : 0;
                nums[j] &= x;
                cnt += nums[j] == k ? 1 : 0;
            }
            ans += cnt;
        }
        return ans;
    }
}
*/