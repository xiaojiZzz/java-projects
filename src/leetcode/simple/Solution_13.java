package leetcode.simple;


/**
 * 罗马数字 包含以下七种字符:I， V， X， L，C，D 和 M。
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如，罗马数字 2 写做 II ，即为两个并列的 I 。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 * 示例:
 * 输入: s = "LVIII"
 * 输出: 58
 * 解释: L = 50, V= 5, III = 3.
 */

public class Solution_13 {
    public int romanToInt(String s) {
//        HashMap<Character, Integer> mp = new HashMap<>();
//        mp.put('I', 1);
//        mp.put('V', 5);
//        mp.put('X', 10);
//        mp.put('L', 50);
//        mp.put('C', 100);
//        mp.put('D', 500);
//        mp.put('M', 1000);

        int length = s.length();
        int ans = 0;

        for (int i = 0; i < length - 1; i++) {
            int n = getValue(s.charAt(i));
            if (n < getValue(s.charAt(i + 1))) {
                ans -= getValue(s.charAt(i));
            } else {
                ans += getValue(s.charAt(i));
            }
        }
        return ans + getValue(s.charAt(length - 1));
    }

    private int getValue(char ch) {
        switch(ch) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }
}
