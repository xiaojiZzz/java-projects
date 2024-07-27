package algorithm_lesson.chapter02;

@SuppressWarnings({"all"})
//算法分析题2-13
public class Analysis2_13 {
    public void quickSort(int a[], int low, int high) {
        if (low < high) {
            int pivotpos = partition(a, low, high);
            quickSort(a, low, pivotpos - 1);
            quickSort(a, pivotpos + 1, high);
        }
    }

    public int partition(int a[], int low, int high) {
        int pivot = a[low];
        while (low < high) {
            while (low < high && a[high] <= pivot) {
                high--;
            }
            a[low] = a[high];
            while (low < high && a[low] >= pivot) {
                low++;
            }
            a[high] = a[low];
        }
        a[low] = pivot;
        return low;
    }
}
