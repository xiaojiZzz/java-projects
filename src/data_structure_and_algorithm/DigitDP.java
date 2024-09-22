package data_structure_and_algorithm;

import java.util.Arrays;

// 数位 DP 见 lc.2376
// 如果一个正整数每一个数位都是互不相同的，我们称它是特殊整数。给你一个正整数 n ，请你返回区间 [1, n] 之间特殊整数的数目。
// 这种类似的题目就是需要使用数位 DP 来解决，其他题目可以看灵神题单
public class DigitDP {
    public int countSpecialNumbers(int n) {
        char[] s = Integer.toString(n).toCharArray();
        int[][] memo = new int[s.length][1 << 10];
        for (int[] row : memo) {
            Arrays.fill(row, -1); // -1 表示没有计算过
        }
        return dfs(0, 0, true, false, s, memo);
    }

    // 反回从 i 开始填数字，i 前面填的数字的集合是 mask，能构造出的特殊整数的数目
    // isLimit 表示前面填的数字是否都是 n 对应位上的，如果为 true，那么当前位至多为 s[i] - '0'，否则至多为 9
    // isNum 表示前面是否填了数字（没有填，就是跳过），如果为 true，那么当前位可以从 0 开始，否则，可以再次跳过，或者从 1 开始
    private int dfs(int i, int mask, boolean isLimit, boolean isNum, char[] s, int[][] memo) {
        if (i == s.length) {
            return isNum ? 1 : 0; // isNum 为 true 表示得到了一个合法数字
        }
        if (!isLimit && isNum && memo[i][mask] != -1) {
            return memo[i][mask]; // 之前计算过
        }
        int res = 0;
        if (!isNum) { // 可以跳过当前数位
            res = dfs(i + 1, mask, false, false, s, memo);
        }
        // 如果前面填的数字都和 n 的一样，那么这一位至多填数字 s[i]（否则就超过 n 啦）
        int up = isLimit ? s[i] - '0' : 9;
        // 枚举要填入的数字 d
        // 如果前面没有填数字，则必须从 1 开始（因为不能有前导零）
        for (int d = isNum ? 0 : 1; d <= up; d++) {
            if ((mask >> d & 1) == 0) { // d 不在 mask 中，说明之前没有填过 d
                res += dfs(i + 1, mask | (1 << d), isLimit && d == up, true, s, memo);
            }
        }
        if (!isLimit && isNum) {
            memo[i][mask] = res; // 记忆化
        }
        return res;
    }
}
