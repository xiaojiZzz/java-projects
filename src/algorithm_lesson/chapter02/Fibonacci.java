package algorithm_lesson.chapter02;

@SuppressWarnings({"all"})
//求解斐波那契数列
public class Fibonacci {
    public int fibonacci(int n) {
        if (n <= 2) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}
