package leetcode.medium;


/**
 * 你正在经营一座摩天轮，该摩天轮共有 4 个座舱 ，每个座舱 最多可以容纳 4 位游客 。
 * 你可以 逆时针 轮转座舱，但每次轮转都需要支付一定的运行成本 runningCost 。摩天轮每次轮转都恰好转动 1 / 4 周。
 * 给你一个长度为 n 的数组 customers ， customers[i] 是在第 i 次轮转（下标从 0 开始）之前到达的新游客的数量。
 * 这也意味着你必须在新游客到来前轮转 i 次。
 * 每位游客在登上离地面最近的座舱前都会支付登舱成本 boardingCost ，一旦该座舱再次抵达地面，他们就会离开座舱结束游玩。
 * 你可以随时停下摩天轮，即便是 在服务所有游客之前 。如果你决定停止运营摩天轮，为了保证所有游客安全着陆，将免费进行所有后续轮转 。
 * 注意，如果有超过 4 位游客在等摩天轮，那么只有 4 位游客可以登上摩天轮，其余的需要等待 下一次轮转 。
 * 返回最大化利润所需执行的 最小轮转次数 。 如果不存在利润为正的方案，则返回 -1 。
 * 示例 1：
 * 输入：customers = [8,3], boardingCost = 5, runningCost = 6
 * 输出：3
 * 解释：座舱上标注的数字是该座舱的当前游客数。
 * 1. 8 位游客抵达，4 位登舱，4 位等待下一舱，摩天轮轮转。当前利润为 4 * $5 - 1 * $6 = $14 。
 * 2. 3 位游客抵达，4 位在等待的游客登舱，其他 3 位等待，摩天轮轮转。当前利润为 8 * $5 - 2 * $6 = $28 。
 * 3. 最后 3 位游客登舱，摩天轮轮转。当前利润为 11 * $5 - 3 * $6 = $37 。
 * 轮转 3 次得到最大利润，最大利润为 $37 。
 * 示例 2：
 * 输入：customers = [10,9,6], boardingCost = 6, runningCost = 4
 * 输出：7
 * 解释：
 * 1. 10 位游客抵达，4 位登舱，6 位等待下一舱，摩天轮轮转。当前利润为 4 * $6 - 1 * $4 = $20 。
 * 2. 9 位游客抵达，4 位登舱，11 位等待（2 位是先前就在等待的，9 位新加入等待的），摩天轮轮转。当前利润为 8 * $6 - 2 * $4 = $40 。
 * 3. 最后 6 位游客抵达，4 位登舱，13 位等待，摩天轮轮转。当前利润为 12 * $6 - 3 * $4 = $60 。
 * 4. 4 位登舱，9 位等待，摩天轮轮转。当前利润为 * $6 - 4 * $4 = $80 。
 * 5. 4 位登舱，5 位等待，摩天轮轮转。当前利润为 20 * $6 - 5 * $4 = $100 。
 * 6. 4 位登舱，1 位等待，摩天轮轮转。当前利润为 24 * $6 - 6 * $4 = $120 。
 * 7. 1 位登舱，摩天轮轮转。当前利润为 25 * $6 - 7 * $4 = $122 。
 * 轮转 7 次得到最大利润，最大利润为$122 。
 * 示例 3：
 * 输入：customers = [3,4,0,5,1], boardingCost = 1, runningCost = 92
 * 输出：-1
 * 解释：
 * 1. 3 位游客抵达，3 位登舱，0 位等待，摩天轮轮转。当前利润为 3 * $1 - 1 * $92 = -$89 。
 * 2. 4 位游客抵达，4 位登舱，0 位等待，摩天轮轮转。当前利润为 7 * $1 - 2 * $92 = -$177 。
 * 3. 0 位游客抵达，0 位登舱，0 位等待，摩天轮轮转。当前利润为 7 * $1 - 3 * $92 = -$269 。
 * 4. 5 位游客抵达，4 位登舱，1 位等待，摩天轮轮转。当前利润为 11 * $1 - 4 * $92 = -$357 。
 * 5. 1 位游客抵达，2 位登舱，0 位等待，摩天轮轮转。当前利润为 13 * $1 - 5 * $92 = -$447 。
 * 利润永不为正，所以返回 -1 。
 */
public class Solution_1599 {
    public int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {
        int ope = 0, waiting = 0, totalProfit = 0, res = -1, maxProfit = 0;
        for (int customer : customers) {
            waiting += customer;
            totalProfit += Math.min(waiting, 4) * boardingCost - runningCost;
            waiting -= Math.min(waiting, 4);
            ope++;
            if (totalProfit > maxProfit) {
                maxProfit = totalProfit;
                res = ope;
            }
        }
        while (waiting > 0) {
            totalProfit += Math.min(waiting, 4) * boardingCost - runningCost;
            waiting -= Math.min(waiting, 4);
            ope++;
            if (totalProfit > maxProfit) {
                maxProfit = totalProfit;
                res = ope;
            }
        }
        return res;
    }
}

/*
class Solution {
    public int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {
        int ans = -1;
        int maxProfit = 0;
        int totalProfit = 0;
        int operations = 0;
        int customersCount = 0;
        int n = customers.length;
        for (int i = 0; i < n; i++) {
            operations++;
            customersCount += customers[i];
            int curCustomers = Math.min(customersCount, 4);
            customersCount -= curCustomers;
            totalProfit += boardingCost * curCustomers - runningCost;
            if (totalProfit > maxProfit) {
                maxProfit = totalProfit;
                ans = operations;
            }
        }
        if (customersCount == 0) {
            return ans;
        }
        int profitEachTime = boardingCost * 4 - runningCost;
        if (profitEachTime <= 0) {
            return ans;
        }
        if (customersCount > 0) {
            int fullTimes = customersCount / 4;
            totalProfit += profitEachTime * fullTimes;
            operations += fullTimes;
            if (totalProfit > maxProfit) {
                maxProfit = totalProfit;
                ans = operations;
            }
            int remainingCustomers = customersCount % 4;
            int remainingProfit = boardingCost * remainingCustomers - runningCost;
            totalProfit += remainingProfit;
            if (totalProfit > maxProfit) {
                maxProfit = totalProfit;
                operations++;
                ans++;
            }
        }
        return ans;
    }
}
*/
