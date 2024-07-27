package leetcode.difficulty;

import java.util.*;


/**
 * 字典 wordList 中从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列 beginWord -> s1 -> s2 -> ... -> sk：
 * 每一对相邻的单词只差一个字母。
 * 对于 1 <= i <= k 时，每个 si 都在 wordList 中。注意， beginWord 不需要在 wordList 中。
 * sk == endWord
 * 给你两个单词 beginWord 和 endWord 和一个字典 wordList ，返回 从 beginWord 到 endWord 的 最短转换序列 中的 单词数目 。
 * 如果不存在这样的转换序列，返回 0 。
 * 示例 1：
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出：5
 * 解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。
 */
public class Solution_127 {
    //双向bfs
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> wordset = new HashSet<>(wordList);
        if (!wordset.contains(endWord)) {
            return 0;
        }
        HashSet<String> visited = new HashSet<>();
        visited.add(beginWord);
        HashSet<String> beginVisited = new HashSet<>();
        beginVisited.add(beginWord);
        HashSet<String> endVisited = new HashSet<>();
        endVisited.add(endWord);
        int step = 1;
        while (!beginVisited.isEmpty() && !endVisited.isEmpty()) {
            if (beginVisited.size() > endVisited.size()) {
                HashSet<String> temp = beginVisited;
                beginVisited = endVisited;
                endVisited = temp;
            }
            HashSet<String> nextVisited = new HashSet<>();
            for (String str : beginVisited) {
                if (curr(str, endVisited, visited, nextVisited, wordset)) {
                    return step + 1;
                }
            }
            beginVisited = nextVisited;
            step++;
        }
        return 0;
    }

    //可能的变化
    public boolean curr(String word, HashSet<String> endVisited, HashSet<String> visited, HashSet<String> nextVisited, HashSet<String> wordset) {
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char num = chars[i];
            for (int j = 0; j < 26; j++) {
                chars[i] = (char) ('a' + j);
                String str = new String(chars);
                if (wordset.contains(str)) {
                    if (endVisited.contains(str)) {
                        return true;
                    }
                    if (!visited.contains(str)) {
                        nextVisited.add(str);
                        visited.add(str);
                    }
                }
            }
            chars[i] = num;
        }
        return false;
    }
}

/*
public class Solution_127 {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> set = new HashSet<>();
        for (String s : wordList) {
            set.add(s);
        }
        if (!set.contains(endWord)) {
            return 0;
        }
        set.remove(beginWord);
        int num = 1;
        Queue<String> queue = new LinkedList<>(); //bfs
        HashSet<String> seen = new HashSet<>(); //已经访问过的word
        queue.offer(beginWord);
        seen.add(beginWord);
        while (!queue.isEmpty()) {
            num++;
            int size = queue.size();
            while (size-- > 0) {
                String str = queue.poll();
                for (String s : curr(str, set)) {
                    if (!seen.contains(s)) {
                        if (s.equals(endWord)) {
                            return num;
                        }
                        seen.add(s);
                        queue.offer(s);
                    }
                }
            }
        }
        return 0;
    }

    //可能的变化
    public List<String> curr(String word, HashSet<String> wordList) {
        List<String> list = new ArrayList<>();
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char num = chars[i];
            for (int j = 0; j < 26; j++) {
                chars[i] = (char) ('a' + j);
                if (wordList.contains(new String(chars))) {
                    list.add(new String(chars));
                }
            }
            chars[i] = num;
        }
        return list;
    }
}
*/

