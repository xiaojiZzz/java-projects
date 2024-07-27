package leetcode.difficulty;

import java.util.HashSet;


/**
 * 给你一个无向图，整数 n 表示图中节点的数目，edges 数组表示图中的边，其中 edges[i] = [ui, vi] ，表示 ui 和 vi 之间有一条无向边。
 * 一个 连通三元组 指的是 三个 节点组成的集合且这三个点之间 两两 有边。
 * 连通三元组的度数 是所有满足此条件的边的数目：一个顶点在这个三元组内，而另一个顶点不在这个三元组内。
 * 请你返回所有连通三元组中度数的 最小值 ，如果图中没有连通三元组，那么返回 -1 。
 * 示例 1：
 * 输入：n = 6, edges = [[1,2],[1,3],[3,2],[4,1],[5,2],[3,6]]
 * 输出：3
 * 解释：只有一个三元组 [1,2,3] 。构成度数的边在上图中已被加粗。
 */
public class Solution_1761 {
    public int minTrioDegree(int n, int[][] edges) {
        HashSet<Integer>[] sets = new HashSet[n + 1];
        for (int i = 0; i < n + 1; i++) {
            sets[i] = new HashSet<>();
        }
        for (int i = 0; i < edges.length; i++) {
            int a = edges[i][0], b = edges[i][1];
            sets[a].add(b);
            sets[b].add(a);
        }
        int res = Integer.MAX_VALUE;
        for (int i = 1; i <= n - 2; i++) {
            for (int j = i + 1; j <= n - 1; j++) {
                for (int k = j + 1; k <= n; k++) {
                    boolean flag = sets[i].contains(j);
                    flag = flag && sets[j].contains(k);
                    flag = flag && sets[i].contains(k);
                    if (flag) {
                        res = Math.min(res, (sets[i].size() + sets[j].size() + sets[k].size() - 6));
                    }
                }
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
