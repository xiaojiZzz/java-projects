package leetcode.medium;

/**
 * 使所有字符相等的最小成本
 * 给你一个下标从 0 开始、长度为 n 的二进制字符串 s ，你可以对其执行两种操作：
 * 选中一个下标 i 并且反转从下标 0 到下标 i（包括下标 0 和下标 i ）的所有字符，成本为 i + 1 。
 * 选中一个下标 i 并且反转从下标 i 到下标 n - 1（包括下标 i 和下标 n - 1 ）的所有字符，成本为 n - i 。
 * 返回使字符串内所有字符 相等 需要的 最小成本 。
 * 反转 字符意味着：如果原来的值是 '0' ，则反转后值变为 '1' ，反之亦然。
 * 示例 1：
 * 输入：s = "0011"
 * 输出：2
 * 解释：执行第二种操作，选中下标 i = 2 ，可以得到 s = "0000" ，成本为 2 。可以证明 2 是使所有字符相等的最小成本。
 * 示例 2：
 * 输入：s = "010101"
 * 输出：9
 * 解释：执行第一种操作，选中下标 i = 2 ，可以得到 s = "101101" ，成本为 3 。
 * 执行第一种操作，选中下标 i = 1 ，可以得到 s = "011101" ，成本为 2 。
 * 执行第一种操作，选中下标 i = 0 ，可以得到 s = "111101" ，成本为 1 。
 * 执行第二种操作，选中下标 i = 4 ，可以得到 s = "111110" ，成本为 2 。
 * 执行第二种操作，选中下标 i = 5 ，可以得到 s = "111111" ，成本为 1 。
 * 使所有字符相等的总成本等于 9 。可以证明 9 是使所有字符相等的最小成本。
 * 提示：
 * 1 <= s.length == n <= 105
 * s[i] 为 '0' 或 '1'
 */
public class Solution_2712 {
    public long minimumCost(String S) {
        char[] s = S.toCharArray();
        int n = s.length;
        long ans = 0;
        for (int i = 1; i < n; i++) {
            if (s[i - 1] != s[i]) {
                ans += Math.min(i, n - i);
            }
        }
        return ans;
    }
}

/*
class Solution {
    public long minimumCost(String s) {
        int n = s.length();
        long[][] suf = new long[n + 1][2];
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '1') {
                suf[i][1] = suf[i + 1][1];
                suf[i][0] = suf[i + 1][1] + (n - i);
            } else {
                suf[i][1] = suf[i + 1][0] + (n - i);
                suf[i][0] = suf[i + 1][0];
            }
        }

        long[] pre = new long[2];
        long res = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                pre[0] = pre[1] + i + 1;
            } else {
                pre[1] = pre[0] + i + 1;
            }
            res = Math.min(res, Math.min(pre[0] + suf[i + 1][0], pre[1] + suf[i + 1][1]));
        }
        return res;
    }
}
*/