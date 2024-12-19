package leetcode.difficulty;

import java.util.Arrays;

/**
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
 * 1 <= words[i].length <= 5 * 104
 * 输入确保 sum(words[i].length) <= 105.
 * words[i]  只包含小写英文字母。
 * 1 <= target.length <= 5 * 104
 * target  只包含小写英文字母。
 */
public class Solution_3292 {
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

/*
class Solution {
    private static final int MOD = 1_070_777_777;

    public int minValidStrings(String[] words, String target) {
        char[] t = target.toCharArray();
        int n = t.length;

        // 多项式字符串哈希（方便计算子串哈希值）
        // 哈希函数 hash(s) = s[0] * base^(n-1) + s[1] * base^(n-2) + ... + s[n-2] * base + s[n-1]
        final int BASE = (int) 8e8 + new Random().nextInt((int) 1e8); // 随机 base，防止 hack
        int[] powBase = new int[n + 1]; // powBase[i] = base^i
        int[] preHash = new int[n + 1]; // 前缀哈希值 preHash[i] = hash(target[0] 到 target[i-1])
        powBase[0] = 1;
        for (int i = 0; i < n; i++) {
            powBase[i + 1] = (int) ((long) powBase[i] * BASE % MOD);
            preHash[i + 1] = (int) (((long) preHash[i] * BASE + t[i]) % MOD); // 秦九韶算法计算多项式哈希
        }

        int maxLen = 0;
        for (String w : words) {
            maxLen = Math.max(maxLen, w.length());
        }
        Set<Integer>[] sets = new HashSet[maxLen];
        Arrays.setAll(sets, i -> new HashSet<>());
        for (String w : words) {
            long h = 0;
            for (int j = 0; j < w.length(); j++) {
                h = (h * BASE + w.charAt(j)) % MOD;
                sets[j].add((int) h); // 注意 j 从 0 开始
            }
        }

        int ans = 0;
        int curR = 0; // 已建造的桥的右端点
        int nxtR = 0; // 下一座桥的右端点的最大值
        for (int i = 0; i < n; i++) {
            int maxJump = calcMaxJump(i, preHash, powBase, sets);
            nxtR = Math.max(nxtR, i + maxJump);
            if (i == curR) { // 到达已建造的桥的右端点
                if (i == nxtR) { // 无论怎么造桥，都无法从 i 到 i+1
                    return -1;
                }
                curR = nxtR; // 造一座桥
                ans++;
            }
        }
        return ans;
    }

    private int calcMaxJump(int i, int[] preHash, int[] powBase, Set<Integer>[] sets) {
        // 开区间二分，left 一定满足要求，right 一定不满足要求
        int left = 0;
        int right = Math.min(preHash.length - 1 - i, sets.length) + 1;
        while (left + 1 < right) {
            int mid = (left + right) >>> 1;
            long subHash = (((long) preHash[i + mid] - (long) preHash[i] * powBase[mid]) % MOD + MOD) % MOD;
            if (sets[mid - 1].contains((int) subHash)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
*/

/*
// 双指针优化
class Solution {
    private static final int MOD = 1_070_777_777;

    public int minValidStrings(String[] words, String target) {
        char[] t = target.toCharArray();
        int n = t.length;

        // 多项式字符串哈希（方便计算子串哈希值）
        // 哈希函数 hash(s) = s[0] * base^(n-1) + s[1] * base^(n-2) + ... + s[n-2] * base + s[n-1]
        final int BASE = (int) 8e8 + new Random().nextInt((int) 1e8); // 随机 base，防止 hack
        int[] powBase = new int[n + 1]; // powBase[i] = base^i
        int[] preHash = new int[n + 1]; // 前缀哈希值 preHash[i] = hash(target[0] 到 target[i-1])
        powBase[0] = 1;
        for (int i = 0; i < n; i++) {
            powBase[i + 1] = (int) ((long) powBase[i] * BASE % MOD);
            preHash[i + 1] = (int) (((long) preHash[i] * BASE + t[i]) % MOD); // 秦九韶算法计算多项式哈希
        }

        int maxLen = 0;
        for (String w : words) {
            maxLen = Math.max(maxLen, w.length());
        }
        Set<Integer>[] sets = new HashSet[maxLen];
        Arrays.setAll(sets, i -> new HashSet<>());
        for (String w : words) {
            long h = 0;
            for (int j = 0; j < w.length(); j++) {
                h = (h * BASE + w.charAt(j)) % MOD;
                sets[j].add((int) h); // 注意 j 从 0 开始
            }
        }

        int ans = 0;
        int curR = 0; // 已建造的桥的右端点
        int nxtR = 0; // 下一座桥的右端点的最大值
        for (int i = 0; i < n; i++) {
            while (nxtR < n && nxtR - i < maxLen && sets[nxtR - i].contains(subHash(i, nxtR + 1, powBase, preHash))) {
                nxtR++;
            }
            if (i == curR) { // 到达已建造的桥的右端点
                if (i == nxtR) { // 无论怎么造桥，都无法从 i 到 i+1
                    return -1;
                }
                curR = nxtR; // 造一座桥
                ans++;
            }
        }
        return ans;
    }

    private int subHash(int l, int r, int[] powBase, int[] preHash) {
        return (int) ((((long) preHash[r] - (long) preHash[l] * powBase[r - l]) % MOD + MOD) % MOD);
    }
}
*/