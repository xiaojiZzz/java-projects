package leetcode.difficulty;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * 给你一个无向图，无向图由整数 n  ，表示图中节点的数目，和 edges 组成，其中 edges[i] = [ui, vi] 表示 ui 和 vi 之间有一条无向边。
 * 同时给你一个代表查询的整数数组 queries 。第 j 个查询的答案是满足如下条件的点对 (a, b) 的数目：
 * a < b
 * cnt 是与 a 或者 b 相连的边的数目，且 cnt 严格大于 queries[j] 。
 * 请你返回一个数组 answers ，其中 answers.length == queries.length 且 answers[j] 是第 j 个查询的答案。
 * 请注意，图中可能会有 重复边 。
 * 示例 1：
 * 输入：n = 4, edges = [[1,2],[2,4],[1,3],[2,3],[2,1]], queries = [2,3]
 * 输出：[6,5]
 * 解释：每个点对中，与至少一个点相连的边的数目如上图所示。
 */
public class Solution_1782 {
    public int[] countPairs(int n, int[][] edges, int[] queries) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] ans = new int[queries.length];
        int[] edgeSum = new int[n + 1];
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1];
            if (x > y) {
                int tmp = x;
                x = y;
                y = tmp;
            }
            map.put(x * (n + 1) + y, map.getOrDefault((x * (n + 1) + y), 0) + 1);
            edgeSum[x]++;
            edgeSum[y]++;
        }
        int[] temp = edgeSum.clone();
        Arrays.sort(edgeSum);
        for (int i = 0; i < queries.length; i++) {
            int q = queries[i];
            int left = 1, right = n;
            while (left < right) {
                if (edgeSum[left] + edgeSum[right] <= q) {
                    left++;
                } else {
                    ans[i] += right - left;
                    right--;
                }
            }
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                int s = entry.getKey(), t = entry.getValue();
                int x = s / (n + 1), y = s % (n + 1);
                if (temp[x] + temp[y] > q && temp[x] + temp[y] - t <= q) {
                    ans[i]--;
                }
            }
        }
        return ans;
    }
}
