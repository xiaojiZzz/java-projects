package leetcode.medium;


/**
 * 给你一个字符串 word ，你可以向其中任何位置插入 "a"、"b" 或 "c" 任意次，返回使 word 有效 需要插入的最少字母数。
 * 如果字符串可以由 "abc" 串联多次得到，则认为该字符串 有效 。
 * 示例 1：
 * 输入：word = "b"
 * 输出：2
 * 解释：在 "b" 之前插入 "a" ，在 "b" 之后插入 "c" 可以得到有效字符串 "abc" 。
 * 示例 2：
 * 输入：word = "aaa"
 * 输出：6
 * 解释：在每个 "a" 之后依次插入 "b" 和 "c" 可以得到有效字符串 "abcabcabc" 。
 * 示例 3：
 * 输入：word = "abc"
 * 输出：0
 * 解释：word 已经是有效字符串，不需要进行修改。
 */
public class Solution_2645 {
    public int addMinimum(String word) {
        int n = word.length();
        int[] d = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            d[i] = d[i - 1] + 2;
            if (i > 1 && word.charAt(i - 1) > word.charAt(i - 2)) {
                d[i] = d[i - 1] - 1;
            }
        }
        return d[n];
    }
}

/*
class Solution {
    public int addMinimum(String word) {
        int ans = 0;
        int len = word.length();
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            char c = word.charAt(i);
            if (c == 'a') {
                if (stack.isEmpty()) {
                    stack.push(c);
                } else if (stack.peek() == 'a') {
                    ans += 2;
                } else if (stack.peek() == 'b') {
                    ans++;
                    stack.pop();
                }
            } else if (c == 'b') {
                if (stack.isEmpty()) {
                    ans++;
                    stack.push('a');
                    stack.push(c);
                } else if (stack.peek() == 'a') {
                    stack.push(c);
                } else {
                    stack.pop();
                    stack.pop();
                    ans += 2;
                    stack.push('a');
                    stack.push(c);
                }
            } else {
                if (stack.isEmpty()) {
                    ans += 2;
                } else if (stack.peek() == 'a') {
                    ans++;
                    stack.pop();
                } else if (stack.peek() == 'b') {
                    stack.pop();
                    stack.pop();
                }
            }
        }
        return ans + (stack.isEmpty() ? 0 : 3 - stack.size());
    }
}
*/

/*
class Solution {
    public int addMinimum(String word) {
        int n = word.length();
        int res = word.charAt(0) - word.charAt(n - 1) + 2;
        for (int i = 1; i < n; i++) {
            res += (word.charAt(i) - word.charAt(i - 1) + 2) % 3;
        }
        return res;
    }
}
*/

/*
class Solution {
    public int addMinimum(String word) {
        int n = word.length(), cnt = 1;
        for (int i = 1; i < n; i++) {
            if (word.charAt(i) <= word.charAt(i - 1)) {
                cnt++;
            }
        }
        return cnt * 3 - n;
    }
}
*/

/*
class Solution {
    public int addMinimum(String word) {
        String s = "abc";
        int ans = 0, n = word.length();
        for (int i = 0, j = 0; j < n; i = (i + 1) % 3) {
            if (word.charAt(j) != s.charAt(i)) {
                ++ans;
            } else {
                ++j;
            }
        }
        if (word.charAt(n - 1) != 'c') {
            ans += word.charAt(n - 1) == 'b' ? 1 : 2;
        }
        return ans;
    }
}
*/
