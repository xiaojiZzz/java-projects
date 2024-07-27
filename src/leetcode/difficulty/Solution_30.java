package leetcode.difficulty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 给定一个字符串 s 和一个字符串数组 words。 words 中所有字符串 长度相同。
 * s 中的 串联子串 是指一个包含  words 中所有字符串以任意顺序排列连接起来的子串。
 * 例如，如果 words = ["ab","cd","ef"]， 那么 "abcdef"， "abefcd"，"cdabef"， "cdefab"，"efabcd"， 和 "efcdab" 都是串联子串。
 * "acdbef" 不是串联子串，因为他不是任何 words 排列的连接。
 * 返回所有串联子串在 s 中的开始索引。你可以以 任意顺序 返回答案。
 * 示例 1：
 * 输入：s = "barfoothefoobarman", words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：因为 words.length == 2 同时 words[i].length == 3，连接的子字符串的长度必须为 6。
 * 子串 "barfoo" 开始位置是 0。它是 words 中以 ["bar","foo"] 顺序排列的连接。
 * 子串 "foobar" 开始位置是 9。它是 words 中以 ["foo","bar"] 顺序排列的连接。
 * 输出顺序无关紧要。返回 [9,0] 也是可以的。
 * 示例 2：
 * 输入：s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
 * 输出：[]
 * 解释：因为 words.length == 4 并且 words[i].length == 4，所以串联子串的长度必须为 16。
 * s 中没有子串长度为 16 并且等于 words 的任何顺序排列的连接。
 * 所以我们返回一个空数组。
 * 示例 3：
 * 输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
 * 输出：[6,9,12]
 * 解释：因为 words.length == 3 并且 words[i].length == 3，所以串联子串的长度必须为 9。
 * 子串 "foobarthe" 开始位置是 6。它是 words 中以 ["foo","bar","the"] 顺序排列的连接。
 * 子串 "barthefoo" 开始位置是 9。它是 words 中以 ["bar","the","foo"] 顺序排列的连接。
 * 子串 "thefoobar" 开始位置是 12。它是 words 中以 ["the","foo","bar"] 顺序排列的连接。
 */
public class Solution_30 {
    public List<Integer> findSubstring(String s, String[] words) {
        int n = s.length(), m = words.length, w = words[0].length();
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        List<Integer> ans = new ArrayList<>();
        out:
        for (int i = 0; i + m * w <= n; i++) {
            Map<String, Integer> cur = new HashMap<>();
            String sub = s.substring(i, i + m * w);
            for (int j = 0; j < sub.length(); j += w) {
                String item = sub.substring(j, j + w);
                if (!map.containsKey(item)) {
                    continue out;
                }
                cur.put(item, cur.getOrDefault(item, 0) + 1);
            }
            if (cur.equals(map)) {
                ans.add(i);
            }
        }
        return ans;
    }
}

/*
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        int n = s.length(), m = words.length, w = words[0].length();
        // 统计 words 中「每个目标单词」的出现次数
        Map<String, Integer> map = new HashMap<>();
        for (String str : words) {
            map.put(str, map.getOrDefault(str, 0) + 1);
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < w; i++) {
            // 构建一个当前子串对应的哈希表，统计当前子串中「每个目标单词」的出现次数
            Map<String, Integer> temp = new HashMap<>();
            // 滑动窗口的大小固定是 m * w，每次将下一个单词添加进 temp，上一个单词移出 temp
            for (int j = i; j + w <= n; j += w) {
                String cur = s.substring(j, j + w);
                temp.put(cur, temp.getOrDefault(cur, 0) + 1);
                if (j >= i + (m * w)) {
                    int idx = j - m * w;
                    String prev = s.substring(idx, idx + w);
                    if (temp.get(prev) == 1) {
                        temp.remove(prev);
                    } else {
                        temp.put(prev, temp.get(prev) - 1);
                    }
                    if (!temp.getOrDefault(prev, 0).equals(map.getOrDefault(prev, 0))) {
                        continue;
                    }
                }
                if (!temp.getOrDefault(cur, 0).equals(map.getOrDefault(cur, 0))) {
                    continue;
                }
                // 上面两个 continue 可以减少 map 之间的 equals 操作
                if (temp.equals(map)) {
                    ans.add(j - (m - 1) * w);
                }
            }
        }
        return ans;
    }
}
*/

/*
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        int m = words.length, n = words[0].length();
        Map<String, Integer> need = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        // 用need哈希表统计每一个单词及其出现次数
        for (String word : words) {
            need.put(word, need.getOrDefault(word, 0) + 1);
        }
        for (int i = 0; i < n; i++) {
            int left = i, valid = 0;
            Map<String, Integer> window = new HashMap<>();
            for (int right = i; right + n <= s.length(); right += n) {
                String curr = s.substring(right, right + n);
                if (need.containsKey(curr)) {
                    window.put(curr, window.getOrDefault(curr, 0) + 1);
                    // 当新增后两边相等，才valid++
                    // 用 == 会出错故用.equals()
                    if (window.get(curr).equals(need.get(curr)))
                        valid++;
                }
                // 什么时候移动窗口
                if (right + n - left == m * n) {
                    // 只有valid数量对了，才收获结果
                    if (valid == need.size()) {
                        res.add(left);
                    }
                    String dele = s.substring(left, left + n);
                    left += n;
                    if (need.containsKey(dele)) {
                        if (window.get(dele).equals(need.get(dele)))
                            valid--;
                        window.put(dele, window.get(dele) - 1);
                        //想要像这样摆在下面的话，就要检查window.get(dele) == need.get(dele) - 1才可以，代表删除前是 window.get(dele) == 0
                        //if(window.get(dele) == need.get(dele) -1)
                        //valid--;
                    }
                }
            }
        }
        return res;
    }
}
*/
