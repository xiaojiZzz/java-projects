package leetcode.medium;


/**
 * 请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。
 * 实现词典类 WordDictionary ：
 * WordDictionary() 初始化词典对象
 * void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配
 * bool search(word) 如果数据结构中存在字符串与 word 匹配，则返回 true ；
 * 否则，返回  false 。word 中可能包含一些 '.' ，每个 . 都可以表示任何一个字母。
 * 示例：
 * 输入：
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * 输出：
 * [null,null,null,null,false,true,true,true]
 * 解释：
 * WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad");
 * wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad");
 * wordDictionary.search("pad"); // 返回 False
 * wordDictionary.search("bad"); // 返回 True
 * wordDictionary.search(".ad"); // 返回 True
 * wordDictionary.search("b.."); // 返回 True
 */
public class Solution_211 {
    class WordDictionary {

        class Trie {
            class TrieNode {
                boolean isWord;
                TrieNode[] children;

                public TrieNode() {
                    isWord = false;
                    children = new TrieNode[26];
                }
            }

            private TrieNode root;

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

        private Trie trie;

        public WordDictionary() {
            trie = new Trie();
        }

        public void addWord(String word) {
            trie.insert(word);
        }

        public boolean search(String word) {
            return dfs(word, 0, trie.root);
        }

        private boolean dfs(String word, int idx, Trie.TrieNode node) {
            if (idx == word.length()) {
                return node.isWord;
            }
            char c = word.charAt(idx);
            if (Character.isLetter(c)) {
                int i = c - 'a';
                if (node.children[i] != null && dfs(word, idx + 1, node.children[i])) {
                    return true;
                }
            } else {
                for (int i = 0; i < 26; i++) {
                    if (node.children[i] != null && dfs(word, idx + 1, node.children[i])) {
                        return true;
                    }
                }
            }
            return false;
        }
    }
}
