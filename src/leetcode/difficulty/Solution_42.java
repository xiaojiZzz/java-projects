package leetcode.difficulty;

/**
 * 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 示例 1：
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * 示例 2：
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 * 提示：
 * n == height.length
 * 1 <= n <= 2 * 104
 * 0 <= height[i] <= 105
 */
public class Solution_42 {
    //优化空间
    public int trap(int[] height) {
        int len = height.length;
        int ans = 0;
        int left = 0, right = len - 1;
        int p = 0, s = 0;
        while (left < right) {
            p = Math.max(p, height[left]);
            s = Math.max(s, height[right]);
            if (p < s) {
                ans += p - height[left];
                left++;
            } else {
                ans += s - height[right];
                right--;
            }
        }
        return ans;
    }
}

/*
public class Solution_42 {
    public int trap(int[] height) {
        int len = height.length;
        int[] p = new int[len];
        int[] s = new int[len];
        int ans = 0;
        p[0] = height[0];
        s[len - 1] = height[len - 1];
        for (int i = 1; i < len; i++) {
            p[i] = Math.max(p[i - 1], height[i]);
            s[len - i - 1] = Math.max(height[len - i - 1], s[len - i]);
        }
        for (int i = 0; i < len; i++) {
            ans += Math.min(p[i], s[i]) - height[i];
        }
        return ans;
    }
}
*/

/*
class Solution {
    //单调栈
    public int trap(int[] height) {
        int ans = 0;
        Deque<Integer> st = new ArrayDeque<>();
        for (int i = 0; i < height.length; i++) {
            while (!st.isEmpty() && height[i] >= height[st.peek()]) {
                int bottomH = height[st.pop()];
                if (st.isEmpty()) {
                    break;
                }
                int left = st.peek();
                int dh = Math.min(height[left], height[i]) - bottomH; // 面积的高
                ans += dh * (i - left - 1);
            }
            st.push(i);
        }
        return ans;
    }
}
*/
