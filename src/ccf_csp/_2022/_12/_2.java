package ccf_csp._2022._12;

import java.util.Scanner;

public class _2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), m = scanner.nextInt();
        int[] rely = new int[m + 1];
        int[] days = new int[m + 1];
        int[] minTime = new int[m + 1];
        int[] maxTime = new int[m + 1];
        boolean flag = true;
        for (int i = 1; i <= m; i++) {
            rely[i] = scanner.nextInt();
        }
        for (int i = 1; i <= m; i++) {
            days[i] = scanner.nextInt();
        }
        for (int i = 1; i <= m; i++) {
            if (rely[i] == 0) {
                minTime[i] = 1;
            } else {
                minTime[i] = minTime[rely[i]] + days[rely[i]];
                if (minTime[i] + days[i] - 1 > n) {
                    flag = false;
                }
            }
        }
        for (int i = 1; i <= m; i++) {
            System.out.print(minTime[i] + " ");
        }
        System.out.println();
        if (flag) {
            for (int i = m; i > 0; i--) {
                int tmp = 777;
                for (int j = i + 1; j <= m; j++) {
                    if (rely[j] == i) {
                        tmp = Math.min(tmp, maxTime[j]);
                    }
                }
                if (tmp == 777) {
                    maxTime[i] = n - days[i] + 1;
                } else {
                    maxTime[i] = tmp - days[i];
                }
            }
            for (int i = 1; i <= m; i++) {
                System.out.print(maxTime[i] + " ");
            }
        }
    }
}
