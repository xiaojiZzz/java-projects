package leetcode.contest.weekly_405;

/**
 * 给你一个字符串 s 和一个整数 k。请你使用以下算法加密字符串：
 * 对于字符串 s 中的每个字符 c，用字符串中 c 后面的第 k 个字符替换 c（以循环方式）。
 * 返回加密后的字符串。
 * 示例 1：
 * 输入： s = "dart", k = 3
 * 输出： "tdar"
 * 解释：
 * 对于 i = 0，'d' 后面的第 3 个字符是 't'。
 * 对于 i = 1，'a' 后面的第 3 个字符是 'd'。
 * 对于 i = 2，'r' 后面的第 3 个字符是 'a'。
 * 对于 i = 3，'t' 后面的第 3 个字符是 'r'。
 * 示例 2：
 * 输入： s = "aaa", k = 1
 * 输出： "aaa"
 * 解释：
 * 由于所有字符都相同，加密后的字符串也将相同。
 * 提示：
 * 1 <= s.length <= 100
 * 1 <= k <= 104
 * s 仅由小写英文字母组成。
 */
public class Solution_3210 {
    public String getEncryptedString(String s, int k) {
        k %= s.length();
        return s.substring(k) + s.substring(0, k);
    }
}
