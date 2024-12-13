package leetcode.difficulty;

import java.util.PriorityQueue;

/**
 * K 次乘运算后的最终数组 II
 * 给你一个整数数组 nums ，一个整数 k  和一个整数 multiplier 。
 * 你需要对 nums 执行 k 次操作，每次操作中：
 * 找到 nums 中的 最小 值 x ，如果存在多个最小值，选择最 前面 的一个。
 * 将 x 替换为 x * multiplier 。
 * k 次操作以后，你需要将 nums 中每一个数值对 109 + 7 取余。
 * 请你返回执行完 k 次乘运算以及取余运算之后，最终的 nums 数组。
 * 示例 1：
 * 输入：nums = [2,1,3,5,6], k = 5, multiplier = 2
 * 输出：[8,4,6,5,6]
 * 解释：
 * 操作	结果
 * 1 次操作后	[2, 2, 3, 5, 6]
 * 2 次操作后	[4, 2, 3, 5, 6]
 * 3 次操作后	[4, 4, 3, 5, 6]
 * 4 次操作后	[4, 4, 6, 5, 6]
 * 5 次操作后	[8, 4, 6, 5, 6]
 * 取余操作后	[8, 4, 6, 5, 6]
 * 示例 2：
 * 输入：nums = [100000,2000], k = 2, multiplier = 1000000
 * 输出：[999999307,999999993]
 * 解释：
 * 操作	结果
 * 1 次操作后	[100000, 2000000000]
 * 2 次操作后	[100000000000, 2000000000]
 * 取余操作后	[999999307, 999999993]
 * 提示：
 * 1 <= nums.length <= 104
 * 1 <= nums[i] <= 109
 * 1 <= k <= 109
 * 1 <= multiplier <= 106
 */
public class Solution_3266 {
    private long quickMul(long x, long y, long m) {
        long res = 1;
        while (y > 0) {
            if ((y & 1) == 1) {
                res = (res * x) % m;
            }
            y >>= 1;
            x = (x * x) % m;
        }
        return res;
    }

    public int[] getFinalState(int[] nums, int k, int multiplier) {
        if (multiplier == 1) {
            return nums;
        }
        int n = nums.length, mx = 0;
        long m = 1000000007L;
        PriorityQueue<long[]> v = new PriorityQueue<>((x, y) -> {
            if (x[0] != y[0]) {
                return Long.compare(x[0], y[0]);
            } else {
                return Long.compare(x[1], y[1]);
            }
        });
        for (int i = 0; i < n; i++) {
            mx = Math.max(mx, nums[i]);
            v.offer(new long[]{nums[i], i});
        }
        for (; v.peek()[0] < mx && k > 0; k--) {
            long[] x = v.poll();
            x[0] *= multiplier;
            v.offer(x);
        }
        for (int i = 0; i < n; i++) {
            long[] x = v.poll();
            int t = k / n + (i < k % n ? 1 : 0);
            nums[(int)x[1]] = (int)((x[0] % m) * quickMul(multiplier, t, m) % m);
        }
        return nums;
    }
}

/*
// 进一步优化，用 while 循环来使得每个数 >= mx，而不需要使用优先队列
class Solution {
    private static final int MOD = 1_000_000_007;

    public int[] getFinalState(int[] nums, int k, int multiplier) {
        if (multiplier == 1) { // 数组不变
            return nums;
        }

        int n = nums.length;
        int mx = 0;
        for (int x : nums) {
            mx = Math.max(mx, x);
        }

        // 每个数直接暴力操作到 >= mx
        long[] a = new long[n];
        int left = k;
        outer:
        for (int i = 0; i < n; i++) {
            long x = nums[i];
            while (x < mx) {
                x *= multiplier;
                if (--left < 0) {
                    break outer;
                }
            }
            a[i] = x;
        }

        if (left < 0) {
            // 暴力模拟
            PriorityQueue<long[]> pq = new PriorityQueue<>((p, q) -> p[0] != q[0] ? Long.compare(p[0], q[0]) : Long.compare(p[1], q[1]));
            for (int i = 0; i < n; i++) {
                pq.offer(new long[]{nums[i], i});
            }
            while (k-- > 0) {
                long[] p = pq.poll();
                p[0] *= multiplier;
                pq.offer(p);
            }
            while (!pq.isEmpty()) {
                long[] p = pq.poll();
                nums[(int) p[1]] = (int) (p[0] % MOD);
            }
            return nums;
        }

        Integer[] ids = new Integer[n];
        Arrays.setAll(ids, i -> i);
        Arrays.sort(ids, (i, j) -> Long.compare(a[i], a[j]));

        // 剩余的操作可以直接用公式计算
        k = left;
        long pow1 = pow(multiplier, k / n);
        long pow2 = pow1 * multiplier % MOD;
        for (int i = 0; i < n; i++) {
            int j = ids[i];
            nums[j] = (int) (a[j] % MOD * (i < k % n ? pow2 : pow1) % MOD);
        }
        return nums;
    }

    private long pow(long x, int n) {
        long res = 1;
        for (; n > 0; n /= 2) {
            if (n % 2 > 0) {
                res = res * x % MOD;
            }
            x = x * x % MOD;
        }
        return res;
    }
}
*/