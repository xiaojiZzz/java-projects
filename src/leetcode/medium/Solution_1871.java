package leetcode.medium;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 跳跃游戏 VII
 * 给你一个下标从 0 开始的二进制字符串 s 和两个整数 minJump 和 maxJump 。
 * 一开始，你在下标 0 处，且该位置的值一定为 '0' 。当同时满足如下条件时，你可以从下标 i 移动到下标 j 处：
 * i + minJump <= j <= min(i + maxJump, s.length - 1) 且 s[j] == '0'.
 * 如果你可以到达 s 的下标 s.length - 1 处，请你返回 true ，否则返回 false 。
 * 示例 1：
 * 输入：s = "011010", minJump = 2, maxJump = 3
 * 输出：true
 * 解释：
 * 第一步，从下标 0 移动到下标 3 。
 * 第二步，从下标 3 移动到下标 5 。
 * 示例 2：
 * 输入：s = "01101110", minJump = 2, maxJump = 3
 * 输出：false
 * 提示：
 * 2 <= s.length <= 105
 * s[i] 要么是 '0' ，要么是 '1'
 * s[0] == '0'
 * 1 <= minJump <= maxJump < s.length
 */
public class Solution_1871 {
    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        if (s.charAt(n - 1) == '1') {
            return false;
        }
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = minJump; i <= maxJump; i++) {
            if (i == n - 1) {
                return true;
            }
            if (s.charAt(i) == '0') {
                queue.offer(i);
            }
        }
        int minRange = maxJump;
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            for (int i = Math.max(minRange + 1, poll + minJump); i <= Math.min(n - 1, poll + maxJump); i++) {
                if (s.charAt(i) == '0') {
                    if (i == n - 1) {
                        return true;
                    }
                    queue.offer(i);
                }
            }
            minRange = poll + maxJump;
        }
        return false;
    }
}

/*
class Solution {
    // 差分数组
    public boolean canReach(String s, int minJump, int maxJump) {
        char[] buf = s.toCharArray();
        int n = buf.length;
        int[] diff = new int[n];
        diff[0] = 1;
        diff[1] = -1;
        int acc = 0;
        for (int i = 0; i < n; i++) {
            acc += diff[i];
            if (acc > 0 && buf[i] == '0') {
                if (i + minJump < n) {
                    diff[i + minJump] += 1;
                }
                if (i + maxJump + 1 < n) {
                    diff[i + maxJump + 1] -= 1;
                }
            }
        }
        return acc > 0 && buf[n - 1] == '0';
    }
}
*/