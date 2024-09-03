package leetcode.medium;

/**
 * 一个小组的最大实力值
 * 给你一个下标从 0 开始的整数数组 nums ，它表示一个班级中所有学生在一次考试中的成绩。
 * 老师想选出一部分同学组成一个 非空 小组，且这个小组的 实力值 最大，如果这个小组里的学生下标为 i0, i1, i2, ... , ik ，
 * 那么这个小组的实力值定义为 nums[i0] * nums[i1] * nums[i2] * ... * nums[ik] 。
 * 请你返回老师创建的小组能得到的最大实力值为多少。
 * 示例 1：
 * 输入：nums = [3,-1,-5,2,5,-9]
 * 输出：1350
 * 解释：一种构成最大实力值小组的方案是选择下标为 [0,2,3,4,5] 的学生。
 * 实力值为 3 * (-5) * 2 * 5 * (-9) = 1350 ，这是可以得到的最大实力值。
 * 示例 2：
 * 输入：nums = [-4,-5,-4]
 * 输出：20
 * 解释：选择下标为 [0, 1] 的学生。得到的实力值为 20 。我们没法得到更大的实力值。
 * 提示：
 * 1 <= nums.length <= 13
 * -9 <= nums[i] <= 9
 */
public class Solution_2708 {
    public long maxStrength(int[] nums) {
        int n = nums.length;
        long[] max = new long[n];
        long[] min = new long[n];
        max[0] = nums[0];
        min[0] = nums[0];
        for (int i = 1; i < n; i++) {
            max[i] = Math.max(Math.max(max[i - 1], nums[i]), Math.max(max[i - 1] * nums[i], min[i - 1] * nums[i]));
            min[i] = Math.min(Math.min(min[i - 1], nums[i]), Math.min(min[i - 1] * nums[i], max[i - 1] * nums[i]));
        }
        return max[n - 1];
    }
}

/*
class Solution {
    public long maxStrength(int[] nums) {
        long mn = nums[0];
        long mx = mn;
        for (int i = 1; i < nums.length; i++) {
            long x = nums[i];
            long tmp = mn;
            mn = Math.min(Math.min(mn, x), Math.min(mn * x, mx * x));
            mx = Math.max(Math.max(mx, x), Math.max(tmp * x, mx * x));
        }
        return mx;
    }
}
*/

/*
class Solution {
    // 二进制枚举
    public long maxStrength(int[] nums) {
        long ans = Long.MIN_VALUE;
        int n = nums.length;
        for (int i = 1; i < (1 << n); i++) {
            long num = 1;
            for (int j = 0; j < n; j++) {
                if (((i >> j) & 1) == 1) {
                    num *= nums[j];
                }
            }
            ans = Math.max(ans, num);
        }
        return ans;
    }
}*/

/*
class Solution {
    public long maxStrength(int[] nums) {
        int n = nums.length;

        // 处理特殊情况：数组只有一个元素
        if (n == 1) {
            return nums[0];
        }

        long product = 1;  // 用于存储非零元素的乘积
        int zeroCount = 0;  // 统计0的个数
        int maxNegative = -10;  // 记录最大的负数
        boolean hasNonZero = false;  // 标记是否存在非零元素

        for (int num : nums) {
            if (num == 0) {
                zeroCount++;
            } else {
                if (num < 0) {
                    maxNegative = Math.max(num, maxNegative);
                }
                product *= num;
                hasNonZero = true;
            }
        }

        // 处理特殊情况：只有一个非零元素
        if (n - zeroCount == 1) {
            return product > 0 ? product : 0;
        }

        // 处理特殊情况：所有元素都是0
        if (!hasNonZero) {
            return 0;
        }

        // 如果乘积为负，去掉最大的负数使乘积变为正数
        if (product < 0) {
            product /= maxNegative;
        }

        return product;
    }
}
*/

/*
class Solution {
    public long maxStrength(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        if (nums[1] == 0 && nums[n - 1] == 0) {
            return 0;
        }
        long ans = 1;
        int i = 0;
        while (i < n) {
            if (nums[i] < 0 && i + 1 < n && nums[i + 1] < 0) {
                ans *= nums[i] * nums[i + 1];
                i += 2;
            } else if (nums[i] <= 0) {
                i += 1;
            } else {
                ans *= nums[i];
                i += 1;
            }
        }
        return ans;
    }
}
*/