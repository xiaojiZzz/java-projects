package leetcode.simple;


/**
 * 给你两个二进制字符串 a 和 b ，以二进制字符串的形式返回它们的和。
 * 示例 1：
 * 输入:a = "11", b = "1"
 * 输出："100"
 * 示例 2：
 * 输入：a = "1010", b = "1011"
 * 输出："10101"
 */
public class Solution_67 {
    public static String addBinary(String a, String b) {
        int length1 = a.length();
        char[] achars = a.toCharArray();
        char[] bchars = b.toCharArray();
        int length2 = b.length();
        int i = 0, j = 0, flag = 0;
        if (length1 < length2) {
            for (i = length1 - 1, j = length2 - 1; i >= 0; i--, j--) {
                if (flag == 0) {
                    if (achars[i] == '1' && bchars[j] == '1') {
                        bchars[j] = '0';
                        flag = 1;
                    } else if (achars[i] == '1' || bchars[j] == '1') {
                        bchars[j] = '1';
                    } else {
                        bchars[j] = '0';
                    }
                } else {
                    if (achars[i] == '1' && bchars[j] == '1') {
                        bchars[j] = '1';
                    } else if (achars[i] == '1' || bchars[j] == '1') {
                        bchars[j] = '0';
                    } else {
                        bchars[j] = '1';
                        flag = 0;
                    }
                }
            }
        } else {
            for (i = length1 - 1, j = length2 - 1; j >= 0; i--, j--) {
                if (flag == 0) {
                    if (achars[i] == '1' && bchars[j] == '1') {
                        achars[i] = '0';
                        flag = 1;
                    } else if (achars[i] == '1' || bchars[j] == '1') {
                        achars[i] = '1';
                    } else {
                        achars[i] = '0';
                    }
                } else {
                    if (achars[i] == '1' && bchars[j] == '1') {
                        achars[i] = '1';
                    } else if (achars[i] == '1' || bchars[j] == '1') {
                        achars[i] = '0';
                    } else {
                        achars[i] = '1';
                        flag = 0;
                    }
                }
            }
        }
        if (length1 < length2) {
            for (; j >= 0; j--) {
                if (flag == 0) {
                    return String.valueOf(bchars);
                } else {
                    if (bchars[j] == '1') {
                        bchars[j] = '0';
                    } else {
                        bchars[j] = '1';
                        return String.valueOf(bchars);
                    }
                }
            }
            if (flag == 0) {
                return String.valueOf(bchars);
            }
        } else {
            for (; i >= 0; i--) {
                if (flag == 0) {
                    return String.valueOf(achars);
                } else {
                    if (achars[i] == '1') {
                        achars[i] = '0';
                    } else {
                        achars[i] = '1';
                        return String.valueOf(achars);
                    }
                }
            }
            if (flag == 0) {
                return String.valueOf(achars);
            }
        }
        if (length1 < length2) {
            char[] chars1 = new char[length2 + 1];
            chars1[0] = '1';
            for (int s = 1; s <= length2; s++) {
                chars1[s] = bchars[s - 1];
            }
            return String.valueOf(chars1);
        } else {
            char[] chars2 = new char[length1 + 1];
            chars2[0] = '1';
            for (int s = 1; s <= length1; s++) {
                chars2[s] = achars[s - 1];
            }
            return String.valueOf(chars2);
        }
    }
}
