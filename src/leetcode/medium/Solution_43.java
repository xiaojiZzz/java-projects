package leetcode.medium;

import java.util.ArrayList;
import java.util.List;


/**
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * 注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。
 * 示例 1:
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 */
public class Solution_43 {
    public String multiply(String num1, String num2) {
        List<StringBuilder> list = new ArrayList<>();
        int m = num1.length(), n = num2.length();
        for (int i = n - 1; i >= 0; i--) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < n - 1 - i; j++) {
                stringBuilder.append(0);
            }
            int carry = 0;
            int num = num2.charAt(i) - '0';
            for (int k = m - 1; k >= 0; k--) {
                int t = num1.charAt(k) - '0';
                int mul = num * t + carry;
                stringBuilder.append(mul % 10);
                carry = mul / 10;
            }
            if (carry != 0) {
                stringBuilder.append(carry);
            }
            list.add(stringBuilder.reverse());
        }
        StringBuilder ans = list.get(list.size() - 1);
        for (int i = 0; i < list.size() - 1; i++) {
            ans = getSum(ans, list.get(i));
        }
        return ans.charAt(0) == '0' ? "0" : ans.toString();
    }

    private StringBuilder getSum(StringBuilder s1, StringBuilder s2) {
        int carry = 0;
        int m = s1.length() - 1, n = s2.length() - 1;
        StringBuilder stringBuilder = new StringBuilder();
        while (m >= 0 && n >= 0) {
            int sum = s1.charAt(m) - '0' + s2.charAt(n) - '0' + carry;
            stringBuilder.append(sum % 10);
            carry = sum / 10;
            m--;
            n--;
        }
        while (m >= 0) {
            int sum = s1.charAt(m) - '0' + carry;
            stringBuilder.append(sum % 10);
            carry = sum / 10;
            m--;
        }
        while (n >= 0) {
            int sum = s2.charAt(n) - '0' + carry;
            stringBuilder.append(sum % 10);
            carry = sum / 10;
            n--;
        }
        if (carry != 0) {
            stringBuilder.append(carry);
        }
        return stringBuilder.reverse();
    }
}
