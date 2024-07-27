package leetcode.medium;

import java.util.Arrays;


/**
 * 给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。
 * 示例 1 :
 * 输入: 2736
 * 输出: 7236
 * 解释: 交换数字2和数字7。
 * 示例 2 :
 * 输入: 9973
 * 输出: 9973
 * 解释: 不需要交换。
 * 注意:
 * 给定数字的范围是 [0, 108]
 */
public class Solution_670 {
    public int maximumSwap(int num) {
        char[] charArray = String.valueOf(num).toCharArray();
        int cnt = charArray.length;
        int[] nums1 = new int[cnt];
        int[] nums2 = new int[cnt];
        int idx = 0;
        while (num > 0) {
            int n = num % 10;
            nums1[idx] = n;
            nums2[idx] = n;
            idx++;
            num /= 10;
        }
        Arrays.sort(nums2);
        for (int i = cnt - 1; i >= 0; i--) {
            if (nums1[i] == nums2[i]) {
                continue;
            }
            for (int j = 0; j < i; j++) {
                if (nums1[j] == nums2[i]) {
                    nums1[j] = nums1[i];
                    nums1[i] = nums2[i];
                    break;
                }
            }
            break;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = cnt - 1; i >= 0; i--) {
            sb.append(nums1[i]);
        }
        return Integer.parseInt(sb.toString());
    }
}

/*
class Solution {
    public int maximumSwap(int num) {
        char[] charArray = String.valueOf(num).toCharArray();
        int n = charArray.length;
        int maxNum = num;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                swap(charArray, i, j);
                maxNum = Math.max(maxNum, Integer.parseInt(new String(charArray)));
                swap(charArray, i, j);
            }
        }
        return maxNum;
    }

    public void swap(char[] charArray, int i, int j) {
        char temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
    }
}
*/

/*
class Solution {
    public int maximumSwap(int num) {
        char[] charArray = String.valueOf(num).toCharArray();
        int n = charArray.length;
        int maxIdx = n - 1;
        int idx1 = -1, idx2 = -1;
        for (int i = n - 1; i >= 0; i--) {
            if (charArray[i] > charArray[maxIdx]) {
                maxIdx = i;
            } else if (charArray[i] < charArray[maxIdx]) {
                idx1 = i;
                idx2 = maxIdx;
            }
        }
        if (idx1 >= 0) {
            swap(charArray, idx1, idx2);
            return Integer.parseInt(new String(charArray));
        } else {
            return num;
        }
    }

    public void swap(char[] charArray, int i, int j) {
        char temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
    }
}
*/
