package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 复原 IP 地址
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
 * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。
 * 你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
 * 示例 1：
 * 输入：s = "25525511135"
 * 输出：["255.255.11.135","255.255.111.35"]
 * 示例 2：
 * 输入：s = "0000"
 * 输出：["0.0.0.0"]
 * 示例 3：
 * 输入：s = "101023"
 * 输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 * 提示：
 * 1 <= s.length <= 20
 * s 仅由数字组成
 */
public class Solution_93 {

    List<String> ans = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        int[] loc = new int[3];
        backtrack(s, 0, 0, loc);
        return ans;
    }

    private void backtrack(String s, int start, int dotCnt, int[] loc) {
        if (dotCnt == 3) {
            String s1 = s.substring(0, loc[0]);
            String s2 = s.substring(loc[0], loc[1]);
            String s3 = s.substring(loc[1], loc[2]);
            String s4 = s.substring(loc[2]);
            if (!check(s1) || !check(s2) || !check(s3) || !check(s4)) {
                return;
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(s1).append(".").append(s2).append(".").append(s3).append(".").append(s4);
            ans.add(stringBuilder.toString());
            return;
        }
        for (int i = start; i < s.length(); i++) {
            loc[dotCnt] = i + 1;
            backtrack(s, start + 1, dotCnt + 1, loc);
        }
    }

    private boolean check(String s) {
        if (s.length() == 0 || (s.length() > 1 && s.charAt(0) == '0') || s.length() > 3 || Integer.parseInt(s) > 255) {
            return false;
        }
        return true;
    }
}
