package leetcode.medium;


/**
 * 车上最初有 capacity 个空座位。车 只能 向一个方向行驶（也就是说，不允许掉头或改变方向）
 * 给定整数 capacity 和一个数组 trips ,  trip[i] = [numPassengersi, fromi, toi] 表示第 i 次旅行有 numPassengersi 乘客，
 * 接他们和放他们的位置分别是 fromi 和 toi 。这些位置是从汽车的初始位置向东的公里数。
 * 当且仅当你可以在所有给定的行程中接送所有乘客时，返回 true，否则请返回 false。
 * 示例 1：
 * 输入：trips = [[2,1,5],[3,3,7]], capacity = 4
 * 输出：false
 */
public class Solution_1094 {
    public boolean carPooling(int[][] trips, int capacity) {
        int[] tripsCap = new int[1001];
        for (int i = 0; i < trips.length; i++) {
            tripsCap[trips[i][1]] -= trips[i][0];
            tripsCap[trips[i][2]] += trips[i][0];
        }
        for (int i = 0; i < tripsCap.length; i++) {
            capacity += tripsCap[i];
            if (capacity < 0) {
                return false;
            }
        }
        return true;
    }
}

/*
public class Solution_1094 {
    public boolean carPooling(int[][] trips, int capacity) {
        int[] allTrips = new int[1001];
        for (int i = 0; i < trips.length; i++) {
            for (int j = trips[i][1]; j < trips[i][2]; j++) {
                allTrips[j] += trips[i][0];
                if (allTrips[j] > capacity) {
                    return false;
                }
            }
        }
        return true;
    }
}
*/
