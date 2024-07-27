package algorithm_lesson.chapter02;

@SuppressWarnings({"all"})
//求解阶乘
public class Factorial {
    public int factorial(int n) {
        if (n == 0) {
            return 1;
        }
        return n * factorial(n - 1);
    }
}
