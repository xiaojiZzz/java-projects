package ccf_csp._2018._12;

import java.util.Scanner;

public class _2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int r = input.nextInt();
        int y = input.nextInt();
        int g = input.nextInt();
        int n = input.nextInt();
        int k = 0, t = 0;
        long sum = 0, temp = 0;
        for (int i = 0; i < n; i++) {
            k = input.nextInt();
            t = input.nextInt();
            temp = 0;
            if (k == 0) {
                sum += t;
                continue;
            } else {
                if (k == 1) {
                    //红
                    temp = (sum - t + r) % (g + r + y);
                } else if (k == 2)
                    //黄
                    temp = (sum - t) % (g + r + y);
                else if (k == 3) {
                    //绿
                    temp = (sum - t + r + g) % (g + r + y);
                }
                if (temp <= r) {
                    //走到的时候为红灯，在(0,r]区间内
                    sum += r - temp;
                } else if (temp > r + g)
                    //走到的时候为黄灯，在(r+g,r+g+y]区间内
                    sum += (g + r + y) - temp + r;
            }
        }
        System.out.println(sum);
    }
}

/*
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int r = in.nextInt();
        int y = in.nextInt();
        int g = in.nextInt();
        int n = in.nextInt();
        long temp, time = 0;
        int k, t;
        for (int i = 0; i < n; i++) {
            k = in.nextInt();
            t = in.nextInt();
            if (k == 0) {
                time += t;
            } else if (k == 1) { // 出生时，为红灯
                if (time < t) { // 到达路口时，还是红灯
                    time += t - time; // 需等待红灯剩余显示时长
                } else { // 到达路口时，变成其他灯
                    temp = (time - t) % (r + y + g); // 对红绿黄总时长取模
                    if (temp < g) { // 到达路口时，变成绿灯
                        // 直接通过
                    } else { // 到达路口时，变成黄灯
                        time += r + y + g - temp;
                    }
                }
            } else if (k == 2) { // 出发时，显示黄灯
                if (time < t) { // 到达路口时，还是黄灯
                    time += (t - time) + r; // 需等待黄灯剩余显示时长+红灯时长
                } else { // 到达路口时，变成其他灯
                    temp = (time - t) % (r + y + g); // 对红绿黄总时长取模
                    if (temp < r) { // 到达路口时，变成红灯
                        time += r - temp; // 需等待红灯剩余显示时长
                    } else if (temp < r + g) { // 到达路口时，变成绿灯（之前有红灯）
                        // 直接通过
                    } else { // 到达路口时，变成黄灯（之前有红灯、绿灯）
                        time += (r + y + g - temp) + r; // 需等待黄灯剩余显示时长+红灯显示时长
                    }
                }
            } else { // 出发时，显示绿灯
                if (time < t) { // 到达路口时，还是绿灯
                    // 直接通过
                } else { // 到达路口时，变成其他灯
                    temp = (time - t) % (r + y + g); // 对红绿黄总时长取模
                    if (temp < y + r) { // 到达路口时，变成红灯（之前有黄灯）
                        time += y + r - temp; // 需等待红灯剩余显示时长
                    } else { // 到达路口时，变成绿灯（之前有黄灯、红灯）
                        // 直接通过
                    }
                }
            }
        }
        in.close();
        System.out.println(time);
    }
}
*/
