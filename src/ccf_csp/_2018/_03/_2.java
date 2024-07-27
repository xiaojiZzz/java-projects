package ccf_csp._2018._03;

import java.util.Scanner;

public class _2 {

    public static class ball {
        int location;
        int direction; //0为向左，1为向右

        public ball(int location, int direction) {
            this.location = location;
            this.direction = direction;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), L = scanner.nextInt(), t = scanner.nextInt();
        int[] line = new int[L + 1];
        ball[] balls = new ball[n];
        for (int i = 0; i < n; i++) {
            balls[i] = new ball(scanner.nextInt(), 1);
            line[balls[i].location]++;
        }
        for (int i = 0; i < t; i++) {
            for (int j = 0; j < n; j++) {
                if (balls[j].direction == 1) {
                    if (balls[j].location < L) {
                        line[balls[j].location++]--;
                        line[balls[j].location]++;
                    } else {
                        balls[j].direction = 0;
                        line[balls[j].location--]--;
                        line[balls[j].location]++;
                    }
                } else {
                    if (balls[j].location > 0) {
                        line[balls[j].location--]--;
                        line[balls[j].location]++;
                    } else {
                        balls[j].direction = 1;
                        line[balls[j].location++]--;
                        line[balls[j].location]++;
                    }
                }
            }
            for (int j = 0; j < L + 1; j++) {
                if (line[j] > 1) {
                    for (int k = 0; k < n; k++) {
                        if (balls[k].location == j) {
                            balls[k].direction = (balls[k].direction + 1) % 2;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.print(balls[i].location + " ");
        }
    }
}
