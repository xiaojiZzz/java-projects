package leetcode.contest.weekly_405;

import java.util.*;

/**
 * 给你一个字符串 target、一个字符串数组 words 以及一个整数数组 costs，这两个数组长度相同。
 * 设想一个空字符串 s。
 * 你可以执行以下操作任意次数（包括零次）：
 * 选择一个在范围  [0, words.length - 1] 的索引 i。
 * 将 words[i] 追加到 s。
 * 该操作的成本是 costs[i]。
 * 返回使 s 等于 target 的 最小 成本。如果不可能，返回 -1。
 * 示例 1：
 * 输入： target = "abcdef", words = ["abdef","abc","d","def","ef"], costs = [100,1,1,10,5]
 * 输出： 7
 * 解释：
 * 选择索引 1 并以成本 1 将 "abc" 追加到 s，得到 s = "abc"。
 * 选择索引 2 并以成本 1 将 "d" 追加到 s，得到 s = "abcd"。
 * 选择索引 4 并以成本 5 将 "ef" 追加到 s，得到 s = "abcdef"。
 * 示例 2：
 * 输入： target = "aaaa", words = ["z","zz","zzz"], costs = [1,10,100]
 * 输出： -1
 * 解释：
 * 无法使 s 等于 target，因此返回 -1。
 * 提示：
 * 1 <= target.length <= 5 * 104
 * 1 <= words.length == costs.length <= 5 * 104
 * 1 <= words[i].length <= target.length
 * 所有 words[i].length 的总和小于或等于 5 * 104
 * target 和 words[i] 仅由小写英文字母组成。
 * 1 <= costs[i] <= 104
 */
public class Solution_3213 {
    // 字符串 hash
    public int minimumCost(String target, String[] words, int[] costs) {
        char[] t = target.toCharArray();
        int n = t.length;

        // 多项式字符串哈希（方便计算子串哈希值）
        // 哈希函数 hash(s) = s[0] * base^(n-1) + s[1] * base^(n-2) + ... + s[n-2] * base + s[n-1]
        final int MOD = 1_070_777_777;
        int BASE = (int) 8e8 + new Random().nextInt((int) 1e8); // 随机 base，防止 hack
        int[] powBase = new int[n + 1]; // powBase[i] = base^i
        int[] preHash = new int[n + 1]; // 前缀哈希值 preHash[i] = hash(target[0] 到 target[i-1])
        powBase[0] = 1;
        for (int i = 0; i < n; i++) {
            powBase[i + 1] = (int) ((long) powBase[i] * BASE % MOD);
            preHash[i + 1] = (int) (((long) preHash[i] * BASE + t[i]) % MOD); // 秦九韶算法计算多项式哈希
        }

        Map<Integer, Integer> minCost = new HashMap<>(); // 哈希值 -> 最小成本
        for (int i = 0; i < words.length; i++) {
            long h = 0;
            for (char b : words[i].toCharArray()) {
                h = (h * BASE + b) % MOD;
            }
            minCost.merge((int) h, costs[i], Integer::min);
        }

        // 有 O(√L) 个不同的长度
        Set<Integer> lengthSet = new HashSet<>();
        for (String w : words) {
            lengthSet.add(w.length());
        }
        int[] sortedLens = new int[lengthSet.size()];
        int k = 0;
        for (int len : lengthSet) {
            sortedLens[k++] = len;
        }
        Arrays.sort(sortedLens);

        int[] f = new int[n + 1];
        Arrays.fill(f, Integer.MAX_VALUE / 2);
        f[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int len : sortedLens) {
                if (len > i) {
                    break;
                }
                // 计算子串 target[i-sz] 到 target[i-1] 的哈希值（计算方法类似前缀和）
                int subHash = (int) (((preHash[i] - (long) preHash[i - len] * powBase[len]) % MOD + MOD) % MOD);
                f[i] = Math.min(f[i], f[i - len] + minCost.getOrDefault(subHash, Integer.MAX_VALUE / 2));
            }
        }
        return f[n] == Integer.MAX_VALUE / 2 ? -1 : f[n];
    }
}

/*
class Solution {

    static int INF = Integer.MAX_VALUE / 2;

    // 一种思路，但是遇到特殊的测试用例，时间复杂度会到 o(n^2)
    public int minimumCost(String target, String[] words, int[] costs) {

        Trie root = new Trie();
        for (int i = 0; i < words.length; i++)
            root.insert(words[i], costs[i]);

        int n = target.length();
        int[] dp = new int[n + 1];
        for (int i = 1; i < dp.length; i++)
            dp[i] = INF;

        for (int i = 0; i < n; i++) {
            Trie cur = root;
            for (int j = i + 1; j <= n; j++) {
                int index = target.charAt(j - 1) - 'a';
                if (cur.children[index] == null)
                    break;
                cur = cur.children[index];

                if (cur.isWord)
                    dp[j] = Math.min(dp[j], dp[i] + cur.cost);
            }
        }
        return dp[n] == INF ? -1 : dp[n];
    }
}

class Trie {
    Trie[] children = new Trie[26];
    int cost = Integer.MAX_VALUE;
    boolean isWord;

    public void insert(String word, int cost) {
        Trie cur = this;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (cur.children[index] == null)
                cur.children[index] = new Trie();
            cur = cur.children[index];
        }

        cur.isWord = true;
        cur.cost = Math.min(cur.cost, cost);
    }
}
*/
