package leetcode.simple;

/**
 * 统计满足 K 约束的子字符串数量 I
 * 给你一个 二进制 字符串 s 和一个整数 k。
 * 如果一个 二进制字符串 满足以下任一条件，则认为该字符串满足 k 约束：
 * 字符串中 0 的数量最多为 k。
 * 字符串中 1 的数量最多为 k。
 * 返回一个整数，表示 s 的所有满足 k 约束 的子字符串的数量。
 * 示例 1：
 * 输入：s = "10101", k = 1
 * 输出：12
 * 解释：
 * s 的所有子字符串中，除了 "1010"、"10101" 和 "0101" 外，其余子字符串都满足 k 约束。
 * 示例 2：
 * 输入：s = "1010101", k = 2
 * 输出：25
 * 解释：
 * s 的所有子字符串中，除了长度大于 5 的子字符串外，其余子字符串都满足 k 约束。
 * 示例 3：
 * 输入：s = "11111", k = 1
 * 输出：15
 * 解释：
 * s 的所有子字符串都满足 k 约束。
 * 提示：
 * 1 <= s.length <= 50
 * 1 <= k <= s.length
 * s[i] 是 '0' 或 '1'。
 */
public class Solution_3258 {
    public int countKConstraintSubstrings(String s, int k) {
        int ans = 0, n = s.length();
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int zeroCnt = 0, oneCnt = 0;
                for (int l = i; l <= j; l++) {
                    if (s.charAt(l) == '0') {
                        zeroCnt++;
                    } else {
                        oneCnt++;
                    }
                }
                if (zeroCnt <= k || oneCnt <= k) {
                    ans++;
                }
            }
        }
        return ans;
    }
}

/*
class Solution {
    public int countKConstraintSubstrings(String s, int k) {
        int ans = 0, left = 0;
        int n = s.length();
        int oneCnt = 0, zeroCnt = 0;
        for (int right = 0; right < n; right++) {
            if (s.charAt(right) == '0') {
                zeroCnt++;
            } else {
                oneCnt++;
            }
            while (zeroCnt > k && oneCnt > k) {
                if (s.charAt(left++) == '0') {
                    zeroCnt--;
                } else {
                    oneCnt--;
                }
            }
            ans += right - left + 1;
        }
        return ans;
    }
}
*/

/*
class Solution {
    public int countKConstraintSubstrings(String s, int k) {
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            int one_cnt = 0;
            int zero_cnt = 0;
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(j) == '1') {
                    one_cnt++;
                } else {
                    zero_cnt++;
                }
                if (one_cnt > k && zero_cnt > k) {
                    break;
                }
                ans++;
            }
        }
        return ans;
    }
}
*/