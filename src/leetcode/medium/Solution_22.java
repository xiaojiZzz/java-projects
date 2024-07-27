package leetcode.medium;

import java.util.ArrayList;
import java.util.List;


/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * 示例 1：
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * 示例 2：
 * 输入：n = 1
 * 输出：["()"]
 */
public class Solution_22 {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        char[] chars = new char[]{'(', ')'};
        backtrack(ans, chars, n, 0, 0, new StringBuilder());
        return ans;
    }

    public void backtrack(List<String> list, char[] chars, int n, int l, int r, StringBuilder stringBuilder) {
        if (r > l) {
            return;
        }
        if (n == l) {
            for (int i = 0; i < n - r; i++) {
                stringBuilder.append(')');
            }
            list.add(stringBuilder.toString());
            for (int i = 0; i < n - r; i++) {
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            }
            return;
        }
        for (char c : chars) {
            stringBuilder.append(c);
            if (c == '(') {
                backtrack(list, chars, n, l + 1, r, stringBuilder);
            } else {
                backtrack(list, chars, n, l, r + 1, stringBuilder);
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
    }
}

/*
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<String>();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    public void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur.toString());
            return;
        }
        if (open < max) {
            cur.append('(');
            backtrack(ans, cur, open + 1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            cur.append(')');
            backtrack(ans, cur, open, close + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }
}
*/
