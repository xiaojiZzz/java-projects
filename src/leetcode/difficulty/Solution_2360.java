package leetcode.difficulty;

import java.util.*;

/**
 * 图中的最长环
 * 给你一个 n 个节点的 有向图 ，节点编号为 0 到 n - 1 ，其中每个节点 至多 有一条出边。
 * 图用一个大小为 n 下标从 0 开始的数组 edges 表示，节点 i 到节点 edges[i] 之间有一条有向边。如果节点 i 没有出边，那么 edges[i] == -1 。
 * 请你返回图中的 最长 环，如果没有任何环，请返回 -1 。
 * 一个环指的是起点和终点是 同一个 节点的路径。
 * 示例 1：
 * 输入：edges = [3,3,4,2,3]
 * 输出去：3
 * 解释：图中的最长环是：2 -> 4 -> 3 -> 2 。
 * 这个环的长度为 3 ，所以返回 3 。
 * 示例 2：
 * 输入：edges = [2,-1,3,1]
 * 输出：-1
 * 解释：图中没有任何环。
 * 提示：
 * n == edges.length
 * 2 <= n <= 105
 * -1 <= edges[i] < n
 * edges[i] != i
 */
public class Solution_2360 {
    public int longestCycle(int[] edges) {
        // 拓扑排序
        int size = edges.length;
        // 储存每个点的入度
        int[] cnt = new int[size];
        for (int edge : edges) {
            if (edge != -1) {
                cnt[edge]++;
            }
        }
        // 队列用来储存入度为 0 的点
        Queue<Integer> queue = new ArrayDeque<>();
        // 将入度为 0 的点先入队列 queue
        for (int i = 0; i < size; i++) {
            if (cnt[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            int top = queue.poll();
            if (edges[top] != -1 && --cnt[edges[top]] == 0) {
                queue.add(edges[top]);
            }
        }
        int ans = -1;
        // 跑完拓扑排序后，cnt[i] != 0 的都是环里面的元素
        for (int i = 0; i < size; i++) {
            if (cnt[i] != 0) {
                // count 记录环的大小, cur 遍历环中每个元素
                int count = 0, cur = i;
                while (cnt[cur] != 0) {
                    count++;
                    cnt[cur]--;
                    cur = edges[cur];
                }
                ans = Math.max(ans, count);
            }
        }
        return ans;
    }
}

/*
class Solution {
    public int longestCycle(int[] edges) {
        int n = edges.length;
        Set<Integer> hash = new HashSet<>();
        Map<Integer, Integer> mp = new HashMap<>();
        int ret = -1;
        for (int i = 0; i < n; i++) {
            if (!hash.contains(i)) {
                ret = Math.max(ret, dfs(edges, hash, mp, i, 0));
            }
        }
        return ret;
    }

    private int dfs(int[] edges, Set<Integer> hash, Map<Integer, Integer> mp, int x, int ans) {
        if (x == -1) {
            return -1;
        }
        if (mp.containsKey(x)) {
            return ans - mp.get(x);
        }
        if (hash.contains(x)) {
            return -1;
        }
        mp.put(x, ans);
        hash.add(x);
        int result = dfs(edges, hash, mp, edges[x], ans + 1);
        mp.remove(x);
        return result;
    }
}
*/

/*
class Solution {
    public int longestCycle(int[] edges) {
        int n = edges.length;
        int ans = -1;
        int curTime = 1; // 当前时间
        int[] visTime = new int[n]; // 首次访问 x 的时间
        for (int i = 0; i < n; i++) {
            int x = i;
            int startTime = curTime; // 本轮循环的开始时间
            while (x != -1 && visTime[x] == 0) { // 没有访问过 x
                visTime[x] = curTime++; // 记录访问 x 的时间
                x = edges[x]; // 访问下一个节点
            }
            if (x != -1 && visTime[x] >= startTime) { // x 在本轮循环中访问了两次，说明 x 在环上
                ans = Math.max(ans, curTime - visTime[x]); // 前后两次访问 x 的时间差，即为环长
            }
        }
        return ans; // 如果没有找到环，返回的是 ans 的初始值 -1
    }
}
*/