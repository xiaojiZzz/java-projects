package leetcode.simple;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个非负索引 rowIndex，返回「杨辉三角」的第 rowIndex 行。
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 * 示例 1:
 * 输入: rowIndex = 3
 * 输出: [1,3,3,1]
 */
public class Solution_119 {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> pre = null, cur;
        for (int i = 0; i <= rowIndex; i++) {
            cur = new ArrayList<>(i + 1);
            for (int k = 0; k <= i; k++) {
                cur.add(0);
            }
            cur.set(0, 1);
            cur.set(i, 1);
            for (int j = 1; j < i; j++) {
                cur.set(j, pre.get(j - 1) + pre.get(j));
            }
            pre = cur;
        }
        return pre;
    }
}

/*
class Solution {

    private static final List<Integer>[] c = new List[34];

    static {
        c[0] = List.of(1);
        for (int i = 1; i < c.length; i++) {
            List<Integer> row = new ArrayList<>(i + 1); // 预分配空间
            row.add(1);
            for (int j = 1; j < i; j++) {
                // 左上方的数 + 正上方的数
                row.add(c[i - 1].get(j - 1) + c[i - 1].get(j));
            }
            row.add(1);
            c[i] = row;
        }
    }

    public List<Integer> getRow(int rowIndex) {
        return c[rowIndex];
    }
}
 */