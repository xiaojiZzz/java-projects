package leetcode.simple;


/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 * 示例 1:
 * 输入: nums = [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 */
public class Solution_283 {
    public void moveZeroes(int[] nums) {
        if (nums.length == 1) {
            return;
        }
        int s = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[s++] = nums[i];
            }
        }
        for (int i = s; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}

/*
class Solution {
    public void moveZeroes(int[] nums) {
        if (nums == null) {
            return;
        }
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            //当前元素!=0，就把其交换到左边，等于0的交换到右边
            if (nums[i] != 0) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j++] = tmp;
            }
        }
    }
}
*/
