package leetcode.medium;

/**
 * 统计打字方案数
 * Alice 在给 Bob 用手机打字。数字到字母的 对应 如下图所示。
 * 为了 打出 一个字母，Alice 需要 按 对应字母 i 次，i 是该字母在这个按键上所处的位置。
 * 比方说，为了按出字母 's' ，Alice 需要按 '7' 四次。类似的， Alice 需要按 '5' 两次得到字母  'k' 。
 * 注意，数字 '0' 和 '1' 不映射到任何字母，所以 Alice 不 使用它们。
 * 但是，由于传输的错误，Bob 没有收到 Alice 打字的字母信息，反而收到了 按键的字符串信息 。
 * 比方说，Alice 发出的信息为 "bob" ，Bob 将收到字符串 "2266622" 。
 * 给你一个字符串 pressedKeys ，表示 Bob 收到的字符串，请你返回 Alice 总共可能发出多少种文字信息 。
 * 由于答案可能很大，将它对 109 + 7 取余 后返回。
 * 示例 1：
 * 输入：pressedKeys = "22233"
 * 输出：8
 * 解释：
 * Alice 可能发出的文字信息包括：
 * "aaadd", "abdd", "badd", "cdd", "aaae", "abe", "bae" 和 "ce" 。
 * 由于总共有 8 种可能的信息，所以我们返回 8 。
 * 示例 2：
 * 输入：pressedKeys = "222222222222222222222222222222222222"
 * 输出：82876089
 * 解释：
 * 总共有 2082876103 种 Alice 可能发出的文字信息。
 * 由于我们需要将答案对 109 + 7 取余，所以我们返回 2082876103 % (109 + 7) = 82876089 。
 * 提示：
 * 1 <= pressedKeys.length <= 105
 * pressedKeys 只包含数字 '2' 到 '9' 。
 */
public class Solution_2266 {
    public int countTexts(String pressedKeys) {
        int[] cnt = new int[]{0, 0, 3, 3, 3, 3, 3, 4, 3, 4};
        int n = pressedKeys.length();
        int mod = 1000000007;
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 1;
        int cntOne = 1;
        for (int i = 2; i <= n; i++) {
            int num = pressedKeys.charAt(i - 1) - '0';
            if (pressedKeys.charAt(i - 2) == pressedKeys.charAt(i - 1)) {
                cntOne++;
            } else {
                cntOne = 1;
            }
            for (int j = 1; j <= Math.min(cntOne, cnt[num]); j++) {
                if (i >= j) {
                    dp[i] = (dp[i] + dp[i - j]) % mod;
                }
            }
        }
        return dp[n];
    }
}

/*
class Solution {

    private static final int MOD = 1_000_000_007;
    private static final int MX = 100_001;
    private static final long[] f = new long[MX];
    private static final long[] g = new long[MX];

    static {
        f[0] = g[0] = 1;
        f[1] = g[1] = 1;
        f[2] = g[2] = 2;
        f[3] = g[3] = 4;
        for (int i = 4; i < MX; i++) {
            f[i] = (f[i - 1] + f[i - 2] + f[i - 3]) % MOD;
            g[i] = (g[i - 1] + g[i - 2] + g[i - 3] + g[i - 4]) % MOD;
        }
    }

    // 不同组之间互不影响，根据乘法原理，把不同组的文字信息种类数相乘，得到答案。
    public int countTexts(String s) {
        long ans = 1;
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            cnt++;
            if (i == s.length() - 1 || c != s.charAt(i + 1)) { // 找到一个完整的组
                ans = ans * (c != '7' && c != '9' ? f[cnt] : g[cnt]) % MOD;
                cnt = 0;
            }
        }
        return (int) ans;
    }
}
*/