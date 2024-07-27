package leetcode.medium;

import java.util.*;


/**
 * 给你一个正整数 n ，它表示一个 有向无环图 中节点的数目，节点编号为 0 到 n - 1 （包括两者）。
 * 给你一个二维整数数组 edges ，其中 edges[i] = [fromi, toi] 表示图中一条从 fromi 到 toi 的单向边。
 * 请你返回一个数组 answer，其中 answer[i]是第 i 个节点的所有 祖先 ，这些祖先节点 升序 排序。
 * 如果 u 通过一系列边，能够到达 v ，那么我们称节点 u 是节点 v 的 祖先 节点。
 * 示例 1：
 * 输入：n = 8, edgeList = [[0,3],[0,4],[1,3],[2,4],[2,7],[3,5],[3,6],[3,7],[4,6]]
 * 输出：[[],[],[],[0,1],[0,2],[0,1,3],[0,1,2,3,4],[0,1,2,3]]
 * 解释：
 * 上图为输入所对应的图。
 * - 节点 0 ，1 和 2 没有任何祖先。
 * - 节点 3 有 2 个祖先 0 和 1 。
 * - 节点 4 有 2 个祖先 0 和 2 。
 * - 节点 5 有 3 个祖先 0 ，1 和 3 。
 * - 节点 6 有 5 个祖先 0 ，1 ，2 ，3 和 4 。
 * - 节点 7 有 4 个祖先 0 ，1 ，2 和 3 。
 * 示例 2：
 * 输入：n = 5, edgeList = [[0,1],[0,2],[0,3],[0,4],[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
 * 输出：[[],[0],[0,1],[0,1,2],[0,1,2,3]]
 * 解释：
 * 上图为输入所对应的图。
 * - 节点 0 没有任何祖先。
 * - 节点 1 有 1 个祖先 0 。
 * - 节点 2 有 2 个祖先 0 和 1 。
 * - 节点 3 有 3 个祖先 0 ，1 和 2 。
 * - 节点 4 有 4 个祖先 0 ，1 ，2 和 3 。
 */
public class Solution_2192 {
    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        List<Set<Integer>> setList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            setList.add(new HashSet<>());
        }
        int[] in = new int[n];
        List<Integer>[] lists = new ArrayList[n];
        Arrays.setAll(lists, v -> new ArrayList<>());
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1];
            lists[x].add(y);
            in[y]++;
        }
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (in[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            for (Integer i : lists[poll]) {
                in[i]--;
                if (in[i] == 0) {
                    queue.offer(i);
                }
                setList.get(i).add(poll);
                setList.get(i).addAll(setList.get(poll));
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ans.add(new ArrayList<>());
            ans.get(i).addAll(setList.get(i));
            Collections.sort(ans.get(i));
        }
        return ans;
    }
}
