package leetcode.medium;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;


/**
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 * 示例 1：
 * 输入：s = "3[a]2[bc]"
 * 输出："aaabcbc"
 * 示例 2：
 * 输入：s = "3[a2[c]]"
 * 输出："accaccacc"
 * 示例 3：
 * 输入：s = "2[abc]3[cd]ef"
 * 输出："abcabccdcdcdef"
 * 示例 4：
 * 输入：s = "abc3[cd]xyz"
 * 输出："abccdcdcdxyz"
 */
public class Solution_394 {
    private int ptr;

    public String decodeString(String s) {
        Deque<String> stk = new LinkedList<>();
        ptr = 0;

        while (ptr < s.length()) {
            char cur = s.charAt(ptr);
            if (Character.isDigit(cur)) {
                // 获取一个数字并进栈
                String digits = getDigits(s);
                stk.addLast(digits);
            } else if (Character.isLetter(cur) || cur == '[') {
                // 获取一个字母并进栈
                stk.addLast(String.valueOf(s.charAt(ptr++)));
            } else {
                ++ptr;
                LinkedList<String> sub = new LinkedList<>();
                while (!"[".equals(stk.peekLast())) {
                    sub.addLast(stk.removeLast());
                }
                Collections.reverse(sub);
                // 左括号出栈
                stk.removeLast();
                // 此时栈顶为当前 sub 对应的字符串应该出现的次数
                int repTime = Integer.parseInt(stk.removeLast());
                StringBuffer t = new StringBuffer();
                String o = getString(sub);
                // 构造字符串
                while (repTime-- > 0) {
                    t.append(o);
                }
                // 将构造好的字符串入栈
                stk.addLast(t.toString());
            }
        }

        return getString(stk);
    }

    public String getDigits(String s) {
        StringBuffer ret = new StringBuffer();
        while (Character.isDigit(s.charAt(ptr))) {
            ret.append(s.charAt(ptr++));
        }
        return ret.toString();
    }

    public String getString(Deque<String> v) {
        StringBuffer ret = new StringBuffer();
        for (String s : v) {
            ret.append(s);
        }
        return ret.toString();
    }
}

/*
class Solution {
    int index = 0;

    public String decodeString(String s) {
        char[] ch = s.toCharArray();
        String ans = getString(ch, 0);
        return ans;
    }

    public String getString(char[] ch, int i) {
        StringBuilder builder = new StringBuilder();
        int cnt = 0;
        while (i < ch.length && ch[i] != ']') {
            if (ch[i] >= '0' && ch[i] <= '9') {
                cnt = cnt * 10 + ch[i++] - '0';
            } else if (ch[i] >= 'a' && ch[i] <= 'z') {
                builder.append(ch[i++]);
            } else {
                builder.append(get(cnt, getString(ch, i + 1)));
                i = index + 1;
                cnt = 0;
            }
        }
        index = i;
        return builder.toString();
    }

    public String get(int cnt, String s) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < cnt; i++) {
            builder.append(s);
        }
        return builder.toString();
    }
}
*/

/*
class Solution {
    public String decodeString(String s) {
        StringBuilder res = new StringBuilder();
        int multi = 0;
        LinkedList<Integer> stack_multi = new LinkedList<>();
        LinkedList<String> stack_res = new LinkedList<>();
        for (Character c : s.toCharArray()) {
            if (c == '[') {
                stack_multi.addLast(multi);
                stack_res.addLast(res.toString());
                multi = 0;
                res = new StringBuilder();
            } else if (c == ']') {
                StringBuilder tmp = new StringBuilder();
                int cur_multi = stack_multi.removeLast();
                for (int i = 0; i < cur_multi; i++) {
                    tmp.append(res);
                }
                res = new StringBuilder(stack_res.removeLast() + tmp);
            } else if (c >= '0' && c <= '9') {
                multi = multi * 10 + Integer.parseInt(c + "");
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }
}
*/

/*
class Solution {
    public String decodeString(String s) {
        // 双栈解法：
        // 准备两个栈：一个存放数字，一个存放字符串
        // 遍历字符有四种情况
        // 1、如果是数字 将数字转成整型数字等待处理
        // 2、如果是字符 将字符添加到当前临时字符串中
        // 3、如果是'['  将当前数字和临时字符串添加到各自栈中
        // 4、如果是']'  将数字和字符栈各取出，然后拼接成新的临时字符串
        // Java 推荐用Deque ArrayDeque实现栈
        // 创建数字栈，创建字符串栈 及临时数字和临时字符串
        Deque<Integer> stack_digit = new ArrayDeque<>();
        Deque<StringBuilder> stack_string = new ArrayDeque<>();
        int tNum = 0;
        StringBuilder tString = new StringBuilder();
        int i = 0;
        // 遍历字符串 分4中情况
        while (i < s.length()) {
            char ch = s.charAt(i++);
            if (ch == '[') { // 如果是"[" 将临时数字和临时字符串入栈
                stack_digit.push(tNum);
                stack_string.push(tString);
                tNum = 0;
                tString = new StringBuilder();
            } else if (ch == ']') { // 如果是"]" 将数字和字符串出栈 此时临时字符串res = 出栈字符串 + 出栈数字*res
                StringBuilder temp = stack_string.pop();
                int count = stack_digit.pop();
                for (int j = 0; j < count; j++) {
                    temp.append(tString.toString());
                }
                tString = temp;
            } else if ('0' <= ch && ch <= '9') {
                // 如果是数字 将字符转成整型数字 ch-‘0’。 注意数字不一定是个位 比如100[a] 所以digit要*10
                tNum = tNum * 10 + ch - '0';
            } else {
                // 如果是字符 直接将字符放在临时字符串中
                tString.append(ch);
            }
        }
        return tString.toString();
    }
}
*/
