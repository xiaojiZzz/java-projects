package leetcode.medium;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true。
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 * 示例 1：
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
 * 示例 2：
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
 *      注意，你可以重复使用字典中的单词。
 * 示例 3：
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 */
public class Solution_139 {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}

/*
class Solution {
    Boolean[] memo;

    public boolean wordBreak(String s, List<String> wordDict) {
        memo = new Boolean[s.length()];
        return dfs(s, wordDict, 0);
    }

    boolean dfs(String s, List<String> wordDict, int idx) {
        if (idx == s.length()) return true;
        if (memo[idx] != null) return memo[idx];
        boolean res = false;
        for (int i = 0; i < wordDict.size(); i++) {
            String s1 = wordDict.get(i);
            if (s.length() - idx < s1.length()) continue;
            if (s.startsWith(s1, idx) && dfs(s, wordDict, idx + s1.length())) {
                res = true;
                break;
            }
        }
        memo[idx] = res;
        return res;
    }
}
*/
