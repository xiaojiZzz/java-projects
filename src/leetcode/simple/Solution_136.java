package leetcode.simple;


/**
 * 给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。
 * 示例 2 ：
 * 输入：nums = [4,1,2,1,2]
 * 输出：4
 */
public class Solution_136 {
    //异或
    public int singleNumber(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int res = nums[0];
        for (int i = 1; i < length; i++) {
            res ^= nums[i];
        }
        return res;
    }
}

/*
class Solution {
    public int singleNumber(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        int res = -1;
        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        for (Map.Entry<Integer, Integer> entry : entries) {
            if (entry.getValue() == 1) {
                res = entry.getKey();
                break;
            }
        }
        return res;
    }
}
*/
