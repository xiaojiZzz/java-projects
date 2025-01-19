package leetcode.simple;

/**
 * 或值至少 K 的最短子数组 I
 * 给你一个 非负 整数数组 nums 和一个整数 k 。
 * 如果一个数组中所有元素的按位或运算 OR 的值 至少 为 k ，那么我们称这个数组是 特别的 。
 * 请你返回 nums 中 最短特别非空子数组的长度，如果特别子数组不存在，那么返回 -1 。
 * 示例 1：
 * 输入：nums = [1,2,3], k = 2
 * 输出：1
 * 解释：
 * 子数组 [3] 的按位 OR 值为 3 ，所以我们返回 1 。
 * 注意，[2] 也是一个特别子数组。
 * 示例 2：
 * 输入：nums = [2,1,8], k = 10
 * 输出：3
 * 解释：
 * 子数组 [2,1,8] 的按位 OR 值为 11 ，所以我们返回 3 。
 * 示例 3：
 * 输入：nums = [1,2], k = 0
 * 输出：1
 * 解释：
 * 子数组 [1] 的按位 OR 值为 1 ，所以我们返回 1 。
 * 提示：
 * 1 <= nums.length <= 50
 * 0 <= nums[i] <= 50
 * 0 <= k < 64
 */
public class Solution_3095 {
    public int minimumSubarrayLength(int[] nums, int k) {
        int n = nums.length;
        int[] cur = new int[6];
        int left = 0, ans = n + 1;
        for (int right = 0; right < n; right++) {
            add(cur, nums[right]);
            while (left <= right && check(cur, k)) {
                ans = Math.min(ans, right - left + 1);
                sub(cur, nums[left]);
                left++;
            }
        }
        return ans == n + 1 ? -1 : ans;
    }

    private boolean check(int[] cur, int k) {
        int n = cur.length;
        int curNum = 0;
        for (int i = 0; i < n; i++) {
            curNum += Math.pow(2, i) * (cur[i] > 0 ? 1 : 0);
        }
        return curNum >= k;
    }

    private void add(int[] cur, int num) {
        for (int i = 0; num > 0; i++) {
            cur[i] += num % 2;
            num /= 2;
        }
    }

    private void sub(int[] cur, int num) {
        for (int i = 0; num > 0; i++) {
            cur[i] -= num % 2;
            num /= 2;
        }
    }
}

/*
class Solution {
    public int minimumSubarrayLength(int[] nums, int k) {
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            if (x >= k) {
                return 1;
            }
            for (int j = i - 1; j >= 0 && (nums[j] | x) != nums[j]; j--) {
                nums[j] |= x;
                if (nums[j] >= k) {
                    ans = Math.min(ans, i - j + 1);
                }
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
*/

/*
class Solution {
    public int minimumSubarrayLength(int[] nums, int k) {
        int ans = Integer.MAX_VALUE;
        int left = 0;
        int bottom = 0;
        int rightOr = 0;
        for (int right = 0; right < nums.length; right++) {
            rightOr |= nums[right];
            while (left <= right && (nums[left] | rightOr) >= k) {
                ans = Math.min(ans, right - left + 1);
                left++;
                if (bottom < left) {
                    // 重新构建一个栈
                    for (int i = right - 1; i >= left; i--) {
                        nums[i] |= nums[i + 1];
                    }
                    bottom = right;
                    rightOr = 0;
                }
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
*/