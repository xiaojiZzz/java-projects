package leetcode.difficulty;

import java.util.List;
import java.util.PriorityQueue;

/**
 * 最小区间
 * 你有 k 个 非递减排列 的整数列表。找到一个 最小 区间，使得 k 个列表中的每个列表至少有一个数包含在其中。
 * 我们定义如果 b-a < d-c 或者在 b-a == d-c 时 a < c，则区间 [a,b] 比 [c,d] 小。
 * 示例 1：
 * 输入：nums = [[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
 * 输出：[20,24]
 * 解释：
 * 列表 1：[4, 10, 15, 24, 26]，24 在区间 [20,24] 中。
 * 列表 2：[0, 9, 12, 20]，20 在区间 [20,24] 中。
 * 列表 3：[5, 18, 22, 30]，22 在区间 [20,24] 中。
 * 示例 2：
 * 输入：nums = [[1,2,3],[1,2,3],[1,2,3]]
 * 输出：[1,1]
 * 提示：
 * nums.length == k
 * 1 <= k <= 3500
 * 1 <= nums[i].length <= 50
 * -105 <= nums[i][j] <= 105
 * nums[i] 按非递减顺序排列
 */
public class Solution_632 {
    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int r = Integer.MIN_VALUE;
        for (int i = 0; i < nums.size(); i++) {
            // 把每个列表的第一个元素入堆
            int x = nums.get(i).get(0);
            pq.offer(new int[]{x, i, 0});
            r = Math.max(r, x);
        }

        int ansL = pq.peek()[0]; // 第一个合法区间的左端点
        int ansR = r; // 第一个合法区间的右端点
        while (pq.peek()[2] + 1 < nums.get(pq.peek()[1]).size()) { // 堆顶列表有下一个元素
            int[] top = pq.poll();
            top[0] = nums.get(top[1]).get(++top[2]); // 堆顶列表的下一个元素
            r = Math.max(r, top[0]); // 更新合法区间的右端点
            pq.offer(top); // 入堆（复用 int[]，提高效率）
            int l = pq.peek()[0]; // 当前合法区间的左端点
            if (r - l < ansR - ansL) {
                ansL = l;
                ansR = r;
            }
        }
        return new int[]{ansL, ansR};
    }
}

/*
class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {
        int sumLen = 0;
        for (List<Integer> list : nums) {
            sumLen += list.size();
        }

        int[][] pairs = new int[sumLen][2];
        int pi = 0;
        for (int i = 0; i < nums.size(); i++) {
            for (int x : nums.get(i)) {
                pairs[pi][0] = x;
                pairs[pi++][1] = i;
            }
        }
        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);

        int ansL = pairs[0][0];
        int ansR = pairs[sumLen - 1][0];
        int empty = nums.size();
        int[] cnt = new int[empty];
        int left = 0;
        for (int[] p : pairs) {
            int r = p[0];
            int i = p[1];
            if (cnt[i] == 0) { // 包含 nums[i] 的数字
                empty--;
            }
            cnt[i]++;
            while (empty == 0) { // 每个列表都至少包含一个数
                int l = pairs[left][0];
                if (r - l < ansR - ansL) {
                    ansL = l;
                    ansR = r;
                }
                i = pairs[left][1];
                cnt[i]--;
                if (cnt[i] == 0) { // 不包含 nums[i] 的数字
                    empty++;
                }
                left++;
            }
        }
        return new int[]{ansL, ansR};
    }
}
*/