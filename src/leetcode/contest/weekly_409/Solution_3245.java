package leetcode.contest.weekly_409;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * 交替组 III
 * 给你一个整数数组 colors 和一个二维整数数组 queries 。colors表示一个由红色和蓝色瓷砖组成的环，第 i 块瓷砖的颜色为 colors[i] ：
 * colors[i] == 0 表示第 i 块瓷砖的颜色是 红色 。
 * colors[i] == 1 表示第 i 块瓷砖的颜色是 蓝色 。
 * 环中连续若干块瓷砖的颜色如果是 交替 颜色（也就是说这组瓷砖中除了第一块和最后一块瓷砖以外，中间瓷砖的颜色与它 左边 和 右边 的颜色都不同），
 * 那么它被称为一个 交替组。
 * 你需要处理两种类型的查询：
 * queries[i] = [1, sizei]，确定大小为sizei的 交替组 的数量。
 * queries[i] = [2, indexi, colori]，将colors[indexi]更改为colori。
 * 返回数组 answer，数组中按顺序包含第一种类型查询的结果。
 * 注意 ，由于 colors 表示一个 环 ，第一块 瓷砖和 最后一块 瓷砖是相邻的。
 * 示例 1：
 * 输入：colors = [0,1,1,0,1], queries = [[2,1,0],[1,4]]
 * 输出：[2]
 * 解释：
 * 第一次查询：
 * 将 colors[1] 改为 0。
 * 第二次查询：
 * 统计大小为 4 的交替组的数量：
 * 示例 2：
 * 输入：colors = [0,0,1,0,1,1], queries = [[1,3],[2,3,0],[1,5]]
 * 输出：[2,0]
 * 解释：
 * 第一次查询：
 * 统计大小为 3 的交替组的数量。
 * 第二次查询：colors不变。
 * 第三次查询：不存在大小为 5 的交替组。
 * 提示：
 * 4 <= colors.length <= 5 * 104
 * 0 <= colors[i] <= 1
 * 1 <= queries.length <= 5 * 104
 * queries[i][0] == 1 或 queries[i][0] == 2
 * 对于所有的i：
 * queries[i][0] == 1： queries[i].length == 2, 3 <= queries[i][1] <= colors.length - 1
 * queries[i][0] == 2： queries[i].length == 3, 0 <= queries[i][1] <= colors.length - 1, 0 <= queries[i][2] <= 1
 */
public class Solution_3245 {

    class FenwickTree {
        private final int[][] t;

        public FenwickTree(int n) {
            t = new int[n + 1][2];
        }

        // op=1，添加一个 size
        // op=-1，移除一个 size
        public void update(int size, int op) {
            for (int i = t.length - size; i < t.length; i += i & -i) {
                t[i][0] += op;
                t[i][1] += op * size;
            }
        }

        // 返回 >= size 的元素个数，元素和
        public int[] query(int size) {
            int cnt = 0, sum = 0;
            for (int i = t.length - size; i > 0; i &= i - 1) {
                cnt += t[i][0];
                sum += t[i][1];
            }
            return new int[]{cnt, sum};
        }
    }

    public List<Integer> numberOfAlternatingGroups(int[] a, int[][] queries) {
        int n = a.length;
        TreeSet<Integer> set = new TreeSet<>();
        FenwickTree t = new FenwickTree(n);

        for (int i = 0; i < n; i++) {
            if (a[i] == a[(i + 1) % n]) {
                add(set, t, n, i); // i 是一个结束位置
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int[] q : queries) {
            if (q[0] == 1) {
                if (set.isEmpty()) {
                    ans.add(n); // 每个长为 size 的子数组都符合要求
                } else {
                    int[] res = t.query(q[1]);
                    ans.add(res[1] - res[0] * (q[1] - 1));
                }
            } else {
                int i = q[1];
                if (a[i] == q[2]) { // 无影响
                    continue;
                }
                int pre = (i - 1 + n) % n;
                int nxt = (i + 1) % n;
                // 修改前，先去掉结束位置
                if (a[pre] == a[i]) {
                    del(set, t, n, pre);
                }
                if (a[i] == a[nxt]) {
                    del(set, t, n, i);
                }
                a[i] ^= 1;
                // 修改后，添加新的结束位置
                if (a[pre] == a[i]) {
                    add(set, t, n, pre);
                }
                if (a[i] == a[nxt]) {
                    add(set, t, n, i);
                }
            }
        }
        return ans;
    }

    // 添加一个结束位置 i
    private void add(TreeSet<Integer> set, FenwickTree t, int n, int i) {
        if (set.isEmpty()) {
            t.update(n, 1);
        } else {
            update(set, t, n, i, 1);
        }
        set.add(i);
    }

    // 移除一个结束位置 i
    private void del(TreeSet<Integer> set, FenwickTree t, int n, int i) {
        set.remove(i);
        if (set.isEmpty()) {
            t.update(n, -1);
        } else {
            update(set, t, n, i, -1);
        }
    }

    // op=1，添加一个结束位置 i
    // op=-1，移除一个结束位置 i
    private void update(TreeSet<Integer> set, FenwickTree t, int n, int i, int op) {
        Integer pre = set.floor(i);
        if (pre == null) {
            pre = set.last();
        }

        Integer nxt = set.ceiling(i);
        if (nxt == null) {
            nxt = set.first();
        }

        t.update((nxt - pre + n - 1) % n + 1, -op); // 移除/添加旧长度
        t.update((i - pre + n) % n, op);
        t.update((nxt - i + n) % n, op); // 添加/移除新长度
    }
}
