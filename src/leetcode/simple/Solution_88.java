package leetcode.simple;


/**
 * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
 * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
 * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，
 * 其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
 * 示例：
 * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * 输出：[1,2,2,3,5,6]
 */
public class Solution_88 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p = 0;
        int q = 0;
        int r = 0;
        int[] num = new int[m + n];
        int res = -1;
        while (p < m && q < n) {
            if (nums1[p] < nums2[q]) {
                res = nums1[p++];
            } else {
                res = nums2[q++];
            }
            num[r++] = res;
        }
        if (p == m) {
            while (q < n) {
                num[r++] = nums2[q++];
            }
        } else {
            while (p < m) {
                num[r++] = nums1[p++];
            }
        }
        for (int i = 0; i < m + n; i++) {
            nums1[i] = num[i];
        }
    }
}

/*
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int r = m + n - 1;
        int p = m - 1;
        int q = n - 1;
        for (int i = m + n - 1; i >= 0; i--) {
            if (p < 0) {
                nums1[r--] = nums2[q--];
            } else if (q < 0) {
                nums1[r--] = nums1[p--];
            } else if (nums1[p] > nums2[q]) {
                nums1[r--] = nums1[p--];
            } else {
                nums1[r--] = nums2[q--];
            }
        }
    }
}
*/
