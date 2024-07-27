package leetcode.medium;


/**
 * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
 * 你必须设计并实现线性时间复杂度的算法且使用常数级空间来解决此问题。
 * 示例 1：
 * 输入：nums = [2,2,3,2]
 * 输出：3
 * 示例 2：
 * 输入：nums = [0,1,0,1,0,1,99]
 * 输出：99
 */
public class Solution_137 {
    public int singleNumber(int[] nums) {
        int[] tmp = new int[32];
        for (int i = 0; i < nums.length; i++) {
            int[] ints = convertToBinaryArray(nums[i]);
            for (int j = 0; j < 32; j++) {
                tmp[j] += ints[j];
            }
        }
        for (int i = 0; i < 32; i++) {
            tmp[i] %= 3;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 32; i++) {
            stringBuffer.append(tmp[i]);
        }
        String s = stringBuffer.toString();
        long result = Long.parseLong(s, 2);
        return (int) result;
    }

    // 将整数转换成二进制数组的方法
    private static int[] convertToBinaryArray(int number) {
        int[] binaryArray = new int[32]; // 假设使用32位表示整数

        for (int i = 31; i >= 0; i--) {
            binaryArray[i] = (number & 1); // 获取最低位的值
            number = number >> 1; // 右移一位
        }

        return binaryArray;
    }
}

/*
class Solution {
    public int singleNumber(int[] nums) {
        int[] tmp = new int[32];
        for (int i = 0; i < nums.length; i++) {
            // 使用位运算获取每一位的值
            for (int j = 0; j < 32; j++) {
                tmp[j] += (nums[i] >> j) & 1;
            }
        }

        int result = 0;
        for (int i = 31; i >= 0; i--) {
            tmp[i] %= 3;
            // 还原出现一次的元素的二进制表示
            result = result * 2 + tmp[i];
        }

        return result;
    }
}
*/

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

/*
class Solution {
    public int singleNumber(int[] nums) {
        int ones = 0, twos = 0;
        for(int num : nums){
            ones = ones ^ num & ~twos;
            twos = twos ^ num & ~ones;
        }
        return ones;
    }
}
*/
