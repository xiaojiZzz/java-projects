package leetcode.simple;

/**
 * 字符串中最大的 3 位相同数字
 * 给你一个字符串 num ，表示一个大整数。如果一个整数满足下述所有条件，则认为该整数是一个 优质整数 ：
 * 该整数是 num 的一个长度为 3 的 子字符串 。
 * 该整数由唯一一个数字重复 3 次组成。
 * 以字符串形式返回 最大的优质整数 。如果不存在满足要求的整数，则返回一个空字符串 "" 。
 * 注意：
 * 子字符串 是字符串中的一个连续字符序列。
 * num 或优质整数中可能存在 前导零 。
 * 示例 1：
 * 输入：num = "6777133339"
 * 输出："777"
 * 解释：num 中存在两个优质整数："777" 和 "333" 。
 * "777" 是最大的那个，所以返回 "777" 。
 * 示例 2：
 * 输入：num = "2300019"
 * 输出："000"
 * 解释："000" 是唯一一个优质整数。
 * 示例 3：
 * 输入：num = "42352338"
 * 输出：""
 * 解释：不存在长度为 3 且仅由一个唯一数字组成的整数。因此，不存在优质整数。
 * 提示：
 * 3 <= num.length <= 1000
 * num 仅由数字（0 - 9）组成
 */
public class Solution_2264 {
    public String largestGoodInteger(String num) {
        int n = num.length();
        String ans = "", max = "0";
        for (int i = 0; i < n - 2; i++) {
            char a = num.charAt(i), b = num.charAt(i + 1), c = num.charAt(i + 2);
            if (a == b && b == c) {
                if (a - '0' >= Integer.parseInt(max)) {
                    ans = "" + a + b + c;
                    max = "" + a;
                }
            }
        }
        return ans;
    }
}

/*
class Solution {
    public String largestGoodInteger(String num) {
        int n = num.length();
        String res = "";
        for (int i = 0; i < n - 2; i++) {
            if (num.charAt(i) == num.charAt(i + 1) && num.charAt(i + 1) == num.charAt(i + 2)) {
                String current = num.substring(i, i + 3);
                if (res.compareTo(current) < 0) {
                    res = current;
                }
            }
        }
        return res;
    }
}
*/

/*
class Solution {
    public String largestGoodInteger(String num) {
        for (char d = '9'; d >= '0'; d--) {
            String s = String.valueOf(d).repeat(3);
            if (num.contains(s)) {
                return s;
            }
        }
        return "";
    }
}
*/

/*
class Solution {
    String largestGoodInteger(String num) {
        char mx = 0;
        for (int i = 0; i < num.length() - 2; i++) {
            char d = num.charAt(i);
            if (d > mx && d == num.charAt(i + 1) && d == num.charAt(i + 2)) {
                mx = d;
            }
        }
        return mx > 0 ? String.valueOf(mx).repeat(3) : "";
    }
}
*/

/*
class Solution {
    String largestGoodInteger(String num) {
        char mx = 0;
        int cnt = 1;
        for (int i = 1; i < num.length(); i++) {
            char d = num.charAt(i);
            if (d != num.charAt(i - 1)) {
                cnt = 1;
                continue;
            }
            cnt++;
            if (cnt == 3 && d > mx) {
                mx = d;
            }
        }
        return mx > 0 ? String.valueOf(mx).repeat(3) : "";
    }
}
*/