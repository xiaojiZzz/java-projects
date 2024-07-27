package leetcode.lcr;

import java.util.Arrays;


/**
 * 同leetcode 875.爱吃香蕉的珂珂
 */
public class Solution_073 {
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1, right = Arrays.stream(piles).max().getAsInt();
        int hours = 0;
        while (left < right) {
            int mid = (left + right) / 2;
            for (int pile : piles) {
                hours += pile % mid == 0 ? pile / mid : pile / mid + 1;
            }
            if (hours <= h) {
                right = mid;
                hours = 0;
            } else {
                left = mid + 1;
                hours = 0;
            }
        }
        return left;
    }
}

