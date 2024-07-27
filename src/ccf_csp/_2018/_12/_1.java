package ccf_csp._2018._12;

import java.util.Scanner;

public class _1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int r = scanner.nextInt(), y = scanner.nextInt(), g = scanner.nextInt();
        int n = scanner.nextInt();
        int[][] times = new int[n][2];
        for (int i = 0; i < n; i++) {
            times[i][0] = scanner.nextInt();
            times[i][1] = scanner.nextInt();
        }
        int time = 0;
        for (int i = 0; i < n; i++) {
            if (times[i][0] == 0 || times[i][0] == 1) {
                time += times[i][1];
            } else if (times[i][0] == 2) {
                time += times[i][1];
                time += r;
            }
        }
        System.out.println(time);
    }
}
