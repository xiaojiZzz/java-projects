package ccf_csp._2023._03;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class _2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int k = scanner.nextInt();

        Map<Integer, Integer> blocks = new HashMap<>();
        int min_t = Integer.MAX_VALUE;
        int max_t = 0;
        for (int i = 0; i < n; i++) {
            int t = scanner.nextInt();
            int c = scanner.nextInt();
            if (t < min_t) {
                min_t = t;
            }
            if (t > max_t) {
                max_t = t;
            }
            blocks.put(t, blocks.getOrDefault(t, 0) + c);
        }

        int cost = 0;
        int temp = 0;
        for (int i = max_t; i >= k - 1; i--) {
            if (m - cost < blocks.getOrDefault(i, 0)) {
                System.out.println(i);
                return;
            }
            if (blocks.containsKey(i)) {
                temp += blocks.get(i);
            }
            cost += (blocks.getOrDefault(i, 0) + temp);
        }
        System.out.println(k);
    }
}
