package leetcode.simple;

/**
 * 按奇偶排序数组 II
 * 给定一个非负整数数组 nums，  nums 中一半整数是 奇数 ，一半整数是 偶数 。
 * 对数组进行排序，以便当 nums[i] 为奇数时，i 也是 奇数 ；当 nums[i] 为偶数时， i 也是 偶数 。
 * 你可以返回 任何满足上述条件的数组作为答案 。
 * 示例 1：
 * 输入：nums = [4,2,5,7]
 * 输出：[4,5,2,7]
 * 解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
 * 示例 2：
 * 输入：nums = [2,3]
 * 输出：[2,3]
 * 提示：
 * 2 <= nums.length <= 2 * 104
 * nums.length 是偶数
 * nums 中一半是偶数
 * 0 <= nums[i] <= 1000
 * 进阶：可以不使用额外空间解决问题吗？
 */
public class Solution_922 {
    public int[] sortArrayByParityII(int[] nums) {
        int evenIdx = nums.length - 2, oddIdx = nums.length - 1;
        int idx = 0;
        while (idx <= oddIdx) {
            while (((idx & 1) ^ (nums[idx] & 1)) != 0) {
                if ((nums[idx] & 1) == 0) {
                    int tmp = nums[evenIdx];
                    nums[evenIdx] = nums[idx];
                    nums[idx] = tmp;
                    evenIdx -= 2;
                } else {
                    int tmp = nums[oddIdx];
                    nums[oddIdx] = nums[idx];
                    nums[idx] = tmp;
                    oddIdx -= 2;
                }
            }
            idx++;
        }
        return nums;
    }
}

/*
class Solution {
    public int[] sortArrayByParityII(int[] nums) {
        int i = 0;
        int j = 1;
        while (i < nums.length) {
            if (nums[i] % 2 == 0) { // 寻找偶数下标中最左边的奇数
                i += 2;
            } else if (nums[j] % 2 == 1) { // 寻找奇数下标中最左边的偶数
                j += 2;
            } else {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
                i += 2;
                j += 2;
            }
        }
        return nums;
    }
}
*/