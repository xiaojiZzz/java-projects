package ccf_csp._2019._03;

import java.text.DecimalFormat;
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
        int max = 10086, min = 10086, temp= 10086;
        double mid  = 0.0;
        Arrays.sort(nums);
        max = nums[n -1];
        min = nums[0];
        if (n % 2 == 1) {
            mid = nums[n / 2];
            temp = nums[n / 2];
        } else {
            if ((nums[n / 2] + nums[n / 2 - 1]) % 2 == 0) {
                mid = (nums[n / 2] + nums[n / 2 - 1]) / 2;
                temp = (nums[n / 2] + nums[n / 2 - 1]) / 2;
            } else {
                mid = (nums[n / 2] + nums[n / 2 - 1]) / 2.0;
                DecimalFormat decimalFormat = new DecimalFormat("#.0");
                String s = decimalFormat.format(mid);
                mid = Double.parseDouble(s);
            }
        }
        System.out.print(max);
        System.out.print(" ");
        if (temp != 10086) {
            System.out.print(temp);
        } else {
            System.out.print(mid);
        }
        System.out.print(" ");
        System.out.print(min);
    }
}
