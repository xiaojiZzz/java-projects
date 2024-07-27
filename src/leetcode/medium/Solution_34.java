package leetcode.medium;


/**
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 * 示例 1：
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 */
public class Solution_34 {
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        int begin = 0;
        int end = nums.length - 1;
        while (begin <= end) {
            int middle = begin + (end - begin) / 2;
            if (nums[middle] == target) {
                for (int i = middle - 1; i >= 0; i--) {
                    if (nums[i] != target) {
                        begin = i + 1;
                        break;
                    }
                }
                for (int i = middle + 1; i < nums.length; i++) {
                    if (nums[i] != target) {
                        end = i - 1;
                        break;
                    }
                }
                return new int[]{begin, end};
            } else if (nums[middle] > target) {
                end = middle - 1;
            } else {
                begin = middle + 1;
            }
        }
        return new int[]{-1, -1};
    }
}

/*
class Solution {
    public int[] searchRange(int[] nums, int target) {
        if(nums.length == 0) return new int[]{-1,-1};

        int l = 0, r = nums.length - 1; //二分范围
        while( l < r)			        //查找元素的开始位置
        {
            int mid = (l + r )/2;
            if(nums[mid] >= target) r = mid;
            else l = mid + 1;
        }
        if( nums[r] != target) return new int[]{-1,-1}; //查找失败
        int L = r;
        l = 0; r = nums.length - 1;     //二分范围
        while( l < r)			        //查找元素的结束位置
        {
            int mid = (l + r + 1)/2;
            if(nums[mid] <= target ) l = mid;
            else r = mid - 1;
        }
        return new int[]{L,r};
    }
}
*/
