package leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 优质数对的总数 II
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
 * 1 <= n, m <= 105
 * 1 <= nums1[i], nums2[j] <= 106
 * 1 <= k <= 103
 */
public class Solution_3164 {
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

/*
class Solution {
    // 使用 merge 方法
    public long numberOfPairs(int[] nums1, int[] nums2, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : nums1) {
            if (x % k != 0) {
                continue;
            }
            x /= k;
            for (int d = 1; d * d <= x; d++) { // 枚举因子
                if (x % d > 0) {
                    continue;
                }
                cnt.merge(d, 1, Integer::sum); // cnt[d]++
                if (d * d < x) {
                    cnt.merge(x / d, 1, Integer::sum); // cnt[x/d]++
                }
            }
        }

        long ans = 0;
        for (int x : nums2) {
            ans += cnt.getOrDefault(x, 0);
        }
        return ans;
    }
}
*/

/*
class Solution {
    public long numberOfPairs(int[] nums1, int[] nums2, int k) {
        Map<Integer, Integer> cnt1 = new HashMap<>();
        for (int x : nums1) {
            if (x % k == 0) {
                cnt1.merge(x / k, 1, Integer::sum); // cnt1[x/k]++
            }
        }
        if (cnt1.isEmpty()) {
            return 0;
        }

        Map<Integer, Integer> cnt2 = new HashMap<>();
        for (int x : nums2) {
            cnt2.merge(x, 1, Integer::sum); // cnt2[x]++
        }

        long ans = 0;
        int u = Collections.max(cnt1.keySet());
        for (Map.Entry<Integer, Integer> e : cnt2.entrySet()) {
            int x = e.getKey();
            int cnt = e.getValue();
            int s = 0;
            for (int y = x; y <= u; y += x) { // 枚举 x 的倍数
                if (cnt1.containsKey(y)) {
                    s += cnt1.get(y);
                }
            }
            ans += (long) s * cnt;
        }
        return ans;
    }
}
*/