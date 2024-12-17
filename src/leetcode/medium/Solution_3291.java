package leetcode.medium;

import java.util.Arrays;

/**
 * 形成目标字符串需要的最少字符串数 I
 * 给你一个字符串数组 words 和一个字符串 target。
 * 如果字符串 x 是 words 中 任意 字符串的前缀，则认为 x 是一个 有效 字符串。
 * 现计划通过 连接 有效字符串形成 target ，请你计算并返回需要连接的 最少 字符串数量。如果无法通过这种方式形成 target，则返回 -1。
 * 示例 1：
 * 输入： words = ["abc","aaaaa","bcdef"], target = "aabcdabc"
 * 输出： 3
 * 解释：
 * target 字符串可以通过连接以下有效字符串形成：
 * words[1] 的长度为 2 的前缀，即 "aa"。
 * words[2] 的长度为 3 的前缀，即 "bcd"。
 * words[0] 的长度为 3 的前缀，即 "abc"。
 * 示例 2：
 * 输入： words = ["abababab","ab"], target = "ababaababa"
 * 输出： 2
 * 解释：
 * target 字符串可以通过连接以下有效字符串形成：
 * words[0] 的长度为 5 的前缀，即 "ababa"。
 * words[0] 的长度为 5 的前缀，即 "ababa"。
 * 示例 3：
 * 输入： words = ["abcdef"], target = "xyz"
 * 输出： -1
 * 提示：
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 5 * 103
 * 输入确保 sum(words[i].length) <= 105。
 * words[i] 只包含小写英文字母。
 * 1 <= target.length <= 5 * 103
 * target 只包含小写英文字母。
 */
public class Solution_3291 {
    public int minValidStrings(String[] words, String target) {
        int m = target.length();
        Trie trie = new Trie();
        int[] dp = new int[m + 1];
        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        dp[0] = 0;
        for (String word : words) {
            trie.insert(word);
        }
        for (int l = 1; l <= m; l++) {
            if (dp[l - 1] == Integer.MAX_VALUE / 2) {
                return -1;
            }
            Trie.TrieNode root = trie.root;
            for (int r = l; r <= m; r++) {
                int idx = target.charAt(r - 1) - 'a';
                if (root.children[idx] != null) {
                    root = root.children[idx];
                    dp[r] = Math.min(dp[r], dp[l - 1] + 1);
                } else {
                    break;
                }
            }
        }
        return dp[m] == Integer.MAX_VALUE / 2 ? -1 : dp[m];
    }
}

class Trie {

    static class TrieNode {
        boolean isWord;
        TrieNode[] children;

        public TrieNode() {
            isWord = false;
            children = new TrieNode[26];
        }
    }

    final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode trieNode = root;
        for (int i = 0; i < word.length(); i++) {
            int n = word.charAt(i) - 'a';
            if (trieNode.children[n] == null) {
                trieNode.children[n] = new TrieNode();
            }
            trieNode = trieNode.children[n];
        }
        trieNode.isWord = true;
    }
}

/*
class Solution {
    // 利用 KMP 求 next 数组的方法，求 word 和 target 的前缀最大长度
    public int minValidStrings(String[] words, String target) {
        int n = target.length();
        int[] back = new int[n];
        for (String word : words) {
            int[] pi = prefixFunction(word, target);
            int m = word.length();
            for (int i = 0; i < n; i++) {
                back[i] = Math.max(back[i], pi[m + 1 + i]);
            }
        }
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 1, n + 1, (int) 1e9);
        for (int i = 0; i < n; i++) {
            dp[i + 1] = dp[i + 1 - back[i]] + 1;
            if (dp[i + 1] > n) {
                return -1;
            }
        }
        return dp[n];
    }

    private int[] prefixFunction(String word, String target) {
        String s = word + "#" + target;
        int n = s.length();
        int[] pi = new int[n];
        for (int i = 1; i < n; i++) {
            int j = pi[i - 1];
            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                j = pi[j - 1];
            }
            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }
            pi[i] = j;
        }
        return pi;
    }
}
*/