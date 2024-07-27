package leetcode.difficulty;

import java.util.PriorityQueue;


/**
 * 中位数是有序整数列表中的中间值。如果列表的大小是偶数，则没有中间值，中位数是两个中间值的平均值。
 * 例如 arr = [2,3,4] 的中位数是 3 。
 * 例如 arr = [2,3] 的中位数是 (2 + 3) / 2 = 2.5 。
 * 实现 MedianFinder 类:
 * MedianFinder() 初始化 MedianFinder 对象。
 * void addNum(int num) 将数据流中的整数 num 添加到数据结构中。
 * double findMedian() 返回到目前为止所有元素的中位数。与实际答案相差 10-5 以内的答案将被接受。
 * 示例 1：
 * 输入
 * ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
 * [[], [1], [2], [], [3], []]
 * 输出
 * [null, null, null, 1.5, null, 2.0]
 * 解释
 * MedianFinder medianFinder = new MedianFinder();
 * medianFinder.addNum(1);    // arr = [1]
 * medianFinder.addNum(2);    // arr = [1, 2]
 * medianFinder.findMedian(); // 返回 1.5 ((1 + 2) / 2)
 * medianFinder.addNum(3);    // arr[1, 2, 3]
 * medianFinder.findMedian(); // return 2.0
 */
public class Solution_295 {
    class MedianFinder {

        PriorityQueue<Integer> min;
        PriorityQueue<Integer> max;

        public MedianFinder() {
            min = new PriorityQueue<>((a, b) -> b - a);
            max = new PriorityQueue<>((a, b) -> a - b);
        }

        public void addNum(int num) {
            if (min.size() > max.size()) {
                Integer x = min.poll();
                if (x > num) {
                    min.add(num);
                    max.add(x);
                } else {
                    min.add(x);
                    max.add(num);
                }
            } else {
                if (min.isEmpty()) {
                    min.add(num);
                } else {
                    Integer x = max.poll();
                    if (x > num) {
                        min.add(num);
                        max.add(x);
                    } else {
                        min.add(x);
                        max.add(num);
                    }
                }
            }
        }

        public double findMedian() {
            if ((min.size() + max.size()) % 2 == 0) {
                return (min.peek() + max.peek()) / 2.0;
            } else {
                return min.peek();
            }
        }
    }
}
