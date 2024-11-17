package leetcode.medium;

import java.util.Arrays;

/**
 * 适龄的朋友
 * 在社交媒体网站上有 n 个用户。给你一个整数数组 ages ，其中 ages[i] 是第 i 个用户的年龄。
 * 如果下述任意一个条件为真，那么用户 x 将不会向用户 y（x != y）发送好友请求：
 * ages[y] <= 0.5 * ages[x] + 7
 * ages[y] > ages[x]
 * ages[y] > 100 && ages[x] < 100
 * 否则，x 将会向 y 发送一条好友请求。
 * 注意，如果 x 向 y 发送一条好友请求，y 不必也向 x 发送一条好友请求。另外，用户不会向自己发送好友请求。
 * 返回在该社交媒体网站上产生的好友请求总数。
 * 示例 1：
 * 输入：ages = [16,16]
 * 输出：2
 * 解释：2 人互发好友请求。
 * 示例 2：
 * 输入：ages = [16,17,18]
 * 输出：2
 * 解释：产生的好友请求为 17 -> 16 ，18 -> 17 。
 * 示例 3：
 * 输入：ages = [20,30,100,110,120]
 * 输出：3
 * 解释：产生的好友请求为 110 -> 100 ，120 -> 110 ，120 -> 100 。
 * 提示：
 * n == ages.length
 * 1 <= n <= 2 * 104
 * 1 <= ages[i] <= 120
 */
public class Solution_825 {
    public int numFriendRequests(int[] ages) {
        int n = ages.length;
        Arrays.sort(ages);
        int left = 0, right = 0, ans = 0;
        for (int age : ages) {
            if (age < 15) {
                continue;
            }
            while (ages[left] <= 0.5 * age + 7) {
                ++left;
            }
            while (right + 1 < n && ages[right + 1] <= age) {
                ++right;
            }
            ans += right - left;
        }
        return ans;
    }
}

/*
class Solution {
    public int numFriendRequests(int[] ages) {
        int[] cnt = new int[121];
        for (int age : ages) {
            ++cnt[age];
        }
        int[] pre = new int[121];
        for (int i = 1; i <= 120; ++i) {
            pre[i] = pre[i - 1] + cnt[i];
        }
        int ans = 0;
        for (int i = 15; i <= 120; ++i) {
            if (cnt[i] > 0) {
                int bound = (int) (i * 0.5 + 8);
                ans += cnt[i] * (pre[i] - pre[bound - 1] - 1);
            }
        }
        return ans;
    }
}
*/

/*
class Solution {
    public int numFriendRequests(int[] ages) {
        int[] cnt = new int[121];
        for (int age : ages) {
            cnt[age]++;
        }
        int ans = 0, ageY = 0, cntWindow = 0;
        for (int ageX = 0; ageX < cnt.length; ageX++) {
            cntWindow += cnt[ageX];
            if (ageY * 2 <= ageX + 14) { // 不能发送好友请求
                cntWindow -= cnt[ageY];
                ageY++;
            }
            if (cntWindow > 0) { // 存在可以发送好友请求的用户
                ans += cnt[ageX] * cntWindow - cnt[ageX];
            }
        }
        return ans;
    }
}
*/
