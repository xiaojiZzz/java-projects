package leetcode.medium;

import java.util.*;


/**
 * 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。
 * 每个拨轮可以自由旋转：例如把 '9' 变为 '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
 * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
 * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
 * 字符串 target 代表可以解锁的数字，你需要给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 -1 。
 * 示例 1:
 * 输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * 输出：6
 * 解释：
 * 可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
 * 注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
 * 因为当拨动到 "0102" 时这个锁就会被锁定。
 */
public class Solution_752 {
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

/*
public class Solution_752 {
    // 打开锁的方法
    public int openLock(String[] deadends, String target) {
        if (target.equals("0000")) return 0;  // 如果目标就是初始状态，不需要旋转锁，直接返回 0
        HashSet<String> ds = new HashSet<>();  // 存放所有不可达的状态，即不能旋转到的状态
        for (String d : deadends) ds.add(d);  // 将不可达状态加入到集合 ds 中
        if (ds.contains("0000")) return -1;  // 如果初始状态是不可达状态，那么无法打开锁，直接返回 -1
        HashSet<String> seen = new HashSet<>();  // 存放已经访问过的状态
        seen.add("0000");  // 将初始状态加入到集合 seen 中
        Queue<String> queue = new LinkedList<>();  // 创建一个队列，存放所有可能的状态
        queue.offer("0000");  // 将初始状态加入到队列中
        int step = 0;  // 初始化步数为 0
        while (!queue.isEmpty()) {  // 当队列不为空时，一直循环
            step++;  // 步数加 1
            int size = queue.size();  // 获取队列中状态的个数
            while (size-- > 0) {  // 遍历队列中的每个状态
                String curr = queue.poll();  // 从队列中取出一个状态
                for (String s : nextStr(curr)) {  // 遍历当前状态 curr 可以转移到的所有状态
                    if (!seen.contains(s) && !ds.contains(s)) {  // 如果 s 没有被访问过且不是不可达状态
                        if (target.equals(s)) return step;  // 如果 s 是目标状态，返回当前步数
                        seen.add(s);  // 将 s 加入到已访问集合中
                        queue.offer(s);  // 将 s 加入到队列中，准备继续搜索
                    }
                }
            }
        }
        return -1;  // 没有找到目标状态，返回 -1
    }

    // 获取当前状态可以转移到的所有状态
    public List<String> nextStr(String str) {
        List<String> list = new ArrayList<>();  // 创建一个列表，存放所有可以转移到的状态
        char[] chars = str.toCharArray();  // 将当前状态转换为字符数组
        for (int i = 0; i < chars.length; i++) {  // 遍历当前状态中的每个数字
            char num = chars[i];  // 获取当前数字
            // 将当前数字向上转动一格
            chars[i] = num == '9' ? '0' : (char) (num + 1);  // 如果当前数字是 9，那么向上转动一格后变成 0；否则加 1
            list.add(new String(chars));  // 将转动一格后的
            // 状态加入到列表中
            // 将当前数字向下转动一格
            chars[i] = num == '0' ? '9' : (char) (num - 1);  // 如果当前数字是 0，那么向下转动一格后变成 9；否则减 1
            list.add(new String(chars));  // 将转动一格后的状态加入到列表中
            chars[i] = num;  // 将当前数字重置为原来的值，为下一次循环做准备
        }
        return list;  // 返回所有可以转移到的状态列表
    }
}
*/
