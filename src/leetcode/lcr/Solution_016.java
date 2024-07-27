package leetcode.lcr;

import java.util.HashMap;
import java.util.Map;


/**
 * 同leetcode 3.无重复字符的最长子串
 */
public class Solution_016 {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() <= 1) {
            return s.length();
        }
        int maxLength = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int start = 0, end = 0; end < s.length(); end++) {
            char element = s.charAt(end);
            if (map.containsKey(element)) {
                start = Math.max(map.get(element) + 1, start);
            }
            maxLength = Math.max(end - start + 1, maxLength);
            map.put(element, end);
        }
        return maxLength;
    }
}
