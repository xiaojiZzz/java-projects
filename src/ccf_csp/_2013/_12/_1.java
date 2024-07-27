package ccf_csp._2013._12;

import java.util.*;

public class _1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int t = scanner.nextInt();
            map.put(t, map.getOrDefault(t, 0) + 1);
        }
        Set<Map.Entry<Integer, Integer>> entrySet = map.entrySet();
        int max = 0;
        int res = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> entry : entrySet) {
            if (entry.getValue() >= max) {
                res = entry.getValue() == max ? Math.min(res, entry.getKey()) : entry.getKey();
                max = entry.getValue();
            }
        }
        System.out.println(res);
    }
}
