package leetcode.simple;


/**
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 * 最高位数字存放在数组的首位，数组中每个元素只存储单个数字。
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * 示例 1：
 * 输入：digits = [1,2,3]
 * 输出：[1,2,4]
 * 解释：输入数组表示数字 123。
 * 示例 2：
 * 输入：digits = [4,3,2,1]
 * 输出：[4,3,2,2]
 * 解释：输入数组表示数字 4321。
 */
//public class Solution_66 {
//    public int[] plusOne(int[] digits) {
//        if (digits.length == 1 && digits[0] == 0) {
//            return new int[]{1};
//        }
//        int length = digits.length;
//        int flag = 0;
//        for (int i = length - 1; i >= 0; i--) {
//            if (digits[i] == 9) {
//                if (i == length - 1) {
//                    digits[i] = 0;
//                    flag = 1;
//                }
//                if (flag == 1 && i != length - 1) {
//                    digits[i] = 0;
//                    flag = 1;
//                }
//            } else {
//                digits[i]++;
//                return digits;
//            }
//        }
//        int[] digits1 = new int[length + 1];
//        digits1[0] = 1;
//        return digits1;
//    }
//}

/**
 * 如果 digits 的末尾没有 9，例如 [1,2,3]，那么我们直接将末尾的数加一，得到 [1,2,4] 并返回；
 * 如果 digits 的末尾有若干个 9，例如 [1,2,3,9,9]，那么我们只需要找出从末尾开始的第一个不为 9 的元素，即 3，将该元素加一，
 * 得到 [1,2,4,9,9]。随后将末尾的 9 全部置零，得到 [1,2,4,0,0]并返回。
 * 如果 digits 的所有元素都是 9，例如 [9,9,9,9,9]，那么答案为 [1,0,0,0,0,0]。
 * 我们只需要构造一个长度比 digits 多 1 的新数组，将首元素置为 1，其余元素置为 0 即可。
 */
public class Solution_66 {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        for (int i = n - 1; i >= 0; --i) {
            if (digits[i] != 9) {
                ++digits[i];
                for (int j = i + 1; j < n; ++j) {
                    digits[j] = 0;
                }
                return digits;
            }
        }
        // digits 中所有的元素均为 9
        int[] ans = new int[n + 1];
        ans[0] = 1;
        return ans;
    }
}

//public class Solution_66 {
//    public int[] plusOne(int[] digits) {
//        int len = digits.length;
//        for (int i = len - 1; i >= 0; i--) {
//            digits[i] = (digits[i] + 1) % 10;
//            if (digits[i] != 0) { //判断加1之后是不是0
//                return digits;
//            }
//        }
//        digits = new int[len + 1];
//        digits[0] = 1;
//        return digits;
//    }
//}