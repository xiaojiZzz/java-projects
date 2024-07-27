package leetcode.medium;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;


/**
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * 实现 MinStack 类:
 * MinStack() 初始化堆栈对象。
 * void push(int val) 将元素val推入堆栈。
 * void pop() 删除堆栈顶部的元素。
 * int top() 获取堆栈顶部的元素。
 * int getMin() 获取堆栈中的最小元素。
 * 示例 1:
 * 输入：
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 * 输出：
 * [null,null,null,null,-3,null,0,-2]
 * 解释：
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 */
public class Solution_155 {
    class MinStack {
        private PriorityQueue<Integer> min;
        private Deque<Integer> stack;

        public MinStack() {
            min = new PriorityQueue();
            stack = new LinkedList<>();
        }

        public void push(int val) {
            min.add(val);
            stack.push(val);
        }

        public void pop() {
            Integer pop = stack.pop();
            min.remove(pop);
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return min.peek();
        }
    }
}

/*
class MinStack {
    Deque<Integer> xStack;
    Deque<Integer> minStack;

    public MinStack() {
        xStack = new ArrayDeque<>();
        minStack = new ArrayDeque<>();
    }

    public void push(int x) {
        xStack.push(x);
        if (minStack.isEmpty()) {
            minStack.push(x);
        } else {
            minStack.push(Math.min(minStack.peek(), x));
        }
    }

    public void pop() {
        xStack.pop();
        minStack.pop();
    }

    public int top() {
        return xStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
*/

/*
class MinStack {
    int min = Integer.MAX_VALUE;
    Stack<Integer> stack = new Stack<Integer>();

    public void push(int x) {
        //当前值更小
        if (x <= min) {
            //将之前的最小值保存
            stack.push(min);
            //更新最小值
            min = x;
        }
        stack.push(x);
    }

    public void pop() {
        //如果弹出的值是最小值，那么将下一个元素更新为最小值
        if (stack.pop() == min) {
            min = stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }
}
*/

/*
class MinStack {
    long min;
    Stack<Long> stack;

    public MinStack() {
        stack = new Stack<>();
    }

    public void push(int x) {
        if (stack.isEmpty()) {
            min = x;
            stack.push(x - min);
        } else {
            stack.push(x - min);
            if (x < min) {
                min = x; // 更新最小值
            }

        }
    }

    public void pop() {
        if (stack.isEmpty()) return;

        long pop = stack.pop();

        //弹出的是负值，要更新 min
        if (pop < 0) {
            min = min - pop;
        }

    }

    public int top() {
        long top = stack.peek();
        //负数的话，出栈的值保存在 min 中
        if (top < 0) {
            return (int) (min);
            //出栈元素加上最小值即可
        } else {
            return (int) (top + min);
        }
    }

    public int getMin() {
        return (int) min;
    }
}
*/

/*
class MinStack {
    class Node {
        int value;
        int min;
        Node next;

        Node(int x, int min) {
            this.value = x;
            this.min = min;
            next = null;
        }
    }

    Node head;

    //每次加入的节点放到头部
    public void push(int x) {
        if (null == head) {
            head = new Node(x, x);
        } else {
            //当前值和之前头结点的最小值较小的做为当前的 min
            Node n = new Node(x, Math.min(x, head.min));
            n.next = head;
            head = n;
        }
    }

    public void pop() {
        if (head != null)
            head = head.next;
    }

    public int top() {
        if (head != null)
            return head.value;
        return -1;
    }

    public int getMin() {
        if (null != head)
            return head.min;
        return -1;
    }
}
*/
