package leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * DNA序列 由一系列核苷酸组成，缩写为 'A', 'C', 'G' 和 'T'.。
 * 例如，"ACGAATTCCG" 是一个 DNA序列 。
 * 在研究 DNA 时，识别 DNA 中的重复序列非常有用。
 * 给定一个表示 DNA序列 的字符串 s ，返回所有在 DNA 分子中出现不止一次的 长度为 10 的序列(子字符串)。你可以按 任意顺序 返回答案。
 * 示例 1：
 * 输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * 输出：["AAAAACCCCC","CCCCCAAAAA"]
 */
public class Solution_187 {
    public List<String> findRepeatedDnaSequences(String s) {
        HashMap<String, Integer> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        int len = s.length();
        if (len <= 10) {
            return list;
        }
        for (int i = 0; i <= len - 10; i++) {
            String tmp = s.substring(i, i + 10);
            map.put(tmp, map.getOrDefault(tmp, 0) + 1);
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1) {
                list.add(entry.getKey());
            }
        }
        return list;
    }
}

/*
public class Solution_187 {
    //位运算+滑动窗口
    static final int L = 10;
    Map<Character, Integer> bin = new HashMap<Character, Integer>() {{
        put('A', 0);
        put('C', 1);
        put('G', 2);
        put('T', 3);
    }};

    public List<String> findRepeatedDnaSequences(String s) {
        List<String> ans = new ArrayList<String>();
        int n = s.length();
        if (n <= L) {
            return ans;
        }
        int x = 0;
        for (int i = 0; i < L - 1; ++i) {
            x = (x << 2) | bin.get(s.charAt(i));
        }
        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
        for (int i = 0; i <= n - L; ++i) {
            x = ((x << 2) | bin.get(s.charAt(i + L - 1))) & ((1 << (L * 2)) - 1); //关键一步
            cnt.put(x, cnt.getOrDefault(x, 0) + 1);
            if (cnt.get(x) == 2) {
                ans.add(s.substring(i, i + L));
            }
        }
        return ans;
    }
}
*/

/*
class Solution {
    //前缀树
    public List<String> findRepeatedDnaSequences(String s) {
        Trie trie = new Trie();
        Set<String> res = new HashSet<>();
        for (int i = 0; i < s.length() - 9; i ++) {
            if (built(s, i, trie)) {
                res.add(s.substring(i, i + 10));
            }
        }
        return new ArrayList<>(res);
    }

    private boolean built(String s, int start, Trie trie) {
        for (int i = start; i < start + 10; i ++) {
            char c = s.charAt(i);
            if (trie.arr[c - 'A'] == null) {
                trie.arr[c - 'A'] = new Trie();
            }
            trie = trie.arr[c - 'A'];
        }
        if (!trie.flag) {
            trie.flag = true;
            return false;
        }
        return true;
    }

    private static class Trie {
        boolean flag = false;
        Trie[] arr = new Trie[26];
    }
}
*/

/*
class Solution {
    //哈希+前缀和
    int N = (int)1e5+10, P = 131313;
    int[] h = new int[N], p = new int[N];
    public List<String> findRepeatedDnaSequences(String s) {
        int n = s.length();
        List<String> ans = new ArrayList<>();
        p[0] = 1;
        for (int i = 1; i <= n; i++) {
            h[i] = h[i - 1] * P + s.charAt(i - 1);
            p[i] = p[i - 1] * P;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i + 10 - 1 <= n; i++) {
            int j = i + 10 - 1;
            int hash = h[j] - h[i - 1] * p[j - i + 1];
            int cnt = map.getOrDefault(hash, 0);
            if (cnt == 1) ans.add(s.substring(i - 1, i + 10 - 1));
            map.put(hash, cnt + 1);
        }
        return ans;
    }
}
*/
