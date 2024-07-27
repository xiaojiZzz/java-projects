package leetcode.medium;

import java.util.ArrayList;


/**
 * 给你两个字符串 start 和 target ，长度均为 n 。每个字符串 仅 由字符 'L'、'R' 和 '_' 组成，其中：
 * 字符 'L' 和 'R' 表示片段，其中片段 'L' 只有在其左侧直接存在一个 空位 时才能向 左 移动，
 * 而片段 'R' 只有在其右侧直接存在一个 空位 时才能向 右 移动。
 * 字符 '_' 表示可以被 任意 'L' 或 'R' 片段占据的空位。
 * 如果在移动字符串 start 中的片段任意次之后可以得到字符串 target ，返回 true ；否则，返回 false 。
 * 示例 1：
 * 输入：start = "_L__R__R_", target = "L______RR"
 * 输出：true
 * 解释：可以从字符串 start 获得 target ，需要进行下面的移动：
 * - 将第一个片段向左移动一步，字符串现在变为 "L___R__R_" 。
 * - 将最后一个片段向右移动一步，字符串现在变为 "L___R___R" 。
 * - 将第二个片段向右移动散步，字符串现在变为 "L______RR" 。
 * 可以从字符串 start 得到 target ，所以返回 true 。
 * 示例 2：
 * 输入：start = "R_L_", target = "__LR"
 * 输出：false
 * 解释：字符串 start 中的 'R' 片段可以向右移动一步得到 "_RL_" 。
 * 但是，在这一步之后，不存在可以移动的片段，所以无法从字符串 start 得到 target 。
 */
public class Solution_2337 {
    public boolean canChange(String start, String target) {
        if (!cycle(start, target)) {
            return false;
        }
        char[] start_ = start.toCharArray();
        char[] target_ = target.toCharArray();
        int n = start.length();
        for (int i = 0; i < n; i++) {
            char c1 = start_[i];
            char c2 = target_[i];
            if (c1 == c2) {
                continue;
            } else if (c2 == 'L') {
                if (c1 == '_') {
                    for (int j = i + 1; j < n; j++) {
                        if (start_[j] == 'L') {
                            char tmp = c1;
                            start_[i] = start_[j];
                            start_[j] = tmp;
                            break;
                        }
                    }
                }
            } else if (c2 == '_') {
                if (c1 == 'R') {
                    for (int j = i + 1; j < n; j++) {
                        if (start_[j] == 'L') {
                            return false;
                        } else if (start_[j] == '_') {
                            char tmp = c1;
                            start_[i] = start_[j];
                            start_[j] = tmp;
                            break;
                        }
                    }
                } else if (c1 == 'L') {
                    return false;
                }
            } else if (c2 == 'R') {
                return false;
            }
        }
        return true;
    }

    private boolean cycle(String start, String target) {
        ArrayList<Character> list1 = new ArrayList<>();
        ArrayList<Character> list2 = new ArrayList<>();
        int n = start.length();
        for (int i = 0; i < n; i++) {
            char c = start.charAt(i);
            if (c != '_') {
                list1.add(c);
            }
        }
        for (int i = 0; i < n; i++) {
            char c = target.charAt(i);
            if (c != '_') {
                list2.add(c);
            }
        }
        int len1 = list1.size();
        int len2 = list2.size();
        if (len1 != len2) {
            return false;
        } else {
            for (int i = 0; i < len1; i++) {
                if (list1.get(i) != list2.get(i)) {
                    return false;
                }
            }
        }
        return true;
    }
}

/*
class Solution {
    public boolean canChange(String start, String target) {
        if (!start.replaceAll("_", "").equals(target.replaceAll("_", ""))) return false;
        for (int i = 0, j = 0; i < start.length(); i++) {
            if (start.charAt(i) == '_') continue;
            while (target.charAt(j) == '_') j++;
            if (i != j && (start.charAt(i) == 'L') == (i < j)) return false;
            ++j;
        }
        return true;
    }
}
*/

/*
class Solution {
    public boolean canChange(String start, String target) {
        int n = start.length();
        int i = 0, j = 0;
        while (i < n && j < n) {
            while (i < n && start.charAt(i) == '_') {
                i++;
            }
            while (j < n && target.charAt(j) == '_') {
                j++;
            }
            if (i < n && j < n) {
                if (start.charAt(i) != target.charAt(j)) {
                    return false;
                }
                char c = start.charAt(i);
                if ((c == 'L' && i < j) || (c == 'R' && i > j)) {
                    return false;
                }
                i++;
                j++;
            }
        }
        while (i < n) {
            if (start.charAt(i) != '_') {
                return false;
            }
            i++;
        }
        while (j < n) {
            if (target.charAt(j) != '_') {
                return false;
            }
            j++;
        }
        return true;
    }
}
*/
