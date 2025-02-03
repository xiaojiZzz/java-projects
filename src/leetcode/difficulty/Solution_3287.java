package leetcode.difficulty;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 求出数组中最大序列值
 * 给你一个整数数组 nums 和一个 正 整数 k 。
 * 定义长度为 2 * x 的序列 seq 的 值 为：
 * (seq[0] OR seq[1] OR ... OR seq[x - 1]) XOR (seq[x] OR seq[x + 1] OR ... OR seq[2 * x - 1]).
 * 请你求出 nums 中所有长度为 2 * k 的子序列的 最大值 。
 * 示例 1：
 * 输入：nums = [2,6,7], k = 1
 * 输出：5
 * 解释：
 * 子序列 [2, 7] 的值最大，为 2 XOR 7 = 5 。
 * 示例 2：
 * 输入：nums = [4,2,5,6,7], k = 2
 * 输出：2
 * 解释：
 * 子序列 [4, 5, 6, 7] 的值最大，为 (4 OR 5) XOR (6 OR 7) = 2 。
 * 提示：
 * 2 <= nums.length <= 400
 * 1 <= nums[i] < 27
 * 1 <= k <= nums.length / 2
 */
public class Solution_3287 {
    public int maxValue(int[] nums, int k) {
        List<Set<Integer>> A = findORs(nums, k);
        List<Set<Integer>> B = findORs(reverse(nums), k);
        int mx = 0;
        for (int i = k - 1; i < nums.length - k; i++) {
            for (int a : A.get(i)) {
                for (int b : B.get(nums.length - i - 2)) {
                    mx = Math.max(mx, a ^ b);
                }
            }
        }
        return mx;
    }

    private List<Set<Integer>> findORs(int[] nums, int k) {
        List<Set<Integer>> dp = new ArrayList<>();
        List<Set<Integer>> prev = new ArrayList<>();
        for (int i = 0; i <= k; i++) {
            prev.add(new HashSet<>());
        }
        prev.get(0).add(0);
        for (int i = 0; i < nums.length; i++) {
            for (int j = Math.min(k - 1, i + 1); j >= 0; j--) {
                for (int x : prev.get(j)) {
                    prev.get(j + 1).add(x | nums[i]);
                }
            }
            dp.add(new HashSet<>(prev.get(k)));
        }
        return dp;
    }

    private int[] reverse(int[] nums) {
        int[] reversed = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            reversed[i] = nums[nums.length - 1 - i];
        }
        return reversed;
    }
}

/*
class Solution {
    public int maxValue(int[] nums, int k) {
        final int MX = 1 << 7;
        int n = nums.length;
        boolean[][] suf = new boolean[n - k + 1][];
        boolean[][] f = new boolean[k + 1][MX];
        f[0][0] = true;
        for (int i = n - 1; i >= k; i--) {
            int v = nums[i];
            // 注意当 i 比较大的时候，循环次数应和 i 有关，因为更大的 j，对应的 f[j] 全为 false
            for (int j = Math.min(k - 1, n - 1 - i); j >= 0; j--) {
                for (int x = 0; x < MX; x++) {
                    if (f[j][x]) {
                        f[j + 1][x | v] = true;
                    }
                }
            }
            if (i <= n - k) {
                suf[i] = f[k].clone();
            }
        }

        int ans = 0;
        f = new boolean[k + 1][MX];
        f[0][0] = true;
        for (int i = 0; i < n - k; i++) {
            int v = nums[i];
            for (int j = Math.min(k - 1, i); j >= 0; j--) {
                for (int x = 0; x < MX; x++) {
                    if (f[j][x]) {
                        f[j + 1][x | v] = true;
                    }
                }
            }
            if (i < k - 1) {
                continue;
            }
            // 这里 f[k] 就是 pre[i]
            for (int x = 0; x < MX; x++) {
                if (f[k][x]) {
                    for (int y = 0; y < MX; y++) {
                        if (suf[i + 1][y]) {
                            ans = Math.max(ans, x ^ y);
                        }
                    }
                }
            }
            if (ans == MX - 1) {
                return ans;
            }
        }
        return ans;
    }
}
*/

/*
class Solution {
    private static final int BIT_WIDTH = 7;

    public int maxValue(int[] nums, int k) {
        final int MX = 1 << BIT_WIDTH;
        int n = nums.length;
        int k2 = Math.min(k, BIT_WIDTH); // 至多选 k2 个数

        boolean[][] suf = new boolean[n - k + 1][];
        boolean[][] f = new boolean[k2 + 1][MX];
        f[0][0] = true;
        int[] maxI = new int[MX];
        int[] cnt = new int[MX];
        for (int i = n - 1; i >= k; i--) {
            int v = nums[i];
            for (int j = Math.min(k2 - 1, n - 1 - i); j >= 0; j--) {
                for (int x = 0; x < MX; x++) {
                    if (f[j][x]) {
                        f[j + 1][x | v] = true;
                    }
                }
            }
            if (i <= n - k) {
                suf[i] = f[k2].clone();
            }
            // 枚举 v 的超集
            for (int s = v; s < MX; s = (s + 1) | v) {
                if (++cnt[s] == k) {
                    // 从 n-1 开始遍历，至少要遍历到 i 才有可能找到 k 个数 OR 等于 s
                    maxI[s] = i;
                }
            }
        }

        int ans = 0;
        boolean[][] pre = new boolean[k2 + 1][MX];
        pre[0][0] = true;
        int[] minI = new int[MX];
        Arrays.fill(minI, Integer.MAX_VALUE);
        Arrays.fill(cnt, 0);
        int[] a = new int[MX];
        int[] b = new int[MX];
        for (int i = 0; i < n - k; i++) {
            int v = nums[i];
            for (int j = Math.min(k2 - 1, i); j >= 0; j--) {
                for (int x = 0; x < MX; x++) {
                    if (pre[j][x]) {
                        pre[j + 1][x | v] = true;
                    }
                }
            }
            // 枚举 v 的超集
            for (int s = v; s < MX; s = (s + 1) | v) {
                if (++cnt[s] == k) {
                    // 从 0 开始遍历，至少要遍历到 i 才有可能找到 k 个数 OR 等于 s
                    minI[s] = i;
                }
            }
            if (i < k - 1) {
                continue;
            }
            int na = 0;
            int nb = 0;
            for (int x = 0; x < MX; x++) {
                if (pre[k2][x] && minI[x] <= i) {
                    a[na++] = x;
                }
                if (suf[i + 1][x] && maxI[x] > i) {
                    b[nb++] = x;
                }
            }
            ans = Math.max(ans, findMaximumXOR(a, na, b, nb));
            if (ans == MX - 1) {
                return ans;
            }
        }
        return ans;
    }

    // 421. 数组中两个数的最大异或值
    // 改成两个数组的最大异或值，做法是类似的，仍然可以用【试填法】解决
    private int findMaximumXOR(int[] a, int n, int[] b, int m) {
        int ans = 0;
        int mask = 0;
        boolean[] seen = new boolean[1 << BIT_WIDTH];
        for (int i = BIT_WIDTH - 1; i >= 0; i--) { // 从最高位开始枚举
            mask |= 1 << i;
            int newAns = ans | (1 << i); // 这个比特位可以是 1 吗？
            Arrays.fill(seen, false);
            for (int j = 0; j < n; j++) {
                seen[a[j] & mask] = true; // 低于 i 的比特位置为 0
            }
            for (int j = 0; j < m; j++) {
                int x = b[j] & mask; // 低于 i 的比特位置为 0
                if (seen[newAns ^ x]) {
                    ans = newAns; // 这个比特位可以是 1
                    break;
                }
            }
        }
        return ans;
    }
}
*/