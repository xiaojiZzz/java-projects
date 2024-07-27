package leetcode.medium;

import java.util.PriorityQueue;


/**
 * 给你一个整数数组 piles ，数组 下标从 0 开始 ，其中 piles[i] 表示第 i 堆石子中的石子数量。另给你一个整数 k ，请你执行下述操作 恰好 k 次：
 * 选出任一石子堆 piles[i] ，并从中 移除 floor(piles[i] / 2) 颗石子。
 * 注意：你可以对 同一堆 石子多次执行此操作。
 * 返回执行 k 次操作后，剩下石子的 最小 总数。
 * floor(x) 为 小于 或 等于 x 的 最大 整数。（即，对 x 向下取整）。
 * 示例 1：
 * 输入：piles = [5,4,9], k = 2
 * 输出：12
 * 解释：可能的执行情景如下：
 * - 对第 2 堆石子执行移除操作，石子分布情况变成 [5,4,5] 。
 * - 对第 0 堆石子执行移除操作，石子分布情况变成 [3,4,5] 。
 * 剩下石子的总数为 12 。
 * 示例 2：
 * 输入：piles = [4,3,6,7], k = 3
 * 输出：12
 * 解释：可能的执行情景如下：
 * - 对第 2 堆石子执行移除操作，石子分布情况变成 [4,3,3,7] 。
 * - 对第 3 堆石子执行移除操作，石子分布情况变成 [4,3,3,4] 。
 * - 对第 0 堆石子执行移除操作，石子分布情况变成 [2,3,3,4] 。
 * 剩下石子的总数为 12 。
 */
public class Solution_1962 {
    public int minStoneSum(int[] piles, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((a, b) -> (b - a));
        int n = piles.length;
        for (int i = 0; i < n; i++) {
            priorityQueue.add(piles[i]);
        }
        for (int i = 0; i < k; i++) {
            Integer integer = priorityQueue.poll();
            integer -= integer / 2;
            priorityQueue.add(integer);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += priorityQueue.poll();
        }
        return ans;
    }
}

/*
class Solution {
    public long minStoneSum(int[] piles, int k) {
        heapify(piles); // 原地堆化（最大堆）
        while (k-- > 0 && piles[0] > 0) {
            piles[0] -= piles[0] / 2; // 直接修改堆顶
            sink(piles, 0); // 堆化（只需要把 piles[0] 下沉）
        }

        int ans = 0;
        for (int x : piles) {
            ans += x;
        }
        return ans;
    }

    // 原地堆化（最大堆）
    // 堆化可以保证 h[0] 是堆顶元素，且 h[i] >= max(h[2*i+1], h[2*i+2])
    private void heapify(int[] h) {
        // 倒着遍历，从而保证 i 的左右子树一定是堆，那么 sink(h, i) 就可以把左右子树合并成一个堆
        // 下标 >= h.length / 2 的元素是二叉树的叶子，无需下沉
        for (int i = h.length / 2 - 1; i >= 0; i--) {
            sink(h, i);
        }
    }

    // 把 h[i] 不断下沉，每次找左右儿子中最大的交换，直到 i 的左右儿子都 <= h[i] 时停止
    private void sink(int[] h, int i) {
        int n = h.length;
        while (2 * i + 1 < n) {
            int j = 2 * i + 1; // i 的左儿子
            if (j + 1 < n && h[j + 1] > h[j]) { // i 的右儿子比 i 的左儿子大
                j++;
            }
            if (h[j] <= h[i]) { // 说明 i 的左右儿子都 <= h[i]，停止下沉
                break;
            }
            swap(h, i, j); // 下沉
            i = j;
        }
    }

    // 交换 h[i] 和 h[j]
    private void swap(int[] h, int i, int j) {
        int tmp = h[i];
        h[i] = h[j];
        h[j] = tmp;
    }
}
*/
