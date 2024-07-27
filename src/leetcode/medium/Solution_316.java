package leetcode.medium;

import java.util.ArrayDeque;
import java.util.Deque;


/**
 * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 * 示例 1：
 * 输入：s = "bcabc"
 * 输出："abc"
 * 示例 2：
 * 输入：s = "cbacdcbc"
 * 输出："acdb"
 */
public class Solution_316 {
    public String removeDuplicateLetters(String s) {
        int len = s.length();
        //转换原因是因为，在遍历输入字符串时，charAt方法会去检查数组下标是否越界
        char[] charArray = s.toCharArray();
        //记录在遍历过程中，出现字符的最后一个下标
        int[] lastIndex = new int[26];//题目要求都是小写字符，所以26足够
        //记录输入字符串中，每一个出现的字符，最后一次出现的下标
        for (int i = 0; i < len; i++) {
            lastIndex[charArray[i] - 'a'] = i;
        }
        //记录字母升序排列的结果
        Deque<Character> stack = new ArrayDeque<>();
        //记录某一个字符在"栈"中是否出现
        boolean[] visited = new boolean[26];//题目要求都是小写字符，所以26足够
        for (int i = 0; i < len; i++) {
            //如果某个字符在"栈"中已经存在，则丢弃该字符
            if (visited[charArray[i] - 'a']) {
                continue;
            }
            //"栈"顶元素的ASCII码值 比当前元素的ASCII码值 大
            // &&  "栈"顶元素在之后还会出现
            while (!stack.isEmpty()
                    && stack.peekLast() > charArray[i]
                    && lastIndex[stack.peekLast() - 'a'] > i) {
                Character top = stack.removeLast();
                visited[top - 'a'] = false;
            }
            //将元素入"栈"（不该入栈时，前面已经continue了）
            stack.addLast(charArray[i]);
            //设置该元素已经在栈内出现过了
            visited[charArray[i] - 'a'] = true;
        }
        //"栈"底到"栈"顶的顺序，就是我们的答案
        StringBuilder sb = new StringBuilder();
        for (Character ch : stack) {
            sb.append(ch);
        }
        return sb.toString();
    }
}

/*
class Solution {
    public String smallestSubsequence(String s) {
        boolean[] vis = new boolean[26];
        int[] num = new int[26];
        for (int i = 0; i < s.length(); i++) {
            num[s.charAt(i) - 'a']++;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!vis[ch - 'a']) {
                while (sb.length() > 0 && sb.charAt(sb.length() - 1) > ch) {
                    if (num[sb.charAt(sb.length() - 1) - 'a'] > 0) {
                        vis[sb.charAt(sb.length() - 1) - 'a'] = false;
                        sb.deleteCharAt(sb.length() - 1);
                    } else {
                        break;
                    }
                }
                vis[ch - 'a'] = true;
                sb.append(ch);
            }
            num[ch - 'a'] -= 1;
        }
        return sb.toString();
    }
}
*/
