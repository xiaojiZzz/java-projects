package data_structure_and_algorithm;

// 二分模版，实现 o(logn) 的时间复杂度
// 用于在升序数组中查找 target 元素的索引
// 该模版可以用于其他二分的情况
public class BinaryTemplate {
    // 闭区间 [0, n - 1]
    public int binaryTemplate1(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    // 左闭右开 [0, n)
    public int binaryTemplate2(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    //开区间 (-1, n)
    public int binaryTemplate3(int[] nums, int target) {
        int left = -1, right = nums.length;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return right;
    }
}
