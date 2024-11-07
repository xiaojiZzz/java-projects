package leetcode.medium;

import java.util.*;

/**
 * 施咒的最大总伤害
 * 一个魔法师有许多不同的咒语。
 * 给你一个数组 power ，其中每个元素表示一个咒语的伤害值，可能会有多个咒语有相同的伤害值。
 * 已知魔法师使用伤害值为 power[i] 的咒语时，
 * 他们就 不能 使用伤害为 power[i] - 2 ，power[i] - 1 ，power[i] + 1 或者 power[i] + 2 的咒语。
 * 每个咒语最多只能被使用 一次 。
 * 请你返回这个魔法师可以达到的伤害值之和的 最大值 。
 * 示例 1：
 * 输入：power = [1,1,3,4]
 * 输出：6
 * 解释：
 * 可以使用咒语 0，1，3，伤害值分别为 1，1，4，总伤害值为 6 。
 * 示例 2：
 * 输入：power = [7,1,6,6]
 * 输出：13
 * 解释：
 * 可以使用咒语 1，2，3，伤害值分别为 1，6，6，总伤害值为 13 。
 * 提示：
 * 1 <= power.length <= 105
 * 1 <= power[i] <= 109
 */
public class Solution_3186 {
    public long maximumTotalDamage(int[] power) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : power) {
            cnt.merge(x, 1, Integer::sum);
        }

        int n = cnt.size();
        int[] a = new int[n];
        int k = 0;
        for (int x : cnt.keySet()) {
            a[k++] = x;
        }
        Arrays.sort(a);

        long[] memo = new long[n];
        Arrays.fill(memo, -1);
        return dfs(a, cnt, memo, n - 1);
    }

    private long dfs(int[] a, Map<Integer, Integer> cnt, long[] memo, int i) {
        if (i < 0) {
            return 0;
        }
        if (memo[i] != -1) {
            return memo[i];
        }
        int x = a[i];
        int left = 0, right = i - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (a[mid] >= x - 2) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return memo[i] = Math.max(dfs(a, cnt, memo, i - 1), dfs(a, cnt, memo, left - 1) + (long) x * cnt.get(x));
    }
}

/*
class Solution {
    public long maximumTotalDamage(int[] power) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : power) {
            cnt.merge(x, 1, Integer::sum);
        }

        int n = cnt.size();
        int[] a = new int[n];
        int k = 0;
        for (int x : cnt.keySet()) {
            a[k++] = x;
        }
        Arrays.sort(a);

        long[] dp = new long[n + 1];
        int j = 0;
        for (int i = 0; i < n; i++) {
            int x = a[i];
            while (a[j] < x - 2) {
                j++;
            }
            dp[i + 1] = Math.max(dp[i], dp[j] + (long) x * cnt.get(x));
        }
        return dp[n];
    }
}
*/