package leetcode.lcr;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 读者来到图书馆排队借还书，图书管理员使用两个书车来完成整理借还书的任务。
 * 书车中的书从下往上叠加存放，图书管理员每次只能拿取书车顶部的书。排队的读者会有两种操作：
 * push(bookID)：把借阅的书籍还到图书馆。
 * pop()：从图书馆中借出书籍。
 * 为了保持图书的顺序，图书管理员每次取出供读者借阅的书籍是 最早 归还到图书馆的书籍。你需要返回 每次读者借出书的值 。
 * 如果没有归还的书可以取出，返回 -1 。
 * 示例 1：
 * 输入：
 * ["BookQueue", "push", "push", "pop"]
 * [[], [1], [2], []]
 * 输出：[null,null,null,1]
 * 解释：
 * MyQueue myQueue = new MyQueue();
 * myQueue.push(1); // queue is: [1]
 * myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
 * myQueue.pop(); // return 1, queue is [2]
 * 提示：
 * 1 <= bookID <= 10000
 * 最多会对 push、pop 进行 10000 次调用
 */
public class Solution_125 {
    class CQueue {

        private Deque<Integer> stack1;
        private Deque<Integer> stack2;

        public CQueue() {
            stack1 = new ArrayDeque<>();
            stack2 = new ArrayDeque<>();
        }

        public void appendTail(int value) {
            if (stack1.isEmpty()) {
                stack1.push(value);
            } else {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
                stack2.push(value);
                while (!stack2.isEmpty()) {
                    stack1.push(stack2.pop());
                }
            }
        }

        public int deleteHead() {
            return stack1.isEmpty() ? -1 : stack1.pop();
        }
    }
}

/*
class CQueue {
    Deque<Integer> inStack;
    Deque<Integer> outStack;

    public CQueue() {
        inStack = new ArrayDeque<Integer>();
        outStack = new ArrayDeque<Integer>();
    }

    public void appendTail(int value) {
        inStack.push(value);
    }

    public int deleteHead() {
        if (outStack.isEmpty()) {
            if (inStack.isEmpty()) {
                return -1;
            }
            in2out();
        }
        return outStack.pop();
    }

    private void in2out() {
        while (!inStack.isEmpty()) {
            outStack.push(inStack.pop());
        }
    }
}
*/
