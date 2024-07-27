package leetcode.simple;


/**
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。
 * 你不能使用任何內建的用于处理大整数的库（比如 BigInteger）， 也不能直接将输入的字符串转换为整数形式。
 * 示例 1：
 * 输入：num1 = "11", num2 = "123"
 * 输出："134"
 * 示例 2：
 * 输入：num1 = "456", num2 = "77"
 * 输出："533"
 * 示例 3：
 * 输入：num1 = "0", num2 = "0"
 * 输出："0"
 */
public class Solution_415 {
    public String addStrings(String num1, String num2) {
        StringBuilder stringBuilder = new StringBuilder();
        int idx1 = num1.length() - 1, idx2 = num2.length() - 1;
        int carry = 0;
        while (idx1 >= 0 && idx2 >= 0) {
            int num = (num1.charAt(idx1) - '0') + (num2.charAt(idx2) - '0') + carry;
            stringBuilder.append(num % 10);
            carry = num / 10;
            idx1--;
            idx2--;
        }
        while (idx1 >= 0) {
            int num = (num1.charAt(idx1) - '0') + carry;
            stringBuilder.append(num % 10);
            carry = num / 10;
            idx1--;
        }
        while (idx2 >= 0) {
            int num = (num2.charAt(idx2) - '0') + carry;
            stringBuilder.append(num % 10);
            carry = num / 10;
            idx2--;
        }
        if (carry != 0) {
            stringBuilder.append(carry);
        }
        return stringBuilder.reverse().toString();
    }
}
