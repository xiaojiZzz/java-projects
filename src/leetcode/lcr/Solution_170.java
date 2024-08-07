package leetcode.lcr;

import java.util.*;

/**
 * 在股票交易中，如果前一天的股价高于后一天的股价，则可以认为存在一个「交易逆序对」。
 * 请设计一个程序，输入一段时间内的股票交易记录 record，返回其中存在的「交易逆序对」总数。
 * 示例 1:
 * 输入：record = [9, 7, 5, 4, 6]
 * 输出：8
 * 解释：交易中的逆序对为 (9, 7), (9, 5), (9, 4), (9, 6), (7, 5), (7, 4), (7, 6), (5, 4)。
 * 限制：
 * 0 <= record.length <= 50000
 */
public class Solution_170 {

    private int[] c;
    private int[] a;

    //离散化树状数组
    public int reversePairs(int[] nums) {
        List<Integer> resultList = new ArrayList<>();
        discretization(nums);
        init(nums.length + 5);
        for (int i = nums.length - 1; i >= 0; --i) {
            int id = getId(nums[i]);
            resultList.add(query(id - 1));
            update(id);
        }
        int count = 0;
        for (int i = 0; i < resultList.size(); i++) {
            count += resultList.get(i);
        }
        return count;
    }

    private void init(int length) {
        c = new int[length];
        Arrays.fill(c, 0);
    }

    private int lowBit(int x) {
        return x & (-x);
    }

    private void update(int pos) {
        while (pos < c.length) {
            c[pos] += 1;
            pos += lowBit(pos);
        }
    }

    private int query(int pos) {
        int ret = 0;
        while (pos > 0) {
            ret += c[pos];
            pos -= lowBit(pos);
        }

        return ret;
    }

    private void discretization(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int size = set.size();
        a = new int[size];
        int index = 0;
        for (int num : set) {
            a[index++] = num;
        }
        Arrays.sort(a);
    }

    private int getId(int x) {
        return Arrays.binarySearch(a, x) + 1;
    }
}

/*
class Solution {
    int[] record, tmp;
    public int reversePairs(int[] record) {
        this.record = record;
        tmp = new int[record.length];
        return mergeSort(0, record.length - 1);
    }
    private int mergeSort(int l, int r) {
        // 终止条件
        if (l >= r) return 0;
        // 递归划分
        int m = (l + r) / 2;
        int res = mergeSort(l, m) + mergeSort(m + 1, r);
        // 合并阶段
        int i = l, j = m + 1;
        for (int k = l; k <= r; k++)
            tmp[k] = record[k];
        for (int k = l; k <= r; k++) {
            if (i == m + 1)
                record[k] = tmp[j++];
            else if (j == r + 1 || tmp[i] <= tmp[j])
                record[k] = tmp[i++];
            else {
                record[k] = tmp[j++];
                res += m - i + 1; // 统计逆序对
            }
        }
        return res;
    }
}
*/
