package leetcode.medium;


/**
 * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。
 * 这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 * 请你实现 Trie 类：
 * Trie() 初始化前缀树对象。
 * void insert(String word) 向前缀树中插入字符串 word 。
 * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
 * boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
 * 示例：
 * 输入
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * 输出
 * [null, null, true, false, true, null, true]
 * 解释
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // 返回 True
 * trie.search("app");     // 返回 False
 * trie.startsWith("app"); // 返回 True
 * trie.insert("app");
 * trie.search("app");     // 返回 True
 */
public class Solution_208 {
    class TrieNode {
        boolean isWord;
        TrieNode[] word;

        public TrieNode() {
            isWord = false;
            word = new TrieNode[26];
        }
    }

    class Trie {
        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode trieNode = root;
            for (int i = 0; i < word.length(); i++) {
                int n = word.charAt(i) - 'a';
                if (trieNode.word[n] == null) {
                    trieNode.word[n] = new TrieNode();
                }
                trieNode = trieNode.word[n];
            }
            trieNode.isWord = true;
        }

        public boolean search(String word) {
            TrieNode trieNode = root;
            for (int i = 0; i < word.length(); i++) {
                int n = word.charAt(i) - 'a';
                if (trieNode.word[n] == null) {
                    return false;
                }
                trieNode = trieNode.word[n];
            }
            return trieNode.isWord;
        }

        public boolean startsWith(String prefix) {
            TrieNode trieNode = root;
            for (int i = 0; i < prefix.length(); i++) {
                int n = prefix.charAt(i) - 'a';
                if (trieNode.word[n] == null) {
                    return false;
                }
                trieNode = trieNode.word[n];
            }
            return true;
        }
    }
}
