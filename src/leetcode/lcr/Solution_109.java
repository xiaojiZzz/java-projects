package leetcode.lcr;

import java.util.HashSet;


/**
 * 同leetcode 752.打开转盘锁
 */
public class Solution_109 {
    // 双向bfs
    public int openLock(String[] deadends, String target) {
        if (target.equals("0000"))
            return 0;  // 如果目标就是初始状态，不需要旋转锁，直接返回 0
        HashSet<String> ds = new HashSet<>();  // 存放所有不可达的状态，即不能旋转到的状态
        for (String d : deadends)
            ds.add(d);  // 将不可达状态加入到集合 ds 中
        if (ds.contains("0000"))
            return -1;  // 如果初始状态是不可达状态，那么无法打开锁，直接返回 -1
        HashSet<String> visited = new HashSet<>();
        visited.add("0000");
        HashSet<String> beginVisited = new HashSet<>();
        beginVisited.add("0000");
        HashSet<String> endVisited = new HashSet<>();
        endVisited.add(target);
        int step = 0;
        while (!beginVisited.isEmpty() && !endVisited.isEmpty()) {
            if (beginVisited.size() > endVisited.size()) {
                HashSet<String> temp = beginVisited;
                beginVisited = endVisited;
                endVisited = temp;
            }
            HashSet<String> nextVisited = new HashSet<>();
            for (String str : beginVisited) {
                if (curr(str, endVisited, visited, nextVisited, ds)) {
                    return step + 1;
                }
            }
            beginVisited = nextVisited;
            step++;
        }
        return -1;
    }

    //可能的变化
    public boolean curr(String word, HashSet<String> endVisited, HashSet<String> visited, HashSet<String> nextVisited, HashSet<String> wordset) {
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {  // 遍历当前状态中的每个数字
            char num = chars[i];  // 获取当前数字
            // 将当前数字向上转动一格
            chars[i] = num == '9' ? '0' : (char) (num + 1);  // 如果当前数字是 9，那么向上转动一格后变成 0；否则加 1
            String str1 = new String(chars);
            if (!wordset.contains(str1)) {
                if (endVisited.contains(str1)) {
                    return true;
                }
                if (!visited.contains(str1)) {
                    nextVisited.add(str1);
                    visited.add(str1);
                }
            }
            // 将当前数字向下转动一格
            chars[i] = num == '0' ? '9' : (char) (num - 1);  // 如果当前数字是 0，那么向下转动一格后变成 9；否则减 1
            String str2 = new String(chars);
            if (!wordset.contains(str2)) {
                if (endVisited.contains(str2)) {
                    return true;
                }
                if (!visited.contains(str2)) {
                    nextVisited.add(str2);
                    visited.add(str2);
                }
            }
            chars[i] = num;  // 将当前数字重置为原来的值，为下一次循环做准备
        }
        return false;
    }
}
