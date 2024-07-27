package leetcode.lcr;

import java.util.Arrays;


/**
 * 同leetcode 213.打家劫舍 II
 */
public class Solution_090 {
    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        return Math.max(myRob(Arrays.copyOfRange(nums, 0, nums.length - 1)),
                myRob(Arrays.copyOfRange(nums, 1, nums.length)));
    }

    private int myRob(int[] nums) {
        int pre = 0, cur = 0, tmp;
        for (int num : nums) {
            tmp = cur;
            cur = Math.max(pre + num, cur);
            pre = tmp;
        }
        return cur;
    }
}

/*
class Solution {
    public int rob(int[] nums) {
        if(nums.length==1){
            return nums[0];
        }
        // 由于偷了最后一个，第一个就不能投，偷了第一个，最后一个就不能偷，那么使用两个dp,一个dp从第一个开始，一个dp从第二个开始，最后判断哪个最大。
        int [] dp0 = new int[nums.length];
        int [] dp1 = new int[nums.length];
        dp0[0] = nums[0];
        dp0[1] = nums[0];
        dp1[1] = nums[1];
        for(int i =2; i<nums.length; i++){
            dp0[i] = Math.max(dp0[i-2]+nums[i],dp0[i-1]);
            dp1[i] = Math.max(dp1[i-2]+nums[i],dp1[i-1]);
        }
        return Math.max(dp0[nums.length-2],dp1[nums.length-1]);
    }
}
*/
