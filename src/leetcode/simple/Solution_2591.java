package leetcode.simple;


/**
 * 给你一个整数 money ，表示你总共有的钱数（单位为美元）和另一个整数 children ，表示你要将钱分配给多少个儿童。
 * 你需要按照如下规则分配：
 * 所有的钱都必须被分配。
 * 每个儿童至少获得 1 美元。
 * 没有人获得 4 美元。
 * 请你按照上述规则分配金钱，并返回 最多 有多少个儿童获得 恰好 8 美元。如果没有任何分配方案，返回 -1 。
 * 示例 1：
 * 输入：money = 20, children = 3
 * 输出：1
 * 解释：
 * 最多获得 8 美元的儿童数为 1 。一种分配方案为：
 * - 给第一个儿童分配 8 美元。
 * - 给第二个儿童分配 9 美元。
 * - 给第三个儿童分配 3 美元。
 * 没有分配方案能让获得 8 美元的儿童数超过 1 。
 * 示例 2：
 * 输入：money = 16, children = 2
 * 输出：2
 * 解释：每个儿童都可以获得 8 美元。
 */
public class Solution_2591 {
    public int distMoney(int money, int children) {
        int left = money - children;
        if (left < 0) {
            return -1;
        }
        int ans = 0;
        boolean flag = false;
        for (int i = 0; i < children; i++) {
            if (left >= 7) {
                left -= 7;
                ans++;
                flag = true;
            } else {
                if (left == 3 && i == children - 1) {
                    ans--;
                }
                flag = false;
                break;
            }
        }
        if (left > 0 && flag) {
            ans--;
        }
        return ans;
    }
}

/*
class Solution {
    public int distMoney(int money, int children) {
        money -= children; // 每人至少 1 美元
        if (money < 0) return -1;
        int ans = Math.min(money / 7, children); // 初步分配，让尽量多的人分到 8 美元
        money -= ans * 7;
        children -= ans;
        if (children == 0 && money > 0 || // 必须找一个前面分了 8 美元的人，分完剩余的钱
                children == 1 && money == 3) // 不能有人恰好分到 4 美元
            --ans;
        return ans;
    }
}
*/
