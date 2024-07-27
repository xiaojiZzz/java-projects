package leetcode.lcr;

import java.util.HashMap;


/**
 * 给定一个已按照 升序排列  的整数数组 numbers ，请你从数组中找出两个数满足相加之和等于目标数 target 。
 * 函数应该以长度为 2 的整数数组的形式返回这两个数的下标值。
 * numbers 的下标 从 0 开始计数 ，所以答案数组应当满足 0 <= answer[0] < answer[1] < numbers.length 。
 * 假设数组中存在且只存在一对符合条件的数字，同时一个数字不能使用两次。
 * 示例 1：
 * 输入：numbers = [1,2,4,6,10], target = 8
 * 输出：[1,3]
 * 解释：2 与 6 之和等于目标数 8 。因此 index1 = 1, index2 = 3 。
 */
public class Solution_006 {
    public int[] twoSum(int[] numbers, int target) {
        HashMap<Integer, Integer> mp = new HashMap<>();
        int t = 0;
        for (int i = 0; i < numbers.length; i++) {
            t = target - numbers[i];
            if (mp.containsKey(t)) {
                return new int[]{mp.get(t), i};
            } else {
                mp.put(numbers[i], i);
            }
        }
        return null;
    }
}

/*
class Solution {
    //双指针
    public int[] twoSum(int[] numbers, int target) {
        int low = 0, high = numbers.length - 1;
        while (low < high) {
            if (numbers[low] + numbers[high] == target) {
                return new int[] {low + 1, high + 1};
            } else if (numbers[low] + numbers[high] > target) {
                high--;
            } else {
                low++;
            }
        }
        return null;
    }
}
*/
