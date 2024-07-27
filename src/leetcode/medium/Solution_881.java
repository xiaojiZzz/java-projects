package leetcode.medium;

import java.util.Arrays;


/**
 * 给定数组 people 。people[i]表示第 i 个人的体重 ，船的数量不限，每艘船可以承载的最大重量为 limit。
 * 每艘船最多可同时载两人，但条件是这些人的重量之和最多为 limit。
 * 返回 承载所有人所需的最小船数 。
 * 示例 1：
 * 输入：people = [1,2], limit = 3
 * 输出：1
 * 解释：1 艘船载 (1, 2)
 * 示例 2：
 * 输入：people = [3,2,2,1], limit = 3
 * 输出：3
 * 解释：3 艘船分别载 (1, 2), (2) 和 (3)
 * 示例 3：
 * 输入：people = [3,5,3,4], limit = 5
 * 输出：4
 * 解释：4 艘船分别载 (3), (3), (4), (5)
 */
public class Solution_881 {
    public int numRescueBoats(int[] people, int limit) {
        int ans = 0;
        Arrays.sort(people);
        int light = 0, heavy = people.length - 1;
        while (light <= heavy) {
            if (people[light] + people[heavy] <= limit) {
                ++light;
            }
            --heavy;
            ++ans;
        }
        return ans;
    }
}

/*
class Solution {
    public int numRescueBoats(int[] people, int limit) {
        int n = people.length;
        Arrays.sort(people);
        boolean[] choose = new boolean[n];
        int isChosen = 0;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (choose[i]) {
                continue;
            }
            int weight = people[i];
            choose[i] = true;
            int index = idx(people, limit - weight + 1);
            boolean flag = false;
            for (int j = index - 1; j > i; j--) {
                if (!choose[j]) {
                    flag = true;
                    choose[j] = true;
                    break;
                }
            }
            if (flag) {
                ans++;
                isChosen += 2;
            } else {
                ans += n - isChosen;
                break;
            }
        }
        return ans;
    }

    public int idx(int[] people, int weight) {
        int left = 0, right = people.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (people[mid] < weight) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
*/
