package leetcode.medium;

import java.util.Arrays;

/**
 * 下一个更大元素 III
 * 给你一个正整数 n ，请你找出符合条件的最小整数，其由重新排列 n 中存在的每位数字组成，并且其值大于 n 。如果不存在这样的正整数，则返回 -1 。
 * 注意 ，返回的整数应当是一个 32 位整数 ，如果存在满足题意的答案，但不是 32 位整数 ，同样返回 -1 。
 * 示例 1：
 * 输入：n = 12
 * 输出：21
 * 示例 2：
 * 输入：n = 21
 * 输出：-1
 * 提示：
 * 1 <= n <= 231 - 1
 */
public class Solution_556 {
    public int nextGreaterElement(int n) {
        char[] chars = String.valueOf(n).toCharArray();
        int len = chars.length;
        for (int i = len - 1; i >= 0; i--) {
            int idx = i;
            char num = Character.MAX_VALUE;
            for (int j = i + 1; j < len; j++) {
                if (chars[i] < chars[j] && chars[j] < num) {
                    idx = j;
                    num = chars[j];
                }
            }
            if (idx != i) {
                char tmp = chars[i];
                chars[i] = chars[idx];
                chars[idx] = tmp;
                char[] copyOfRange = Arrays.copyOfRange(chars, i + 1, len);
                Arrays.sort(copyOfRange);
                for (int j = i + 1; j < len; j++) {
                    chars[j] = copyOfRange[j - i - 1];
                }
                long l = Long.parseLong(String.valueOf(chars));
                if (l > Integer.MAX_VALUE) {
                    return -1;
                } else {
                    return (int) l;
                }
            }
        }
        return -1;
    }
}

/*
class Solution {
    public int nextGreaterElement(int n) {
        char[] nums = Integer.toString(n).toCharArray();
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i < 0) {
            return -1;
        }

        int j = nums.length - 1;
        while (j >= 0 && nums[i] >= nums[j]) {
            j--;
        }
        swap(nums, i, j);
        reverse(nums, i + 1);
        long ans = Long.parseLong(new String(nums));
        return ans > Integer.MAX_VALUE ? -1 : (int) ans;
    }

    public void reverse(char[] nums, int begin) {
        int i = begin, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    public void swap(char[] nums, int i, int j) {
        char temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
*/
