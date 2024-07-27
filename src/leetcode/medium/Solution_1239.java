package leetcode.medium;

import java.util.HashSet;
import java.util.List;


/**
 * 给定一个字符串数组 arr，字符串 s 是将 arr 的含有 不同字母 的 子序列 字符串 连接 所得的字符串。
 * 请返回所有可行解 s 中最长长度。
 * 子序列 是一种可以从另一个数组派生而来的数组，通过删除某些元素或不删除元素而不改变其余元素的顺序。
 * 示例 1：
 * 输入：arr = ["un","iq","ue"]
 * 输出：4
 * 解释：所有可能的串联组合是：
 * - ""
 * - "un"
 * - "iq"
 * - "ue"
 * - "uniq" ("un" + "iq")
 * - "ique" ("iq" + "ue")
 * 最大长度为 4。
 * 示例 2：
 * 输入：arr = ["cha","r","act","ers"]
 * 输出：6
 * 解释：可能的解答有 "chaers" 和 "acters"。
 * 示例 3：
 *
 * 输入：arr = ["abcdefghijklmnopqrstuvwxyz"]
 * 输出：26
 */
public class Solution_1239 {

    private int res = 0;

    public int maxLength(List<String> arr) {

        StringBuilder sb = new StringBuilder();

        backtrack(arr, 0, sb);
        return res;
    }

    public void backtrack(List<String> arr, int start, StringBuilder sb) {

        if (!isVaild(sb.toString()))
            return; // 剪枝

        res = Math.max(res, sb.length());
        for (int i = start; i < arr.size(); i++) {

            String tmp = arr.get(i);
            sb.append(tmp); // 做选择
            backtrack(arr, i + 1, sb);
            sb.delete(sb.length() - tmp.length(), sb.length()); // 撤销选择
        }
    }

    public boolean isVaild(String s) {

        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {

            if (set.contains(s.charAt(i)))
                return false;
            set.add(s.charAt(i));
        }
        return true;
    }
}
