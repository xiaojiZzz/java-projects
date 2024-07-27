package leetcode.simple;


/**
 * 给你一个整数 columnNumber ，返回它在 Excel 表中相对应的列名称。
 * 例如：
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 * 示例 1：
 * 输入：columnNumber = 1
 * 输出："A"
 */
public class Solution_168 {
    public String convertToTitle(int columnNumber) {
        StringBuffer sb = new StringBuffer();
        while (columnNumber > 0) {
            columnNumber--;
            sb.append((char) (columnNumber % 26 + 'A'));
            columnNumber /= 26;
        }
        return sb.reverse().toString();
    }
}

//public class Solution_168 {
//    public String convertToTitle(int columnNumber) {
//        StringBuffer sb = new StringBuffer();
//        while (columnNumber > 0) {
//            int a0 = (columnNumber - 1) % 26 + 1;
//            sb.append((char) (a0 - 1 + 'A'));
//            columnNumber = (columnNumber - a0) / 26;
//        }
//        return sb.reverse().toString();
//    }
//}