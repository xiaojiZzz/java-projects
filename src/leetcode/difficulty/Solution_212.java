package leetcode.difficulty;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * 给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words， 返回所有二维网格上的单词 。
 * 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母在一个单词中不允许被重复使用。
 * 示例 1：
 * 输入：board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]],
 * words = ["oath","pea","eat","rain"]
 * 输出：["eat","oath"]
 * 示例 2：
 * 输入：board = [["a","b"],["c","d"]], words = ["abcb"]
 * 输出：[]
 */
public class Solution_212 {
    class Trie {
        class TrieNode {
            String s;
            // 字符串仅由小写英文字符组成，若没有这个限制，就不能用数组来存储字符，而使用哈希表
            TrieNode[] children;

            public TrieNode() {
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
            trieNode.s = word;
        }
    }

    private int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }
        Set<String> set = new HashSet<>();
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, trie.root, i, j, set);
            }
        }
        return new ArrayList<>(set);
    }

    public void dfs(char[][] board, Trie.TrieNode trie, int i, int j, Set<String> set) {
        if (board[i][j] == '#' || trie.children[board[i][j] - 'a'] == null) {
            return;
        }
        char c = board[i][j];
        trie = trie.children[c - 'a'];
        if (trie.s != null) {
            set.add(trie.s);
        }
        board[i][j] = '#';
        for (int[] dir : dirs) {
            int i2 = i + dir[0], j2 = j + dir[1];
            if (i2 >= 0 && i2 < board.length && j2 >= 0 && j2 < board[0].length) {
                dfs(board, trie, i2, j2, set);
            }
        }
        board[i][j] = c;
    }
}
