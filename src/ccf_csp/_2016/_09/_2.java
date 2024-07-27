package ccf_csp._2016._09;

import java.util.Arrays;
import java.util.Scanner;

public class _2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        boolean[] seats = new boolean[101];
        int[] leftSeats = new int[21];
        int[][] res = new int[n][5];
        Arrays.fill(leftSeats, 5);
        for (int i = 0; i < n; i++) {
            int need = scanner.nextInt();
            boolean flag = false;
            for (int j = 1; j <= 20; j++) {
                if (leftSeats[j] >= need) {
                    flag = true;
                    leftSeats[j] -= need;
                    int idx = (j - 1) * 5 + 1;
                    while (seats[idx]) {
                        idx++;
                    }
                    int id = 0;
                    for (int k = idx; k < idx + need; k++) {
                        seats[k] = true;
                        res[i][id++] = k;
                    }
                }
                if (flag) {
                    break;
                }
            }
            if (!flag) {
                int num = 0;
                for (int j = 1; j < 101; j++) {
                    if (num < need) {
                        if (!seats[j]) {
                            seats[j] = true;
                            res[i][num++] = j;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 5; j++) {
                if (res[i][j] != 0) {
                    System.out.print(res[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
