package leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 统计坏数对的数目
 * 给你一个下标从 0 开始的整数数组 nums 。如果 i < j 且 j - i != nums[j] - nums[i] ，那么我们称 (i, j) 是一个 坏数对 。
 * 请你返回 nums 中 坏数对 的总数目。
 * 示例 1：
 * 输入：nums = [4,1,3,3]
 * 输出：5
 * 解释：数对 (0, 1) 是坏数对，因为 1 - 0 != 1 - 4 。
 * 数对 (0, 2) 是坏数对，因为 2 - 0 != 3 - 4, 2 != -1 。
 * 数对 (0, 3) 是坏数对，因为 3 - 0 != 3 - 4, 3 != -1 。
 * 数对 (1, 2) 是坏数对，因为 2 - 1 != 3 - 1, 1 != 2 。
 * 数对 (2, 3) 是坏数对，因为 3 - 2 != 3 - 3, 1 != 0 。
 * 总共有 5 个坏数对，所以我们返回 5 。
 * 示例 2：
 * 输入：nums = [1,2,3,4,5]
 * 输出：0
 * 解释：没有坏数对。
 * 提示：
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 109
 */
public class Solution_2364 {
    public long countBadPairs(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        long ans = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int num = i - nums[i];
            int cnt = map.getOrDefault(num, 0);
            ans += cnt;
            map.put(num, cnt + 1);
        }
        return (long) n * (n - 1) / 2 - ans;
    }
}
