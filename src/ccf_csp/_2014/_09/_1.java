package ccf_csp._2014._09;

import java.util.Arrays;
import java.util.Scanner;

public class _1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = scanner.nextInt();
        }
        Arrays.sort(data);
        int count = 0;
        for (int i = 0; i < n - 1; i++) {
            if (data[i + 1] - data[i] == -1 || data[i + 1] - data[i] == 1) {
                count++;
            }
        }
        System.out.println(count);
    }
}
