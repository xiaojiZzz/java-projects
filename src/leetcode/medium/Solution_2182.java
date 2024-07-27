package leetcode.medium;

import java.util.Map;
import java.util.TreeMap;


/**
 * 给你一个字符串 s 和一个整数 repeatLimit ，用 s 中的字符构造一个新字符串 repeatLimitedString ，
 * 使任何字母 连续 出现的次数都不超过 repeatLimit 次。你不必使用 s 中的全部字符。
 * 返回 字典序最大的 repeatLimitedString 。
 * 如果在字符串 a 和 b 不同的第一个位置，字符串 a 中的字母在字母表中出现时间比字符串 b 对应的字母晚，则认为字符串 a 比字符串 b 字典序更大 。
 * 如果字符串中前 min(a.length, b.length) 个字符都相同，那么较长的字符串字典序更大。
 * 示例 1：
 * 输入：s = "cczazcc", repeatLimit = 3
 * 输出："zzcccac"
 * 解释：使用 s 中的所有字符来构造 repeatLimitedString "zzcccac"。
 * 字母 'a' 连续出现至多 1 次。
 * 字母 'c' 连续出现至多 3 次。
 * 字母 'z' 连续出现至多 2 次。
 * 因此，没有字母连续出现超过 repeatLimit 次，字符串是一个有效的 repeatLimitedString 。
 * 该字符串是字典序最大的 repeatLimitedString ，所以返回 "zzcccac" 。
 * 注意，尽管 "zzcccca" 字典序更大，但字母 'c' 连续出现超过 3 次，所以它不是一个有效的 repeatLimitedString 。
 * 示例 2：
 * 输入：s = "aababab", repeatLimit = 2
 * 输出："bbabaa"
 * 解释：
 * 使用 s 中的一些字符来构造 repeatLimitedString "bbabaa"。
 * 字母 'a' 连续出现至多 2 次。
 * 字母 'b' 连续出现至多 2 次。
 * 因此，没有字母连续出现超过 repeatLimit 次，字符串是一个有效的 repeatLimitedString 。
 * 该字符串是字典序最大的 repeatLimitedString ，所以返回 "bbabaa" 。
 * 注意，尽管 "bbabaaa" 字典序更大，但字母 'a' 连续出现超过 2 次，所以它不是一个有效的 repeatLimitedString 。
 */
public class Solution_2182 {
    public String repeatLimitedString(String s, int repeatLimit) {
        TreeMap<Character, Integer> treeMap = new TreeMap<>();
        for (int i = 0; i < s.length(); i++) {
            treeMap.put(s.charAt(i), treeMap.getOrDefault(s.charAt(i), 0) + 1);
        }
        StringBuffer stringBuffer = new StringBuffer();
        while (treeMap.size() > 0) {
            Map.Entry<Character, Integer> entry = treeMap.lastEntry();
            int times = entry.getValue();
            if (times <= repeatLimit) {
                while (times-- > 0) {
                    stringBuffer.append(entry.getKey());
                }
                treeMap.remove(entry.getKey());
            } else {
                int num = repeatLimit;
                while (num-- > 0) {
                    stringBuffer.append(entry.getKey());
                }
                treeMap.remove(entry.getKey());
                if (treeMap.isEmpty()) {
                    break;
                } else {
                    Map.Entry<Character, Integer> lastEntry = treeMap.lastEntry();
                    stringBuffer.append(lastEntry.getKey());
                    if (lastEntry.getValue() - 1 == 0) {
                        treeMap.remove(lastEntry.getKey());
                    } else {
                        treeMap.put(lastEntry.getKey(), lastEntry.getValue() - 1);
                    }
                }
                treeMap.put(entry.getKey(), times - repeatLimit);
            }
        }
        return stringBuffer.toString();
    }
}

/*
class Solution {
    public String repeatLimitedString(String s, int repeatLimit) {
        int[] counts = new int[26];
        int n = s.length();
        //录入各字母的频次
        for (int i = 0; i < n; i++) {
            counts[s.charAt(i) - 'a']++;
        }
        int maxIndex = 25;
        while (counts[maxIndex] == 0) maxIndex--;
        int secondMaxIndex = maxIndex - 1;
        while (secondMaxIndex >= 0 && counts[secondMaxIndex] == 0) secondMaxIndex--;
        StringBuilder res = new StringBuilder(); //用与储存结果
        while (maxIndex >= 0) {
            int repeat = Math.min(repeatLimit, counts[maxIndex]);
            counts[maxIndex] -= repeat;
            while (repeat > 0) {
                res.append((char) ('a' + maxIndex));
                repeat--;
            }
            if (secondMaxIndex < 0) break; //如果没有可用的次大字符，构造结束
            if (counts[maxIndex] > 0) {
                res.append((char) ('a' + secondMaxIndex));
                counts[secondMaxIndex]--;
            }
            if (counts[maxIndex] == 0) {
                maxIndex = secondMaxIndex;
                secondMaxIndex--;
            }
            while (secondMaxIndex >= 0 && counts[secondMaxIndex] == 0) secondMaxIndex--;
        }
        return res.toString();
    }
}
*/
