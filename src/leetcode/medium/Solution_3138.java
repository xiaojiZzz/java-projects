package leetcode.medium;

import java.util.Arrays;

/**
 * 同位字符串连接的最小长度
 * 给你一个字符串 s ，它由某个字符串 t 和若干 t  的 同位字符串 连接而成。
 * 请你返回字符串 t 的 最小 可能长度。
 * 同位字符串 指的是重新排列一个单词得到的另外一个字符串，原来字符串中的每个字符在新字符串中都恰好只使用一次。
 * 示例 1：
 * 输入：s = "abba"
 * 输出：2
 * 解释：
 * 一个可能的字符串 t 为 "ba" 。
 * 示例 2：
 * 输入：s = "cdef"
 * 输出：4
 * 解释：
 * 一个可能的字符串 t 为 "cdef" ，注意 t 可能等于 s 。
 * 提示：
 * 1 <= s.length <= 105
 * s 只包含小写英文字母。
 */
public class Solution_3138 {
    public int minAnagramLength(String s) {
        int n = s.length();
        for (int i = 1; i < n; i++) {
            if (n % i == 0) {
                String str = "";
                boolean flag = true;
                for (int j = 0; j < n; j += i) {
                    int[] cnt = new int[26];
                    for (int k = j; k < j + i; k++) {
                        cnt[s.charAt(k) - 'a']++;
                    }
                    if (str.length() == 0) {
                        str = Arrays.toString(cnt);
                    } else {
                        if (!str.equals(Arrays.toString(cnt))) {
                            flag = false;
                            break;
                        }
                    }
                }
                if (flag) {
                    return i;
                }
            }
        }
        return n;
    }
}

/*
class Solution {
    public int minAnagramLength(String S) {
        char[] s = S.toCharArray();
        int n = s.length;
        next:
        for (int k = 1; k <= n / 2; k++) {
            if (n % k > 0) {
                continue;
            }
            int[] cnt0 = new int[26];
            for (int j = 0; j < k; j++) {
                cnt0[s[j] - 'a']++;
            }
            for (int i = k * 2; i <= n; i += k) {
                int[] cnt = new int[26];
                for (int j = i - k; j < i; j++) {
                    cnt[s[j] - 'a']++;
                }
                if (!Arrays.equals(cnt, cnt0)) {
                    continue next;
                }
            }
            return k;
        }
        return n;
    }
}
*/

/*
class Solution {
    public int minAnagramLength(String S) {
        char[] s = S.toCharArray();
        int n = s.length;
        int[] cntAll = new int[26];
        for (char c : s) {
            cntAll[c - 'a']++;
        }
        int g = 0;
        for (int c : cntAll) {
            g = gcd(g, c);
        }
        next:
        for (int times = g; times > 1; times--) {
            if (g % times > 0) {
                continue;
            }
            int k = n / times;
            int[] cnt0 = new int[26];
            for (int j = 0; j < k; j++) {
                cnt0[s[j] - 'a']++;
            }
            for (int i = k * 2; i <= n; i += k) {
                int[] cnt = new int[26];
                for (int j = i - k; j < i; j++) {
                    cnt[s[j] - 'a']++;
                }
                if (!Arrays.equals(cnt, cnt0)) {
                    continue next;
                }
            }
            return k;
        }
        return n;
    }

    private int gcd(int a, int b) {
        while (a != 0) {
            int tmp = a;
            a = b % a;
            b = tmp;
        }
        return b;
    }
}
*/