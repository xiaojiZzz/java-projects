package data_structure_and_algorithm;

// 字典树 用于处理字符串集合，例如用于高效地存储和检索字符串数据集中的键
public class Trie {

    static class TrieNode {
        boolean isWord;
        // 字符串仅由小写英文字符组成，若没有这个限制，就不能用数组来存储字符，而使用哈希表
        TrieNode[] children;

        public TrieNode() {
            isWord = false;
            children = new TrieNode[26];
        }
    }

    private final TrieNode root;

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

    public boolean search(String word) {
        TrieNode trieNode = root;
        for (int i = 0; i < word.length(); i++) {
            int n = word.charAt(i) - 'a';
            if (trieNode.children[n] == null) {
                return false;
            }
            trieNode = trieNode.children[n];
        }
        return trieNode.isWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode trieNode = root;
        for (int i = 0; i < prefix.length(); i++) {
            int n = prefix.charAt(i) - 'a';
            if (trieNode.children[n] == null) {
                return false;
            }
            trieNode = trieNode.children[n];
        }
        return true;
    }
}
