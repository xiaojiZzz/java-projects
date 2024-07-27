package leetcode.medium;


/**
 * 桌子上有 n 个球，每个球的颜色不是黑色，就是白色。
 * 给你一个长度为 n 、下标从 0 开始的二进制字符串 s，其中 1 和 0 分别代表黑色和白色的球。
 * 在每一步中，你可以选择两个相邻的球并交换它们。
 * 返回「将所有黑色球都移到右侧，所有白色球都移到左侧所需的 最小步数」。
 * 示例 1：
 * 输入：s = "101"
 * 输出：1
 * 解释：我们可以按以下方式将所有黑色球移到右侧：
 * - 交换 s[0] 和 s[1]，s = "011"。
 * 最开始，1 没有都在右侧，需要至少 1 步将其移到右侧。
 * 示例 2：
 * 输入：s = "100"
 * 输出：2
 * 解释：我们可以按以下方式将所有黑色球移到右侧：
 * - 交换 s[0] 和 s[1]，s = "010"。
 * - 交换 s[1] 和 s[2]，s = "001"。
 * 可以证明所需的最小步数为 2 。
 * 示例 3：
 * 输入：s = "0111"
 * 输出：0
 * 解释：所有黑色球都已经在右侧。
 */
public class Solution_2938 {
    public long minimumSteps(String s) {
        int n = s.length();
        long ans = 0;
        int idx = n - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '1') {
                ans += idx - i;
                idx--;
            }
        }
        return ans;
    }
}

/*
class Solution {
    public long minimumSteps(String s) {
        long ans = 0;
        int cnt1 = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') {
                cnt1++;
            } else {
                ans += cnt1;
            }
        }
        return ans;
    }
}
*/
