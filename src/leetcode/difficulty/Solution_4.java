package leetcode.difficulty;


/**
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 * 示例：
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 */
public class Solution_4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] ints = new int[nums1.length + nums2.length];
        int n1 = 0, n2 = 0;
        int idx = 0;
        while (idx < ints.length && n1 < nums1.length && n2 < nums2.length) {
            if (nums1[n1] < nums2[n2]) {
                ints[idx++] = nums1[n1++];
            } else {
                ints[idx++] = nums2[n2++];
            }
        }
        while (n1 < nums1.length) {
            ints[idx++] = nums1[n1++];
        }
        while (n2 < nums2.length) {
            ints[idx++] = nums2[n2++];
        }
        if (ints.length % 2 == 0) {
            return (ints[ints.length / 2 - 1] + ints[ints.length / 2]) / 2.0;
        } else {
            return ints[ints.length / 2];
        }
    }
}

/*
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length, length2 = nums2.length;
        int totalLength = length1 + length2;
        if (totalLength % 2 == 1) {
            int midIndex = totalLength / 2;
            return getKthElement(nums1, nums2, midIndex + 1);
        } else {
            int midIndex1 = totalLength / 2 - 1, midIndex2 = totalLength / 2;
            return (getKthElement(nums1, nums2, midIndex1 + 1) + getKthElement(nums1, nums2, midIndex2 + 1)) / 2.0;
        }
    }

    public int getKthElement(int[] nums1, int[] nums2, int k) {
        */
        /* 主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
         * 这里的 "/" 表示整除
         * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
         * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
         * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
         * 这样 pivot 本身最大也只能是第 k-1 小的元素
         * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
         * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
         * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
         *//*

        int length1 = nums1.length, length2 = nums2.length;
        int index1 = 0, index2 = 0;

        while (true) {
            // 边界情况
            if (index1 == length1) {
                return nums2[index2 + k - 1];
            }
            if (index2 == length2) {
                return nums1[index1 + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }

            // 正常情况
            int half = k / 2;
            int newIndex1 = Math.min(index1 + half, length1) - 1;
            int newIndex2 = Math.min(index2 + half, length2) - 1;
            int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
            if (pivot1 <= pivot2) {
                k -= (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            } else {
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }
    }
}
*/

/*
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        if (n1 > n2)
            return findMedianSortedArrays(nums2, nums1);
        // n1 <= n2
        int cnt = (n1 + n2 + 1) / 2;
        int left = 0, right = n1;
        // 这里的left不是下标，而是取多少个元素
        while (left < right) {
            int mid1 = (left + right) / 2;  // nums1取mid1个元素
            int mid2 = cnt - mid1 - 1;        // nums2取cnt-mid1个元素，下标为cnt-mid1-1
            if (nums1[mid1] >= nums2[mid2]) {
                right = mid1;
            } else {
                left = mid1 + 1;
            }
        }
        int cnt1 = left, cnt2 = cnt - left;  // nums1取left个元素（cnt1）, nums2取cnt-left个元素（cnt2）
        int leftVal = Math.max(cnt1 == 0 ? Integer.MIN_VALUE : nums1[cnt1 - 1], cnt2 == 0 ? Integer.MIN_VALUE : nums2[cnt2 - 1]);
        if ((n1 + n2) % 2 == 1) {
            return leftVal;
        }
        int rightVal = Math.min(cnt1 == n1 ? Integer.MAX_VALUE : nums1[cnt1], cnt2 == n2 ? Integer.MAX_VALUE : nums2[cnt2]);
        return (leftVal + rightVal) / 2.0;
    }
}
*/
