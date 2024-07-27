package ccf_csp._2019._03;

import java.util.Scanner;
import java.util.Stack;

public class _2 {
    public static void main(String[] args) {
        Stack<Integer> numStack = new Stack<>(); // 数字栈
        Stack<Character> operStack = new Stack<>(); // 符号栈
        int res, ans, index; // res是表达式最终的值，ans是临时计算的值，index是扫描表达式的索引
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String[] s = new String[n];
        for (int i = 0; i < n; i++) {
            s[i] = in.next();
        }
        for (int i = 0; i < n; i++) {
            // 初始化
            res = ans = index = 0;
            while (!numStack.isEmpty()) { // 初始化数字栈为空栈
                numStack.pop();
            }
            while (!operStack.isEmpty()) { // 初始化符号栈为空栈
                operStack.pop();
            }
            // 扫描表达式，处理减、乘、除法
            while (index < s[i].length()) { // 开始扫描表达式
                if (s[i].charAt(index) <= '9' && s[i].charAt(index) >= '0') { // 如果是数字，入数字栈
                    numStack.push(Integer.parseInt(s[i].substring(index, index + 1)));
                } else if (s[i].charAt(index) == '+') { // 如果是加号，入符号栈
                    operStack.push(s[i].charAt(index));
                } else if (s[i].charAt(index) == '-') { // 如果是减号，改成加号入符号栈。同时，让下一个数字（正数）变成相反数（负数）
                    operStack.push('+');
                    numStack.push((Integer.parseInt(s[i].substring(index + 1, index + 2))) * (-1));
                    index++; // 由于已经处理运算符之后的数字，所以index要自增一次
                } else if (s[i].charAt(index) == 'x') { // 如果是乘号，不入符号栈。而是从数字栈取出栈顶的数字，与乘号的下一个数字做乘法运算，并将结果入数字栈
                    ans = numStack.pop();
                    numStack.push(ans * (Integer.parseInt(s[i].substring(index + 1, index + 2))));
                    index++; // 由于已经处理运算符之后的数字，所以index要自增一次
                } else { // 如果是除号，不入符号栈。而是从数字栈取出栈顶的数字，与除号的下一个数字做除法运算，并将结果入数字栈
                    ans = numStack.pop();
                    numStack.push(ans / (Integer.parseInt(s[i].substring(index + 1, index + 2))));
                    index++; // 由于已经处理运算符之后的数字，所以index要自增一次
                }
                index++;
            }

            // 计算最终值
            while (!numStack.isEmpty()) { // 将数字栈里面的数字直接相加，得到的结果就是表达式的值
                res += numStack.pop();
            }

            // 判断
            if (res == 24) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
    }
}

