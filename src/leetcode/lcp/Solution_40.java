package leetcode.lcp;

import java.util.PriorityQueue;

/**
 * 心算挑战
 * 「力扣挑战赛」心算项目的挑战比赛中，要求选手从 N 张卡牌中选出 cnt 张卡牌，
 * 若这 cnt 张卡牌数字总和为偶数，则选手成绩「有效」且得分为 cnt 张卡牌数字总和。
 * 给定数组 cards 和 cnt，其中 cards[i] 表示第 i 张卡牌上的数字。
 * 请帮参赛选手计算最大的有效得分。若不存在获取有效得分的卡牌方案，则返回 0。
 * 示例 1：
 * 输入：cards = [1,2,8,9], cnt = 3
 * 输出：18
 * 解释：选择数字为 1、8、9 的这三张卡牌，此时可获得最大的有效得分 1+8+9=18。
 * 示例 2：
 * 输入：cards = [3,3,1], cnt = 1
 * 输出：0
 * 解释：不存在获取有效得分的卡牌方案。
 * 提示：
 * 1 <= cnt <= cards.length <= 10^5
 * 1 <= cards[i] <= 1000
 */
public class Solution_40 {
    public int maxmiumScore(int[] cards, int cnt) {
        PriorityQueue<Integer> even = new PriorityQueue<>((a, b) -> b - a);
        PriorityQueue<Integer> odd = new PriorityQueue<>((a, b) -> b - a);
        for (int card : cards) {
            if (card % 2 == 0) {
                even.add(card);
            } else {
                odd.add(card);
            }
        }
        int ans = 0;
        while (cnt > 0 && !even.isEmpty() && !odd.isEmpty()) {
            if (cnt >= 2 && odd.size() >= 2) {
                Integer oddPoll = odd.poll();
                Integer evenPoll = even.poll();
                if (odd.peek() + oddPoll >= evenPoll + (even.size() == 0 ? 0 : even.peek())) {
                    ans += oddPoll + odd.poll();
                    even.add(evenPoll);
                    cnt -= 2;
                } else {
                    ans += evenPoll;
                    odd.add(oddPoll);
                    cnt--;
                }
            } else {
                ans += even.poll();
                cnt--;
            }
        }
        while (cnt > 0) {
            while (cnt > 0 && !even.isEmpty()) {
                ans += even.poll();
                cnt--;
            }
            while (cnt > 0 && !odd.isEmpty()) {
                if (cnt >= 2 && odd.size() >= 2) {
                    ans += odd.poll() + odd.poll();
                    cnt -= 2;
                } else {
                    return 0;
                }
            }
        }
        return ans;
    }
}

/*
class Solution {
    public int maxmiumScore(int[] cards, int cnt) {
        Arrays.sort(cards);

        int ans = 0;
        int tmp = 0;
        int odd = -1, even = -1;
        int end = cards.length - cnt;
        for (int i = cards.length - 1; i >= end; i--) {
            tmp += cards[i];
            if ((cards[i] & 1) != 0) {
                odd = cards[i];
            } else {
                even = cards[i];
            }
        }

        if ((tmp & 1) == 0) {
            return tmp;
        }

        for (int i = cards.length - cnt - 1; i >= 0; i--) {
            if ((cards[i] & 1) != 0) {
                if (even != -1) {
                    ans = Math.max(ans, tmp - even + cards[i]);
                    break;
                }
            }
        }

        for (int i = cards.length - cnt - 1; i >= 0; i--) {
            if ((cards[i] & 1) == 0) {
                if (odd != -1) {
                    ans = Math.max(ans, tmp - odd + cards[i]);
                    break;
                }
            }
        }

        return ans;
    }
}
*/
