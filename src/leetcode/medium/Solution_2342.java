package leetcode.medium;

import java.util.HashMap;


/**
 * 给你一个下标从 0 开始的数组 nums ，数组中的元素都是 正 整数。请你选出两个下标 i 和 j（i != j），
 * 且 nums[i] 的数位和 与  nums[j] 的数位和相等。
 * 请你找出所有满足条件的下标 i 和 j ，找出并返回 nums[i] + nums[j] 可以得到的 最大值 。
 * 示例 1：
 * 输入：nums = [18,43,36,13,7]
 * 输出：54
 * 解释：满足条件的数对 (i, j) 为：
 * - (0, 2) ，两个数字的数位和都是 9 ，相加得到 18 + 36 = 54 。
 * - (1, 4) ，两个数字的数位和都是 7 ，相加得到 43 + 7 = 50 。
 * 所以可以获得的最大和是 54 。
 * 示例 2：
 * 输入：nums = [10,12,19,14]
 * 输出：-1
 * 解释：不存在满足条件的数对，返回 -1 。
 */
public class Solution_2342 {
    public int maximumSum(int[] nums) {
        int max = -1, len = nums.length;
        HashMap<Integer, Integer> map = new HashMap();
        for (int i = 0; i < len; i++) {
            int sum = 0, num = nums[i];
            while (num > 0) {
                sum += num % 10;
                num /= 10;
            }
            if (map.containsKey(sum)) {
                max = Math.max(max, nums[map.get(sum)] + nums[i]);
                if (nums[i] > nums[map.get(sum)]) {
                    map.put(sum, i);
                }
            } else {
                map.put(sum, i);
            }
        }
        return max;
    }
}

/*
class Solution {
    public int maximumSum(int[] nums) {
        int ans = -1;
        int[] mx = new int[82]; // 至多 9 个 9 相加
        for (int num : nums) {
            int s = 0; // num 的数位和
            for (int x = num; x > 0; x /= 10) { // 枚举 num 的每个数位
                s += x % 10;
            }
            if (mx[s] > 0) { // 说明左边也有数位和等于 s 的元素
                ans = Math.max(ans, mx[s] + num); // 更新答案的最大值
            }
            mx[s] = Math.max(mx[s], num); // 维护数位和等于 s 的最大元素
        }
        return ans;
    }
}
*/
