package algorithm_lesson.chapter02;

@SuppressWarnings({"all"})
//归并排序
public class MergeSort {
    public void mergeSort(int[] a, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort(a, low, mid);
            mergeSort(a, mid + 1, high);
            merge(a, low, mid, high);
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
}
