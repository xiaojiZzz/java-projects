package leetcode.medium;

import java.util.HashMap;
import java.util.Map;


/**
 * 给你一个由 不同 正整数组成的数组 nums ，请你返回满足 a * b = c * d 的元组 (a, b, c, d) 的数量。
 * 其中 a、b、c 和 d 都是 nums 中的元素，且 a != b != c != d 。
 * 示例 1：
 * 输入：nums = [2,3,4,6]
 * 输出：8
 * 解释：存在 8 个满足题意的元组：
 * (2,6,3,4) , (2,6,4,3) , (6,2,3,4) , (6,2,4,3)
 * (3,4,2,6) , (4,3,2,6) , (3,4,6,2) , (4,3,6,2)
 * 示例 2：
 * 输入：nums = [1,2,4,5,10]
 * 输出：16
 * 解释：存在 16 个满足题意的元组：
 * (1,10,2,5) , (1,10,5,2) , (10,1,2,5) , (10,1,5,2)
 * (2,5,1,10) , (2,5,10,1) , (5,2,1,10) , (5,2,10,1)
 * (2,10,4,5) , (2,10,5,4) , (10,2,4,5) , (10,2,4,5)
 * (4,5,2,10) , (4,5,10,2) , (5,4,2,10) , (5,4,10,2)
 */
public class Solution_1726 {
    public int tupleSameProduct(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int key = nums[i] * nums[j];
                cnt.put(key, cnt.getOrDefault(key, 0) + 1);
            }
        }
        int ans = 0;
        for (Integer v : cnt.values()) {
            ans += v * (v - 1) * 4;
        }
        return ans;
    }
}

/*
class Solution {
    public int tupleSameProduct(int[] nums) {
        if (nums.length < 4) return 0;
        int ans = 0;
        int n = nums.length;
        Arrays.sort(nums);

        for (int i = 0; i < n - 3; i++) {
            for (int j = i + 3; j < n; j++) {
                int x = nums[i] * nums[j];
                int p = i + 1, q = j - 1;
                while (p < q) {
                    int y = nums[p] * nums[q];
                    if (y == x) {
                        ans++;
                        p++;
                        q--;
                    } else if (y > x) {
                        q--;
                    } else {
                        p++;
                    }
                }
            }
        }
        return ans << 3;
    }
}
*/
