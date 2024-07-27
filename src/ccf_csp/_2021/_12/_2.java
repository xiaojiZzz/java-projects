package ccf_csp._2021._12;

import java.util.Scanner;

public class _2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), N = scanner.nextInt();
        int[] A = new int[n + 2];
        int r = N / (n + 1);
        for (int i = 1; i < n + 1; i++) {
            A[i] = scanner.nextInt();
        }
        A[n + 1] = N;
        long error = 0;
        int fx, gx; //fx,gx为当前计算的函数值
        int dr; //dr为内层循环每次要移动的位次
        int ddr = 0; //用于处理边界情况时的临时变量
        int flag = 0; //用于标记dr是否改变

        //以A[i]的值作为外层循环的界限
        for (int i = 0; i < n + 1; i++) {
            fx = i;
            //在每段A[i] 与 A[i + 1] 的间隔中，gx的值以r为间隔而变化
            for (int j = A[i]; j < A[i + 1]; j += dr) {
                gx = j / r;
                //上一个边界情况之后，上一段g(x)相等段中还有遗留未计算的部分，对其进行处理
                if (flag == 1) {
                    dr = ddr;
                    flag = 0; //标记边界情况已经处理完毕
                } else {
                    dr = r; //边界情况处理完之后，重新将循环间隔变回r
                }
                //当前段处于A[i]和A[i + 1]中间，直接计算error值
                if (j + dr - 1 < A[i + 1]) {
                    error += (long) Math.abs(fx - gx) * dr;
                } else { //j靠近A[i + 1]的边界情况，即 j + dr - 1 >= A[i + 1] 时
                    error += (long) Math.abs(fx - gx) * (A[i + 1] - j);
                    ddr = dr - (A[i + 1] - j); //记录当前g(x)值相同的长度为r的段内，尚未计算error值部分的长度
                    flag = 1; //标记当前段内有尚未计算error值的部分
                }
            }
        }
        System.out.println(error);
    }
}

/*
import java.util.Scanner;

public class Main {
    //70分,运行超时
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), N = scanner.nextInt();
        int[] A = new int[n + 1];
        int r = N / (n + 1);
        for (int i = 1; i < n + 1; i++) {
            A[i] = scanner.nextInt();
        }
        int fx, gx;
        long error = 0;
        int flag = 0;
        for (int i = 0; i < N; i++) {
            gx = i / r;
            while (flag <= n && A[flag] <= i) {
                flag++;
            }
            fx = flag - 1;
            error += Math.abs(fx - gx);
        }
        System.out.println(error);
    }
}
*/

