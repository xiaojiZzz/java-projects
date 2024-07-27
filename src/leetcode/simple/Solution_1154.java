package leetcode.simple;


/**
 * 给你一个字符串 date ，按 YYYY-MM-DD 格式表示一个 现行公元纪年法 日期。返回该日期是当年的第几天。
 * 示例 1：
 * 输入：date = "2019-01-09"
 * 输出：9
 * 解释：给定日期是2019年的第九天。
 * 示例 2：
 * 输入：date = "2019-02-10"
 * 输出：41
 */
public class Solution_1154 {
    private int[] months = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public int dayOfYear(String date) {
        int days = 0;
        String[] strings = date.split("-");
        int year = Integer.parseInt(strings[0]), month = Integer.parseInt(strings[1]), day = Integer.parseInt(strings[2]);
        if (year % 400 == 0 || year % 4 == 0 && year % 100 != 0) {
            months[1] += 1;
        }
        for (int i = 1; i < month; i++) {
            days += months[i - 1];
        }
        return days + day;
    }
}
