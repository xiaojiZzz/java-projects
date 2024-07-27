package leetcode.medium;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * 给你一个下标从 0 开始的字符串 s 和一个单词字典 dictionary 。
 * 你需要将 s 分割成若干个 互不重叠 的子字符串，每个子字符串都在 dictionary 中出现过。s 中可能会有一些 额外的字符 不在任何子字符串中。
 * 请你采取最优策略分割 s ，使剩下的字符 最少 。
 * 示例 1：
 * 输入：s = "leetscode", dictionary = ["leet","code","leetcode"]
 * 输出：1
 * 解释：将 s 分成两个子字符串：下标从 0 到 3 的 "leet" 和下标从 5 到 8 的 "code" 。只有 1 个字符没有使用（下标为 4），所以我们返回 1 。
 * 示例 2：
 * 输入：s = "sayhelloworld", dictionary = ["hello","world"]
 * 输出：3
 * 解释：将 s 分成两个子字符串：下标从 3 到 7 的 "hello" 和下标从 8 到 12 的 "world" 。下标为 0 ，1 和 2 的字符没有使用，所以我们返回 3 。
 */
public class Solution_2707 {
    public int minExtraChar(String s, String[] dictionary) {
        Set<String> set = Arrays.stream(dictionary).collect(Collectors.toSet());
        int len = s.length();
        int[] dp = new int[len + 1];
        for (int i = 1; i <= len; i++) {
            dp[i] = dp[i - 1] + 1;
            for (int j = 0; j < i; j++) {
                if (set.contains(s.substring(j, i))) {
                    dp[i] = Math.min(dp[j], dp[i]);
                }
            }
        }
        return dp[len];
    }
}

/*
class Solution {
    //字典树
    public int minExtraChar(String s, String[] dictionary) {
        int n = s.length();
        int[] d = new int[n + 1];
        Arrays.fill(d, Integer.MAX_VALUE);
        Trie trie = new Trie();
        for (String str : dictionary) {
            StringBuilder sb = new StringBuilder(str).reverse();
            trie.insert(sb.toString());
        }
        d[0] = 0;
        for (int i = 1; i <= n; i++) {
            d[i] = d[i - 1] + 1;
            Trie node = trie;
            for (int j = i - 1; j >= 0; j--) {
                if (node != null) {
                    node = node.track(s.charAt(j));
                    if (node != null && node.isEnd()) {
                        d[i] = Math.min(d[i], d[j]);
                    }
                }
            }
        }
        return d[n];
    }
}

class Trie {
    private Trie[] children;
    private boolean isEnd;

    public Trie() {
        children = new Trie[26];
        isEnd = false;
    }

    public void insert(String word) {
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (node.children[ch - 'a'] == null) {
                node.children[ch - 'a'] = new Trie();
            }
            node = node.children[ch - 'a'];
        }
        node.isEnd = true;
    }

    public Trie track(char ch) {
        Trie node = this;
        if (node == null || node.children[ch - 'a'] == null) {
            return null;
        }
        node = node.children[ch - 'a'];
        return node;
    }

    public boolean isEnd() {
        return isEnd;
    }
}
*/
