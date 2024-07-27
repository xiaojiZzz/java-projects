package leetcode.simple;


/**
 * 给你一个日期，请你设计一个算法来判断它是对应一周中的哪一天。
 * 输入为三个整数：day、month 和 year，分别表示日、月、年。
 * 您返回的结果必须是这几个值中的一个 {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}。
 * 示例 1：
 * 输入：day = 31, month = 8, year = 2019
 * 输出："Saturday"
 * 示例 2：
 * 输入：day = 18, month = 7, year = 1999
 * 输出："Sunday"
 * 示例 3：
 * 输入：day = 15, month = 8, year = 1993
 * 输出："Sunday"
 */
public class Solution_1185 {
    private static final String[] week = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    private static int[] months = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public String dayOfTheWeek(int day, int month, int year) {
        // 判断今天距离1970最后一天(周四)间隔多少天
        int ans = 4;  // 间隔天数
        for (int i = 1971; i < year; i++) {  // 先累加年数
            // 判断这一年是否为闰年
            boolean isLeap = (i % 4 == 0 && i % 100 != 0) || i % 400 == 0;
            ans += isLeap ? 366 : 365;
        }
        for (int i = 1; i < month; i++) {  // 再累加月数
            ans += months[i - 1];
            if (i == 2 && ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0))
                ans++;  // 判断闰月
        }
        ans += day;  // 最后累加天数
        return week[ans % 7];
    }
}
