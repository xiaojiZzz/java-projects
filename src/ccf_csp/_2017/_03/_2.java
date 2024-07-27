package ccf_csp._2017._03;

import java.util.LinkedList;
import java.util.Scanner;

public class _2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), m = scanner.nextInt();
        LinkedList<Integer> myList = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            myList.add(i + 1);
        }
        for (int i = 0; i < m; i++) {
            int num = scanner.nextInt();
            int move = scanner.nextInt();
            int index = myList.indexOf(num);
            int tmp = myList.get(index);
            int index2 = index + move;
            if (move > 0) {
                myList.add(index2 + 1, tmp);
                myList.remove(index);
            } else {
                myList.add(index2, tmp);
                myList.remove(index + 1);
            }
        }
        for (int i = 0; i < myList.size(); i++) {
            System.out.print(myList.get(i) + " ");
        }
    }
}
