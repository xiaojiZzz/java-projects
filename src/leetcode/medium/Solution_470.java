package leetcode.medium;


/**
 * 给定方法 rand7 可生成 [1,7] 范围内的均匀随机整数，试写一个方法 rand10 生成 [1,10] 范围内的均匀随机整数。
 * 你只能调用 rand7() 且不能调用其他方法。请不要使用系统的 Math.random() 方法。
 * 每个测试用例将有一个内部参数 n，即你实现的函数 rand10() 在测试时将被调用的次数。请注意，这不是传递给 rand10() 的参数。
 * 示例 1:
 * 输入: 1
 * 输出: [2]
 * 示例 2:
 * 输入: 2
 * 输出: [2,8]
 * 示例 3:
 * 输入: 3
 * 输出: [3,8,10]
 */
public class Solution_470 {
    // 如果用 rand3() 生成 rand18() 呢，或者随便一个生成另一个呢？
    // 结论：(randX() - 1) * Y + randY() 可以等概率的生成[1, X * Y]范围的随机数
    public int rand10() {
        int first, second;
        while ((first = rand7()) > 6) ;
        while ((second = rand7()) > 5) ;
        return (first & 1) == 1 ? second : 5 + second;
    }

    // 只是为了不报错，实际不调用
    private int rand7() {
        return 0;
    }
}

/*
class Solution extends SolBase {
    public int rand10() {

        // ①已知 rand7()可以等概率生成1~7；
        // ②那么rand7()-1可以等概率生成0~6；
        // ③那么（rand7()-1）* 7 可以等概率生成{0, 7, 14, 21, 28, 35, 42};
        // ④那么（rand7()-1）* 7 + rand7()可以等概率生成1~49。

        // 现在我们实现了等概率生成1~49的功能，我们规定如果生成的数大于40，
        // 我们就重新生成，即只要生成在1~40之间的数，这一我们就可以生成1~40的等概率分布。

        // 再接着，我们用（1~40）%10，就可以得到0~9
        // 最后，（1~40）%10 + 1 ，就可以得到1~10了。

        int ans = 0;
        do {
            ans = ((rand7() - 1) * 7 + rand7());
        } while (ans > 40);
        return 1 + ans % 10;
    }
}
*/

/*
class Solution extends SolBase {
    public int rand10() {
        while (true) {
            int a = rand7();
            int b = rand7();
            int num = (a - 1) * 7 + b; // rand 49
            if (num <= 40) return num % 10 + 1; // 拒绝采样

            a = num - 40; // rand 9
            b = rand7();
            num = (a - 1) * 7 + b; // rand 63
            if (num <= 60) return num % 10 + 1;

            a = num - 60; // rand 3
            b = rand7();
            num = (a - 1) * 7 + b; // rand 21
            if (num <= 20) return num % 10 + 1;
        }
    }
}
*/
