package leetcode.medium;

import java.util.HashMap;
import java.util.Map;


/**
 * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。
 * 数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给你一个整数，将其转为罗马数字。
 * 示例 1:
 * 输入: num = 3
 * 输出: "III"
 * 示例 2:
 * 输入: num = 4
 * 输出: "IV"
 * 示例 3:
 * 输入: num = 9
 * 输出: "IX"
 * 示例 4:
 * 输入: num = 58
 * 输出: "LVIII"
 * 解释: L = 50, V = 5, III = 3.
 * 示例 5:
 * 输入: num = 1994
 * 输出: "MCMXCIV"
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 */
public class Solution_12 {
    public String intToRoman(int num) {
        Map<Integer, Character> map = new HashMap<>();
        map.put(1, 'I');
        map.put(5, 'V');
        map.put(10, 'X');
        map.put(50, 'L');
        map.put(100, 'C');
        map.put(500, 'D');
        map.put(1000, 'M');
        StringBuilder stringBuilder = new StringBuilder();
        int multiple = 1000;
        while (multiple > 0) {
            int i = num / multiple;
            if (i == 4) {
                stringBuilder.append(map.get(multiple));
                stringBuilder.append(map.get(multiple * 5));
            } else if (i == 9) {
                stringBuilder.append(map.get(multiple));
                stringBuilder.append(map.get(10 * multiple));
            } else if (i < 4) {
                for (int j = 0; j < i; j++) {
                    stringBuilder.append(map.get(multiple));
                }
            } else {
                stringBuilder.append(map.get(5 * multiple));
                for (int j = 0; j < i - 5; j++) {
                    stringBuilder.append(map.get(multiple));
                }
            }
            num -= i * multiple;
            multiple /= 10;
        }
        return stringBuilder.toString();
    }
}

/*
class Solution {
    int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public String intToRoman(int num) {
        StringBuffer res = new StringBuffer();
        for (int i = 0; i < values.length; i++) {
            int value = values[i];
            String symbol = symbols[i];
            while (value <= num) {
                num -= value;
                res.append(symbol);
            }
            if (num == 0) break;
        }
        return res.toString();
    }
}
*/
