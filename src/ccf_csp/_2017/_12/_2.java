package ccf_csp._2017._12;

import java.util.LinkedList;
import java.util.Scanner;

public class _2 {
    //链表
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        LinkedList<Integer> mylist = new LinkedList<>();
        for (int i = 0; i < n; i++)
            mylist.add(i + 1);

        int num = 0, point = 0;
        while (mylist.size() > 1) {
            num++;
            if (point >= mylist.size())
                point = 0;
            if (num % k == 0 || num % 10 == k)  // 退出游戏
                mylist.remove(point);
            else // 往后指
                point++;
        }
        System.out.println(mylist.getFirst());
    }
}

/*
import java.util.Scanner;

public class Main {
    //数组标记
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] flag = new int[n+1];  // -1 表示已退出游戏
        int count = n, num = 0;     // count 表示人数，num 表示报数
        while(count > 1){
            for(int i = 1;i <= n;i++){
                if(flag[i] == 0){
                    num++;
                    if(num % k == 0 || num % 10 == k){
                        flag[i] = -1;
                        count--;
                        // 在循环途中结束游戏(不加这步90分，前两次90分原因)
                        if (count == 1) break;
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++)
            if (flag[i] != -1)
                System.out.println(i);
    }
}
*/

/*
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    //队列
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        Queue<Integer> myque = new LinkedList<>();
        for (int i = 0; i < n; i++)
            myque.add(i + 1);

        int num = 0;    // 表示报数
        while (myque.size() > 1) {
            num++;
            Integer peo = myque.remove();  // 出队
            if (num % k != 0 && num % 10 != k)  // 再入队
                myque.add(peo);
        }
        System.out.println(myque.peek());
    }
}
*/
