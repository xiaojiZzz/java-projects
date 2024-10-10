package leetcode.simple;

/**
 * 优质数对的总数 I
 * 给你两个整数数组 nums1 和 nums2，长度分别为 n 和 m。同时给你一个正整数 k。
 * 如果 nums1[i] 可以被 nums2[j] * k 整除，则称数对 (i, j) 为 优质数对（0 <= i <= n - 1, 0 <= j <= m - 1）。
 * 返回 优质数对 的总数。
 * 示例 1：
 * 输入：nums1 = [1,3,4], nums2 = [1,3,4], k = 1
 * 输出：5
 * 解释：
 * 5个优质数对分别是 (0, 0), (1, 0), (1, 1), (2, 0), 和 (2, 2)。
 * 示例 2：
 * 输入：nums1 = [1,2,4,12], nums2 = [2,4], k = 3
 * 输出：2
 * 解释：
 * 2个优质数对分别是 (3, 0) 和 (3, 1)。
 * 提示：
 * 1 <= n, m <= 50
 * 1 <= nums1[i], nums2[j] <= 50
 * 1 <= k <= 50
 */
public class Solution_3162 {
    public int numberOfPairs(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length;
        int ans = 0;
        for (int value : nums1) {
            for (int i : nums2) {
                if (value % (i * k) == 0) {
                    ans++;
                }
            }
        }
        return ans;
    }
}

/*
class Solution {
    public long numberOfPairs(int[] nums1, int[] nums2, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) {
            if (num % k == 0) {
                num /= k;
                for (int d = 1; d * d <= num; d++) {
                    if (num % d == 0) {
                        map.put(num / d, map.getOrDefault(num / d, 0) + 1);
                        map.put(d, map.getOrDefault(d, 0) + 1);
                    }
                    if (d * d == num) {
                        map.put(d, map.get(d) - 1);
                    }
                }
            }
        }
        long ans = 0;
        for (int num : nums2) {
            ans += map.getOrDefault(num, 0);
        }
        return ans;
    }
}
*/