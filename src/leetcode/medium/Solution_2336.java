package leetcode.medium;

import java.util.PriorityQueue;


/**
 * 现有一个包含所有正整数的集合 [1, 2, 3, 4, 5, ...] 。
 * 实现 SmallestInfiniteSet 类：
 * SmallestInfiniteSet() 初始化 SmallestInfiniteSet 对象以包含 所有 正整数。
 * int popSmallest() 移除 并返回该无限集中的最小整数。
 * void addBack(int num) 如果正整数 num 不 存在于无限集中，则将一个 num 添加 到该无限集中。
 * 示例：
 * 输入
 * ["SmallestInfiniteSet", "addBack", "popSmallest", "popSmallest", "popSmallest",
 * "addBack", "popSmallest", "popSmallest", "popSmallest"]
 * [[], [2], [], [], [], [1], [], [], []]
 * 输出
 * [null, null, 1, 2, 3, null, 1, 4, 5]
 * 解释
 * SmallestInfiniteSet smallestInfiniteSet = new SmallestInfiniteSet();
 * smallestInfiniteSet.addBack(2);    // 2 已经在集合中，所以不做任何变更。
 * smallestInfiniteSet.popSmallest(); // 返回 1 ，因为 1 是最小的整数，并将其从集合中移除。
 * smallestInfiniteSet.popSmallest(); // 返回 2 ，并将其从集合中移除。
 * smallestInfiniteSet.popSmallest(); // 返回 3 ，并将其从集合中移除。
 * smallestInfiniteSet.addBack(1);    // 将 1 添加到该集合中。
 * smallestInfiniteSet.popSmallest(); // 返回 1 ，因为 1 在上一步中被添加到集合中，
 *                                    // 且 1 是最小的整数，并将其从集合中移除。
 * smallestInfiniteSet.popSmallest(); // 返回 4 ，并将其从集合中移除。
 * smallestInfiniteSet.popSmallest(); // 返回 5 ，并将其从集合中移除。
 */
public class Solution_2336 {
    class SmallestInfiniteSet {
        private PriorityQueue<Integer> priorityQueue;

        public SmallestInfiniteSet() {
            priorityQueue = new PriorityQueue<>();
            for (int i = 1; i < 1001; i++) {
                priorityQueue.add(i);
            }
        }

        public int popSmallest() {
            return priorityQueue.poll();
        }

        public void addBack(int num) {
            if (priorityQueue.contains(num)) {
                return;
            }
            priorityQueue.add(num);
        }
    }
}

/*
class SmallestInfiniteSet {
    boolean[] vis = new boolean[1010];
    PriorityQueue<Integer> q = new PriorityQueue<>((a,b)->a-b);
    int idx = 1;
    public int popSmallest() {
        int ans = -1;
        if (!q.isEmpty()) {
            ans = q.poll();
            vis[ans] = false;
        } else {
            ans = idx++;
        }
        return ans;
    }
    public void addBack(int x) {
        if (x >= idx || vis[x]) return ;
        if (x == idx - 1) {
            idx--;
        } else {
            q.add(x);
            vis[x] = true;
        }
    }
}
*/
