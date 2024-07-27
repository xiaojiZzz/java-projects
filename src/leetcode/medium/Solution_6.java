package leetcode.medium;

import java.util.ArrayList;
import java.util.List;


/**
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 * 示例：
 * 输入：s = "PAYPALISHIRING", numRows = 4
 * 输出："PINALSIGYAHRPI"
 * 解释：
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 */

public class Solution_6 {
    public String convert(String s, int numRows) {
        if (numRows < 2) {
            return s;
        }
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            rows.add(new StringBuilder());
        }
        int i = 0, flag = -1;
        for (char c : s.toCharArray()) {
            rows.get(i).append(c);
            if (i == 0 || i == numRows - 1) {
                flag = -flag;
            }
            i += flag;
        }
        StringBuilder res = new StringBuilder();
        for (StringBuilder row : rows) {
            res.append(row);
        }
        return res.toString();
    }
}

/*
class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1 || numRows > s.length()) {
            return s;
        }
        int n = s.length();
        char[][] res = new char[numRows][(n + 2 * numRows - 3) / (2 * numRows - 2) * (numRows - 1)];
        for (int i = 0, x = 0, y = 0; i < n; i++) {
            res[x][y] = s.charAt(i);
            if (i % (2 * numRows - 2) < numRows - 1) {
                x++;
            } else {
                x--;
                y++;
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (char[] chars : res) {
            for (char c : chars) {
                if (c != 0) {
                    stringBuffer.append(c);
                }
            }
        }
        return stringBuffer.toString();
    }
}
*/

/*
class Solution {
    //减少空间复杂度
    public String convert(String s, int numRows) {
        int n = s.length(), r = numRows;
        if (r == 1 || r >= n) {
            return s;
        }
        StringBuffer[] mat = new StringBuffer[r];
        for (int i = 0; i < r; ++i) {
            mat[i] = new StringBuffer();
        }
        for (int i = 0, x = 0, t = r * 2 - 2; i < n; ++i) {
            mat[x].append(s.charAt(i));
            if (i % t < r - 1) {
                ++x;
            } else {
                --x;
            }
        }
        StringBuffer ans = new StringBuffer();
        for (StringBuffer row : mat) {
            ans.append(row);
        }
        return ans.toString();
    }
}
*/

/*
class Solution {
    //直接构造，利用索引对应
    public String convert(String s, int numRows) {
        int n = s.length();
        int r = numRows;
        if (r == 1 || r >= n) {
            return s;
        }
        StringBuffer stringBuffer = new StringBuffer();
        int t = 2 * r - 2;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j + i < n; j += t) {
                stringBuffer.append(s.charAt(j + i));
                if (0 < i && i < r - 1 && j + t - i < n) {
                    stringBuffer.append(s.charAt(j + t - i));
                }
            }
        }
        return stringBuffer.toString();
    }
}
*/
