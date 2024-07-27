package leetcode.medium;


/**
 * 给你一个长度为 n 、下标从 0 开始的整数数组 nums ，表示收集不同巧克力的成本。每个巧克力都对应一个不同的类型，
 * 最初，位于下标 i 的巧克力就对应第 i 个类型。
 * 在一步操作中，你可以用成本 x 执行下述行为：
 * 同时修改所有巧克力的类型，将巧克力的类型 ith 修改为类型 ((i + 1) mod n)th。
 * 假设你可以执行任意次操作，请返回收集所有类型巧克力所需的最小成本。
 * 示例 1：
 * 输入：nums = [20,1,15], x = 5
 * 输出：13
 * 解释：最开始，巧克力的类型分别是 [0,1,2] 。我们可以用成本 1 购买第 1 个类型的巧克力。
 * 接着，我们用成本 5 执行一次操作，巧克力的类型变更为 [1,2,0] 。我们可以用成本 1 购买第 2 个类型的巧克力。
 * 然后，我们用成本 5 执行一次操作，巧克力的类型变更为 [2,0,1] 。我们可以用成本 1 购买第 0 个类型的巧克力。
 * 因此，收集所有类型的巧克力需要的总成本是 (1 + 5 + 1 + 5 + 1) = 13 。可以证明这是一种最优方案。
 * 示例 2：
 * 输入：nums = [1,2,3], x = 4
 * 输出：6
 * 解释：我们将会按最初的成本收集全部三个类型的巧克力，而不需执行任何操作。因此，收集所有类型的巧克力需要的总成本是 1 + 2 + 3 = 6 。
 */
public class Solution_2735 {
    public long minCost(int[] nums, int x) {
        int n = nums.length;
        int[][] f = new int[n][n];
        for (int i = 0; i < n; ++i) {
            f[i][0] = nums[i];
            for (int j = 1; j < n; ++j) {
                f[i][j] = Math.min(f[i][j - 1], nums[(i - j + n) % n]);
            }
        }
        long ans = 1L << 60;
        for (int j = 0; j < n; ++j) {
            long cost = 1L * x * j;
            for (int i = 0; i < n; ++i) {
                cost += f[i][j];
            }
            ans = Math.min(ans, cost);
        }
        return ans;
    }
}

/*
class Solution {
    public long minCost(int[] nums, int x) {
        int n = nums.length;
        int[] f = new int[n];
        System.arraycopy(nums, 0, f, 0, n);
        long ans = getSum(f);
        for (int k = 1; k < n; ++k) {
            for (int i = 0; i < n; ++i) {
                f[i] = Math.min(f[i], nums[(i + k) % n]);
            }
            ans = Math.min(ans, (long) k * x + getSum(f));
        }
        return ans;
    }

    public long getSum(int[] f) {
        long sum = 0;
        for (int num : f) {
            sum += num;
        }
        return sum;
    }
}
*/

/*
class Solution {
    //二次差分
    public long minCost(int[] nums, int x) {
        int n = nums.length;
        // 找出 nums 中最小的元素，并用其为首元素构造一个新的数组
        int minIdx = 0;
        for (int i = 1; i < n; ++i) {
            if (nums[i] < nums[minIdx]) {
                minIdx = i;
            }
        }
        int[] tmp = new int[n];
        for (int i = 0; i < n; ++i) {
            tmp[i] = nums[(minIdx + i) % n];
        }
        System.arraycopy(tmp, 0, nums, 0, n);

        int[] L = new int[n];
        int[] R = new int[n];
        L[0] = n - 1;
        // 循环来看，右侧 nums[0] 是更小的元素，但不一定是第一个更小的元素，需要用单调栈计算得到
        for (int i = 0; i < n; ++i) {
            R[i] = n - i - 1;
        }
        Deque<Integer> stack = new ArrayDeque<Integer>();
        stack.push(0);
        for (int i = 1; i < n; ++i) {
            while (!stack.isEmpty() && nums[i] < nums[stack.peek()]) {
                R[stack.peek()] = i - stack.peek() - 1;
                stack.pop();
            }
            L[i] = i - stack.peek() - 1;
            stack.push(i);
        }

        long[] F = new long[n];

        // 进行操作需要的成本
        diffTwice(F, 0, n - 1, x, 0);

        for (int i = 0; i < n; ++i) {
            int minv = Math.min(L[i], R[i]);
            int maxv = Math.max(L[i], R[i]);
            // 第一种情况，窗口数量 k+1，总和 nums[i] * k + nums[i]
            diffTwice(F, 0, minv, nums[i], nums[i]);
            // 第二种情况，窗口数量 minv+1，总和 0 * k + nums[i] * (minv + 1)
            diffTwice(F, minv + 1, maxv, 0, (long) nums[i] * (minv + 1));
            // 第三种情况，窗口数量 L[i]+R[i]-k+1，总和 -nums[i] * k + nums[i] * (L[i] + R[i] + 1)
            diffTwice(F, maxv + 1, L[i] + R[i], -nums[i], (long) nums[i] * (L[i] + R[i] + 1));
        }

        // 计算两次前缀和
        for (int i = 0; i < 2; ++i) {
            long[] G = new long[n];
            G[0] = F[0];
            for (int j = 1; j < n; ++j) {
                G[j] = G[j - 1] + F[j];
            }
            System.arraycopy(G, 0, F, 0, n);
        }

        long minimum = Long.MAX_VALUE;
        for (long num : F) {
            minimum = Math.min(minimum, num);
        }
        return minimum;
    }

    // 辅助函数，一次差分，将 F[l..r] 都增加 d
    public void diffOnce(long[] F, int l, int r, long d) {
        if (l > r) {
            return;
        }
        int n = F.length;
        if (l < n) {
            F[l] += d;
        }
        if (r + 1 < n) {
            F[r + 1] -= d;
        }
    }


    // 辅助函数，二次差分，将 F[l..r] 增加 ki + b，i 是下标
    public void diffTwice(long[] F, int l, int r, long k, long b) {
        if (l > r) {
            return;
        }
        diffOnce(F, l, l, k * l + b);
        diffOnce(F, l + 1, r, k);
        diffOnce(F, r + 1, r + 1, -(k * r + b));
    }
}
*/
