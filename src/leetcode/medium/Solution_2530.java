package leetcode.medium;

import java.util.PriorityQueue;


/**
 * 给你一个下标从 0 开始的整数数组 nums 和一个整数 k 。你的 起始分数 为 0 。
 * 在一步 操作 中：
 * 选出一个满足 0 <= i < nums.length 的下标 i ，
 * 将你的 分数 增加 nums[i] ，并且
 * 将 nums[i] 替换为 ceil(nums[i] / 3) 。
 * 返回在 恰好 执行 k 次操作后，你可能获得的最大分数。
 * 向上取整函数 ceil(val) 的结果是大于或等于 val 的最小整数。
 * 示例 1：
 * 输入：nums = [10,10,10,10,10], k = 5
 * 输出：50
 * 解释：对数组中每个元素执行一次操作。最后分数是 10 + 10 + 10 + 10 + 10 = 50 。
 * 示例 2：
 * 输入：nums = [1,10,3,3,3], k = 3
 * 输出：17
 * 解释：可以执行下述操作：
 * 第 1 步操作：选中 i = 1 ，nums 变为 [1,4,3,3,3] 。分数增加 10 。
 * 第 2 步操作：选中 i = 1 ，nums 变为 [1,2,3,3,3] 。分数增加 4 。
 * 第 3 步操作：选中 i = 2 ，nums 变为 [1,1,1,3,3] 。分数增加 3 。
 * 最后分数是 10 + 4 + 3 = 17 。
 */
public class Solution_2530 {
    public long maxKelements(int[] nums, int k) {
        long res = 0;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((a, b) -> b - a);
        for (int num : nums) {
            priorityQueue.add(num);
        }
        for (int i = 0; i < k; i++) {
            Integer poll = priorityQueue.poll();
            res += poll;
            poll = (poll + 2) / 3;
            priorityQueue.add(poll);
        }
        return res;
    }
}

/*
class Solution {
    public long maxKelements(int[] nums, int k) {
        heapify(nums); // 原地堆化（最大堆）
        long ans = 0;
        while (k-- > 0) {
            ans += nums[0]; // 堆顶
            nums[0] = (nums[0] + 2) / 3;
            sink(nums, 0); // 堆化（只需要把 nums[0] 下沉）
        }
        return ans;
    }

    // 原地堆化（最大堆）
    // 堆化可以保证 h[0] 是堆顶元素，且 h[i] >= max(h[2*i+1], h[2*i+2])
    private void heapify(int[] h) {
        // 下标 >= h.length / 2 的元素是二叉树的叶子，无需下沉
        // 倒着遍历，从而保证 i 的左右子树一定是堆，那么 sink(h, i) 就可以把左右子树合并成一个堆
        for (int i = h.length / 2 - 1; i >= 0; i--) {
            sink(h, i);
        }
    }

    // 把 h[i] 不断下沉，直到 i 的左右儿子都 <= h[i]
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

/*
public class Solution {
    //用时最短
    public long maxKelements(int[] nums, int k) {
        long s = 0;
        int count = 0, n = nums.length;
        while (count < k) {
            Arrays.sort(nums);
            int i = n - 1, min = (nums[n - 1] + 2) / 3;
            while (i >= 0 && count < k && nums[i] >= min) {
                s += nums[i--];
                count++;
            }
            if (count == k) break;
            while (++i < n) nums[i] = (nums[i] + 2) / 3;
        }
        return s;
    }
}
*/
