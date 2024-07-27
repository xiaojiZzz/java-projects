package leetcode.simple;

import java.util.HashMap;
import java.util.Map;


/**
 * 给定一种规律 pattern 和一个字符串 s ，判断 s 是否遵循相同的规律。
 * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 s 中的每个非空单词之间存在着双向连接的对应规律。
 * 示例1:
 * 输入: pattern = "abba", s = "dog cat cat dog"
 * 输出: true
 * 示例 2:
 * 输入:pattern = "abba", s = "dog cat cat fish"
 * 输出: false
 * 示例 3:
 * 输入: pattern = "aaaa", s = "dog cat cat dog"
 * 输出: false
 */
public class Solution_290 {
    public boolean wordPattern(String pattern, String s) {
        String[] split = s.split(" ");
        if (pattern.length() != split.length) {
            return false;
        }
        Map<Character, String> map1 = new HashMap<>();
        Map<String, Character> map2 = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            if (map1.containsKey(pattern.charAt(i))) {
                if (!map1.get(pattern.charAt(i)).equals(split[i])) {
                    return false;
                }
            }
            if (map2.containsKey(split[i])) {
                if (map2.get(split[i]) != pattern.charAt(i)) {
                    return false;
                }
            }
            map1.put(pattern.charAt(i), split[i]);
            map2.put(split[i], pattern.charAt(i));
        }
        return true;
    }
}
