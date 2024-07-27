package ccf_csp._2018._09;

import java.util.Scanner;

public class _2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n  = scanner.nextInt();
        int[] times = new int[1000001];
        int chat = 0;
        for (int i = 0; i < n; i++) {
            int s = scanner.nextInt();
            int e = scanner.nextInt();
            for (int j = s; j < e; j++) {
                times[j]++;
            }
        }
        for (int i = 0; i < n; i++) {
            int s = scanner.nextInt();
            int e = scanner.nextInt();
            for (int j = s; j < e; j++) {
                times[j]++;
            }
        }
        for (int i = 0; i < times.length; i++) {
            if (times[i] == 2) {
                chat++;
            }
        }
        System.out.println(chat);
    }
}
