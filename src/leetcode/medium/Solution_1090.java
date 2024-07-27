package leetcode.medium;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;


/**
 * 我们有一个 n 项的集合。给出两个整数数组 values 和 labels ，
 * 第 i 个元素的值和标签分别是 values[i] 和 labels[i]。还会给出两个整数 numWanted 和 useLimit 。
 * 从 n 个元素中选择一个子集 s :
 * 子集 s 的大小 小于或等于 numWanted 。
 * s 中 最多 有相同标签的 useLimit 项。
 * 一个子集的 分数 是该子集的值之和。
 * 返回子集 s 的最大 分数 。
 * 示例 1：
 * 输入：values = [5,4,3,2,1], labels = [1,1,2,2,3], numWanted = 3, useLimit = 1
 * 输出：9
 * 解释：选出的子集是第一项，第三项和第五项。
 * 示例 2：
 * 输入：values = [5,4,3,2,1], labels = [1,3,3,3,2], numWanted = 3, useLimit = 2
 * 输出：12
 * 解释：选出的子集是第一项，第二项和第三项。
 * 示例 3：
 * 输入：values = [9,8,8,7,6], labels = [0,0,0,1,1], numWanted = 3, useLimit = 1
 * 输出：16
 * 解释：选出的子集是第一项和第四项。
 */
public class Solution_1090 {
    public int largestValsFromLabels(int[] values, int[] labels, int numWanted, int useLimit) {
        int n = values.length;
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            priorityQueue.add(new int[]{values[i], labels[i]});
            if (!map.containsKey(labels[i])) {
                map.put(labels[i], 0);
            }
        }
        int ans = 0;
        int idx = 0;
        while (!priorityQueue.isEmpty()) {
            if (idx >= numWanted) {
                break;
            }
            int[] poll = priorityQueue.poll();
            int val = poll[0], lab = poll[1];
            Integer times = map.get(lab);
            if (times < useLimit) {
                ans += val;
                map.put(lab, times + 1);
                idx++;
            }
        }
        return ans;
    }
}

/*
class Solution {
    public int largestValsFromLabels(int[] values, int[] labels, int numWanted, int useLimit) {
        int n = values.length;
        Integer[] id = new Integer[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
        Arrays.sort(id, (a, b) -> values[b] - values[a]);

        int ans = 0, choose = 0;
        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
        for (int i = 0; i < n && choose < numWanted; ++i) {
            int label = labels[id[i]];
            if (cnt.getOrDefault(label, 0) == useLimit) {
                continue;
            }
            ++choose;
            ans += values[id[i]];
            cnt.put(label, cnt.getOrDefault(label, 0) + 1);
        }
        return ans;
    }
}
*/
