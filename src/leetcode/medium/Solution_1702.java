package leetcode.medium;


/**
 * 给你一个二进制字符串 binary ，它仅有 0 或者 1 组成。你可以使用下面的操作任意次对它进行修改：
 * 操作 1 ：如果二进制串包含子字符串 "00" ，你可以用 "10" 将其替换。
 * 比方说， "00010" -> "10010"
 * 操作 2 ：如果二进制串包含子字符串 "10" ，你可以用 "01" 将其替换。
 * 比方说， "00010" -> "00001"
 * 请你返回执行上述操作任意次以后能得到的 最大二进制字符串 。
 * 如果二进制字符串 x 对应的十进制数字大于二进制字符串 y 对应的十进制数字，那么我们称二进制字符串 x 大于二进制字符串 y 。
 * 示例 1：
 * 输入：binary = "000110"
 * 输出："111011"
 * 解释：一个可行的转换为：
 * "000110" -> "000101"
 * "000101" -> "100101"
 * "100101" -> "110101"
 * "110101" -> "110011"
 * "110011" -> "111011"
 * 示例 2：
 * 输入：binary = "01"
 * 输出："01"
 * 解释："01" 没办法进行任何转换。
 */
public class Solution_1702 {
    public String maximumBinaryString(String binary) {
        int n = binary.length();
        char[] s = binary.toCharArray();
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (s[i] == '0') {
                while (j <= i || (j < n && s[j] == '1')) {
                    j++;
                }
                if (j < n) {
                    s[j] = '1';
                    s[i] = '1';
                    s[i + 1] = '0';
                }
            }
        }
        return new String(s);
    }
}

/*
class Solution {
    public String maximumBinaryString(String binary) {
        int n = binary.length(), i = binary.indexOf('0');
        if (i < 0) {
            return binary;
        }
        int zeros = 0;
        StringBuilder s = new StringBuilder();
        for (int j = 0; j < n; j++) {
            if (binary.charAt(j) == '0') {
                zeros++;
            }
            s.append('1');
        }
        s.setCharAt(i + zeros - 1, '0');
        return s.toString();
    }
}
*/
