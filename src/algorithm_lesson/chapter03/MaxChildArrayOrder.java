package algorithm_lesson.chapter03;

@SuppressWarnings({"all"})
//数组最大不连续递增子序列
public class MaxChildArrayOrder {
    public static int maxChildArrayOrder(int[] a) {
        int n = a.length;
        int temp[] = new int[n];
        for (int i = 0; i < n; i++) {
            temp[i] = 1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (a[i] > a[j] && temp[j] + 1 > temp[i]) {
                    temp[i] = temp[j] + 1;
                }
            }
        }
        int max = temp[0];
        for (int i = 1; i < n; i++) {
            if (temp[i] > max) {
                max = temp[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] a = {3, 1, 4, 1, 5, 9, 2, 6, 5};
        int max = maxChildArrayOrder(a);
        System.out.println(max);
    }
}
