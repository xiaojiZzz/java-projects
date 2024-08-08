package leetcode.medium;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 这里有一个非负整数数组 arr，你最开始位于该数组的起始下标 start 处。当你位于下标 i 处时，你可以跳到 i + arr[i] 或者 i - arr[i]。
 * 请你判断自己是否能够跳到对应元素值为 0 的 任一 下标处。
 * 注意，不管是什么情况下，你都无法跳到数组之外。
 * 示例 1：
 * 输入：arr = [4,2,3,0,3,1,2], start = 5
 * 输出：true
 * 解释：
 * 到达值为 0 的下标 3 有以下可能方案：
 * 下标 5 -> 下标 4 -> 下标 1 -> 下标 3
 * 下标 5 -> 下标 6 -> 下标 4 -> 下标 1 -> 下标 3
 * 示例 2：
 * 输入：arr = [4,2,3,0,3,1,2], start = 0
 * 输出：true
 * 解释：
 * 到达值为 0 的下标 3 有以下可能方案：
 * 下标 0 -> 下标 4 -> 下标 1 -> 下标 3
 * 示例 3：
 * 输入：arr = [3,0,2,1,2], start = 2
 * 输出：false
 * 解释：无法到达值为 0 的下标 1 处。
 * 提示：
 * 1 <= arr.length <= 5 * 10^4
 * 0 <= arr[i] < arr.length
 * 0 <= start < arr.length
 */
public class Solution_1306 {
    public boolean canReach(int[] arr, int start) {
        int n = arr.length;
        boolean[] isVisited = new boolean[n];
        Queue<Integer> queue = new ArrayDeque<>();
        isVisited[start] = true;
        queue.add(start);
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            if (arr[poll] == 0) {
                return true;
            }
            int stride = arr[poll];
            if (poll - stride >= 0 && !isVisited[poll - stride]) {
                queue.add(poll - stride);
                isVisited[poll - stride] = true;
            }
            if (poll + stride < n && !isVisited[poll + stride]) {
                queue.add(poll + stride);
                isVisited[poll + stride] = true;
            }
        }
        return false;
    }
}

/*
class Solution {
    public boolean canReach(int[] arr, int start) {
        int n = arr.length;
        boolean[] visited = new boolean[n];
        return dfs(arr, start, n, visited);
    }
    public boolean dfs(int[] num, int idx, int n, boolean[] visited) {
        if (idx < 0 || idx >= num.length || visited[idx]) {
            return false;
        }
        if (num[idx] == 0) {
            return true;
        }
        int step = num[idx];
        visited[idx] = true;
        return dfs(num, idx + step, n, visited) || dfs(num, idx - step, n, visited);
    }
}
*/