package ccf_csp._2019._12;

import java.util.Scanner;

public class _1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] times = new int[4];
        int cnt = 0;
        int nums = 1;
        boolean flag = true;
        while (flag) {
            if (nums % 7 == 0 || hasSeven(nums)) {
                times[(nums - 1) % 4]++;
                nums++;
            } else {
                cnt++;
                nums++;
            }
            if (cnt >= n) {
                flag = false;
            }
        }
        for (int i = 0; i < 3; i++) {
            System.out.println(times[i]);
        }
        System.out.print(times[3]);
    }

    public static boolean hasSeven(int n) {
        String s = String.valueOf(n);
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '7') {
                return true;
            }
        }
        return false;
    }
}
