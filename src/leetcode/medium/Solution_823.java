package leetcode.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * 给出一个含有不重复整数元素的数组 arr ，每个整数 arr[i] 均大于 1。
 * 用这些整数来构建二叉树，每个整数可以使用任意次数。其中：每个非叶结点的值应等于它的两个子结点的值的乘积。
 * 满足条件的二叉树一共有多少个？答案可能很大，返回 对 109 + 7 取余 的结果。
 * 示例 1:
 * 输入: arr = [2, 4]
 * 输出: 3
 * 解释: 可以得到这些二叉树: [2], [4], [4, 2, 2]
 * 示例 2:
 * 输入: arr = [2, 4, 5, 10]
 * 输出: 7
 * 解释: 可以得到这些二叉树: [2], [4], [5], [10], [4, 2, 2], [10, 2, 5], [10, 5, 2].
 */
public class Solution_823 {
    public int numFactoredBinaryTrees(int[] arr) {
        final long MOD = (long) 1e9 + 7;
        Arrays.sort(arr);
        int n = arr.length;
        Map<Integer, Integer> idx = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            idx.put(arr[i], i);
        }

        long[] memo = new long[n];
        Arrays.fill(memo, -1); // -1 表示没有计算过
        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans += dfs(i, arr, memo, idx);
        }
        return (int) (ans % MOD);
    }

    private long dfs(int i, int[] arr, long[] memo, Map<Integer, Integer> idx) {
        if (memo[i] != -1) // 之前计算过
            return memo[i];
        int val = arr[i];
        long res = 1;
        for (int j = 0; j < i; ++j) { // val 的因子一定比 val 小
            int x = arr[j];
            if (val % x == 0 && idx.containsKey(val / x)) { // 另一个因子 val/x 必须在 arr 中
                res += dfs(j, arr, memo, idx) * dfs(idx.get(val / x), arr, memo, idx);
            }
        }
        return memo[i] = res; // 记忆化
    }
}

/*
class Solution {
    public int numFactoredBinaryTrees(int[] arr) {
        final long MOD = (long) 1e9 + 7;
        Arrays.sort(arr);
        int n = arr.length;
        Map<Integer, Integer> idx = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            idx.put(arr[i], i);
        }
        long ans = 0;
        long[] f = new long[n];
        for (int i = 0; i < n; i++) {
            int val = arr[i];
            f[i] = 1;
            for (int j = 0; j < i; ++j) { // val 的因子一定比 val 小
                int x = arr[j];
                if (val % x == 0 && idx.containsKey(val / x)) { // 另一个因子 val/x 必须在 arr 中
                    f[i] += f[j] * f[idx.get(val / x)];
                }
            }
            ans += f[i];
        }
        return (int) (ans % MOD);
    }
}
*/

/*
class Solution {
    public int numFactoredBinaryTrees(int[] arr) {
        final long MOD = (long) 1e9 + 7;
        Arrays.sort(arr);
        int n = arr.length;
        Map<Integer, Integer> idx = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            idx.put(arr[i], i);
        }
        long ans = 0;
        long[] f = new long[n];
        for (int i = 0; i < n; i++) {
            int val = arr[i];
            f[i] = 1;
            for (int j = 0; j < i; ++j) {
                int x = arr[j];
                if ((long) x * x > val) { // 防止乘法溢出
                    break;
                }
                if (x * x == val) {
                    f[i] += f[j] * f[j];
                    break;
                }
                if (val % x == 0 && idx.containsKey(val / x)) {
                    f[i] += f[j] * f[idx.get(val / x)] * 2;
                }
            }
            ans += f[i];
        }
        return (int) (ans % MOD);
    }
}
*/
