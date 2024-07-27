package algorithm_lesson.chapter04;

@SuppressWarnings({"all"})
//算法实现题4-9 虚拟汽车加油问题
public class Realization4_9 {
    public static int n = 7;
    public static int k = 7;
    public static int[] gasStation = {1, 2, 3, 4, 5, 1, 6, 6};

    public static void minTimes(int n, int k, int[] gasStation) {
        int sum = 0;
        int gas = n; //表示当前油还可以行驶的距离
        boolean flag = true;
        for (int i = 0; i < k + 1; i++) {
            if (n < gasStation[i]) {
                flag = false;
            }
            if (gas < gasStation[i]) {
                sum++;
                gas = n;
            }
            gas -= gasStation[i];
        }
        if (!flag) {
            System.out.println("No Solution");
        } else {
            System.out.println("最少加油次数为：" + sum);
        }
    }

    public static void main(String[] args) {
        minTimes(n, k, gasStation);
    }
}
