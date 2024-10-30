package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个正整数 n。
 * 如果一个二进制字符串 x 的所有长度为 2 的子字符串
 * 中包含 至少 一个 "1"，则称 x 是一个 有效 字符串。
 * 返回所有长度为 n 的 有效 字符串，可以以任意顺序排列。
 * 示例 1：
 * 输入： n = 3
 * 输出： ["010","011","101","110","111"]
 * 解释：
 * 长度为 3 的有效字符串有："010"、"011"、"101"、"110" 和 "111"。
 * 示例 2：
 * 输入： n = 1
 * 输出： ["0","1"]
 * 解释：
 * 长度为 1 的有效字符串有："0" 和 "1"。
 * 提示：
 * 1 <= n <= 18
 */
public class Solution_3211 {
    public List<String> validStrings(int n) {
        List<String> ans = new ArrayList<>();
        StringBuilder stringBuilder1 = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder1.append(0);
        stringBuilder2.append(1);
        backtrack(n, ans, stringBuilder1);
        backtrack(n, ans, stringBuilder2);
        return ans;
    }

    private void backtrack(int n, List<String> ans, StringBuilder stringBuilder) {
        if (stringBuilder.length() == n) {
            ans.add(stringBuilder.toString());
            return;
        }
        if (stringBuilder.charAt(stringBuilder.length() - 1) == '1') {
            stringBuilder.append(0);
            backtrack(n, ans, stringBuilder);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        stringBuilder.append(1);
        backtrack(n, ans, stringBuilder);
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
    }
}

/*
class Solution {
    public List<String> validStrings(int n) {
        List<String> ans = new ArrayList<>();
        int mask = (1 << n) - 1; // 长度为 n 的全为 1 的二进制数
        for (int i = 0; i < (1 << n); i++) {
            int x = mask ^ i; // 取反
            if (((x >> 1) & x) == 0) { // 没有相邻的 1，也就是说 i 没有相邻的 0
                ans.add(Integer.toBinaryString((1 << n) | i).substring(1));
            }
        }
        return ans;
    }
}
*/