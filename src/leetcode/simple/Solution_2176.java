package leetcode.simple;

/**
 * 给你一个下标从 0 开始长度为 n 的整数数组 nums 和一个整数 k ，
 * 请你返回满足 0 <= i < j < n ，nums[i] == nums[j] 且 (i * j) 能被 k 整除的数对 (i, j) 的 数目 。
 * 示例 1：
 * 输入：nums = [3,1,2,2,2,1,3], k = 2
 * 输出：4
 * 解释：
 * 总共有 4 对数符合所有要求：
 * - nums[0] == nums[6] 且 0 * 6 == 0 ，能被 2 整除。
 * - nums[2] == nums[3] 且 2 * 3 == 6 ，能被 2 整除。
 * - nums[2] == nums[4] 且 2 * 4 == 8 ，能被 2 整除。
 * - nums[3] == nums[4] 且 3 * 4 == 12 ，能被 2 整除。
 * 示例 2：
 * 输入：nums = [1,2,3,4], k = 1
 * 输出：0
 * 解释：由于数组中没有重复数值，所以没有数对 (i,j) 符合所有要求。
 * 提示：
 * 1 <= nums.length <= 100
 * 1 <= nums[i], k <= 100
 */
public class Solution_2176 {
    public int countPairs(int[] nums, int k) {
        int n = nums.length, ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (i * j % k == 0 && nums[i] == nums[j]) {
                    ans++;
                }
            }
        }
        return ans;
    }
}

/*
class Solution {
    private static final int MX = 101;
    private static final List<Integer>[] divisors = new ArrayList[MX];

    static {
        Arrays.setAll(divisors, i -> new ArrayList<>());
        // 预处理每个数的因子
        for (int i = 1; i < MX; i++) {
            for (int j = i; j < MX; j += i) {
                divisors[j].add(i);
            }
        }
    }

    public int countPairs(int[] nums, int k) {
        int ans = 0;
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int j = 0; j < nums.length; j++) { // 枚举 j，计算左边有多少个符合要求的 i
            int x = nums[j];
            if (j > 0 && x == nums[0]) {
                ans++; // 单独统计 i=0 的情况
            }
            int k2 = k / gcd(k, j); // i 必须是 k2 的倍数
            // 用位运算把二元组 (x, k2) 合并成一个整数
            ans += cnt.getOrDefault(k2 << 10 | x, 0);
            for (int d : divisors[j]) { // j 是 d 的倍数
                cnt.merge(d << 10 | x, 1, Integer::sum); // cnt[d<<10|x]++
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        while (a != 0) {
            int tmp = b % a;
            b = a;
            a = tmp;
        }
        return b;
    }
}
*/