package leetcode.difficulty;

import java.util.Arrays;


/**
 * 给你一个字符串 s 。请返回 s 中最长的 超赞子字符串 的长度。
 * 「超赞子字符串」需满足满足下述两个条件：
 * 该字符串是 s 的一个非空子字符串
 * 进行任意次数的字符交换后，该字符串可以变成一个回文字符串
 * 示例 1：
 * 输入：s = "3242415"
 * 输出：5
 * 解释："24241" 是最长的超赞子字符串，交换其中的字符后，可以得到回文 "24142"
 * 示例 2：
 * 输入：s = "12345678"
 * 输出：1
 * 示例 3：
 * 输入：s = "213123"
 * 输出：6
 * 解释："213123" 是最长的超赞子字符串，交换其中的字符后，可以得到回文 "231132"
 * 示例 4：
 * 输入：s = "00"
 * 输出：2
 */
public class Solution_1542 {
    private static final int D = 10; // s 中的字符种类数

    public int longestAwesome(String s) {
        int n = s.length();
        int[] pos = new int[1 << D];
        Arrays.fill(pos, n); // n 表示没有找到异或前缀和
        pos[0] = -1; // pre[-1] = 0
        int ans = 0;
        int pre = 0;
        for (int i = 0; i < n; i++) {
            pre ^= 1 << (s.charAt(i) - '0');
            for (int d = 0; d < D; d++) {
                ans = Math.max(ans, i - pos[pre ^ (1 << d)]); // 奇数
            }
            ans = Math.max(ans, i - pos[pre]); // 偶数
            if (pos[pre] == n) { // 首次遇到值为 pre 的前缀异或和，记录其下标 i
                pos[pre] = i;
            }
        }
        return ans;
    }
}
