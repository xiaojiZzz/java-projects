package leetcode.simple;

/**
 * 检查棋盘方格颜色是否相同
 * 给你两个字符串 coordinate1 和 coordinate2，代表 8 x 8 国际象棋棋盘上的两个方格的坐标。
 * 以下是棋盘的参考图。
 * 如果这两个方格颜色相同，返回 true，否则返回 false。
 * 坐标总是表示有效的棋盘方格。坐标的格式总是先字母（表示列），再数字（表示行）。
 * 示例 1：
 * 输入： coordinate1 = "a1", coordinate2 = "c3"
 * 输出： true
 * 解释：
 * 两个方格均为黑色。
 * 示例 2：
 * 输入： coordinate1 = "a1", coordinate2 = "h3"
 * 输出： false
 * 解释：
 * 方格 "a1" 是黑色，而 "h3" 是白色。
 * 提示：
 * coordinate1.length == coordinate2.length == 2
 * 'a' <= coordinate1[0], coordinate2[0] <= 'h'
 * '1' <= coordinate1[1], coordinate2[1] <= '8'
 */
public class Solution_3274 {
    public boolean checkTwoChessboards(String coordinate1, String coordinate2) {
        return ((coordinate1.charAt(0) & 1) ^ (coordinate1.charAt(1) & 1)) ==
                ((coordinate2.charAt(0) & 1) ^ (coordinate2.charAt(1) & 1));
    }
}

/*
class Solution {
    public boolean checkTwoChessboards(String s, String t) {
        // 相加是偶数就是黑色，反之为白色
        int a = (s.charAt(0) + s.charAt(1)) % 2;
        int b = (t.charAt(0) + t.charAt(1)) % 2;
        return a == b;
    }
}
*/

/*
class Solution {
    public boolean checkTwoChessboards(String coordinate1, String coordinate2) {
        // 行数或列数变化 1 时，颜色就会变化
        return (coordinate1.charAt(0) - coordinate2.charAt(0) + coordinate1.charAt(1) - coordinate2.charAt(1)) % 2 == 0;
    }
}
*/