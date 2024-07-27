package ccf_csp._2014._12;

import java.util.HashMap;
import java.util.Scanner;

public class _1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] number = new int[n];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int id = scanner.nextInt();
            map.put(id, map.getOrDefault(id, 0) + 1);
            number[i] = map.get(id);
        }
        System.out.print(number[0]);
        for (int i = 1; i < n; i++) {
            System.out.print(" ");
            System.out.print(number[i]);
        }
    }
}
