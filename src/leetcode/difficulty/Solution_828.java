package leetcode.difficulty;

import java.util.Arrays;


/**
 * 我们定义了一个函数 countUniqueChars(s) 来统计字符串 s 中的唯一字符，并返回唯一字符的个数。
 * 例如：s = "LEETCODE" ，则其中 "L", "T","C","O","D" 都是唯一字符，因为它们只出现一次，所以 countUniqueChars(s) = 5 。
 * 本题将会给你一个字符串 s ，我们需要返回 countUniqueChars(t) 的总和，其中 t 是 s 的子字符串。输入用例保证返回值为 32 位整数。
 * 注意，某些子字符串可能是重复的，但你统计时也必须算上这些重复的子字符串（也就是说，你必须统计 s 的所有子字符串中的唯一字符）。
 * 示例 1：
 * 输入: s = "ABC"
 * 输出: 10
 * 解释: 所有可能的子串为："A","B","C","AB","BC" 和 "ABC"。
 *      其中，每一个子串都由独特字符构成。
 *      所以其长度总和为：1 + 1 + 1 + 2 + 2 + 3 = 10
 * 示例 2：
 * 输入: s = "ABA"
 * 输出: 8
 * 解释: 除了 countUniqueChars("ABA") = 1 之外，其余与示例 1 相同。
 * 示例 3：
 * 输入：s = "LEETCODE"
 * 输出：92
 */
public class Solution_828 {
    public int uniqueLetterString(String s) {
        int ans = 0;
        int[] tmp0 = new int[26]; //存储某一个字符前一个字符所在位置
        int[] tmp1 = new int[26]; //存储某个字符当前所处位置

        Arrays.fill(tmp0, -1);
        Arrays.fill(tmp1, -1);

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char now = chars[i];
            int index = now - 'A';
            if (tmp1[index] > -1) {
                ans += (i - tmp1[index]) * (tmp1[index] - tmp0[index]);
            }
            tmp0[index] = tmp1[index];
            tmp1[index] = i;
        }
        for (int i = 0; i < 26; i++) {
            if (tmp1[i] > -1) {
                ans += (tmp1[i] - tmp0[i]) * (s.length() - tmp1[i]);
            }
        }
        return ans;
    }
}

/*
class Solution {
    public int uniqueLetterString(String s) {
        Map<Character, List<Integer>> map = new HashMap();
        char[] sc = s.toCharArray();
        for (int i = 0; i < sc.length; i++) {
            if (!map.containsKey(sc[i])) map.put(sc[i], new ArrayList());
            map.get(sc[i]).add(i);
        }

        int result = 0;
        for(Map.Entry<Character, List<Integer>> entry : map.entrySet()) {
            int head = -1, tail = -1;
            List<Integer> item = entry.getValue();
            for (int i = 0; i < item.size(); i++) {
                tail = (i < item.size() - 1) ? item.get(i + 1) : sc.length;
                result += (item.get(i) - head) * (tail - item.get(i));
                head = item.get(i);
            }
        }
        return result;
    }
}
*/

/*
class Solution {
    public int uniqueLetterString(String s) {
        Map<Character, List<Integer>> index = new HashMap<Character, List<Integer>>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!index.containsKey(c)) {
                index.put(c, new ArrayList<Integer>());
                index.get(c).add(-1);
            }
            index.get(c).add(i);
        }
        int res = 0;
        for (Map.Entry<Character, List<Integer>> entry : index.entrySet()) {
            List<Integer> arr = entry.getValue();
            arr.add(s.length());
            for (int i = 1; i < arr.size() - 1; i++) {
                res += (arr.get(i) - arr.get(i - 1)) * (arr.get(i + 1) - arr.get(i));
            }
        }
        return res;
    }
}
*/
