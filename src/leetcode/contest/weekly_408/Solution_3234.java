package leetcode.contest.weekly_408;

/**
 * 统计 1 显著的字符串的数量
 * 给你一个二进制字符串 s。
 * 请你统计并返回其中 1 显著 的子字符串的数量。
 * 如果字符串中 1 的数量 大于或等于 0 的数量的 平方，则认为该字符串是一个 1 显著 的字符串 。
 * 示例 1：
 * 输入：s = "00011"
 * 输出：5
 * 解释：
 * 1 显著的子字符串如下表所示。
 * i	j	s[i..j]	0 的数量	1 的数量
 * 3	3	1	0	1
 * 4	4	1	0	1
 * 2	3	01	1	1
 * 3	4	11	0	2
 * 2	4	011	1	2
 * 示例 2：
 * 输入：s = "101101"
 * 输出：16
 * 解释：
 * 1 不显著的子字符串如下表所示。
 * 总共有 21 个子字符串，其中 5 个是 1 不显著字符串，因此有 16 个 1 显著子字符串。
 * i	j	s[i..j]	0 的数量	1 的数量
 * 1	1	0	1	0
 * 4	4	0	1	0
 * 1	4	0110	2	2
 * 0	4	10110	2	3
 * 1	5	01101	2	3
 * 提示：
 * 1 <= s.length <= 4 * 104
 * s 仅包含字符 '0' 和 '1'。
 */
public class Solution_3234 {
    public int numberOfSubstrings(String S) {
        char[] s = S.toCharArray();
        int n = s.length;
        int m = 0;
        int[] a = new int[n + 1];
        for (int i = 0; i < n; i++) {
            if (s[i] == '0') {
                a[m++] = i;
            }
        }

        int tot1 = n - m;
        a[m] = n; // 哨兵

        int ans = 0;
        int i = 0; // >= left 的第一个 0 的下标是 a[i]
        for (int left = 0; left < n; left++) {
            if (s[left] == '1') {
                ans += a[i] - left; // 不含 0 的子串个数
            }
            for (int k = i; k < m; k++) {
                int cnt0 = k - i + 1;
                if (cnt0 * cnt0 > tot1) {
                    break;
                }
                int cnt1 = a[k] - left - (k - i);
                ans += Math.max(a[k + 1] - a[k] - Math.max(cnt0 * cnt0 - cnt1, 0), 0);
            }
            if (s[left] == '0') {
                i++; // 这个 0 后面不会再枚举到了
            }
        }
        return ans;
    }
}
