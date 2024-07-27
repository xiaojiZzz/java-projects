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
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        lists.add(list1);
        if (rowIndex == 0) {
            return lists.get(0);
        }
        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(1);
        lists.add(list2);
        if (rowIndex == 1) {
            return lists.get(1);
        }
        for (int i = 2; i <= rowIndex; i++) {
            List<Integer> list = new ArrayList<>();
            list.add(1);
            for (int j = 0; j < i - 1; j++) {
                List<Integer> integers = lists.get(i - 1);
                int x = integers.get(j) + integers.get(j + 1);
                list.add(x);
            }
            list.add(1);
            lists.add(list);
        }
        return lists.get(rowIndex);
    }
}
