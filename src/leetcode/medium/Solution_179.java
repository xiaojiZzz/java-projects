package leetcode.medium;

import java.util.Arrays;


/**
 * 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
 * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 * 示例 1：
 * 输入：nums = [10,2]
 * 输出："210"
 * 示例 2：
 * 输入：nums = [3,30,34,5,9]
 * 输出："9534330"
 */
public class Solution_179 {
    public String largestNumber(int[] nums) {
        int n = nums.length;
        String[] ss = new String[n];
        for (int i = 0; i < n; i++) {
            ss[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(ss, (s1, s2) -> (s2 + s1).compareTo(s1 + s2));
        String result = String.join("", ss);
        return result.charAt(0) == '0' ? "0" : result;
    }
}
