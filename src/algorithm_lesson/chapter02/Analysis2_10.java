package algorithm_lesson.chapter02;

@SuppressWarnings({"all"})
//算法分析题2-10
public class Analysis2_10 {
    public void mergeSort(int[] a, int low, int high) {
        if (low < high) {
            int i = (int) Math.sqrt(high - low + 1);
            if (i > 1) {
                for (int j = 0; j < i; j++) {
                    mergeSort(a, low + i * j, low + (1 + j) * i - 1);
                }
                mergeSort(a, low + i * i, high);
            }
            merge(a, low, i, high);
            //mergeAll(a, low, high); 用于归并跟号n个排好序的数组段
        }
    }

    public void merge(int[] a, int low, int mid, int high) {
        int[] b = new int[high - low + 1];
        int i = 0;
        int l = low;
        int r = mid + 1;
        while (l <= mid && r <= high) {
            b[i++] = a[l] <= a[r] ? a[l++] : a[r++];
        }
        while (l <= mid) {
            b[i++] = a[l++];
        }
        while (r <= high) {
            b[i++] = a[r++];
        }
        for (i = 0; i < b.length; i++) {
            a[low + i] = b[i];
        }
    }

    public static void main(String[] args) {
        int[] a = {3, 43, 53, 1, 2132, 42, 32, 12};
        new Analysis2_10().mergeSort(a, 0, a.length - 1);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }
}
