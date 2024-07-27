package ccf_csp._2022._03;

import java.util.HashSet;
import java.util.Scanner;

public class _1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), k = scanner.nextInt();
        int[][] nums = new int[k][2];
        HashSet<Integer> set = new HashSet<>();
        set.add(0);
        int cnt = 0;
        for (int i = 0; i < k; i++) {
            nums[i][0] = scanner.nextInt();
            nums[i][1] = scanner.nextInt();
        }
        for (int i = 0; i < k; i++) {
            if (!set.contains(nums[i][1])) {
                cnt++;
                set.add(nums[i][0]);
            } else {
                set.add(nums[i][0]);
            }
        }
        System.out.println(cnt);
    }
}
