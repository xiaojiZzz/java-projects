package leetcode.difficulty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 下一个更大元素 IV
 * 给你一个下标从 0 开始的非负整数数组 nums 。对于 nums 中每一个整数，你必须找到对应元素的 第二大 整数。
 * 如果 nums[j] 满足以下条件，那么我们称它为 nums[i] 的 第二大 整数：
 * j > i
 * nums[j] > nums[i]
 * 恰好存在 一个 k 满足 i < k < j 且 nums[k] > nums[i] 。
 * 如果不存在 nums[j] ，那么第二大整数为 -1 。
 * 比方说，数组 [1, 2, 4, 3] 中，1 的第二大整数是 4 ，2 的第二大整数是 3 ，3 和 4 的第二大整数是 -1 。
 * 请你返回一个整数数组 answer ，其中 answer[i]是 nums[i] 的第二大整数。
 * 示例 1：
 * 输入：nums = [2,4,0,9,6]
 * 输出：[9,6,6,-1,-1]
 * 解释：
 * 下标为 0 处：2 的右边，4 是大于 2 的第一个整数，9 是第二个大于 2 的整数。
 * 下标为 1 处：4 的右边，9 是大于 4 的第一个整数，6 是第二个大于 4 的整数。
 * 下标为 2 处：0 的右边，9 是大于 0 的第一个整数，6 是第二个大于 0 的整数。
 * 下标为 3 处：右边不存在大于 9 的整数，所以第二大整数为 -1 。
 * 下标为 4 处：右边不存在大于 6 的整数，所以第二大整数为 -1 。
 * 所以我们返回 [9,6,6,-1,-1] 。
 * 示例 2：
 * 输入：nums = [3,3]
 * 输出：[-1,-1]
 * 解释：
 * 由于每个数右边都没有更大的数，所以我们返回 [-1,-1] 。
 * 提示：
 * 1 <= nums.length <= 105
 * 0 <= nums[i] <= 109
 */
public class Solution_2454 {
    public int[] secondGreaterElement(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        // s 是递减的
        List<Integer> s = new ArrayList<>();
        // t 也是递减的
        List<Integer> t = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            while (!t.isEmpty() && nums[t.get(t.size() - 1)] < x) {
                ans[t.get(t.size() - 1)] = x; // t 栈顶的下下个更大元素是 x
                t.remove(t.size() - 1);
            }
            int j = s.size();
            while (j > 0 && nums[s.get(j - 1)] < x) {
                j--; // s 栈顶的下一个更大元素是 x
            }
            List<Integer> popped = s.subList(j, s.size());
            t.addAll(popped); // 把从 s 弹出的这一整段元素加到 t
            popped.clear(); // 弹出一整段元素，poped 其实是 s 的视图，对 popped 操作，就是对 s 操作
            s.add(i); // 当前元素（的下标）加到 s 栈顶
        }
        return ans;
    }
}

/*
class Solution {
    public int[] secondGreaterElement(int[] nums) {
        int[] res = new int[nums.length];
        Arrays.fill(res, -1);
        Deque<Integer> stack = new ArrayDeque<Integer>();
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);
        for (int i = 0; i < nums.length; ++i) {
            while (!pq.isEmpty() && pq.peek()[0] < nums[i]) {
                res[pq.poll()[1]] = nums[i];
            }
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                pq.offer(new int[]{nums[stack.peek()], stack.peek()});
                stack.pop();
            }
            stack.push(i);
        }
        return res;
    }
}
*/

/*
class Solution {
    private static final int MX = 100000;
    private static final int[] s = new int[MX];
    private static final int[] t = new int[MX];

    public int[] secondGreaterElement(int[] nums) {
        int n = nums.length, lenS = 0, lenT = 0;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            while (lenT > 0 && nums[t[lenT - 1]] < x) {
                ans[t[--lenT]] = x; // t 栈顶的下下个更大元素是 x
            }
            int tmp = lenS;
            while (lenS > 0 && nums[s[lenS - 1]] < x) {
                lenS--; // s 栈顶的下一个更大元素是 x
            }
            System.arraycopy(s, lenS, t, lenT, tmp - lenS); // 把从 s 弹出的这一整段元素加到 t
            lenT += tmp - lenS;
            s[lenS++] = i; // 当前元素（的下标）加到 s 栈顶
        }
        return ans;
    }
}
*/