package leetcode.medium;

import java.util.HashSet;


/**
 * 如果可以使用以下操作从一个字符串得到另一个字符串，则认为两个字符串 接近 ：
 * 操作 1：交换任意两个 现有 字符。
 * 例如，abcde -> aecdb
 * 操作 2：将一个 现有 字符的每次出现转换为另一个 现有 字符，并对另一个字符执行相同的操作。
 * 例如，aacabb -> bbcbaa（所有 a 转化为 b ，而所有的 b 转换为 a ）
 * 你可以根据需要对任意一个字符串多次使用这两种操作。
 * 给你两个字符串，word1 和 word2 。如果 word1 和 word2 接近 ，就返回 true ；否则，返回 false 。
 * 示例 1：
 * 输入：word1 = "abc", word2 = "bca"
 * 输出：true
 * 解释：2 次操作从 word1 获得 word2 。
 * 执行操作 1："abc" -> "acb"
 * 执行操作 1："acb" -> "bca"
 * 示例 2：
 * 输入：word1 = "a", word2 = "aa"
 * 输出：false
 * 解释：不管执行多少次操作，都无法从 word1 得到 word2 ，反之亦然。
 * 示例 3：
 * 输入：word1 = "cabbba", word2 = "abbccc"
 * 输出：true
 * 解释：3 次操作从 word1 获得 word2 。
 * 执行操作 1："cabbba" -> "caabbb"
 * 执行操作 2："caabbb" -> "baaccc"
 * 执行操作 2："baaccc" -> "abbccc"
 * 示例 4：
 * 输入：word1 = "cabbba", word2 = "aabbss"
 * 输出：false
 * 解释：不管执行多少次操作，都无法从 word1 得到 word2 ，反之亦然。
 */
public class Solution_1657 {
    public boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }
        int[] times1 = new int[26];
        int[] times2 = new int[26];
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < word1.length(); i++) {
            char c = word1.charAt(i);
            times1[c - 'a'] += 1;
            set.add(c);
        }
        for (int i = 0; i < word2.length(); i++) {
            times2[word2.charAt(i) - 'a'] += 1;
        }
        for (int i = 0; i < word2.length(); i++) {
            if (!set.contains(word2.charAt(i))) {
                return false;
            }
        }
        for (int i = 0; i < 26; i++) {
            boolean flag = false;
            for (int j = 0; j < 26; j++) {
                if (times1[i] == times2[j]) {
                    times2[j] = -1;
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                return false;
            }
        }
        return true;
    }
}

/*
class Solution {
    public boolean closeStrings(String word1, String word2) {
        int n1 = word1.length(), n2 = word2.length();
        if (n1 != n2) return false;
        int[] cnt1 = new int[26], cnt2 = new int[26];
        for (int i = 0; i < n1; i++) {
            cnt1[word1.charAt(i) - 'a']++;
            cnt2[word2.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (cnt1[i] == 0 && cnt2[i] != 0 || cnt1[i] != 0 && cnt2[i] == 0) return false;
        }
        Arrays.sort(cnt1);
        Arrays.sort(cnt2);
        for (int i = 0; i < 26; i++) {
            if (cnt1[i] != cnt2[i]) return false;
        }
        return true;
    }
}
*/

/*
import static java.nio.charset.StandardCharsets.ISO_8859_1;

public class Solution {
    public boolean closeStrings(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int sMask = 0, tMask = 0;
        int[] sCnt = new int[26], tCnt = new int[26];
        for (byte c : s.getBytes(ISO_8859_1)) { // 比 toCharArray 更快
            sMask |= 1 << (c - 'a'); // 记录 s 中有字符 c
            sCnt[c - 'a']++;
        }
        for (byte c : t.getBytes(ISO_8859_1)) {
            tMask |= 1 << (c - 'a'); // 记录 t 中有字符 c
            tCnt[c - 'a']++;
        }

        Arrays.sort(sCnt);
        Arrays.sort(tCnt);
        return sMask == tMask && Arrays.equals(sCnt, tCnt);
    }
}
*/
