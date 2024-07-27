package ccf_csp._2015._09;

import java.util.Scanner;

public class _1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        int left = 0, right = 0, count = 0;
        for (; right < n; right++) {
            if (arr[left] == arr[right]) {
                continue;
            } else {
                left = right;
                count++;
            }
        }
        System.out.println(++count);
    }
}
