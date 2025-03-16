package leetcode.difficulty;

/**
 * 最大波动的子字符串
 * 字符串的 波动 定义为子字符串中出现次数 最多 的字符次数与出现次数 最少 的字符次数之差。
 * 给你一个字符串 s ，它只包含小写英文字母。请你返回 s 里所有 子字符串的 最大波动 值。
 * 子字符串 是一个字符串的一段连续字符序列。
 * 示例 1：
 * 输入：s = "aababbb"
 * 输出：3
 * 解释：
 * 所有可能的波动值和它们对应的子字符串如以下所示：
 * - 波动值为 0 的子字符串："a" ，"aa" ，"ab" ，"abab" ，"aababb" ，"ba" ，"b" ，"bb" 和 "bbb" 。
 * - 波动值为 1 的子字符串："aab" ，"aba" ，"abb" ，"aabab" ，"ababb" ，"aababbb" 和 "bab" 。
 * - 波动值为 2 的子字符串："aaba" ，"ababbb" ，"abbb" 和 "babb" 。
 * - 波动值为 3 的子字符串 "babbb" 。
 * 所以，最大可能波动值为 3 。
 * 示例 2：
 * 输入：s = "abcde"
 * 输出：0
 * 解释：
 * s 中没有字母出现超过 1 次，所以 s 中每个子字符串的波动值都是 0 。
 * 提示：
 * 1 <= s.length <= 104
 * s  只包含小写英文字母。
 */
public class Solution_2272 {
    public int largestVariance(String S) {
        char[] s = S.toCharArray();
        int ans = 0;
        for (char a = 'a'; a <= 'z'; a++) {
            for (char b = 'a'; b <= 'z'; b++) {
                if (b == a) {
                    continue;
                }
                int f0 = 0;
                int f1 = Integer.MIN_VALUE;
                for (char ch : s) {
                    if (ch == a) {
                        f0 = Math.max(f0, 0) + 1;
                        f1++;
                    } else if (ch == b) {
                        f1 = f0 = Math.max(f0, 0) - 1;
                    } // else f0 = Math.max(f0, 0); 可以留到 ch 等于 a 或者 b 的时候计算，f1 不变
                    ans = Math.max(ans, f1);
                }
            }
        }
        return ans;
    }
}

/*
class Solution {
    public int largestVariance(String s) {
        int ans = 0;
        int[][] f0 = new int[26][26];
        int[][] f1 = new int[26][26];
        for (int[] row : f1) {
            Arrays.fill(row, Integer.MIN_VALUE);
        }

        for (char ch : s.toCharArray()) {
            ch -= 'a';
            // 遍历到 ch 时，只需计算 a=ch 或者 b=ch 的状态，其他状态和 ch 无关，f 值不变
            for (int i = 0; i < 26; i++) {
                if (i == ch) {
                    continue;
                }
                // 假设出现次数最多的字母 a=ch，更新所有 b=i 的状态
                f0[ch][i] = Math.max(f0[ch][i], 0) + 1;
                f1[ch][i]++;
                // 假设出现次数最少的字母 b=ch，更新所有 a=i 的状态
                f1[i][ch] = f0[i][ch] = Math.max(f0[i][ch], 0) - 1;
                ans = Math.max(ans, Math.max(f1[ch][i], f1[i][ch]));
            }
        }
        return ans;
    }
}
*/