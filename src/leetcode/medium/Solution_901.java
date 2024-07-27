package leetcode.medium;

import java.util.ArrayDeque;
import java.util.Deque;


/**
 * 设计一个算法收集某些股票的每日报价，并返回该股票当日价格的 跨度 。
 * 当日股票价格的 跨度 被定义为股票价格小于或等于今天价格的最大连续日数（从今天开始往回数，包括今天）。
 * 例如，如果未来 7 天股票的价格是 [100,80,60,70,60,75,85]，那么股票跨度将是 [1,1,1,2,1,4,6] 。
 * 实现 StockSpanner 类：
 * StockSpanner() 初始化类对象。
 * int next(int price) 给出今天的股价 price ，返回该股票当日价格的 跨度 。
 * 示例：
 * 输入：
 * ["StockSpanner", "next", "next", "next", "next", "next", "next", "next"]
 * [[], [100], [80], [60], [70], [60], [75], [85]]
 * 输出：
 * [null, 1, 1, 1, 2, 1, 4, 6]
 * 解释：
 * StockSpanner stockSpanner = new StockSpanner();
 * stockSpanner.next(100); // 返回 1
 * stockSpanner.next(80);  // 返回 1
 * stockSpanner.next(60);  // 返回 1
 * stockSpanner.next(70);  // 返回 2
 * stockSpanner.next(60);  // 返回 1
 * stockSpanner.next(75);  // 返回 4 ，因为截至今天的最后 4 个股价 (包括今天的股价 75) 都小于或等于今天的股价。
 * stockSpanner.next(85);  // 返回 6
 */
public class Solution_901 {
    class StockSpanner {
        Deque<int[]> stack;
        int idx;

        public StockSpanner() {
            stack = new ArrayDeque<int[]>();
            stack.push(new int[]{-1, Integer.MAX_VALUE});
            idx = -1;
        }

        public int next(int price) {
            idx++;
            while (price >= stack.peek()[1]) {
                stack.pop();
            }
            int ret = idx - stack.peek()[0];
            stack.push(new int[]{idx, price});
            return ret;
        }
    }
}

/*
class StockSpanner {

    private ArrayList<Integer> list;

    public StockSpanner() {
        list = new ArrayList<>();
    }

    public int next(int price) {
        list.add(price);
        int res = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            if (price >= list.get(i)) {
                res++;
            } else {
                break;
            }
        }
        return res;
    }
}
*/
