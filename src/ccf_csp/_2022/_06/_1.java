package ccf_csp._2022._06;

import java.util.Scanner;

public class _1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        double mean = 0, var = 0, sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        mean = sum / n;
        sum = 0;
        for (int i = 0; i < n; i++) {
            sum += (nums[i] - mean) * (nums[i] - mean);
        }
        var = Math.sqrt(sum / n);
        for (int i = 0; i < n; i++) {
            System.out.println((nums[i] - mean) / var);
        }
    }
}
