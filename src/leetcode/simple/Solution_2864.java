package leetcode.simple;

import java.util.HashMap;


/**
 * 给你一个 二进制 字符串 s ，其中至少包含一个 '1' 。
 * 你必须按某种方式 重新排列 字符串中的位，使得到的二进制数字是可以由该组合生成的 最大二进制奇数 。
 * 以字符串形式，表示并返回可以由给定组合生成的最大二进制奇数。
 * 注意 返回的结果字符串 可以 含前导零。
 * 示例 1：
 * 输入：s = "010"
 * 输出："001"
 * 解释：因为字符串 s 中仅有一个 '1' ，其必须出现在最后一位上。所以答案是 "001" 。
 * 示例 2：
 * 输入：s = "0101"
 * 输出："1001"
 * 解释：其中一个 '1' 必须出现在最后一位上。而由剩下的数字可以生产的最大数字是 "100" 。所以答案是 "1001" 。
 */
public class Solution_2864 {
    public String maximumOddBinaryNumber(String s) {
        int oneCnt = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                oneCnt++;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < oneCnt - 1; i++) {
            stringBuilder.append('1');
        }
        for (int i = 0; i < s.length() - oneCnt; i++) {
            stringBuilder.append('0');
        }
        stringBuilder.append('1');
        HashMap<String, String> map = new HashMap<>();

        return stringBuilder.toString();
    }
}

/*
class Solution {
    public String maximumOddBinaryNumber(String s) {
        int cnt1 = (int) s.chars().filter(c -> c == '1').count();
        return "1".repeat(cnt1 - 1) + "0".repeat(s.length() - cnt1) + "1";
    }
}
*/
