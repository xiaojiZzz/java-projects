package leetcode.medium;

/**
 * 两个线段获得的最多奖品
 * 在 X 轴 上有一些奖品。给你一个整数数组 prizePositions ，它按照 非递减 顺序排列，其中 prizePositions[i] 是第 i 件奖品的位置。
 * 数轴上一个位置可能会有多件奖品。再给你一个整数 k 。
 * 你可以同时选择两个端点为整数的线段。每个线段的长度都必须是 k 。
 * 你可以获得位置在任一线段上的所有奖品（包括线段的两个端点）。注意，两个线段可能会有相交。
 * 比方说 k = 2 ，你可以选择线段 [1, 3] 和 [2, 4] ，
 * 你可以获得满足 1 <= prizePositions[i] <= 3 或者 2 <= prizePositions[i] <= 4 的所有奖品 i 。
 * 请你返回在选择两个最优线段的前提下，可以获得的 最多 奖品数目。
 * 示例 1：
 * 输入：prizePositions = [1,1,2,2,3,3,5], k = 2
 * 输出：7
 * 解释：这个例子中，你可以选择线段 [1, 3] 和 [3, 5] ，获得 7 个奖品。
 * 示例 2：
 * 输入：prizePositions = [1,2,3,4], k = 0
 * 输出：2
 * 解释：这个例子中，一个选择是选择线段 [3, 3] 和 [4, 4] ，获得 2 个奖品。
 * 提示：
 * 1 <= prizePositions.length <= 105
 * 1 <= prizePositions[i] <= 109
 * 0 <= k <= 109
 * prizePositions 有序非递减。
 */
public class Solution_2555 {
    public int maximizeWin(int[] prizePositions, int k) {
        int n = prizePositions.length;
        if (k * 2 + 1 >= prizePositions[n - 1] - prizePositions[0]) {
            return n;
        }
        int ans = 0;
        int left = 0;
        int[] mx = new int[n + 1];
        for (int right = 0; right < n; right++) {
            while (prizePositions[right] - prizePositions[left] > k) {
                left++;
            }
            ans = Math.max(ans, mx[left] + right - left + 1);
            mx[right + 1] = Math.max(mx[right], right - left + 1);
        }
        return ans;
    }
}

/*
class Solution {
    public int maximizeWin(int[] prizePositions, int k) {
        int n = prizePositions.length;
        if (k * 2 + 1 >= prizePositions[n - 1] - prizePositions[0]) {
            return n;
        }
        int ans = 0;
        int mx = 0;
        int left = 0;
        int right = 0;
        for (int mid = 0; mid < n; mid++) {
            // 把 prizePositions[mid] 视作第二条线段的左端点，计算第二条线段可以覆盖的最大奖品下标
            while (right < n && prizePositions[right] - prizePositions[mid] <= k) {
                right++;
            }
            // 循环结束后，right-1 是第二条线段可以覆盖的最大奖品下标
            ans = Math.max(ans, mx + right - mid);
            // 把 prizePositions[mid] 视作第一条线段的右端点，计算第一条线段可以覆盖的最小奖品下标
            while (prizePositions[mid] - prizePositions[left] > k) {
                left++;
            }
            // 循环结束后，left 是第一条线段可以覆盖的最小奖品下标
            mx = Math.max(mx, mid - left + 1);
        }
        return ans;
    }
}
*/

/*
class Solution {
    // 二分
    public int maximizeWin(int[] prizePositions, int k) {
        int n = prizePositions.length;
        int[] f = new int[n + 1];
        int ans = 0;
        for (int i = 1; i <= n; ++i) {
            int x = prizePositions[i - 1];
            int j = search(prizePositions, x - k);
            ans = Math.max(ans, f[j] + i - j);
            f[i] = Math.max(f[i - 1], i - j);
        }
        return ans;
    }

    private int search(int[] nums, int x) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
*/