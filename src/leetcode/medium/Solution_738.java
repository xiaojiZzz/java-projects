package leetcode.medium;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 单调递增的数字
 * 当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。
 * 给定一个整数 n ，返回 小于或等于 n 的最大数字，且数字呈 单调递增 。
 * 示例 1:
 * 输入: n = 10
 * 输出: 9
 * 示例 2:
 * 输入: n = 1234
 * 输出: 1234
 * 示例 3:
 * 输入: n = 332
 * 输出: 299
 * 提示:
 * 0 <= n <= 109
 */
public class Solution_738 {
    public int monotoneIncreasingDigits(int n) {
        String s = String.valueOf(n);
        Deque<Character> stack = new ArrayDeque<>();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if (!stack.isEmpty() && stack.peek() > s.charAt(i)) {
                char min = stack.pop();
                while (!stack.isEmpty() && stack.peek() > min - 1) {
                    stack.pop();
                }
                stack.push((char) (min - 1));
                for (int j = stack.size(); j < len; j++) {
                    stack.push('9');
                }
                break;
            }
            stack.push(s.charAt(i));
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (!stack.isEmpty()) {
            stringBuilder.append(stack.pollLast());
        }
        return Integer.parseInt(stringBuilder.toString());
    }
}

/*
class Solution {
    public int monotoneIncreasingDigits(int n) {
        char[] strN = Integer.toString(n).toCharArray();
        int i = 1;
        while (i < strN.length && strN[i - 1] <= strN[i]) {
            i += 1;
        }
        if (i < strN.length) {
            while (i > 0 && strN[i - 1] > strN[i]) {
                strN[i - 1] -= 1;
                i -= 1;
            }
            for (i += 1; i < strN.length; ++i) {
                strN[i] = '9';
            }
        }
        return Integer.parseInt(new String(strN));
    }
}
*/

/*
class Solution {
    public int monotoneIncreasingDigits(int N) {
        char[] arr = (N + "").toCharArray();
        int max = -1, idx = -1;
        for (int i = 0; i < arr.length - 1; i++) {
            // 只判断小于的情况，这样下面 arr[idx] -= 1; 直接减一就行，不需要判断相邻位置数重复的情况了
            if (max < arr[i]) {
                max = arr[i];
                idx = i;
            }
            if (arr[i] > arr[i + 1]) {
                arr[idx] -= 1;
                for (int j = idx + 1; j < arr.length; j++) {
                    arr[j] = '9';
                }
                break;
            }
        }
        return Integer.parseInt(new String(arr));
    }
}
*/

/*
class Solution {
    public int monotoneIncreasingDigits(int n) {
        String s = String.valueOf(n);
        char[] chars = s.toCharArray();
        int idx = s.length();
        for (int i = s.length() - 1; i > 0; i--) {
            if (chars[i - 1] > chars[i]) {
                chars[i - 1]--;
                idx = i;
            }
        }
        for (int i = idx; i < s.length(); i++) {
            chars[i] = '9';
        }
        return Integer.parseInt(String.valueOf(chars));
    }
}
*/