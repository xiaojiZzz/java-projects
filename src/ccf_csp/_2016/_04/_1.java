package ccf_csp._2016._04;

import java.util.Scanner;

public class _1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] ints = new int[n];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            ints[i] = scanner.nextInt();
        }
        for (int i = 1; i < n - 1; i++) {
            if ((ints[i - 1] < ints[i] && ints[i] > ints[i + 1]) || (ints[i - 1] > ints[i] && ints[i] < ints[i + 1])) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
