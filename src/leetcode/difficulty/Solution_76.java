package leetcode.difficulty;

import java.util.HashMap;


/**
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * 注意：
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 * 示例 1：
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
 */
public class Solution_76 {
    //滑动窗口
    public String minWindow(String s, String t) {
        HashMap<Character, Integer> hs = new HashMap<>();
        HashMap<Character, Integer> ht = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            ht.put(t.charAt(i), ht.getOrDefault(t.charAt(i), 0) + 1);
        }
        String ans = "";
        int len = Integer.MAX_VALUE, cnt = 0;
        for (int i = 0, j = 0; i < s.length(); i++) {
            hs.put(s.charAt(i), hs.getOrDefault(s.charAt(i), 0) + 1);
            if (ht.containsKey(s.charAt(i)) && hs.get(s.charAt(i)) <= ht.get(s.charAt(i))) {
                cnt++;
            }
            while (j <= i && (!ht.containsKey(s.charAt(j)) || hs.get(s.charAt(j)) > ht.get(s.charAt(j)))) {
                int count = hs.get(s.charAt(j)) - 1;
                hs.put(s.charAt(j), count);
                j++;
            }
            if (cnt == t.length() && i - j + 1 < len) {
                len = i - j + 1;
                ans = s.substring(j, i + 1);
            }
        }
        return ans;
    }
}

/*
class Solution {
    public String minWindow(String s, String t) {
        int n = s.length(), m = t.length(), l = 0, r = -1, cnt = 0;
        int[] a = new int['z' - 'A' + 1];
        int[] b = new int['z' - 'A' + 1];
        for (int i = 0; i < m; ++i) {
            a[t.charAt(i) - 'A']++;
        }
        for (int j = 0, i = -1; i < n; ) {
            while (cnt < m && ++i < n) {
                int x = s.charAt(i) - 'A';
                b[x]++;
                if (b[x] <= a[x]) cnt++;
            }
            if (cnt < m) {
                break;
            }
            while (b[s.charAt(j) - 'A'] > a[s.charAt(j) - 'A']) {
                b[s.charAt(j++) - 'A']--;
            }
            if (r < l || (i - j + 1) < (r - l + 1)) {
                l = j;
                r = i;
            }
            b[s.charAt(j++) - 'A']--;
            cnt--;
        }
        return r < l ? "" : s.substring(l, r + 1);
    }
}
*/

/*
class Solution {
    public String minWindow(String s, String t) {
        int m = s.length(), n = t.length();
        int[] a = new int['z' - 'A' + 1];
        int[] b = new int['z' - 'A' + 1];
        for (int i = 0; i < n; i++) {
            b[t.charAt(i) - 'A']++;
        }
        int cnt = 0, len = Integer.MAX_VALUE;
        int l = 0;
        String ans = "";
        for (int i = 0; i < m; i++) {
            int x = s.charAt(i) - 'A';
            a[x]++;
            if (a[x] <= b[x]) {
                cnt++;
            }
            while (l < i && a[s.charAt(l) - 'A'] > b[s.charAt(l) - 'A']) {
                a[s.charAt(l) - 'A']--;
                l++;
            }
            if (cnt == n && i - l + 1 < len) {
                len = i - l + 1;
                ans = s.substring(l, i + 1);
            }
        }
        return ans;
    }
}
*/
