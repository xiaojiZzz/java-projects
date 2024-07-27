package leetcode.medium;

/**
 * 颜色分类
 * 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * 必须在不使用库内置的 sort 函数的情况下解决这个问题。
 * 示例 1：
 * 输入：nums = [2,0,2,1,1,0]
 * 输出：[0,0,1,1,2,2]
 * 示例 2：
 * 输入：nums = [2,0,1]
 * 输出：[0,1,2]
 */
public class Solution_75 {
    public void sortColors(int[] nums) {
        int cnt1 = 0, cnt2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                cnt1++;
            } else if (nums[i] == 1) {
                cnt2++;
            }
        }
        for (int i = 0; i < cnt1; i++) {
            nums[i] = 0;
        }
        for (int i = cnt1; i < cnt1 + cnt2; i++) {
            nums[i] = 1;
        }
        for (int i = cnt1 + cnt2; i < nums.length; i++) {
            nums[i] = 2;
        }
    }
}

/*
class Solution {
    public void sortColors(int[] nums) {
        int n = nums.length;
        int p0 = 0, p2 = n - 1;
        for (int i = 0; i <= p2; ++i) {
            while (i <= p2 && nums[i] == 2) {
                int temp = nums[i];
                nums[i] = nums[p2];
                nums[p2] = temp;
                --p2;
            }
            if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[p0];
                nums[p0] = temp;
                ++p0;
            }
        }
    }
}
*/

/*
class Solution {
    public void sortColors(int[] nums) {
        int n = nums.length;
        int p0 = 0, p1 = 0;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == 1) {
                int temp = nums[i];
                nums[i] = nums[p1];
                nums[p1] = temp;
                ++p1;
            } else if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[p0];
                nums[p0] = temp;
                if (p0 < p1) {
                    temp = nums[i];
                    nums[i] = nums[p1];
                    nums[p1] = temp;
                }
                ++p0;
                ++p1;
            }
        }
    }
}
*/

/*
class Solution {
    public void sortColors(int[] nums) {
        int num0 = 0, num1 = 0, num2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                nums[num2++] = 2;
                nums[num1++] = 1;
                nums[num0++] = 0;
            } else if (nums[i] == 1) {
                nums[num2++] = 2;
                nums[num1++] = 1;
            } else {
                num2++;
            }
        }
    }
}
*/

/*
class Solution {
    public void sortColors(int[] nums) {
        int l = 0;
        int m = l;
        int r = nums.length - 1;
        while (m <= r) {
            if (nums[m] == 0) {
                nums[m++] = nums[l];
                nums[l++] = 0;
            } else if (nums[m] == 1) {
                ++m;
            } else {
                nums[m] = nums[r];
                nums[r--] = 2;
            }
        }
    }
}
*/
