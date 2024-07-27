package ccf_csp._2017._12;

import java.util.Arrays;
import java.util.Scanner;

public class _1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        Arrays.sort(nums);
        int min = 10010;
        for (int i = 1; i < n; i++) {
            min = Math.min(Math.abs(nums[i] - nums[i - 1]), min);
        }
        System.out.println(min);
    }
}
