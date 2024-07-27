package leetcode.contest.biweekly_134;

/**
 * 交替组 II
 * 给你一个整数数组 colors 和一个整数 k ，colors表示一个由红色和蓝色瓷砖组成的环，第 i 块瓷砖的颜色为 colors[i] ：
 * colors[i] == 0 表示第 i 块瓷砖的颜色是 红色 。
 * colors[i] == 1 表示第 i 块瓷砖的颜色是 蓝色 。
 * 环中连续 k 块瓷砖的颜色如果是 交替 颜色（也就是说除了第一块和最后一块瓷砖以外，中间瓷砖的颜色与它 左边 和 右边 的颜色都不同），
 * 那么它被称为一个 交替 组。
 * 请你返回 交替 组的数目。
 * 注意 ，由于 colors 表示一个 环 ，第一块 瓷砖和 最后一块 瓷砖是相邻的。
 * 示例 1：
 * 输入：colors = [0,1,0,1,0], k = 3
 * 输出：3
 * 示例 2：
 * 输入：colors = [0,1,0,0,1,0,1], k = 6
 * 输出：2
 * 示例 3：
 * 输入：colors = [1,1,0,1], k = 4
 * 输出：0
 * 提示：
 * 3 <= colors.length <= 105
 * 0 <= colors[i] <= 1
 * 3 <= k <= colors.length
 */
public class Solution_3208 {
    public int numberOfAlternatingGroups(int[] colors, int k) {
        int n = colors.length;
        int ans = 0;
        int cnt = 0;
        for (int i = 0; i < n * 2; i++) {
            if (i > 0 && colors[i % n] == colors[(i - 1) % n]) {
                cnt = 0;
            }
            cnt++;
            // 注意 i 要大于等于 n，避免重复计算
            if (i >= n && cnt >= k) {
                ans++;
            }
        }
        return ans;
    }
}

/*
class Solution {
    public int numberOfAlternatingGroups(int[] colors, int k) {
        int n = colors.length;
        int[] arr = new int[2 * n];
        int[] nor = new int[2 * n - 1];
        for (int i = 0; i < n; i++) {
            arr[i] = colors[i];
            arr[i + n] = colors[i];
        }
        for (int i = 0; i < 2 * n - 1; i++) {
            nor[i] = arr[i] ^ arr[i + 1];
        }
        int idx = 0, ans = 0;
        if (nor[n - 1] == 0) {
            while (idx < n - 1) {
                if (nor[idx] == 1) {
                    int l = 0;
                    while (idx < n - 1 && nor[idx] == 1) {
                        l++;
                        idx++;
                    }
                    if (l >= k - 1) {
                        ans += l - k + 2;
                    }
                } else {
                    idx++;
                }
            }
        } else {
            while (idx < n + k - 2) {
                if (nor[idx] == 1) {
                    int l = 0;
                    while (idx < n + k - 2 && nor[idx] == 1) {
                        l++;
                        idx++;
                    }
                    if (l >= k - 1) {
                        ans += l - k + 2;
                    }
                } else {
                    idx++;
                }
            }
        }
        return ans;
    }
}
*/
