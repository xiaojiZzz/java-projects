package leetcode.difficulty;

import java.util.HashSet;
import java.util.Set;


/**
 * 给你一个字符串 s ，考虑其所有 重复子串 ：即 s 的（连续）子串，在 s 中出现 2 次或更多次。这些出现之间可能存在重叠。
 * 返回 任意一个 可能具有最长长度的重复子串。如果 s 不含重复子串，那么答案为 "" 。
 * 示例 1：
 * 输入：s = "banana"
 * 输出："ana"
 * 示例 2：
 * 输入：s = "abcd"
 * 输出：""
 */
public class Solution_1044 {

    // 字符串哈希
    private long[] p, h;

    public String longestDupSubstring(String s) {
        int n = s.length();
        p = new long[n + 1];
        h = new long[n + 1];
        p[0] = 1;
        int P = 1313131;
        for (int i = 0; i < n; i++) {
            p[i + 1] = p[i] * P;
            h[i + 1] = h[i] * P + s.charAt(i);
        }
        int left = 0, right = n;
        String ans = "";
        while (left < right) {
            int mid = left + (right - left) / 2;
            String str = check(mid, n, s);
            if (str.length() != 0) {
                left = mid + 1;
                ans = ans.length() < str.length() ? str : ans;
            } else {
                right = mid;
            }
        }
        return ans;
    }

    public String check(int len, int n, String s) {
        Set<Long> set = new HashSet<>();
        for (int i = 1; i + len - 1 <= n; i++) {
            int j = i + len - 1;
            long hash = h[j] - h[i - 1] * p[j - i + 1];
            if (set.contains(hash)) {
                return s.substring(i - 1, j);
            }
            set.add(hash);
        }
        return "";
    }
}
