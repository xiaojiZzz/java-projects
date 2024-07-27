package ccf_csp._2022._03;

import java.util.Scanner;

public class _2 {
    //会超时
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), m = scanner.nextInt(), k = scanner.nextInt();
        int[][] plans = new int[n][2];
        int[] q = new int[m];
        for (int i = 0; i < n; i++) {
            plans[i][0] = scanner.nextInt();
            plans[i][1] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            q[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                int start = q[i] + k;
                int end = start + plans[j][1] - 1;
                if (plans[j][0] >= start && plans[j][0] <= end) {
                    cnt++;
                }
            }
            q[i] = cnt;
        }
        for (int i = 0; i < m; i++) {
            System.out.println(q[i]);
        }
    }
}

/*
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), m = scanner.nextInt(), k = scanner.nextInt();
        int[] qLeft = new int[200001];
        int[] qRight = new int[200001];
        int[] res = new int[m];
        for (int i = 0; i < n; i++) {
            int t = scanner.nextInt(), c = scanner.nextInt();
            if (k <= t) {
                int l = Math.max(0, t - c - k + 1);
                int r = t - k + 1; //左闭又开
                qLeft[l]++;
                qRight[r]++;
            }
        }
        for (int i = 1; i < 200001; i++) {
            qLeft[i] += qLeft[i - 1];
            qRight[i] += qRight[i - 1];
        }
        for (int i = 0; i < m; i++) {
            int q = scanner.nextInt();
            res[i] = qLeft[q] - qRight[q];
        }
        for (int i = 0; i < m; i++) {
            System.out.println(res[i]);
        }
    }
}
*/

/*
public class Main {
    //简化上面的版本
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), m = scanner.nextInt(), k = scanner.nextInt();
        int[] Q = new int[200001];
        int[] res = new int[m];
        for (int i = 0; i < n; i++) {
            int t = scanner.nextInt(), c = scanner.nextInt();
            if (k <= t) {
                int l = Math.max(0, t - c - k + 1);
                int r = t - k + 1; //左闭又开
                Q[l]++;
                Q[r]--;
            }
        }
        for (int i = 1; i < 200001; i++) {
            Q[i] += Q[i - 1];
        }
        for (int i = 0; i < m; i++) {
            int q = scanner.nextInt();
            res[i] = Q[q];
        }
        for (int i = 0; i < m; i++) {
            System.out.println(res[i]);
        }
    }
}
*/
