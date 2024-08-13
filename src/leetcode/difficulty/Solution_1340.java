package leetcode.difficulty;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 跳跃游戏 V
 * 给你一个整数数组 arr 和一个整数 d 。每一步你可以从下标 i 跳到：
 * i + x ，其中 i + x < arr.length 且 0 < x <= d 。
 * i - x ，其中 i - x >= 0 且 0 < x <= d 。
 * 除此以外，你从下标 i 跳到下标 j 需要满足：arr[i] > arr[j] 且 arr[i] > arr[k] ，
 * 其中下标 k 是所有 i 到 j 之间的数字（更正式的，min(i, j) < k < max(i, j)）。
 * 你可以选择数组的任意下标开始跳跃。请你返回你 最多 可以访问多少个下标。
 * 请注意，任何时刻你都不能跳到数组的外面。
 * 示例 1：
 * 输入：arr = [6,4,14,6,8,13,9,7,10,6,12], d = 2
 * 输出：4
 * 解释：你可以从下标 10 出发，然后如上图依次经过 10 --> 8 --> 6 --> 7 。
 * 注意，如果你从下标 6 开始，你只能跳到下标 7 处。你不能跳到下标 5 处因为 13 > 9 。
 * 你也不能跳到下标 4 处，因为下标 5 在下标 4 和 6 之间且 13 > 9 。
 * 类似的，你不能从下标 3 处跳到下标 2 或者下标 1 处。
 * 示例 2：
 * 输入：arr = [3,3,3,3,3], d = 3
 * 输出：1
 * 解释：你可以从任意下标处开始且你永远无法跳到任何其他坐标。
 * 示例 3：
 * 输入：arr = [7,6,5,4,3,2,1], d = 1
 * 输出：7
 * 解释：从下标 0 处开始，你可以按照数值从大到小，访问所有的下标。
 * 示例 4：
 * 输入：arr = [7,1,7,1,7,1], d = 2
 * 输出：2
 * 示例 5：
 * 输入：arr = [66], d = 1
 * 输出：1
 * 提示：
 * 1 <= arr.length <= 1000
 * 1 <= arr[i] <= 10^5
 * 1 <= d <= arr.length
 */
public class Solution_1340 {
    public int maxJumps(int[] arr, int d) {
        int n = arr.length;
        int[][] numsIndices = new int[n][2];
        for (int i = 0; i < n; i++) {
            numsIndices[i][0] = arr[i];
            numsIndices[i][1] = i;
        }
        Arrays.sort(numsIndices, Comparator.comparingInt(a -> a[0]));
        int[] dp = new int[n];
        int maxIndices = 0;
        for (int i = 0; i < n; i++) {
            int num = numsIndices[i][0], index = numsIndices[i][1];
            int currMaxIndices = 1;
            int minIndex = Math.max(index - d, 0), maxIndex = Math.min(index + d, n - 1);
            for (int nextIndex = index - 1; nextIndex >= minIndex && arr[nextIndex] < num; nextIndex--) {
                currMaxIndices = Math.max(currMaxIndices, dp[nextIndex] + 1);
            }
            for (int nextIndex = index + 1; nextIndex <= maxIndex && arr[nextIndex] < num; nextIndex++) {
                currMaxIndices = Math.max(currMaxIndices, dp[nextIndex] + 1);
            }
            dp[index] = currMaxIndices;
            maxIndices = Math.max(maxIndices, currMaxIndices);
        }
        return maxIndices;
    }
}

/*
class Solution {
    int[] arr;
    int n; //数组长度
    int d;
    int[] dp;   //用来存储每个柱子的最大结果

    public int maxJumps(int[] arr, int d) {
        this.arr = arr;
        this.n = arr.length;
        this.d = d;
        dp = new int[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, getMaxFromOnePoint(i));
        }
        return ans;
    }

    private int getMaxFromOnePoint(int p) {
        if (dp[p] != 0) return dp[p];   //当前柱子已经计算过，直接返回它的值
        // 如果没有，分别计算它往左和往右跳一次可以得到的最大值
        int leftMax = 0;
        int l = 1;  // 往左跳的距离
        while (p - l >= 0 && l <= d) {
            if (arr[p - l] >= arr[p]) {   //遇到了高柱子挡路，只能结束
                break;
            } else {
                if (dp[p - l] == 0) dp[p - l] = getMaxFromOnePoint(p - l);
                leftMax = Math.max(leftMax, dp[p - l]);
                l++;
            }
        }
        // 同理右边
        int rightMax = 0;
        int r = 1;
        while (p + r < n && r <= d) {
            if (arr[p + r] >= arr[p]) {
                break;
            } else {
                if (dp[p + r] == 0) dp[p + r] = getMaxFromOnePoint(p + r);
                rightMax = Math.max(rightMax, dp[p + r]);
                r++;
            }
        }
        dp[p] = Math.max(leftMax, rightMax) + 1;
        return dp[p];
    }
}
*/
