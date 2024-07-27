package leetcode.simple;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 找到两个数组中的公共元素
 * 给你两个下标从 0 开始的整数数组 nums1 和 nums2 ，它们分别含有 n 和 m 个元素。
 * 请你计算以下两个数值：
 * 统计 0 <= i < n 中的下标 i ，满足 nums1[i] 在 nums2 中 至少 出现了一次。
 * 统计 0 <= i < m 中的下标 i ，满足 nums2[i] 在 nums1 中 至少 出现了一次。
 * 请你返回一个长度为 2 的整数数组 answer ，按顺序 分别为以上两个数值。
 * 示例 1：
 * 输入：nums1 = [4,3,2,3,1], nums2 = [2,2,5,2,3,6]
 * 输出：[3,4]
 * 解释：分别计算两个数值：
 * - nums1 中下标为 1 ，2 和 3 的元素在 nums2 中至少出现了一次，所以第一个值为 3 。
 * - nums2 中下标为 0 ，1 ，3 和 4 的元素在 nums1 中至少出现了一次，所以第二个值为 4 。
 * 示例 2：
 * 输入：nums1 = [3,4,2,3], nums2 = [1,5]
 * 输出：[0,0]
 * 解释：两个数组中没有公共元素，所以两个值都为 0 。
 * 提示：
 * n == nums1.length
 * m == nums2.length
 * 1 <= n, m <= 100
 * 1 <= nums1[i], nums2[i] <= 100
 */
public class Solution_2956 {
    public int[] findIntersectionValues(int[] nums1, int[] nums2) {
        Set<Integer> set1 = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
        Set<Integer> set2 = Arrays.stream(nums2).boxed().collect(Collectors.toSet());
        int cnt1 = 0, cnt2 = 0;
        for (int num : nums1) {
            if (set2.contains(num)) {
                cnt1++;
            }
        }
        for (int num : nums2) {
            if (set1.contains(num)) {
                cnt2++;
            }
        }
        return new int[]{cnt1, cnt2};
    }
}

/*
class Solution {
    public int[] findIntersectionValues(int[] nums1, int[] nums2) {
        int[] nums = new int[101];
        for (int i : nums1) {
            nums[i]++;
        }
        int n1 = 0, n2 = 0;
        for (int i : nums2) {
            if (nums[i] != 0) {
                n2++;
                n1 += (nums[i] == -1 ? 0 : nums[i]);
                nums[i] = -1;
            }
        }
        return new int[]{n1, n2};
    }
}
*/
