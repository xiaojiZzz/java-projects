package leetcode.contest.weekly_405;

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
        int mask = (1 << n) - 1;
        for (int i = 0; i < (1 << n); i++) {
            int x = mask ^ i;
            if (((x >> 1) & x) == 0) {
                ans.add(Integer.toBinaryString((1 << n) | i).substring(1));
            }
        }
        return ans;
    }
}

/*
class Solution {
    public List<String> validStrings(int n) {
        if (n == 1) {
            return Arrays.asList("0", "1");
        }
        List<String> ans = new ArrayList<>();
        backtrack(ans, new StringBuilder(), n);
        return ans;
    }

    private void backtrack(List<String> list, StringBuilder stringBuilder, int n) {
        if (stringBuilder.length() == n) {
            list.add(stringBuilder.toString());
            return;
        }
        if (stringBuilder.length() == 0) {
            stringBuilder.append(0);
            backtrack(list, stringBuilder, n);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        } else {
            if (stringBuilder.charAt(stringBuilder.length() - 1) != '0') {
                stringBuilder.append(0);
                backtrack(list, stringBuilder, n);
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            }
        }
        stringBuilder.append(1);
        backtrack(list, stringBuilder, n);
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
    }
}
*/
