package leetcode.simple;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * 总计有 n 个环，环的颜色可以是红、绿、蓝中的一种。这些环分别穿在 10 根编号为 0 到 9 的杆上。
 * 给你一个长度为 2n 的字符串 rings ，表示这 n 个环在杆上的分布。rings 中每两个字符形成一个 颜色位置对 ，用于描述每个环：
 * 第 i 对中的 第一个 字符表示第 i 个环的 颜色（'R'、'G'、'B'）。
 * 第 i 对中的 第二个 字符表示第 i 个环的 位置，也就是位于哪根杆上（'0' 到 '9'）。
 * 例如，"R3G2B1" 表示：共有 n == 3 个环，红色的环在编号为 3 的杆上，绿色的环在编号为 2 的杆上，蓝色的环在编号为 1 的杆上。
 * 找出所有集齐 全部三种颜色 环的杆，并返回这种杆的数量。
 * 示例 1：
 * 输入：rings = "B0B6G0R6R0R6G9"
 * 输出：1
 * 解释：
 * - 编号 0 的杆上有 3 个环，集齐全部颜色：红、绿、蓝。
 * - 编号 6 的杆上有 3 个环，但只有红、蓝两种颜色。
 * - 编号 9 的杆上只有 1 个绿色环。
 * 因此，集齐全部三种颜色环的杆的数目为 1 。
 * 示例 2：
 * 输入：rings = "B0R0G0R9R0B0G0"
 * 输出：1
 * 解释：
 * - 编号 0 的杆上有 6 个环，集齐全部颜色：红、绿、蓝。
 * - 编号 9 的杆上只有 1 个红色环。
 * 因此，集齐全部三种颜色环的杆的数目为 1 。
 * 示例 3：
 * 输入：rings = "G4"
 * 输出：0
 * 解释：
 * 只给了一个环，因此，不存在集齐全部三种颜色环的杆。
 */
public class Solution_2103 {
    public int countPoints(String rings) {
        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
        for (int i = 0; i < rings.length(); i += 2) {
            char first = rings.charAt(i);
            char end = rings.charAt(i + 1);
            int num1 = -1;
            int num2 = Integer.valueOf(String.valueOf(end));
            if (first == 'R') {
                num1 = 0;
            } else if (first == 'G') {
                num1 = 1;
            } else {
                num1 = 2;
            }
            HashSet<Integer> orDefault = map.get(num2);
            if (orDefault == null) {
                orDefault = new HashSet<>();
            }
            orDefault.add(num1);
            map.put(num2, orDefault);
        }
        int res = 0;
        Set<Map.Entry<Integer, HashSet<Integer>>> entries = map.entrySet();
        for (Map.Entry<Integer, HashSet<Integer>> entry : entries) {
            if (entry.getValue().size() == 3) {
                res++;
            }
        }
        return res;
    }
}

/*
class Solution {
    static final int POLE_NUM = 10;

    public int countPoints(String rings) {
        int[] state = new int[POLE_NUM];
        int n = rings.length();
        for (int i = 0; i < n; i += 2) {
            char color = rings.charAt(i);
            int poleIndex = rings.charAt(i + 1) - '0';
            if (color == 'R') {
                state[poleIndex] |= 1;
            } else if (color == 'G') {
                state[poleIndex] |= 2;
            } else {
                state[poleIndex] |= 4;
            }
        }
        int res = 0;
        for (int i = 0; i < POLE_NUM; i++) {
            res += state[i] == 7 ? 1 : 0;
        }
        return res;
    }
}
*/
