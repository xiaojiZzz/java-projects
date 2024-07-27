package leetcode.medium;

import java.util.*;


/**
 * 给你一个仅由小写英文字母组成的字符串 s 。
 * 如果一个字符串仅由单一字符组成，那么它被称为 特殊 字符串。例如，字符串 "abc" 不是特殊字符串，而字符串 "ddd"、"zz" 和 "f" 是特殊字符串。
 * 返回在 s 中出现 至少三次 的 最长特殊子字符串 的长度，如果不存在出现至少三次的特殊子字符串，则返回 -1 。
 * 子字符串 是字符串中的一个连续 非空 字符序列。
 * 示例 1：
 * 输入：s = "aaaa"
 * 输出：2
 * 解释：出现三次的最长特殊子字符串是 "aa" ：子字符串 "aaaa"、"aaaa" 和 "aaaa"。
 * 可以证明最大长度是 2 。
 * 示例 2：
 * 输入：s = "abcdef"
 * 输出：-1
 * 解释：不存在出现至少三次的特殊子字符串。因此返回 -1 。
 * 示例 3：
 * 输入：s = "abcaba"
 * 输出：1
 * 解释：出现三次的最长特殊子字符串是 "a" ：子字符串 "abcaba"、"abcaba" 和 "abcaba"。
 * 可以证明最大长度是 1 。
 */
public class Solution_2981 {
    public int maximumLength(String s) {
        // 也可以利用二分来快速定位截取的字符串长度
        int len = s.length();
        for (int i = len - 2; i >= 1; i--) {
            Map<String, Integer> map = new HashMap<>();
            for (int j = 0; j <= len - i; j++) {
                String str = s.substring(j, j + i);
                map.put(str, map.getOrDefault(str, 0) + 1);
            }
            for (Map.Entry<String, Integer> stringIntegerEntry : map.entrySet()) {
                if (stringIntegerEntry.getValue() >= 3 && isSame(stringIntegerEntry.getKey())) {
                    return stringIntegerEntry.getKey().length();
                }
            }
        }
        return -1;
    }

    public boolean isSame(String s) {
        int[] arr = new int[26];
        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (arr[i] != 0) {
                if (arr[i] == s.length()) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}

/*
class Solution {
    public int maximumLength(String s) {
        int ans = -1;
        int len = s.length();

        List<Integer>[] chs = new List[26];
        for (int i = 0; i < 26; i++) {
            chs[i] = new ArrayList<Integer>();
        }
        int cnt = 0;
        for (int i = 0; i < len; i++) {
            cnt++;
            if (i + 1 == len || s.charAt(i) != s.charAt(i + 1)) {
                int ch = s.charAt(i) - 'a';
                chs[ch].add(cnt);
                cnt = 0;

                for (int j = chs[ch].size() - 1; j > 0; j--) {
                    if (chs[ch].get(j) > chs[ch].get(j - 1)) {
                        Collections.swap(chs[ch], j, j - 1);
                    } else {
                        break;
                    }
                }

                if (chs[ch].size() > 3) {
                    chs[ch].remove(chs[ch].size() - 1);
                }
            }
        }

        for (int i = 0; i < 26; i++) {
            if (chs[i].size() > 0 && chs[i].get(0) > 2) {
                ans = Math.max(ans, chs[i].get(0) - 2);
            }
            if (chs[i].size() > 1 && chs[i].get(0) > 1) {
                ans = Math.max(ans, Math.min(chs[i].get(0) - 1, chs[i].get(1)));
            }
            if (chs[i].size() > 2) {
                ans = Math.max(ans, chs[i].get(2));
            }
        }

        return ans;
    }
}
*/
