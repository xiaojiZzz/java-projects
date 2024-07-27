package ccf_csp._2014._03;

import java.util.Arrays;
import java.util.Scanner;

public class _1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }
        Arrays.sort(array);
        int left = 0, right = n - 1;
        if (array[0] >= 0) {
            System.out.println(0);
        }
        int count = 0;
        while (left < right) {
            if (array[left] + array[right] == 0) {
                count++;
                left++;
                right--;
            } else if (array[left] + array[right] > 0) {
                right--;
            } else {
                left++;
            }
        }
        System.out.println(count);
    }
}
