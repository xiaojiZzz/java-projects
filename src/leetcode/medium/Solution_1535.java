package leetcode.medium;

import java.util.Arrays;


/**
 * 给你一个由 不同 整数组成的整数数组 arr 和一个整数 k 。
 * 每回合游戏都在数组的前两个元素（即 arr[0] 和 arr[1] ）之间进行。比较 arr[0] 与 arr[1] 的大小，
 * 较大的整数将会取得这一回合的胜利并保留在位置 0 ，较小的整数移至数组的末尾。当一个整数赢得 k 个连续回合时，游戏结束，该整数就是比赛的 赢家 。
 * 返回赢得比赛的整数。
 * 题目数据 保证 游戏存在赢家。
 * 示例 1：
 * 输入：arr = [2,1,3,5,4,6,7], k = 2
 * 输出：5
 * 解释：一起看一下本场游戏每回合的情况：
 * 因此将进行 4 回合比赛，其中 5 是赢家，因为它连胜 2 回合。
 * 示例 2：
 * 输入：arr = [3,2,1], k = 10
 * 输出：3
 * 解释：3 将会在前 10 个回合中连续获胜。
 * 示例 3：
 * 输入：arr = [1,9,8,2,3,7,6,4,5], k = 7
 * 输出：9
 * 示例 4：
 * 输入：arr = [1,11,22,33,44,55,66,77,88,99], k = 1000000000
 * 输出：99
 */
public class Solution_1535 {
    public int getWinner(int[] arr, int k) {
        int max = Arrays.stream(arr).max().getAsInt();
        int n = arr.length;
        if (k >= n - 1) {
            return max;
        }
        int win = arr[0];
        int times = 0;
        for (int i = 1; i < n; i++) {
            if (win > arr[i]) {
                times++;
                if (times >= k) {
                    return win;
                }
            } else {
                win = arr[i];
                times = 1;
                if (times >= k) {
                    return win;
                }
            }
        }
        return win;
    }
}

/*
class Solution {
    public int getWinner(int[] arr, int k) {
        int mx = arr[0];
        int win = 0;
        for (int i = 1; i < arr.length && win < k; i++) {
            if (arr[i] > mx) { // 新的最大值
                mx = arr[i];
                win = 0;
            }
            win++; // 获胜回合 +1
        }
        return mx;
    }
}
*/
