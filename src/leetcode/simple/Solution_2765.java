package leetcode.simple;


/**
 * 给你一个下标从 0 开始的整数数组 nums 。如果 nums 中长度为 m 的子数组 s 满足以下条件，我们称它是一个 交替子数组 ：
 * m 大于 1 。
 * s1 = s0 + 1 。
 * 下标从 0 开始的子数组 s 与数组 [s0, s1, s0, s1,...,s(m-1) % 2] 一样。
 * 也就是说，s1 - s0 = 1 ，s2 - s1 = -1 ，s3 - s2 = 1 ，s4 - s3 = -1 ，以此类推，直到 s[m - 1] - s[m - 2] = (-1)m 。
 * 请你返回 nums 中所有 交替 子数组中，最长的长度，如果不存在交替子数组，请你返回 -1 。
 * 子数组是一个数组中一段连续 非空 的元素序列。
 * 示例 1：
 * 输入：nums = [2,3,4,3,4]
 * 输出：4
 * 解释：交替子数组有 [3,4] ，[3,4,3] 和 [3,4,3,4] 。最长的子数组为 [3,4,3,4] ，长度为4 。
 * 示例 2：
 * 输入：nums = [4,5,6]
 * 输出：2
 * 解释：[4,5] 和 [5,6] 是仅有的两个交替子数组。它们长度都为 2 。
 */
public class Solution_2765 {
    public int alternatingSubarray(int[] nums) {
        int l = 0, r = 1;
        int len = nums.length;
        int ans = 0;
        while (r < len) {
            if (nums[r] - nums[r - 1] == Math.pow(-1, r - l + 1)) {
                r++;
            } else {
                ans = Math.max(ans, r - l);
                l++;
                r = l + 1;
            }
        }
        ans = Math.max(ans, r - l);
        return ans > 1 ? ans : -1;
    }
}

/*
class Solution {
    public int alternatingSubarray(int[] nums) {
        int n = nums.length;
        int result = 1;
        int count = 1;
        for (int i = 0; i < n; i++) {
            int curNum = nums[i];
            while (i + 1 < n && nums[i + 1] == (count % 2 == 0 ? curNum : curNum + 1)) {
                count++;
                i++;
            }
            if (count != 1) {
                i--;
                result = Math.max(result, count);
                count = 1;
            }

        }
        return result == 1 ? -1 : result;
    }
}
*/

/*
class Solution {
    public int alternatingSubarray(int[] nums) {
        int n = nums.length;
        int l = 0, r = 1;
        int ans = 0;
        while (r < n) {
            while (r < n && nums[r] - nums[r - 1] == Math.pow(-1, r - l + 1)) {
                r++;
            }
            if (r - l != 1) {
                ans = Math.max(ans, r - l);
                l = r - 1;
            } else {
                l++;
                r++;
            }
        }
        return ans > 1 ? ans : -1;
    }
}
*/
