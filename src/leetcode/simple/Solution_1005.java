package leetcode.simple;

import java.util.Arrays;

/**
 * 给你一个整数数组 nums 和一个整数 k ，按以下方法修改该数组：
 * 选择某个下标 i 并将 nums[i] 替换为 -nums[i] 。
 * 重复这个过程恰好 k 次。可以多次选择同一个下标 i 。
 * 以这种方式修改数组后，返回数组 可能的最大和 。
 * 示例 1：
 * 输入：nums = [4,2,3], k = 1
 * 输出：5
 * 解释：选择下标 1 ，nums 变为 [4,-2,3] 。
 * 示例 2：
 * 输入：nums = [3,-1,0,2], k = 3
 * 输出：6
 * 解释：选择下标 (1, 2, 2) ，nums 变为 [3,1,0,2] 。
 * 示例 3：
 * 输入：nums = [2,-3,-1,5,-4], k = 2
 * 输出：13
 * 解释：选择下标 (1, 4) ，nums 变为 [2,3,-1,5,4] 。
 * 提示：
 * 1 <= nums.length <= 104
 * -100 <= nums[i] <= 100
 * 1 <= k <= 104
 */
public class Solution_1005 {
    public int largestSumAfterKNegations(int[] nums, int k) {
        Arrays.sort(nums);
        int sum = 0;
        int n = nums.length;
        int minusCnt = 0, zeroCnt = 0;
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num < 0) {
                minusCnt++;
                min = Math.min(min, -num);
            } else if (num > 0) {
                sum += num;
                min = Math.min(min, num);
            } else {
                zeroCnt++;
                min = 0;
            }
        }
        if (k <= minusCnt) {
            for (int i = 0; i < minusCnt; i++) {
                if (i < k) {
                    sum -= nums[i];
                } else {
                    sum += nums[i];
                }
            }
        } else {
            k -= minusCnt;
            for (int i = 0; i < minusCnt; i++) {
                sum -= nums[i];
            }
            if (zeroCnt > 0 || k % 2 == 0) {
                return sum;
            } else {
                sum -= 2 * min;
            }
        }
        return sum;
    }
}

/*
class Solution {
    public int largestSumAfterKNegations(int[] nums, int k) {
        // 排序，把可能有的负数排到前面
        Arrays.sort(nums);
        int sum = 0, min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            // 贪心：如果是负数，而k还有盈余，就把负数反过来
            if (nums[i] < 0 && k > 0) {
                nums[i] = -1 * nums[i];
                k--;
            }
            min = Math.min(min, nums[i]);
            sum += nums[i];
        }
        // 如果k没剩，那说明能转的负数都转正了，已经是最大和，返回sum
        // 如果k有剩，说明负数已经全部转正，所以如果k还剩偶数个就自己抵消掉，不用删减，如果k还剩奇数个就减掉2倍最小正数。
        return sum - (k % 2 == 0 ? 0 : 2 * min);
    }
}
*/

/*
class Solution {
    public int largestSumAfterKNegations(int[] nums, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int num : nums) {
            priorityQueue.add(num);
        }
        for (int i = 0; i < k; i++) {
            priorityQueue.add(-priorityQueue.poll());
        }
        int res = 0;
        while (!priorityQueue.isEmpty()) {
            res += priorityQueue.poll();
        }
        return res;
    }
}
*/

/*
class Solution {
    public int largestSumAfterKNegations(int[] nums, int k) {
        // cnts 用于记录数组元素值和出现次数的映射，可以看成一个哈希表: key 为值，value 为值个数(nums中不同元素的值可能相同)
        int[] cnts = new int[210];
        // 初始化 cnts 数组。因为数组元素值范围是[-100,100]，所以增加 100 偏移量, 即值为 i 的元素个数为 cnts[i + 100]
        for (int i : nums)
            cnts[i + 100]++;
        // 扫描[-100,0)区间，更新 cnts 数组，并把 k 减 1
        for (int i = -100; i < 0 && k > 0; i++) {
            // cnts[i + 100] != 0 表示 nums 数组中存在值 i , k > 0 表示还有剩余次数
            while (cnts[i + 100] != 0 && k-- > 0) {
                // 把 nums 数组中值为 i 的元素的值改为 -i , 所以需要同时更新值为 -i 和 i 的元素数量
                cnts[i + 100]--;
                cnts[-i + 100]++;
            }
        }
        // cnts[0 + 100] == 0 表示 nums 数组不存在 0 值
        if (cnts[100] == 0 && k > 0 && k % 2 != 0) {
            int val = 1;
            // 找到绝对值最小的值
            while (cnts[val + 100] == 0)
                val++;
            // 更新值的符号，并更新 cnts 中的计数
            cnts[val + 100]--;
            cnts[-val + 100]++;
        }
        int ans = 0;
        // 遍历值区间[-100,100] , 总和 ans = 值 * 值个数
        for (int i = -100; i <= 100; i++)
            ans += i * cnts[i + 100];
        return ans;
    }
}
*/
